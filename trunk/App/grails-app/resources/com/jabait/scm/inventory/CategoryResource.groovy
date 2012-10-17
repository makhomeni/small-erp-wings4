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
    Response readAll(){
        List<Category> categories = inventoryService.readAllCategories();
        Map<String,Object> categoryMap;
        List<Map<String,Object>> allCategories = new ArrayList<Map<String,Object>>();
        for (Category category: categories) {
            categoryMap = new HashMap<String,Object>();
            categoryMap.put("id", category.id);
            categoryMap.put("categoryName", category.categoryName);
            String parent = "";
            if(category.parentCategory != null) parent = category?.parentCategory?.categoryName
            categoryMap.put("parentCategory", parent);

            allCategories.add(categoryMap);
        }
        ok allCategories;
    }

    @POST
    Response create(String category){
        
        JSONObject categoryJsonObject = new JSONObject(category);
        Category categoryDto = new Category();
        categoryDto.categoryName = categoryJsonObject.get("categoryName").toString();
        println "categoryDto = " + categoryJsonObject.length();
        try{

            if(categoryJsonObject.length() == 2){
                Category parentCategory = Category?.get(Long.valueOf(
                        categoryJsonObject?.get("parentCategory")?.toString()));
                categoryDto.parentCategory = parentCategory;
            }

        } catch(Exception ex){

        }


        created categoryDto.save();
    }
}
