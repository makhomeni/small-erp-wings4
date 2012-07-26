<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/20/12
  Time: 10:45 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.jabait.security.Authority" %>
<div class="fieldcontain ${hasErrors(bean: authorityInstance, field: 'roleTitle', 'error')} required">
	<label for="roleTitle">
		Role
		<span class="required-indicator">*</span>
	</label>
    <g:if test="${params.action == 'editAuthority'}">
        <g:textField name="roleTitle" required="" value="${authorityInstance?.roleTitle}" readonly="readonly"/>
    </g:if>
    <g:else>
        <g:textField name="roleTitle" required="" value="${authorityInstance?.roleTitle}" />
    </g:else>

</div>
<div class="fieldcontain ${hasErrors(bean: authorityInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="authority.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
    <g:textField name="description" required="" value="${authorityInstance?.description}"/>
</div>
