<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/22/12
  Time: 11:00 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" media="screen" href="${resource(dir: 'css', file: 'login_new_style.css')}">
    <!--[if IE 9]>
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'ie9_login.css')}">
    <![endif]-->

    <!--[if IE 8]>
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'ie8_login.css')}">
    <![endif]-->

    <!--[if IE 7]>
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'ie7_login.css')}">
    <![endif]-->

    <script src="${resource(plugin: 'jquery', file: 'js/jquery/jquery-1.7.1.min.js')}" type="text/javascript"></script>
    <script src="${resource(dir: 'js', file: 'login_gen.js')}" type="text/javascript"></script>
    <script>
        jQuery(document).ready(function() {
            jQuery("#loginform").each (function(){
                this.reset();
            });
            jQuery("#userCode").val("")
            jQuery("#password").val("")
            jQuery("#password").bind("click", function(){
                 $(this).val("")
            })
        });
    </script>
</head>
<body>

<div class="body">
    <div class="loginlogo">
        <img src="${resource(dir: 'images', file: 'logo.png')}" alt="Logo" style="padding-left: 80px;">
    </div>
    <g:if test="${flash.message}">
        <div class="notification notifyError loginNotify">${flash.message}</div>
    </g:if>
    <g:form name="loginform" controller="auth" action="authenticateUser" method="post" >

        <div class="loginbox">
            <div class="loginbox_inner">
                <div class="loginbox_content">
                    <input type="text" id="login" name="userCode" class="username" style="background-position: 0px 0px; " value="">
                    <input type="password" name="password" class="password" style="background-position: 0px 0px; " value="">
                    <button name="submit" class="submit">Login</button>
                </div><!--loginbox_content-->
            </div><!--loginbox_inner-->
        </div><!--loginbox-->

        <div class="loginoption">
            <a href="" class="cant">Can't access your account?</a>

            <input type="checkbox" name="remember"> Remember me on this computer.
        </div><!--loginoption-->
    </g:form>
</div>
<r:layoutResources />
</body>
</html>