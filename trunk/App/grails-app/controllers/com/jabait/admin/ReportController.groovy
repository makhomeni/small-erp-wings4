package com.jabait.admin

import com.jabait.hrm.payroll.PayRegister

import org.codehaus.groovy.grails.plugins.jasper.JasperReportDef
import java.text.DateFormat
import java.text.SimpleDateFormat

import com.jabait.hrm.time.AttendanceRegister
import com.jabait.hrm.payroll.Allowance
import com.jabait.hrm.payroll.Deductions
import com.jabait.hrm.Employee
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFCell
import com.jabait.hrm.payroll.AdvancePayRegister
import com.jabait.util.EnglishNumberToWords
import org.apache.commons.lang.WordUtils

class ReportController {

    def jasperService;

    def index() { }

    def advanceVoucherRangeReport(){


        Map advancePayRegisterMap;

        DateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
        String voucherNumber = "advance_" + dateFormatter.format(new Date());

        Date fromDate = new Date().parse("yyyy-MM-dd",params.fromDate);
        Date toDate = new Date().parse("yyyy-MM-dd",params.toDate);

        List<AdvancePayRegister> advancePayRegisters = AdvancePayRegister.withCriteria{
            and{
                between("approvedDate", fromDate, toDate)
            }
            and{
                eq("advanceRequester.id", Employee.findByUserCode(params.userCode).id)
            }
            and{
                eq("status", 1)
            }
        }

        Double amount = 0.0;
        
        println("The ultimate size of " + advancePayRegisters.size())

        advancePayRegisters.each {
            advancePayRegister ->
            amount += advancePayRegister.advanceAmount
        }

        String amountWords = WordUtils.capitalize(EnglishNumberToWords.convert(amount.longValue()));
        println(amountWords)
        
        List vouchers = new ArrayList();
        Map voucherMap = new HashMap();
        voucherMap.put("particulars", Employee.findByUserCode(params.userCode)?.employeeProfile?.name?.firstName
        + " " +Employee.findByUserCode(params.userCode)?.employeeProfile?.name?.middleName
        + " " +Employee.findByUserCode(params.userCode)?.employeeProfile?.name?.surname);
        voucherMap.put("amount", amount);
        voucherMap.put("total", amount);
        voucherMap.put("voucherNumber", voucherNumber);
        voucherMap.put("amountWords", amountWords);
        voucherMap.put("accountTitle", AdvancePayRegister.findByAdvanceRequester(Employee.
                findByUserCode(params.userCode)).account.accountHead.description);
        vouchers.add(voucherMap);




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
    }

    def payRegisterReport () {



        Date toJobDate = new Date().parse("yyyy-MM-dd",params.toJobDate);
        Date fromJobDate = new Date().parse("yyyy-MM-dd",params.fromJobDate);
        Map<String,Object> salarySheetMap;
        List salarySheet = new ArrayList();

        if (!params.userCode.toString().equals("") && !params.fromJobDate.toString().equals("") && !params.toJobDate.toString().equals("")){

            int i=5;
            int sl=1;
            int workingDay=26;
            Employee employee=Employee.findByUserCode(params.userCode.toString());

            PayRegister payRegister=PayRegister.findByEmployee(employee);



                salarySheetMap = new HashMap<String, Object>();

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

            println("present.toString() is"+present.toString());
            println("absent.toString() is"+absent.toString());
            println("late.toString() is :"+late.toString());
            println("remarkNull.toString() is :"+remarkNull.toString());
                totalLate=Math.floor(late/3);
                totalAbsent = (int)absent+totalLate;

                perDaySalary=Math.ceil((payRegister.employee.employeeProfile.getSalary())/26);

                Set<Allowance> allowances = payRegister.employee.getAllowances();
                double allowanceAmount = 0;


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
                    temp = joinDate.split(":");


                    joinDate = temp[0];
                    temp = joinDate.split("00");
                    joinDate = temp[0];
                    joinDate = joinDate.split(" ")[0]
                }




                i++;
                present = present + remarkNull + (late - totalLate);
                totalSalary = present*perDaySalary;

                salarySheetMap.put("name",payRegister?.employee?.employeeProfile?.name?.getFirstName()?.toString()+" "+payRegister?.employee?.employeeProfile?.name?.getMiddleName()?.toString()+" "+payRegister?.employee?.employeeProfile?.name?.getSurname()?.toString());
                salarySheetMap.put("jobTitle", payRegister?.employee?.employeeProfile?.getJobTitle()?.getJobTitleName()?.toString());
                salarySheetMap.put("joiningDate", payRegister?.employee?.employeeProfile?.getJoiningDate()?.toString());
                salarySheetMap.put("grantSalary", payRegister?.employee?.employeeProfile?.getSalary()?.toString());
                salarySheetMap.put("workingDay", workingDay);
                salarySheetMap.put("actualDay", present?.toString());
                salarySheetMap.put("totalSalary", totalSalary?.toString());
                salarySheetMap.put("lunch", allowanceAmount?.toString());
                salarySheetMap.put("grossSalary", grossSalary?.toString());
                salarySheetMap.put("advance", deductionAmount?.toString());
                salarySheetMap.put("payableSalary", netPayable?.toString());

                salarySheet.add(salarySheetMap);

            params.SUBREPORT_DIR = "${servletContext.getRealPath('/reports')}/"
            params.IMAGE_DIR = "${servletContext.getRealPath('/images')}/"

            //render salarySheet
            JasperReportDef reportDef = jasperService.buildReportDefinition(params, request.getLocale(), [data:salarySheet])

            if (!reportDef.fileFormat.inline && !reportDef.parameters._inline){
                response.setHeader("Content-disposition", "attachment; filename="+(reportDef.parameters._name ?: reportDef.name) + "." + reportDef.fileFormat.extension);
                response.contentType = reportDef.fileFormat.mimeTyp
                response.characterEncoding = "UTF-8"
                response.outputStream << reportDef.contentStream.toByteArray()
            }
            else{
                // Inline report (e.g. HTML)
                render(text: reportDef.contentStream, contentType: reportDef.fileFormat.mimeTyp, encoding: reportDef.parameters.encoding ? reportDef.parameters.encoding : 'UTF-8');
            }
        }
        else if(!params.toJobDate.toString().equals("") && !params.fromJobDate.toString().equals("")){
            int i=5;
            int sl=1;
            int workingDay=26;
            for(PayRegister payRegister:PayRegister.list()){
                salarySheetMap = new HashMap<String, Object>();

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


                List<AdvancePayRegister> advancePayRegisters = AdvancePayRegister.withCriteria{
                    and{
                        between("approvedDate", fromJobDate, toJobDate)
                    }
                    and{
                        eq("advanceRequester.id", payRegister.getEmployee().id)
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
                    temp = joinDate.split(":");


                    joinDate = temp[0];
                    temp = joinDate.split("00");
                    joinDate = temp[0];
                    joinDate = joinDate.split(" ")[0]
                }

                i++;
                
                present = present + remarkNull + (late - totalLate);
                totalSalary = present*perDaySalary;

                salarySheetMap.put("name",payRegister?.employee?.employeeProfile?.name?.getFirstName()?.toString()+" "+payRegister?.employee?.employeeProfile?.name?.getMiddleName()?.toString()+" "+payRegister?.employee?.employeeProfile?.name?.getSurname()?.toString());
                salarySheetMap.put("jobTitle", payRegister?.employee?.employeeProfile?.getJobTitle()?.getJobTitleName()?.toString());
                salarySheetMap.put("joiningDate",joinDate);
                salarySheetMap.put("grantSalary", payRegister?.employee?.employeeProfile?.getSalary()?.toString());
                salarySheetMap.put("workingDay", workingDay);
                salarySheetMap.put("actualDay", present?.toString());
                salarySheetMap.put("totalSalary", totalSalary?.toString());
                salarySheetMap.put("lunch", allowanceAmount?.toString());
                salarySheetMap.put("grossSalary", grossSalary?.toString());
                salarySheetMap.put("advance", deductionAmount?.toString());
                salarySheetMap.put("payableSalary", netPayable?.toString());

                salarySheet.add(salarySheetMap);

                }


            params.SUBREPORT_DIR = "${servletContext.getRealPath('/reports')}/"
            params.IMAGE_DIR = "${servletContext.getRealPath('/images')}/"

            //render salarySheet
            JasperReportDef reportDef = jasperService.buildReportDefinition(params, request.getLocale(), [data:salarySheet])

            DateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
            String fileName = dateFormatter.format(new Date());
            String filenamePdf = "report_pdf_" + fileName+ "_payroll";

            if (!reportDef.fileFormat.inline && !reportDef.parameters._inline){
                response.setHeader("Content-disposition", "attachment; filename="+ filenamePdf + "." + reportDef.fileFormat.extension);
                response.contentType = reportDef.fileFormat.mimeTyp
                response.characterEncoding = "UTF-8"
                response.outputStream << reportDef.contentStream.toByteArray()
            }
            else{

                render(text: reportDef.contentStream, contentType: reportDef.fileFormat.mimeTyp, encoding: reportDef.parameters.encoding ? reportDef.parameters.encoding : 'UTF-8');
            }
        }

    }

    def payRegisterExcelReport () {



        Date toJobDate = new Date().parse("yyyy-MM-dd",params.toJobDate);
        Date fromJobDate = new Date().parse("yyyy-MM-dd",params.fromJobDate);
        Map<String,Object> salarySheetMap;
        List salarySheet = new ArrayList();

        if (!params.userCode.toString().equals("") && !params.fromJobDate.toString().equals("") && !params.toJobDate.toString().equals("")){

            int i=5;
            int sl=1;
            int workingDay=26;
            Employee employee=Employee.findByUserCode(params.userCode.toString());

            PayRegister payRegister=PayRegister.findByEmployee(employee);



            salarySheetMap = new HashMap<String, Object>();

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

            println("present.toString() is"+present.toString());
            println("absent.toString() is"+absent.toString());
            println("late.toString() is :"+late.toString());
            println("remarkNull.toString() is :"+remarkNull.toString());
            totalLate=Math.floor(late/3);
            totalAbsent = (int)absent+totalLate;

            perDaySalary=Math.ceil((payRegister.employee.employeeProfile.getSalary())/26);

            Set<Allowance> allowances = payRegister.employee.getAllowances();
            double allowanceAmount = 0;

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
                temp = joinDate.split(":");


                joinDate = temp[0];
                temp = joinDate.split("00");
                joinDate = temp[0];
                joinDate = joinDate.split(" ")[0]
            }




            i++;
            present = present + remarkNull + (late - totalLate);
            totalSalary = present*perDaySalary;

            salarySheetMap.put("name",payRegister?.employee?.employeeProfile?.name?.getFirstName()?.toString()+" "+payRegister?.employee?.employeeProfile?.name?.getMiddleName()?.toString()+" "+payRegister?.employee?.employeeProfile?.name?.getSurname()?.toString());
            salarySheetMap.put("jobTitle", payRegister?.employee?.employeeProfile?.getJobTitle()?.getJobTitleName()?.toString());
            salarySheetMap.put("joiningDate", payRegister?.employee?.employeeProfile?.getJoiningDate()?.toString());
            salarySheetMap.put("grantSalary", payRegister?.employee?.employeeProfile?.getSalary()?.toString());
            salarySheetMap.put("workingDay", workingDay);
            salarySheetMap.put("actualDay", present?.toString());
            salarySheetMap.put("totalSalary", totalSalary?.toString());
            salarySheetMap.put("lunch", allowanceAmount?.toString());
            salarySheetMap.put("grossSalary", grossSalary?.toString());
            salarySheetMap.put("advance", deductionAmount?.toString());
            salarySheetMap.put("payableSalary", netPayable?.toString());
            salarySheetMap.put("signature", " ");

            salarySheet.add(salarySheetMap);

            params.SUBREPORT_DIR = "${servletContext.getRealPath('/reports')}/"
            params.IMAGE_DIR = "${servletContext.getRealPath('/images')}/"

            //render salarySheet
            JasperReportDef reportDef = jasperService.buildReportDefinition(params, request.getLocale(), [data:salarySheet])

            if (!reportDef.fileFormat.inline && !reportDef.parameters._inline){
                response.setHeader("Content-disposition", "attachment; filename="+(reportDef.parameters._name ?: reportDef.name) + "." + reportDef.fileFormat.extension);
                response.contentType = reportDef.fileFormat.mimeTyp
                response.characterEncoding = "UTF-8"
                response.outputStream << reportDef.contentStream.toByteArray()
            }
            else{
                // Inline report (e.g. HTML)
                render(text: reportDef.contentStream, contentType: reportDef.fileFormat.mimeTyp, encoding: reportDef.parameters.encoding ? reportDef.parameters.encoding : 'UTF-8');
            }
        }
        else if(!params.toJobDate.toString().equals("") && !params.fromJobDate.toString().equals("")){
            int i=5;
            int sl=1;
            int workingDay=26;
            for(PayRegister payRegister:PayRegister.list()){
                salarySheetMap = new HashMap<String, Object>();

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


                List<AdvancePayRegister> advancePayRegisters = AdvancePayRegister.withCriteria{
                    and{
                        between("approvedDate", fromJobDate, toJobDate)
                    }
                    and{
                        eq("advanceRequester.id", payRegister.getEmployee().id)
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
                    temp = joinDate.split(":");


                    joinDate = temp[0];
                    temp = joinDate.split("00");
                    joinDate = temp[0];
                    joinDate = joinDate.split(" ")[0]
                }

                i++;

                present = present + remarkNull + (late - totalLate);
                totalSalary = present*perDaySalary;
                String signature=" ";
                salarySheetMap.put("name",payRegister?.employee?.employeeProfile?.name?.getFirstName()?.toString()+" "+payRegister?.employee?.employeeProfile?.name?.getMiddleName()?.toString()+" "+payRegister?.employee?.employeeProfile?.name?.getSurname()?.toString());
                salarySheetMap.put("jobTitle", payRegister?.employee?.employeeProfile?.getJobTitle()?.getJobTitleName()?.toString());
                salarySheetMap.put("joiningDate",joinDate);
                salarySheetMap.put("grantSalary", payRegister?.employee?.employeeProfile?.getSalary()?.toString());
                salarySheetMap.put("workingDay", workingDay);
                salarySheetMap.put("actualDay", present?.toString());
                salarySheetMap.put("totalSalary", totalSalary?.toString());
                salarySheetMap.put("lunch", allowanceAmount?.toString());
                salarySheetMap.put("grossSalary", grossSalary?.toString());
                salarySheetMap.put("advance", deductionAmount?.toString());
                salarySheetMap.put("payableSalary", netPayable?.toString());
                salarySheetMap.put("signature", signature);

                salarySheet.add(salarySheetMap);

            }


            params.SUBREPORT_DIR = "${servletContext.getRealPath('/reports')}/"
            params.IMAGE_DIR = "${servletContext.getRealPath('/images')}/"

            //render salarySheet
            JasperReportDef reportDef = jasperService.buildReportDefinition(params, request.getLocale(), [data:salarySheet])

            DateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
            String fileName = dateFormatter.format(new Date());
            String filenameXls = "report_xls_" + fileName+ "_payroll";

            if (!reportDef.fileFormat.inline && !reportDef.parameters._inline){
                println("reportDef.fileFormat.extension " + reportDef.fileFormat.extension)
                response.setHeader("Content-disposition", "attachment; filename="+ filenameXls + "." + reportDef.fileFormat.extension);
                response.contentType = reportDef.fileFormat.mimeTyp
                println("response.contentType  " + response.contentType)
                response.characterEncoding = "UTF-8"
                response.outputStream << reportDef.contentStream.toByteArray()
            }
            else{

                render(text: reportDef.contentStream, contentType: reportDef.fileFormat.mimeTyp, encoding: reportDef.parameters.encoding ? reportDef.parameters.encoding : 'UTF-8');
            }
        }

    }


    def attendanceRegisterExcelReport () {



        Date toJobDate = new Date().parse("yyyy-MM-dd",params.toJobDate);
        Date fromJobDate = new Date().parse("yyyy-MM-dd",params.fromJobDate);
        Map<String,Object> salarySheetMap;
        List salarySheet = new ArrayList();

//        if (!params.userCode.toString().equals("") && !params.fromJobDate.toString().equals("") && !params.toJobDate.toString().equals("")){
//
//            int i=5;
//            int sl=1;
//            int workingDay=26;
//            Employee employee=Employee.findByUserCode(params.userCode.toString());
//
//            PayRegister payRegister=PayRegister.findByEmployee(employee);
//
//
//
//            salarySheetMap = new HashMap<String, Object>();
//
//            int present = 0
//            int absent = 0
//            int late = 0;
//            int remarkNull=0;
//            int totalPresent=0;
//            int totalLate=0;
//            int totalAbsent=0;
//            Double perDaySalary=0.00,grossSalary=0.00,totalSalary=0.00;
//            Double netPayable=0.00;
//
//            present = Integer.parseInt(AttendanceRegister.withCriteria{
//                and{
//                    between("jobDate", fromJobDate, toJobDate)
//                }
//                and{
//                    eq("attendant.id", payRegister.employee.id)
//                }
//                and{
//                    eq("remark", "present")
//                }
//            }.size().toString());
//            absent = Integer.parseInt(AttendanceRegister.withCriteria{
//                and{
//                    between("jobDate", fromJobDate, toJobDate)
//                }
//                and{
//                    eq("attendant.id", payRegister.employee.id)
//                }
//                and{
//                    eq("remark", "absent")
//                }
//            }.size().toString());
//            late = Integer.parseInt(AttendanceRegister.withCriteria{
//                and{
//                    between("jobDate", fromJobDate, toJobDate)
//                }
//                and{
//                    eq("attendant.id", payRegister.employee.id)
//                }
//                and{
//                    eq("remark", "late")
//                }
//            }.size().toString());
//            remarkNull=Integer.parseInt(AttendanceRegister.withCriteria{
//                and{
//                    between("jobDate", fromJobDate, toJobDate)
//                }
//                and{
//                    eq("attendant.id", payRegister.employee.id)
//                }
//                and{
//                    eq("remark", " ")
//                }
//            }.size().toString());
//
//            println("present.toString() is"+present.toString());
//            println("absent.toString() is"+absent.toString());
//            println("late.toString() is :"+late.toString());
//            println("remarkNull.toString() is :"+remarkNull.toString());
//            totalLate=Math.floor(late/3);
//            totalAbsent = (int)absent+totalLate;
//
//            perDaySalary=Math.ceil((payRegister.employee.employeeProfile.getSalary())/26);
//
//            Set<Allowance> allowances = payRegister.employee.getAllowances();
//            double allowanceAmount = 0;
//
//
//
//            List<Deductions> deductions=Deductions.findAllByDeductionDateBetweenAndPayee(fromJobDate,toJobDate,payRegister.employee);
//            Double deductionAmount=0.00;
//            for (Deductions deduction : deductions) {
//                deductionAmount+=deduction.getAmount();
//
//            }
//            println("deductionAmount is:"+deductionAmount);
//
//            for(Allowance allowance : allowances){
//                allowanceAmount += allowance.allowanceAmount;
//            }
//
//
//            grossSalary=(perDaySalary*present)+allowanceAmount+((late-totalLate)*perDaySalary)+(remarkNull*perDaySalary);
//            println("late-totalLate is:"+late-totalLate+"payRegister.employee.getId()"+payRegister.employee.getId()+"remarkNull is :"+remarkNull);
//
//            netPayable = grossSalary - deductionAmount;
//
//            println("netPayable is :"+netPayable);
//            String joinDate = "";
//
//            joinDate = payRegister?.employee?.employeeProfile?.joiningDate?.toString();
//
//            if (joinDate != null){
//                String[] temp;
//                temp = joinDate.split(":");
//
//
//                joinDate = temp[0];
//                temp = joinDate.split("00");
//                joinDate = temp[0];
//                joinDate = joinDate.split(" ")[0]
//            }
//
//
//
//
//            i++;
//            present = present + remarkNull + (late - totalLate);
//            totalSalary = present*perDaySalary;
//
//            salarySheetMap.put("name",payRegister?.employee?.employeeProfile?.name?.getFirstName()?.toString()+" "+payRegister?.employee?.employeeProfile?.name?.getMiddleName()?.toString()+" "+payRegister?.employee?.employeeProfile?.name?.getSurname()?.toString());
//            salarySheetMap.put("jobTitle", payRegister?.employee?.employeeProfile?.getJobTitle()?.getJobTitleName()?.toString());
//            salarySheetMap.put("joiningDate", payRegister?.employee?.employeeProfile?.getJoiningDate()?.toString());
//            salarySheetMap.put("grantSalary", payRegister?.employee?.employeeProfile?.getSalary()?.toString());
//            salarySheetMap.put("workingDay", workingDay);
//            salarySheetMap.put("actualDay", present?.toString());
//            salarySheetMap.put("totalSalary", totalSalary?.toString());
//            salarySheetMap.put("lunch", allowanceAmount?.toString());
//            salarySheetMap.put("grossSalary", grossSalary?.toString());
//            salarySheetMap.put("advance", deductionAmount?.toString());
//            salarySheetMap.put("payableSalary", netPayable?.toString());
//
//            salarySheet.add(salarySheetMap);
//
//            params.SUBREPORT_DIR = "${servletContext.getRealPath('/reports')}/"
//            params.IMAGE_DIR = "${servletContext.getRealPath('/images')}/"
//
//            //render salarySheet
//            JasperReportDef reportDef = jasperService.buildReportDefinition(params, request.getLocale(), [data:salarySheet])
//
//            if (!reportDef.fileFormat.inline && !reportDef.parameters._inline){
//                response.setHeader("Content-disposition", "attachment; filename="+(reportDef.parameters._name ?: reportDef.name) + "." + reportDef.fileFormat.extension);
//                response.contentType = reportDef.fileFormat.mimeTyp
//                response.characterEncoding = "UTF-8"
//                response.outputStream << reportDef.contentStream.toByteArray()
//            }
//            else{
//                // Inline report (e.g. HTML)
//                render(text: reportDef.contentStream, contentType: reportDef.fileFormat.mimeTyp, encoding: reportDef.parameters.encoding ? reportDef.parameters.encoding : 'UTF-8');
//            }
//        }
//        else if(!params.toJobDate.toString().equals("") && !params.fromJobDate.toString().equals("")){
//            int i=5;
//            int sl=1;
//            int workingDay=26;
//            for(PayRegister payRegister:PayRegister.list()){
//                salarySheetMap = new HashMap<String, Object>();
//
//                int present = 0
//                int absent = 0
//                int late = 0;
//                int remarkNull=0;
//                int totalPresent=0;
//                int totalLate=0;
//                int totalAbsent=0;
//                Double perDaySalary=0.00,grossSalary=0.00,totalSalary=0.00;
//                Double netPayable=0.00;
//
//                present = Integer.parseInt(AttendanceRegister.withCriteria{
//                    and{
//                        between("jobDate", fromJobDate, toJobDate)
//                    }
//                    and{
//                        eq("attendant.id", payRegister.employee.id)
//                    }
//                    and{
//                        eq("remark", "present")
//                    }
//                }.size().toString());
//                absent = Integer.parseInt(AttendanceRegister.withCriteria{
//                    and{
//                        between("jobDate", fromJobDate, toJobDate)
//                    }
//                    and{
//                        eq("attendant.id", payRegister.employee.id)
//                    }
//                    and{
//                        eq("remark", "absent")
//                    }
//                }.size().toString());
//                late = Integer.parseInt(AttendanceRegister.withCriteria{
//                    and{
//                        between("jobDate", fromJobDate, toJobDate)
//                    }
//                    and{
//                        eq("attendant.id", payRegister.employee.id)
//                    }
//                    and{
//                        eq("remark", "late")
//                    }
//                }.size().toString());
//                remarkNull=Integer.parseInt(AttendanceRegister.withCriteria{
//                    and{
//                        between("jobDate", fromJobDate, toJobDate)
//                    }
//                    and{
//                        eq("attendant.id", payRegister.employee.id)
//                    }
//                    and{
//                        eq("remark", " ")
//                    }
//                }.size().toString());
//                totalLate=Math.floor(late/3);
//                totalAbsent = (int)absent+totalLate;
//
//
//                perDaySalary=Math.ceil((payRegister.employee.employeeProfile.getSalary())/26);
//
//                Set<Allowance> allowances = payRegister.employee.getAllowances();
//                double allowanceAmount = 0;
//
//
//
//                List<Deductions> deductions=Deductions.findAllByDeductionDateBetweenAndPayee(fromJobDate,toJobDate,payRegister.employee);
//                Double deductionAmount=0.00;
//                for (Deductions deduction : deductions) {
//                    deductionAmount+=deduction.getAmount();
//
//                }
//                println("deductionAmount is:"+deductionAmount);
//
//                for(Allowance allowance : allowances){
//                    allowanceAmount += allowance.allowanceAmount;
//                }
//
//
//                grossSalary=(perDaySalary*present)+allowanceAmount+((late-totalLate)*perDaySalary)+(remarkNull*perDaySalary);
//                println("late-totalLate is:"+late-totalLate+"payRegister.employee.getId()"+payRegister.employee.getId()+"remarkNull is :"+remarkNull);
//
//                netPayable = grossSalary - deductionAmount;
//
//                println("netPayable is :"+netPayable);
//                String joinDate = "";
//
//                joinDate = payRegister?.employee?.employeeProfile?.joiningDate?.toString();
//
//                if (joinDate != null){
//                    String[] temp;
//                    temp = joinDate.split(":");
//
//
//                    joinDate = temp[0];
//                    temp = joinDate.split("00");
//                    joinDate = temp[0];
//                    joinDate = joinDate.split(" ")[0]
//                }
//
//                i++;
//
//                present = present + remarkNull + (late - totalLate);
//                totalSalary = present*perDaySalary;
//
//                salarySheetMap.put("name",payRegister?.employee?.employeeProfile?.name?.getFirstName()?.toString()+" "+payRegister?.employee?.employeeProfile?.name?.getMiddleName()?.toString()+" "+payRegister?.employee?.employeeProfile?.name?.getSurname()?.toString());
//                salarySheetMap.put("jobTitle", payRegister?.employee?.employeeProfile?.getJobTitle()?.getJobTitleName()?.toString());
//                salarySheetMap.put("joiningDate",joinDate);
//                salarySheetMap.put("grantSalary", payRegister?.employee?.employeeProfile?.getSalary()?.toString());
//                salarySheetMap.put("workingDay", workingDay);
//                salarySheetMap.put("actualDay", present?.toString());
//                salarySheetMap.put("totalSalary", totalSalary?.toString());
//                salarySheetMap.put("lunch", allowanceAmount?.toString());
//                salarySheetMap.put("grossSalary", grossSalary?.toString());
//                salarySheetMap.put("advance", deductionAmount?.toString());
//                salarySheetMap.put("payableSalary", netPayable?.toString());
//
//                salarySheet.add(salarySheetMap);
//
//            }



            int i=4;
            Long duration=0;
            int hours=0;
            int minute=0;

            String inTime="";
            String outTime="";
            Date outTimes=null;
            for(AttendanceRegister attendanceRegister : AttendanceRegister.list()){
                salarySheetMap = new HashMap<String, Object>();
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
                jobDate = jobDate.split(" ")[0]

                salarySheetMap.put("userCode", attendanceRegister?.attendant?.userCode?.toString());
                salarySheetMap.put("employeeName", attendanceRegister?.attendant?.employeeProfile?.name?.getFirstName()?.toString()+" "+attendanceRegister?.attendant?.employeeProfile?.name?.getMiddleName()?.toString()+" "+attendanceRegister?.attendant?.employeeProfile?.name?.getSurname()?.toString());
                salarySheetMap.put("jobDate", jobDate.toString());
                salarySheetMap.put("inTime", attendanceRegister?.inTime?.getHours()?.toString()+":"+attendanceRegister?.inTime?.getMinutes()?.toString()+":"+attendanceRegister?.inTime?.getSeconds()?.toString());
                salarySheetMap.put("outTime", outTimes?.getTimeString());
                salarySheetMap.put("duration", hours+":"+minute);
                salarySheetMap.put("remark", attendanceRegister?.remark?.toString());

                salarySheet.add(salarySheetMap);


//                HSSFRow row1 = worksheet.createRow((short) i);

//                i++;
//
//                for (int j=0;j<7;j++){
//
//                    println("outTimes is :"+outTimes?.getTimeString());
//                    HSSFCell cell8=row1.createCell((short) 0).setCellValue(attendanceRegister?.attendant?.userCode?.toString());
//                    HSSFCell cell9=row1.createCell((short) 1).setCellValue(attendanceRegister?.attendant?.employeeProfile?.name?.getFirstName()?.toString()+" "+attendanceRegister?.attendant?.employeeProfile?.name?.getMiddleName()?.toString()+" "+attendanceRegister?.attendant?.employeeProfile?.name?.getSurname()?.toString());
//                    HSSFCell cell10=row1.createCell((short) 2).setCellValue(jobDate.toString());
//                    HSSFCell cell11=row1.createCell((short) 3).setCellValue(attendanceRegister?.inTime?.getHours()?.toString()+":"+attendanceRegister?.inTime?.getMinutes()?.toString()+":"+attendanceRegister?.inTime?.getSeconds()?.toString());
//                    HSSFCell cell12=row1.createCell((short) 4).setCellValue(outTimes?.getTimeString());
//                    HSSFCell cell13=row1.createCell((short) 5).setCellValue(hours+":"+minute);
//                    HSSFCell cell14=row1.createCell((short) 6).setCellValue(attendanceRegister?.remark?.toString());
//
//
//                }

            }


            params.SUBREPORT_DIR = "${servletContext.getRealPath('/reports')}/"
            params.IMAGE_DIR = "${servletContext.getRealPath('/images')}/"

            //render salarySheet
            JasperReportDef reportDef = jasperService.buildReportDefinition(params, request.getLocale(), [data:salarySheet])

            DateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
            String fileName = dateFormatter.format(new Date());
            String filenameXls = "report_pdf_" + fileName+ "_payroll";

            if (!reportDef.fileFormat.inline && !reportDef.parameters._inline){
                response.setHeader("Content-disposition", "attachment; filename="+ filenameXls + "." + reportDef.fileFormat.extension);
                response.contentType = reportDef.fileFormat.mimeTyp
                response.characterEncoding = "UTF-8"
                response.outputStream << reportDef.contentStream.toByteArray()
            }
            else{

                render(text: reportDef.contentStream, contentType: reportDef.fileFormat.mimeTyp, encoding: reportDef.parameters.encoding ? reportDef.parameters.encoding : 'UTF-8');
            }
        //}

    }


}
