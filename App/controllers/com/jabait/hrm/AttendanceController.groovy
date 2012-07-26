package com.jabait.hrm

import com.jabait.hrm.time.LateThreshold
import com.jabait.hrm.time.AttendanceAdjustment
import grails.converters.JSON
import com.jabait.hrm.time.AttendanceRegister
import org.apache.poi.ss.usermodel.DataValidation
import org.apache.poi.ss.usermodel.DataValidationConstraint
import org.apache.poi.ss.usermodel.DataValidationHelper
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper
import org.apache.poi.hssf.util.CellRangeAddressList
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.DVConstraint
import org.apache.poi.hssf.usermodel.HSSFDataValidation
import org.apache.poi.hssf.usermodel.HSSFCell
import org.apache.poi.hssf.usermodel.HSSFCellStyle
import org.apache.poi.hssf.usermodel.HSSFDataFormat
import org.apache.poi.hssf.util.HSSFColor
import org.apache.poi.poifs.crypt.EncryptionInfo
import com.jabait.coresecurity.util.EncryptionUtils
import java.text.SimpleDateFormat
import java.text.DateFormat
import org.springframework.format.annotation.DateTimeFormat
import org.apache.poi.hslf.model.Sheet
import org.apache.poi.ss.usermodel.CellStyle
import javax.servlet.ServletOutputStream
import java.sql.Timestamp



class AttendanceController {

    def attendanceService;
    def configurationService;
    def index() { }

    def employeeEntry() {

        render(view:'employee_entry', model: [type:'Employee Entry']);


    }

    def employeeEntryAdmin() {

        render(view:'employee_entry_admin', model: [type:'Employee Entry Admin']);


    }

    def saveEmployeeEntry(){
        flash.message = attendanceService.saveEmployeeEntry(params)
        redirect(action: "employeeEntry")
    }

    def saveAdminPrivilegedAttendance(){
        println("Enter in controller")
        flash.message = attendanceService.saveAdminPrivilegedAttendance(params)
        redirect(action: "employeeEntryAdmin")
    }

    def attendanceAdjustmentList(){
        //def attendanceAdjustmentList=AttdanceAdjustment.findAll();
        render(view: "attendance_adjustment_list", model: [type: "Attendance Adjustment List"])
    }
    def attendanceAdjustmentJsonData(){
        if(!params.limit){
            params.max =10;
        }
        else {
            params.max = params.limit;
            render([attendanceAdjustments : AttendanceAdjustment.list(offset: params.start,max: params.max),totalCount:AttendanceAdjustment.count()] as JSON);

        }

    }

    def lateThresholdList(){
        render(view: "late_threshold_list", model: [type: "Late Threshold List"])
    }

    def lateThresholdJsonData(){
        if(!params.limit){
            params.max = 10;
        }
        else {
            params.max = params.limit;
            render([lateThresholds : LateThreshold.list(offset: params.start,max: params.max),totalCount:LateThreshold.count()] as JSON);

        }

    }

    def saveThresholdType(){
        if (!attendanceService.saveThresholdType(params)){
            flash.message = "Threshold Type save failed";
            redirect(action: 'createLateThreshold')
        }
        else{
            flash.message = "Threshold Type save successfully";
            redirect(action: 'createLateThreshold')
        }

    }

    def createLateThreshold(){
//        def leaves = configurationService.leaveList();
        render(view: "create_late_threshold", model: [type: "Create Late Threshold"])
    }


    def saveAdjustmentThreshold(){
        if (!attendanceService.saveAdjustmentThreshold(params)){
            flash.message = "Adjustment Type save failed";
            redirect(action: 'createAdjustmentThreshold')
        }
        else{
            flash.message = "Adjustment Type save successfully";
            redirect(action: 'createAdjustmentThreshold')
        }

    }

    def createAdjustmentThreshold(){
//        def leaves = configurationService.leaveList();
        render(view: "create_adjustment_threshold", model: [type: "Create Late Threshold"])

    }

    def deleteAttendanceAdjustment(){

        def attendanceAdjustmentInstance = AttendanceAdjustment.get(params.id)
        attendanceService.deleteAttendanceAdjustment(attendanceAdjustmentInstance, params.id);
        flash.message = "Attendance Adjustment Instance.delete.message"
        //flash.args = [employeInstance.userCode]
        flash.default = "Attendance Adjustment Instance Status deleted";
        /*params.max = Math.min(params.max ? params.int('max') : 10, 100);
        render(view: "user_list", model:  [userInstanceList: userDetailsService.loadUsers(params), userInstanceTotal: User.count()]);*/
        redirect(action: "attendanceAdjustmentList");
        return true;
    }


    def deleteLateThreshold(){

        def lateThresholdInstance = LateThreshold.get(params.id)
        attendanceService.deleteLateThreshold(lateThresholdInstance, params.id);
        flash.message = "Late Threshold.delete.message"
        //flash.args = [employeInstance.userCode]
        flash.default = "Late Threshold Status deleted";
        /*params.max = Math.min(params.max ? params.int('max') : 10, 100);
        render(view: "user_list", model:  [userInstanceList: userDetailsService.loadUsers(params), userInstanceTotal: User.count()]);*/
        redirect(action: "lateThresholdList");
        return true;
    }

    def showAdjustmentDetails(){

        render(view: 'show_adjustment_details');

    }

    def showLateThresholdDetails(){

        render(view: 'show_late_threshold_details');
    }

    def excelDataList(){

        String fromDate="";
        String toDate="";
        String all="";
        String userCode="";
        DateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
        String fileName = dateFormatter.format(new Date());

        String filename = "report_" + fileName+ "_attendance" +".xls";

        List<AttendanceRegister> attendanceRegisters;

        if( !params.toJobDate.toString().equals("")
                && !params.fromJobDate.toString().equals("")
                && !params.userCode.toString().equals("")) {

            Date toJobDate = new Date().parse("yyyy-MM-dd",params.toJobDate);
            Date fromJobDate=new Date().parse("yyyy-MM-dd",params.fromJobDate);
            Employee attendant = Employee.findByUserCode(params.userCode.toString());

            userCode= params.userCode.toString();
            fromDate=params.fromJobDate.toString();
            toDate=params.toJobDate.toString();
            attendanceRegisters=AttendanceRegister.findAllByAttendantAndJobDateBetween(attendant,fromJobDate, toJobDate);
            print("attendanceRegisters.size()"+attendanceRegisters.size()) ;


        }


        else if( params.fromJobDate != "" &&
                !params.toJobDate.toString().equals("") ){

            Date toJobDate=new Date().parse("yyyy-MM-dd",params.toJobDate);
            Date fromJobDate=new Date().parse("yyyy-MM-dd",params.fromJobDate);

            fromDate=params.fromJobDate.toString();
            toDate=params.toJobDate.toString();

            attendanceRegisters = AttendanceRegister.findAllByJobDateBetween(fromJobDate, toJobDate);


        }
        else if (!params.userCode.toString().equals("")){

            userCode= params.userCode.toString();
            all="All Attendance Record-";

            Employee attendant = Employee.findByUserCode(params.userCode.toString());
            attendanceRegisters=AttendanceRegister.findAllByAttendant(attendant);

        }

        else {
            attendanceRegisters = AttendanceRegister.list();
            all="All Attendance Record";

        }


        try {

            response.setHeader( "Content-Disposition", "attachment; filename=" + filename );
            response.setContentType("application/vnd.ms-excel");

            FileOutputStream fileOut = new FileOutputStream(filename);
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet worksheet = workbook.createSheet("Attendant Register");


           HSSFRow row11=worksheet.createRow((short) 0);
           HSSFCell cell000=row11.createCell((short) 3).setCellValue("Ocean Group");
           HSSFCell cell001=worksheet.createRow((short) 1).createCell((short) 3).setCellValue("Attendance register");
           HSSFCell cell002=worksheet.createRow((short) 2).createCell((short) 3).setCellValue(all+""+userCode+""+fromDate+" "+toDate);

            HSSFRow row0 = worksheet.createRow((short) 3);
            
            HSSFCell cell1=row0.createCell((short) 0).setCellValue("User Code");
            HSSFCell cell2=row0.createCell((short) 1).setCellValue("Employee name");
            HSSFCell cell3=row0.createCell((short) 2).setCellValue("Job Date");
            HSSFCell cell4=row0.createCell((short) 3).setCellValue("In Time");
            HSSFCell cell5=row0.createCell((short) 4).setCellValue("Out Time");
            HSSFCell cell6=row0.createCell((short) 5).setCellValue("Duration");
            HSSFCell cell7=row0.createCell((short) 6).setCellValue("Remark");



            int i=4;
            Long duration=0;
            int hours=0;
            int minute=0;

            String inTime="";
            String outTime="";
            Date outTimes=null;


            for(AttendanceRegister attendanceRegister : attendanceRegisters){
               if (attendanceRegister?.getOutTime()?.getTime()!=null&& attendanceRegister?.getInTime()?.getTime()!=null){
                    duration=attendanceRegister?.getOutTime()?.getTime()-attendanceRegister?.getInTime()?.getTime();
                    minute=Math.floor(duration/(60*1000));
                    hours=Math.floor(duration/(60*60*1000));
                    minute=Math.floor(minute-(hours*60));
               }
                else {
                    hours = 00;
                   minute=00;
               }
                if (attendanceRegister?.getOutTime()!=null){
                    outTimes=attendanceRegister?.getOutTime();
                    outTime=attendanceRegister?.getOutTime();
                }
                else {
                    outTime="Null";
                    outTimes=null;
                }

                String jobDate=attendanceRegister?.getJobDate()?.toString();

                String[] temp;
                temp=jobDate.split(":");


                jobDate=temp[0];
                temp=jobDate.split("00");
                jobDate= temp[0];

                HSSFRow row1 = worksheet.createRow((short) i);

                i++;

                for (int j=0;j<7;j++){

                    println("outTimes is :"+outTimes?.getTimeString());
                    HSSFCell cell8=row1.createCell((short) 0).setCellValue(attendanceRegister?.attendant?.userCode?.toString());
                    HSSFCell cell9=row1.createCell((short) 1).setCellValue(attendanceRegister?.attendant?.employeeProfile?.name?.getFirstName()?.toString()+" "+attendanceRegister?.attendant?.employeeProfile?.name?.getMiddleName()?.toString()+" "+attendanceRegister?.attendant?.employeeProfile?.name?.getSurname()?.toString());
                    HSSFCell cell10=row1.createCell((short) 2).setCellValue(jobDate.toString());
                    HSSFCell cell11=row1.createCell((short) 3).setCellValue(attendanceRegister?.inTime?.getHours()?.toString()+":"+attendanceRegister?.inTime?.getMinutes()?.toString()+":"+attendanceRegister?.inTime?.getSeconds()?.toString());
                    HSSFCell cell12=row1.createCell((short) 4).setCellValue(outTimes?.getTimeString());
                    HSSFCell cell13=row1.createCell((short) 5).setCellValue(hours+":"+minute);
                    HSSFCell cell14=row1.createCell((short) 6).setCellValue(attendanceRegister?.remark?.toString());


                }
            }

            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            response.outputStream << new File(filename).bytes

            File file = new File(filename)
            file.delete();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    def attendanceJsonData(){
        if(!params.limit){
            params.max = 10;
        }
        else {
            params.max = params.limit;

        }


        List<AttendanceRegister> attendanceRegisters;






              if(params.toJobDate!=null && params.fromJobDate!=null && params.userCode!=null){
                 //print("ddd");
                Date toJobDate=new Date().parse("yyyy-MM-dd",params.toJobDate);
                Date fromJobDate=new Date().parse("yyyy-MM-dd",params.fromJobDate);
                Employee attendant = Employee.findByUserCode(params.userCode.toString());
           
                 attendanceRegisters=AttendanceRegister.findAllByAttendantAndJobDateBetween(attendant,fromJobDate, toJobDate);
                  print("attendanceRegisters.size()"+attendanceRegisters.size()) ;
                
    
            }


       else if( params.fromJobDate!=null && params.toJobDate!=null){

            Date toJobDate=new Date().parse("yyyy-MM-dd",params.toJobDate);
            Date fromJobDate=new Date().parse("yyyy-MM-dd",params.fromJobDate);
            print("toJobDate"+toJobDate);
            print("fromJobDate"+fromJobDate);
            attendanceRegisters = AttendanceRegister.findAllByJobDateBetween(fromJobDate, toJobDate);
            print("attendanceRegisters.size()"+attendanceRegisters.size()) ;

        }
        else if (params.userCode!=null){
                  Employee attendant = Employee.findByUserCode(params.userCode.toString());
                  attendanceRegisters=AttendanceRegister.findAllByAttendant(attendant);
                  print("attendanceRegisters.size()"+attendanceRegisters.size()) ;

              }

        else{
            attendanceRegisters = AttendanceRegister.list();

        }


        List attendanceHistory = new ArrayList();
        Map<String, String> attendanceMapped = null;
        for (AttendanceRegister attendanceRegister : attendanceRegisters){

            attendanceMapped = new HashMap<String,String>();
            String id = attendanceRegister?.id;
            String attendanceInHours = attendanceRegister?.inTime?.hours?.toString();
            String attendanceInMinutes = attendanceRegister?.inTime?.minutes?.toString();
            String attendanceInSeconds = attendanceRegister?.inTime?.seconds?.toString();
            String attendanceInTime = attendanceInHours.concat(":")
                    .concat(attendanceInMinutes.concat(":")
                    .concat(attendanceInSeconds));



            String attendanceOutHours = "";
            String attendanceOutMinutes = "";
            String attendanceOutSeconds = "";

            if (attendanceRegister?.outTime != null){
                attendanceOutHours = attendanceRegister?.outTime?.hours?.toString();
                attendanceOutMinutes = attendanceRegister?.outTime?.minutes?.toString();
                attendanceOutSeconds = attendanceRegister?.outTime?.seconds?.toString();
            }

            String attendanceOutTime = "";

            if (!attendanceOutHours.equals(""))
                attendanceOutTime = attendanceOutHours.concat(":")
                        .concat(attendanceOutMinutes.concat(":")
                        .concat(attendanceOutSeconds));


            String attendantFirstName = "";
            String attendantMiddleName = "";
            String attendantLastName = "";

            if (attendanceRegister?.attendant?.employeeProfile?.name?.firstName != null)
                attendantFirstName = attendanceRegister?.attendant?.employeeProfile?.name?.firstName;
            if (attendanceRegister?.attendant?.employeeProfile?.name?.middleName != null)
                attendantMiddleName = attendanceRegister?.attendant?.employeeProfile?.name?.middleName;
            if (attendanceRegister?.attendant?.employeeProfile?.name?.surname != null)
                attendantLastName = attendanceRegister?.attendant?.employeeProfile?.name?.surname;




            String jobDate = attendanceRegister.jobDate.toString().split(" ")[0]
            // jobDate= attendanceRegister.jobDate.toString().split(":")[0]

            String attendant = attendantFirstName.concat(" ".concat(attendantMiddleName)).concat(" ".concat(attendantLastName));

            attendanceMapped.put("id", id);
            attendanceMapped.put("inTime", attendanceInTime);
            attendanceMapped.put("outTime", attendanceOutTime);
            attendanceMapped.put("attendant", attendant);
            attendanceMapped.put("jobDate", jobDate);
            attendanceMapped.put("attendanceStatus", attendanceRegister.remark);

            attendanceHistory.add(attendanceMapped);

        }

        int max = 10;
        int totalCount = AttendanceRegister.count();


       if (attendanceHistory.size() < 10) {
           max = attendanceHistory.size()
       }

       int start = (params.start != null) ? Integer.parseInt(params.start) : 0;
       int limit = (params.limit != null) ? Integer.parseInt(params.limit) : 10;

       render([attendances : attendanceHistory.asList().subList(start, start + limit > attendanceHistory.size() ? attendanceHistory.size() : start + limit),totalCount:totalCount] as JSON);

    }

    def attendanceList(){

        render(view: 'attendance_list', model: [type: 'Attendance List'])
    }


    def lateThresholdDetails(){

        LateThreshold lateThreshold = LateThreshold.get(params.id)

        render(view: "show_late_threshold_details", model: [lateThresholdType: lateThreshold.thresholdType, lateThresholds: lateThreshold.lateThreshold])
    }

    def adjustmentDetails(){

        AttendanceAdjustment adjustmentType = AttendanceAdjustment.get(params.id)
        render(view: "show_adjustment_details", model: [adjustmentTypes: adjustmentType.adjustmentType, adjustmentThresholds:adjustmentType.adjustmentThreshold])

    }

}

