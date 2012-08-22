package com.wings4.model;

import com.towel.el.annotation.Resolvable;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/16/12
 * Time: 3:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class Product {

    @Resolvable(colName = "ID")
    private Integer productId;

    @Resolvable(colName = "Product Name")
    private String productName;

    @Resolvable(colName = "Stock Keeping Unit")
    private String stockKeepingUnit;

    @Resolvable(colName = "UPC")
    private String universalProductCode;

    @Resolvable(colName = "Classification")
    private String productClassification;

    @Resolvable(colName = "Category")
    private String productCategory;

    @Resolvable(colName = "ProductCreate Details")
    private String productDetails;

    public Product(Integer productId, String productName, String stockKeepingUnit, String universalProductCode,
                   String productClassification, String productCategory, String productDetails) {
        this.productId = productId;
        this.productName = productName;
        this.stockKeepingUnit = stockKeepingUnit;
        this.universalProductCode = universalProductCode;
        this.productClassification = productClassification;
        this.productCategory = productCategory;
        this.productDetails = productDetails;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getStockKeepingUnit() {
        return stockKeepingUnit;
    }

    public void setStockKeepingUnit(String stockKeepingUnit) {
        this.stockKeepingUnit = stockKeepingUnit;
    }

    public String getUniversalProductCode() {
        return universalProductCode;
    }

    public void setUniversalProductCode(String universalProductCode) {
        this.universalProductCode = universalProductCode;
    }

    public String getProductClassification() {
        return productClassification;
    }

    public void setProductClassification(String productClassification) {
        this.productClassification = productClassification;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }
}
