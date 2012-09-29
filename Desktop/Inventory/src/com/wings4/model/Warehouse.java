package com.wings4.model;

import com.towel.el.annotation.Resolvable;

/**
 * Created with IntelliJ IDEA.
 * User: Masum
 * Date: 8/29/12
 * Time: 1:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class Warehouse {

    @Resolvable(colName = "ID")
    private Integer id;

    @Resolvable(colName = "Organization")
    private String organization;

    @Resolvable(colName = "WareHouse Name")
    private String wareHouseName;

    @Resolvable(colName = "Address")
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getWareHouseName() {
        return wareHouseName;
    }

    public void setWareHouseName(String wareHouseName) {
        this.wareHouseName = wareHouseName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
