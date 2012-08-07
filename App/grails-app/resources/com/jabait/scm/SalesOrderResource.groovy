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

@Path('/api/salesOrder')
class SalesOrderResource {

    def salesOrderResourceService
    def id

    @POST
    @Produces(["applicatio/json"])
    @Consumes(["applicatio/json"])
    Response createSalesOrder(String salesOrder){
        JSONObject jsonObject = new JSONObject(salesOrder);
    }

    @GET
    Response read() {
        ok salesOrderResourceService.read(id)
    }

    @PUT
    Response update(SalesOrder dto) {
        dto.id = id
        ok salesOrderResourceService.update(dto)
    }

    @DELETE
    void delete() {
        salesOrderResourceService.delete(id)
    }

}

