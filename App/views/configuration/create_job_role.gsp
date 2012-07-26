<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/20/12
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.jabait.hrm.JobRole"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<g:applyLayout name="app">
    <head>

        <title>${title}</title>
        <script type="text/javascript" src="${resource(dir: 'js', file: 'livevalidation_standalone.compressed.js')}">

        </script>

        <script type="text/javascript">

            $(document).ready(function(){
                var liveValidation = new LiveValidation('confirmPassword');
//                alert(liveValidation)
                liveValidation.add( Validate.Confirmation, { match: 'password' } );
            })
            function submitForm(){
                document.jobRoleForm.submit();
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
                <li><g:link controller="configuration" action="jobRoleList">Job Role List</g:link></li>
                <li>Create Job Role</li>
            </ul>
        </div>
        <br class="clear"/>

        <div class="widget">

            <div class="header" style="width: 610px;">
                <span><span class="ico gray jobRole"></span>${title}</span>
            </div>

            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:form name="jobRoleForm" id="jobRoleForm" controller="configuration" action="saveRole" method="POST">
                <fieldset class="jobRole">
                    %{--<g:render template="organization_form"/>--}%
                    <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'jobRoleName', 'error')} required">
                        <label for="jobRoleName">
                            Job Role Name
                            <span class="required-indicator">*</span>
                        </label>

                        <g:textField name="jobRoleName" id="jobRoleName" required=""  value="" />

                    </div>



                    <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'jobRoleDescription', 'error')} required">
                        <label for="jobRoleDescription">
                            Job Role Description
                            <span class="required-indicator">*</span>
                        </label>

                        <g:textField name="jobRoleDescription" id="jobRoleDescription" required=""  value="" />

                    </div>


                    <div class="grid_4 alpha">&nbsp;&nbsp;</div>

                    <div class="grid_4 omega" style="padding-left: 194px;padding-top: 15px">

                        <a id="jobRoleForm" class="button icon approve" onclick="submitForm()">Save</a>
                        <a id="ff" class="button danger icon remove" onclick="document.jobRoleForm.reset()">Clear</a>

                    </div>

                </fieldset>
                <br class="clear"/>

            </g:form>
        </div>
    </content>
    </body>
</g:applyLayout>