<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/20/12
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.jabait.hrm.time.Leave"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<g:applyLayout name="app">
    <head>
        <g:set var="entityName" value="${message(code:'configuration.description', default: 'Create Leave')}"/>
        <title><g:message code="Create Leave" args="[entityName]"/></title>
        <script type="text/javascript" src="${resource(dir: 'js', file: 'livevalidation_standalone.compressed.js')}">

        </script>
        <script type="text/javascript">


            function submitForm(){
                document.leaveForm.submit();
            }
        </script>



        <style>
            div.selector{
                margin-top: -22px;
                margin-left: 155px;
                margin-bottom: 12px;
            }
        </style>


    </head>
    <body>
    <content tag="bannerTag">
        <h1>${type}</h1>
    </content>
    <content tag="rightTag">

        <script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.uniform.js')}" ></script>
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'uniform.aristo.css')}">

        <div class="bread_crumbs_ui_div" style="width: 611px">
            <ul id="crumbs_ui_custom">
                <li><g:link controller="application" action="index">Dashboard</g:link></li>
                %{--<li><g:link controller="configuration" action="jobConfigHome">Job Config Home</g:link></li>--}%
                <li><g:link controller="configuration" action="leaveList">Leave List</g:link></li>
                <li>Create Leave</li>
            </ul>
        </div>
        <br class="clear"/>

        <div class="widget">

            <div class="header" style="width: 610px;">
                <span><span class="ico gray leave"></span>${type}</span>
            </div>

            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:form name="leaveForm" id="leaveForm" controller="configuration" action="saveLeave" method="POST">
                <fieldset class="leave">
                    %{--<g:render template="organization_form"/>--}%
                    <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'leave', 'error')} required">
                        <label for="leaveType">
                            <g:message code="Create Leave" default="Leave Type" />
                            <span class="required-indicator">*</span>
                        </label>

                        <g:select name="leaveType" from="${['Casual Leave', 'Sick Leave', 'Maternity Leave','Study Leave','Annual Leave', 'Community Leave', ' Long Service Leave','Others']}"/>

                    </div>






                    <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'leaveDuration', 'error')} required">
                        <label for="leaveDuration">
                            <g:message code="leavedURATION.label" default="Leave Duration" />
                            <span class="required-indicator">*</span>
                        </label>

                        <g:textField name="leaveDuration" id="leaveDuration"></g:textField>


                    </div>


                    <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'leaveNotes', 'error')} required">
                        <label for="leaveNotes">
                            <g:message code="leaveNotes.label" default="Leave Notes" />
                            <span class="required-indicator">*</span>
                        </label>


                        <g:textField name="leaveNotes" required=""  value="" />

                    </div>




                    <div class="grid_4 alpha">&nbsp;&nbsp;</div>
                    <div class="grid_4 omega" style="padding-left: 194px;padding-top: 15px">

                        <a id="leaveForm" class="button icon approve" onclick="submitForm()">Save</a>
                        <a id="ff" class="button danger icon remove" onclick="document.leaveForm.reset()">Clear</a>

                    </div>

                </fieldset>
                <br class="clear"/>

            </g:form>
        </div>
        <script type="text/javascript">
            $(document).ready(function(){
                $("input, textarea, select, button").uniform();
            })
        </script>
    </content>

    </body>
</g:applyLayout>