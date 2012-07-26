package com.jabait.configuration

import com.jabait.hrm.Organization
import com.jabait.hrm.Department
import com.jabait.hrm.EmploymentStatus
import com.jabait.hrm.JobRole
import com.jabait.hrm.JobSpec
import com.jabait.hrm.JobTitle
import com.jabait.hrm.time.Leave
import com.jabait.security.Address
import com.jabait.security.Email
import com.jabait.security.Phone
import com.jabait.hrm.time.LateThreshold
import com.jabait.security.Feature
import com.jabait.hrm.payroll.AllowanceType
import com.jabait.hrm.payroll.Frequency
import com.jabait.admin.SystemSettings

class ConfigurationService {

    def serviceMethod() {

    }
    def organizationList(){
        def organizations = Organization.list();
        return organizations;
    }

    def employeeStatusList(){
        def employeeStatusList = EmploymentStatus.list();
        return employeeStatusList;
    }

    def roleList(){
        def jobRoleList = JobRole.list();
        return jobRoleList;


    }

    def jobSpecList(){
        def jobSpec = JobSpec.list();
        return jobSpec;


    }

    def jobTitleList(){
        def jobTitles = JobTitle.list();
        return jobTitles;
    }

    def leaveList(){
        def leaves = Leave.list();
        return leaves;

    }

    def sectionList(){
        def sections = Department.list();
        return sections;

    }
    def saveOrganization(params){
        println("organizationName = "+params.organizationName);
        def organization = Organization.findByOrganizationName(params.organizationName)
        if(organization){
            println("already exits org")
            return "Organization allready exists";
        }else{
            def organizationName = params.organizationName;
            def parentOrganization = Organization.findById(params.organization);
            if(parentOrganization){
                println("parent org found")
            }

            def address = new Address();
            address.country = params.country;
            address.extendedAddress = params.extendedAddress;
            address.poBox = params.poBox;
            address.postalCode = params.postalCode;
            address.region = params.region;
            address.streetAddress = params.streetAddress;

            Email email = new Email();
            println("email = "+params.email);
            email.address = params.email;
            email.type = "Organization email";

            Phone phone = new Phone();
            phone.number  = params.phone;
            phone.type = "organization phone";

            Organization newOrganization= new Organization();
            newOrganization.address = address;
            newOrganization.organizationName = organizationName;
            newOrganization.email = email;
            newOrganization.phone = phone;
            newOrganization.parent = parentOrganization;

            if(newOrganization.hasErrors()){
                println(newOrganization.errors)

            }
            else{
                if(newOrganization.save()){
                    return "Organization saved successfully";
                }
                else{
                    return "Organization saved failed";
                }
            }

        }

    }

    def saveDepartment(params){
        def department = Department.findByDepartmentName(params.departmentName);
        if(department){
            return false;
        }
        else {

            Organization organization = Organization.get(params.organization);
            def newDepartment = new Department();
            if(organization){
                newDepartment.organization = organization;
                newDepartment.departmentName = params.departmentName;
                if(!newDepartment.save(flush: true)){
                    println("Not Save");
                    return false;
                }
                else {
                    println("Save");
                    return true;
                }

            }else{
                return false;
            }
        }
    }

    def saveRole(params){
        println("roleName = "+params.jobRoleName);
        println("roleDescription = "+params.jobRoleDescription);
        def role = JobRole.findByRoleName(params.jobRoleName)
        if(role){
            println("already exits role")
            return false;
        }
        else{
            def jobRole = new JobRole();
            jobRole.roleName = params.jobRoleName;
            jobRole.description = params.jobRoleDescription;
            if(!jobRole.save(flush: true)){
                println("Not Save");
                return false;
            }
            else {
                println("Save");
                return true;
            }

        }
    }

    def saveTitle(params){
        def title = JobTitle.findByJobTitleCode(params.jobTitleCode)
        if(title){
            println("already exits role")
            return false;
        }
        else{
            def jobTitle = new JobTitle();
            jobTitle.jobTitleCode = params.jobTitleCode;
            jobTitle.jobTitleName = params.jobTitleName;
            jobTitle.jobTitleDescription = params.jobTitleDescription;
            if(!jobTitle.save(flush: true)){
                println("Not Save");
                return false;
            }
            else {
                println("Save");
                return true;
            }

        }
    }


    def saveSpec(params){
        def spec = JobSpec.findByJobSpecName(params.jobSpaceName)
        if(spec){
            println("already exits role")
            return false;
        }
        else{
            def jobSpec = new JobSpec();
            jobSpec.jobSpecName = params.jobSpaceName;
            jobSpec.jobSpecDesc = params.jobSpecDesc;
            jobSpec.jobSpecDuties = params.jobSpecDuties;
            if(!jobSpec.save(flush: true)){
                println("Not Save");
                return false;
            }
            else {
                println("Save");
                return true;
            }

        }
    }

    def saveLeave(params){

        def leave = Leave.findByLeaveType(params.leaveType)
        if(leave){
            println("already exits role")
            return false;
        }
        else{
            def jobLeave = new Leave();
            jobLeave.leaveType = params.leaveType;
            jobLeave.leaveDuration = Integer.parseInt(params.leaveDuration);
            jobLeave.leaveNotes = params.leaveNotes;
            if(!jobLeave.save(flush: true)){
                println("Not Save");
                return false;
            }
            else {
                println("Save");
                return true;
            }

        }
    }

    def leaveEncashmentAssignment(params){
        def organizationId = Integer.parseInt(params.organizationId);
        def encashmentStatus = params.status;
        def systemKey = params.systemKey;
        println(organizationId + " "+ encashmentStatus);

        SystemSettings alreadyExistsSystemSettings = SystemSettings.findBySystemKey(systemKey);
        if(alreadyExistsSystemSettings){
            alreadyExistsSystemSettings.systemValue =  encashmentStatus;
            if(alreadyExistsSystemSettings.save()){
                return "Leave Encashment Saved Successfully";
            }else{
                return "Leave Encashment Save Failed";
            }
        }else{
            Organization organization = Organization.get(organizationId);

            SystemSettings systemSettings = new SystemSettings();
            systemSettings.systemKey = "leave encashment";
            systemSettings.systemValue = encashmentStatus;
            if(systemSettings.save()){
                organization.addToSystemSettings(systemSettings)
                println("saved");
                return "Leave Encashment Saved Successfully";
            }else{
                println("Not saved")
                return "Leave Encashment Save Failed";
            }
        }



    }

    def saveEmploymentStatus(params){

        def employmentStatus = EmploymentStatus.findByStatusCode(params.employeeStatusCode)
        if(employmentStatus){
            println("already exits role")
            return false;
        }
        else{
            def employment= new EmploymentStatus();
            employment.statusCode = params.employeeStatusCode;
            employment.statusName = params.employeeStatusName;

            if(!employment.save(flush: true)){
                println("Not Save");
                return false;
            }
            else {
                println("Save");
                return true;
            }

        }
    }

    def saveSection(params){

        if(Department.findByDepartmentName(params.sectionName)){
            return false;
        }
        else {

            Department parentDepartment = Department.get(params.department);
            Organization organization = Organization.get(params.organization);
            def section = new Department();

            section.departmentName = params.sectionName;
            section.parentDepartment = parentDepartment;
            section.organization = organization ;

            if(!section.save(flush: true)){
                println("Not Save");
                return false;
            }
            else {
                println("Save");
                return true;
            }
        }
    }

    boolean deleteDepartment(departmentInstance,id){
        if(!departmentInstance){
            return false;
        }
        try{
            def department = Department.get(id);
            department.delete(flush: true);
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    boolean deleteEmployeeStatus(employeeStatusInstance,id){
        if(!employeeStatusInstance){
            return false;
        }
        try{
            def employeeStatus = EmploymentStatus.get(id);
            employeeStatus.delete(flush: true);
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    boolean deleteJobTitle(jobTitleInstance,id){
        if(!jobTitleInstance){
            return false;
        }
        try{
            def jobTitle = JobTitle.get(id);
            jobTitle.delete(flush: true);
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    boolean deleteJobRole(jobRoleInstance,id){
        if(!jobRoleInstance){
            return false;
        }
        try{
            def jobRole = JobRole.get(id);
            jobRole.delete(flush: true);
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    boolean deleteJobSpec(jobSpecInstance,id){
        if(!jobSpecInstance){
            return false;
        }
        try{
            def jobSpec = JobSpec.get(id);
            jobSpec.delete(flush: true);
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    boolean deleteLeave(leaveInstance,id){
        if(!leaveInstance){
            return false;
        }
        try{
            def leave = Leave.get(id);
            leave.delete(flush: true);
            return true;
        } catch (Exception ex){
            return false;
        }
    }

     boolean deleteSection(sectionInstance,id){
        if(!sectionInstance){
            return false;
        }
        try{
            def section = Department.get(id);
            section.delete(flush: true);
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    boolean deleteOrganization(organizationInstance,id){
        if(!organizationInstance){
            return false;
        }
        try{
            def organization = Organization.get(id);
            organization.delete(flush: true);
            return true;
        } catch (Exception ex){
            return false;
        }
    }

//    boolean editJobSpec(jobSpecInstance,id){
//        // def encryptionUtils = new EncryptionUtils("jabait");
//        def jobSpec = JobSpec.get(id);
//        if(!jobSpec){
//            return false;
//        } else {
//            jobSpec.save(flush: true);
//            return true;
//        }
//    }
//
//    List loadJobSpec(jobSpecList){
//        return JobSpec.list(jobSpecList);
//    }

    String updateJobSpec(params){


        JobSpec jobSpec = JobSpec.get(params.jobSpecId);
        jobSpec.jobSpecName = params.jobSpecName;
        jobSpec.jobSpecDesc = params.jobSpecDesc;
        jobSpec.jobSpecDuties = params.jobSpecDuties;
        println("Jobspeck not exist");

        if(!jobSpec.save(flush: true)){
            return "Update failed";
        }else{
            return "Update Successfully";
        }
    }
    
    String updateJobTitle (params){
        
        JobTitle jobTitle = JobTitle.get(params.jobTitleId);
        jobTitle.jobTitleCode = params.jobTitleCode;
        jobTitle.jobTitleName = params.jobTitleName;
        jobTitle.jobTitleDescription = params.joTitleDescription;

        println("Job Title not Exist");

        if(!jobTitle.save(flush: true)){

            return "Update Failed";
        }else{

            return "Update Successfully";

        }

        
        
        
    }

    boolean updateJobRole(jobRoleId, params){

        JobRole jobRole = JobRole.get(jobRoleId);
        jobRole.roleName = params.roleName;
        jobRole.description = params.description;
        if(!jobRole.save(flush: true)){
            return false;
        }else{
            return true;
        }


    }


    boolean editLeave(id){
        // def encryptionUtils = new EncryptionUtils("jabait");
        def leave = Leave.get(id);
        if(!leave){
            return false;
        } else {
            leave.save(flush: true);
            return true;
        }
    }

    List loadLeave(leaveList){
        return Leave.list(leaveList);
    }

    boolean updateLeave(leaveId, params){

        Leave leave = Leave.get(leaveId);
        leave.leaveType = params.leaveType;
        leave.leaveDuration = Integer.parseInt(params.leaveDuration);

        println("leave duration"+params.leaveDuration)
        leave.leaveNotes = params.leaveNotes;
        if(!leave.save(flush: true)){
            return false;
        }else{
            return true;
        }


    }

    boolean deleteAllowanceType(allowanceInstance,id){
        if(!allowanceInstance){
            return false;
        }
        try{
            def allowance = AllowanceType.get(id);
            allowance.delete(flush: true);
            return true;
        } catch (Exception ex){
            return false;
        }
    }


    def saveAllowanceType(params){
        def allowanceType = AllowanceType.findAllByAllowanceName(params.allowanceName)
        if(allowanceType){
            println("already exits role")
            return false;
        }
        else{
            def allowance = new AllowanceType();
            println "params.allowanceName = $params.allowanceName"
            allowance.allowanceName = params.allowanceName;
            allowance.allowanceDescription = params.allowanceDescription;

            if(!allowance.save(flush: true)){
                println("Not Save");
                return false;
            }
            else {
                println("Save");
                return true;
            }

        }
    }
    def allowenceTypeList(){
        def allowenceType = AllowanceType.list();
        return allowenceType;

    }


//    boolean editJobTitle(jobTitleInstance,id){
//        // def encryptionUtils = new EncryptionUtils("jabait");
//        def jobTitle = JobTitle.get(id);
//        if(!jobTitle){
//            return false;
//        } else {
//            jobTitle.save(flush: true);
//            return true;
//        }
//    }
//
//    List loadJobTitle(jobTitleList){
//        return JobTitle.list(jobTitleList);
//    }


    boolean updateStatus(jobStatusId, params){

        EmploymentStatus jobStatus = EmploymentStatus.get(jobStatusId);
        jobStatus.statusCode = params.statusCode;
        jobStatus.statusName = params.statusName;
        if(!jobStatus.save(flush: true)){
            return false;
        }else{
            return true;
        }


    }


    boolean updateAllowance(allowanceId, params){

        AllowanceType allowanceType = AllowanceType.get(allowanceId);
        allowanceType.allowanceName = params.allowanceName;
        allowanceType.allowanceDescription = params.allowanceDescription;
        if(!allowanceType.save(flush: true)){
            return false;
        }else{
            return true;
        }


    }
    
    
}




