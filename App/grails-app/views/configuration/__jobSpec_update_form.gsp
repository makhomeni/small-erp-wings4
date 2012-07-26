<%--
  Created by IntelliJ IDEA.
  User: Masum
  Date: 3/15/12
  Time: 4:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.jabait.hrm.JobSpec" %>
<div class="fieldcontain ${hasErrors(bean: jobSpecInstance, field: 'jobSpecName', 'error')} required">
    <label for="jobSpecName">
        <g:message code="jobSpec.label" default="Job Spec Namee" />
        <span class="required-indicator">*</span>
    </label>
    <g:if test="${params.action == 'editJobSpec'}">
        <g:textField name="jobSpecName" required="" value="${jobSpecInstance?.jobSpecName}" readonly="readonly"/>
    </g:if>
    <g:else>
        <g:textField name="jobSpecName" required="" value="${jobSpecInstance?.jobSpecName}" />
    </g:else>

</div>
<div class="fieldcontain ${hasErrors(bean: jobSpecInstance, field: 'jobSpecDesc', 'error')} required">
    <label for="jobSpecDesc">
        <g:message code="jobSpecDesc.label" default="Job Spec Desc" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="jobSpecDesc" required="" value="${jobSpecInstance?.jobSpecDesc}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: jobSpecInstance, field: 'jobSpecDuties', 'error')} required">
    <label for="jobSpecDuties">
        <g:message code="jobSpecDuties.label" default="Job Spec Duties" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="jobSpecDuties" required="" value="${jobSpecInstance?.jobSpecDuties}"/>
</div>
