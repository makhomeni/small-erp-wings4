package com.wings4.model;

import com.towel.el.annotation.Resolvable;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 9/22/12
 * Time: 11:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class Purchase {

    @Resolvable(colName = "ID")
    private Integer id;

    @Resolvable(colName = "Vendor")
    private String vendor;

    @Resolvable(colName = "Purchase Order")
    private String purchaseOrder;

    @Resolvable(colName = "Product")
    private String product;

    @Resolvable(colName = "Purchase Date")
    private String purchaseDate;

    @Resolvable(colName = "Price")
    private String price;

    @Resolvable(colName = "Quantity")
    private String quantity;

    @Resolvable(colName = "Purchase Type")
    private String purchaseType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(String purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }
}
