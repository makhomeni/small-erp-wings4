package com.wings4.model;

import com.towel.el.annotation.Resolvable;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/31/12
 * Time: 5:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProductClassification {

    @Resolvable(colName = "ID")
    private Integer classificationId;

    @Resolvable(colName = "Classification")
    private String classification;

    @Resolvable(colName = "Description")
    private String description;

    public Integer getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(Integer classificationId) {
        this.classificationId = classificationId;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
