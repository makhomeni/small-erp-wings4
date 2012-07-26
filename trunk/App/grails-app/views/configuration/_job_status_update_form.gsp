<%--
  Created by IntelliJ IDEA.
  User: Masum
  Date: 3/15/12
  Time: 4:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.jabait.hrm.EmploymentStatus" %>
<div class="fieldcontain ${hasErrors(bean: jobStatusInstance, field: 'Status Code', 'error')} required">
    <label for="statusCode">
        Status Code
        <span class="required-indicator">*</span>
    </label>
    <g:if test="${params.action == 'editStatusCode'}">
        <g:textField name="statusCode" required="" value="${jobStatusInstance?.statusCode}" readonly="readonly"/>
    </g:if>
    <g:else>
        <g:textField name="statusCode" required="" value="${jobStatusInstance?.statusCode}" />
    </g:else>

</div>
<div class="fieldcontain ${hasErrors(bean: jobStatusInstance, field: 'statusName', 'error')} required">
    <label for="statusName">
        Status Name
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="statusName" required="" value="${jobStatusInstance?.statusName}"/>
</div>
