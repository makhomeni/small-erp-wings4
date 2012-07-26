<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/20/12
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.jabait.hrm.time.Leave; com.jabait.security.Authority"%>
<%@ page import="com.jabait.security.UserGroup"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<g:applyLayout name="app">
<head>
    <title>${titleOfPage}</title>
    <style type="text/css">
    .label{
        padding: 15px 10px!important;
    }
    .label:hover{
        background-color:#eaf7ff;
    }
    .adjustImg{
        margin-left: -6px;
    }

    </style>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'jscal2.js')}"></script>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'en.js')}"></script>

    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'border-radius.css')}"/>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'jscal2.css')}"/>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'steel.css')}"/>
    <script type="text/javascript">
        var userDataStore;
        var checkBoxSelMod;
        var userListingEditorGrid;
        var userSelectWindow;

        Ext.onReady(function(){
            userDataStore = new Ext.data.Store({
                id: "userDataStore",
                url: '${createLink(action: 'employeeJsonData', controller: 'employee')}',
                reader: new Ext.data.JsonReader({
                    root: 'employees',
                    totalProperty: 'totalCount',
                    id: 'id'
                },[
                    {name: 'id', type: 'int', mapping: 'id'},
                    {name: 'userCode', type: 'string', mapping: 'userCode'},
                    {name: 'active', type: 'boolean', mapping: 'active'}
                ]),
                autoLoad : true
            })




            checkBoxSelMod = new Ext.grid.CheckboxSelectionModel();
            userListingEditorGrid = new Ext.grid.EditorGridPanel({
                id: "userListingEditorGrid",
                store : userDataStore,
                selModel : checkBoxSelMod,
                clicksToEdit: 2,

                width: 565,
                height: 380,
                columns : [
                    {
                        dataIndex: 'id',
                        header: 'ID',
                        width: 80
                    },{
                        dataIndex: 'userCode',
                        header: 'Username',
                        sortable: true,
                        width:200,
                        editable: false
                    },{
                        dataIndex: 'active',
                        header: 'Active',
                        sortable: true,
                        width: 70
                    }
                ],
                stripeRows : true,
                bbar: new Ext.PagingToolbar({
                    store : userDataStore,
                    pageSize : 10,
                    displayInfo : true,
                    displaymsg : 'Displaying {0} - {1} of {2}',
                    emptyMsg : "No records found"
                }),

                listeners: {
                    "cellclick" : function(grid, rowIndex, columnIndex, e){

                        var selectedUser = userListingEditorGrid.getSelectionModel().getSelections();

                        Ext.each(selectedUser, function(sel) {

                            $("#employeeId").val(sel.get('userCode'))
                            //to show department selection option


                            userSelectWindow.hide();
                        });


                        departmentStore.load({
                            params : {start: 0, limit: 10}
                        })
                    }
                }
            });
            userDataStore.load({params: {start: 0, limit: 10}});

            userSelectWindow = new Ext.Window({
                id: 'userSelectWindow',
                title: 'Select Organization for Employee',
                closable: false,
                width: 400,
                height: 400,
                plain:true,
                modal:true,
                layout: 'fit',
                resizable: false,
                items: userListingEditorGrid,
                buttons:[
                    {
                        text: 'Cancel',
                        handler: function(){
                            userSelectWindow.hide();
                        }
                    }
                ]
            });


        })

    </script>
</head>

<body>
<content tag="titleTag">

</content>
<content tag="rightTag">

    <div class="bread_crumbs_ui_div" style="width: 701px">
        <ul id="crumbs_ui_custom">
            <li><g:link controller="application" action="index">Dashboard</g:link></li>
            <li><g:link controller="payroll" action="home">Leave Home</g:link></li>
            <li><g:link controller="payroll" action="advancePayRegisterList">Advance Pay Register List</g:link></li>
            <li>Advance Pay Register Request</li>
        </ul>
    </div>

    <br class="clear"/>


    <g:if test="${flash.message}">
        <div class="message" role="status" style="width: 518px; margin-left: 0px;">${flash.message}</div>
    </g:if>

    <div class="container_16">
        <div class="grid_12 alpha">

            <div class="widget">



                <div class="header">
                    <span><span class="ico gray advRequest"></span>&nbsp;&nbsp;Advance Payment Request</span>
                </div>
                <g:set var="userName" value="${session?.user?.userCode}"></g:set>

                %{--alert(${session?.user?.userCode})--}%
                <div class="content tableName" style="height: 520px;">
                    <g:form name="advanceRequestForm" controller="payroll" action="saveAdvancePayRegister">
                        <input type="hidden" name="_name" value="advance_pay_register"/>
                        <input type="hidden" name="_file" value="voucher.jasper"/>
                        <input type="hidden" name="_format" value="PDF"/>
                        <g:if test="${userName.equals("admin")}">
                            <div class="grid_8 label">
                                <div class="grid_3 alpha msg_text">
                                    <g:message code="leave.employee.id" default="Employee Id"></g:message>
                                </div>
                                <div class="grid_4 omega">
                                    <div>
                                        <g:textField name="employeeId" id="employeeId"></g:textField>
                                    </div>
                                    <div style="margin-left: 220px; margin-top: -30px;">
                                        <img src="${resource(dir: 'images', file: 'employee.png')}" alt="employee" id="employeeImg" class="adjustImg" onclick="userSelectWindow.show()" title="Click to select employee">
                                    </div>
                                </div>
                            </div>
                        </g:if>

                        <g:hiddenField name="loginUserId" id="loginUserId" value="${session?.user?.userCode}"></g:hiddenField>

                        <div class="grid_8 label">
                            <div class="grid_3 alpha msg_text">
                                <g:message code="authorizer" default="Authorizer" ></g:message>
                            </div>
                            <div class="grid_4 omega">
                                <g:textField name="authorizedId" id="authorizedId" value="${session?.user?.userCode}" readonly="true"></g:textField>
                            </div>
                        </div><div class="grid_8 label">
                            <div class="grid_3 alpha msg_text">
                                <g:message code="advance.payroll.amount" default="Advance Amount"></g:message>
                            </div>
                            <div class="grid_4 omega">
                                <g:textField name="advanceAmount" id="advanceAmount"></g:textField>
                            </div>
                        </div>
                        <div class="grid_8 label">
                            <div class="grid_3 alpha msg_text">
                                <g:message code="advance.request.date" default="Requested Date"></g:message>
                            </div>
                            <div class="grid_4 omega">
                                <g:textField name="requestedDate" readonly="readonly" id="requestedDate"></g:textField>
                            </div>
                            <img src="${resource(dir: 'images/calendar', file: 'calendar_add.png')}" alt="calender" id="showCalendarDateOfJoin" class="adjustImg" title="Click to select date of Join">
                            <script type="text/javascript">
                                //<![CDATA[
                                var toDate;
                                var fromDate = Calendar.setup({
                                    inputField : "requestedDate",
                                    trigger    : "showCalendarDateOfJoin",
                                    dateFormat : "%Y-%m-%d",
                                    min: new Date(),
                                    showTime: false,
                                    onSelect: function() {
                                        this.hide();
                                    }
                                });
                                //]]>
                            </script>
                        </div>

                        <div class="grid_8 label">
                            <div class="grid_3 alpha msg_text">
                                <g:message code="leave.purpose.label" default="Purpose"></g:message>
                            </div>
                            <div class="grid_4 omega">
                                %{--<g:textField name="leaveType"></g:textField>--}%
                                <g:checkBox name="leavePurpose" value="${false}"></g:checkBox>&nbsp;Marriage/Family/Education
                                <br />
                                <br />
                                <g:checkBox name="leavePurpose" value="${false}"></g:checkBox>&nbsp;Medical/Accident
                                <br />
                                <br />
                                <g:checkBox name="leavePurpose" value="${false}"></g:checkBox>&nbsp;Other
                            </div>


                        </div>


                    </g:form>
                    <br class="clear" />
                    <br class="clear" />

                    <div class="grid_8" style="margin-left: 200px;">
                        <a id="leaveRequestSave" class="button icon approve" onclick="submitForm()">Save</a>
                        <a id="clearForm" class="button danger icon remove" onclick="document.leaveRequestForm.reset()">Clear</a>
                    </div>


                </div>

                %{----}%
                <script type="text/javascript">
                    function submitForm(){
                        document.advanceRequestForm.submit();
                    }

                </script>
                <script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.uniform.js')}" ></script>

                <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'uniform.aristo.css')}">
                <script>
                    $(document).ready(function(){
                        $("input, textarea, select, button").uniform();
                    })
                </script>




            </div><!--end widget -->
        </div><!--end left column -->

    %{--left column--}%

    %{--<div class="grid_7 omega">--}%

    %{--<div class="widget">--}%

    %{--<div class="header">--}%
    %{--<span><span class="ico gray organization"></span>Organization Information</span>--}%
    %{--<span style="margin-left: 140px;"><a id="editOrganizationInfo"><img id="editOrganizationInfoImg" src="${resource(dir:'images',file: 'edit.png')}" alt="Edit" /></a></span>--}%
    %{--</div>--}%


    %{--</div><!--end widget -->--}%
    %{--</div><!--end left column -->--}%

        <br class="clear">



</content>
</body>

</g:applyLayout>