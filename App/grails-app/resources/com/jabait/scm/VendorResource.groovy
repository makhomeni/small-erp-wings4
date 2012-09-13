package com.jabait.scm

import static org.grails.jaxrs.response.Responses.*

import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.Produces
import javax.ws.rs.PUT
import javax.ws.rs.core.Response

import org.grails.jaxrs.provider.DomainObjectNotFoundException
import javax.xml.ws.Response
import javax.ws.rs.Path
import javax.ws.rs.core.Response
import javax.ws.rs.PathParam
import com.jabait.hrm.Organization


@Path('/api/vendor')
class VendorResource {

    def vendorResourceService
    def id

    @GET
    @Path("/{id}")
    Response read(@PathParam("id") Long id) {
        ok vendorResourceService.read(id)
    }


    @GET
    Response readAll(){
        ok vendorResourceService.readAll();
    }

    @PUT
    Response update(Organization dto) {
        dto.id = id
        ok vendorResourceService.update(dto)
    }

    @DELETE
    void delete() {
        vendorResourceService.delete(id)
    }

}

