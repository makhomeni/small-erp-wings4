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

        localVendor.country = params.country;
        localVendor.address = params.address;




//        address.streetAddress = streetAddress;
//        address.country = country;
//        address.postalCode = postalCode;

        println("organization id = " + params.organizationId);

        Organization organization = Organization.get(params.organizationId);
        localVendor.organization = organization;
        if(localVendor.save()){
            return "Vendor created successfully";
        }else{
            return "Vendor creation failed";
        }

    }
}
