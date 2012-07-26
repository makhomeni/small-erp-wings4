<%--
  Created by IntelliJ IDEA.
  User: Masum
  Date: 3/15/12
  Time: 4:44 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.jabait.hrm.JobSpec"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<g:applyLayout name="app">
    <head>
        <g:set var="entityName" value="${message(code:'user.description', default: 'Job Spec')}"/>
        <title><g:message code="default.edit.label" args="[entityName]"/></title>

        <script type="text/javascript">
            function submitForm()
            {
                document.jobSpecForm.submit();
            }


        </script>


    </head>
    <body>

    <content tag="rightTag">

        <div class="widget">

            <div class="header" style="width: 610px;">
                <span><span class="ico gray home"></span>${type}</span>
            </div>

            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:form name="jobSpecForm" controller="configuration" action="updateJobSpec" method="POST">
                <fieldset class="form">

                    <g:render template="jobSpec_update_form"/>
                    <g:hiddenField name="jobSpecId" id="jobSpecId" value="${jobSpecInstance.id}"></g:hiddenField>

                    <div class="grid_4 alpha">&nbsp;&nbsp;</div>
                    <div class="grid_4 omega" style="padding-left: 195px; padding-top: 12px;">

                        <a id="jobSpec" class="button icon approve" onclick="submitForm()">Save</a>
                        <a id="ff" class="button danger icon remove" onclick="document.jobSpecForm.reset()">Clear</a>

                    </div>

                </fieldset>
                <br class="clear"/>

            </g:form>
        </div>
    </content>
    </body>
</g:applyLayout>