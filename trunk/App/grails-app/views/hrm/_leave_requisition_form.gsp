<%--
  Created by IntelliJ IDEA.
  User: Md Dablu Hossain
  Date: 5/29/12
  Time: 10:58 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.jabait.security.User" %>
<%@ page import="com.jabait.hrm.Organization"%>
<div class="container_16" style="width: 701px;">

<div style="padding-top: 15px">

<div class="grid_6 alpha leftElement">
    %{--This is Employee Information Box--}%
    <fieldset class="user-profile-fieldset" style="width: 350px">
        <legend class="legend-color">Employee Info</legend>

        %{--username--}%
        <div style="padding-top: 5px">

        </div>

        <div class="grid_2" style="padding-top: 5px;">
            <label for="username">
                <g:message code="username.label" default="User Name" />
                <span class="required-indicator">*</span>
            </label>
        </div>
        <div class="grid_3">
            <g:select id="organizationList" name="username" from="${Organization.list()}" optionKey="id" optionValue="organizationName" noSelection="['':'Select Organization...']"></g:select>

            %{--<g:textField name="username" id="username" required="" value=""/>--}%
        </div>

        <br class="clear" />
        <br class="clear" />

        %{--password--}%


    </fieldset>

</div>
%{--This the Organization Section--}%
<div class="grid_6 omega rightElement" >

    <fieldset class="user-profile-fieldset" style="width: 350px">
        <legend class="legend-color">Organization</legend>

        <div style="padding-top: 8px">

        </div>

        %{--Organization--}%

        <div class="grid_2" style="padding-top: 5px;">
            <label for="Organization">
                <g:message code="organization.label" default="Organization" />
                <span class="required-indicator">*</span>
            </label>
        </div>

        <div class="grid_3">
            <g:textField name="Organization" required="" value=""/>
        </div>
        %{--<div class="grid_3">--}%
            %{--<g:textField id="organization" name="organization" required="" readonly="readonly" value=""/>--}%
            %{--<g:hiddenField name="organizationId" id="organizationId"></g:hiddenField>--}%
        %{--</div>--}%

        %{--<a id="basic-modal" href="#">--}%
            %{--<img src="${resource(dir: 'images', file: 'organization_picker.png')}" alt="Organization Image" class="adjustImg" title="Click to select organization">--}%
        %{--</a>--}%



        %{--<div id="basic-modal-content" >--}%

        %{--</div>--}%




        %{--<br class="clear" />--}%
        %{--<br class="clear" />--}%


        %{--<div class="grid_2" style="padding-top: 5px;">--}%
            %{--<label for="Department">--}%
                %{--<g:message code="employee.username.label" default="Department" />--}%
                %{--<span class="required-indicator">*</span>--}%
            %{--</label>--}%
        %{--</div>--}%
        %{--<div class="grid_3">--}%
            %{--<g:textField id="department" name="department" required="" value="" readonly="readonly" />--}%
            %{--<g:hiddenField name="departmentId" id="departmentId"></g:hiddenField>--}%
        %{--</div>--}%
        %{--<a id="basic-modal-department" href="#" style="display: none;">--}%
            %{--<img src="${resource(dir: 'images', file: 'department.png')}" alt="Department Image" class="adjustImg" title="Click to select department">--}%
        %{--</a>--}%



        %{--<div id="basic-modal-content-department" >--}%

        %{--</div>--}%

        %{--<br class="clear" />--}%
        %{--<br class="clear" />--}%

        %{--section--}%

        %{--<div class="grid_2" style="padding-top: 5px;">--}%
            %{--<label for="Section">--}%
                %{--<g:message code="employee.username.label" default="Section" />--}%
                %{--<span class="required-indicator">*</span>--}%
            %{--</label>--}%
        %{--</div>--}%
        %{--<div class="grid_3">--}%
            %{--<g:textField name="section" required="" value="" readonly="readonly"/>--}%
            %{--<g:hiddenField name="sectionId" id="sectionId"></g:hiddenField>--}%
        %{--</div>--}%

        %{--<a id="basic-modal-section" href="#" style="display: none;" >--}%
            %{--<img src="${resource(dir: 'images/assets/icons', file: 'section.png')}" alt="Section Image" class="adjustImg" title="Click to select section">--}%
        %{--</a>--}%

        %{--<div id="basic-modal-content-section" >--}%

        %{--</div>--}%

        %{--<br class="clear" />--}%
        %{--<br class="clear" />--}%

        %{--region--}%
        %{--<div class="grid_2" style="padding-top: 5px;">--}%
            %{--<label for="Region">--}%
                %{--<g:message code="employee.username.label" default="Region" />--}%
                %{--<span class="required-indicator">*</span>--}%
            %{--</label>--}%
        %{--</div>--}%
        %{--<div class="grid_3">--}%
            %{--<g:textField name="region" required="" value=""/>--}%
        %{--</div>--}%

        <br class="clear" />

    </fieldset>

</div>

</div>  <!--div for padding end-->

</div><!-- grid16 container for top element-->
<br class="clear" />
%{--<br class="clear" />--}%
<div class="container_16" style="width: 701px;">
<div class="grid_6 alpha leftElement" style="padding-top: 20px;">

    <fieldset class="user-profile-fieldset" style="width: 350px">
        <legend class="legend-color">Leave Type</legend>
        <div class="grid_2" style="padding-top: 5px;">
        <label for="leaveType">
        <g:message code="leaveType.label" default="leaveType" />
        <span class="required-indicator">*</span>
        </label>
        </div>
        <div class="grid_3">
            <g:select id="LeaveType" name="leaveType" from="${Organization.list()}" optionKey="id" optionValue="organizationName" noSelection="['':'Select Organization...']"></g:select>

            %{--<g:textField name="username" id="username" required="" value=""/>--}%
        </div>

        %{--<div style="padding-top: 8px">--}%

        %{--</div>--}%
        %{--Gender--}%
        %{--<div class="grid_2" style="padding-top: 5px;">--}%
            %{--<label for="Gender">--}%
                %{--<g:message code="employee.username.label" default="Gender" />--}%
                %{--<span class="required-indicator">*</span>--}%
            %{--</label>--}%
        %{--</div>--}%
        %{--<div class="grid_3">--}%
            %{--<g:select name="gender" from="${['Male', 'Female']}"/>--}%
        %{--</div>--}%

        %{--<br class="clear" />--}%
        %{--<br class="clear" />--}%


        %{--<div class="grid_2" style="padding-top: 5px;">--}%
            %{--<label for="Marital Status">--}%
                %{--<g:message code="employee.username.label" default="Marital Status" />--}%
                %{--<span class="required-indicator">*</span>--}%
            %{--</label>--}%
        %{--</div>--}%
        %{--<div class="grid_3">--}%
            %{--<g:textField name="maritalStatus" required="" value=""/>--}%
            %{--<g:select name="maritalStatus" from="${['Married', 'Single']}"/>--}%
        %{--</div>--}%

        %{--<br class="clear" />--}%
        %{--<br class="clear" />--}%

        %{--DOB--}%
        %{--<div class="grid_2" style="padding-top: 5px;">--}%
            %{--<label for="DOB">--}%
                %{--<g:message code="employee.username.label" default="Date Of Birth" />--}%
                %{--<span class="required-indicator">*</span>--}%
            %{--</label>--}%
        %{--</div>--}%
        %{--<div class="grid_3">--}%
            %{--<g:textField name="dateOfBirth" required="" value="" readonly="readonly"/>--}%
        %{--</div>--}%
        %{--<img src="${resource(dir: 'images/calendar', file: 'calendar_add.png')}" alt="calender" id="showCalendarDateOfBirth" class="adjustImg" title="Click to select date of birth">--}%
        %{--<script type="text/javascript">--}%
            %{--//<![CDATA[--}%
            %{--Calendar.setup({--}%
                %{--inputField : "dateOfBirth",--}%
                %{--trigger    : "showCalendarDateOfBirth",--}%
                %{--onSelect   : function() { this.hide() },--}%
                %{--showTime   : 12,--}%
                %{--dateFormat : "%d/%m/%Y %H:%m:%S"//this is a timestamp format--}%
            %{--});--}%
            %{--//]]>--}%
        %{--</script>--}%

        %{--<br class="clear" />--}%
        %{--<br class="clear" />--}%


        %{--<div class="grid_2" style="padding-top: 5px;">--}%
            %{--<label for="doh">--}%
                %{--<g:message code="employee.username.label" default="Date Of Hire" />--}%
                %{--<span class="required-indicator">*</span>--}%
            %{--</label>--}%
        %{--</div>--}%
        %{--<div class="grid_3">--}%
            %{--<g:textField name="dateOfHire" required="" value="" readonly="readonly"/>--}%
        %{--</div>--}%
        %{--<img src="${resource(dir: 'images/calendar', file: 'calendar_add.png')}" alt="calender" id="showCalendarDateOfHire" class="adjustImg" title="Click to select date of hire">--}%
        %{--<script type="text/javascript">--}%
            %{--//<![CDATA[--}%
            %{--Calendar.setup({--}%
                %{--inputField : "dateOfHire",--}%
                %{--trigger    : "showCalendarDateOfHire",--}%
                %{--onSelect   : function() { this.hide() },--}%
                %{--showTime   : 12,--}%
                %{--dateFormat : "%d/%m/%Y %H:%m:%S"//this is a timestamp format--}%
            %{--});--}%
            %{--//]]>--}%
        %{--</script>--}%

        %{--<br class="clear" />--}%
        %{--<br class="clear" />--}%

        %{--<div class="grid_2" style="padding-top: 5px;">--}%
            %{--<label for="doh">--}%
                %{--<g:message code="employee.username.label" default="Date Of Join" />--}%
                %{--<span class="required-indicator">*</span>--}%
            %{--</label>--}%
        %{--</div>--}%
        %{--<div class="grid_3">--}%
            %{--<g:textField name="dateOfJoin" required="" value="" readonly="readonly"/>--}%
        %{--</div>--}%
        %{--<img src="${resource(dir: 'images/calendar', file: 'calendar_add.png')}" alt="calender" id="showCalendarDateOfJoin" class="adjustImg" title="Click to select date of Join">--}%
        %{--<script type="text/javascript">--}%
            %{--//<![CDATA[--}%
            %{--Calendar.setup({--}%
                %{--inputField : "dateOfJoin",--}%
                %{--trigger    : "showCalendarDateOfJoin",--}%
                %{--onSelect   : function() { this.hide() },--}%
                %{--showTime   : 12,--}%
                %{--dateFormat : "%d/%m/%Y %H:%m:%S"//this is a timestamp format--}%
            %{--});--}%
            %{--//]]>--}%
        %{--</script>--}%

        %{--<br class="clear" />--}%
        <br class="clear" />
        <br class="clear" />
        <hr>
        <div class="grid_2" style="padding-top: 5px;">
        <label >
        <g:message code="otherleave.label" default="otherleave" />

        </label>
        </div>
        <br class="clear" />
        <br class="clear" />

        <div  style="padding-top: 5px">
             <g:checkBox name="adoption" id="adoption" value="${false}" style="margin-left: 10px" />
            <span style="margin-left: 10px">[Birth/Adoption/Foster Care]</span>
        </div>

        <br class="clear" />

        <div  style="padding-top: 5px">
            <g:checkBox name="leaveforparent" id="leaveforparent" value="${false}" style="margin-left: 10px" />
            <span style="margin-left: 10px">[Serious health condition of spouse <br>&nbsp;&nbsp; son, daughter, or parent ]</span>
        </div>

        <br class="clear" />

        <div  style="padding-top: 5px">
            <g:checkBox name="healtcondition" id="healtcondition" value="${false}" style="margin-left: 10px" />
            <span style="margin-left: 10px">[Serious health condition of self]</span>
        </div>

        <br class="clear" />

        <div  style="padding-top: 5px">
            <g:checkBox name="adoption" id="adoption" value="${false}" style="margin-left: 10px" />
            <span style="margin-left: 10px">[Birth/Adoption/Foster Care]</span>
        </div>


        %{--<div class="grid_2" style="padding-top: 5px;">--}%
            %{--<label for="adoption">--}%
                %{--<g:message code="adoption.label" default="adoption" />--}%
            %{--</label>--}%
        %{--</div>--}%


    </fieldset>


</div>

<div class="grid_6 omega rightElement"  style="padding-top: 20px;">

    %{--<fieldset class="user-profile-fieldset" style="height: 248px">--}%
        %{--<legend class="legend-color">Identification</legend>--}%

        %{--<div style="padding-top: 8px">--}%

        %{--</div>--}%

        %{--<div class="grid_2" style="padding-top: 5px;">--}%
            %{--<label for="National ID">--}%
                %{--<g:message code="employee.username.label" default="National ID" />--}%
                %{--<span class="required-indicator">*</span>--}%
            %{--</label>--}%
        %{--</div>--}%

        %{--<div class="grid_3">--}%
            %{--<g:textField name="nationalID" required="" value=""/>--}%
        %{--</div>--}%

        %{--<br class="clear" />--}%
        %{--<br class="clear" />--}%

        %{--<div class="grid_2" style="padding-top: 5px;">--}%
            %{--<label for="Passport">--}%
                %{--<g:message code="employee.username.label" default="Passport No" />--}%
                %{--<span class="required-indicator">*</span>--}%
            %{--</label>--}%
        %{--</div>--}%
        %{--<div class="grid_3">--}%
            %{--<g:textField name="passportNo" required="" value=""/>--}%
        %{--</div>--}%

        %{--<br class="clear" />--}%
        %{--<br class="clear" />--}%


        %{--<div class="grid_2" style="padding-top: 5px;">--}%
            %{--<label for="Issue Date">--}%
                %{--<g:message code="employee.username.label" default="Issue Date" />--}%
                %{--<span class="required-indicator">*</span>--}%
            %{--</label>--}%
        %{--</div>--}%
        %{--<div class="grid_3">--}%
            %{--<g:textField name="issueDate" required="" value="" readonly="readonly"/>--}%
        %{--</div>--}%
        %{--<img src="${resource(dir: 'images/calendar', file: 'calendar_add.png')}" alt="calender" id="showCalendarIssudate" class="adjustImg" title="Click to select issue date">--}%
        %{--<script type="text/javascript">--}%
            %{--//<![CDATA[--}%
            %{--Calendar.setup({--}%
                %{--inputField : "issueDate",--}%
                %{--trigger    : "showCalendarIssudate",--}%
                %{--onSelect   : function() { this.hide() },--}%
                %{--showTime   : 12,--}%
                %{--dateFormat : "%d/%m/%Y %H:%m:%S"//this is a timestamp format--}%
            %{--});--}%
            %{--//]]>--}%
        %{--</script>--}%

        %{--<br class="clear" />--}%
        %{--<br class="clear" />--}%


        %{--Expired Date--}%
        %{--<div class="grid_2" style="padding-top: 5px;">--}%
            %{--<label for="Expired Date">--}%
                %{--<g:message code="employee.username.label" default="Expired Date" />--}%
                %{--<span class="required-indicator">*</span>--}%
            %{--</label>--}%
        %{--</div>--}%
        %{--<div class="grid_3">--}%
            %{--<g:textField name="expireDate" required="" value="" readonly="readonly"/>--}%
        %{--</div>--}%
        %{--<img src="${resource(dir: 'images/calendar', file: 'calendar_add.png')}" alt="calender" id="showCalendarExpireDate" class="adjustImg" title="Click to select date">--}%
        %{--<script type="text/javascript">--}%
            %{--//<![CDATA[--}%
            %{--Calendar.setup({--}%
                %{--inputField : "expireDate",--}%
                %{--trigger    : "showCalendarExpireDate",--}%
                %{--onSelect   : function() { this.hide() },--}%
                %{--showTime   : 12,--}%
                %{--dateFormat : "%d/%m/%Y %H:%m:%S"//this is a timestamp format--}%
            %{--});--}%
            %{--//]]>--}%
        %{--</script>--}%

        %{--<br class="clear" />--}%
        %{--<br class="clear" />--}%


        %{--<div class="grid_2" style="padding-top: 5px;">--}%
            %{--<label for="Issue From">--}%
                %{--<g:message code="employee.username.label" default="Issue From" />--}%
                %{--<span class="required-indicator">*</span>--}%
            %{--</label>--}%
        %{--</div>--}%
        %{--<div class="grid_3">--}%
            %{--<g:textField name="issueFrom" required="" value="" />--}%
        %{--</div>--}%
        %{--<br class="clear" />--}%
        %{--<br class="clear" />--}%



        %{--<br class="clear" />--}%
    %{--</fieldset>--}%
    <br class="clear">



</div>
<br class="clear" />
<br class="clear" />
<fieldset class="grid_8" style="margin-left: 275px;">
    <a id="generalSave" class="button icon approve" onclick="">Save</a>
    <a id="clearForm" class="button danger icon remove" onclick="document.userForm.reset()">Clear</a>
</fieldset>

<br class="clear"/>

<br class="clear"/>


</div>