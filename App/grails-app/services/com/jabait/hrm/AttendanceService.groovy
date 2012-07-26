package com.jabait.hrm

import com.jabait.hrm.time.AttendanceRegister

import com.jabait.coresecurity.util.EncryptionUtils
import java.text.SimpleDateFormat
import com.jabait.security.User
import com.jabait.hrm.time.LateThreshold
import com.jabait.hrm.time.AttendanceAdjustment

class AttendanceService {

    def serviceMethod() {

    }

    def saveEmployeeEntry(params){
        Employee employee = Employee.findByUserCodeAndPassword(params.userCode, new EncryptionUtils("jabait").encrypt(params.password));
        if(employee!= null){
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            AttendanceRegister attendanceRegister = AttendanceRegister.findByAttendantAndJobDate(employee, date);
            if(attendanceRegister != null){
                attendanceRegister.outTime = new Date();
                attendanceRegister.save(flush: true);
                return "attendance for exit entered";
            } else {
                AttendanceRegister attendanceRegisterEntry = new AttendanceRegister();
                attendanceRegisterEntry.inTime = new Date();
                attendanceRegisterEntry.jobDate = new Date();
                attendanceRegisterEntry.attendant = employee;
                attendanceRegisterEntry.remark = "";
                attendanceRegisterEntry.outTime = null;
                attendanceRegisterEntry.lateMinute = 15;
                attendanceRegisterEntry.save(flush: true);

                return "attendance entered";
            }


        }
        else {
            return "No Employee Found with these credentials!!!";
        }
    }

    def saveAdminPrivilegedAttendance(params){

        User admin= User.findByUserCodeAndPassword(params.adminId, new EncryptionUtils("jabait").encrypt(params.adminPassword))

        if(admin!= null){
            println("admin exist")
            def employeeId = params.employeeId;
            String time = params.time.toString();
            String finalTime = "00:00:00";
            

            if(time.contains("AM")) {
                time = time.replaceAll("AM", "").trim()
                String[] hourMin = time.split(":");
                finalTime = hourMin[0].concat(":").concat(hourMin[1]).concat(":00")
            } else {
                time = time.replaceAll("PM", "").trim()
                String[] hourMin = time.split(":");
                String hour = Integer.parseInt(hourMin[0]) + 12
                finalTime = hour.concat(":").concat(hourMin[1]).concat(":00")
            }

            Date entryTime = new Date().parse("H:m:s", finalTime)

            AttendanceRegister attendanceRegister = new AttendanceRegister();

            attendanceRegister.attendant = Employee.get(employeeId)
            attendanceRegister.inTime = entryTime;
            attendanceRegister.jobDate = new Date();
            attendanceRegister.remark ="Employee entry";
            attendanceRegister.lateMinute = 23;

            //println(employeeId +"  "+entryTime)

            if(!attendanceRegister.save()){
                return "Entry failed";
            }
            else{
                return "Entry succeeded";
            }


        }else{
           return "Admin username and password not correct";
        }


    }

    def saveThresholdType(params){
        // println("roleName = "+params.jobRoleName);
        // println("roleDescription = "+params.jobRoleDescription);
//        (params.jobTitleCode)
        def threshold =  LateThreshold.findByThresholdType(params.thresholdType)
        if(threshold){
            println("already exits Late Threshold")
//            flash.message = "organ"
            return false;
        }
        else{
            def thresholdType = new LateThreshold();
            thresholdType.thresholdType = params.thresholdType;
            thresholdType.lateThreshold = new Integer(params.lateThreshold);

            // jobRole.save();
            if(!thresholdType.save(flush: true)){
                println("Not Save");
                return false;
            }
            else {
                println("Save");
                return true;
            }

        }
    }

    def saveAdjustmentThreshold(params){
        String adjustmentType = params.adjustmentType.toString();
        def adjustment =  AttendanceAdjustment.findByAdjustmentType(adjustmentType)
        if(adjustment){
            println("already exits Late Threshold")
//            flash.message = "organ"
            return false;
        }
        else{
            def adjustmentThreshold = new AttendanceAdjustment();
            adjustmentThreshold.adjustmentType = params.adjustmentType;
            adjustmentThreshold.adjustmentThreshold = new Integer(params.adjustmentThreshold.toString());

            // jobRole.save();
            if(!adjustmentThreshold.save(flush: true)){
                println("Not Save");
                return false;
            }
            else {
                println("Save");
                return true;
            }

        }
    }

    boolean deleteAttendanceAdjustment(attendanceAdjustmentInstance,id){
        if(!attendanceAdjustmentInstance){
            return false;
        }
        try{
            def attendance = AttendanceAdjustment.get(id);
            attendance.delete(flush: true);
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    boolean deleteLateThreshold(lateThresholdInstance,id){
        if(!lateThresholdInstance){
            return false;
        }
        try{
            def late = LateThreshold.get(id);
            late.delete(flush: true);
            return true;
        } catch (Exception ex){
            return false;
        }
    }
}
