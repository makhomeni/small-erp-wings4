package com.jabait.scm

import com.jabait.hrm.Organization

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 7/27/12
 * Time: 1:34 AM
 * To change this template use File | Settings | File Templates.
 */
class Customer {

    Integer id;
    String firstName;
    String lastName;
    String emailId;
    Organization organization;
    Integer mobileNumber;
    Integer phoneNumber;
    String address;
    ContactPerson contact;
    Referral reference;

    static mapping = {
        table('inventory_customer')
        autoImport(false)
    }

}
