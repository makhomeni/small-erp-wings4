<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/20/12
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.jabait.scm.ContactPerson"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<g:applyLayout name="app">
    <head>
        <g:set var="entityName" value="${message(code:'product.description', default: 'User')}"/>
        <title><g:message code="default.create.label" args="[entityName]"/></title>
        <script type="text/javascript" src="${resource(dir: 'js', file: 'livevalidation_standalone.compressed.js')}"></script>

    </head>
    <body>

    <content tag="rightTag">

        <div class="bread_crumbs_ui_div" style="width: 611px">
            <ul id="crumbs_ui_custom">
                <li><g:link controller="application" action="index">Dashboard</g:link></li>
                <li><g:link controller="job" action="salesOrderList">Sales Order List</g:link></li>
                <li>${type}</li>
            </ul>
        </div>

        <br class="clear" />

        <div class="widget">



            <div class="header" style="width: 610px;">
                <span><span class="ico gray product"></span>${type}</span>
            </div>

            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <div id="list_user" class="content scaffold-list" role="main" style="width: 560px;">
                <g:form name="productForm" id="productForm" controller="inventory" action="saveProduct" method="POST">
                    <fieldset class="form">
                        <g:render template="product_form"/>
                        <div class="grid_4 alpha">&nbsp;&nbsp;</div>



                        <div class="grid_4 omega" style="padding-left: 181px;padding-top: 20px;">

                            <a id="productCreate" class="button icon approve" onclick="document.productForm.submit();">Save</a>
                            <a id="ff" class="button danger icon remove" onclick="document.productForm.reset()">Clear</a>
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