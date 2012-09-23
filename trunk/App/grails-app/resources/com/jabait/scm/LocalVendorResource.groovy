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
        localVendor.firstName = vendorJson.get("name").toString().split(" ")[0];
        localVendor.lastName = vendorJson.get("name").toString().split(" ")[1];
        localVendor.organization = Organization.get(1);
        localVendor.address = vendorJson.get("address").toString().split(" ")[0];
        localVendor.extendedAddress = vendorJson.get("address").toString().split(" ")[1];
        localVendor.country = vendorJson.get("country").toString();
        localVendor.mobileNo = vendorJson.get("phoneNumber").toString().split(" ")[1];
        localVendor.description = vendorJson.get("description").toString();
        localVendor.emailId = vendorJson.get("email").toString();
        localVendor.phoneNo = vendorJson.get("phoneNumber").toString().split(" ")[0];

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

