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
        JSONObject productSave = new JSONObject(product);
        Product productObject = new Product();
        productObject.productName = productSave.get("productName");
        productObject.productCategory = Category.get(Integer.parseInt(productSave.get("productName").toString()));
        productObject.stockKeepingUnit = productSave.get("stockKeepingUnit");
        productObject.unitOfMeasure = UnitOfMeasure.get(Integer.parseInt(
                productSave.get("unitOfMeasure").toString()));
        productObject.productType = ProductType.get(Integer.parseInt(productSave.get("productName").toString()));
        productObject.universalProductCode = productSave.get("universalProductCode");
        productObject.licenseInfo = productSave.get("licenseInfo");
        productObject.classification = ProductClassification.get(Integer.parseInt(
                productSave.get("universalProductCode").toString()));

        ok inventoryService.save(productObject);
    }

    @GET
    @Path('/{id}')
    @Produces(["application/xml"])
    Response read(@PathParam("id") Long id) {
        ok inventoryService.read(id);
    }


//    @GET
//    Response readProductType(@QueryParam("utilType") String utilType){
//        if(utilType.equals("productType")){
//            List<ProductType> allProductTypes = ProductType.list();
//            ok allProductTypes;
//        } else if(utilType.equals("classification")){
//            List<ProductClassification> allProductClassifications = ProductClassification.list();
//            ok allProductClassifications;
//        } else if(utilType.equals("uom")){
//            List<UnitOfMeasure> allUnitOfMeasures = UnitOfMeasure.list();
//            ok allUnitOfMeasures;
//        }
//    }

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

