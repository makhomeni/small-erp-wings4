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
import javax.ws.rs.PathParam
import javax.ws.rs.POST
import org.json.JSONObject
import com.jabait.scm.inventory.Product
import com.jabait.scm.inventory.InventoryRegister

@Path("/api/sales")
class SalesResource {

    def salesResourceService
    def id

    @GET
    @Path("/{id}")
    Response read(@PathParam("id") Long id) {
        ok salesResourceService.read(id)
    }

    @GET
    Response readAll(){
        Map<String,Object> salesObject;
        List salesList = new ArrayList();
        List<Sales> salesSet = Sales.list();
        for (Sales sales: salesSet) {
            salesObject = new HashMap<String,Object>();
            salesObject.put("id",sales.id);
            salesObject.put("customerName", sales.customer.firstName + " " + sales.customer.lastName);
            salesObject.put("productName", sales.salesOrder.product.productName);
            salesObject.put("salesDate", sales.salesDate);
            salesObject.put("quantity", sales.quantity);
            salesObject.put("price", sales.price);
            salesObject.put("salesOrder", sales.salesOrder.jobName);
            salesObject.put("salesType", sales.salesType);

            salesList.add(salesObject);
        }
        
        ok salesList;
    }

    @PUT
    Response update(Sales dto) {
        dto.id = id
        ok salesResourceService.update(dto)
    }

    @DELETE
    void delete() {
        salesResourceService.delete(id)
    }

    @POST
    Response createSales(String salesJson){
        JSONObject jsonObject = new JSONObject(salesJson);
        Sales sales = new Sales();
        sales.customer = Customer.get(Integer.parseInt(jsonObject.get("customerName").toString()));
        sales.salesOrder = SalesOrder.get(Integer.parseInt(jsonObject.get("salesOrder").toString()))
        sales.price = Integer.parseInt(jsonObject.get("price").toString());
        sales.quantity = Integer.parseInt(jsonObject.get("quantity").toString());
        sales.salesType = jsonObject.get("salesType").toString();
        sales.salesDate = new Date();

        Product product = sales.salesOrder.product;

        InventoryRegister inventoryRegister1 = InventoryRegister.findByProduct(product);

        if(inventoryRegister1){
            InventoryRegister inventoryRegisterUpdate = InventoryRegister.get(inventoryRegister1.id);
            inventoryRegister1.onHand = inventoryRegister1.onHand - sales.salesOrder.orderQuantity;
            def newValue = inventoryRegister1.onSalesOrder + sales.salesOrder.orderQuantity;
            inventoryRegisterUpdate.onSalesOrder = newValue;
            if (inventoryRegisterUpdate.save(flush: true)) {
                println "updated";
            } else {
                println "not saved inventory";
            }
        }else{

        }


        if(sales.save(flush: true)){
            println "saved";
            ok sales;
        }else{

            println "not saved";
            sales.errors.each {
                println it;
            }
            ok sales;
        }
    }

}

