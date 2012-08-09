package com.jabait.scm.inventory

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

@Path('/api/inventoryRegister')
class InventoryRegisterResource {

    def inventoryRegisterResourceService
    def id

    @POST
    @Consumes(["application/json"])
    @Produces(["application/json"])
    Response createInventory(String inventory){
         JSONObject inventoryObject = new JSONObject(inventory);
        Product product = Product.get(Integer.parseInt(inventoryObject.get("product").toString()));
        Integer onHand =  Integer.parseInt(inventoryObject.get("onHand").toString());
        Integer onOrder = Integer.parseInt(inventoryObject.get("onOrder").toString());;
        Integer allocated = Integer.parseInt(inventoryObject.get("allocated").toString());;;
        Integer committed = Integer.parseInt(inventoryObject.get("committed").toString());;;
        Integer unavailable = Integer.parseInt(inventoryObject.get("unavailable").toString());;;
        Integer backOrdered = Integer.parseInt(inventoryObject.get("backOrdered").toString());;;
        Integer dropShip = Integer.parseInt(inventoryObject.get("dropShip").toString());;;
        Integer availableForSale = Integer.parseInt(inventoryObject.get("availableForSale").toString());;;
        Integer availableToPick = Integer.parseInt(inventoryObject.get("availableToPick").toString());;;

        InventoryRegister inventoryRegister = new InventoryRegister();
        inventoryRegister.product = product;
        inventoryRegister.onHand = onHand;
        inventoryRegister.onOrder = onOrder;
        inventoryRegister.allocated = allocated;
        inventoryRegister.committed = committed;
        inventoryRegister.unavailable = unavailable;
        inventoryRegister.backOrdered = backOrdered;
        inventoryRegister.dropShip = dropShip;
        inventoryRegister.availableForSale = availableForSale;
        inventoryRegister.availableToPick = availableToPick;

        created inventoryRegister.save();
    }

    @GET
    Response read() {
        ok inventoryRegisterResourceService.read(id)
    }

    @PUT
    Response update(InventoryRegister dto) {
        dto.id = id
        ok inventoryRegisterResourceService.update(dto)
    }

    @DELETE
    void delete() {
        inventoryRegisterResourceService.delete(id)
    }

}

