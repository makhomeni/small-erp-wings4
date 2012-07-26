<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/19/12
  Time: 3:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="grails.converters.JSON; com.jabait.hrm.JobRole"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<g:applyLayout name="app">
    <head>

        <title>${type}</title>
        <script>
            var jobRoleDataStore;
            var checkBoxSelMod;
            var jobRoleListingEditorGrid;
            var confirmationMessage;

            var checkBoxSelModForGroupAssignment;
            var groupListingEditorGridForAssignment;
            var jobRoleGroupStore;


            function deleteRec(id){
                alert("enter"+id);

                Ext.Msg.show({
                    title:'Delete Job Role?',
                    msg: 'You are deleting a job role. Would you like to delete?',
                    maxWidth:400,
                    buttons: Ext.Msg.YESNOCANCEL,
                    fn: function(btn){
                        if(btn == 'yes'){

                            alert(1)

                            window.location = "${createLink(controller: 'configuration',action: 'deleteJobRole')}"+"/"+id;
                        }
                    }
                });
            }

            Ext.onReady(function(){

                jobRoleDataStore = new Ext.data.Store({
                    id: "jobRoleDataStore",
                    url: '${createLink(action: 'jobRoleJsonData')}',
                    reader: new Ext.data.JsonReader({
                        root: 'jobRoles',
                        totalProperty: 'totalCount',
                        id: 'id'
                    },[
                        {name: 'id', type: 'int', mapping: 'id'},
                        {name: 'roleName', type: 'string', mapping: 'roleName'},
                        {name: 'description', type: 'string', mapping: 'description'}
                    ]),
                    autoLoad : true
                })

                checkBoxSelMod = new Ext.grid.CheckboxSelectionModel();
                jobRoleListingEditorGrid = new Ext.grid.EditorGridPanel({
                    id: "jobRoleListingEditorGrid",
                    store : jobRoleDataStore,
                    selModel : checkBoxSelMod,
                    clicksToEdit: 2,
                    renderTo: "list_user",
                    width: 560,
                    height: 300,
                    columns : [
                        {
                            dataIndex: 'id',
                            header: 'ID',
                            width: 80
                        },{
                            dataIndex: 'roleName',
                            header: 'Role Name:',
                            sortable: true,
                            width:200,
                            editable: false
                        },{
                            dataIndex: 'description',
                            header: 'Description',
                            sortable: true,
                            width: 75
                        },{

                            header:'Action',
                            dataIndex: 'action',
                            width: 200,
                            renderer: function(v, p, record) {
                                var renderText  = "";

                                renderText = renderText + " <a href='${createLink(controller: "configuration",action: "editJobRole")}/"+record.get('id')+"'><img title='Edit' src='${resource(dir:'images',file: 'edit.png')}'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

                                renderText = renderText + " <a href='${createLink(controller: 'configuration',action: "showRole")}/"+record.get('id')+"'><img title='Show' src='${resource(dir:'images',file: 'show.png')}'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";


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
                        store : jobRoleDataStore,
                        pageSize : 10,
                        displayInfo : true,
                        displaymsg : 'Displaying {0} - {1} of {2}',
                        emptyMsg : "No records found"
                    }),
                    tbar: [{
                        xtype: "button",
                        text: "Add Job Role",
                        iconCls: "addRecord",
                        handler:function(){
                            window.location="${createLink(action:'createRoleList')}"
                        }
                    }]
                });
                jobRoleDataStore.load({params: {start: 0, limit: 10}});
                function deleteUser(){
                    alert();
                }

            })


        </script>
        <style type="text/css">
        .header{
            width: 612px;
        }
        .widget{
            width: 612px;
        }

        </style>
    </head>
    <body>

    <content tag="titleTag">

    </content>
    <content tag="rightTag">

        <div class="bread_crumbs_ui_div" style="width: 614px">
            <ul id="crumbs_ui_custom">
                <li><g:link controller="application" action="index">Dashboard</g:link></li>
                <li><g:link controller="configuration" action="jobConfigHome">Job Config Home</g:link></li>
                <li>Job Role List</li>
            </ul>
        </div>
        <br class="clear"/>

        <div class="widget">

            <div class="header">
                <span><span class="ico gray jobRole"></span>${type}</span>
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
            <div id="list_user" class="content scaffold-list" role="main" style="width: 562px;">

            </div>
        </div>
    </content>
    </body>
</g:applyLayout>