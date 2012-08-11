package com.jabait.security

import com.jabait.attendance.util.FeatureCollection
import com.jabait.accounting.PaymentTerm
import com.jabait.scm.DeliveryTerm

class Feature {

    Integer id
    String description
    String documents
    String fields
    String module
    String operation

    static hasMany = [authorities : Authority]

    static belongsTo = [Authority]


    static constraints = {
        description(nullable: true)
        documents(nullable: true)
    }

    public static void initialize(){
        List<Feature> fingerPrintFeatures = Feature.findAllByModule(FeatureCollection.MODULE_NAME);
        if (fingerPrintFeatures?.isEmpty()) {
            def defaultFeature102 = new Feature(
                    description: "Feature to get access to all the functionality of BioMetric Application including Employee registration,finger print attachment, assign user/password",
                    documents: "all",
                    fields: "all",
                    module: FeatureCollection.MODULE_NAME,
                    operation: FeatureCollection.ADMINISTRATIVE_OPERATION
            )

            def defaultFeature103 = new Feature(
                    description: "Feature to get access to all the Attendance related activity in BioMetric Application excluding Employee registration,finger print attachment, assign user/password",
                    documents: "all",
                    fields: "all",
                    module: FeatureCollection.MODULE_NAME,
                    operation: FeatureCollection.ATTENDANCE_ADMIN
            )

            def defaultFeature104 = new Feature(
                    description: "Feature to get access to only Attendance Entry",
                    documents: "all",
                    fields: "all",
                    module: FeatureCollection.MODULE_NAME,
                    operation: FeatureCollection.ATTENDANCE_ENTRY
            )

            def defaultFeature105 = new Feature(
                    description: "Feature to get access to only attendance exit",
                    documents: "all",
                    fields: "all",
                    module: FeatureCollection.MODULE_NAME,
                    operation: FeatureCollection.ATTENDANCE_EXIT
            )

            def defaultFeature106 = new Feature(
                    description: "Feature to get access to view attendance list, attendance entry and attendance exit but will not get administrative attendance control",
                    documents: "all",
                    fields: "all",
                    module: FeatureCollection.MODULE_NAME,
                    operation: FeatureCollection.ATTENDANCE_LIST_VIEWER
            )

            def defaultFeature107 = new Feature(
                    description: "Feature to get access to view & edit attendance list, attendance entry and attendance exit",
                    documents: "all",
                    fields: "all",
                    module: FeatureCollection.MODULE_NAME,
                    operation: FeatureCollection.ATTENDANCE_LIST_MODIFIER
            )

            def defaultFeature108 = new Feature(
                    description: "Feature to get access to emplyee registration access in BioMetric Application",
                    documents: "all",
                    fields: "all",
                    module: FeatureCollection.MODULE_NAME,
                    operation: FeatureCollection.EMPLOYEE_REGISTRAR
            )

            def defaultFeature109 = new Feature(
                    description: "Feature to get access to emplyee edit in BioMetric Application",
                    documents: "all",
                    fields: "all",
                    module: FeatureCollection.MODULE_NAME,
                    operation: FeatureCollection.EMPLOYEE_REGISTRAR_EDIT
            )

            //create feature

            def defaultAuthority = new Authority(
                    roleTitle: "Fingerprint Administrator",
                    description: "Finger Print Super Admin"
            )
            defaultAuthority.addToFeatures(defaultFeature102)
                    .addToFeatures(defaultFeature103).addToFeatures(defaultFeature104).addToFeatures(defaultFeature105)
                    .addToFeatures(defaultFeature106).addToFeatures(defaultFeature107).addToFeatures(defaultFeature108)
                    .addToFeatures(defaultFeature109);
            defaultAuthority.save(flush: true);


        }


        //add advance payroll related FEATURE
        if(Feature.findByOperation("advancePayRegisterList") == null){
            def advancePayRegisterListFeature = new Feature(
                    description: "Feature to see advance pay register",
                    documents: "all",
                    fields: "all",
                    module: "payroll",
                    operation: "advancePayRegisterList"
            )
            Authority.get(1).addToFeatures(advancePayRegisterListFeature).save(flush: true);
        }

        if(Feature.findByOperation("createAdvancePayRequest") == null){
            def createAdvancePayRequestFeature = new Feature(
                    description: "Feature to see create advance pay register",
                    documents: "all",
                    fields: "all",
                    module: "payroll",
                    operation: "createAdvancePayRequest"
            )
            Authority.get(1).addToFeatures(createAdvancePayRequestFeature).save(flush: true);
        }


        if(Feature.findByOperation("saveAdvancePayRegister") == null){
            def saveAdvancePayRegisterFeature = new Feature(
                    description: "Feature to see save advance pay register",
                    documents: "all",
                    fields: "all",
                    module: "payroll",
                    operation: "saveAdvancePayRegister"
            )
            Authority.get(1).addToFeatures(saveAdvancePayRegisterFeature).save(flush: true);
        }
        if(Feature.findByOperation("createLocalVendor") == null){
            def createVendorFeature = new Feature(
                    description: "Feature create vendor",
                    documents: "all",
                    fields: "all",
                    module: "vendor",
                    operation: "createLocalVendor"
            )
            Authority.get(1).addToFeatures(createVendorFeature).save(flush: true);
        }
        if(Feature.findByOperation("saveLocalVendor") == null){
            def saveVendorFeature = new Feature(
                    description: "Feature save vendor",
                    documents: "all",
                    fields: "all",
                    module: "vendor",
                    operation: "saveLocalVendor"
            )
            Authority.get(1).addToFeatures(saveVendorFeature).save(flush: true);
        }
        if(Feature.findByOperation("localVendorList") == null){
            def vendorListFeature = new Feature(
                    description: "Feature for vendor list",
                    documents: "all",
                    fields: "all",
                    module: "vendor",
                    operation: "localVendorList"
            )
            Authority.get(1).addToFeatures(vendorListFeature).save(flush: true);
        }
        if(Feature.findByOperation("createCategory") == null){
            def createCategoryFeature = new Feature(
                    description: "Feature to create category",
                    documents: "all",
                    fields: "all",
                    module: "inventory",
                    operation: "createCategory"
            )
            Authority.get(1).addToFeatures(createCategoryFeature).save(flush: true);
        }

        if(Feature.findByOperation("saveCategory") == null){
            def saveCategoryFeature = new Feature(
                    description: "Feature to save category",
                    documents: "all",
                    fields: "all",
                    module: "inventory",
                    operation: "saveCategory"
            )
            Authority.get(1).addToFeatures(saveCategoryFeature).save(flush: true);
        }

        if(Feature.findByOperation("categoryList") == null){
            def categoryListFeature = new Feature(
                    description: "Feature category list",
                    documents: "all",
                    fields: "all",
                    module: "inventory",
                    operation: "categoryList"
            )
            Authority.get(1).addToFeatures(categoryListFeature).save(flush: true);
        }

    }

    public static initializeSalesOrder(){
        if(PaymentTerm.count() == 0 ){
            PaymentTerm paymentTerm = new PaymentTerm();
            paymentTerm.days = 10;
            paymentTerm.name = "Late";
            paymentTerm.description = "Payment will be done in month";

            paymentTerm.save();
        }

        if(DeliveryTerm.count() == 0){
            DeliveryTerm deliveryTerm = new DeliveryTerm();
            deliveryTerm.terms = "test";
            deliveryTerm.description = "test";
            deliveryTerm.save();
        }


    }
}
