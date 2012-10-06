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
import com.jabait.scm.inventory.InventoryRegister
import com.jabait.scm.inventory.Product

@Path('/api/purchase')
class PurchaseResource {

    def purchaseResourceService
    def id

    @GET
    @Path("/{id}")
    Response read(@PathParam("id") Long id) {
        ok purchaseResourceService.read(id)
    }

    @GET
    Response readAll(){
        ok purchaseResourceService.readAll();
    }

    @PUT
    Response update(Purchase dto) {
        dto.id = id
        ok purchaseResourceService.update(dto)
    }

    @DELETE
    void delete() {
        purchaseResourceService.delete(id)
    }

    @POST
    Response createPurchase(String purchaseJson){

        JSONObject jsonObject = new JSONObject(purchaseJson);
        Purchase purchase = new Purchase();
        purchase.vendor = Vendor.get(Integer.parseInt(jsonObject.get("vendor").toString()));
        purchase.purchaseOrder = PurchaseOrder.get(Integer.parseInt(jsonObject.get("purchaseOrder").toString()))
        purchase.price = Integer.parseInt(jsonObject.get("price").toString());
        purchase.quantity = Integer.parseInt(jsonObject.get("quantity").toString());
        purchase.purchaseType = jsonObject.get("purchaseType").toString();
        purchase.purchaseDate = new Date();

        Product product = purchase.purchaseOrder.product;

        InventoryRegister inventoryRegister1 = InventoryRegister.findByProduct(product);

        if(inventoryRegister1){
            println "got inventory";
            InventoryRegister inventoryRegisterUpdate = InventoryRegister.get(inventoryRegister1.id);
//            inventoryRegister1.onHand = inventoryRegister1.onHand + orderQuantity;
            def newValue = inventoryRegister1.onPurchaseOrder + purchase.purchaseOrder.orderQuantity;
            inventoryRegisterUpdate.onPurchaseOrder = newValue;
            if (inventoryRegisterUpdate.save(flush: true)) {
                println "updated";
            } else {
                println "not saved inventory";
            }
        }else{
            InventoryRegister inventoryRegister = new InventoryRegister();
            inventoryRegister.product =  product;
            inventoryRegister.onHand += purchase.purchaseOrder.orderQuantity;
            inventoryRegister.onPurchaseOrder = purchase.purchaseOrder.orderQuantity;
            if (inventoryRegister.save()) {
                println "saved successfully";
            } else {
                inventoryRegister.errors.each {
                    println it;
                }
            }
        }


        if(purchase.save()){
            println "saved";
            ok purchase;
        }else{
            
            println "not saved";
            purchase.errors.each {
                println it;
            }
            ok purchase;
        }
    }

}

