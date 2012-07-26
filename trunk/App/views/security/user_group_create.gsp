<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 3/15/12
  Time: 4:44 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.jabait.security.UserGroup"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<g:applyLayout name="app">
    <head>
        <g:set var="entityName" value="${message(code:'userGroup.title', default: 'User Group')}"/>
        <title><g:message code="default.create.label" args="[entityName]"/></title>

        <script type="text/javascript">
            function submitForm()
            {
                document.userGroupForm.submit();
            }


        </script>

    </head>
    <body>

		<content tag="rightTag">

            <div class="bread_crumbs_ui_div" style="width: 611px">
                <ul id="crumbs_ui_custom">
                    <li><g:link controller="application" action="index">Dashboard</g:link></li>
                    <li><g:link controller="security" action="userGroupList">Group List</g:link></li>
                    <li>Create User Group</li>

                </ul>
            </div>
            <br class="clear" />

            <div class="widget">

                <div class="header" style="width: 610px;">
                    <span><span class="ico gray home"></span>${type}</span>
                </div>

			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:form name="userGroupForm" controller="security" action="saveUserGroup" method="POST">
				<fieldset class="form">
					<g:render template="user_group_form"/>
                    <div class="grid_4 alpha">&nbsp;&nbsp;</div>
                    <div class="grid_4 omega" style="padding-left: 196px; padding-top: 15px;">
                        %{--<g:submitButton name="create" class="button save btn" value="${message(code: 'default.button.create.label', default: 'Create')}"/>--}%
                        %{--<input class="button reset btn" type="reset" value="Clear" />--}%

                        <a id="userCreate" class="button icon approve" onclick="submitForm()">Save</a>
                        <a id="ff" class="button danger icon remove" onclick="document.userGroupForm.reset()">Clear</a>
                    </div>
				</fieldset>
				<br class="clear"/>

			</g:form>
                </div>
		</content>
    </body>
</g:applyLayout>