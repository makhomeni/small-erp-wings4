<%--
  Created by IntelliJ IDEA.
  User: Shohag
  Date: 4/15/12
  Time: 5:03 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.jabait.hrm.Department" %>
<div class="fieldcontain ${hasErrors(bean: departmentInstance, field: 'departmentName', 'error')} required">
    <label for="departmentName">
        <g:message code="user.userCode.label" default="Department Name" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="departmentName" required="" value="${departmentInstance?.departmentName}" />
</div>
<div class="fieldcontain ${hasErrors(bean: departmentInstance, field: 'organization', 'error')} required">
    <label for="organization">
        <g:message code="user.password.label" default="Organization name" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="organizationName" required="" value="${departmentInstance?.organization}"/>
</div>