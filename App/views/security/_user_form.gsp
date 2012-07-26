<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/20/12
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.jabait.security.User" %>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'userCode', 'error')} required">
	<label for="userCode">
		<g:message code="user.userCode.label" default="User Name" />
		<span class="required-indicator">*</span>
	</label>


        <g:textField name="userCode" required=""  value="" />

</div>
<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="user.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
    <g:passwordField name="password" required="" value=""/>
</div>
<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
    <label for="password">
        <g:message code="user.password.label" default="Confirm Password" />
        <span class="required-indicator">*</span>
    </label>
    <g:passwordField name="confirmPassword" required="" value=""/>
</div>


