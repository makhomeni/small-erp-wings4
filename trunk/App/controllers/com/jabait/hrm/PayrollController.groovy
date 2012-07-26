package com.jabait.hrm


import grails.converters.JSON
import com.jabait.hrm.payroll.*
import com.jabait.hrm.time.AttendanceRegister
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFCell
import java.text.DateFormat
import java.text.SimpleDateFormat
import com.jabait.security.User
import com.jabait.accounting.Accounts
import com.jabait.accounting.AccountHead
import org.apache.commons.lang.WordUtils
import com.jabait.util.EnglishNumberToWords
import org.codehaus.groovy.grails.plugins.jasper.JasperReportDef


class PayrollController {

    def jasperService;

    def home(){
        render(view: "payroll_home",  model: [type: 'Payroll List']);
    }

    def advancePayRegisterList(){
        render(view: "advance_pay_register_list", model: [type: 'Advance Pay Register List']);
    }

    def createAdvancePayRequest(){
        render(view: "create_advance_pay_request", model: [titleOfPage: 'Advance Pay Request']);
    }


    def advanceApprove(){
        AdvancePayRegister advancePayRegister = AdvancePayRegister.get(params.advanceRegisterId);
        advancePayRegister.reasonToApproveReject = params.reason;
        advancePayRegister.status = 1;
        advancePayRegister.approvedDate = new Date();
        advancePayRegister.save();

        render (["success": true] as JSON);
    }

    def advanceDisapprove(){
        AdvancePayRegister advancePayRegister = AdvancePayRegister.get(params.advanceRegisterId);
        advancePayRegister.reasonToApproveReject = params.reason;
        advancePayRegister.status = 2;
        advancePayRegister.approvedDate = new Date();
        advancePayRegister.save();

        render (["success": true] as JSON);
    }

    def saveAdvancePayRegister(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date requestedDate = formatter.parse(params.requestedDate);

        AdvancePayRegister advancePayRegister = new AdvancePayRegister();
        advancePayRegister.status = 3;
        advancePayRegister.advanceRequester = Employee.findByUserCode(params.employeeId);
        advancePayRegister.advanceBy = User.findByUserCode(params.authorizedId);
        advancePayRegister.requestedDate = requestedDate;
        advancePayRegister.advanceAmount = Double.parseDouble(params.advanceAmount);
        advancePayRegister.account=Accounts.findByAccountName("Advance Salary");
        if(advancePayRegister.account==null){
            AccountHead.initialize();
            Accounts.initialize();
            advancePayRegister.account=Accounts.findByAccountName("Advance Salary");
        }
        advancePayRegister.save();
        
        ///
        Map advancePayRegisterMap;

        DateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
        String voucherNumber = "advance_" + dateFormatter.format(new Date());




        Double amount = advancePayRegister.advanceAmount;



        String amountWords = WordUtils.capitalize(EnglishNumberToWords.convert(amount.longValue()));
        println(amountWords)

        List vouchers = new ArrayList();
        Map voucherMap = new HashMap();
        voucherMap.put("particulars", advancePayRegister.advanceRequester?.employeeProfile?.name?.firstName
                + " " + advancePayRegister.advanceRequester?.employeeProfile?.name?.middleName
                + " " + advancePayRegister.advanceRequester?.employeeProfile?.name?.surname);
        voucherMap.put("amount", amount);
        voucherMap.put("total", amount);
        voucherMap.put("voucherNumber", voucherNumber);
        voucherMap.put("amountWords", amountWords);
        voucherMap.put("accountTitle", advancePayRegister.account.accountHead.description);
        vouchers.add(voucherMap);
        
        println("vourchers " + vouchers.size())


        println("params._file " + params._file)

        //render advanceVoucher
        JasperReportDef reportDef = jasperService.buildReportDefinition(params, request.getLocale(), [data:vouchers])

        DateFormat dateFormatterAuto = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
        String fileName = dateFormatterAuto.format(new Date());
        String filenamePdf = "voucher_pdf_" + fileName+ "_payroll";

        if (!reportDef.fileFormat.inline && !reportDef.parameters._inline){
            response.setHeader("Content-disposition", "attachment; filename="+ filenamePdf + "." + reportDef.fileFormat.extension);
            response.contentType = reportDef.fileFormat.mimeTyp
            response.characterEncoding = "UTF-8"
            response.outputStream << reportDef.contentStream.toByteArray()
        }
        else{

            render(text: reportDef.contentStream, contentType: reportDef.fileFormat.mimeTyp, encoding: reportDef.parameters.encoding ? reportDef.parameters.encoding : 'UTF-8');
        }
        ///

        //redirect(action: "createAdvancePayRequest");
    }
    
    def advancePayRollJsonData(){
        List<AdvancePayRegister> advancePayRegisters = AdvancePayRegister.list();
        
        List advancePayRegistersArray = new ArrayList();
        Map<String,Object> advancePayMap;

        for (AdvancePayRegister advancePayRegister : advancePayRegisters) {
            String appStatus = "Pending";
            switch(advancePayRegister.status){
                case 3: appStatus = "Pending";
                    break;
                case 1: appStatus = "Approved";
                    break;
                case 2: appStatus = "Disapproved";
                    break;
            }

            advancePayMap = new HashMap<String,Object>();
            advancePayMap.put("id", advancePayRegister.id);
            advancePayMap.put("advanceRequester", advancePayRegister.advanceRequester?.employeeProfile?.name?.firstName +
            " " + advancePayRegister.advanceRequester?.employeeProfile?.name?.middleName + " " +
                    advancePayRegister.advanceRequester?.employeeProfile?.name?.surname);
            advancePayMap.put("advanceBy", advancePayRegister.advanceBy.userCode);
            advancePayMap.put("advanceAmount", advancePayRegister.advanceAmount.toString());
            advancePayMap.put("account", advancePayRegister.account.accountHead.description.toString());
            advancePayMap.put("requestedDate", advancePayRegister.requestedDate.toString());
            advancePayMap.put("reasonToApproveReject", advancePayRegister.reasonToApproveReject.toString());
            advancePayMap.put("approvedDate", advancePayRegister.approvedDate.toString());
            advancePayMap.put("status", appStatus);

            advancePayRegistersArray.add(advancePayMap);
        }

        int totalCount = advancePayRegistersArray.size();

        int start = (params.start != null) ? Integer.parseInt(params.start) : 0;
        int limit = (params.limit != null) ? Integer.parseInt(params.limit) : 10;

        render([advancePayRegister : advancePayRegistersArray.asList().subList(start,
                start + limit > advancePayRegistersArray.size() ?
                    advancePayRegistersArray.size() : start + limit),totalCount:totalCount] as JSON);
    }

    def payRegisterList(){

        render(view: 'pay_register_list', model: [type: 'Payroll List'])

    }
    def  savePayRegister(){


//        println("params.payDate"+params.payDate);
        println("params.userCode"+params.userCode);

        PayRegister payRegister;
        Double basicSalary = 0;
        Double perDaySalary = 0;
        int absentByLate = 0;
        int present = 0
        int absent = 0
        int late = 0;
        int remarkNull=0;
        Double netPay = 0;
        Double deductionsAmount = 0;


        if (!params.userCode.toString().equals("") && !params.fromJobDate.toString().equals("") && !params.toJobDate.toString().equals("")){

            println("all ar not null");

            DateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
            String fileName = dateFormatter.format(new Date());

            Date toJobDate=new Date().parse("yyyy-MM-dd",params.toJobDate);
            Date fromJobDate=new Date().parse("yyyy-MM-dd",params.fromJobDate);

            Employee employee=Employee.findByUserCode(params.userCode);
            //for (Employee employee : Employee.findAll()){
                Set<Allowance> allowances = employee.allowances
                double allowanceAmount = 0;
//            filename=filename+employee.userCode;
                fileName=fileName+"-"+employee.getId();
                for(Allowance allowance : allowances){
                    allowanceAmount += allowance.allowanceAmount;
                }


                present = Integer.parseInt(AttendanceRegister.withCriteria{
                    and{
                        between("jobDate", fromJobDate, toJobDate)
                    }
                    and{
                        eq("attendant.id", employee.id)
                    }
                    and{
                        eq("remark", "present")
                    }
                }.size().toString());
                println("present is"+present);
                absent = Integer.parseInt(AttendanceRegister.withCriteria{
                    and{
                        between("jobDate", fromJobDate, toJobDate)
                    }
                    and{
                        eq("attendant.id", employee.id)
                    }
                    and{
                        eq("remark", "absent")
                    }
                }.size().toString());

                late = Integer.parseInt(AttendanceRegister.withCriteria{
                    and{
                        between("jobDate", fromJobDate, toJobDate)
                    }
                    and{
                        eq("attendant.id", employee.id)
                    }
                    and{
                        eq("remark", "late")
                    }
                }.size().toString());
              remarkNull= Integer.parseInt(AttendanceRegister.withCriteria{
                and{
                    between("jobDate", fromJobDate, toJobDate)
                }
                and{
                    eq("attendant.id", employee.id)
                }
                and{
                    eq("remark", "late")
                }
            }.size().toString());

                if (EmployeeProfile.findByOwner(employee)?.salary != null){
                    basicSalary = EmployeeProfile.findByOwner(employee)?.salary;
                }
            List<AdvancePayRegister> advancePayRegisters = AdvancePayRegister.withCriteria{
                and{
                    between("approvedDate", fromJobDate, toJobDate)
                }
                and{
                    eq("advanceRequester.id", Employee.findByUserCode(params.userCode).id)
                }
                and{
                    eq("status", 1)
                }
            }

            Double deductionAmount = 0.0;

            println("The ultimate size of " + advancePayRegisters.size())

            advancePayRegisters.each {
                advancePayRegister ->
                deductionAmount += advancePayRegister.advanceAmount
            }
                println("deductionAmount is:"+deductionAmount+"employee.getId()"+employee.getId());
                perDaySalary = (basicSalary)/26;
                perDaySalary = Math.ceil(perDaySalary);

                absentByLate = Math.floor((late)/3);

                Double grossPay = allowanceAmount+(present*perDaySalary)+((late-absentByLate)*perDaySalary)+(remarkNull*perDaySalary);

                netPay = (Double)grossPay-deductionAmount;
               PayRegister payRegister1=PayRegister.findByEmployee(employee);
                if (payRegister1!=null){
                   println("");
                }
            else {

                }
            if (payRegister1?.getStartDay()?.getMonth() == fromJobDate.getMonth() && payRegister1?.getEndDay()?.getMonth() == toJobDate.getMonth()){
                println("payRegister1.getStartDay() is ok"+payRegister1?.getStartDay()+"payRegister1.getEndDay()"+payRegister1.getEndDay()+"employee.getId()"+employee.getId());
            }
            else {
                payRegister = new PayRegister();
                payRegister.employee = employee;
                payRegister.payDate = new Date();
                payRegister.salaryDue = employee?.employeeProfile?.getSalary();
                payRegister.startDay = fromJobDate;
                payRegister.endDay = toJobDate;
                payRegister.grossPay = grossPay;
                payRegister.deductions = deductionAmount;
                payRegister.netPay = netPay;
                payRegister.paymentIdentifier ="25";
                payRegister.save();
            }



        }
        else if(!params.toJobDate.toString().equals("") && !params.fromJobDate.toString().equals("")){
            println("all ar not null");
            DateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
            String fileName = dateFormatter.format(new Date());
            println("fileName is:"+fileName);
            Date toJobDate=new Date().parse("yyyy-MM-dd",params.toJobDate);
            Date fromJobDate=new Date().parse("yyyy-MM-dd",params.fromJobDate);


            for (Employee employee : Employee.findAll()){
                Set<Allowance> allowances = employee.allowances
                double allowanceAmount = 0;
//            filename=filename+employee.userCode;
                fileName=fileName+"-"+employee.getId();
                for(Allowance allowance : allowances){
                    allowanceAmount += allowance.allowanceAmount;
                }


                present = Integer.parseInt(AttendanceRegister.withCriteria{
                    and{
                        between("jobDate", fromJobDate, toJobDate)
                    }
                    and{
                        eq("attendant.id", employee.id)
                    }
                    and{
                        eq("remark", "present")
                    }
                }.size().toString());
                println("present is"+present);
                absent = Integer.parseInt(AttendanceRegister.withCriteria{
                    and{
                        between("jobDate", fromJobDate, toJobDate)
                    }
                    and{
                        eq("attendant.id", employee.id)
                    }
                    and{
                        eq("remark", "absent")
                    }
                }.size().toString());

                late = Integer.parseInt(AttendanceRegister.withCriteria{
                    and{
                        between("jobDate", fromJobDate, toJobDate)
                    }
                    and{
                        eq("attendant.id", employee.id)
                    }
                    and{
                        eq("remark", "late")
                    }
                }.size().toString());

                if (EmployeeProfile.findByOwner(employee)?.salary != null){
                    basicSalary = EmployeeProfile.findByOwner(employee)?.salary;
                }
                else {

                }
                List<AdvancePayRegister> advancePayRegisters = AdvancePayRegister.withCriteria{
                    and{
                        between("approvedDate", fromJobDate, toJobDate)
                    }
                    and{
                        eq("advanceRequester.id", employee.id)
                    }
                    and{
                        eq("status", 1)
                    }
                }

                Double deductionAmount = 0.0;

                println("The ultimate size of " + advancePayRegisters.size())

                advancePayRegisters.each {
                    advancePayRegister ->
                    deductionAmount += advancePayRegister.advanceAmount
                }


                println("deductionAmount is:"+deductionAmount+"employee.getId()"+employee.getId());
                perDaySalary = (basicSalary)/26;
                perDaySalary = Math.ceil(perDaySalary);

                absentByLate = Math.floor((late)/3);

//                Double grossPay = allowanceAmount+(present*perDaySalary);
                Double grossPay = allowanceAmount+(present*perDaySalary)+((late-absentByLate)*perDaySalary)+(remarkNull*perDaySalary);

                netPay = (Double)grossPay-deductionAmount;
                List<PayRegister> payRegisterList=PayRegister.findAllByEmployee(employee);
                //String number="25625";
                int paymentIdentifier=0;
                paymentIdentifier=Integer.parseInt(new SimpleDateFormat("HHmmss",Locale.US).format(new Date()));
                //serialNo = serialNo + employee.getId();
                paymentIdentifier+=employee.getId();
                println("serialNo is:"+paymentIdentifier)  ;
                if (payRegisterList.size()!= 0){
                    println("size is "+payRegisterList.size());
                    for(PayRegister  payRegister1: payRegisterList){

                        if (payRegister1?.getStartDay()?.getMonth() == fromJobDate?.getMonth() && payRegister1?.getEndDay()?.getMonth() == toJobDate?.getMonth()){
                            println("payRegister1.getStartDay() is ok"+payRegister1?.getStartDay()+"payRegister1.getEndDay()"+payRegister1.getEndDay()+"employee.getId()"+employee.getId());
                        }
                        else {
                            payRegister = new PayRegister();
                            payRegister.employee = employee;
                            payRegister.payDate = new Date();
                            payRegister.salaryDue = employee?.employeeProfile?.getSalary();
                            payRegister.startDay = fromJobDate;
                            payRegister.endDay = toJobDate;
                            payRegister.grossPay = grossPay;
                            payRegister.deductions = deductionAmount;
                            payRegister.netPay = netPay;
                            payRegister.paymentIdentifier = paymentIdentifier.toString();
                            payRegister.save();
                        }
                    }
                }
                else {

                    payRegister = new PayRegister();
                    payRegister.employee = employee;
                    payRegister.payDate = new Date();
                    payRegister.salaryDue = employee?.employeeProfile?.getSalary();
                    payRegister.startDay = fromJobDate;
                    payRegister.endDay = toJobDate;
                    payRegister.grossPay = grossPay;
                    payRegister.deductions = deductionAmount;
                    payRegister.netPay = netPay;
                    payRegister.paymentIdentifier = paymentIdentifier.toString();
                    payRegister.save();
                }


            }


        }
        else{
            println("all process");

            }
//        }

        redirect(controller: 'payroll',action: 'payRegisterList');
    }

    def exportExcelPayRoll(){

        DateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
        String fileName = dateFormatter.format(new Date());

        String filename = "report_" + fileName+ "_payroll" +".xls";

        Date toJobDate=new Date().parse("yyyy-MM-dd",params.toJobDate);
        Date fromJobDate=new Date().parse("yyyy-MM-dd",params.fromJobDate);

        if (!params.userCode.toString().equals("") && !params.fromJobDate.toString().equals("") && !params.toJobDate.toString().equals("")){

            try {
                response.setHeader( "Content-Disposition", "attachment; filename=" + filename );
                response.setContentType("application/vnd.ms-excel");

                FileOutputStream fileOut = new FileOutputStream(filename);
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet worksheet = workbook.createSheet("PayRoll Register");

                HSSFCell cell1=worksheet.createRow((short) 0).createCell((short) 6).setCellValue("Ocean Group");
                HSSFCell cell2=worksheet.createRow((short) 1).createCell((short) 6).setCellValue("Corporate Office: House No-91,Road No-3,Block-F,Banani,Dhaka-1213 ");
                HSSFCell cell3=worksheet.createRow((short) 2).createCell((short) 6).setCellValue("Salary for The Month of"+params.fromJobDate +"to"+params.toJobDate);

                HSSFRow row0 = worksheet.createRow((short) 4);

                HSSFCell cell21=row0.createCell((short) 0).setCellValue("SL");
                HSSFCell cell22=row0.createCell((short) 1).setCellValue("Name");
                HSSFCell cell32=row0.createCell((short) 2).setCellValue("Designation");
                HSSFCell cell4=row0.createCell((short) 3).setCellValue("Join Date");
                HSSFCell cell5=row0.createCell((short) 4).setCellValue("G.Salary");
                HSSFCell cell6=row0.createCell((short) 5).setCellValue("Working Day");
                HSSFCell cell7=row0.createCell((short) 6).setCellValue("Actual Day");
                HSSFCell cell71=row0.createCell((short) 7).setCellValue("Total Salary");
                HSSFCell cell72=row0.createCell((short) 8).setCellValue("Launch");
                HSSFCell cell73=row0.createCell((short) 9).setCellValue("Gross Salary");
                HSSFCell cell74=row0.createCell((short) 10).setCellValue("Advance Salary");
                HSSFCell cell75=row0.createCell((short) 11).setCellValue("Payable Salary");
                HSSFCell cell76=row0.createCell((short) 12).setCellValue("Signature");


                int i=5;
                int sl=1;
                int wrokingDay=26;




                for(PayRegister payRegister:PayRegister.list()){
                    int present = 0
                    int absent = 0
                    int late = 0;
                    int remarkNull=0;
                    int totalPresent=0;
                    int totalLate=0;
                    int totalAbsent=0;
                    Double perDaySalary=0.00,grossSalary=0.00;
                    Double netPayable=0.00;

                    present = Integer.parseInt(AttendanceRegister.withCriteria{
                        and{
                            between("jobDate", fromJobDate, toJobDate)
                        }
                        and{
                            eq("attendant.id", payRegister.employee.id)
                        }
                        and{
                            eq("remark", "present")
                        }
                    }.size().toString());
                    absent = Integer.parseInt(AttendanceRegister.withCriteria{
                        and{
                            between("jobDate", fromJobDate, toJobDate)
                        }
                        and{
                            eq("attendant.id", payRegister.employee.id)
                        }
                        and{
                            eq("remark", "absent")
                        }
                    }.size().toString());
                    late = Integer.parseInt(AttendanceRegister.withCriteria{
                        and{
                            between("jobDate", fromJobDate, toJobDate)
                        }
                        and{
                            eq("attendant.id", payRegister.employee.id)
                        }
                        and{
                            eq("remark", "late")
                        }
                    }.size().toString());
                    remarkNull=Integer.parseInt(AttendanceRegister.withCriteria{
                        and{
                            between("jobDate", fromJobDate, toJobDate)
                        }
                        and{
                            eq("attendant.id", payRegister.employee.id)
                        }
                        and{
                            eq("remark", " ")
                        }
                    }.size().toString());
                    totalLate=Math.floor(late/3);
                    totalAbsent = (int)absent+totalLate;


                    perDaySalary=Math.ceil((payRegister.employee.employeeProfile.getSalary())/26);

                    Set<Allowance> allowances = payRegister.employee.getAllowances();
                    double allowanceAmount = 0;



                    List<Deductions> deductions=Deductions.findAllByDeductionDateBetweenAndPayee(fromJobDate,toJobDate,payRegister.employee);
                    Double deductionAmount=0.00;
                    for (Deductions deduction : deductions) {
                        deductionAmount+=deduction.getAmount();

                    }
                    println("deductionAmount is:"+deductionAmount);

                    for(Allowance allowance : allowances){
                        allowanceAmount += allowance.allowanceAmount;
                    }


                    grossSalary = (perDaySalary*present)+allowanceAmount+((late-totalLate)*perDaySalary)+(remarkNull*perDaySalary);
                    println("late-totalLate is:"+late-totalLate+"payRegister.employee.getId()"+payRegister.employee.getId()+"remarkNull is :"+remarkNull);

                    netPayable = grossSalary - deductionAmount;

                    println("netPayable is :"+netPayable);

                    String payDate = payRegister?.employee?.employeeProfile?.getJoiningDate()?.toString();

                    String[] temp;
                    temp=payDate.split(":");


                    payDate=temp[0];
                    temp=payDate.split("00");
                    payDate= temp[0];

                    HSSFRow row1 = worksheet.createRow((short) i);
                    i++;
                    for (int j=0;j<13;j++){

                        HSSFCell cellA1=row1.createCell((short) 0);
                        HSSFCell cellA2=row1.createCell((short) 1);
                        HSSFCell cellA3=row1.createCell((short) 2);
                        HSSFCell cellA4=row1.createCell((short) 3);
                        HSSFCell cellA5=row1.createCell((short) 4);
                        HSSFCell cellA6=row1.createCell((short) 5);
                        HSSFCell cellA7=row1.createCell((short) 6);
                        HSSFCell cellA8=row1.createCell((short) 7);
                        HSSFCell cellA9=row1.createCell((short) 8);
                        HSSFCell cellA10=row1.createCell((short) 9);
                        HSSFCell cellA11=row1.createCell((short) 10);
                        HSSFCell cellA12=row1.createCell((short) 11);
                        HSSFCell cellA13=row1.createCell((short) 12);
                        HSSFCell cellA14=row1.createCell((short) 13);

                        cellA1.setCellValue(sl);
                        cellA2.setCellValue(payRegister?.employee?.employeeProfile?.name?.getFirstName()?.toString()+" "+payRegister?.employee?.employeeProfile?.name?.getMiddleName()?.toString()+" "+payRegister?.employee?.employeeProfile?.name?.getSurname()?.toString());
                        cellA3.setCellValue(payRegister?.employee?.employeeProfile?.getJobTitle()?.getJobTitleName()?.toString());
                        cellA4.setCellValue(payDate);
                        cellA5.setCellValue(payRegister?.employee?.employeeProfile?.getSalary()?.toString());
                        cellA6.setCellValue(wrokingDay?.toString());
                        cellA7.setCellValue(present?.toString());
                        cellA8.setCellValue(payRegister?.employee?.employeeProfile?.getSalary()?.toString());
                        cellA9.setCellValue(allowanceAmount?.toString());
                        cellA10.setCellValue(grossSalary?.toString());
                        cellA11.setCellValue(deductionAmount?.toString());
                        cellA12.setCellValue(netPayable?.toString());
                        cellA13.setCellValue(" ");



                    }
                    sl++;


                }

                workbook.write(fileOut);
                response.outputStream << new File(filename).bytes
                fileOut.flush();
                fileOut.close();

                File file = new File(filename)
                file.delete();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(!params.toJobDate.toString().equals("") && !params.fromJobDate.toString().equals("")){


            
            try {
            response.setHeader( "Content-Disposition", "attachment; filename=" + filename );
            response.setContentType("application/vnd.ms-excel");

            FileOutputStream fileOut = new FileOutputStream(filename);
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet worksheet = workbook.createSheet("PayRoll Register");

            HSSFCell cell1=worksheet.createRow((short) 0).createCell((short) 6).setCellValue("Ocean Group");
            HSSFCell cell2=worksheet.createRow((short) 1).createCell((short) 6).setCellValue("Corporate Office: House No-91,Road No-3,Block-F,Banani,Dhaka-1213 ");
            HSSFCell cell3=worksheet.createRow((short) 2).createCell((short) 6).setCellValue("Salary for The Month of"+params.fromJobDate +"to"+params.toJobDate);

            HSSFRow row0 = worksheet.createRow((short) 4);

            HSSFCell cell21=row0.createCell((short) 0).setCellValue("SL");
            HSSFCell cell22=row0.createCell((short) 1).setCellValue("Name");
            HSSFCell cell32=row0.createCell((short) 2).setCellValue("Designation");
            HSSFCell cell4=row0.createCell((short) 3).setCellValue("Join Date");
            HSSFCell cell5=row0.createCell((short) 4).setCellValue("G.Salary");
            HSSFCell cell6=row0.createCell((short) 5).setCellValue("Working Day");
            HSSFCell cell7=row0.createCell((short) 6).setCellValue("Actual Day");
            HSSFCell cell71=row0.createCell((short) 7).setCellValue("Total Salary");
            HSSFCell cell72=row0.createCell((short) 8).setCellValue("Launch");
            HSSFCell cell73=row0.createCell((short) 9).setCellValue("Gross Salary");
            HSSFCell cell74=row0.createCell((short) 10).setCellValue("Advance Salary");
            HSSFCell cell75=row0.createCell((short) 11).setCellValue("Payable Salary");
            HSSFCell cell76=row0.createCell((short) 12).setCellValue("Signature");


            int i=5;
            int sl=1;
            int wrokingDay=26;




            for(PayRegister payRegister:PayRegister.list()){
                int present = 0
                int absent = 0
                int late = 0;
                int remarkNull=0;
                int totalPresent=0;
                int totalLate=0;
                int totalAbsent=0;
                Double perDaySalary=0.00,grossSalary=0.00,totalSalary=0.00;
                Double netPayable=0.00;

                present = Integer.parseInt(AttendanceRegister.withCriteria{
                    and{
                        between("jobDate", fromJobDate, toJobDate)
                    }
                    and{
                        eq("attendant.id", payRegister.employee.id)
                    }
                    and{
                        eq("remark", "present")
                    }
                }.size().toString());
                absent = Integer.parseInt(AttendanceRegister.withCriteria{
                    and{
                        between("jobDate", fromJobDate, toJobDate)
                    }
                    and{
                        eq("attendant.id", payRegister.employee.id)
                    }
                    and{
                        eq("remark", "absent")
                    }
                }.size().toString());
                late = Integer.parseInt(AttendanceRegister.withCriteria{
                    and{
                        between("jobDate", fromJobDate, toJobDate)
                    }
                    and{
                        eq("attendant.id", payRegister.employee.id)
                    }
                    and{
                        eq("remark", "late")
                    }
                }.size().toString());
                remarkNull=Integer.parseInt(AttendanceRegister.withCriteria{
                    and{
                        between("jobDate", fromJobDate, toJobDate)
                    }
                    and{
                        eq("attendant.id", payRegister.employee.id)
                    }
                    and{
                        eq("remark", " ")
                    }
                }.size().toString());
                totalLate=Math.floor(late/3);
                totalAbsent = (int)absent+totalLate;


                perDaySalary=Math.ceil((payRegister.employee.employeeProfile.getSalary())/26);

                Set<Allowance> allowances = payRegister.employee.getAllowances();
                double allowanceAmount = 0;



               List<Deductions> deductions=Deductions.findAllByDeductionDateBetweenAndPayee(fromJobDate,toJobDate,payRegister.employee);
               Double deductionAmount=0.00;
                for (Deductions deduction : deductions) {
                    deductionAmount+=deduction.getAmount();

                }
                println("deductionAmount is:"+deductionAmount);

                for(Allowance allowance : allowances){
                    allowanceAmount += allowance.allowanceAmount;
                }


                grossSalary=(perDaySalary*present)+allowanceAmount+((late-totalLate)*perDaySalary)+(remarkNull*perDaySalary);
                println("late-totalLate is:"+late-totalLate+"payRegister.employee.getId()"+payRegister.employee.getId()+"remarkNull is :"+remarkNull);

                netPayable = grossSalary - deductionAmount;

                println("netPayable is :"+netPayable);
                String joinDate = "";

                joinDate = payRegister?.employee?.employeeProfile?.joiningDate?.toString();

                if (joinDate != null){
                    String[] temp;
                    temp=joinDate.split(":");


                    joinDate=temp[0];
                    temp=joinDate.split("00");
                    joinDate= temp[0];
                    joinDate = joinDate.split(" ")[0]
                }


                HSSFRow row1 = worksheet.createRow((short) i);

                i++;
                present=present+remarkNull+(late-totalLate);
                totalSalary=present*perDaySalary;
                for (int j=0;j<13;j++){

                    HSSFCell cellA1=row1.createCell((short) 0);
                    HSSFCell cellA2=row1.createCell((short) 1);
                    HSSFCell cellA3=row1.createCell((short) 2);
                    HSSFCell cellA4=row1.createCell((short) 3);
                    HSSFCell cellA5=row1.createCell((short) 4);
                    HSSFCell cellA6=row1.createCell((short) 5);
                    HSSFCell cellA7=row1.createCell((short) 6);
                    HSSFCell cellA8=row1.createCell((short) 7);
                    HSSFCell cellA9=row1.createCell((short) 8);
                    HSSFCell cellA10=row1.createCell((short) 9);
                    HSSFCell cellA11=row1.createCell((short) 10);
                    HSSFCell cellA12=row1.createCell((short) 11);
                    HSSFCell cellA13=row1.createCell((short) 12);
                    HSSFCell cellA14=row1.createCell((short) 13);

                    cellA1.setCellValue(sl);
                    cellA2.setCellValue(payRegister?.employee?.employeeProfile?.name?.getFirstName()?.toString()+" "+payRegister?.employee?.employeeProfile?.name?.getMiddleName()?.toString()+" "+payRegister?.employee?.employeeProfile?.name?.getSurname()?.toString());
                    cellA3.setCellValue(payRegister?.employee?.employeeProfile?.getJobTitle()?.getJobTitleName()?.toString());
                    cellA4.setCellValue(joinDate);
                    cellA5.setCellValue(payRegister?.employee?.employeeProfile?.getSalary()?.toString());
                    cellA6.setCellValue(wrokingDay?.toString());
                    cellA7.setCellValue(present?.toString());
                    cellA8.setCellValue(totalSalary?.toString());
                    cellA9.setCellValue(allowanceAmount?.toString());
                    cellA10.setCellValue(grossSalary?.toString());
                    cellA11.setCellValue(deductionAmount?.toString());
                    cellA12.setCellValue(netPayable?.toString());
                    cellA13.setCellValue(" ");



                }
                sl++;


            }




            workbook.write(fileOut);
            response.outputStream << new File(filename).bytes
            fileOut.flush();
            fileOut.close();

            File file = new File(filename)
            file.delete();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        }

        redirect(controller: 'payroll',action: 'payRegisterList');
    }
    
    def testList(){
        List<PayRegister> payRegisters = PayRegister.list();
        params.SUBREPORT_DIR = "${servletContext.getRealPath('/reports')}/"
        params.IMAGE_DIR = "${servletContext.getRealPath('/images')}/"

        chain(controller: 'report', action: 'generateReport', model: [data: payRegisters], params: params)
    }

    def payRollJsonData(){
        if(!params.limit){
            params.max =10;
        }
        else {

            params.max = params.limit;
        }


        List attendanceHistory = new ArrayList();
        Map<String, String> attendanceMapped = null;

        for (PayRegister payRegister : PayRegister.list()){

            String processDate = "";

            String startedDate="";
            String endDay = "";

            startedDate = payRegister?.getStartDay()?.toString() ;
            endDay = payRegister?.getEndDay()?.toString();
            endDay = endDay.split(":")[0].split("00")[0].split(" ")[0];
            startedDate = startedDate.split(":")[0].split("00")[0].split(" ")[0];
            println("startedDate"+startedDate);

            processDate = payRegister?.getPayDate()?.toString();

            if (processDate != null){
                String[] temp;
                temp = processDate.split(":");


                processDate = temp[0];
                temp = processDate.split("00");
                processDate = temp[0];
                processDate = processDate.split(" ")[0]
            }
            else {

            }


            attendanceMapped = new HashMap<String,String>();
            String id = payRegister?.id;
            String employeeName = payRegister?.employee?.userCode?.toString();
//            String endDay = payRegister?.endDay?.toString();
            String grossPay = payRegister?.grossPay?.toString();
            String netPay = payRegister?.netPay?.toString();
            String payDate = processDate;
            String paymentIdentifier = payRegister?.paymentIdentifier?.toString();
            String paymentMethod = payRegister?.paymentMethod?.id?.toString();
            String salaryDue = payRegister?.salaryDue?.toString();
//            String startDate = payRegister?.startDay?.toString();
            String deductions = payRegister?.getDeductions()?.toString();

            attendanceMapped.put("id", id);
            attendanceMapped.put("employee", employeeName);
            attendanceMapped.put("endDay", endDay);
            attendanceMapped.put("grossPay", grossPay);
            attendanceMapped.put("netPay", netPay);
            attendanceMapped.put("payDate", payDate);
            attendanceMapped.put("paymentIdentifier", paymentIdentifier);
            attendanceMapped.put("paymentMethod", paymentMethod);
            attendanceMapped.put("salaryDue", salaryDue);
            attendanceMapped.put("startDate", startedDate);
            attendanceMapped.put("deductions",deductions);

            attendanceHistory.add(attendanceMapped)
        }

        int max = 10;
        int totalCount = PayRegister.count();


        if (attendanceHistory.size() < 10) {
            max = attendanceHistory.size()
        }

        int start = (params.start != null) ? Integer.parseInt(params.start) : 0;
        int limit = (params.limit != null) ? Integer.parseInt(params.limit) : 10;

        render([payregister : attendanceHistory.asList().subList(start, start + limit > attendanceHistory.size() ? attendanceHistory.size() : start + limit),totalCount:totalCount] as JSON);

    }

    def index() {

    }
}
