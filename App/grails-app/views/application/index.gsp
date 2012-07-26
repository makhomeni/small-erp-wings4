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
        <title>${title}</title>
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'core.css')}" type="text/css">
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'skin_clean.css')}" type="text/css">
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'plugins.css')}" type="text/css">
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'css3.css')}" type="text/css">
       <style type="text/css">
           .logo{
               margin-left: 200px;
           }

           #shortcut{
               padding-right: 499px;
           }
           .container_16{
               margin-left: 238px;
               margin-top: 13px;
           }


           .msg_topic{
               width: 160px;
           }
           .msg_date{
               width: 157px;
           }

       </style>


    </head>
    <body>


    <content tag="rightTag">
        <div class="container_16">

            <div class="grid_7 alpha">

                <div class="widget">
                    <div class="header">
                        <span><span class="ico gray leave"></span>&nbsp;&nbsp;Leave Information</span>
                    </div>

                    <!--start table-->
                    <div class="content tableName" >

                        <table class="display data_table" >
                            <tbody>

                            <tr>
                                <td >
                                    <div class="msg">

                                        <div class="msg_topic"><strong>No. of Leave</strong></div>
                                    </div>
                                </td>
                                <td >
                                    <div class="msg_date" > </div>

                                </td>
                            </tr>


                            <tr>
                                <td >
                                    <div class="msg">

                                        <div class="msg_topic"><strong>No. of Accepted Leave</strong></div>
                                    </div>
                                </td>
                                <td >
                                    <div class="msg_date"  ></div>

                                </td>
                            </tr>
                            <tr>
                                <td >
                                    <div class="msg">

                                        <div class="msg_topic"><strong>No. of Rejected Leave</strong></div>
                                    </div>
                                </td>
                                <td >
                                    <div class="msg_date" ></div>

                                </td>
                            </tr>



                            </tbody>

                        </table>

                    </div><!-- End content -->
                    <!--end table-->


                </div>

            </div>  <!--End left column-->

            <div class="grid_7 alpha">

                <div class="widget">
                    <div class="header">
                        <span><span class="ico gray attendance bio"></span>&nbsp;&nbsp;Attendance Information</span>
                    </div>

                    <!--start table-->
                    <div class="content tableName" >

                        <table class="display data_table" >
                            <tbody>

                            <tr>
                                <td >
                                    <div class="msg">

                                        <div class="msg_topic"><strong>No. of Present Employee</strong></div>
                                    </div>
                                </td>
                                <td >
                                    <div class="msg_date" > </div>

                                </td>
                            </tr>

                            <tr>
                                <td >
                                    <div class="msg">

                                        <div class="msg_topic"><strong>No. of Late Employee</strong></div>
                                    </div>
                                </td>
                                <td >
                                    <div class="msg_date" > </div>

                                </td>
                            </tr>


                            <tr>
                                <td >
                                    <div class="msg">

                                        <div class="msg_topic"><strong>No. Of Absent Employee</strong></div>
                                    </div>
                                </td>
                                <td >
                                    <div class="msg_date" ></div>

                                </td>
                            </tr>



                            </tbody>

                        </table>

                    </div><!-- End content -->
                <!--end table-->


                </div>

            </div>  <!--End left column-->

        </div>        <!--end cotainer 16-->

    </content>
    </body>
</g:applyLayout>