<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/20/12
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.jabait.hrm.EmploymentStatus"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<g:applyLayout name="app">
<head>

    <title>EmploymentStatus</title>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'livevalidation_standalone.compressed.js')}">

    </script>
    <script type="text/javascript">

        $(document).ready(function(){
            var liveValidation = new LiveValidation('confirmPassword');
//                alert(liveValidation)
            liveValidation.add( Validate.Confirmation, { match: 'password' } );
        })

        function submitForm(){
            document.employeeStatusForm.submit();
        }
    </script>

</head>
<body>
<content tag="bannerTag">
    <h1>${type}</h1>
</content>
<content tag="rightTag">

    <div class="bread_crumbs_ui_div" style="width: 611px">
        <ul id="crumbs_ui_custom">
            <li><g:link controller="application" action="index">Dashboard</g:link></li>
            <li><g:link controller="configuration" action="jobConfigHome">Job Config Home</g:link></li>
            <li><g:link controller="configuration" action="employmentStatusList"> Employment Status List</g:link></li>
            <li> Create Employment Status</li>
        </ul>
    </div>
    <br class="clear"/>

<div class="widget">

    <div class="header" style="width: 610px;">
        <span><span class="ico gray jobStatus"></span>${type}</span>
    </div>

    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:form name="employeeStatusForm" id="employeeStatusForm" controller="configuration" action="saveEmploymentStatus" method="POST">
        <fieldset class="form">
            %{--<g:render template="organization_form"/>--}%
            <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'employeeStatusCode', 'error')} required">
                <label for="employeeStatusName">
                    Employee Code
                    <span class="required-indicator">*</span>
                </label>

                <g:textField name="employeeStatusCode" required=""  value="" />

            </div>



            <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'employeeStatusName', 'error')} required">
                <label for="employeeStatusName">
                    Employee Status Name
                    <span class="required-indicator">*</span>
                </label>

                <g:textField name="employeeStatusName" id="employeeStatusName" required=""  value="" />

            </div>


            <div class="grid_4 alpha">&nbsp;&nbsp;</div>

            <div class="grid_4 omega" style="padding-left: 194px;padding-top: 15px">

                <a id="employeeStatusForm" class="button icon approve" onclick="submitForm()">Save</a>
                <a id="ff" class="button danger icon remove" onclick="document.employeeStatusForm.reset()">Clear</a>

            </div>

        </fieldset>
        <br class="clear"/>

    </g:form>
</div>
</content>
</body>
</g:applyLayout>