package com.jabait.scm.inventory

import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.Response
import javax.ws.rs.PathParam
import static org.grails.jaxrs.response.Responses.*
import javax.ws.rs.GET
import org.json.JSONObject
import javax.ws.rs.POST
import javax.ws.rs.core.MediaType
import javax.ws.rs.Consumes

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/3/12
 * Time: 2:16 PM
 * To change this template use File | Settings | File Templates.
 */
@Path("/api/category")
class CategoryResource {

    def inventoryService;
    def id;

    @GET
    @Path('/{id}')
    @Produces(["application/xml"])
    Response read(@PathParam("id") Long id) {
        ok inventoryService.read(Category.get(id))
    }

    @GET
    @Produces(["application/xml"])
    Response readAll(){
        ok inventoryService.readAllCategories();
    }

    @POST
    @Produces([MediaType.APPLICATION_JSON])
    @Consumes([MediaType.APPLICATION_JSON])
    Response create(Object category){
        
        JSONObject categoryJsonObject = new JSONObject(category);
        Category parentCategory = Category.get(Long.valueOf(
                categoryJsonObject.get("parentCategory").toString()));
        Category categoryDto = new Category();
        categoryDto.categoryName = categoryJsonObject.get("categoryName").toString();
        categoryDto.parentCategory = parentCategory;
        created categoryDto.save();
    }
}
