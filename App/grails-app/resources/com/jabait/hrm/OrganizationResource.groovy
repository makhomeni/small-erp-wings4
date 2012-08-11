package com.jabait.hrm

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

@Path("/api/organization")
class OrganizationResource {

    def organizationResourceService
    def id

    @GET
    @Path("/{id}")
    Response read(@PathParam("id") Long id) {
        ok organizationResourceService.read(id)
    }


    @GET
    Response readAll(){
        ok organizationResourceService.readAll();
    }

    @PUT
    Response update(Organization dto) {
        dto.id = id
        ok organizationResourceService.update(dto)
    }

    @DELETE
    void delete() {
        organizationResourceService.delete(id)
    }

}

