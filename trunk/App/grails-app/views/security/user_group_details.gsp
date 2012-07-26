<%--
  Created by IntelliJ IDEA.
  User: Md Dablu Hossain
  Date: 4/17/12
  Time: 1:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.jabait.security.Authority"%>
<%@ page import="com.jabait.security.User" %>
<%@page import="com.jabait.security.Feature" %>
<%@ page import="com.jabait.security.UserGroup"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<g:applyLayout name="app">
    <head>

        <g:set var="entityName" value="${userGroupInstance.groupName}"/>
        <title><g:message code="userGroup.details"/></title>
    </head>
    <body>
    <content tag="titleTag">

    </content>
    <content tag="rightTag">

        <div class="bread_crumbs_ui_div" style="width: 819px">
            <ul id="crumbs_ui_custom">
                <li><g:link controller="application" action="index">Dashboard</g:link></li>
                <li><g:link controller="security" action="userGroupList">Group List</g:link></li>
                <li>User Group Details</li>
            </ul>
        </div>
        <br class="clear" />

        <div class="container_16">
            <div class="grid_7 alpha">
                <div class="widget">
                    <div class="header">
                        <span><span class="ico gray authority"></span>Authority</span>
                    </div>
                    <div class="content tableName">
                        <table class="display data_table" >
                            <tbody>
                            <g:if test="${!authorities}">
                                <tr>
                                    <td >
                                        <div class="msg">

                                            <div class="msg_topic">No authorities assigned to this group </div>
                                        </div>
                                    </td>

                                </tr>
                            </g:if>
                            <g:each in="${authorities}" var="authorityList">
                                <g:each in="${authorityList}" var="authority">
                                    <tr>
                                        <td >
                                            <div class="msg">

                                                <div class="msg_topic"><strong>${fieldValue(bean: authority, field: "roleTitle")}</strong> </div>
                                            </div>
                                        </td>

                                    </tr>
                                </g:each>
                            </g:each>

                            </tbody>

                        </table>

                    </div>

                </div>
            </div>
            <div class="grid_7 omega">
                <div class="widget">
                    <div class="header">
                        <span><span class="ico gray user"></span>User</span>
                    </div>
                    <div class="content tableName">
                        <table class="display data_table" >
                            <tbody>

                            <g:if test="${!users}">
                                <tr>
                                    <td >
                                        <div class="msg">

                                            <div class="msg_topic">No user assigned to this group </div>
                                        </div>
                                    </td>

                                </tr>
                            </g:if>
                            <g:each in="${users}" var="userList">
                                <g:each in="${userList}" var="user">

                                    <tr>
                                        <td ><div class="msg">

                                            <div class="msg_topic"><strong>${fieldValue(bean: user, field: "userCode")}</strong></div>
                                        </div>
                                        </td>

                                    </tr>

                                </g:each>
                            </g:each>

                            </tbody>
                        </table>

                    </div>
                </div>
            </div>
        </div>

    </content>
    </body>
</g:applyLayout>