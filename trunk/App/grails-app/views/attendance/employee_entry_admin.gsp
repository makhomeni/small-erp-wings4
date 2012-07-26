<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/20/12
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.jabait.hrm.Employee"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<g:applyLayout name="app">
    <head>
        <g:set var="entityName" value="${message(code:'attendance.description', default: 'Attendance')}"/>
        <title><g:message code="default.create.label" args="[entityName]"/></title>
        <script type="text/javascript" src="${resource(dir: 'js', file: 'livevalidation_standalone.compressed.js')}">

        </script>

        <script type="text/javascript" src="../js/jscal2.js"></script>
        <script type="text/javascript" src="../js/en.js"></script>
        %{--<link rel="stylesheet" type="text/css" href="../css/border-radius.css" />--}%
        %{--<link rel="stylesheet" type="text/css" href="../css/jscal2.css">--}%
        <link rel="stylesheet" type="text/css" href="../css/steel.css">
    </head>
    <body>

    <content tag="rightTag">



        <script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.uniform.js')}" ></script>
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'uniform.aristo.css')}">
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'jquery.ptTimeSelect.css')}">
        <script type="text/javascript" src="${resource(dir: 'js', file: 'livevalidation_standalone.compressed.js')}"></script>
        <script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.ptTimeSelect.js')}" ></script>

        <style>
        div.selector{
            margin-top: -20px;
            margin-left: 156px;
        }
        </style>
        <script>
            $(document).ready(function(){

                $("input, textarea, select, button").uniform();

            })

            function submitForm(){
                document.adminPrivilegedAttendanceForm.submit();
            }


            function isNumberKey(evt)
            {
                var charCode = (evt.which) ? evt.which : event.keyCode
                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;

                return true;
            }
        </script>

    <div class="widget">

        <div class="header" style="width: 610px;">
            <span><span class="ico gray department"></span>${type}</span>
        </div>

        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <g:form name="adminPrivilegedAttendanceForm" id="adminPrivilegedAttendanceForm" controller="attendance" action="saveAdminPrivilegedAttendance" method="POST">
            <fieldset class="form">
                %{--<g:render template="organization_form"/>--}%

                <div class="fieldcontain ${hasErrors(bean: employeeInstance, field: 'employeeName', 'error')} required">
                    <label for="employeeName">
                        <g:message code="employeename.label" default="Employee Code" />
                        <span class="required-indicator">*</span>
                    </label>


                    <g:select id="attendanceList" name="employeeId" from="${Employee.list()}" optionKey="id" optionValue="userCode" noSelection="['':'Employee Name']"></g:select>

                </div>


                <div class="fieldcontain ${hasErrors(bean: employeeInstance, field: 'adminId', 'error')} required">
                    <label for="adminId">
                        <g:message code="adminId.label" default="Admin Id" />
                        <span class="required-indicator">*</span>
                    </label>
                    <g:textField name="adminId" required=""  value="" />

                </div>


                <div class="fieldcontain ${hasErrors(bean: employeeInstance, field: 'adminPassword', 'error')} required">
                    <label for="adminPassword">
                        <g:message code="adminPassword.label" default="Admin Password" />
                        <span class="required-indicator">*</span>
                    </label>
                    <g:passwordField name="adminPassword" required=""  value="" />

                </div>


                <div class="fieldcontain ${hasErrors(bean: employeeInstance, field: 'time', 'error')} required">
                    <label for="time">
                        <g:message code="time.label" default="Time" />
                        <span class="required-indicator">*</span>
                    </label>
                    <g:textField name="time" required=""  value="" onkeypress="return isNumberKey(event);"/>

                </div>

                %{--<img src="${resource(dir: 'images/calendar', file: 'calendar_add.png')}" alt="calender" id="showCalendarExpireDate" class="adjustImg" title="Click to select date">--}%
                %{--<script type="text/javascript">--}%
                    %{--//<![CDATA[--}%
                    %{--Calendar.setup({--}%
                        %{--inputField : "time",--}%
                        %{--trigger    : "showCalendarExpireDate",--}%
                        %{--onSelect   : function() { this.hide() },--}%
                        %{--showTime   : 12,--}%
                        %{--dateFormat : "%H:%m:%S"//this is a timestamp format--}%

                    %{--});--}%
                    %{--//]]>--}%
                %{--</script>--}%


                <br class="clear"/>
                <fieldset class="grid_8">
                    <div class="grid_4 alpha">&nbsp;&nbsp;</div>


                    <div class="grid_4 omega" style="padding-left: 194px;padding-top: 10px; padding-bottom: 10px"; >




                    <a id="adminPrivilegedAttendanceSave" class="button icon approve" onclick="submitForm()">Save</a>
                    <a id="ff" class="button danger icon remove" onclick="document.adminPrivilegedAttendanceForm.reset()">Clear</a>




           </div>

            </fieldset>

        </g:form>

        <script>
            $(document).ready(function(){
                $("#time").ptTimeSelect()
            })
        </script>
    </content>
    </body>
</g:applyLayout>