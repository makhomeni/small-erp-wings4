package com.jabait.scm.vendor

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
    }
    def localVendorList(){
        render(view: "/scm/local_vendor_list", model: [type: "Vendor List"]);
    }

    def localVendorJsonData(){
        if (!params.limit) {
            params.max = 10;
        } else {
            params.max = params.limit;
        }
        List<LocalVendor> localVendorList = new ArrayList<LocalVendor>();

        localVendorList = LocalVendor.list();

        List<Map> localVendors = new ArrayList<>();

        Map<String, String> vendorMap;

        for (LocalVendor localVendor : localVendorList){

            vendorMap = new HashMap<String, String>();
            def vendorFirstName = localVendor.firstName;
            def vendorLastName = localVendor.lastName;

            def vendorFullName = vendorFirstName + " " + vendorLastName
            def organizationName = localVendor?.organization?.organizationName;
            if (!organizationName){
                organizationName = "";
            }

            vendorMap.put("vendorName", vendorFullName);
            vendorMap.put("organizationName", organizationName);
            vendorMap.put("mobileNo", localVendor?.mobileNo);
            vendorMap.put("description", localVendor.description);
            vendorMap.put("emailId", localVendor.emailId);
            vendorMap.put("phoneNo", localVendor.phoneNo);

            localVendor.add(vendorMap);
        }

        /* for pagination */

        int totalCount = localVendors.size();



        int start = (params.start != null) ? Integer.parseInt(params.start) : 0;
        int limit = (params.limit != null) ? Integer.parseInt(params.limit) : 10;

        render([vendors : localVendors.asList().subList(start, start + limit > totalCount ? totalCount : start + limit),totalCount:totalCount] as JSON);

    }
    //////vendor///////

}
