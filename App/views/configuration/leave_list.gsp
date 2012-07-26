<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/19/12
  Time: 3:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="grails.converters.JSON; com.jabait.hrm.time.Leave"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<g:applyLayout name="app">
    <head>
        <g:set var="entityName" value="${message(code: 'leaveList.label', default:'LeaveList')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <script>
            var leaveDataStore;
            var checkBoxSelMod;
            var leaveListingEditorGrid;
            var checkBoxSelModForGroupAssignment;
            var groupListingEditorGridForAssignment;
            var leaveGroupStore;
            var checkBoxSelModForAuthorityAssignment;


            function deleteRec(id){
                alert("enter"+id);

                Ext.Msg.show({
                    title:'Delete Leave?',
                    msg: 'You are deleting a leave. Would you like to delete?',
                    maxWidth:400,
                    buttons: Ext.Msg.YESNOCANCEL,
                    fn: function(btn){
                        if(btn == 'yes'){

                            alert(1)

                            window.location = "${createLink(controller: 'configuration',action: 'deleteLeave')}"+"/"+id;
                        }
                    }
                });
            }

            Ext.onReady(function(){

                /////////////// start user group grid//////////////

                leaveDataStore = new Ext.data.Store({
                    id: "leaveDataStore",
                    url: '${createLink(action: 'leaveJsonData')}',
                    reader: new Ext.data.JsonReader({
                        root: 'leaves',
                        totalProperty: 'totalCount',
                        id: 'id'
                    },[
                        {name: 'id', type: 'int', mapping: 'id'},
                        {name: 'leaveType', type: 'string', mapping: 'leaveType'},
                        {name: 'leaveDuration', type: 'string', mapping: 'leaveDuration'}
                    ]),
                    autoLoad : true
                })
                 checkBoxSelMod = new Ext.grid.CheckboxSelectionModel();
                leaveListingEditorGrid = new Ext.grid.EditorGridPanel({
                    id: "leaveListingEditorGrid",
                    store : leaveDataStore,
                    selModel : checkBoxSelMod,
                    clicksToEdit: 2,
                    renderTo: "list_user",
                    width: 560,
                    height: 300,
                    columns : [
                        {
                            dataIndex: 'id',
                            header: 'ID',
                            width: 75
                        },{
                            dataIndex: 'leaveType',
                            header: 'Leave Type',
                            sortable: true,
                            width:200,
                            editable: false
                        },{
                            dataIndex: 'leaveDuration',
                            header: 'Leave Duration',
                            sortable: true,
                            width: 75
                        },{
                            header:'Action',
                            dataIndex: 'action',
                            width: 195,
                            renderer: function(v, p, record) {
                                var renderText  = "";

                                renderText = renderText + " <a href='${createLink(controller: "configuration",action: "editLeave")}/"+record.get('id')+"'><img title='Edit' src='${resource(dir:'images',file: 'edit.png')}'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

                                renderText = renderText + " <a href='${createLink(controller: 'configuration',action: "showLeaveDetails")}/"+record.get('id')+"'><img title='Show' src='${resource(dir:'images',file: 'show.png')}'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                                if(record.get('id') == ${session.user.id}){
                                    renderText = renderText;
                                }else{
                                    renderText += " <a id='delete' onclick='deleteRec("+record.get('id')+")' href='#' ><img title='Delete' src='${resource(dir:'images',file: 'delete.png')}'/></a>"
                                }

                                return renderText;
                            }
                        }
                    ],
                    stripeRows : true,
                    bbar: new Ext.PagingToolbar({
                        store : leaveDataStore,
                        pageSize : 10,
                        displayInfo : true,
                        displaymsg : 'Displaying {0} - {1} of {2}',
                        emptyMsg : "No records found"
                    }),
                    tbar: [{
                        xtype: "button",
                        text: "Add Leaves",
                        iconCls: "addRecord",
                        handler:function(){
                            window.location="${createLink(action:'createLeave')}"
                        }
                    }]
                });
                leaveDataStore.load({params: {start: 0, limit: 10}});
                function deleteUser(){
                    alert();
                }

            })


        </script>
        <style type="text/css">
        .header{
            width: 849px;
        }
        .widget{
            width: 850px;
        }

        </style>
    </head>
    <body>
    <content tag="titleTag">

    </content>
    <content tag="rightTag">

        <div class="bread_crumbs_ui_div" style="width: 611px">
            <ul id="crumbs_ui_custom">
                <li><g:link controller="application" action="index">Dashboard</g:link></li>

                <li>Leave List</li>
            </ul>
        </div>
        <br class="clear"/>

        <div class="widget" style="width: 609px">

            <div class="header" style="width: 609px;">
                <span><span class="ico gray leave"></span>${type}</span>
            </div>

            <g:if test="${flash.message}">
                <div class="container_12">
                    <div class="grid_4">

                    </div>
                    <div class="grid_4 message" role="status">
                        <g:message code="${flash.message}" args="${flash.args}" default="${flash.default}"></g:message>
                    </div>
                    <div class="clear"></div>
                </div>
            </g:if>
            <div id="list_user" class="content scaffold-list" role="main" style="width: 559px;">

            </div>
        </div>
    </content>
    </body>
</g:applyLayout>