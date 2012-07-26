<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/19/12
  Time: 3:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="grails.converters.JSON; com.jabait.hrm.Department"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<g:applyLayout name="app">
    <head>

        <title>${type}</title>
        <style type="text/css">
        .header{
            width: 910px;
        }
        .widget{
            width: 910px;
        }

        </style>

    </head>
    <body>
    %{--<content tag="bannerTag"><h1>${type}</h1></content>--}%

    <content tag="titleTag">

    </content>
    <content tag="rightTag">

        <div class="bread_crumbs_ui_div" style="width: 961px">
            <ul id="crumbs_ui_custom">
                <li><g:link controller="application" action="index">Dashboard</g:link></li>
                <li><g:link controller="calendar" action="calendarHomeConfig">Calendar Home</g:link></li>
                <li>Employee Calendar</li>
            </ul>
        </div>

        <br class="clear"/>

        <div class="widget" style="width:960px;">

            <div class="header" style="width:960px;">
                <span><span class="ico gray calendar"></span>${type}</span>
            </div>

            <g:if test="${flash.message}">
                <div class="container_12">

                    <div class="grid_4">
                    </div>

                    <div class="grid_4 message" role="status">
                        <g:message code="${flash.message}" args="${flash.args}" default="${flash.default}"></g:message>
                    </div>

                    <div class="clear">

                    </div>
                </div>
            </g:if>

            <div id="calendar_system" class="content scaffold-list" role="main" style="width: 910px;">
                <iframe src="${createLink(action: 'employeeCalendarView')}" width="910px" height="500px" frameborder="0"></iframe>
            </div>

        </div>
        %{--end widget--}%

    </content>
    </body>
</g:applyLayout>