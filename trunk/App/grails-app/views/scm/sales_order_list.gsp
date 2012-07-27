<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/19/12
  Time: 3:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="grails.converters.JSON; com.jabait.security.User"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<g:applyLayout name="app">
    <head>
        <g:set var="entityName" value="${message(code: 'user.label', default:'User')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <script>


            function deleteRec(id){

                Ext.Msg.show({
                    title:'Delete User?',
                    msg: 'You are deleting a user. Would you like to delete?',
                    maxWidth:400,
                    buttons: Ext.Msg.YESNOCANCEL,
                    fn: function(btn){
                        if(btn == 'yes'){

                            window.location = "${createLink(controller:'inventory', action: 'deleteProduct')}"+"/"+id;
                        }
                    }
                });
            }

            Ext.onReady(function(){
            })


        </script>
        <style type="text/css">
        .header{
            width: 609px;
        }
        .widget{
            width: 609px;
        }

        </style>
    </head>
    <body>
    %{--<content tag="bannerTag"><h1>${type}</h1></content>--}%


    <content tag="rightTag">

        <div class="bread_crumbs_ui_div" style="width: 610px">
            <ul id="crumbs_ui_custom">
                <li><g:link controller="application" action="index">Dashboard</g:link></li>
                <li>User List</li>
            </ul>
        </div>
        <br class="clear"/>

        <div class="widget">

            <div class="header">
                <span><span class="ico gray user"></span>${type}</span>
            </div>

            <g:if test="${flash.message}">
                <div class="message" role="status" style="width: 605px; margin-left: 0px;">${flash.message}</div>
                <br class="clear">
            </g:if>
            <div id="list_user" class="content scaffold-list" role="main">

                <div id="search" style="margin-bottom: 13px;">
                    <label for="userCode">
                        User Code:
                        <g:textField name="userCode" id="userCode"></g:textField>
                    </label>
                    <a id="searchButton" class="button icon approve">Search User</a>
                </div>

            </div>
        </div>
    </content>
    </body>
</g:applyLayout>