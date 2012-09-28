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
import javax.ws.rs.POST
import com.jabait.hrm.Organization
import com.jabait.util.Carrier
import javax.ws.rs.PathParam

@Path('/api/externalVendor')
class ExternalVendorResource {

    def externalVendorResourceService
    def id

    @GET
    @Path("/{id}")
    Response read(@PathParam("id") Long id) {
        ok externalVendorResourceService.read(id)
    }

    @GET
    Response readAll(){
        ok ExternalVendor.findAll();
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

    @POST
    Response create(String vendor){
        JSONObject vendorJson = new JSONObject(vendor);
        ExternalVendor externalVendor = new ExternalVendor();
        externalVendor.firstName = vendorJson.get("name").toString().split(" ")[0];
        externalVendor.lastName = vendorJson.get("name").toString().split(" ")[1];
        externalVendor.organization = Organization.get(1);
        externalVendor.address = vendorJson.get("address").toString().split(" ")[0];
        externalVendor.extendedAddress = vendorJson.get("address").toString().split(" ")[1];
        externalVendor.country = vendorJson.get("country").toString();
        externalVendor.mobileNo = vendorJson.get("phoneNumber").toString().split(" ")[1];
        externalVendor.description = vendorJson.get("description").toString();
        externalVendor.emailId = vendorJson.get("email").toString();
        externalVendor.phoneNo = vendorJson.get("phoneNumber").toString().split(" ")[0];
        externalVendor.defaultCarrier = Carrier.get(1);

        if(externalVendor.save()){
            println "saved";
            created true;
        }
        else{

            externalVendor.errors.each {
                println it;

            }
            println "not saved";
            created false;
        }

    }

}

