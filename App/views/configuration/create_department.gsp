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

    <title>Create Department</title>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'livevalidation_standalone.compressed.js')}">

    </script>


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
            document.departmentForm.submit();
        }
    </script>
    <div class="bread_crumbs_ui_div" style="width: 611px">
        <ul id="crumbs_ui_custom">
            <li><g:link controller="application" action="index">Dashboard</g:link></li>
            <li><g:link controller="configuration" action="organizationConfigHome">Organization Index</g:link></li>
            <li><g:link controller="configuration" action="departmentList">Department List</g:link></li>
            <li>Department Create</li>
        </ul>
    </div>
    <br class="clear"/>
    <div class="widget">

        <div class="header" style="width: 610px;">
            <span><span class="ico gray department"></span>${type}</span>
        </div>

    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:form name="departmentForm" id="departmentForm" controller="configuration" action="saveDepartment" method="POST">
        <fieldset class="form">
            %{--<g:render template="organization_form"/>--}%
            <div class="fieldcontain required">
                <label for="departmentName">
                    Department Name
                    <span class="required-indicator">*</span>
                </label>

                <g:textField name="departmentName" id="departmentName" required=""  value="" />

            </div>

            <div class="fieldcontain required">
                <label for="organizationList">
                   Organization
                    <span class="required-indicator">*</span>
                </label>


                <g:select id="organizationList" name="organization" from="${Organization.list()}"
                          optionKey="id" optionValue="organizationName" noSelection="['':'Select Organization...']"></g:select>

            </div>

            <br class="clear"/>
            <fieldset class="grid_8">
                <div class="grid_4 alpha">&nbsp;&nbsp;</div>


                <div class="grid_4 omega" style="padding-left: 194px;padding-top: 10px; padding-bottom: 10px" >




                <a id="departmentSave" class="button icon approve" onclick="submitForm()">Save</a>
                <a id="ff" class="button danger icon remove" onclick="document.departmentForm.reset()">Clear</a>



        </div>

        </fieldset>

    </g:form>
</content>
</body>
</g:applyLayout>