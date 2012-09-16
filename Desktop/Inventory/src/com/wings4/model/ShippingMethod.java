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
    private Integer id;

    @Resolvable(colName = "Description")
    private String description;

    @Resolvable(colName = "Shipping Method")
    private String shippingMethod;

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
