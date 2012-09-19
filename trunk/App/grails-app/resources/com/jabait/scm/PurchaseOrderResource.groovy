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

@Path('/api/purchaseOrder')
@Consumes(['application/xml', 'application/json'])
@Produces(['application/xml', 'application/json'])
class PurchaseOrderResource {

    def purchaseOrderResourceService
    def id

    @GET
    Response read() {
        ok purchaseOrderResourceService.read(id)
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

        JSONObject jsonObject = new JSONObject(purchaseOrderJson);
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.organization = Organization.get(Integer.parseInt(jsonObject.get("organizationId").toString()));
        purchaseOrder.shippingMethod = ShippingMethod.get(Integer.parseInt(jsonObject.get("shippingMethodId").toString()));
        purchaseOrder.paymentTerm = PaymentTerm.get(Integer.parseInt(jsonObject.get("paymentTermId").toString()));
        purchaseOrder.shippingAddress = jsonObject.get("shippingAddress");
        purchaseOrder.vendor = Vendor.get(Integer.parseInt(jsonObject.get("vendorId").toString()));
        purchaseOrder.createdBy = User.get(1);
        purchaseOrder.createdDate = new Date();
        purchaseOrder.deliveryTerm = DeliveryTerm.get(Integer.parseInt(jsonObject.get("deliveryTermId").toString()));
        purchaseOrder.dueDate = new Date();
        purchaseOrder.isArchived = false;
        purchaseOrder.isSent = false;
        purchaseOrder.jobName = "purchase order";
        purchaseOrder.orderQuantity = Integer.parseInt(jsonObject.get("orderQuantity").toString());
        purchaseOrder.priority = 1;

        purchaseOrder.status = 1;
        created purchaseOrder.save();
//        if (purchaseOrder.save()) {
//            println "saved";
//        } else {
//            purchaseOrder.errors.each {
//                println it;
//            }
//            println "not saved";
//        }


    }

}

