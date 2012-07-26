<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/16/12
  Time: 2:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.jabait.security.Feature"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<g:applyLayout name="app">

    <head>

        <title>${type}</title>

        <script type="text/javascript">
            function submitForm()
            {
                document.featureForm.submit();
            }


        </script>


    </head>
    <body>


    <content tag="rightTag">

        <div class="bread_crumbs_ui_div" style="width: 611px">
            <ul id="crumbs_ui_custom">
                <li><g:link controller="application" action="index">Dashboard</g:link></li>
                <li><g:link controller="security" action="featureList">Feature List</g:link></li>
                <li>Create Feature</li>
            </ul>
        </div>
        <br class="clear" />

        <script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.uniform.js')}" ></script>



        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'uniform.aristo.css')}">
        <style>
        div.selector{
            margin-top: -20px;
            margin-left: 156px;
        }
        </style>
        <script>
            $(document).ready(function(){
                $("input, textarea, select, button").uniform();
            })
        </script>
        <div class="widget">

            <div class="header" style="width: 610px;">
                <span><span class="ico gray home"></span>${type}</span>
            </div>
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <g:form name="featureForm" controller="security" action="saveFeature" method="POST">
            <fieldset class="form">
                <g:render template="feature_form"/>
                <div class="grid_4 alpha">&nbsp;&nbsp;</div>
                <div class="grid_4 omega" style="padding-left: 198px; padding-top: 20px">


                    <a id="featureForm" class="button icon approve" onclick="submitForm()">Save</a>
                    <a id="ff" class="button danger icon remove" onclick="document.userGroupForm.reset()">Clear</a>
                </div>
            </fieldset>
            <br class="clear"/>

        </g:form>
            </div>
    </content>
    </body>
</g:applyLayout>