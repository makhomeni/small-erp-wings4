<%--
  Created by IntelliJ IDEA.
  User: Masum
  Date: 2/20/12
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<g:applyLayout name="app">
    <head>
    </head>

    <body>
    <content tag="titleTag">

    </content>
    <content tag="rightTag">
        <div class="container_16">
            <div class="grid_8 alpha">

                <div class="widget">

                    <div class="header"><span><span class="ico gray genInfo"></span>Organization Information</span></div>

                    <div class="content tableName">

                        <table class="display data_table" >
                            <tbody>


                            <tr>
                                <td >
                                    <div class="msg">

                                        <div class="msg_topic"><strong>Organization Name:</strong></div>
                                    </div>
                                </td>
                                <td >
                                    <div class="msg_date" > ${fieldValue(bean: organization, field: "organizationName")}</div>

                                </td>
                            </tr>

                            <tr>
                                <td >
                                    <div class="msg">

                                        <div class="msg_topic"><strong>Parent Organization:</strong></div>
                                    </div>
                                </td>
                                <td >
                                    <div class="msg_date" >${fieldValue(bean: organization, field: "parent.organizationName")}</div>

                                </td>
                            </tr>

                            <tr>
                                <td >
                                    <div class="msg">

                                        <div class="msg_topic"><strong>Country:</strong></div>
                                    </div>
                                </td>
                                <td >
                                    <div class="msg_date" >${fieldValue(bean: organization, field: "address.country")}</div>

                                </td>
                            </tr>

                            <tr>
                                <td >
                                    <div class="msg">

                                        <div class="msg_topic"><strong>Extended Address:</strong></div>
                                    </div>
                                </td>
                                <td >
                                    <div class="msg_date" >${fieldValue(bean: organization, field: "address.extendedAddress")}</div>

                                </td>
                            </tr>

                            <tr>
                                <td >
                                    <div class="msg">

                                        <div class="msg_topic"><strong>Po.Box:</strong></div>
                                    </div>
                                </td>
                                <td >
                                    <div class="msg_date" >${fieldValue(bean: organization, field: "address.poBox")}</div>

                                </td>
                            </tr>

                            <tr>
                                <td >
                                    <div class="msg">

                                        <div class="msg_topic"><strong>Postal Code:</strong></div>
                                    </div>
                                </td>
                                <td >
                                    <div class="msg_date" >${fieldValue(bean: organization, field: "address.postalCode")}</div>

                                </td>
                            </tr>

                            <tr>
                                <td >
                                    <div class="msg">

                                        <div class="msg_topic"><strong>Region:</strong></div>
                                    </div>
                                </td>
                                <td >
                                    <div class="msg_date" >${fieldValue(bean: organization, field: "address.region")}</div>

                                </td>
                            </tr>

                            <tr>
                                <td >
                                    <div class="msg">

                                        <div class="msg_topic"><strong>Street Address:</strong></div>
                                    </div>
                                </td>
                                <td >
                                    <div class="msg_date" >${fieldValue(bean: organization, field: "address.streetAddress")}</div>

                                </td>
                            </tr>

                            <tr>
                                <td >
                                    <div class="msg">

                                        <div class="msg_topic"><strong>E-Mail:</strong></div>
                                    </div>
                                </td>
                                <td >
                                    <div class="msg_date" >${fieldValue(bean: organization, field: "email.address")}</div>

                                </td>
                            </tr>

                            <tr>
                                <td >
                                    <div class="msg">

                                        <div class="msg_topic"><strong>Phone:</strong></div>
                                    </div>
                                </td>
                                <td >
                                    <div class="msg_date" >${fieldValue(bean: organization, field: "phone.number")}</div>

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