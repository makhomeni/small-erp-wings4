<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/22/12
  Time: 5:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.jabait.security.User"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<g:applyLayout name="app">
    <head>

        <title>${type}</title>
        <script type="text/javascript" src="${resource(dir: 'js', file: 'livevalidation_standalone.compressed.js')}">

        </script>
        <script type="text/javascript">

            $(document).ready(function(){
                var liveValidation = new LiveValidation('confirmPassword');
//                alert(liveValidation)
                liveValidation.add( Validate.Confirmation, { match: 'password' } );
            })

            function submitForm()
            {
                document.userForm.submit();
            }
        </script>

        <style>
        div.selector{
            margin-top: -22px;
            margin-left: 155px;
            margin-bottom: 12px;
        }
        </style>
    </head>
    <body>
    <content tag="bannerTag">
        <h1>${type}</h1>
    </content>

    <content tag="rightTag">

        <div class="bread_crumbs_ui_div" style="width: 610px">
            <ul id="crumbs_ui_custom">
                <li><g:link controller="application" action="index">Dashboard</g:link></li>
                <li><g:link controller="security" action="userList">User List</g:link></li>
                <li>User Edit</li>
            </ul>
        </div>
        <br class="clear" />

        <script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.uniform.js')}" ></script>
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'uniform.aristo.css')}">

        <div class="widget">

            <div class="header" style="width: 610px">
                <span><span class="ico gray user"></span>${type}</span>
            </div>

    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
        <g:form name="userForm" controller="security" action="updateUser" method="POST">
            <fieldset class="form">
                <g:hiddenField name="id" value="${userInstance?.id}" />
                %{----}%

                <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'userCode', 'error')} required">
                    <label for="userCode">
                        User Name
                        <span class="required-indicator">*</span>
                    </label>
                    <g:textField name="userCode" required="" readonly="readonly" value="${userInstance?.userCode}" />

                </div>

                <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
                    <label for="password">
                        User Status
                        <span class="required-indicator">*</span>
                    </label>
                   <g:select name="active" from="${['True', 'False']}"></g:select>
                </div>

                <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
                    <label for="password">
                        New Password
                        <span class="required-indicator">*</span>
                    </label>
                    <g:passwordField name="password" required="" value=""/>
                </div>
                <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
                    <label for="password">
                        Confirm Password
                        <span class="required-indicator">*</span>
                    </label>
                    <g:passwordField name="confirmPassword" required="" value=""/>
                </div>


                <div class="grid_4 alpha">&nbsp;&nbsp;</div>
                <div class="grid_4 omega" style="padding-left: 190px; padding-top: 15px">
                    %{--<g:submitButton name="update" class="button save" value="${message(code: 'default.button.update.label', default: 'Update')}"/>--}%
                    %{--<input class="button reset" type="reset" value="Reset" />--}%

                    <a id="userCreate" class="button icon approve" onclick="submitForm()">Update</a>
                    <a id="ff" class="button danger icon remove" onclick="document.userForm.reset()">Clear</a>
                </div>

            </fieldset>
            <br class="clear"/>


        </g:form>
    <br class="clear"/>

            <script type="text/javascript">
                $(document).ready(function(){
                    $("input, textarea, select, button").uniform();
                })
            </script>


    </content>
    </body>
</g:applyLayout>