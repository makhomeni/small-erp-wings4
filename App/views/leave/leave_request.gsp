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
            <li><g:link controller="leave" action="leaveList">Leave List</g:link></li>
            <li>Leave Request</li>
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
            <span><span class="ico gray leaveRequest"></span>&nbsp;&nbsp;Leave Request</span>
        </div>
        <g:set var="userName" value="${session?.user?.userCode}"></g:set>

        %{--alert(${session?.user?.userCode})--}%
       <div class="content tableName" style="height: 520px;">
           <g:form name="leaveRequestForm" controller="leave" action="saveLeaveRequest">
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

               <div class="grid_8 label">
                   <div class="grid_3 alpha msg_text">
                       <g:message code="leave.type.label" default="Leave Type"></g:message>
                   </div>
                   <div class="grid_4 omega">
                       <g:select id="leaveType" name="leaveType" from="${Leave.list()}" optionKey="id" optionValue="leaveType" noSelection="['':'Select Leave Type...']"></g:select>

                   </div>
               </div>
               <div class="grid_8 label">
                   <div class="grid_3 alpha msg_text">
                       <g:message code="leave.from.label" default="Leave From"></g:message>
                   </div>
                   <div class="grid_4 omega">
                       <g:textField name="leaveFrom" readonly="readonly" id="leaveFrom"></g:textField>
                   </div>
                   <img src="${resource(dir: 'images/calendar', file: 'calendar_add.png')}" alt="calender" id="showCalendarDateOfJoin" class="adjustImg" title="Click to select date of Join">
                   <script type="text/javascript">
                       //<![CDATA[
                       var toDate;
                       var fromDate = Calendar.setup({
                           inputField : "leaveFrom",
                           trigger    : "showCalendarDateOfJoin",
                           dateFormat : "%Y-%m-%d",
                           min: new Date(),
                           showTime: false,
                           onSelect: function() {
                               this.hide();
                           }
                       });
                       //]]>
                   </script>
               </div>
               <div class="grid_8 label">
                   <div class="grid_3 alpha msg_text">
                       <g:message code="leave.to.label" default="To"></g:message>
                   </div>
                   <div class="grid_4 omega">
                       <g:textField name="leaveTo" id="leaveTo"></g:textField>
                   </div>
                   <img src="${resource(dir: 'images/calendar', file: 'calendar_add.png')}" alt="calender" id="showCalendarLeaveTo" class="adjustImg" title="Click to select date of Join">
                   <script type="text/javascript">
                       //<![CDATA[
                       toDate = Calendar.setup({
                           inputField : "leaveTo",
                           trigger    : "showCalendarLeaveTo",
                           onSelect   : function() { this.hide() },
                           showTime   : false,
                           dateFormat : "%Y-%m-%d",
                           min:  new Date()
                       });
                       //]]>
                   </script>
               </div>

               <div class="grid_8 label">
                   <div class="grid_3 alpha msg_text">
                       <g:message code="leave.purpose.label" default="Purpose"></g:message>
                   </div>
                   <div class="grid_4 omega">
                       %{--<g:textField name="leaveType"></g:textField>--}%
                       <g:checkBox name="leavePurpose" value="${false}"></g:checkBox>&nbsp;Illness/Injury/Incapitation
                       <br />
                       <br />
                       <g:checkBox name="leavePurpose" value="${false}"></g:checkBox>&nbsp;Medical/Dental/Optical Exam
                       <br />
                       <br />
                       <g:checkBox name="leavePurpose" value="${false}"></g:checkBox>&nbsp;Care of familly member
                       <br />
                       <br />
                       <g:checkBox name="leavePurpose" value="${false}"></g:checkBox>&nbsp;Other
                   </div>


               </div>

               <div class="grid_8 label">
                   <div class="grid_3 alpha msg_text">
                       <g:message code="remark.label" default="Purpose"></g:message>
                   </div>
                   <div class="grid_4 omega">
                       %{--<g:textArea name="leaveType"></g:textArea>--}%
                       <g:textArea name="remark" rows="5" cols="10"></g:textArea>
                   </div>
               </div>
           </g:form>
           <br class="clear" />
           <br class="clear" />

           <div class="grid_8" style="margin-left: 200px;">
               <a id="leaveRequestSave" class="button icon approve" onclick="submitForm()">Save</a>
               <a id="clearForm" class="button danger icon remove" onclick="document.leaveRequestForm.reset()">Clear</a>
           </div>


       </div>

        %{----}%
        <script type="text/javascript">
            function submitForm(){
//                alert(1);
                document.leaveRequestForm.submit();
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