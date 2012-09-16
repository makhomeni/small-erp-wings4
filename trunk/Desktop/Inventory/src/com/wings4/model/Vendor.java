package com.wings4.model;

import com.towel.el.annotation.Resolvable;

/**
 *
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 9/17/12
 * Time: 1:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class Vendor {
    
    @Resolvable(colName = "id")
    private Integer id;

    @Resolvable(colName = "first_name")
    private String firstName;

    @Resolvable(colName = "last_name")
    private String lastName;
    
    @Resolvable(colName = "organization")
    private Organization organization;

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

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
