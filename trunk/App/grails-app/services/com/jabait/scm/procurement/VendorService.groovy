package com.jabait.scm.procurement

import com.jabait.scm.Vendor
import com.jabait.hrm.Organization
import com.jabait.scm.LocalVendor
import com.jabait.security.Address

class VendorService {

    def serviceMethod() {

    }

    def saveLocalVendor(params){

        LocalVendor localVendor = new LocalVendor();

        localVendor.firstName = params.firstName;
        localVendor.lastName = params.lastName;
        localVendor.description = params.description;
        localVendor.emailId = params.emailId;
        localVendor.mobileNo = params.mobileNo;
        localVendor.phoneNo = params.phoneNo;

        def country = params.country;
        def streetAddress = params.address1;
        def postalCode = params.postCode;

        Address address = new Address();
        address.streetAddress = streetAddress;
        address.country = country;
        address.postalCode = postalCode;

        Organization organization = Organization.get(Integer.parseInt(params.orgId));
        localVendor.organization = organization;
        localVendor.billingAddress = address;
        if(localVendor.save()){
            return "Vendor created successfully";
        }else{
            return "Vendor creation failed";
        }

    }
}
