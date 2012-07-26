<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/20/12
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.jabait.security.User"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<g:applyLayout name="app">
    <head>
        <g:set var="entityName" value="${message(code:'user.description', default: 'User')}"/>
        <title><g:message code="default.create.label" args="[entityName]"/></title>
        <script type="text/javascript" src="${resource(dir: 'js', file: 'livevalidation_standalone.compressed.js')}">

        </script>
        <script type="text/javascript">

            $(document).ready(function(){

                var liveValidation = new LiveValidation('confirmPassword');
//                alert(liveValidation)
                liveValidation.add( Validate.Confirmation, { match: 'password' } );
            })

        </script>
        <script type="text/javascript">
            function submitForm()
            {
                document.userForm.submit();
            }
        </script>

    </head>
    <body>

        <content tag="rightTag">

            <div class="bread_crumbs_ui_div" style="width: 611px">
                <ul id="crumbs_ui_custom">
                    <li><g:link controller="application" action="index">Dashboard</g:link></li>
                    <li><g:link controller="security" action="userList">User List</g:link></li>
                    <li>User Create</li>
                </ul>
            </div>

            <br class="clear" />

            <div class="widget">



                <div class="header" style="width: 610px;">
                    <span><span class="ico gray user"></span>${type}</span>
                </div>

                <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                </g:if>

                <div id="list_user" class="content scaffold-list" role="main" style="width: 560px;">
                <g:form name="userForm" id="userForm" controller="security" action="saveUser" method="POST">
                    <fieldset class="form">
                        <g:render template="user_form"/>
                        <div class="grid_4 alpha">&nbsp;&nbsp;</div>



                        <div class="grid_4 omega" style="padding-left: 181px;padding-top: 20px;">
                            %{--<g:submitButton name="create" class="button save btn" value="${message(code: 'default.button.create.label', default: 'Create')}"/>--}%
                            %{--<input class="button reset" type="reset" value="Clear" />--}%

                            <a id="userCreate" class="button icon approve" onclick="submitForm()">Save</a>
                            <a id="ff" class="button danger icon remove" onclick="document.userForm.reset()">Clear</a>
                            %{--<g:link action="userGroupDetails" controller="security" class="btn">Link</g:link>--}%
                        </div>
                    </fieldset>
                    %{--<br class="clear"/>--}%

                </g:form>
                </div>
           </div>
        </content>
    </body>

</g:applyLayout>