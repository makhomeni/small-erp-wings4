/*
 * EmployeeController
 *
 * version-1
 *
 * copyright @ jabait
 */
package com.jabait.hrm

import grails.converters.JSON
import com.jabait.security.User
import org.json.JSONObject
import org.codehaus.groovy.grails.web.json.JSONArray
import com.jabait.hrm.payroll.AllowanceType
import com.jabait.hrm.payroll.Allowance


class EmployeeController {


    def employeeService;

    def index() {
        render "ok";
    }


    def employeeList() {
        render(view: '/hrm/employee_list', model: [type: "Employee List"])
    }
    //for departmnet
    def createEmployee() {
        render(view: "/hrm/employee_create", model: [titleOfPage: "Employee Registration"])
    }

    def showEmployeeDetails() {
        def user = User.get(params.id);
        def authorities = user.authorities
        def userGroup = user.userGroups
        Employee employee = Employee.findByUserCode(user.userCode)
//        def nameInstance = employee.employeeProfile.name;
        def employeeProfile = employee.employeeProfile;
        System.out.println(authorities)
        authorities.each {
            authorityName ->
            System.out.println(authorityName)
        }
        render(view: "/hrm/employee_details", model: [employeeProfile: employeeProfile, titleOfPage: "Employee Details"])

    }

    def saveEmployee() {
        render([result: employeeService.saveEmployee(params)] as JSON);
    }


    def deleteEmployee() {
        flash.message  = employeeService.deleteEmployee(params);
        redirect(action: "employeeList");
    }

    def showEmployee() {

        render(view: "/hrm/employee_list");
    }

    def saveEmployeeContactInfo() {
        if (!employeeService.saveEmployeeContactInfo(params)) {
            render([result: false] as JSON);
        } else {
            render([result: true] as JSON);
        }
    }



    def employmentJSONData() {
        render();
    }

    def saveEmployeeAvatar() {
//        MultipartHttpServletRequest request;
//        println(request.getFile("file").inputStream.text);
        def downloadfile = request.getFile("file");
        downloadfile.transferTo(new File("E:/employeeimage/a.jpeg"));
    }

    def organizationJsonData() {
        if (!params.limit) {
            params.max = 10;
        } else {
            params.max = params.limit;
        }

        render([organizations: Organization.list(offset: params.start, max: params.max), totalCount: Organization.count()] as JSON);
    }

    def employeeJsonData() {

        if (!params.limit) {
            params.max = 10;
        } else {
            params.max = params.limit;
        }

        List<Employee> employeeList;

        if (params.userCode != null){
            println("in usercode exist");
            employeeList = Employee.findAllByUserCode(params.userCode.toString());
        }
        else if (params.designation != null){
//            employeeList = Employee.executeQuery("select employe.userCode from Employee employe where employe.employeeProfile.jobTitle.jobTitleName = '"+params.designation+"'");

            employeeList = Employee.findAll("from Employee as employee where employee.employeeProfile.jobTitle.jobTitleName=?",
                    [params.designation]);
        }
        else if (params.active){
            employeeList = Employee.findAll("from Employee as employee where employee.active=?",
                    [Boolean.parseBoolean(params.active.toString())]);
            
        }
        else if (params.firstName){
            employeeList = Employee.findAll("from Employee as employee where employee.employeeProfile.name.firstName =?",
                    [params.firstName]);
//            employeeList = Employee.findAll{
//                employeeProfile.name.firstName.toString() ilike(params.firstName.toString())
//            }
        }
        else{
            employeeList = Employee.list();
        }

        List employees = new ArrayList();
        Map<String, String> employeeMap = null;
        
        for (Employee employee : employeeList){

            employeeMap = new HashMap<String, String>();

            def employeeFirstName = "";
            def employeeLastName = "";
            def employeeMiddleName = "";
            def designation = "";
            def salary = "";


            if (employee?.employeeProfile?.name?.firstName != null){
                employeeFirstName = employee?.employeeProfile?.name?.firstName;
            }
            if (employee?.employeeProfile?.name?.middleName != null){
                employeeMiddleName = employee?.employeeProfile?.name?.middleName;
            }
            if (employee?.employeeProfile?.name?.surname != null){
                employeeLastName = employee?.employeeProfile?.name?.surname;
            }
            
            String employeeFullName = employeeFirstName + " " + employeeMiddleName + " " + employeeLastName;

            def employeeDesignation = employee?.employeeProfile?.jobTitle?.jobTitleName;
            if (employeeDesignation){
                designation = employeeDesignation;
            }
            
            def employeeSalary = employee?.employeeProfile?.salary;
            if (employeeSalary){
                salary = employeeSalary;
            }


            employeeMap.put("id", employee.id.toString());
            employeeMap.put("userCode", employee.userCode);
            employeeMap.put("active", employee.active.toString());
            employeeMap.put("fullName", employeeFullName.toString());
            employeeMap.put("designation", designation);
//            employeeMap.put("phone", employee?.employeeProfile?.mobile.toString());
            employeeMap.put("salary", salary);

            employees.add(employeeMap);

        }

        ////
        int max = 10;
        int totalCount = employees.size();


        if (totalCount < 10) {
            max = employees.size()
        }

        int start = (params.start != null) ? Integer.parseInt(params.start) : 0;
        int limit = (params.limit != null) ? Integer.parseInt(params.limit) : 10;

        render([employees : employees.asList().subList(start, start + limit > employees.size() ? employees.size() : start + limit),totalCount:totalCount] as JSON);
        

    }

    def departmentJsonData() {
        if (!params.limit) {
            params.max = 10;
        } else {
            params.max = params.limit;
        }
        println("org id = " + params.orgId)
        def organization = Organization.get(params.orgId)

        def totalCount = 0;
        organization?.departments?.each {
            totalCount++;
        }
        def max = 10;
        if (totalCount < 10) {
            max = totalCount
        }
        render([departments: organization?.departments?.asList()?.subList(0, max), totalCount: totalCount] as JSON);
    }

    def sectionJsonData() {
        if (!params.limit) {
            params.max = 10;
        } else {
            params.max = params.limit;
        }

        println("department id = " + params.deptId)
        def department = null;
        def parentDepartment = Department?.get(params.deptId);
        if(parentDepartment!=null){
            department = Department.findAllByParentDepartment(parentDepartment,params);
        } else{
            department = Department.findAllByParentDepartmentIsNull(params);
        }
        long count=0;
        if (parentDepartment!=null){
            count = Department.countByParentDepartment(parentDepartment)
        } else{
            count = Department.countByParentDepartmentIsNull()
        }



        render([sections: department, totalCount: count] as JSON);
    }

    def designationJsonData(){
        if (!params.limit) {
            params.max = 10;
        } else {
            params.max = params.limit;
        }

        render([designations: JobTitle.list(offset: params.start, max: params.max), totalCount: JobTitle.count()] as JSON);
    }
    
    def updateEmployeeName(){
        render ([result:employeeService.updateName(params)] as JSON);
    }
    def updateMiscellaneous(){
        render([result:employeeService.updateMiscellaneous(params)] as JSON);
    }
    def updateContactInfo(){
        render(employeeService.updateContactInfo(params));
    }
    def updateOrgInfo(){
        render(employeeService.updateOrgInfo(params));
    }

    def updateAllowanceInfo(){
        Map<String, String> operationResult = new HashMap<String, String>();
        Map<String, Double> updatedValue = new HashMap<String, Double>();
        List<Map<String,String>> operationResultList = new ArrayList<Map<String,String>>();
        List<Map<String,Double>> updatedValueList = new ArrayList<Map<String,Double>>();

        AllowanceType allowanceType;
        Allowance allowance;
        EmployeeProfile employeeProfile = EmployeeProfile.get(params.profileId);

        JSONArray userJsonArray = new JSONArray(params.serverJsonData);
        println "userJsonArray.length() = " + userJsonArray.length();

        //iterate through the json array
        for (int i = 0; i < userJsonArray.length(); i++){
            def update = false;
            println(i+ " --------time in loop start------------");
            JSONObject jsonObject = new JSONObject(userJsonArray.get(i))
            println "jsonObject.get(\"id\")  = " + jsonObject.get("id")
            println "jsonObject.get(\"optionValue\")  = " + jsonObject.get("optionValue")
//            allowance = new Allowance();
            allowanceType = AllowanceType.get(Integer.parseInt(jsonObject.get("id").toString()))
//            allowance.allowanceType = allowanceType;
//            allowance.allowanceAmount = Double.parseDouble(jsonObject.get("optionValue").toString());
            employeeProfile.owner.allowances.each {
                empAllowance->
                println("  loop $i = "+empAllowance.allowanceType.allowanceName+" allowance type name from db = "+allowanceType.allowanceName)
                if (empAllowance.allowanceType.id == allowanceType.id) {
                    empAllowance.allowanceAmount = Double.parseDouble(jsonObject.get("optionValue").toString());
                    if (empAllowance.save()){
                        update = true;
                        operationResult.put(allowanceType.allowanceName , "updated");
                        updatedValue.put(allowanceType.allowanceName, Double.parseDouble(jsonObject.get("optionValue").toString()))
                        println(empAllowance.id+" type name ="+empAllowance.allowanceType.allowanceName+".  previous allowance updated successfully");
                    }else{
                        operationResult.put(allowanceType.allowanceName , "not updated");
                        updatedValue.put(allowanceType.allowanceName, empAllowance.allowanceAmount);
                    }

                }
            }

            if (update){
                continue;
            }
            allowance = new Allowance();
            allowance.allowanceType = allowanceType;
            allowance.allowanceAmount = Double.parseDouble(jsonObject.get("optionValue").toString());
            if (allowance.save()){
                employeeProfile.owner.addToAllowances(allowance).save(flush: true);
                operationResult.put(allowanceType.allowanceName , "saved");
                updatedValue.put(allowanceType.allowanceName, Double.parseDouble(jsonObject.get("optionValue").toString()))
                println(jsonObject.get("id").toString() + " new allowance saved successfully");
            }else{
                operationResult.put(allowanceType.allowanceName , "not saved");
                updatedValue.put(allowanceType.allowanceName, Double.parseDouble(jsonObject.get("optionValue").toString()))
                updatedValue.put(allowanceType.allowanceName, 0);
            }
            println(i+ " ----------------time in loop end-----------------");
            
        }

        operationResultList.add(operationResult);
        updatedValueList.add(updatedValue);

        render([results: operationResultList, update: updatedValueList, result: true] as JSON);

    }

    def saveEmployeeAllowance() {
//        if (!employeeService.saveEmployeeAllowance(params)) {
//            render([result: false] as JSON);
//        } else {
//            render([result: true] as JSON);
//        }


        //get the allowances values from user input
        //if value is not null then get the allowance type save Allowance and assign that allowances in employee



        Map<String, String> operationResult = new HashMap<String, String>();
        List<Map<String,String>> operationResultList = new ArrayList<Map<String,String>>();

        AllowanceType allowanceType;
        Allowance allowance;
        EmployeeProfile employeeProfile = EmployeeProfile.get(params.profileId);

        JSONArray userJsonArray = new JSONArray(params.serverJsonData);
        println "userJsonArray.length() = " + userJsonArray.length();
        boolean result = true;

        //iterate through the json array
        for (int i = 0; i < userJsonArray.length(); i++){

            println(i+ " --------time in loop start------------");

            JSONObject jsonObject = new JSONObject(userJsonArray.get(i))

            println "jsonObject.get(\"id\")  = " + jsonObject.get("id")
            println "jsonObject.get(\"optionValue\")  = " + jsonObject.get("optionValue")

            if (jsonObject.get("optionValue")){
                allowanceType = AllowanceType.get(Integer.parseInt(jsonObject.get("id").toString()))
                allowance = new Allowance();
                allowance.allowanceAmount =  Double.parseDouble(jsonObject.get("optionValue").toString());
                allowance.allowanceType = allowanceType;
                if (allowance.save()){
                    employeeProfile.owner.addToAllowances(allowance).save(flush: true);
                    operationResult.put(allowanceType.allowanceName, "saved")
                }
                else{
                    result = result & false;
                    operationResult.put(allowanceType.allowanceName, "Save Failed")
                }
            }


            println(i+ " ----------------time in loop end-----------------");

        } //end for loop

        operationResultList.add(operationResult);

        render([details: operationResultList,  result: result] as JSON);

    }
}
