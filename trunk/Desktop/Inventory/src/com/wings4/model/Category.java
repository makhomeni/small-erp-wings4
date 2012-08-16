package com.wings4.model;

import com.towel.el.annotation.Resolvable;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/16/12
 * Time: 3:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class Category {

    @Resolvable(colName = "ID")
    private Integer categoryId;

    @Resolvable(colName = "Category Name")
    private String categoryName;

    @Resolvable(colName = "Parent Category")
    private String parentCategory;

    public Category(Integer categoryId, String categoryName, String parentCategory) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.parentCategory = parentCategory;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(String parentCategory) {
        this.parentCategory = parentCategory;
    }
}
