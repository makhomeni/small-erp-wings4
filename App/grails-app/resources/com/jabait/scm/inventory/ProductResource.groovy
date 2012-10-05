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
import javax.ws.rs.POST
import javax.ws.rs.core.MediaType
import com.jabait.util.UnitOfMeasure
import javax.ws.rs.QueryParam

@Path("/api/product")
class ProductResource {
    
    def inventoryService
    def id

    @POST
    @Consumes(["application/json"])
    @Produces(["application/json"])
    Response createProduct(String product){
        JSONObject jsonObject = new JSONObject(product);
        Product productCreate = new Product();
//        DeliveryTerm.get(Integer.parseInt(jsonObject.get("deliveryTerm").toString()));
        Category category = Category.get(Integer.parseInt(jsonObject.get("productCategory").toString()));
        ProductType productType = ProductType.get(Integer.parseInt(jsonObject.get("productType").toString()));
        ProductClassification productClassification = ProductClassification.get(Integer.parseInt(jsonObject.get("productClassification").toString()));

        productCreate.stockKeepingUnit = jsonObject.get("stockKeepingUnit").toString();
        productCreate.universalProductCode = jsonObject.get("universalProductCode").toString();
        productCreate.productCategory = category;
        productCreate.productName = jsonObject.get("productName").toString()
        productCreate.unitOfMeasure = jsonObject.get("unitOfMeasure").toString();
        productCreate.productType = productType;
        productCreate.licenseInfo = jsonObject.get("licenseInfo").toString();
        productCreate.classification = productClassification;
        productCreate.alertNotes = "no";
        productCreate.productDetails = "product details";
        productCreate.taxable = true;
        productCreate.active = true;
        if (productCreate.save()) {
            println "saved successfully";
        } else {
            productCreate.errors.each {
                println it;
            }
        }
    }

    @GET
    @Path('/{id}')
    @Produces(["application/xml"])
    Response read(@PathParam("id") Long id) {
        ok inventoryService.read(id);
    }

    @GET
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

