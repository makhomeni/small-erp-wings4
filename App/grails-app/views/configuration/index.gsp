<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/19/12
  Time: 3:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.jabait.security.SecurityService" contentType="text/html;charset=UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<g:applyLayout name="app">
    <head>
        <meta name="layout" content="page">
        <title>Ocean Erp</title>
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'core.css')}" type="text/css">
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'skin_clean.css')}" type="text/css">
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'plugins.css')}" type="text/css">
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'css3.css')}" type="text/css">
        <style type="text/css">
            .widget{
                width: 700px;
                padding-left:300px;
            }

        </style>
    </head>
    <body>

    <content tag="rightTag">

        <div id="feature_list" class="content scaffold-list" role="main" style="width: 625px;">
            <!-- The navigation bar -->

                <!-- Big buttons -->
                <ul class="dash">
                    <li>
                        <g:link name="configuration" controller="configuration" action="createOrganization" class="tooltip" title="Organization">
                            <img src="${resource(dir: 'images', file: 'assets/icons/organization.png')}" alt="" />
                            <span>Organization</span>
                        </g:link>
                    </li>
                    <li>
                        <g:link name="configuration" controller="configuration" action="createDepartment" class="tooltip" title="Department">
                            <img src="${resource(dir: 'images', file: 'assets/icons/department.png')}" alt="" />
                            <span>Department</span>
                        </g:link>
                    </li>
                </ul>
                <!-- End of Big buttons -->

        </div>
    </content>
    </body>
</g:applyLayout>