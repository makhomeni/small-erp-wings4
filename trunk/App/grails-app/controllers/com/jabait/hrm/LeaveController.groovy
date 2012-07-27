package com.jabait.hrm

import grails.converters.JSON
import com.jabait.hrm.time.LeaveRegister
import com.jabait.security.User
import com.jabait.hrm.time.LeaveWaiver
import com.jabait.hrm.time.AttendanceRegister
//import org.apache.poi.ss.formula.functions.Days360

class LeaveController {
    def leaveService;
    def index() { }

    def leaveManagement(){
         render(view: "/hrm/leave_requisition", model: [titleOfPage: "Leave Management"]);
    }
    def saveLeave(){
        render(view: "/hrm/leave_requisition", model: [titleOfPage: "Leave Management"]);
    }
    def leave(){
        render(view: "leave_home", model: [titleOfPage: "Leave"])
    }
    def leaveRequest(){
        render(view: "leave_request", model: [titleOfPage: "Leave Request"])
    }
    def saveLeaveRequest(){
        flash.message = leaveService.saveLeaveRequest(params);
        redirect(action: 'leaveRequest');
    }
    def waiverList(){
        render(view: 'waiver_list', model: [titleOfPage: "Waiver List"])
    }
    def advanceWaiverList(){
        render(view: 'waiver_list_advance', model: [titleOfPage: "Advance waiver List"])
    }




    def waiverJsonData(){
        if (!params.limit) {
            params.max = 10;
        } else {
            params.max = params.limit;
        }

        def waiverType = Integer.parseInt(params.waiverType);
        List<LeaveWaiver> leaveWaivers;
        if (session?.user?.userCode == "admin"){
            leaveWaivers = LeaveWaiver.findAll("from LeaveWaiver as waiver where waiver.waiverType="+waiverType+"");

        }
        else{
            def userCode = (session?.user?.userCode);
//            leaveRegisters = LeaveRegister.findByEmployees(Employee.findByUserCode(session?.user?.userCode));
//            leaveRegisters = LeaveRegister.executeQuery("select all from LeaveRegister leaveRegister where leaveRegister.employees.userCode='"+userCode+"'")
            leaveWaivers = LeaveWaiver.findAll("from LeaveWaiver as waiver where waiver.waiverEmployee.userCode='"+userCode+"' and waiver.waiverType="+waiverType+"");

        }

        List waiverHistory = new ArrayList();
        Map<String, String> waiverMap = null;

        for (LeaveWaiver leaveWaiver : leaveWaivers){
            waiverMap = new HashMap<String, String>();
            def waiverTakerFirstName = "";
            def waiverTakerMiddleName = "";
            def waiverTakerLastName = "";

            if (leaveWaiver.waiverEmployee.employeeProfile.name.firstName != null){
                waiverTakerFirstName = leaveWaiver.waiverEmployee.employeeProfile.name.firstName;
            }
            if (leaveWaiver.waiverEmployee.employeeProfile.name.middleName != null){
                waiverTakerMiddleName = leaveWaiver.waiverEmployee.employeeProfile.name.middleName;
            }
            if (leaveWaiver.waiverEmployee.employeeProfile.name.surname != null){
                waiverTakerLastName = leaveWaiver.waiverEmployee.employeeProfile.name.surname;
            }

            String waiverTakerFullName  = waiverTakerFirstName.concat(" ".concat(waiverTakerMiddleName)).concat(" ".concat(waiverTakerLastName));

            String reason = leaveWaiver.reason;

            String waiverStatus = leaveWaiver.waiverStatus.toString();

            String waiverDate = leaveWaiver.waiverDate.toString().split(" ")[0];
            String applyDate = leaveWaiver.applicationDate.toString().split(" ")[0];
            String waiverTime = leaveWaiver.waiverTime.toString().split(" ")[1];



            waiverMap.put("id", leaveWaiver.id.toString())
            waiverMap.put("waiverTakerFullName",waiverTakerFullName);
            waiverMap.put("waiverDate", waiverDate);
            waiverMap.put("waiverTime",waiverTime);
            waiverMap.put("applyDate",applyDate);
            waiverMap.put("reason",reason);
            waiverMap.put("waiverStatus",waiverStatus);

            waiverHistory.add(waiverMap);

        }



        /* for pagination */
        int max = 10;
        int totalCount = waiverHistory.size();


        if (waiverHistory.size() < 10) {
            max = waiverHistory.size()
        }

        int start = (params.start != null) ? Integer.parseInt(params.start) : 0;
        int limit = (params.limit != null) ? Integer.parseInt(params.limit) : 10;

        render([waivers : waiverHistory.asList().subList(start, start + limit > waiverHistory.size() ? waiverHistory.size() : start + limit),totalCount:totalCount] as JSON);


    }




    def waiverRequest(){
        render(view: "leave_waiver", model: [titleOfPage: "Waiver Request"])
    }
    
    def advanceWaiverRequest(){

        render(view: "leave_waiver_advance", model: [titleOfPage: "Advance Waiver Request"])

    }
    def saveWaiverRequest(){
        flash.message = leaveService.saveWaiverRequest(params)
        redirect(action: 'waiverRequest')
    }

    def saveAdvanceWaiverRequest(){
        flash.message = leaveService.saveWaiverRequest(params)
        redirect(action: 'advanceWaiverRequest')
    }

    def leaveList(){
        render(view: 'leave_list', model: [titleOfPage: "Leave List"])
    }
    def leaveJsonData(){
        if (!params.limit) {
            params.max = 10;
        } else {
            params.max = params.limit;
        }
        List<LeaveRegister> leaveRegisters;
        if (session?.user?.userCode == "admin"){
            leaveRegisters = LeaveRegister.list();
        }
        else{
            def userCode = (session?.user?.userCode);
//            leaveRegisters = LeaveRegister.findByEmployees(Employee.findByUserCode(session?.user?.userCode));
//            leaveRegisters = LeaveRegister.executeQuery("select all from LeaveRegister leaveRegister where leaveRegister.employees.userCode='"+userCode+"'")
              leaveRegisters = LeaveRegister.findAll("from LeaveRegister as leave where leave.employees.userCode='"+userCode+"'");
            
        }
        
        List leaveHistory = new ArrayList();
        Map<String, String> leaveMap = null;

        for (LeaveRegister leaveRegister : leaveRegisters){
            leaveMap = new HashMap<String, String>();
            def leaveTakerFirstName = "";
            def leaveTakerMiddleName = "";
            def leaveTakerLastName = "";

            if (leaveRegister.employees.employeeProfile.name.firstName != null){
                leaveTakerFirstName = leaveRegister.employees.employeeProfile.name.firstName;
            }
            if (leaveRegister.employees.employeeProfile.name.middleName != null){
                leaveTakerMiddleName = leaveRegister.employees.employeeProfile.name.middleName;
            }
            if (leaveRegister.employees.employeeProfile.name.surname != null){
                leaveTakerLastName = leaveRegister.employees.employeeProfile.name.surname;
            }

            String leaveTakerFullName  = leaveTakerFirstName.concat(" ".concat(leaveTakerMiddleName)).concat(" ".concat(leaveTakerLastName));

            String remark = leaveRegister.remark;

            String leaveStatus = "Pending";
            int dbLeaveStatus = leaveRegister.leaveStatus;
            switch(dbLeaveStatus){
                case 0: leaveStatus = "Disapproved";
                    break;
                case 1: leaveStatus = "Approved";
                    break;
                case 2: leaveStatus = "Pending";
                    break;
            }

            String leaveFromDate = leaveRegister.fromDate.toString().split(" ")[0];
            String leaveToDate = leaveRegister.toDate.toString().split(" ")[0];
            String applyDate = leaveRegister.applyDate.toString().split(" ")[0];

            leaveMap.put("id", leaveRegister.id.toString())
            leaveMap.put("leaveTakerFullName",leaveTakerFullName);
            leaveMap.put("leaveFromDate", leaveFromDate);
            leaveMap.put("leaveToDate",leaveToDate);
            leaveMap.put("applyDate",applyDate);
            leaveMap.put("remark",remark);
            leaveMap.put("leaveStatus",leaveStatus);

            leaveHistory.add(leaveMap);

        }

        /* for pagination */
        int max = 10;
        int totalCount = leaveHistory.size();


        if (leaveHistory.size() < 10) {
            max = leaveHistory.size()
        }

        int start = (params.start != null) ? Integer.parseInt(params.start) : 0;
        int limit = (params.limit != null) ? Integer.parseInt(params.limit) : 10;

        render([leaves : leaveHistory.asList().subList(start, start + limit > leaveHistory.size() ? leaveHistory.size() : start + limit),totalCount:totalCount] as JSON);


    }

    def leaveAction(){
        println("approve leave enter  "+params.userId+" "+params.leaveRegisterId+" "+params.approveReason);
//        User user = User.get(params.userId)
        if (leaveService.leaveAction(params)){
            println("saved");
            render (["success": true] as JSON);
        }
        else{
            render (["success": false] as JSON);
        }
    }
    def isEmployee(){
        if (leaveService.isEmployee(params.userCode)){
            render (["success": true] as JSON);
        }
        else{
            render (["success": false] as JSON);
        }
    }

    def waiverAction(){

        if (leaveService.waiverAction(params)){
            render(["success": true] as JSON);
        }
        else{
            render (["success": false] as JSON);
        }
    }

    def advanceWaiverAction(){

        if (leaveService.advanceWaiverAction(params)){
            render(["success": true] as JSON);
        }
        else{
            render (["success": false] as JSON);
        }
    }

    def getEmployeeAttendanceInformation(){
        String jobDate = "2012-06-05";
//        AttendanceRegister.countByJobDate(jobDate);
        println(AttendanceRegister.executeQuery("select count(*) from AttendanceRegister ar where ar.inTime ='10:30:00' and ar.jobDate = jobDate"))
        AttendanceRegister.executeQuery("select count(*) from AttendanceRegister ar where ar.inTime ='10:30:00' and ar.jobDate = jobDate")
    }
    
    def testWaiverData(){
//        LeaveWaiver leaveWaiver = LeaveWaiver.get(1)
//        println ("waiver time = "+leaveWaiver.waiverTime);
//        AttendanceRegister attendanceRegister = AttendanceRegister.findByAttendant(leaveWaiver.waiverEmployee);
//        println("in time = "+attendanceRegister.inTime);
//
//        GregorianCalendar gc = new GregorianCalendar();
////        gc.setTime(leaveWaiver.waiverTime) - gc.setTime(attendanceRegister.inTime)
//        println("out put time" +(new Date(attendanceRegister.inTime.getTime() - leaveWaiver.waiverTime.getTime())))
//        Date differenceTime =  new Date(attendanceRegister.inTime.getTime() - leaveWaiver.waiverTime.getTime());
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(differenceTime)
//        println("late or advance minute == "+calendar.get(Calendar.HOUR) +":"+ calendar.get(Calendar.MINUTE)+":"+ calendar.get(Calendar.SECOND));
        LeaveWaiver leaveWaiver = LeaveWaiver.get(1);
        String leaveFromDate = leaveWaiver.waiverDate.toString().split(" ")[0] + " "+"23:59:00";
        println(leaveFromDate)
    }

}
