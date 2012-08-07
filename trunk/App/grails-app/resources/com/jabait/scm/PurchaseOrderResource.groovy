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

@Path('/api/purchaseOrder')
@Consumes(['application/xml', 'application/json'])
@Produces(['application/xml', 'application/json'])
class PurchaseOrderResource {

    def purchaseOrderResourceService
    def id

    @GET
    Response read() {
        ok purchaseOrderResourceService.read(id)
    }

    @PUT
    Response update(PurchaseOrder dto) {
        dto.id = id
        ok purchaseOrderResourceService.update(dto)
    }

    @DELETE
    void delete() {
        purchaseOrderResourceService.delete(id)
    }

}

