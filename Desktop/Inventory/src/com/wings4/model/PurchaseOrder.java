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

    @Resolvable(colName = "Vendor Name")
    private String vendorId;

    @Resolvable(colName = "Job Name")
    private String jobName;


    @Resolvable(colName = "Shipping Address")
    private String shippingAddress;

    @Resolvable(colName = "Organization")
    private String organizationId;

    @Resolvable(colName = "Shipping Method")
    private String shippingMethodId;

    @Resolvable(colName = "Payment Term")
    private String paymentTermId;

    @Resolvable(colName = "Delivery Term")
    private String deliveryTermId;

    @Resolvable(colName = "Order Quantity")
    private String orderQuantity;

    @Resolvable(colName = "Product")
    private String productId;

    public String getDeliveryTermId() {
        return deliveryTermId;
    }

    public void setDeliveryTermId(String deliveryTermId) {
        this.deliveryTermId = deliveryTermId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getShippingMethodId() {
        return shippingMethodId;
    }

    public void setShippingMethodId(String shippingMethodId) {
        this.shippingMethodId = shippingMethodId;
    }

    public String getPaymentTermId() {
        return paymentTermId;
    }

    public void setPaymentTermId(String paymentTermId) {
        this.paymentTermId = paymentTermId;
    }

    public String getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(String orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
