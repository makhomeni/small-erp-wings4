package com.jabait.scm.vendor

import grails.converters.JSON
import com.jabait.scm.Vendor

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
        List<Vendor> vendorList = new ArrayList<Vendor>();

        vendorList = Vendor.list();

        List<Map> vendors = new ArrayList<>();

        Map<String, String> vendorMap;

        for (Vendor vendor : vendorList){

            vendorMap = new HashMap<String, String>();
            def vendorFirstName = vendor.firstName;
            def vendorLastName = vendor.lastName;

            def vendorFullName = vendorFirstName + " " + vendorLastName
            def organizationName = vendor?.organization?.organizationName;
            if (!organizationName){
                organizationName = "";
            }

            vendorMap.put("vendorName", vendorFullName);
            vendorMap.put("organizationName", organizationName);
            vendorMap.put("mobileNo", vendor?.mobileNo);
            vendorMap.put("description", vendor.description);
            vendorMap.put("emailId", vendor.emailId);
            vendorMap.put("phoneNo", vendor.phoneNo);

            vendors.add(vendorMap);
        }

        /* for pagination */

        int totalCount = vendors.size();



        int start = (params.start != null) ? Integer.parseInt(params.start) : 0;
        int limit = (params.limit != null) ? Integer.parseInt(params.limit) : 10;

        render([vendors : vendors.asList().subList(start, start + limit > totalCount ? totalCount : start + limit),totalCount:totalCount] as JSON);

    }
    //////vendor///////

}
