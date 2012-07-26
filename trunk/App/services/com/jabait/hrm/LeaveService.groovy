package com.jabait.hrm

import com.jabait.hrm.time.Leave
import java.text.SimpleDateFormat
import java.text.ParseException
import com.jabait.hrm.time.LeaveRegister
import com.jabait.security.User
import com.jabait.hrm.time.LeaveWaiver
import grails.converters.JSON
import com.jabait.hrm.calendar.CalendarEvent
import com.jabait.hrm.calendar.CalendarType
import com.jabait.hrm.time.AttendanceRegister

class LeaveService {

    def serviceMethod() {

    }
    def saveLeaveRequest(params){
        println(params.leaveType);
        Employee employee;
        if(params.employeeId){
            employee = Employee.findByUserCode(params.employeeId);
        }else{
            println("loginUserId = "+params.loginUserId);
            employee = Employee.findByUserCode(params.loginUserId);
        }

        def leaveType =  Leave.findByLeaveType(params.leaveType)
        println(params.leaveFrom + " leave to = "+params.leaveTo)
        def leaveFromDate = parseDate(params.leaveFrom, "yyyy-MM-dd");
        def leaveToDate = parseDate(params.leaveTo, "yyyy-MM-dd");

        def leaveDays = Integer.parseInt(params.leaveTo.toString()?.split("-")[2]) - Integer.parseInt(params.leaveFrom.toString()?.split("-")[2]) + 1;

        println(leaveDays);

        println(leaveFromDate.toString() + " leaveToDate = "+leaveToDate.toString())
        def remark = params.remark;

        LeaveRegister leaveRegister = new LeaveRegister(fromDate: leaveFromDate,
                                                        toDate: leaveToDate,
                                                        leaveType: leaveType,
                                                        remark: remark,
                                                        employees: employee,
                                                        leaveDays: leaveDays)

        if(leaveRegister.save(flush: true)){

            return "Leave request save successfully";
        }
        else{
            return "Leave request save failed";
        }

    }
    def saveWaiverRequest(params){
        Employee waiverEmployee;
        if(params.employeeId){
            waiverEmployee = Employee.findByUserCode(params.employeeId);
        }else{
            println("loginUserId = "+params.loginUserId);
            waiverEmployee = Employee.findByUserCode(params.loginUserId);
        }

        def waiverType = Integer.parseInt(params.waiverType)

        def waiverTimeHour = params.waiverTimeHour;
        def waiverTimeMin = params.waiverTimeMin;
        def waiverTime = parseDate(waiverTimeHour+":"+waiverTimeMin+":"+"00", "H:m:s");
        println(waiverTimeHour+" "+waiverTimeMin)
        def waiverDate = parseDate(params.waiverDate, "yyyy-MM-dd");
        println("formated date = "+waiverDate.toString());
        def reason = params.reason;

        LeaveWaiver leaveWaiver = new LeaveWaiver(waiverEmployee: waiverEmployee,
                                                  waiverDate: waiverDate,
                                                  reason: reason,
                                                  waiverTime: waiverTime,
                                                  waiverType: waiverType);
        if(leaveWaiver.save(flush: true)){
            return "Leave waiver request save successfully";
        }
        else {
            return "Leave waiver request save failed";
        }
    }



    def leaveAction(params){
//        +" "++" "+params.approveReason
        User user = User.get(params.userId);
        LeaveRegister leaveRegister = LeaveRegister.get(params.leaveRegisterId);
        println(params.leaveStatus);
        def leaveStatus =  Integer.parseInt(params.leaveStatus) ;
        leaveRegister.leaveStatus =  leaveStatus;
        leaveRegister.approvedDisapprovedReason = params.reason;
        Employee employee = leaveRegister.employees;
        if(leaveStatus == 1){
            println("in approve section")
            CalendarEvent calendarEvent = new CalendarEvent();
            calendarEvent.calendarType = CalendarType.findByName("leave");
            calendarEvent.author = employee;

            Date endTime = parseDate(leaveRegister.toDate.toString().split(" ")[0] + " "+"23:59:00","yyyy-MM-dd H:m:s");
            Date startTime = parseDate(leaveRegister.fromDate.toString().split(" ")[0] + " "+"00:00:00", "yyyy-MM-dd H:m:s");

            calendarEvent.startTime = startTime;
            calendarEvent.endTime = endTime;
            calendarEvent.subject = "leave";
            calendarEvent.description = "leave is apporoved";
            calendarEvent.repeatType = "no";
            calendarEvent.save();
        }

//        calendarEvent.updateDate = le

        if(leaveRegister.save()){
//            calendarEvent.save();
            return true;
        }
        else{
            return false;
        }
    }
    
    def waiverAction(params){
        
        User user = User.get(params.userId);
        LeaveWaiver leaveWaiver = LeaveWaiver.get(params.leaveWaiverId)
        def weaverStatus = Integer.parseInt(params.leaveStatus);
        leaveWaiver.waiverStatus = weaverStatus;
        leaveWaiver.approvedDisapprovedReason = params.reason;


        if(weaverStatus == 1){

            CalendarEvent calendarEvent = new CalendarEvent();
            calendarEvent.calendarType = CalendarType.findByName("waiver");
            calendarEvent.author = leaveWaiver.waiverEmployee;

            Date endTime = parseDate(leaveWaiver.waiverDate.toString().split(" ")[0] + " "+"23:59:00","yyyy-MM-dd H:m:s");
            Date startTime = parseDate(leaveWaiver.waiverDate.toString().split(" ")[0] + " "+"00:00:00", "yyyy-MM-dd H:m:s");

            calendarEvent.startTime = startTime;
            calendarEvent.endTime = endTime;
            calendarEvent.subject = "waiver";
            calendarEvent.description = "Waiver is apporoved";
            calendarEvent.repeatType = "no";
            calendarEvent.save();
        }


        if(user){
            leaveWaiver.approvedBy = user;
        }

        AttendanceRegister attendanceRegister = AttendanceRegister.findByAttendantAndJobDate(leaveWaiver.waiverEmployee,leaveWaiver.waiverDate);
        if(attendanceRegister){

            attendanceRegister.remark = "present on request";
            attendanceRegister.save();
        }

        if(leaveWaiver.save(flush: true)){
            println("saved in service");
            return true;
        }
        else{
            return false;
        }
    }

    def advanceWaiverAction(params){

        User user = User.get(params.userId);
        LeaveWaiver leaveWaiver = LeaveWaiver.get(params.leaveWaiverId)
        def weaverStatus = Integer.parseInt(params.leaveStatus);
        leaveWaiver.waiverStatus = weaverStatus;
        leaveWaiver.approvedDisapprovedReason = params.reason;


        if(weaverStatus == 1){

            CalendarEvent calendarEvent = new CalendarEvent();
            calendarEvent.calendarType = CalendarType.findByName("waiver");
            calendarEvent.author = leaveWaiver.waiverEmployee;
            Date endTime = parseDate(leaveWaiver.waiverDate.toString().split(" ")[0] + " "+"23:59:00","yyyy-MM-dd H:m:s");
            Date startTime = parseDate(leaveWaiver.waiverDate.toString().split(" ")[0] + " "+"00:00:00", "yyyy-MM-dd H:m:s");
            calendarEvent.startTime = startTime;
            calendarEvent.endTime = endTime;
            calendarEvent.subject = "advance waiver";
            calendarEvent.description = "Advance Waiver is apporoved";
            calendarEvent.repeatType = "no";
            calendarEvent.save();
        }


        if(user){
            leaveWaiver.approvedBy = user;
        }



        if(leaveWaiver.save(flush: true)){
            println("saved in service");
            return true;
        }
        else{
            return false;
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
    def isEmployee(userCode){
        Employee employee = Employee.findByUserCode(userCode)
        if (employee){
            println ("this user is employee");
            return true;
        }else{
            println("npt employee");
            return false;
        }
    }
}
