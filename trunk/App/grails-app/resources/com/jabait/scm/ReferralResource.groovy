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

@Path('/api/referral')
class ReferralResource {

    def referralResourceService
    def id

    @GET
    @Path('/{id}')
    @Produces(["application/xml"])
    Response read(@PathParam("id") Long id) {
        ok referralResourceService.read(id)
    }

    @GET
    @Produces(["application/json"])
    Response readAll() {
        ok referralResourceService.readAll()
    }

    @PUT
    Response update(Referral dto) {
        dto.id = id
        ok referralResourceService.update(dto)
    }

    @DELETE
    void delete() {
        referralResourceService.delete(id)
    }

}

