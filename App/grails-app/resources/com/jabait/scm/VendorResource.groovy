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


@Path('/api/vendor')
@Consumes(['application/xml', 'application/json'])
@Produces(['application/xml', 'application/json'])
class VendorResource {

    def vendorResourceService
    def id

    @GET
    Response read() {
        ok vendorResourceService.read(id)
    }

    @GET
    Response readAll() {
        ok vendorResourceService.readAll()
    }

    @PUT
    Response update(Vendor dto) {
        dto.id = id
        ok vendorResourceService.update(dto)
    }

    @DELETE
    void delete() {
        vendorResourceService.delete(id)
    }

}

