package com.jabait.hrm


import com.jabait.security.Email
import com.jabait.security.Phone
import com.jabait.security.Address
import com.jabait.admin.SystemSettings


class Organization {

    String organizationName;
    Organization parent;
    Email email;
    Phone phone;
    Address address;

    static hasMany = [departments : Department, systemSettings: SystemSettings]

    static constraints = {
        parent(nullable:true);
        organizationName(nullable: false);
        email(nullable: false);
        departments(nullable: true);
        systemSettings(nullable: true);
    }

    public static initialize(){
        if(Organization.getCount() == 0){
            Organization organization = new Organization();

            Address address = new Address();
            address.country = "Bangladesh";
            address.extendedAddress = "Bonani";
            address.poBox = "1213";
            address.postalCode = "1213";
            address.region = "Dhaka";
            address.streetAddress = "3";

            Email email = new Email();

            email.address = "info@ogl.co";
            email.type = "Organization email";

            Phone phone = new Phone();
            phone.number  = "9890875";
            phone.type = "organization phone";

            organization.address = address;
            organization.organizationName = "Ocean Group";
            organization.email = email;
            organization.phone = phone;
            organization.parent = null;
            
            organization.save(flush: true);

        }
    }
}
