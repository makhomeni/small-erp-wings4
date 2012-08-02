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

@Path("/api/product")
class ProductResource {
    
    def productResourceService
    def id
    
    @GET
    @Produces(["application/xml"])
    Response read() {
        ok productResourceService.read(id)
    }
    
    @PUT
    Response update(Product dto) {
        dto.id = id
        ok productResourceService.update(dto)
    }
    
    @DELETE
    void delete() {
        productResourceService.delete(id)
    }
    
}

