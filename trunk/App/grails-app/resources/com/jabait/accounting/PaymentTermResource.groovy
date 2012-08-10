package com.jabait.accounting

import static org.grails.jaxrs.response.Responses.*

import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.Produces
import javax.ws.rs.PUT
import javax.ws.rs.core.Response

import org.grails.jaxrs.provider.DomainObjectNotFoundException
import javax.ws.rs.Path

@Path("/api/paymentTerm")
class PaymentTermResource {
    
    def paymentTermResourceService
    def id

    @GET
    @Path("/{id}")
    Response read() {
        ok paymentTermResourceService.read(id)
    }

    @GET
    Response readAll(){
        ok paymentTermResourceService.readAll();
    }
    
    @PUT
    Response update(PaymentTerm dto) {
        dto.id = id
        ok paymentTermResourceService.update(dto)
    }
    
    @DELETE
    void delete() {
        paymentTermResourceService.delete(id)
    }
    
}

