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
import javax.ws.rs.PathParam


@Path('/api/contactPerson')
class ContactPersonResource {

    def contactPersonResourceService
    def id

    @GET
    @Path('/{id}')
    @Produces(["application/json"])
    Response read(@PathParam("id") Long id) {
        ok contactPersonResourceService.read(id)
    }

    @GET
    @Produces(["application/json"])
    Response readAll() {
        ok contactPersonResourceService.readAll()
    }

    @PUT
    Response update(ContactPerson dto) {
        dto.id = id
        ok contactPersonResourceService.update(dto)
    }

    @DELETE
    void delete() {
        contactPersonResourceService.delete(id)
    }

}

