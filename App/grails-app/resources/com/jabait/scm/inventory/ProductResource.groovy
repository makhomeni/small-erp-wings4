package com.jabait.scm.inventory

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
import org.json.JSONObject

@Path("/api/product")
class ProductResource {
    
    def inventoryService
    def id

    @GET
    @Path('/{id}')
    @Produces(["application/xml"])
    Response read(@PathParam("id") Long id) {
        ok inventoryService.read(id)
    }

    @GET
    @Produces(["application/xml"])
    Response readAll(){
        ok inventoryService.readAllProducts();
    }
    
    @PUT
    @Produces(["application/json"])
    @Consumes(["application/json"])
    Response update(String productData) {
        JSONObject productUpdate = new JSONObject(productData);
        Product product = Product.get(Long.parseLong(productUpdate.get("id").toString()));
        product.productName = productUpdate.get("productName");
        product.productCategory = Category.get(Long.valueOf(productUpdate.get("category").toString()));
        ok inventoryService.update(product);
    }
    
    @DELETE
    void delete() {
        inventoryService.delete(id)
    }
    
}

