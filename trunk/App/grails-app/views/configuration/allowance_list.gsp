<%--
  Created by IntelliJ IDEA.
  User: masum
  Date: 6/26/12
  Time: 1:58 PM
  To change this template use File | Settings | File Templates.
--%>
 <g:applyLayout name="app">
    <head>

        <title>${type}</title>
        <script>
            var allowanceDataStore;
            var checkBoxSelMod;
            var allowanceListingEditorGrid;
            var confirmationMessage;

            var checkBoxSelModForGroupAssignment;
            var groupListingEditorGridForAssignment;
            var allowanceGroupStore;


            function deleteRec(id){
                alert("enter"+id);

                Ext.Msg.show({
                    title:'Delete Allowance?',
                    msg: 'You are deleting a allowance. Would you like to delete?',
                    maxWidth:400,
                    buttons: Ext.Msg.YESNOCANCEL,
                    fn: function(btn){
                        if(btn == 'yes'){

                            alert(1)

                            window.location = "${createLink(controller: 'configuration',action: 'deleteAllowanceType')}"+"/"+id;
                        }
                    }
                });
            }

            Ext.onReady(function(){

                allowanceDataStore = new Ext.data.Store({
                    id: "allowanceDataStore",
                    url: '${createLink(action: 'allowanceJsonData')}',
                    reader: new Ext.data.JsonReader({
                        root: 'allowance',
                        totalProperty: 'totalCount',
                        id: 'id'
                    },[
                        {name: 'id', type: 'int', mapping: 'id'},
                        {name: 'allowanceName', type: 'string', mapping: 'allowanceName'},
                        {name: 'allowanceDescription', type: 'string', mapping: 'allowanceDescription'}
                    ]),
                    autoLoad : true
                })

                checkBoxSelMod = new Ext.grid.CheckboxSelectionModel();
                allowanceListingEditorGrid = new Ext.grid.EditorGridPanel({
                    id: "allowanceListingEditorGrid",
                    store : allowanceDataStore,
                    selModel : checkBoxSelMod,
                    clicksToEdit: 2,
                    renderTo: "list_user",
                    width: 580,
                    height: 300,
                    columns : [
                        {
                            dataIndex: 'id',
                            header: 'ID',
                            width: 80
                        },{
                            dataIndex: 'allowanceName',
                            header: 'Allowance Name:',
                            sortable: true,
                            width:100,
                            editable: false
                        },{
                            dataIndex: 'allowanceDescription',
                            header: 'Allowance Description',
                            sortable: true,
                            width: 175
                        }
                        ,{
                            header:'Action',
                            dataIndex: 'action',
                            width: 200,
                            header:'Action',
                            dataIndex: 'action',
                            width: 200,
                            renderer: function(v, p, record) {
                                var renderText  = "";

                                renderText = renderText + " <a href='${createLink(controller: "configuration",action: "editAllowance")}/"+record.get('id')+"'><img title='Edit' src='${resource(dir:'images',file: 'edit.png')}'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

                                renderText = renderText + " <a href='${createLink(controller: 'configuration',action: "showAllowance")}/"+record.get('id')+"'><img title='Show' src='${resource(dir:'images',file: 'show.png')}'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";


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
                        store : allowanceDataStore,
                        pageSize : 10,
                        displayInfo : true,
                        displaymsg : 'Displaying {0} - {1} of {2}',
                        emptyMsg : "No records found"
                    }),
                    tbar: [{
                        xtype: "button",
                        text: "Add Allowance",
                        iconCls: "addRecord",
                        handler:function(){
                            window.location="${createLink(action:'createAllowanceType')}"
                        }
                    }]
                });
                allowanceDataStore.load({params: {start: 0, limit: 10}});
                function deleteUser(){

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

    <div class="bread_crumbs_ui_div" style="width: 850px">
        <ul id="crumbs_ui_custom">
            <li><g:link controller="application" action="index">Dashboard</g:link></li>
            <li><g:link controller="configuration" action="payrollConfigHome">Payroll Config Home</g:link></li>
            <li>Allowance List</li>
        </ul>
    </div>

        <br class="clear"/>

        <div class="widget">

            <div class="header" style="width: 849px;">
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
            <div id="list_user" class="content scaffold-list" role="main" style="width: 799px;">

            </div>
        </div>
    </content>
    </body>
</g:applyLayout>