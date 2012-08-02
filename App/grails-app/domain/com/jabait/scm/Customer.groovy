package com.jabait.scm

import com.jabait.hrm.Organization
import com.jabait.security.Address

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 7/27/12
 * Time: 1:34 AM
 * To change this template use File | Settings | File Templates.
 */
class Customer {

    String firstName;
    String lastName;
    String emailId;
    Organization organization;
    String mobileNumber;
    String phoneNumber;
    String address;
    ContactPerson contact;
    Referral reference;
    Address billingAddress;

    static mapping = {
        table('inventory_customer')
        autoImport(false)
    }

}
