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

