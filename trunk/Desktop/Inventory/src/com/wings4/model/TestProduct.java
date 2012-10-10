package com.wings4.model;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 10/10/12
 * Time: 5:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestProduct {
    private long id;
    private String state;
    private String branch;
    private String productLine;
    private String item;
    private long quantity;
    private float amount;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity){
        this.quantity = quantity;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
