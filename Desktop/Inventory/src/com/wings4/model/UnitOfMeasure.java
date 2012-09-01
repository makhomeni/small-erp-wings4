package com.wings4.model;

import com.towel.el.annotation.Resolvable;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/31/12
 * Time: 5:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class UnitOfMeasure {

    @Resolvable(colName = "ID")
    private Integer id;

    @Resolvable(colName = "Unit of Measure")
    private String uom;

    @Resolvable(colName = "Description")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
