<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/20/12
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.jabait.scm.inventory.Product"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<g:applyLayout name="app">
    <head>
        <g:set var="entityName" value="${message(code:'product.description', default: 'Product')}"/>
        <title><g:message code="default.create.label" args="[entityName]"/></title>
        <script type="text/javascript" src="${resource(dir: 'js', file: 'livevalidation_standalone.compressed.js')}"></script>
        <r:require modules="bootstrap-file-upload"/>
    </head>
    <body>

    <content tag="rightTag">
        <style>
        .gear{
           margin-top: -11px;
        }
        .disconnect{
            margin-top: -11px;
        }
        </style>
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'misc.style.css')}">
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'accordionmenu.css')}">
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'custom.property.css')}">
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'fixedHeader.css')}">
        <!--[if lte IE 8]>
		<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'fixedHeaderie6.css')}">
		<![endif]-->
        <link  rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'zice.style.css')}">
        <link  rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'icon.css')}">
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'ui-custom.css')}">
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'colorpicker.css')}">
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'elfinder.css')}">
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'ui-custom.css')}">
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'dataTables.css')}">
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'validationEngine.jquery.css')}">
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'jscrollpane.css')}">
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'tipsy.css')}">
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'jquery.cleditor.css')}">
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'chosen.css')}">
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'jquery.confirm.css')}">
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'sourcerer.css')}">
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'grid.css')}">

        <script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.uniform.js')}" ></script>


        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'uniform.aristo.css')}">

        <div class="bread_crumbs_ui_div" style="width: 901px">
            <ul id="crumbs_ui_custom">
                <li><g:link controller="application" action="index">Dashboard</g:link></li>
                <li><g:link controller="inventory" action="productList">Product List</g:link></li>
                <li>${type}</li>
            </ul>
        </div>

        <br class="clear" />

        <div class="widget" style="width: 900px;">

            <script>
                $(document).ready(function(){
                    $('#fileupload').fileupload();
                    $(".logout > .disconnect").css("margin-top", "-11px");
                    $("select").uniform();
                    $(".selector").css("margin-top", "-24px");
                    $(".selector").css("margin-left", "120px");
                })
            </script>

            <div class="header" style="width: 900px;">
                <span><span class="ico gray product"></span>${type}</span>
            </div>

            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <div id="list_user" class="content scaffold-list" role="main" style="width: 850px;">
                <g:form name="productForm" id="productForm" controller="inventory" action="saveProduct" method="POST">
                    <fieldset class="form">
                        <g:render template="../scm/product_form"/>
                        <div class="grid_4 alpha">&nbsp;&nbsp;</div>
                        <div class="grid_4 omega" style="padding-left: 181px;padding-top: 20px;">


                            %{--<g:link action="userGroupDetails" controller="security" class="btn">Link</g:link>--}%
                        </div>
                    </fieldset>
                %{--<br class="clear"/>--}%

                </g:form>
                <bsfu:fileUpload action="upload" controller="inventory"/>
                <bsfu:imageGallery/>
                <a id="productCreate" class="button icon approve" onclick="document.productForm.submit();">Save</a>
                <a id="ff" class="button danger icon remove" onclick="document.productForm.reset()">Clear</a>
            </div>
        </div>
    </content>
    </body>

</g:applyLayout>