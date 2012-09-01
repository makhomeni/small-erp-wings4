package com.wings4.model;

import com.towel.el.annotation.Resolvable;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/31/12
 * Time: 5:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProductType {

    @Resolvable(colName = "ID")
    private Integer productTypeId;

    @Resolvable(colName = "Description")
    private String description;

    @Resolvable(colName = "Name")
    private String name;

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
