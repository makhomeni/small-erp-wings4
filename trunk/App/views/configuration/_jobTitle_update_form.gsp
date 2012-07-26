<%--
  Created by IntelliJ IDEA.
  User: masum
  Date: 6/27/12
  Time: 11:27 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.jabait.hrm.JobTitle" %>
<div class="fieldcontain ${hasErrors(bean: jobTitleInstance, field: 'jobTitleCode', 'error')} required">
    <label for="jobTitleCode">
        Job Title
        <span class="required-indicator">*</span>
    </label>
    <g:if test="${params.action == 'editJobTitle'}">
        <g:textField name="jobTitleCode" required="" value="${jobTitleInstance?.jobTitleCode}" readonly="readonly"/>
    </g:if>
    <g:else>
        <g:textField name="jobTitleCode" required="" value="${jobTitleInstance?.jobTitleCode}" />
    </g:else>

</div>
<div class="fieldcontain ${hasErrors(bean: jobTitleInstance, field: 'jobTitleName', 'error')} required">
    <label for="jobTitleName">
       Job Title Name
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="jobTitleName" required="" value="${jobTitleInstance?.jobTitleName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: jobTitleInstance, field: 'jobTitleDescription', 'error')} required">
    <label for="jobTitleDescription">
       Job Title Description
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="jobTitleDescription" required="" value="${jobTitleInstance?.jobTitleDescription}"/>
</div>
