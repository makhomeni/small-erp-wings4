<%--
  Created by IntelliJ IDEA.
  User: masum
  Date: 7/23/12
  Time: 3:34 PM
  To change this template use File | Settings | File Templates.
--%>
%{--<%@ page import="com.jabait.configuration.All" %>--}%
<div class="fieldcontain ${hasErrors(bean: allowanceInstance, field: 'Allowance Name', 'error')} required">
    <label for="allowanceName">
        <g:message code="allowanceName.label" default="Allowance Name" />
        <span class="required-indicator">*</span>
    </label>
    <g:if test="${params.action == 'editAllowanceName'}">
        <g:textField name="allowanceName" required="" value="${allowanceInstance?.allowanceName}" readonly="readonly"/>
    </g:if>
    <g:else>
        <g:textField name="allowanceName" required="" value="${allowanceInstance?.allowanceName}" />
    </g:else>

</div>
<div class="fieldcontain ${hasErrors(bean: allowanceInstance, field: 'allowanceDescription', 'error')} required">
    <label for="allowanceDescription">
        <g:message code="allowanceDescription.label" default="Allowance Description" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="allowanceDescription" required="" value="${allowanceInstance?.allowanceDescription}"/>
</div>
