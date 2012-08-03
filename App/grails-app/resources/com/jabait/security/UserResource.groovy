package com.jabait.security

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
import com.jabait.coresecurity.util.EncryptionUtils
import javax.ws.rs.POST
import javax.ws.rs.core.MediaType
import org.json.JSONObject

@Path('/api/user')
class UserResource {
    
    def userResourceService
    def id
    
    @GET
    @Path("/{id}")
    Response read() {
        ok userResourceService.read(id)
    }

    @POST
    @Produces(["application/json"])
    @Consumes(["application/json"])
    Response authenticate(Object user){
        JSONObject userObject = new JSONObject(user);
        User userAuth = User.findByUserCodeAndPassword(userObject.get("username").toString(),
                new EncryptionUtils("jabait").encrypt(userObject.get("password").toString()));
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

