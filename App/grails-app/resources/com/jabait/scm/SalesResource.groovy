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
import javax.ws.rs.POST
import org.json.JSONObject
import com.jabait.scm.inventory.Product

@Path("/api/sales")
class SalesResource {

    def salesResourceService
    def id

    @GET
    @Path("/{id}")
    Response read(@PathParam("id") Long id) {
        ok salesResourceService.read(id)
    }

    @GET
    Response readAll(){
        ok salesResourceService.readAll();
    }

    @PUT
    Response update(Sales dto) {
        dto.id = id
        ok salesResourceService.update(dto)
    }

    @DELETE
    void delete() {
        salesResourceService.delete(id)
    }

    @POST
    Response createSales(String salesJson){

        JSONObject jsonObject = new JSONObject(salesJson);
        Sales sales = new Sales();
        Customer customer = Customer.get(Integer.parseInt(jsonObject.get("customerName").toString()));
        sales.customer=customer;
        Double price = Double.parseDouble(jsonObject.get("price").toString());
        sales.price = price;
        Product product = Product.get(Integer.parseInt(jsonObject.get("productName").toString()));
        sales.product =product;

        sales.quantity =Integer.parseInt(jsonObject.get("quantity").toString());
        sales.salesDate = new Date();
        String salesOrder = "test";
        sales.salesOrder = salesOrder;

        if (sales.save()) {
            println "saved";
            created true;
        } else {
            sales.errors.each {
                println it;
            }
            println "not saved";
            created false;
        }



//        sales.

    }

}

