package com.jabait.scm.procurement

import com.jabait.scm.Vendor
import com.jabait.hrm.Organization
import com.jabait.scm.LocalVendor

class VendorService {

    def serviceMethod() {

    }

    def saveLocalVendor(params){

        LocalVendor localVendor = new LocalVendor();
        localVendor.firstName = params.firstName;
        localVendor.lastName = params.lastName;
        localVendor.description = params.description;
        localVendor.emailId = params.emailId;
        localVendor.phoneNo = params.phoneNo;

        Organization organization = Organization.get(Integer.parseInt(params.orgId));
        localVendor.organization = organization;
        if(localVendor.save()){
            return "Vendor created successfully";
        }else{
            return "Vendor creation failed";
        }

    }
}
