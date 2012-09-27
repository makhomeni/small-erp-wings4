package com.jabait.scm

import static org.grails.jaxrs.response.Responses.*

import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.Produces
import javax.ws.rs.PUT
import javax.ws.rs.core.Response

import org.grails.jaxrs.provider.DomainObjectNotFoundException
import javax.ws.rs.Path
import javax.ws.rs.POST
import org.json.JSONObject
import com.jabait.hrm.Organization
import com.jabait.accounting.PaymentTerm
import com.jabait.security.User
import com.jabait.scm.inventory.ShippingMethod
import javax.ws.rs.PathParam
import com.jabait.scm.inventory.Product
import com.jabait.scm.inventory.InventoryRegister

@Path('/api/purchaseOrder')
@Consumes(['application/xml', 'application/json'])
@Produces(['application/xml', 'application/json'])
class PurchaseOrderResource {

    def purchaseOrderResourceService
    def id


    @GET
    @Path("/{id}")
    Response read(@PathParam("id") Long id) {
           ok purchaseOrderResourceService.read(id)
    }

    @GET
    Response readAll(){
        List<PurchaseOrder> purchaseOrders = purchaseOrderResourceService.readAll();
        Map<String,Object> purchaseOrderMap;
        List<Map<String,Object>> allPurchaseOrders = new ArrayList<Map<String,Object>>();
        for (PurchaseOrder purchaseOrder: purchaseOrders) {
            purchaseOrderMap = new HashMap<String,Object>();
            purchaseOrderMap.put("id", purchaseOrder.id);
            purchaseOrderMap.put("vendor", purchaseOrder.vendor);
            purchaseOrderMap.put("organization", purchaseOrder.organization);
//            purchaseOrderMap.put("parentCategory", category?.parentCategory?.categoryName);

            allPurchaseOrders.add(purchaseOrderMap);
        }
        ok allPurchaseOrders;
    }

    @PUT
    Response update(PurchaseOrder dto) {
        dto.id = id
        ok purchaseOrderResourceService.update(dto)
    }

    @DELETE
    void delete() {
        purchaseOrderResourceService.delete(id)
    }

    @POST
    Response create(String purchaseOrderJson){
//        println "in web";

        JSONObject jsonObject = new JSONObject(purchaseOrderJson);
        PurchaseOrder purchaseOrder = new PurchaseOrder();
//        println "orga id = "+jsonObject.get("organizationId").toString()
        purchaseOrder.organization = Organization.get(Integer.parseInt(jsonObject.get("organizationId").toString()));

//        println "shippingMethodId id = "+jsonObject.get("shippingMethodId").toString()
        purchaseOrder.shippingMethod = ShippingMethod.get(Integer.parseInt(jsonObject.get("shippingMethodId").toString()));

//        println "paymentTermId id = "+jsonObject.get("paymentTermId").toString()
        purchaseOrder.paymentTerm = PaymentTerm.get(Integer.parseInt(jsonObject.get("paymentTermId").toString()));
        purchaseOrder.shippingAddress = jsonObject.get("shippingAddress");

//        println "vendorId id = "+jsonObject.get("vendorId").toString()
        purchaseOrder.vendor = Vendor.get(Integer.parseInt(jsonObject.get("vendorId").toString()));
        Product product = Product.get(Integer.parseInt(jsonObject.get("productId").toString()));
        purchaseOrder.product = product;

        purchaseOrder.createdBy = User.get(1);
        purchaseOrder.createdDate = new Date();

//        println "deliveryTermId id = "+jsonObject.get("deliveryTermId").toString()
        purchaseOrder.deliveryTerm = DeliveryTerm.get(Integer.parseInt(jsonObject.get("deliveryTermId").toString()));

        purchaseOrder.dueDate = new Date();
        purchaseOrder.isArchived = false;
        purchaseOrder.isSent = false;
        purchaseOrder.jobName = jsonObject.get("jobName");
        Integer orderQuantity =  Integer.parseInt(jsonObject.get("orderQuantity").toString());
        purchaseOrder.orderQuantity = orderQuantity;
        purchaseOrder.priority = 1;

        purchaseOrder.status = 1;

        InventoryRegister inventoryRegister = new InventoryRegister();
        inventoryRegister.product =  product;
        inventoryRegister.onHand = orderQuantity;
        inventoryRegister.onPurchaseOrder = orderQuantity;

        if (inventoryRegister.save()) {
            println "saved successfully";
        } else {
            inventoryRegister.errors.each {
                println it;
            }
        }
        

        if (purchaseOrder.save()) {
            println "saved";
            created true;
        } else {
            purchaseOrder.errors.each {
                println it;
            }
            println "not saved";
            created false;
        }


    }

}

