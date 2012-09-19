package com.wings4.model;

import com.towel.el.annotation.Resolvable;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 8/28/12
 * Time: 9:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class PurchaseOrder {

    @Resolvable(colName = "ID")
    private Integer id;

    @Resolvable(colName = "LocalVendor")
    private String vendor;

    @Resolvable(colName = "Shipping Address")
    private String shippingAddress;

    @Resolvable(colName = "Organization")
    private String organization;

    @Resolvable(colName = "Shipping Method")
    private String shippingMethod;

    @Resolvable(colName = "Payment Term")
    private String paymentTerm;

    @Resolvable(colName = "Delivery Term")
    private String deliveryTerm;

    @Resolvable(colName = "Order Quantity")
    private String orderQuantity;

    public String getDeliveryTerm() {
        return deliveryTerm;
    }

    public void setDeliveryTerm(String deliveryTerm) {
        this.deliveryTerm = deliveryTerm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(String paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    public String getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(String orderQuantity) {
        this.orderQuantity = orderQuantity;
    }
}
