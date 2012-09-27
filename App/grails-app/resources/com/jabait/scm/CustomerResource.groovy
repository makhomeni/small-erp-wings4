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

@Path('/api/customer')
class CustomerResource {
    
    def customerResourceService
    def id
    
    @GET
    @Path("/{id}")
    Response read(@PathParam("id") Long id) {
        ok customerResourceService.read(id);
    }

    @GET
    Response readAll(){
        println "in customer";
        ok customerResourceService.readAll();
    }
    
    @PUT
    Response update(Customer dto) {
        dto.id = id
        ok customerResourceService.update(dto)
    }
    
    @DELETE
    void delete() {
        customerResourceService.delete(id)
    }
    
}

