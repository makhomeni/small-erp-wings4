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
    String organizationName;
    String mobileNumber;
    String phoneNumber;
    String address;
    //ContactPerson contact; //why do you guys again and again putting contact person whereas Masum in the 
    //last meeting with Mr. Harun he was asking what is non-sense of com.jabait.scm.ContactPerson
    //I am removing it. Please don't put unnecessary things as these are creating confusion
//    Referral reference;
    //Address billingAddress; //If you are specifying address then why do you need another address of Address keep
    //consistency
    String billingAddress;

    static mapping = {
        table('inventory_customer')
        autoImport(false)
    }

    public static void initialize(){
        if(Customer.getCount() == 0){
            Customer customer = new Customer();
            customer.firstName = "Masum";
            customer.lastName = "Dewan";
            customer.emailId = "masum@yahoo.com";
            customer.organization = Organization.get(1);
            customer.mobileNumber = "09123456789";
            customer.phoneNumber = "0212345";
            customer.address = "318/A";
            customer.billingAddress = "319/A";
            customer.save();
        }
    }

}
