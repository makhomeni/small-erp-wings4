<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/20/12
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.jabait.hrm.Organization; com.jabait.hrm.Department"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<g:applyLayout name="app">
    <head>
        <g:set var="entityName" value="${message(code:'configuration.description', default: 'Configuration')}"/>
        <title><g:message code="default.create.label" args="[entityName]"/></title>
        <script type="text/javascript" src="${resource(dir: 'js', file: 'livevalidation_standalone.compressed.js')}">

        </script>
        %{--<script type="text/javascript">--}%

        %{--$(document).ready(function(){--}%
        %{--var liveValidation = new LiveValidation('confirmPassword');--}%
        %{--//                alert(liveValidation)--}%
        %{--liveValidation.add( Validate.Confirmation, { match: 'password' } );--}%
        %{--})--}%
        %{--</script>--}%

    </head>
    <body>
    <content tag="bannerTag">
        <h1>${type}</h1>
    </content>
    <content tag="rightTag">

        <script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.uniform.js')}" ></script>


        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'uniform.aristo.css')}">
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
                document.sectionForm.submit();
            }
    </script>

        <div class="bread_crumbs_ui_div" style="width: 611px">
            <ul id="crumbs_ui_custom">
                <li><g:link controller="application" action="index">Dashboard</g:link></li>
                <li><g:link controller="configuration" action="organizationConfigHome">Organization Index</g:link></li>
                <li><g:link controller="configuration" action="sectionList">Section List</g:link></li>
                <li>Create Section</li>
            </ul>
        </div>

        <div class="widget">

            <div class="header" style="width: 610px;">
                <span><span class="ico gray section"></span>${type}</span>
            </div>

        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <g:form name="sectionForm" id="sectionForm" controller="configuration" action="saveSection" method="POST">
            <fieldset class="section">
        %{--<g:render template="organization_form"/>--}%
            <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'sectionName', 'error')} required">
                <label for="sectionName">
                    <g:message code="section.label" default="Section Name" />
                    <span class="required-indicator">*</span>
                </label>
                %{--
                  <g:if test="${userInstance.userCode}">
                    </g:if>
                    <g:else>
                    </g:else>
                --}%

                <g:textField name="sectionName" required=""  value="" />

            </div>



            <div class="fieldcontain ${hasErrors(bean: featureInstance, field: 'parentSection', 'error')} required">
            <label for="section">
                <g:message code="section.label" default="Department" />
                <span class="required-indicator">*</span>

            </label>

                <g:select id="departmentList" name="department" from="${Department.list()}" optionKey="id" optionValue="departmentName" noSelection="['':'Select Department...']"></g:select>

            </div>


            <div class="fieldcontain ${hasErrors(bean: featureInstance, field: 'organization', 'error')} required">
            <label for="section">
                <g:message code="organization.label" default="Organization" />
                <span class="required-indicator">*</span>

            </label>

                <g:select id="organizationList" name="organization" from="${Organization.list()}" optionKey="id" optionValue="organizationName" noSelection="['':'Select Organization...']"></g:select>

            </div>

            %{--<br class="clear"/>--}%
            <fieldset class="grid_8">
                <div class="grid_4 alpha">&nbsp;&nbsp;</div>
                <div class="grid_4 omega" style="padding-left: 194px;padding-top: 10px; padding-bottom: 10px"; >

                    %{--<g:submitButton name="create" class="button save" value="${message(code: 'default.button.create.label', default: 'Create')}"/>--}%
                    %{--<input class="button reset" type="reset" value="Clear" />--}%


                    <a id="Section" class="button icon approve" onclick="submitForm()">Save</a>
                    <a id="ff" class="button danger icon remove" onclick="document.sectionForm.reset()">Clear</a>



                </div>



            </fieldset>

        </g:form>
            </div>
    </content>
    </body>
</g:applyLayout>