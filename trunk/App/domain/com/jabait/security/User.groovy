package com.jabait.security
import com.jabait.coresecurity.util.EncryptionUtils
import com.jabait.hrm.time.OfficeHour
import com.jabait.attendance.util.FeatureCollection

class User {

    Integer id;
    String userCode;
    String password;
    boolean active;

    static mapping = {
        table("security_user")
        discriminator(column: [name: "user_type", length: 255], value: "admin")
    }

    static constraints = {
        userCode(blank:false, size:3..100,unique:true)
        password(blank:false, size:4..100)
    }

    static hasMany = [authorities : Authority, userGroups : UserGroup]



    public static void initialize(){
        EncryptionUtils encUtil = new EncryptionUtils("jabait");
        if(User.getCount() == 0){

            def name = new Name(
                    firstName: "Mohammed",
                    middleName: "Hossain",
                    surname: "Doula",
                    nickname: "Ronnie"
            )
            
            def defaultFeature1 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "security",
                    operation: "userList"
            )

            def defaultFeature2 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "security",
                    operation: "featureList"
            )

            def defaultFeature3 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "security",
                    operation: "authorityList"
            )

            def defaultFeature4 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "security",
                    operation: "createUser"
            )

            def defaultFeature5 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "security",
                    operation: "saveUser"
            )

            def defaultFeature6 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "security",
                    operation: "deleteUser"
            )

            def defaultFeature7 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "security",
                    operation: "createAuthority"
            )

            def defaultFeature8 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "security",
                    operation: "saveAuthority"
            )

            def defaultFeature9 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "security",
                    operation: "deleteAuthority"
            )
            //feature
            def defaultFeature10 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "security",
                    operation: "createFeature"
            )

            def defaultFeature11 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "security",
                    operation: "saveFeature"
            )

            def defaultFeature12 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "security",
                    operation: "deleteFeature"
            )
            def defaultFeature13 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "security",
                    operation: "editUser"
            )
            def defaultFeature14 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "security",
                    operation: "updateUser"
            )
            def defaultFeature15 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "security",
                    operation: "editAuthority"
            )
            def defaultFeature16 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "security",
                    operation: "updateAuthority"
            )
            def defaultFeature17 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "security",
                    operation: "editFeature"
            )
            def defaultFeature18 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "security",
                    operation: "updateFeature"
            )
            def defaultFeature19 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "security",
                    operation: "userGroupList"
            )
            def defaultFeature20 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "security",
                    operation: "createUserGroup"
            )
            def defaultFeature21 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "security",
                    operation: "saveUserGroup"
            )
            def defaultFeature22 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "security",
                    operation: "editUserGroup"
            )
            def defaultFeature23 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "security",
                    operation: "updateUserGroup"
            )
            def defaultFeature24 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "security",
                    operation: "authorityAssignment"
            )
            def defaultFeature25 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "security",
                    operation: "featureAssignment"
            )

            def defaultFeature26 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "security",
                    operation: "userGroupAssignment"
            )
            def defaultFeature27 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "security",
                    operation: "deleteUserGroup"
            )


            //for hrm employee
            def defaultFeature28 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "employee",
                    operation: "employeeList"
            )

            def defaultFeature29 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "employee",
                    operation: "createEmployee"
            )
            def defaultFeature30 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "employee",
                    operation: "saveEmployee"
            )
            def defaultFeature31 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "employee",
                    operation: "deleteEmployee"
            )
            def defaultFeature32 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "employee",
                    operation: "saveEmployeeContactInfo"
            )


            def defaultFeature33 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "employee",
                    operation: "saveEmployeeAllowance"
            )
            def defaultFeature34 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "employee",
                    operation: "updateEmployeeName"
            )
            def defaultFeature35 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "employee",
                    operation: "updateMiscellaneous"
            )
            def defaultFeature36 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "employee",
                    operation: "updateContactInfo"
            )
            def defaultFeature37 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "employee",
                    operation: "updateOrgInfo"
            )
            def defaultFeature38 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "employee",
                    operation: "updateAllowanceInfo"
            )

            //for hrn configuration

            def defaultFeature39 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "createOrganization"
            )
            def defaultFeature40 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "saveOrganization"
            )
            def defaultFeature41 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "deleteOrganization"
            )
            def defaultFeature42 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "organizationList"
            )
            def defaultFeature43 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "createDepartment"
            )
            def defaultFeature44 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "saveDepartment"
            )
            def defaultFeature45 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "deleteDepartment"
            )
            def defaultFeature46 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "departmentList"
            )
            def defaultFeature47 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "createSection"
            )
            def defaultFeature48 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "deleteSection"
            )

            def defaultFeature49 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "sectionList"
            )
            def defaultFeature50 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "saveSection"
            )
            def defaultFeature51 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "createLeave"
            )
            def defaultFeature52 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "saveLeave"
            )
            def defaultFeature53 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "deleteLeave"
            )

            def defaultFeature54 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "leaveList"
            )

            def defaultFeature55 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "createJobSpec"
            )
            def defaultFeature56 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "deleteJobSpec"
            )
            def defaultFeature57 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "jobSpecList"
            )
            def defaultFeature58 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "saveSpec"
            )
            def defaultFeature59 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "createJobTitle"
            )
            def defaultFeature60 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "deleteJobTitle"
            )
            def defaultFeature61 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "jobTitleList"
            )
            def defaultFeature62 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "saveTitle"
            )
            def defaultFeature63 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "createRoleList"
            )
            def defaultFeature64 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "saveRole"
            )
            def defaultFeature65 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "deleteJobRole"
            )
            def defaultFeature66 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "jobRoleList"
            )
            def defaultFeature67 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "createEmploymentStatus"
            )
            def defaultFeature68 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "saveEmploymentStatus"
            )
            def defaultFeature69 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "deleteEmployeeStatus"
            )
            def defaultFeature70 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "employmentStatusList"
            )

            def defaultFeature71 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "editJobRole"
            )
            def defaultFeature72 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "updateJobRole"
            )
            def defaultFeature73 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "editJobSpec"
            )
            def defaultFeature74 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "updateJobSpec"
            )
            def defaultFeature75 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "editLeave"
            )
            def defaultFeature76 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "updateLeave"
            )
            def defaultFeature77 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "allowanceTypeList"
            )
            def defaultFeature78 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "deleteAllowanceType"
            )
            def defaultFeature79 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "saveAllowanceType"
            )

            def defaultFeature80 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "createAllowanceType"
            )

            def defaultFeature81 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "editTitle"
            )

            def defaultFeature82 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "configuration",
                    operation: "updateJobTitle"
            )


            //leave controller

            def defaultFeature83 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "leave",
                    operation: "saveLeave"
            )

            def defaultFeature84 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "leave",
                    operation: "waiverList"
            )

            def defaultFeature85 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "leave",
                    operation: "advanceWaiverList"
            )

            def defaultFeature86 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "leave",
                    operation: "saveWaiverRequest"
            )

            def defaultFeature87 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "leave",
                    operation: "saveAdvanceWaiverRequest"
            )

            def defaultFeature88 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "leave",
                    operation: "leaveList"
            )

            // hrm for attendance controller

            def defaultFeature89 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "attendance",
                    operation: "saveEmployeeEntry"
            )

            def defaultFeature90 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "attendance",
                    operation: "saveAdminPrivilegedAttendance"
            )
            def defaultFeature91 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "attendance",
                    operation: "attendanceAdjustmentList"
            )
            def defaultFeature92 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "attendance",
                    operation: "lateThresholdList"
            )


            def defaultFeature93 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "attendance",
                    operation: "saveThresholdType"
            )

            def defaultFeature94 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "attendance",
                    operation: "createLateThreshold"
            )

            def defaultFeature95 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "attendance",
                    operation: "createAdjustmentThreshold"
            )

            def defaultFeature96 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "attendance",
                    operation: "deleteAttendanceAdjustment"
            )

            def defaultFeature97 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "attendance",
                    operation: "deleteLateThreshold"
            )

            def defaultFeature98 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "attendance",
                    operation: "excelDataList"
            )

            def defaultFeature99 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "attendance",
                    operation: "attendanceList"
            )



            ///////////////

            //hrm payroll
            def defaultFeature100 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "payroll",
                    operation: "payRegisterList"
            )



            def defaultFeature101 = new Feature(
                    description: "all",
                    documents: "all",
                    fields: "all",
                    module: "payroll",
                    operation: "savePayRegister"
            )

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

            def defaultFeature110 = new Feature(
                    description: "Feature to get access to emplyee edit in BioMetric Application",
                    documents: "all",
                    fields: "all",
                    module: "security",
                    operation: "deleteFeatureFromAuthority"
            )

            def defaultFeature111 = new Feature(
                    description: "Feature to access Advance Pay Register List",
                    documents: "all",
                    fields: "all",
                    module: "payroll",
                    operation: "advancePayRegisterList"
            )

            def defaultFeature112 = new Feature(
                    description: "Feature to access Advance Pay Register List",
                    documents: "all",
                    fields: "all",
                    module: "payroll",
                    operation: "createAdvancePayRequest"
            )
            def defaultFeature113 = new Feature(
                    description: "Feature to access Advance Pay Register List",
                    documents: "all",
                    fields: "all",
                    module: "payroll",
                    operation: "saveAdvancePayRegister"
            )

            //create feature

            def defaultAuthority = new Authority(
                    roleTitle: "gen_admin",
                    description: "general admin"
            )


            //defaultAuthority.save(flush: true)
            //add features to authority
            defaultAuthority.addToFeatures(defaultFeature1).addToFeatures(defaultFeature2)
                    .addToFeatures(defaultFeature3).addToFeatures(defaultFeature4)
                    .addToFeatures(defaultFeature5).addToFeatures(defaultFeature6).addToFeatures(defaultFeature7)
                    .addToFeatures(defaultFeature8).addToFeatures(defaultFeature9).addToFeatures(defaultFeature10)
                    .addToFeatures(defaultFeature11).addToFeatures(defaultFeature12).addToFeatures(defaultFeature13)
                    .addToFeatures(defaultFeature14).addToFeatures(defaultFeature15).addToFeatures(defaultFeature16)
                    .addToFeatures(defaultFeature17).addToFeatures(defaultFeature18).addToFeatures(defaultFeature19)
                    .addToFeatures(defaultFeature20).addToFeatures(defaultFeature21)
                    .addToFeatures(defaultFeature22).addToFeatures(defaultFeature23).addToFeatures(defaultFeature24)
                    .addToFeatures(defaultFeature25).addToFeatures(defaultFeature26).addToFeatures(defaultFeature27)
                    .addToFeatures(defaultFeature28).addToFeatures(defaultFeature29).addToFeatures(defaultFeature30)
                    .addToFeatures(defaultFeature31).addToFeatures(defaultFeature32).addToFeatures(defaultFeature33)
                    .addToFeatures(defaultFeature34).addToFeatures(defaultFeature35).addToFeatures(defaultFeature36)
                    .addToFeatures(defaultFeature37).addToFeatures(defaultFeature38).addToFeatures(defaultFeature39)
                    .addToFeatures(defaultFeature40).addToFeatures(defaultFeature41).addToFeatures(defaultFeature42)
                    .addToFeatures(defaultFeature43).addToFeatures(defaultFeature44).addToFeatures(defaultFeature45)
                    .addToFeatures(defaultFeature46).addToFeatures(defaultFeature47).addToFeatures(defaultFeature48)
                    .addToFeatures(defaultFeature49).addToFeatures(defaultFeature50).addToFeatures(defaultFeature51)
                    .addToFeatures(defaultFeature52).addToFeatures(defaultFeature53).addToFeatures(defaultFeature54)
                    .addToFeatures(defaultFeature55).addToFeatures(defaultFeature56).addToFeatures(defaultFeature57)
                    .addToFeatures(defaultFeature58).addToFeatures(defaultFeature59).addToFeatures(defaultFeature60)
                    .addToFeatures(defaultFeature61).addToFeatures(defaultFeature62).addToFeatures(defaultFeature63)
                    .addToFeatures(defaultFeature64).addToFeatures(defaultFeature65).addToFeatures(defaultFeature66)
                    .addToFeatures(defaultFeature67).addToFeatures(defaultFeature68).addToFeatures(defaultFeature69)
                    .addToFeatures(defaultFeature70).addToFeatures(defaultFeature71).addToFeatures(defaultFeature72)
                    .addToFeatures(defaultFeature73).addToFeatures(defaultFeature74).addToFeatures(defaultFeature75)
                    .addToFeatures(defaultFeature76).addToFeatures(defaultFeature77).addToFeatures(defaultFeature78)
                    .addToFeatures(defaultFeature79).addToFeatures(defaultFeature80).addToFeatures(defaultFeature81)
                    .addToFeatures(defaultFeature82).addToFeatures(defaultFeature83).addToFeatures(defaultFeature84)
                    .addToFeatures(defaultFeature85).addToFeatures(defaultFeature86).addToFeatures(defaultFeature87)
                    .addToFeatures(defaultFeature88).addToFeatures(defaultFeature89).addToFeatures(defaultFeature90)
                    .addToFeatures(defaultFeature91).addToFeatures(defaultFeature92).addToFeatures(defaultFeature93)
                    .addToFeatures(defaultFeature94).addToFeatures(defaultFeature95).addToFeatures(defaultFeature96)
                    .addToFeatures(defaultFeature97).addToFeatures(defaultFeature98).addToFeatures(defaultFeature99)
                    .addToFeatures(defaultFeature100).addToFeatures(defaultFeature101).addToFeatures(defaultFeature102)
                    .addToFeatures(defaultFeature103).addToFeatures(defaultFeature104).addToFeatures(defaultFeature105)
                    .addToFeatures(defaultFeature106).addToFeatures(defaultFeature107).addToFeatures(defaultFeature108)
                    .addToFeatures(defaultFeature109).addToFeatures(defaultFeature110).addToFeatures(defaultFeature111)
                    .addToFeatures(defaultFeature112).addToFeatures(defaultFeature113)


            //////    ///// for authority and feature end

            def defaultUser = new User(
                    userCode: "admin",
                    password: encUtil.encrypt("asadmin"),
                    active: true
            )

            //defaultUser.save(flush: true);
            defaultUser.addToAuthorities(defaultAuthority).save(flush: true);

            Date startDate = new Date();
            startDate.setHours(10);
            startDate.setMinutes(00);
            startDate.setSeconds(00);
            
            Date endDate = new Date();
            endDate.setHours(19);
            endDate.setMinutes(00);
            endDate.setSeconds(00);
            
            OfficeHour officeHour = new OfficeHour();
            officeHour.startTime=startDate;
            officeHour.endTime = endDate;
            officeHour.save(flush: true);


        }
    }
}
