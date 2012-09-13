package com.wings4.model;

import com.towel.el.annotation.Resolvable;

/**
 *
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 9/13/12
 * Time: 6:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class Organization  {

    public Organization(){

    }

    @Resolvable(colName = "ID")
    private Integer id;

    @Resolvable(colName = "Organization Name")
    private String organizationName;

    @Resolvable(colName = "Parent Category")
    private Organization parent;


    @Resolvable(colName = "email")
    private String email;

    @Resolvable(colName = "phone")
    private String phone;

    @Resolvable(colName = "address")
    private String address;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Organization getParent() {
        return parent;
    }

    public void setParent(Organization parent) {
        this.parent = parent;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
