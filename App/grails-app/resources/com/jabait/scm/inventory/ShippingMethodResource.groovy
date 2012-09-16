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

@Path("/api/shippingMethod")
class ShippingMethodResource {

    def shippingMethodResourceService
    def id

    @GET
    @Path("/{id}")
    Response read() {
        ok shippingMethodResourceService.read(id)
    }

    @GET
    Response readAll(){
        ok shippingMethodResourceService.readAll();
    }

    @PUT
    Response update(ShippingMethod dto) {
        dto.id = id
        ok shippingMethodResourceService.update(dto)
    }

    @DELETE
    void delete() {
        shippingMethodResourceService.delete(id)
    }

}

