package com.wings4.model;

import com.towel.el.annotation.Resolvable;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/29/12
 * Time: 10:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExternalVendor {

    @Resolvable(colName = "ID")
    private Integer id;

    @Resolvable(colName = "Name")
    private String name;

    @Resolvable(colName = "Organization")
    private String organization;

    @Resolvable(colName = "Address")
    private String address;

    @Resolvable(colName = "Phone Number")
    private String phoneNumber;

    @Resolvable(colName = "Email")
    private String email;
    
    @Resolvable(colName = "Country")
    private String country;

    @Resolvable(colName = "Carrier")
    private String carrier;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }
}
