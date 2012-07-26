<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/20/12
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.jabait.security.Authority"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<g:applyLayout name="app">
    <head>
        <g:set var="entityName" value="${message(code:'authority.description', default: 'Authority')}"/>
        <title><g:message code="default.create.label" args="[entityName]"/></title>

        <script type="text/javascript">
            function submitForm()
            {
                document.authorityForm.submit();
            }


        </script>

    </head>

    <body>

    <content tag="rightTag">
        <div class="bread_crumbs_ui_div" style="width: 611px">
            <ul id="crumbs_ui_custom">
                <li><g:link controller="application" action="index">Dashboard</g:link></li>
                <li><g:link controller="security" action="authorityList">Authority List</g:link></li>
                <li>Authority Create</li>
            </ul>
        </div>

        <br class="clear" />


        <div class="widget">

            <div class="header" style="width: 610px;">
                <span><span class="ico gray authority"></span>${type}</span>
            </div>



                <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                </g:if>


               <g:form name="authorityForm" controller="security" action="saveAuthority" method="POST">
                    <fieldset class="form">
                        <g:render template="authority_form"/>

                        <div class="grid_4 alpha">&nbsp;&nbsp;</div>
                        <div class="grid_4 omega" style="padding-left: 194px;padding-top: 15px">


                            <a id="authorityForm" class="button icon approve" onclick="submitForm()">Save</a>
                            <a id="ff" class="button danger icon remove" onclick="document.authorityForm.reset()">Clear</a>

                        </div>

                    </fieldset>
                    <br class="clear"/>

                </g:form>


            </div>
    </content>
    </body>

</g:applyLayout>