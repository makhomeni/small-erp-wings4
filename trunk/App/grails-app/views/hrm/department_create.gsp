<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 4/15/12
  Time: 4:49 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.jabait.hrm.Department"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<g:applyLayout name="app">
    <head>
        <g:set var="entityName" value="${message(code:'department.description', default: 'User')}"/>
        <title><g:message code="default.create.label" args="[entityName]"/></title>
    </head>
    <body>
    <content tag="bannerTag">
        <h1>${type}</h1>
    </content>
    <content tag="rightTag">
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <g:form name="departmentForm" controller="employee" action="createDepartment" method="POST">
            <fieldset class="form">
                <g:render template="/hrm/department_form"/>
            </fieldset>
            <br class="clear"/>
            <fieldset class="grid_8">
                <div class="grid_4 alpha">&nbsp;&nbsp;</div>
                <div class="grid_4 omega">
                    <g:submitButton name="create" class="button save" value="${message(code: 'default.button.create.label', default: 'Create')}"/>
                    <input class="button reset" type="reset" value="Cancel" />
                </div>
            </fieldset>
        </g:form>
    </content>
    </body>
</g:applyLayout>