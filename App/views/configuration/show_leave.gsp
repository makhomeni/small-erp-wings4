<%--
  Created by IntelliJ IDEA.
  User: Masum
  Date: 2/20/12
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.jabait.hrm.time.Leave"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<g:applyLayout name="app">
    <head>


    </head>

    <body>
    <content tag="titleTag">

    </content>
    <content tag="rightTag">
        <div class="container_16">
            <div class="grid_7 alpha">

                <div class="widget">

                    <div class="header"><span><span class="ico gray genInfo"></span>Leave Information</span></div>

                    <div class="content tableName">

                        <table class="display data_table" >
                            <tbody>


                            <tr>
                                <td >
                                    <div class="msg">

                                        <div class="msg_topic"><strong>Leave Type:</strong></div>
                                    </div>
                                </td>
                                <td >
                                    <div class="msg_date" > ${leaveTypes}</div>

                                </td>
                            </tr>

                            <tr>
                                <td >
                                    <div class="msg">

                                        <div class="msg_topic"><strong>Leave Duration:</strong></div>
                                    </div>
                                </td>
                                <td >
                                    <div class="msg_date" >${leaveDurations}</div>

                                </td>
                            </tr>

                            <tr>
                                <td >
                                    <div class="msg">

                                        <div class="msg_topic"><strong>leaveNotes:</strong></div>
                                    </div>
                                </td>
                                <td >
                                    <div class="msg_date" >${leaveNotess}</div>

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