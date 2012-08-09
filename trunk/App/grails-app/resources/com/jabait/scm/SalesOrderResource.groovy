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
import javax.ws.rs.POST
import org.json.JSONObject
import javax.ws.rs.core.MediaType
import com.jabait.security.User

@Path('/api/salesOrder')
class SalesOrderResource {

    def salesOrderResourceService
    def id

    @POST
    @Produces(["applicatio/json"])
    @Consumes(["applicatio/json"])
    Response createSalesOrder(String salesOrder){
        JSONObject jsonObject = new JSONObject(salesOrder);
    }

    @GET
    Response read() {
        ok salesOrderResourceService.read(id)
    }

    @PUT
    Response update(SalesOrder dto) {
        dto.id = id
        ok salesOrderResourceService.update(dto)
    }

    @DELETE
    void delete() {
        salesOrderResourceService.delete(id)
    }

    @POST
    @Produces([MediaType.APPLICATION_JSON])
    @Consumes([MediaType.APPLICATION_JSON])
    Response create(String salesOrder){
        JSONObject jsonObjectSalesOrder = new JSONObject(salesOrder);
        JobOrder jobOrder = new JobOrder();
        jobOrder.jobName = jsonObjectSalesOrder.get("jobName").toString();
        jobOrder.orderQuantity = Integer.parseInt(jsonObjectSalesOrder.get("orderQuantity").toString());
        jobOrder.createdBy = User.get(Integer.parseInt(jsonObjectSalesOrder.get("createdBy").toString()));
        jobOrder.status = Integer.parseInt(jsonObjectSalesOrder.get("status").toString());
        jobOrder.priority = Integer.parseInt(jsonObjectSalesOrder.get("priority").toString());
        jobOrder.isSent = Boolean.parseBoolean(jsonObjectSalesOrder.get("isSent").toString());
        jobOrder.deliveryTerm = DeliveryTerm.get(1);
        jobOrder.dueDate = new Date();
        jobOrder.createdDate = new Date();
        jobOrder.isArchived = Boolean.parseBoolean(jsonObjectSalesOrder.get("isArchived").toString());

        created jobOrder.save();

    }
}

