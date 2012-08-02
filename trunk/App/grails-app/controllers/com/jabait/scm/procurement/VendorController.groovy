package com.jabait.scm.procurement

import grails.converters.JSON
import com.jabait.scm.Vendor
import com.jabait.scm.LocalVendor

class VendorController {

    def vendorService;

    def index() { }

    //////vendor///////
    def createLocalVendor(){
        render(view: "/scm/local_vendor_create", model: [type: "Create Vendor"]);
    }

    def saveLocalVendor(){
        flash.message = vendorService.saveLocalVendor(params);
        redirect(action: 'createLocalVendor');
    }
    def localVendorList(){
        render(view: "/scm/local_vendor_list", model: [type: "Local Vendor List"]);
    }

    def localVendorJsonData(){
        if (!params.limit) {
            params.max = 10;
        } else {
            params.max = params.limit;
        }
        List<LocalVendor> localVendorList = LocalVendor.list();

        List<Map<String,String>> localVendors = new ArrayList<Map<String,String>>();

        Map<String, String> localVendorMap;

        for (LocalVendor localVendor : localVendorList){

            localVendorMap = new HashMap<String,String>();
            def vendorFirstName = localVendor.firstName;
            def vendorLastName = localVendor.lastName;

            def vendorFullName = vendorFirstName + " " + vendorLastName
            def organizationName = localVendor?.organization?.organizationName;
            if (!organizationName){
                organizationName = "";
            }

            localVendorMap.put("vendorName", vendorFullName);
            localVendorMap.put("organizationName", organizationName);
            localVendorMap.put("mobileNo", localVendor?.mobileNo);
            localVendorMap.put("description", localVendor.description);
            localVendorMap.put("emailId", localVendor.emailId);
            localVendorMap.put("phoneNo", localVendor.phoneNo);

            localVendors.add(localVendorMap);
        }

        /* for pagination */

        int totalCount = localVendors.size();



        int start = (params.start != null) ? Integer.parseInt(params.start) : 0;
        int limit = (params.limit != null) ? Integer.parseInt(params.limit) : 10;

        render([vendors : localVendors.asList().subList(start, start + limit > totalCount ?
            totalCount : start + limit),totalCount:totalCount] as JSON);

    }
    //////vendor///////

}
