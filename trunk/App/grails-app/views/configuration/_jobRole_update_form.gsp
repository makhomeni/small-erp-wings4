<%--
  Created by IntelliJ IDEA.
  User: Masum
  Date: 3/15/12
  Time: 4:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.jabait.hrm.JobRole" %>
<div class="fieldcontain ${hasErrors(bean: jobRoleInstance, field: 'Role Name', 'error')} required">
    <label for="roleName">
        <g:message code="jobRole.label" default="Job Role" />
        <span class="required-indicator">*</span>
    </label>
    <g:if test="${params.action == 'editUserGroup'}">
        <g:textField name="roleName" required="" value="${jobRoleInstance?.roleName}" readonly="readonly"/>
    </g:if>
    <g:else>
        <g:textField name="roleName" required="" value="${jobRoleInstance?.roleName}" />
    </g:else>

</div>
<div class="fieldcontain ${hasErrors(bean: jobRoleInstance, field: 'description', 'error')} required">
    <label for="description">
        <g:message code="roleName.description.label" default="Description" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="description" required="" value="${jobRoleInstance?.description}"/>
</div>
