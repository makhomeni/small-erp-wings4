package com.jabait.security

import static org.grails.jaxrs.response.Responses.*

import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.Produces
import javax.ws.rs.PUT
import javax.ws.rs.core.Response

import javax.ws.rs.Path

import com.jabait.coresecurity.util.EncryptionUtils
import javax.ws.rs.POST

import org.json.JSONObject
import javax.ws.rs.PathParam
import org.grails.jaxrs.provider.DomainObjectNotFoundException

@Path('/api/user')
class UserResource {
    
    def userResourceService
    def id
    
    @GET
    @Path("/{id}")
    Response read(@PathParam("id") Long id) {
        ok userResourceService.read(id)
    }

    @POST
    @Produces(["application/json"])
    @Consumes(["application/json"])
    Response authenticate(String user){
        JSONObject userObject = new JSONObject(user);
        User userAuth = User.findByUserCodeAndPassword(userObject.get("username").toString(),
                new EncryptionUtils("jabait").encrypt(userObject.get("password").toString()));
        if(!userAuth){
            throw new DomainObjectNotFoundException(User.class, 1)
        }
        created userAuth;
    }
    
    @PUT
    Response update(User dto) {
        dto.id = id
        ok userResourceService.update(dto)
    }
    
    @DELETE
    void delete() {
        userResourceService.delete(id)
    }
    
}

