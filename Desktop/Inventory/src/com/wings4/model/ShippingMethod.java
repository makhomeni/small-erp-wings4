package com.wings4.model;

import com.towel.el.annotation.Resolvable;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 9/14/12
 * Time: 12:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class ShippingMethod {

    @Resolvable(colName = "ID")
    private Integer shippingMethodId;

    @Resolvable(colName = "Description")
    private String description;

    public Integer getShippingMethodId() {
        return shippingMethodId;
    }

    public void setShippingMethodId(Integer shippingMethodId) {
        this.shippingMethodId = shippingMethodId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
