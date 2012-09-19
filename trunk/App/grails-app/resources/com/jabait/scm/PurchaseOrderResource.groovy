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
import com.jabait.hrm.Organization
import com.jabait.accounting.PaymentTerm
import com.jabait.security.User
import com.jabait.scm.inventory.ShippingMethod

@Path('/api/purchaseOrder')
@Consumes(['application/xml', 'application/json'])
@Produces(['application/xml', 'application/json'])
class PurchaseOrderResource {

    def purchaseOrderResourceService
    def id

    @GET
    Response read() {
        ok purchaseOrderResourceService.read(id)
    }

    @PUT
    Response update(PurchaseOrder dto) {
        dto.id = id
        ok purchaseOrderResourceService.update(dto)
    }

    @DELETE
    void delete() {
        purchaseOrderResourceService.delete(id)
    }

    @POST
    Response create(String purchaseOrderJson){

        JSONObject jsonObject = new JSONObject(purchaseOrderJson);
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.organization = Organization.get(1);
        purchaseOrder.paymentTerm = PaymentTerm.get(1);
        purchaseOrder.shippingAddress = "sdsdsd";
        purchaseOrder.vendor = Vendor.get(1);
        purchaseOrder.createdBy = User.get(1);
        purchaseOrder.createdDate = new Date();
        purchaseOrder.deliveryTerm = DeliveryTerm.get(1);
        purchaseOrder.dueDate = new Date();
        purchaseOrder.isArchived = false;
        purchaseOrder.isSent = false;
        purchaseOrder.jobName = "purchase order";
        purchaseOrder.orderQuantity = 1;
        purchaseOrder.priority = 1;
        purchaseOrder.shippingMethod = ShippingMethod.get(1);
        purchaseOrder.status = 1;
        if (purchaseOrder.save()) {
            println "saved";
        } else {
            purchaseOrder.errors.each {
                println it;
            }
            println "not saved";
        }
        String organizationName = jsonObject.get("organizationId");
        println "organizationName = $organizationName"

    }

}

