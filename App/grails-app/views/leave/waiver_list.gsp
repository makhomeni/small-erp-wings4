<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 6/17/12
  Time: 10:05 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<g:applyLayout name="app">
<head>
  <title>${titleOfPage}</title>
<script type="text/javascript">

    var waiverListDataStore;
    var waiverListGrid;
    var checkBoxSelectionModel;
    var isEmployee;
    var userId;

//    for approve
    var approveForm;
    var approveWindow;

    var leaveWaiverId;
    var reason;

    var disApproveWindow;
    var disApproveForm;
    var disApproveReason;

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

        waiverListDataStore = new Ext.data.Store({
            id: "waiverListDataStore",
            url: '${createLink(controller: 'leave',action: 'waiverJsonData')}',
            baseParams:{waiverType: 0},
            reader: new Ext.data.JsonReader({
                root: 'waivers',
                totalProperty: 'totalCount',
                id: 'id'
            },[
                {name: 'id', type: 'int', mapping: 'id'},
                {name: 'waiverTakerFullName', type: 'string', mapping: 'waiverTakerFullName'},
                {name: 'reason', type: 'string', mapping: 'reason'},
                {name: 'applyDate', type: 'string', mapping: 'applyDate'},
                {name: 'waiverDate', type: 'string', mapping: 'waiverDate'},
                {name: 'waiverTime', type: 'string', mapping: 'waiverTime'},
                {name: 'waiverStatus', type: 'int', mapping: 'waiverStatus'}
            ]),
            autoLoad : true
        });

        checkBoxSelectionModel= new Ext.grid.CheckboxSelectionModel();
        waiverListGrid = new Ext.grid.EditorGridPanel({
            id: "waiverListGrid",
            store : waiverListDataStore,
            selModel : checkBoxSelectionModel,
            clicksToEdit: 2,
            renderTo: "list_leave",
            width: 720,
            height: 480,
            columns : [
                {
                    dataIndex: 'waiverTakerFullName',
                    header: 'Full Name',
                    width: 140
                },{
                    dataIndex: 'reason',
                    header: 'Reason',
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
                    dataIndex: 'waiverDate',
                    header: 'Waiver Date',
                    sortable: true,
                    width: 70
                },{
                    dataIndex: 'waiverTime',
                    header: 'Waiver Time',
                    sortable: true,
                    width: 70
                },{
                    header: 'Status',
                    width: 70,
                    dataIndex: 'waiverStatus',
                    sortable: true,
                    renderer: function(v, p, record) {
                        var renderText = renderText = "<img title='Pending' src='${resource(dir:'images',file: 'pending.png')}'/>";
                        var waiverStatus = record.get('waiverStatus');
//                        alert(record.get('waiverStatus'))
                        if(waiverStatus == 1){
                            renderText = "<img title='Approved' src='${resource(dir:'images',file: 'approved.png')}'/>"
                        }else if(waiverStatus == 0){
                            renderText = "<img title='Disapproved' src='${resource(dir:'images',file: 'disapprove.png')}'/>"
                        }

                        return renderText;
                    }

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
                store : waiverListDataStore,
                pageSize : 10,
                displayInfo : true,
                displaymsg : 'Displaying {0} - {1} of {2}',
                emptyMsg : "No records found"
            }),

            tbar: [{
                xtype: "button",
                text: "Add Waiver Request",
                iconCls: "addRecord" ,
                handler:function(){
                    window.location="${createLink(controller: 'leave',action: 'waiverRequest')}"
                }
            }]
        });
        waiverListDataStore.load({params: {start: 0, limit: 10}});

//        for approve leave waiver
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

                        var itemIndex = waiverListGrid.store.indexOf(waiverListGrid.getSelectionModel().getSelected());
                        leaveWaiverId = waiverListGrid.getStore().getAt(itemIndex).get("id");



                        approveForm.getForm().submit({
                            url:'${createLink(controller: "leave",action: "waiverAction")}',
                            params: {
                                userId: userId,
                                leaveWaiverId:leaveWaiverId,
                                reason: reason,
                                leaveStatus:${1}
                            },
                            success: function(response){
                                Ext.MessageBox.alert("Success", "Waiver approved successfully");
                                waiverListGrid.getStore().reload();
                                waiverListGrid.getView().refresh();
                                approveWindow.hide();
                            },
                            failure: function(response){
                                Ext.MessageBox.alert("Failed", "Waiver approved failed");
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

        disApproveForm = new Ext.form.FormPanel({
            items: [
                {
                    xtype: "textarea",
                    name:"disApprovedReason",
                    id:"disApprovedReason",
                    fieldLabel: "Reason",
                    width: 250,
                    height: 120
                }]
        });

        disApproveWindow = new Ext.Window({
            id: 'disApproveWindow',
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
                        disApproveReason =  Ext.getCmp('disApprovedReason').getValue();

                        var itemIndex = waiverListGrid.store.indexOf(waiverListGrid.getSelectionModel().getSelected());
                        leaveWaiverId = waiverListGrid.getStore().getAt(itemIndex).get("id");



                        disApproveForm.getForm().submit({
                            url:'${createLink(controller: "leave",action: "waiverAction")}',
                            params: {
                                userId: userId,
                                leaveWaiverId:leaveWaiverId,
                                reason: disApproveReason,
                                leaveStatus:${0}
                            },
                            success: function(response){
                                Ext.MessageBox.alert("Success", "Waiver disapproved successfully");
                                waiverListGrid.getStore().reload();
                                waiverListGrid.getView().refresh();
                                disApproveWindow.hide();
                            },
                            failure: function(response){
                                Ext.MessageBox.alert("Failed", "Waiver disapproved failed");
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

<style type="text/css">

#disApprovedReason, #approvedReason{
    margin-top: 4px;
    background-position: 0px 0px;
}

</style>

</head>
<body>
<content tag="rightTag">

    <div class="bread_crumbs_ui_div" style="width: 772px">
        <ul id="crumbs_ui_custom">
            <li><g:link controller="application" action="index">Dashboard</g:link></li>
            <li><g:link controller="leave" action="leave">Leave Home</g:link></li>
            <li>Weaver List</li>
        </ul>
    </div>

    <br class="clear"/>


        <div class="widget" style="width: 770px;">

            <div class="header" style="margin-top: 5px;">
                <span><span class="ico gray lateWaiver" style="margin-right: 5px;"></span>${titleOfPage}</span>
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
