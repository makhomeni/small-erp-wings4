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

@Path("/api/warehouse")
@Consumes(['application/xml', 'application/json'])
@Produces(['application/xml', 'application/json'])
class WarehouseResource {

    def warehouseResourceService
    def id

    @GET
    Response read() {
        ok warehouseResourceService.read(id)
    }

    @PUT
    Response update(Warehouse dto) {
        dto.id = id
        ok warehouseResourceService.update(dto)
    }

    @DELETE
    void delete() {
        warehouseResourceService.delete(id)
    }

}

