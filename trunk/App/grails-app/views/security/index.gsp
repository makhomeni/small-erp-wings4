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
    </head>
    <body>

    <content tag="bannerTag">
        <h1>${type}</h1>
    </content>
    <content tag="rightTag">
        <br class="clear"/>
        <div id="feature_list" class="content scaffold-list" role="main">
            <!-- The navigation bar -->
            <div class="pad20">
                <!-- Big buttons -->
                <ul class="dash">
                    <li>
                        <g:if test="${securityService.isAllowedForUser(session)}">
                            <g:link name="user" controller="security" action="userList" class="tooltip" title="User">
                                <img src="${resource(dir: 'images', file: 'assets/icons/user_icon.png')}" alt="" />
                                <span>User List</span>
                            </g:link>
                        </g:if>
                    </li>
                    <li>
                        <g:if test="${securityService.isAllowedForAuthority(session)}">
                            <g:link name="authority" controller="security" action="authorityList" class="tooltip" title="Authority">
                                <img src="${resource(dir: 'images', file: 'assets/icons/authority_icon.png')}" alt="" />
                                <span>Authority List</span>
                            </g:link>
                        </g:if>
                    </li>
                    <li>
                        <g:if test="${securityService.isAllowedForFeature(session)}">
                            <g:link name="feature" controller="security" action="featureList" class="tooltip" title="Feature">
                                <img src="${resource(dir: 'images', file: 'assets/icons/feature_icon.png')}" alt="" />
                                <span>Feature List</span>
                            </g:link>
                        </g:if>
                    </li>
                    <li>
                        <g:if test="${securityService.isAllowedForUserGroup(session)}">
                            <g:link name="userGroup" controller="security" action="userGroupList" class="tooltip" title="User Group">
                                <img src="${resource(dir: 'images', file: 'assets/icons/user_group_icon.png')}" alt="" />
                                <span>User Group List</span>
                            </g:link>
                        </g:if>
                    </li>
                </ul>
                <!-- End of Big buttons -->
            </div>
        </div>
    </content>
    </body>
</g:applyLayout>