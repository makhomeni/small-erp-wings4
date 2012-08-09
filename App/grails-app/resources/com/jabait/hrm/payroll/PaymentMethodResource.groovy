package com.jabait.hrm.payroll

import static org.grails.jaxrs.response.Responses.*

import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.Produces
import javax.ws.rs.PUT
import javax.ws.rs.core.Response

import org.grails.jaxrs.provider.DomainObjectNotFoundException
import javax.ws.rs.Path

@Path("/api/payment")
class PaymentMethodResource {
    
    def paymentMethodResourceService
    def id
    
    @GET
    @Path("/{id}")
    Response read() {
        ok paymentMethodResourceService.read(id)
    }

    @GET
    Response readAll(){
        ok paymentMethodResourceService.readAll();
    }
    
    @PUT
    Response update(PaymentMethod dto) {
        dto.id = id
        ok paymentMethodResourceService.update(dto)
    }
    
    @DELETE
    void delete() {
        paymentMethodResourceService.delete(id)
    }
    
}

