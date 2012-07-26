<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/20/12
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.jabait.hrm.time.Leave; com.jabait.security.Authority"%>
<%@ page import="com.jabait.security.UserGroup"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<g:applyLayout name="app">
<head>
<title>${titleOfPage}</title>
<style type="text/css">
.label{
    padding: 15px 10px!important;
}
.label:hover{
    background-color:#eaf7ff;
}
.adjustImg{
    margin-left: -6px;
}

</style>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'jscal2.js')}"></script>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'en.js')}"></script>

    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'border-radius.css')}"/>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'jscal2.css')}"/>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'steel.css')}"/>

</head>

<body>
<content tag="titleTag">

</content>

<content tag="rightTag">

    <div class="bread_crumbs_ui_div" style="width: 520px">
        <ul id="crumbs_ui_custom">
            <li><g:link controller="application" action="index">Dashboard</g:link></li>
            <li><g:link controller="leave" action="leave">Leave Home</g:link></li>
            <li><g:link controller="leave" action="waiverList">Weaver List</g:link></li>
            <li>Weaver Request</li>
        </ul>
    </div>

    <br class="clear"/>


    <g:if test="${flash.message}">
        <div class="message" role="status" style="width: 518px; margin-left: 0px;">${flash.message}</div>
    </g:if>

<div class="container_16">
<div class="grid_9 alpha">

    <div class="widget">



        <div class="header">
            <span><span class="ico gray leaveRequest"></span>&nbsp;&nbsp;Waiver Request</span>
        </div>

        <g:set var="userName" value="${session?.user?.userCode}"></g:set>
       <div class="content tableName" style="height: 302px;">
           <g:form name="leaveWaiverForm" controller="leave" action="saveWaiverRequest">

               <g:if test="${userName.equals("admin")}">
                   <div class="grid_8 label">
                       <div class="grid_3 alpha msg_text">
                           <g:message code="leave.employee.id" default="Employee Id"></g:message>
                       </div>
                       <div class="grid_4 omega">

                           <g:textField name="employeeId" id="employeeId"></g:textField>

                       </div>
                   </div>
               </g:if>

               <g:hiddenField name="loginUserId" id="loginUserId" value="${session?.user?.userCode}"></g:hiddenField>
                <g:hiddenField name="waiverType" id="waiverType" value="0"></g:hiddenField>

               <div class="grid_8 label">

                   <div class="grid_3 alpha msg_text">
                       <g:message code="waiver.from.label" default="Waiver Date"></g:message>
                   </div>

                   <div class="grid_4 omega">
                       <g:textField name="waiverDate" readonly="readonly" id="waiverDate" style="width: 62px"></g:textField>
                       <g:textField name="waiverTimeHour" id="waiverTimeHour" readonly="" style="width: 40px"></g:textField>
                       : <g:textField name="waiverTimeMin" id="waiverTimeMin" readonly="" style="width: 40px"></g:textField>
                   </div>

                   <img src="${resource(dir: 'images/calendar', file: 'calendar_add.png')}" alt="calender" id="showCalendarDateOfJoin" class="adjustImg" title="Click to select date of Join">

                   <script type="text/javascript">
                       //<![CDATA[
                       var waiverCalendar = Calendar.setup({
                           inputField : "waiverDate",
                           trigger    : "showCalendarDateOfJoin",
                           onSelect   : updateFields,
                           showTime   : 12,
                           dateFormat : "%Y-%m-%d %H:%m:%S",
                           max: new Date()
                       });

                       function updateFields(cal) {
                           var date = cal.selection.get();
                           if (date) {
                               date = Calendar.intToDate(date);
                               document.getElementById("waiverDate").value = Calendar.printDate(date, "%Y-%m-%d");
                           }
                           document.getElementById("waiverTimeHour").value = cal.getHours();
                           document.getElementById("waiverTimeMin").value = cal.getMinutes();
                       };

//                       waiverCalendar.args.max = new Date();
//
//                       Calendar.setup({
//                           trigger    : "showCalendarDateOfJoin",
//                           showTime     : 12,
//                           onSelect     : updateFields,
//                           onTimeChange : updateFields
//                       });


                       //]]>
                   </script>
               </div>

               <div class="grid_8 label">
                   <div class="grid_3 alpha msg_text">
                       <g:message code="reason.label" default="Reason"></g:message>
                   </div>
                   <div class="grid_4 omega">
                       %{--<g:textArea name="leaveType"></g:textArea>--}%
                       <g:textArea name="reason" rows="5" cols="10"></g:textArea>
                   </div>
               </div>

           </g:form>
           <br class="clear" />
           <br class="clear" />

           <div class="grid_8" style="margin-left: 200px;">
               <a id="leaveRequestSave" class="button icon approve" onclick="submitForm()">Save</a>
               <a id="clearForm" class="button danger icon remove" onclick="document.leaveWaiverForm.reset()">Clear</a>
           </div>


       </div>

        %{----}%
        <script type="text/javascript">
            function submitForm(){
//                alert(1);
                document.leaveWaiverForm.submit();
            }

        </script>
        <script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.uniform.js')}" ></script>

        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'uniform.aristo.css')}">
        <script>
            $(document).ready(function(){
                $("input, textarea, select, button").uniform();
            })
        </script>




    </div><!--end widget -->
</div><!--end left column -->

%{--left column--}%

%{--<div class="grid_7 omega">--}%

    %{--<div class="widget">--}%

        %{--<div class="header">--}%
            %{--<span><span class="ico gray organization"></span>Organization Information</span>--}%
            %{--<span style="margin-left: 140px;"><a id="editOrganizationInfo"><img id="editOrganizationInfoImg" src="${resource(dir:'images',file: 'edit.png')}" alt="Edit" /></a></span>--}%
        %{--</div>--}%


    %{--</div><!--end widget -->--}%
%{--</div><!--end left column -->--}%

<br class="clear">



</content>
</body>

</g:applyLayout>