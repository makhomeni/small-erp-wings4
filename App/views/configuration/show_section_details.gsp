<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/20/12
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.jabait.hrm.Department"%>
<%@ page import="com.jabait.hrm.Organization"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<g:applyLayout name="app">
    <head>

        <title>${titleOfPage}</title>
    </head>

    <body>
    <content tag="titleTag">

    </content>
    <content tag="rightTag">
        <div class="container_16">
            <div class="grid_7 alpha">

                <div class="widget">

                    <div class="header"><span><span class="ico gray genInfo"></span>Section Information</span></div>

                    <div class="content tableName">

                        <table class="display data_table" >
                            <tbody>


                            <tr>
                                <td >
                                    <div class="msg">

                                        <div class="msg_topic"><strong>Section Name:</strong></div>
                                    </div>
                                </td>
                                <td >
                                    <div class="msg_date" > ${departmentNames}</div>

                                </td>
                            </tr>

                            <tr>
                                <td >
                                    <div class="msg">

                                        <div class="msg_topic"><strong>Department:</strong></div>
                                    </div>
                                </td>
                                <td >
                                    <div class="msg_date" >${parentDepartments}</div>

                                </td>
                            </tr>

                            <tr>
                                <td >
                                    <div class="msg">

                                        <div class="msg_topic"><strong>Organization:</strong></div>
                                    </div>
                                </td>
                                <td >
                                    <div class="msg_date" >${organizations}</div>

                                </td>
                            </tr>

                            </tbody>
                        </table>

                    </div><!-- End content -->

                </div><!-- End full width -->





            </div><!-- End column_right -->

        </div>




    </content>
    </body>

</g:applyLayout>