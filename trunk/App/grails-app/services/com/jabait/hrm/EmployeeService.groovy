package com.jabait.hrm

import com.jabait.security.Name
import com.jabait.security.Passport
import com.jabait.security.Identification
import com.jabait.coresecurity.util.EncryptionUtils
import com.jabait.security.User

import com.jabait.security.Phone
import com.jabait.security.Email
import java.text.SimpleDateFormat
import java.text.ParseException
import grails.converters.JSON
import com.jabait.hrm.payroll.AllowanceType
import com.jabait.hrm.payroll.Allowance
import com.jabait.hrm.payroll.PayRegister
import com.jabait.hrm.time.AttendanceRegister
import com.jabait.hrm.time.FingerPrintDetails

class EmployeeService {

    static transactional = true;

    def serviceMethod() {

    }
    def saveEmployee(params){

        println("enter  "+params.username+" password  "+params.password+" date of hire = "+params.dateOfHire);
        def username = params.username;
        def password = params.password;
        def firstName = params.firstName;
        def middleName = params.middleName;
        def lastName = params.lastName;
        def organizationId = params.organizationId;
        def departmentId = params.departmentId;
        def sectionId = params.sectionId;
        def designationId = params.designationId;
        def region = params.region;
        def maritalStatus = params.maritalStatus;
        def gender = params.gender;
        def nationalID = params.nationalId;
        def passportNo = params.passportNo;
        def salary = params.salary;
        def providentFund = params.providentFund;

        Name name = new Name(firstName: firstName, middleName: middleName, surname: lastName, nickname: lastName);

        def dateOfBirth = new Date().parse("dd/MM/yyyy H:m:s", params.dateOfBirth)
        def issueDate = new Date().parse("dd/MM/yyyy H:m:s", params.issueDate)
        def joinDate = new Date().parse("dd/MM/yyyy H:m:s", params.dateOfJoin)
        def bloodGroup = params.bloodGroup;

        def issueFrom = params.issueFrom;

        def expireDate = new Date().parse("dd/MM/yyyy H:m:s", params.expireDate)
        def hireDate = new Date().parse("dd/MM/yyyy H:m:s", params.dateOfHire)

        println("formated date = "+issueDate+" issueFrom = "+issueFrom+" expireDate = "+expireDate+" "+bloodGroup)


        Passport passport = new Passport(passportNumber: passportNo, issueDate: issueDate, issueFrom: issueFrom, expiredDate: expireDate);
//        DrivingLicense drivingLicense = new DrivingLicense()
        Identification identification = new Identification(nationalIdentificationNumber: nationalID, passport: passport);

        EncryptionUtils encUtil = new EncryptionUtils("jabait");
        def encryptedPassword = encUtil.encrypt(password);


        EmployeeProfile employeeProfile = new EmployeeProfile(
                name: name,
                employeeGender: gender,
                employeeMaritalStatus: maritalStatus,
                employeeBirthDate: dateOfBirth,
                hireDate: hireDate,
                joiningDate:joinDate,
                department: Department.get(departmentId),
                organization:Organization.get(organizationId),
                section:Department.get(sectionId),
                bloodGroup: bloodGroup,
                jobTitle: JobTitle.get(designationId),
                salary: salary,
                region: region,
                providentFundEnable: providentFund
        )

//        employeeProfile.organization =  Organization.get(organizationId);
//        employeeProfile.section = Department.get(sectionId);
//        employeeProfile.bloodGroup = bloodGroup;


        User employee = new Employee(
                userCode: username,
                password: encryptedPassword,
                employeeProfile: employeeProfile,
                active: true
        )
        if (!employee.save(flush: true)){
            println("save failed")
            return 0;
        }else{
            println("saved successfully"+employeeProfile.id);
            return employeeProfile.id;
        }



    }


    def saveEmployeeContactInfo(params){

        def homeTelephone = params.homeTelephone;
        def mobile = params.mobile;
        def workTelephone = params.workTelephone;
        def workEmail = params.workEmail;
        def generalEmail = params.generalEmail;
        def profileId = params.profileId;

        println(homeTelephone+" "+mobile+" "+workTelephone+" "+workEmail+" "+generalEmail+" "+profileId)

        EmployeeProfile employeeProfile = EmployeeProfile.get(profileId);
        if(employeeProfile){
            println("profile exist");
            Phone homePhone =  new Phone(number: homeTelephone, type: "Home Telephone");
            //homePhone.save(flush: true);
            Phone mobilePhone =  new Phone(number: mobile, type: "Mobile phone");
            //mobilePhone.save(flush: true)
            Phone workPhone =  new Phone(number: workTelephone, type: "work telephone");
            //workPhone.save(flush: true)
            Email employeeWorkEmail = new Email(address: workEmail, type: "Work email");
            //employeeWorkEmail.save(flush: true)
            Email employeeGeneralEmail = new Email(address: generalEmail, type: "General email");
            //employeeGeneralEmail.save(flush: true);

            employeeProfile.homePhone = homePhone;
            employeeProfile.mobile = mobilePhone;
            employeeProfile.workPhone =  workPhone;
            employeeProfile.workEmail = employeeWorkEmail;
            employeeProfile.generalEmail = employeeGeneralEmail;
            println "Employee Name " + employeeProfile.name.firstName
            if(employeeProfile.hasErrors()){
                println "EmployeeProfile Errors " + employeeProfile.errors
            }   else {

                if(employeeProfile.save()){
                    println("saved")
                    return true;
                }
                else{
                    println("not Saved");
                    return false;
                }

            }


        }
        else{
            println("profile not exist");
        }

    }

    def saveEmployeeAllowance(params){

        def allowanceType = AllowanceType.get(params.allowanceTypeId);
        def allowanceAmount = Double.parseDouble(params.allowanceAmount);
        def salaryAmount = params.salaryAmount;
        EmployeeProfile employeeProfile = EmployeeProfile.get(params.profileId);

        Allowance allowance = new Allowance();
        allowance.allowanceAmount = allowanceAmount;
        allowance.allowanceType = allowanceType;

        if(allowance.save()){
            println("saved"+employeeProfile.owner.id);
            employeeProfile.owner.addToAllowances(allowance).save(flush: true);

            return true;
        }
        else{

            println("not saved"+employeeProfile.owner.id);
            return false;


        }


    }


    def saveEmployeeUser(userCode, password){
        User.withTransaction {
            userTransaction->
            try{
                User employee = new Employee(
                        userCode: userCode,
                        password: password
                )
                employee.save(flush: true);
                return employee;
            } catch(Exception ex){
                return null;
            }
        }

    }

    def deleteEmployee(params){
        def employee = Employee.get(params.id);

        if(!employee){
            return "Employee delete failed";
        }else{
            if(employee!=null){
                try{
                    def temporaryAllowance = [];


                    //delete associated allowances
                    employee.allowances.each {
                        temporaryAllowance << it;
                    }
                    temporaryAllowance.each { allowance->
                        employee.removeFromAllowances(allowance);
                    }


                    //remove associated education
                    def temporaryEducation = [];
                    employee.educations.each {
                        temporaryEducation << it;
                    }
                    temporaryEducation.each {education->
                        employee.removeFromEducations(education);
                    }

                    //remove associated finger print
//                        def temporaryFingerPrint = [];
                    FingerPrintDetails fingerPrintDetails =  FingerPrintDetails.findByEmployee(employee);

                    println(" fingerprint "+fingerPrintDetails.id)
                    try{
                        println("in try fingerprint details");
                        fingerPrintDetails.delete();
                        println("delete successfully");
                    }catch(Exception e){
                        println ("Fingerprintdetails delete failed" + fingerPrintDetails.id);
                    }

                    //delete employee profile
                    try{
                        employee.employeeProfile.delete();
                    }catch(Exception ex){
                        println("profile delete falied");
                    }



                    //delete employee
                    employee.delete(flush: true)
                    return "Employee deleted successfully";
                }
                catch (Exception ex){
                    return "Employee delete failed";
                }
            } else{
                return "Employee delete failed";
            }
        }

    }

    private void deleteEmployeeRelatedRecords(Employee employee) {
        def register = PayRegister.findAllByEmployee(employee);
        register.each {
            try {
                it.delete(flush: true);

            } catch (Exception e) {

            }
        }
        def attnRegister = AttendanceRegister.findAllByAttendant(employee);
        attnRegister.each {
            try {
                it.delete(flush: true);

            } catch (Exception e) {

            }
        }
        def fingerDetails = FingerPrintDetails.findAllByEmployee(employee);
        fingerDetails.each {
            try {
                it.delete(flush: true);

            } catch (Exception e) {

            }
        }
    }

    def updateName(params){

        def employeeProfile = EmployeeProfile.get(params.profileId)
        println(employeeProfile.name.id);
        def name = Name.get(employeeProfile.name.id);
        def firstName = params.firstName;
        def middleName  = params.middleName;
        def surName = params.surname;

        name.firstName = firstName;
        name.middleName = middleName;
        name.surname = surName;

        if(name.save()){
            return name;
        }else{
            return name;
        }
    }
    def updateMiscellaneous(params){

        def employeeProfile = EmployeeProfile.get(params.profileId);
//        new Date().parse("yyyy-MM-DD H:m:s", params.employeeBirthDate)

        def employeeBirthDate = parseDate(params.employeeBirthDate, "yyyy-MM-dd H:m:s");
//        new Date().parse("yyyy-MM-DD H:m:s", params.hireDate)
        def hireDate = parseDate(params.hireDate, "yyyy-MM-dd H:m:s");
//        new Date().parse("yyyy-MM-DD H:m:s", params.joiningDate)
        def joiningDate = parseDate(params.joiningDate, "yyyy-MM-dd H:m:s");
        println("join date = ");
        println(joiningDate.toString()+" "+employeeBirthDate.toString()+ " "+hireDate.toString())
        println("--join date"+params.bloodGroup);

        employeeProfile.employeeBirthDate = employeeBirthDate;
        employeeProfile.hireDate = hireDate;
        employeeProfile.joiningDate = joiningDate;
        employeeProfile.bloodGroup = params.bloodGroup;


        employeeProfile.employeeGender = params.employeeGender;
        employeeProfile.employeeMaritalStatus = params.employeeMaritalStatus;

        if(employeeProfile.save()){
            return employeeProfile;
        }
        else{
            return employeeProfile;
        }

    }

    def  updateContactInfo(params){

        def employeeProfile = EmployeeProfile.get(params.profileId);

        //if id exist then it has to be updated otherwise create

        Phone homePhone;
        if(params.homePhoneId){
            homePhone = Phone.get(params.homePhoneId);
            homePhone.number =  params.homePhone;
        }else{
            println(" params.homePhone = "+params.homePhone)
            homePhone = new Phone(number: params.homePhone, type: "home phone")
        }

        Phone mobile;
        if(params.mobileId){
            mobile = Phone.get(params.mobileId);
            mobile.number =  params.mobile;
        }else{
            println(" params.mobile = "+params.mobile)
            mobile = new Phone(number: params.mobile, type: "mobile phone")
        }

        Phone workPhone;
        if(params.workPhoneId){
            workPhone = Phone.get(params.workPhoneId);
            workPhone.number = params.workPhone;
        }else{
            println(" params.workPhone = "+params.workPhone)
            workPhone = new Phone(number: params.workPhone, type: "work phone")
        }

        Email workEmail;
        if(params.workEmailId){
            workEmail = Email.get(params.workEmailId);
            workEmail.address =  params.workEmail;
        }else{
            println(" params.workEmail = "+params.workEmail)
            workEmail = new Email(address: params.workEmail, type: "work email")
        }

        Email generalEmail;
        if(params.generalEmailId){
            generalEmail = Email.get(params.generalEmailId);
            generalEmail.address = params.generalEmail;
        }else{
            println(" params.generalEmail = "+params.generalEmail)
            generalEmail = new Email(address: params.generalEmail, type: "work email")
        }

        if(homePhone){
            employeeProfile.homePhone = homePhone;
        }
        if(mobile){
            employeeProfile.mobile = mobile;
        }
        if(workPhone){
            employeeProfile.workPhone =  workPhone;
        }
        if(workEmail){
            employeeProfile.workEmail =  workEmail;
        }
        if(generalEmail){
            employeeProfile.generalEmail = generalEmail;
        }

        if(employeeProfile.hasErrors()){
            println "EmployeeProfile Errors " + employeeProfile.errors
        }   else {

            if(employeeProfile.save()){
                println("saved")
                return ([result: employeeProfile,homePhone: homePhone, mobile: mobile, workPhone: workPhone, workEmail: workEmail, generalEmail: generalEmail] as JSON);
            }
            else{
                println("not Saved");
                return employeeProfile;
            }

        }
    }

    def updateOrgInfo(params){

        def region = params.region;
        def salaryString = params.salary.toString().replace(",", "");

        def salary = Double.parseDouble(salaryString?:"0.0");

        def providentFund = false;
        if(params.providentFund.equals("true")){
            providentFund = true;
        }
        def tax = false;
        if(params.tax.equals("true")){
            tax = true;
        }

        println(salary+" provident fund = "+providentFund);
        def employeeProfile = EmployeeProfile.get(params.profileId);
        def designationId = params.designationId;
        JobTitle jobTitle = JobTitle.get(designationId);
        println(designationId)
        def organizationId = params.organizationId;
        if(organizationId){
            Organization organization = Organization.get(organizationId)
            employeeProfile.organization = organization
        }
        def departmentId = params.departmentId;
        if(departmentId){
            Department department = Department.get(departmentId)
            employeeProfile.department = department;
        }

        employeeProfile.region = region;
        employeeProfile.salary = salary;
        employeeProfile.jobTitle = jobTitle;
        employeeProfile.providentFundEnable = providentFund;
        employeeProfile.tax = tax;
        if(employeeProfile.save()){
            return ([result: employeeProfile,jobTitle: jobTitle, organization: employeeProfile.organization,
                    department: employeeProfile.department, providentFund: employeeProfile.providentFundEnable.toString(),
                    tax: employeeProfile.tax.toString()] as JSON);
        }
        else{
            return employeeProfile;
        }


    }

    private Date parseDate(String date, String format)
    {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try{
            return formatter.parse(date);
        }catch (ParseException pe){
            pe.printStackTrace();
            System.out.println("Exception occur");
        }

    }

    def getEmployee(id){
        return Employee.get(id);
    }
}
