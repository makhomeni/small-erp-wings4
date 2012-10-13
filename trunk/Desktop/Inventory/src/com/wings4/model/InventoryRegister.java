package com.wings4.model;

import com.towel.el.annotation.Resolvable;

import java.util.Date;

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

    private Double onHand;

    private Double onHandFromLocalSupplier;

    private Double onHandImportByLC;

    private Double onSalesReturn;

    private Double onLoanReturn;

    private Double onPurchaseOrder = 0.0;//how many product is ordered will be added
    private Double onSalesOrder = 0.0;
    private Double allocated = 0.0;

    private Double committed = 0.0;

    private Double unavailable = 0.0;
    private Double unavailableFromSales = 0.0;
    private Double unavailableFromSample = 0.0;
    private Double unavailableFromLoan = 0.0;

    private Double backOrdered = 0.0;
    private Double dropShip = 0.0;

    private Double availableForSale = 0.0;
    private Double availableToPick = 0.0;

    private String modifiedDate;

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

    public Double getOnHand() {
        return onHand;
    }

    public void setOnHand(Double onHand) {
        this.onHand = onHand;
    }

    public Double getOnHandFromLocalSupplier() {
        return onHandFromLocalSupplier;
    }

    public void setOnHandFromLocalSupplier(Double onHandFromLocalSupplier) {
        this.onHandFromLocalSupplier = onHandFromLocalSupplier;
    }

    public Double getOnHandImportByLC() {
        return onHandImportByLC;
    }

    public void setOnHandImportByLC(Double onHandImportByLC) {
        this.onHandImportByLC = onHandImportByLC;
    }

    public Double getOnSalesReturn() {
        return onSalesReturn;
    }

    public void setOnSalesReturn(Double onSalesReturn) {
        this.onSalesReturn = onSalesReturn;
    }

    public Double getOnLoanReturn() {
        return onLoanReturn;
    }

    public void setOnLoanReturn(Double onLoanReturn) {
        this.onLoanReturn = onLoanReturn;
    }

    public Double getOnPurchaseOrder() {
        return onPurchaseOrder;
    }

    public void setOnPurchaseOrder(Double onPurchaseOrder) {
        this.onPurchaseOrder = onPurchaseOrder;
    }

    public Double getOnSalesOrder() {
        return onSalesOrder;
    }

    public void setOnSalesOrder(Double onSalesOrder) {
        this.onSalesOrder = onSalesOrder;
    }

    public Double getAllocated() {
        return allocated;
    }

    public void setAllocated(Double allocated) {
        this.allocated = allocated;
    }

    public Double getCommitted() {
        return committed;
    }

    public void setCommitted(Double committed) {
        this.committed = committed;
    }

    public Double getUnavailable() {
        return unavailable;
    }

    public void setUnavailable(Double unavailable) {
        this.unavailable = unavailable;
    }

    public Double getUnavailableFromSales() {
        return unavailableFromSales;
    }

    public void setUnavailableFromSales(Double unavailableFromSales) {
        this.unavailableFromSales = unavailableFromSales;
    }

    public Double getUnavailableFromSample() {
        return unavailableFromSample;
    }

    public void setUnavailableFromSample(Double unavailableFromSample) {
        this.unavailableFromSample = unavailableFromSample;
    }

    public Double getUnavailableFromLoan() {
        return unavailableFromLoan;
    }

    public void setUnavailableFromLoan(Double unavailableFromLoan) {
        this.unavailableFromLoan = unavailableFromLoan;
    }

    public Double getBackOrdered() {
        return backOrdered;
    }

    public void setBackOrdered(Double backOrdered) {
        this.backOrdered = backOrdered;
    }

    public Double getDropShip() {
        return dropShip;
    }

    public void setDropShip(Double dropShip) {
        this.dropShip = dropShip;
    }

    public Double getAvailableForSale() {
        return availableForSale;
    }

    public void setAvailableForSale(Double availableForSale) {
        this.availableForSale = availableForSale;
    }

    public Double getAvailableToPick() {
        return availableToPick;
    }

    public void setAvailableToPick(Double availableToPick) {
        this.availableToPick = availableToPick;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
