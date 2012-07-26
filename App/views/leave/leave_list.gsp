<%--
  Created by IntelliJ IDEA.
  User: Shohag-pc
  Date: 5/30/12
  Time: 8:29 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>

<g:applyLayout name="app">
    <head>
        <title>${titleOfPage}</title>

        <script type="text/javascript">

            var leaveListDataStore;
            var leaveListGrid;
            var checkBoxSelectionModel;
            var approveWindow;
            var approveForm;
            var disApproveWindow;
            var disApproveForm;

            var reason;
            var disApproveReason;
            var leaveRegisterId;
            var userId;
            var isEmployee;


            Ext.onReady(function(){

                Ext.Ajax.request({
                    url:'${createLink(controller: "leave", action: "isEmployee")}',
                    params: {
                        userCode:"${session?.user?.userCode}"
                    },
                    success: function(response, opts) {

                        isEmployee = Ext.util.JSON.decode(response.responseText).success;
                    },
                    failure: function(response, opts) {
                        // alert(response)
                    }
                })

                leaveListDataStore = new Ext.data.Store({
                    id: "employeeDataStore",
                    url: '${createLink(controller: 'leave',action: 'leaveJsonData')}',
                    reader: new Ext.data.JsonReader({
                        root: 'leaves',
                        totalProperty: 'totalCount',
                        id: 'id'
                    },[
                        {name: 'id', type: 'int', mapping: 'id'},
                        {name: 'leaveTakerFullName', type: 'string', mapping: 'leaveTakerFullName'},
                        {name: 'remark', type: 'string', mapping: 'remark'},
                        {name: 'applyDate', type: 'string', mapping: 'applyDate'},
                        {name: 'leaveFromDate', type: 'string', mapping: 'leaveFromDate'},
                        {name: 'leaveToDate', type: 'string', mapping: 'leaveToDate'},
                        {name: 'leaveStatus', type: 'string', mapping: 'leaveStatus'}
                    ]),
                    autoLoad : true
                });


                checkBoxSelectionModel= new Ext.grid.CheckboxSelectionModel();
                leaveListGrid = new Ext.grid.EditorGridPanel({
                    id: "leaveListGrid",
                    store : leaveListDataStore,
                    selModel : checkBoxSelectionModel,
                    clicksToEdit: 2,
                    renderTo: "list_leave",
                    width: 720,
                    height: 350,
                    columns : [
                        {
                            dataIndex: 'leaveTakerFullName',
                            header: 'Full Name',
                            width: 140
                        },{
                            dataIndex: 'remark',
                            header: 'Remark',
                            sortable: true,
                            width:150,
                            editable: false
                        },{
                            dataIndex: 'applyDate',
                            header: 'Apply Date',
                            sortable: true,
                            width: 70
                        }
                        ,{
                            dataIndex: 'leaveFromDate',
                            header: 'Leave From',
                            sortable: true,
                            width: 70
                        },{
                            dataIndex: 'leaveToDate',
                            header: 'Leave To',
                            sortable: true,
                            width: 70
                        },{
                            dataIndex: 'leaveStatus',
                            header: 'Status',
                            sortable: true,
                            width: 70
                        }
                        ,{
                            header:'Action',
                            dataIndex: '',
                            width: 120,
                            renderer: function(v, p, record) {

                                %{-- later on add condition if the user is authorized to do the actioon--}%

                                userId = ${session.user.id};
                                var renderText  = "";

                                if(!isEmployee){
                                    renderText = renderText + " <a href='#' onclick='approveWindow.show()'><img title='Approve' src='${resource(dir:'images',file: 'accept_16.png')}'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

                                %{--alert(record.get('id')+" "+${session.user.id})--}%

                                    renderText += " <a id='delete' onclick='disApproveWindow.show()' href='#' ><img title='Disapprove' src='${resource(dir:'images',file: 'reject.png')}'/></a>"


                                    return renderText;
                                }else{
                                    return renderText;
                                }


                            }
                        }
                    ],
                    stripeRows : true,
                    bbar: new Ext.PagingToolbar({
                        store : leaveListDataStore,
                        pageSize : 10,
                        displayInfo : true,
                        displaymsg : 'Displaying {0} - {1} of {2}',
                        emptyMsg : "No records found"
                    }),

                    tbar: [{
                        xtype: "button",
                        text: "Add Leave Request",
                        iconCls: "addRecord" ,
                        handler:function(){
                            window.location="${createLink(controller: 'leave',action: 'leaveRequest')}"
                        }
                    }]
                });
                leaveListDataStore.load({params: {start: 0, limit: 10}});

                approveForm = new Ext.form.FormPanel({
                    items: [
                        {
                            xtype: "textarea",
                            name:"approvedReason",
                            id:"approvedReason",
                            fieldLabel: "Reason",
                            width: 250,
                            height: 120
                        }]
                });

                approveWindow = new Ext.Window({
                    id: 'authorityAssignmentWindow',
                    title: 'Reason',
                    closable: false,
                    width: 400,
                    height: 200,
                    plain:true,
                    modal:true,
                    layout: 'fit',
                    resizable: false,
                    items: approveForm,
                    buttons:[
                        {
                            text: 'Save and Close',
                            handler: function(){
                                reason =  Ext.getCmp('approvedReason').getValue();

                                var itemIndex = leaveListGrid.store.indexOf(leaveListGrid.getSelectionModel().getSelected());
                                leaveRegisterId = leaveListGrid.getStore().getAt(itemIndex).get("id");

                                approveForm.getForm().submit({
                                    url:'${createLink(controller: "leave",action: "leaveAction")}',
                                    params: {
                                        userId: userId,
                                        leaveRegisterId:leaveRegisterId,
                                        reason: reason,
                                        leaveStatus:${1}
                                    },
                                    success: function(response){
                                        Ext.MessageBox.alert("Success", "Leave approved successfully");
                                        leaveListGrid.getStore().reload();
                                        leaveListGrid.getView().refresh();
                                        approveWindow.hide();
                                    },
                                    failure: function(response){
                                        Ext.MessageBox.alert("Failed", "Leave approved failed");
                                        approveWindow.hide();
                                    }
                                })
                            }
                        },{
                            text: 'Cancel',
                            handler: function(){
                                approveWindow.hide();
                            }
                        }
                    ]
                });

                disApproveForm= new Ext.form.FormPanel({
                    items: [
                        {
                            xtype: "textarea",
                            fieldLabel: "Reason",
                            id:"disApprovedReason",
                            name:"disApprovedReason",
                            width: 250,
                            height: 120
                        }]
                });


                disApproveWindow = new Ext.Window({
                    id: 'leavedisApproveWindow',
                    title: 'Reason',
                    closable: false,
                    width: 400,
                    height: 200,
                    plain:true,
                    modal:true,
                    layout: 'fit',
                    resizable: false,
                    items: disApproveForm,
                    buttons:[
                        {
                            text: 'Save and Close',
                            handler: function(){
                                reason = Ext.getCmp("disApprovedReason").getValue()

                                var itemIndex = leaveListGrid.store.indexOf(leaveListGrid.getSelectionModel().getSelected());
                                leaveRegisterId = leaveListGrid.getStore().getAt(itemIndex).get("id");

                                disApproveForm.getForm().submit({
                                    url:'${createLink(controller: 'leave',action: "leaveAction")}',
                                    params: {
                                        userId: userId,
                                        leaveRegisterId:leaveRegisterId,
                                        reason: reason,
                                        leaveStatus:${0}
                                    },
                                    success: function(response){
                                        Ext.MessageBox.alert("Success", "Leave disapproved successfully");
                                        leaveListGrid.getStore().reload();
                                        leaveListGrid.getView().refresh();
                                        disApproveWindow.hide();
                                    },
                                    failure: function(response){
                                        Ext.MessageBox.alert("Failed", "Leave disapproved failed");
                                        disApproveWindow.hide();
                                    }
                                })
                            }
                        },{
                            text: 'Cancel',
                            handler: function(){
                                disApproveWindow.hide();
                            }
                        }
                    ]
                });

            })

        </script>
        <style>
        input[type="text"], input[type="password"], .fileupload {
            background-position: 0px 0px;
            margin-top: 4px;
        }
        #disApprovedReason, #approvedReason{
            margin-top: 4px;
            height:  26px;
            background-position: 0px 0px;
        }

         .x-form-item label.x-form-item-label{margin-top: 4px;}
        .x-form-text x-form-field{}
        </style>
    </head>
    <body>

    <content tag="rightTag">

        <div class="bread_crumbs_ui_div" style="width: 772px">
            <ul id="crumbs_ui_custom">
                <li><g:link controller="application" action="index">Dashboard</g:link></li>
                <li><g:link controller="leave" action="leave">Leave Home</g:link></li>
                <li>Leave List</li>
            </ul>
        </div>

        <br class="clear"/>


        <div class="widget" style="width: 770px;">

            <div class="header">
                <span><span class="ico gray leave"></span>&nbsp;&nbsp;${titleOfPage}</span>
            </div>

            <g:if test="${flash.message}">
                <div class="container_12">
                    <div class="grid_4">

                    </div>

                    <div class="grid_4 message" role="status">
                        <g:message code="${flash.message}" args="${flash.args}" default="${flash.default}"></g:message>
                    </div>

                    <div class="clear"></div>

                    <g:hiddenField name="username" value="${session?.user?.userCode}"></g:hiddenField>

                </div>
            </g:if>
            <div id="list_leave" class="content scaffold-list" role="main">

            </div>
        </div>

    </content>

    </body>
</g:applyLayout>
