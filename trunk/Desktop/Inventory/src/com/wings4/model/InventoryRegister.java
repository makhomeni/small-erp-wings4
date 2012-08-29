package com.wings4.model;

import com.towel.el.annotation.Resolvable;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/29/12
 * Time: 10:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class InventoryRegister {

    @Resolvable(colName = "ID")
    private Integer id;

    @Resolvable(colName = "Product Name")
    private String productName;

    @Resolvable(colName = "On Hand")
    private Integer onHand;

    @Resolvable(colName = "On Order")
    private Integer onOrder;

    @Resolvable(colName = "Allocated")
    private Integer allocated;

    @Resolvable(colName = "Committed")
    private Integer committed;

    @Resolvable(colName = "Unavailable")
    private Integer unavailable;

    @Resolvable(colName = "Back Ordered")
    private Integer backOrdered;

    @Resolvable(colName = "Drop Ship")
    private Integer dropShip;

    @Resolvable(colName = "Available for Sale")
    private Integer availableForSale;

    @Resolvable(colName = "Available to Pick")
    private Integer availableToPick;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getOnHand() {
        return onHand;
    }

    public void setOnHand(Integer onHand) {
        this.onHand = onHand;
    }

    public Integer getOnOrder() {
        return onOrder;
    }

    public void setOnOrder(Integer onOrder) {
        this.onOrder = onOrder;
    }

    public Integer getAllocated() {
        return allocated;
    }

    public void setAllocated(Integer allocated) {
        this.allocated = allocated;
    }

    public Integer getCommitted() {
        return committed;
    }

    public void setCommitted(Integer committed) {
        this.committed = committed;
    }

    public Integer getUnavailable() {
        return unavailable;
    }

    public void setUnavailable(Integer unavailable) {
        this.unavailable = unavailable;
    }

    public Integer getBackOrdered() {
        return backOrdered;
    }

    public void setBackOrdered(Integer backOrdered) {
        this.backOrdered = backOrdered;
    }

    public Integer getDropShip() {
        return dropShip;
    }

    public void setDropShip(Integer dropShip) {
        this.dropShip = dropShip;
    }

    public Integer getAvailableForSale() {
        return availableForSale;
    }

    public void setAvailableForSale(Integer availableForSale) {
        this.availableForSale = availableForSale;
    }

    public Integer getAvailableToPick() {
        return availableToPick;
    }

    public void setAvailableToPick(Integer availableToPick) {
        this.availableToPick = availableToPick;
    }
}
