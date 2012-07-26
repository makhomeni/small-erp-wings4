<%--
  Created by IntelliJ IDEA.
  User: Masum
  Date: 2/20/12
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.jabait.hrm.time.LateThreshold"%>
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

                    <div class="header"><span><span class="ico gray attendance"></span>Late Threshold</span></div>

                    <div class="content tableName">

                        <table class="display data_table" >
                            <tbody>


                            <tr>
                                <td >
                                    <div class="msg">

                                        <div class="msg_topic"><strong>Threshold Type:</strong></div>
                                    </div>
                                </td>
                                <td >
                                    <div class="msg_date" > ${lateThresholdType}</div>

                                </td>
                            </tr>

                            <tr>
                                <td >
                                    <div class="msg">

                                        <div class="msg_topic"><strong>Late Threshold:</strong></div>
                                    </div>
                                </td>
                                <td >
                                    <div class="msg_date" >${lateThresholds}</div>

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