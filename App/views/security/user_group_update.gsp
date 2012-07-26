<%--
  Created by IntelliJ IDEA.
  User: shohag
  Date: 3/15/12
  Time: 4:44 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.jabait.security.UserGroup"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<g:applyLayout name="app">
    <head>

        <title>Edit User</title>

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
                <li><g:link controller="security" action="editUserGroup">Edit User Group</g:link></li>

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
        <g:form name="userGroupForm" controller="security" action="updateUserGroup" method="POST">
            <fieldset class="form">
                <g:hiddenField name="id" value="${userGroupInstance?.id}" />
                <g:render template="user_group_form"/>

                <div class="grid_4 alpha">&nbsp;&nbsp;</div>
                <div class="grid_4 omega" style="padding-left: 195px; padding-top: 12px;">
                    %{--<g:submitButton name="update" class="button save" value="${message(code: 'default.button.update.label', default: 'Update')}"/>--}%
                    %{--<input class="button reset" type="reset" value="Clear" />--}%

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