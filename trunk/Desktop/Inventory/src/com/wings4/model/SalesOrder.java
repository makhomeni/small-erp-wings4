package com.wings4.model;

import com.towel.el.annotation.Resolvable;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 8/28/12
 * Time: 9:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class SalesOrder {


    @Resolvable(colName = "ID")
    private Integer id;


    @Resolvable(colName = "Job Name")
    private String jobName;

    @Resolvable(colName = "Order Quantity")
    private String orderQuantity;

    @Resolvable(colName = "Created Date")
    private String createdDate;

    @Resolvable(colName = "Created By")
    private String createdBy;

    @Resolvable(colName = "Status")
    private String status;

    @Resolvable(colName = "Priority")
    private String priority;

    @Resolvable(colName = "Is Sent")
    private String isSent;

    @Resolvable(colName = "Delivery Term")
    private String deliveryTerm;

    @Resolvable(colName = "Due Date")
    private String dueDate;

    @Resolvable(colName = "Customer")
    private String customer;

    @Resolvable(colName = "address1")
    private String address1;

    @Resolvable(colName = "address2")
    private String address2;

    @Resolvable(colName = "Shipping Method")
    private String shippingMethod;



    @Resolvable(colName = "Payment Term")
    private String paymentTerm;

    @Resolvable(colName = "Product")
    private String product;


    @Resolvable(colName = "Is Archived")
    private String isArchived;

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(String paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(String orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getSent() {
        return isSent;
    }

    public void setSent(String sent) {
        isSent = sent;
    }

    public String getDeliveryTerm() {
        return deliveryTerm;
    }

    public void setDeliveryTerm(String deliveryTerm) {
        this.deliveryTerm = deliveryTerm;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getArchived() {
        return isArchived;
    }

    public void setArchived(String archived) {
        isArchived = archived;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }
}
