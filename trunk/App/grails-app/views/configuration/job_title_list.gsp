<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/19/12
  Time: 3:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="grails.converters.JSON; com.jabait.hrm.JobTitle"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<g:applyLayout name="app">
    <head>

        <title>${type}</title>
        <script>
            var jobTitleDataStore;
            var checkBoxSelMod;
            var jobTitleListingEditorGrid;
            var confirmationMessage;

            var checkBoxSelModForGroupAssignment;
            var groupListingEditorGridForAssignment;
            var jobTitleGroupStore;


            function deleteRec(id){
                alert("enter"+id);

                Ext.Msg.show({
                    title:'Delete Job Title?',
                    msg: 'You are deleting Job Title. Would you like to delete?',
                    maxWidth:400,
                    buttons: Ext.Msg.YESNOCANCEL,
                    fn: function(btn){
                        if(btn == 'yes'){

                            alert(1)

                            window.location = "${createLink(controller: 'configuration',action: 'deleteJobTitle')}"+"/"+id;
                        }
                    }
                });
            }

            Ext.onReady(function(){

//
                /////////////// start user group grid//////////////

                jobTitleDataStore = new Ext.data.Store({
                    id: "jobTitleDataStore",
                    url: '${createLink(action: 'jobTitleJsonData')}',
                    reader: new Ext.data.JsonReader({
                        root: 'jobTitles',
                        totalProperty: 'totalCount',
                        id: 'id'
                    },[
                        {name: 'id', type: 'int', mapping: 'id'},
                        {name: 'jobTitleCode', type: 'string', mapping: 'jobTitleCode'},
                        {name: 'jobTitleName', type: 'string', mapping: 'jobTitleName'}
                    ]),
                    autoLoad : true
                })

//                alert("user add feature " + userAddFeature)
                //create the grid
                //  confirmationMessage = Ext.MessageBox.confirm('Confirm', 'Do you really mean it?');
                //  var dialog = confirmationMessage.getDialog();
                // alert(dialog)


                checkBoxSelMod = new Ext.grid.CheckboxSelectionModel();
                jobTitleListingEditorGrid = new Ext.grid.EditorGridPanel({
                    id: "jobTitleListingEditorGrid",
                    store : jobTitleDataStore,
                    selModel : checkBoxSelMod,
                    clicksToEdit: 2,
                    renderTo: "list_user",
                    width: 560,
                    height: 300,
                    columns : [
                        {
                            dataIndex: 'id',
                            header: 'ID',
                            width: 20
                        },{
                            dataIndex: 'jobTitleCode',
                            header: 'Job Title Code',
                            sortable: true,
                            width:60,
                            editable: false
                        },{
                            dataIndex: 'jobTitleName',
                            header: 'jobTitleName',
                            sortable: true,
                            width: 260
                        },{

                            header:'Action',
                            dataIndex: 'action',
                            width: 200,
                            renderer: function(v, p, record) {
                                var renderText  = "";

                                renderText = renderText + " <a href='${createLink(controller: "configuration",action: "editTitle")}/"+record.get('id')+"'><img title='Edit' src='${resource(dir:'images',file: 'edit.png')}'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

                                renderText = renderText + " <a href='${createLink(controller: 'configuration',action: "showTitle")}/"+record.get('id')+"'><img title='Show' src='${resource(dir:'images',file: 'show.png')}'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";


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
                        store : jobTitleDataStore,
                        pageSize : 10,
                        displayInfo : true,
                        displaymsg : 'Displaying {0} - {1} of {2}',
                        emptyMsg : "No records found"
                    }),
                    tbar: [{
                        xtype: "button",
                        text: "Add Job Title",
                        iconCls: "addRecord",
                        handler:function(){
                            window.location="${createLink(action:'createJobTitle')}"
                        }

                    }]
                });
               jobTitleDataStore.load({params: {start: 0, limit: 10}});
                function deleteUser(){
                    alert();
                }

            })


        </script>
        <style type="text/css">
        .header{
            width: 609px;
        }
        .widget{
            width: 609px;
        }

        </style>
    </head>
    <body>
    %{--<content tag="bannerTag"><h1>${type}</h1></content>--}%

    <content tag="titleTag">

    </content>
    <content tag="rightTag">

        <div class="bread_crumbs_ui_div" style="width: 611px">
            <ul id="crumbs_ui_custom">
                <li><g:link controller="application" action="index">Dashboard</g:link></li>
                <li><g:link controller="configuration" action="jobConfigHome">Job Config Home</g:link></li>
                <li>Job Title List</li>
            </ul>
        </div>
        <br class="clear"/>

        <div class="widget">

            <div class="header" >
                <span><span class="ico gray jobTitle"></span>${type}</span>
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