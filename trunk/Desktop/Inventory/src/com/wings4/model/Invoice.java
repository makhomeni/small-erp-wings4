package com.wings4.model;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 10/15/12
 * Time: 11:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class Invoice {
    private int pkid;
    private String name;
    private Double unit;
    private Double qty;
    private Double remarks;

    public Double getRemarks() {
        return remarks;
    }

    public void setRemarks(Double remarks) {
        this.remarks = remarks;
    }

    public int getPkid() {
        return pkid;
    }

    public void setPkid(int pkid) {
        this.pkid = pkid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getUnit() {
        return unit;
    }

    public void setUnit(Double unit) {
        this.unit = unit;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }
}
