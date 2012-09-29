package com.jabait.scm.inventory

import javax.ws.rs.GET
import javax.ws.rs.core.Response
import javax.ws.rs.QueryParam
import static org.grails.jaxrs.response.Responses.ok
import static org.grails.jaxrs.response.Responses.ok
import com.jabait.util.UnitOfMeasure
import static org.grails.jaxrs.response.Responses.ok
import javax.ws.rs.Path

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 9/29/12
 * Time: 12:44 PM
 * To change this template use File | Settings | File Templates.
 */
@Path("/api/productUtil")
class ProductUtil {

    @GET
    Response readProductType(@QueryParam("utilType") String utilType){
        if(utilType.equals("productType")){
            List<ProductType> allProductTypes = ProductType.list();
            ok allProductTypes;
        } else if(utilType.equals("classification")){
            List<ProductClassification> allProductClassifications = ProductClassification.list();
            ok allProductClassifications;
        } else if(utilType.equals("uom")){
            List<UnitOfMeasure> allUnitOfMeasures = UnitOfMeasure.list();
            ok allUnitOfMeasures;
        }
    }
}
