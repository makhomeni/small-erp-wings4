package com.wings4.model;

import com.towel.el.annotation.Resolvable;

/**
 * 
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 9/19/12
 * Time: 8:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class DeliveryTerm {

    @Resolvable(colName = "id")
    private Integer id;

    @Resolvable(colName = "terms")
    private String terms;

    @Resolvable(colName = "description")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
