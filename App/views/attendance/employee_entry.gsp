

<%@ page import="com.jabait.hrm.time.AttendanceRegister" %>

<g:applyLayout name="app">
    <head>
        <g:set var="entityName" value="${message(code:'configuration.description', default: 'Employee Entry')}"/>
        <title><g:message code="employeeEntry.label" args="[entityName]"/></title>
        <script type="text/javascript" src="${resource(dir: 'js', file: 'livevalidation_standalone.compressed.js')}">

        </script>
        <script type="text/javascript">

            $(document).ready(function(){
                var liveValidation = new LiveValidation('confirmPassword');
//                alert(liveValidation)
                liveValidation.add( Validate.Confirmation, { match: 'password' } );
            })

            function submitForm(){
                document.employeeEntryForm.submit();
            }

        </script>

    </head>
    <body>
    <content tag="titleTag">

    </content>

    <content tag="rightTag">

        <div class="widget">

            <div class="header" style="width: 610px;">
                <span><span class="ico gray jobSpec"></span>Employee Entry</span>
            </div>

            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:form name="employeeEntryForm" id="employeeEntryForm" controller="attendance" action="saveEmployeeEntry" method="POST">
                <fieldset class="form">
                    %{--<g:render template="organization_form"/>--}%
                    <div class="fieldcontain ${hasErrors(bean: employeeInstance, field: 'employeeEntryId', 'error')} required">
                        <label for="employeeEntryName">
                            <g:message code="employeeEntryId.label" default="Employee Entry Id" />
                            <span class="required-indicator">*</span>
                        </label>


                        <g:textField name="userCode" required=""  value="" />

                    </div>






                    <div class="fieldcontain ${hasErrors(bean: employeeInstance, field: 'employeePassword', 'error')} required">
                        <label for="employeePassword">
                            <g:message code="attendance.password.label" default="Employee Password" />
                            <span class="required-indicator">*</span>
                        </label>

                        <g:passwordField name="password" required=""  value="" />

                    </div>


                    <div class="fieldcontain ${hasErrors(bean: employeeInstance, field: 'remark', 'error')} required">
                        <label for="remark">
                            <g:message code="remark.label" default="Remark" />
                            <span class="required-indicator">*</span>
                        </label>


                        <g:textField name="remark" required=""  value="" />

                    </div>




                    <div class="grid_4 alpha">&nbsp;&nbsp;</div>
                    <div class="grid_4 omega" style="padding-left: 184px;padding-top: 15px">

                        <a id="employeeEntryForm" class="button icon approve" onclick="submitForm()">Save</a>
                        <a id="ff" class="button danger icon remove" onclick="document.employeeEntryForm.reset()">Clear</a>

                    </div>

                </fieldset>
                <br class="clear"/>

            </g:form>
        </div>
    </content>
    </body>
</g:applyLayout>