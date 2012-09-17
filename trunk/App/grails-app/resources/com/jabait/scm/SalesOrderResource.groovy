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
import javax.ws.rs.PathParam
import com.jabait.hrm.Organization
import com.jabait.accounting.PaymentTerm
import com.jabait.scm.inventory.ShippingMethod
import com.jabait.security.User

@Path('/api/salesOrder')
class SalesOrderResource {

    def salesOrderResourceService

    @POST
    Response createSalesOrder(String salesOrder){
        JSONObject jsonObject = new JSONObject(salesOrder);

        Referral referral = new Referral();
        referral.firstName = "sdfsdf";
        referral.lastName = "sdfsdf";
        referral.organization = Organization.get(1);
        referral.save(flush: true);


        Customer customer = new Customer();
        customer.address = "s";
        customer.billingAddress = "dsf";
        customer.firstName = "sdf";
        customer.lastName = "sdf";
        customer.organization = Organization.get(1);
        customer.phoneNumber = "3432";
        customer.mobileNumber = "342";
        customer.reference = referral;
        customer.emailId = "sdfsdf";
        customer.save(flush: true);
        
        if(customer.hasErrors()){
            println("sdf" + customer.errors)
        }
        
        PaymentTerm paymentTerm = new PaymentTerm();
        paymentTerm.name = "asfd";
        paymentTerm.days = 10;
        paymentTerm.description = "324";
        paymentTerm.save(flush: true);
        
        ShippingMethod shippingMethod = new ShippingMethod();
        shippingMethod.shippingMethod = "DHL";
        shippingMethod.description = "sdf";
        shippingMethod.save(flush: true);
        
        DeliveryTerm deliveryTerm = new DeliveryTerm();
        deliveryTerm.terms = "sdfsdf";
        deliveryTerm.description = "sdfsd";
        deliveryTerm.save(flush: true);

        SalesOrder sales = new SalesOrder();

        sales.address1 = "some";
        sales.address2 = "some2";
        sales.customer = customer;
        sales.paymentTerm = paymentTerm;
        sales.shippingMethod = shippingMethod;
        sales.createdBy = User.get(1);
        sales.createdDate = new Date();
        sales.deliveryTerm = deliveryTerm;
        sales.dueDate = new Date();
        sales.isArchived = true;
        sales.isSent = true;
        sales.jobName = "SO";
        sales.priority = 1;
        sales.orderQuantity = 10;
        sales.status = 1;

        if(sales.hasErrors()){
            println("sdf" + sales.errors);
        }

        if(sales.save(flush: true)){
            println("saved");
        } else {
            print("not saved");
        }

        println("salesOrderResource")

        ok sales.save();
    }

    def id

    @GET
    @Path("/{id}")
    Response read(@PathParam("id") Long id) {
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

}

