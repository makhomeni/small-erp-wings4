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
import javax.ws.rs.POST
import org.json.JSONObject
import com.jabait.hrm.Organization

@Path('/api/localVendor')
class LocalVendorResource {

    def localVendorResourceService
    def id

    @POST
    Response create(String vendor){
        JSONObject vendorJson = new JSONObject(vendor);
        LocalVendor localVendor = new LocalVendor();
        localVendor.firstName = vendorJson.get("firstName").toString();
        localVendor.lastName = vendorJson.get("lastName").toString();
        localVendor.organization = Organization.get(Long.parseLong(vendorJson.get("organization").toString()));
        localVendor.address = vendorJson.get("address").toString();
        localVendor.extendedAddress = vendorJson.get("extendedAddress").toString();
        localVendor.country = vendorJson.get("country").toString();
        localVendor.mobileNo = vendorJson.get("mobile").toString();
        localVendor.description = vendorJson.get("description").toString();
        localVendor.emailId = vendorJson.get("emailId").toString();
        localVendor.phoneNo = vendorJson.get("phoneNo").toString();

        ok localVendor.save(flush: true);
    }

    @GET
    Response readAll() {
        ok LocalVendor.list();
    }

    @PUT
    Response update(LocalVendor dto) {
        dto.id = id
        ok localVendorResourceService.update(dto)
    }

    @DELETE
    void delete() {
        localVendorResourceService.delete(id)
    }

}

