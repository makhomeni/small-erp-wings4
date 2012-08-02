<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/20/12
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.jabait.scm.inventory.Category"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<g:applyLayout name="app">
    <head>
        <g:set var="entityName" value="${message(code:'category.description', default: 'Category')}"/>
        <title><g:message code="default.create.label" args="[entityName]"/></title>
        <script type="text/javascript" src="${resource(dir: 'js', file: 'livevalidation_standalone.compressed.js')}"></script>
    </head>
    <body>

    <content tag="rightTag">

        <script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.uniform.js')}" ></script>


        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'uniform.aristo.css')}">

        <div class="bread_crumbs_ui_div" style="width: 901px">
            <ul id="crumbs_ui_custom">
                <li><g:link controller="application" action="index">Dashboard</g:link></li>
                <li><g:link controller="inventory" action="categoryList">Category List</g:link></li>
                <li>${type}</li>
            </ul>
        </div>

        <br class="clear" />

        <div class="widget" style="width: 900px;">

            <script>
                $(document).ready(function(){
                    $("select").uniform();
                    $(".selector").css("margin-top", "-24px");
                    $(".selector").css("margin-left", "215px");
                })
            </script>

            <div class="header" style="width: 900px;">
                <span><span class="ico gray product"></span>${type}</span>
            </div>

            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <div id="list_user" class="content scaffold-list" role="main" style="width: 850px;">
                <g:form name="categoryForm" id="categoryForm" controller="inventory" action="saveCategory" method="POST">
                    <fieldset class="form">
                        <div class="fieldcontain ${hasErrors(bean: category, field: 'category', 'error')} required">
                            <label for="categoryName">
                                <g:message code="category.categoryName.label" default="Category Name" />
                                <span class="required-indicator">*</span>
                            </label>
                            <g:textField name="categoryName" required=""  value="" />
                        </div>

                        <div class="fieldcontain ${hasErrors(bean: category, field: 'parentCategory', 'error')} required">
                            <label for="category">
                                <g:message code="category.parentCategory.label" default="Parent Category" />
                                <span class="required-indicator">*</span>
                            </label>
                            <g:select id="category" name="category" from="${Category.list()}"
                                      optionKey="id" optionValue="categoryName" noSelection="['':'Select Category...']"></g:select>
                        </div>

                        <div class="grid_4 alpha">&nbsp;&nbsp;</div>
                        <div class="grid_4 omega" style="padding-left: 181px;padding-top: 20px;">
                            <a id="categoryCreate" class="button icon approve" onclick="document.categoryForm.submit();">Save</a>
                            <a id="ff" class="button danger icon remove" onclick="document.categoryForm.reset()">Clear</a>
                        </div>
                    </fieldset>
                %{--<br class="clear"/>--}%

                </g:form>
            </div>
        </div>
    </content>
    </body>

</g:applyLayout>