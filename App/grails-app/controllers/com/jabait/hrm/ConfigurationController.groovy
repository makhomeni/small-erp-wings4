package com.jabait.hrm

import grails.converters.JSON
import com.jabait.hrm.time.Leave
import com.jabait.security.User
import com.jabait.hrm.payroll.AllowanceType
import com.jabait.hrm.time.LateThreshold

class ConfigurationController {

    //def departmentService;
    def configurationService;

    def index() {
        render(view: "index")
    }


    def createOrganization(){
//        def organizations = configurationService.organizationList();
        def organizationList = configurationService.organizationList()
        render(view: "create_organization", model: [type:"Create Organization"]);
    }

    def saveOrganization(){
        println(""+params.organizationName);
        /* if (!configurationService.saveOrganization(params)){
            flash.message = "Organization save failed";
            redirect(action: 'createOrganization')
        }
        else{
            flash.message = "Organization save successfully";
            redirect(action: 'createOrganization')
        }*/
        flash.message = configurationService.saveOrganization(params);
        redirect(action: 'createOrganization')

    }

    def deleteOrganization(){

        def organizationInstance = Organization.get(params.id)
        configurationService.deleteOrganization(organizationInstance, params.id);
        flash.message = "organization.delete.message"
        //flash.args = [employeInstance.userCode]
        flash.default = "Organization deleted";
        /*params.max = Math.min(params.max ? params.int('max') : 10, 100);
        render(view: "user_list", model:  [userInstanceList: userDetailsService.loadUsers(params), userInstanceTotal: User.count()]);*/
        redirect(action: "organizationList");
    }


    def showOrganizationDetails() {

        Organization organization = Organization.get(params.id);
        render(view: "show_organization_details", model: [organization: organization]);

    }

    def organizationConfigHome(){
        render(view: "organization_config_home", model: [type:"Organization"])
    }

    def organizationList(){
        render(view: "organization_list", model: [type:"Organization"])
    }


    def organizationJsonData(){
        if (!params.limit){
            params.max = 10;
        }else{
            params.max = params.limit;
        }
        render( [ organizations: Organization.list(offset: params.start, max: params.max), totalCount: Organization.count() ] as JSON );

    }

    def createDepartment(){
        render(view: "create_department", model: [type: "Create Department"])
    }

    def saveDepartment(){

        println("  "+params.departmentName+" "+params.organization);
        if (!configurationService.saveDepartment(params)){
            flash.message = "Department save failed";
            redirect(action: 'createDepartment')
        }
        else{
            flash.message = "Department save successfully";
            redirect(action: 'createDepartment')
        }

    }


    def deleteDepartment(){

        def departmentInstance = Department.get(params.id)
        configurationService.deleteDepartment(departmentInstance, params.id);
        flash.message = "department.delete.message"
        //flash.args = [employeInstance.userCode]
        flash.default = "Department deleted";
        /*params.max = Math.min(params.max ? params.int('max') : 10, 100);
        render(view: "user_list", model:  [userInstanceList: userDetailsService.loadUsers(params), userInstanceTotal: User.count()]);*/
        redirect(action: "departmentList");
        return true;
    }

    def showDepartmentDetails() {

         Department department = Department.get(params.id);
         render(view: "show_department_details", model: [department: department]);

    }

    def departmentList(){
        render(view: "department_list", model: [type: "Department List"])
    }

    def departmentJsonData(){
        if (!params.limit){
            params.max = 10;
        }else{
            params.max = params.limit;
        }
        render( [ departments: Department.list(offset: params.start, max: params.max), totalCount: Department.count() ] as JSON );

    }


    def createSection(){
        render(view: "create_section", model: [type: "Create Section"])
    }


    def deleteSection(){

        def sectionInstance = Department.get(params.id)
        configurationService.deleteSection(sectionInstance, params.id);
        flash.message = "section.delete.message"
        //flash.args = [employeInstance.userCode]
        flash.default = "Section deleted";
        /*params.max = Math.min(params.max ? params.int('max') : 10, 100);
        render(view: "user_list", model:  [userInstanceList: userDetailsService.loadUsers(params), userInstanceTotal: User.count()]);*/
        redirect(action: "sectionList");
        return true;
    }

    def showSectionDetails() {

            Department department = Department.get(params.id);
            Organization org = Organization.get(params.id);

            render(view: "show_section_details", model: [departmentNames: department.departmentName, parentDepartments: department.parentDepartment,organizations: org.organizationName ])


       // render(view: "show_section_details")


    }






    def sectionList(){

        render(view: "section_list", model:[type: "Section"])

    }

    def saveSection(){
        if (!configurationService.saveSection(params)){
            flash.message = "Section save failed";
            redirect(action: 'createSection')
        }
        else{
            flash.message = "Section save successfully";
            redirect(action: 'createSection')
        }

    }

    def sectionJsonData(){
        if (!params.limit){
            params.max = 10;
        }else{
            params.max = params.limit;
        }
        render( [ section: Department.list(offset: params.start, max: params.max), totalCount: Department.count() ] as JSON );

    }





    def createLeave(){
//        def leaves = configurationService.leaveList();
        render(view: "create_leave", model: [type: "Create Leave"])
    }

    def saveLeave(){
        if (!configurationService.saveLeave(params)){
            flash.message = "Leave save failed";
            redirect(action: 'createLeave')
        }
        else{
            flash.message = "Leave save successfully";
            redirect(action: 'createLeave')
        }

    }



    def deleteLeave(){

        def leaveInstance = Leave.get(params.id)
        configurationService.deleteLeave(leaveInstance, params.id);
        flash.message = "leave.delete.message"
        //flash.args = [employeInstance.userCode]
        flash.default = "Leave deleted";
        /*params.max = Math.min(params.max ? params.int('max') : 10, 100);
        render(view: "user_list", model:  [userInstanceList: userDetailsService.loadUsers(params), userInstanceTotal: User.count()]);*/
        redirect(action: "leaveList");
        return true;
    }



    def leaveList(){

        render(view: "leave_list", model: [type: "Leave"])

    }

    def leaveJsonData(){
        if (!params.limit){
            params.max = 10;
        }else{
            params.max = params.limit;
        }
        render( [ leaves: Leave.list(offset: params.start, max: params.max), totalCount: Leave.count() ] as JSON );

    }



    def createJobSpec(){
//        def jobSpec = configurationService.jobSpecList();
        render(view: "create_job_spec", model: [type: "Create Job Spec"])
    }


    def deleteJobSpec(){

        def jobSpecInstance = JobSpec.get(params.id)
        configurationService.deleteJobSpec(jobSpecInstance, params.id);
        flash.message = "jobSpec.delete.message"
        //flash.args = [employeInstance.userCode]
        flash.default = "Job Spec deleted";
        /*params.max = Math.min(params.max ? params.int('max') : 10, 100);
        render(view: "user_list", model:  [userInstanceList: userDetailsService.loadUsers(params), userInstanceTotal: User.count()]);*/
        redirect(action: "jobSpecList");
        return true;
    }


    def jobConfigHome(){
        render(view: "job_config_home", model: [type:"Job Config Home"])
    }

    def jobSpecList(){

        render(view: "job_spec_list", model: [type: "Job Spec"])

    }

    def showJobSpecDetails() {

            JobSpec jobSpec = JobSpec.get(params.id)


        render(view: "show_jobSpec_details", model: [jobSpecNames: jobSpec.jobSpecName, jobSpecDescs: jobSpec.jobSpecDesc, jobSpecDuties: jobSpec.jobSpecDuties ])
        }

       // render(view: "show_jobSpec_details")




    def saveSpec(){
        if (!configurationService.saveSpec(params)){
            flash.message = "Spec save failed";
            redirect(action: 'createJobSpec')
        }
        else{
            flash.message = "Spec save successfully";
            redirect(action: 'createJobSpec')
        }

    }


    def jobSpecJsonData(){
        if (!params.limit){
            params.max = 10;
        }else{
            params.max = params.limit;
        }
        render( [ jobSpec: JobSpec.list(offset: params.start, max: params.max), totalCount: JobSpec.count() ] as JSON );

    }


    def createJobTitle(){
//        def jobTitles = configurationService.jobTitleList();
        render(view: "create_job_title", model: [type: "Create Job Title"])
    }


    def deleteJobTitle(){

        def jobTitleInstance = JobTitle.get(params.id)
        configurationService.deleteJobTitle(jobTitleInstance, params.id);
        flash.message = "jobTitle.delete.message"
        //flash.args = [employeInstance.userCode]
        flash.default = "Job Title deleted";
        /*params.max = Math.min(params.max ? params.int('max') : 10, 100);
        render(view: "user_list", model:  [userInstanceList: userDetailsService.loadUsers(params), userInstanceTotal: User.count()]);*/
        redirect(action: "jobTitleList");
        return true;
    }


    def jobTitleList(){

        render(view: "job_title_list", model: [type: "Job Title"])

    }

    def saveTitle(){
        if (!configurationService.saveTitle(params)){
            flash.message = "Title save failed";
            redirect(action: 'createJobTitle')
        }
        else{
            flash.message = "Title save successfully";
            redirect(action: 'createJobTitle')
        }

    }


    def jobTitleJsonData(){
        if (!params.limit){
            params.max = 10;
        }else{
            params.max = params.limit;
        }
        render( [ jobTitles: JobTitle.list(offset: params.start, max: params.max), totalCount: JobTitle.count() ] as JSON );

    }



    def createRoleList(){
//        def role = configurationService.roleList();
        render(view: "create_job_role", model: [title:"Create Role List"])
    }

    def saveRole(){
        if (!configurationService.saveRole(params)){
            flash.message = "Role save failed";
            redirect(action: 'createRoleList')
        }
        else{
            flash.message = "Role save successfully";
            redirect(action: 'createRoleList')
        }

    }

    def deleteJobRole(){

        def jobRoleInstance = JobRole.get(params.id)
        configurationService.deleteJobRole(jobRoleInstance, params.id);
        flash.message = "jobRole.delete.message"
        //flash.args = [employeInstance.userCode]
        flash.default = "Job Role deleted";
        /*params.max = Math.min(params.max ? params.int('max') : 10, 100);
        render(view: "user_list", model:  [userInstanceList: userDetailsService.loadUsers(params), userInstanceTotal: User.count()]);*/
        redirect(action: "jobRoleList");
        return true;
    }


    def jobRoleList(){
        render(view: "job_role_list", model: [type:"Role List"])

    }



    def jobRoleJsonData(){
        if (!params.limit){
            params.max = 10;
        }else{
            params.max = params.limit;
        }
        render( [ jobRoles: JobRole.list(offset: params.start, max: params.max), totalCount: JobRole.count() ] as JSON );

    }




    def createEmploymentStatus(){
//        def employmentStatus = configurationService.employeeStatusList();
        render(view: "create_employment_status", model: [type: "Create Employment Status"])
    }


    def saveEmploymentStatus(){
        if (!configurationService.saveEmploymentStatus(params)){
            flash.message = "EmploymentStatus save failed";
            redirect(action: 'createEmploymentStatus')
        }
        else{
            flash.message = "EmploymentStatus save successfully";
            redirect(action: 'createEmploymentStatus')
        }

    }



    def deleteEmployeeStatus(){

        def employeeStatusInstance = EmploymentStatus.get(params.id)
        configurationService.deleteEmployeeStatus(employeeStatusInstance, params.id);
        flash.message = "employeeStatus.delete.message"
        //flash.args = [employeInstance.userCode]
        flash.default = "Employee Status deleted";
        /*params.max = Math.min(params.max ? params.int('max') : 10, 100);
        render(view: "user_list", model:  [userInstanceList: userDetailsService.loadUsers(params), userInstanceTotal: User.count()]);*/
        redirect(action: "employmentStatusList");
        return true;
    }

    def employmentStatusList(){

        render(view: "employment_status_list", model: [type: "Employment Status"])

    }



    def employmentStatusJsonData(){
        if (!params.limit){
            params.max = 10;
        }else{
            params.max = params.limit;
        }
        render( [ status: EmploymentStatus.list(offset: params.start, max: params.max), totalCount: EmploymentStatus.count() ] as JSON );

    }


    def attendanceConfigHome(){
        render(view: "attendance_config_home", model: [title: "Atttendance Configuration"])
    }

    def showEmployeeStatus() {

        EmploymentStatus employee = EmploymentStatus.get(params.id);

        render(view: "show_employe_details", model: [statusCodes:employee.statusCode,statusNames: employee.statusName ])

    }
    def showTitle() {

        JobTitle title = JobTitle.get(params.id);

        render(view: "show_jobtitle_details", model: [jobTitleCodes:title.jobTitleCode,jobTitleNames: title.jobTitleName,jobTitleDescriptions: title.jobTitleDescription ])

    }

    def showRole() {

        JobRole role = JobRole.get(params.id);

        render(view: "show_role_details", model: [roleNames:role.roleName,descriptions: role.description])

    }


    def showLeaveDetails() {

        Leave leave = Leave.get(params.id);

        render(view: "show_leave", model: [leaveTypes:leave.leaveType,leaveDurations: leave.leaveDuration,leaveNotess: leave.leaveNotes])

    }


    def editJobRole(){
        def jobRoleInstance = JobRole.get(params.id);
        render(view: "job_role_update", model: [jobRoleInstance:jobRoleInstance, type: "Edit JobRole"]);
    }

    def updateJobRole(){
        if (configurationService.updateJobRole(params.id, params)){
            flash.message = "Job Role updated successfully";
        }
        else{
            flash.message = "Job Role update failed";
        }
//        render(view: "user_group_update")
        redirect(action: 'editJobRole', id: params.id);
    }


    def editLeave(){
        def leaveInstance = Leave.get(params.id);
        render(view: "leave_update", model: [leaveInstance: leaveInstance, type: "Edit Leave"]);
    }


    def updateLeave(){
        if(!configurationService.updateLeave(params.leaveId, params)){
            flash.message = "Leave update failed";
        }
        else{
            flash.message = "Leave updated successfully";
        }
        redirect(action: "editLeave", id: params.leaveId);

    }

    def payrollConfigHome(){
        render(view: "payroll_config_home", model: [type:"Payroll"])
    }

  def allowanceTypeList(){
       render(view: "allowance_list", model: [type:"Allowance"])
   }

    def deleteAllowanceType(){

        def allowanceInstance = AllowanceType.get(params.id)
        configurationService.deleteAllowanceType(allowanceInstance, params.id);
        flash.message = "allowance.delete.message"
        flash.default = "Allowance deleted";
        redirect(action: "allowanceTypeList");
        return true;
    }

    def saveAllowanceType(){
        if (!configurationService.saveAllowanceType(params)){
            println ("Failed");
            flash.message = "Allowance save failed";
            redirect(action: 'createAllowanceType')
        }
        else{
            println ("Sucess");
            flash.message = "Allowance save successfully";
            redirect(action: 'createAllowanceType')
        }

    }

    def createAllowanceType(){

        render(view: "create_allowance", model: [type: "Create Allowance Type"])
    }

    def allowanceJsonData(){
        if(!params.limit){
            params.max = 10;
        }
        else {
            params.max = params.limit;
            render([allowance : AllowanceType.list(offset: params.start,max: params.max),totalCount:AllowanceType.count()] as JSON);

        }

    }

    def editTitle(){
        def jobTitleInstance = JobTitle.get(params.id);
        render(view: "job_title_update", model: [jobTitleInstance:jobTitleInstance, type: "Edit Job Title"]);
    }

    def updateJobTitle(){
        flash.message = configurationService.updateJobTitle(params);
        redirect(action: 'editTitle', id: params.jobTitleId);
    }

    def leaveEncashment(){
        render(view: "leave_encashment_assignment", model: [title: "Leave Encashment"])
    }

    def leaveEncashmentAssignment(){
        flash.message = configurationService.leaveEncashmentAssignment(params);
        redirect(action: "leaveEncashment");
    }

    def editJobStatus(){
        def jobStatusInstance = EmploymentStatus.get(params.id);
        render(view: "job_status_update", model: [jobStatusInstance:jobStatusInstance, type: "Edit JobStatus"]);
    }

    def updateJobStatus(){
        if (configurationService.updateJobRole(params.id, params)){
            flash.message = "Job Status updated successfully";
        }
        else{
            flash.message = "Job Status update failed";
        }
//        render(view: "user_group_update")
        redirect(action: 'editJobStatus', id: params.id);
    }


    def editAllowance(){
        def allowanceInstance = AllowanceType.get(params.id);
        render(view: "allowance_update", model: [allowanceInstance:allowanceInstance, type: "Edit Allowance"]);
    }

    def updateAllowance(){
        if (configurationService.updateAllowance(params.id, params)){
            flash.message = "Allowance Type updated successfully";
        }
        else{
            flash.message = "Allowance Type update failed";
        }
//        render(view: "user_group_update")
        redirect(action: 'editAllowance', id: params.id);
    }


    def editJobSpec(){
        def jobSpecInstance = JobSpec.get(params.id);
        render(view: "job_spec_update", model: [jobSpecInstance:jobSpecInstance, type: "Edit Job Spec"]);
    }

    def updateJobSpec(){
        flash.message = configurationService.updateJobSpec(params);
        redirect(action: 'editJobSpec', id: params.jobSpecId);
    }




}
