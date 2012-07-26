<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/20/12
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.jabait.security.Authority"%>
<%@ page import="com.jabait.security.UserGroup"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<g:applyLayout name="app">
    <head>

        <title>User Details</title>
        
        <style type="text/css">

            .msg_topic{
                width: 332px;
            }

        </style>
        
    </head>

    <body>
    <content tag="titleTag">

    </content>
    <content tag="rightTag">
    <div class="container_16">
    <div class="grid_7 alpha">

        <div class="widget">

            <div class="header"><span><span class="ico gray authority"></span>Authority</span></div>

            <div class="content tableName">

                <table class="display data_table" >
                    <tbody>
                    <g:if test="${!authorities}">
                        <tr>
                            <td >
                                <div class="msg">

                                    <div class="msg_topic">No authorities assigned to this user </div>
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

            </div><!-- End content -->
        </div><!--end widget -->
       </div><!--end left column -->
          %{--right column--}%
    <div class="grid_7 omega" >

        <!-- full width -->
        <div class="widget">
            <div class="header"><span><span class="ico gray userGroup"></span>User Group</span></div>

        <div class="content tableName">
            <table class="display data_table" >
                <tbody>

                <g:if test="${!userGroups}">
                    <tr>
                        <td >
                            <div class="msg">

                                <div class="msg_topic">No user group assigned to this user </div>
                            </div>
                        </td>

                    </tr>
                </g:if>
                      <g:each in="${userGroups}" var="userGroupList">
                          <g:each in="${userGroupList}" var="userGroup">

                        <tr>
                            <td >
                                <div class="msg">

                                    <div class="msg_topic"><strong>${fieldValue(bean: userGroup, field: "groupName")}</strong></div>
                                </div>
                            </td>

                        </tr>

                          </g:each>
                      </g:each>

                </tbody>
            </table>

        </div><!-- End content -->

        </div><!-- End full width -->





    </div><!-- End column_right -->

    </div>




    </content>
    </body>

</g:applyLayout>