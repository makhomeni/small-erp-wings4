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

        <title>Edit Feature</title>

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
                <li>Edit Feature List</li>

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

            <g:form name="featureForm" controller="security" action="updateFeature" method="POST">
                <fieldset class="form">
                    <g:render template="feature_form"/>
                    <g:hiddenField name="id" value="${featureInstance.id}"/>

                    <div class="grid_4 alpha">&nbsp;&nbsp;</div>
                    <div class="grid_4 omega" style="padding-left: 193px;padding-top: 20px;">
                        %{--<g:submitButton name="update" class="button save" value="${message(code: 'default.button.update.label', default: 'Update')}"/>--}%
                        %{--<input class="button reset" type="reset" value="Clear" />--}%

                        <a id="userCreate" class="button icon approve" onclick="submitForm()">Save</a>
                        <a id="ff" class="button danger icon remove" onclick="document.featureForm.reset()">Clear</a>

                    </div>

                </fieldset>
                <br class="clear"/>
            </g:form>
            </div>
    </content>
    </body>
</g:applyLayout>