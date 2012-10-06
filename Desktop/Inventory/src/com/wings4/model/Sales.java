package com.wings4.model;

import com.towel.el.annotation.Resolvable;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 9/22/12
 * Time: 11:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class Sales {

    @Resolvable(colName = "ID")
    private Integer id;

    @Resolvable(colName = "Customer Name")
    private String customerName;

    @Resolvable(colName = "Product Name")
    private String productName;

    @Resolvable(colName = "Sales Date")
    private String salesDate;

    @Resolvable(colName = "Quantity")
    private Integer quantity;

    @Resolvable(colName = "Price")
    private Double price;
    private String salesOrder;
    private String salesType;

    public String getSalesType() {
        return salesType;
    }

    public void setSalesType(String salesType) {
        this.salesType = salesType;
    }

    public String getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(String salesOrder) {
        this.salesOrder = salesOrder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(String salesDate) {
        this.salesDate = salesDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

