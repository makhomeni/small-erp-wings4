package com.wings4.model;

import com.towel.el.annotation.Resolvable;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 9/14/12
 * Time: 12:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class PaymentTerm {

    @Resolvable(colName = "ID")
    private Integer paymentTermId;

    @Resolvable(colName = "Name")
    private String name;

    @Resolvable(colName = "Number of Days")
    private String days;

    @Resolvable(colName = "Description")
    private String description;

    public Integer getPaymentTermId() {
        return paymentTermId;
    }

    public void setPaymentTermId(Integer paymentTermId) {
        this.paymentTermId = paymentTermId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
