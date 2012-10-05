package com.wings4.model;

import com.towel.el.annotation.Resolvable;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 8/28/12
 * Time: 9:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class Customer {

    @Resolvable(colName = "ID")
    private Integer id;

    @Resolvable(colName = "First Name")
    private String firstName;

    @Resolvable(colName = "Last Name")
    private String lastName;


    @Resolvable(colName = "Email Id")
    private String emailId;

    @Resolvable(colName = "Organization")
    private String organization;

    @Resolvable(colName = "Mobile Number")
    private String mobileNumber;

    @Resolvable(colName = "Phone Number")
    private String phoneNumber;

    @Resolvable(colName = "Address")
    private String address;

    @Resolvable(colName = "Billing Address")
    private String billingAddress;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }


}
