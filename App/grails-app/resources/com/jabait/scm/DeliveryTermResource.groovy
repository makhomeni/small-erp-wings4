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

@Path("api/delivery")
class DeliveryTermResource {
    
    def deliveryTermResourceService
    def id
    
    @GET
    @Path("/{id}")
    Response read() {
        ok deliveryTermResourceService.read(id)
    }

    @GET
    Response readAll(){
        ok deliveryTermResourceService.readAll();
    }
    
    @PUT
    Response update(DeliveryTerm dto) {
        dto.id = id
        ok deliveryTermResourceService.update(dto)
    }
    
    @DELETE
    void delete() {
        deliveryTermResourceService.delete(id)
    }
    
}

