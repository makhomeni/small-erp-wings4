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
import org.json.JSONObject

@Path('/api/externalVendor')
class ExternalVendorResource {

    def externalVendorResourceService
    def id

    @GET
    Response read() {
        ok externalVendorResourceService.read(id)
    }

    Response create(String externalVendorData){
        JSONObject externalVendorObject = new JSONObject(externalVendorData);
        ExternalVendor externalVendor = new ExternalVendor();
        ok externalVendor.save(flush: true);
    }

    @PUT
    Response update(ExternalVendor dto) {
        dto.id = id
        ok externalVendorResourceService.update(dto)
    }

    @DELETE
    void delete() {
        externalVendorResourceService.delete(id)
    }

}

