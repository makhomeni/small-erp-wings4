<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 3/15/12
  Time: 4:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.jabait.security.UserGroup" %>
<div class="fieldcontain ${hasErrors(bean: userGroupInstance, field: 'groupName', 'error')} required">
	<label for="groupName">
		<g:message code="userGroup.groupName.label" default="Group Name" />
		<span class="required-indicator">*</span>
	</label>
    <g:if test="${params.action == 'editUserGroup'}">
        <g:textField name="groupName" required="" value="${userGroupInstance?.groupName}" readonly="readonly"/>
    </g:if>
    <g:else>
        <g:textField name="groupName" required="" value="${userGroupInstance?.groupName}" />
    </g:else>

</div>
<div class="fieldcontain ${hasErrors(bean: userGroupInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="userGroup.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
    <g:textField name="description" required="" value="${userGroupInstance?.description}"/>
</div>
