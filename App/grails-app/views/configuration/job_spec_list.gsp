<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/19/12
  Time: 3:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="grails.converters.JSON; com.jabait.hrm.JobSpec"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<g:applyLayout name="app">
    <head>

        <title>${type}</title>
        <script>
            var jobSpecDataStore;
            var checkBoxSelMod;
            var jobSpecListingEditorGrid;
            var confirmationMessage;

            var checkBoxSelModForGroupAssignment;
            var groupListingEditorGridForAssignment;
            var jobSpecGroupStore;


            function deleteRec(id){
                alert("enter"+id);

                Ext.Msg.show({
                    title:'Delete Job Spec?',
                    msg: 'You are deleting a job spec. Would you like to delete?',
                    maxWidth:400,
                    buttons: Ext.Msg.YESNOCANCEL,
                    fn: function(btn){
                        if(btn == 'yes'){

                            alert(1)

                            window.location = "${createLink(controller: 'configuration',action: 'deleteJobSpec')}"+"/"+id;
                        }
                    }
                });
            }

            Ext.onReady(function(){
//
//                //for checking menu items are authorized
//                var userAddFeature = "";
//                var authorityAssignmentFeature = "";
//                var userGroupAssignmentFeature = "";
//
//                //for showing inrenderer
//                var editUserFeature = "";
//                var deleteUserFeature = "";
//
//                //for delete confirmation
//

                /////////////// start user group grid//////////////

                jobSpecDataStore = new Ext.data.Store({
                    id: "jobSpecDataStore",
                    url: '${createLink(action: 'jobSpecJsonData')}',
                    reader: new Ext.data.JsonReader({
                        root: 'jobSpec',
                        totalProperty: 'totalCount',
                        id: 'id'
                    },[
                        {name: 'id', type: 'int', mapping: 'id'},
                        {name: 'jobSpecName', type: 'string', mapping: 'jobSpecName'},
                        {name: 'jobSpecDesc', type: 'string', mapping: 'jobSpecDesc'},
                        {name: 'jobSpecDuties', type: 'string', mapping: 'jobSpecDuties'}
                    ]),
                    autoLoad : true
                })

//                alert("user add feature " + userAddFeature)
                //create the grid
                //  confirmationMessage = Ext.MessageBox.confirm('Confirm', 'Do you really mean it?');
                //  var dialog = confirmationMessage.getDialog();
                // alert(dialog)


                checkBoxSelMod = new Ext.grid.CheckboxSelectionModel();
                jobSpecListingEditorGrid = new Ext.grid.EditorGridPanel({
                    id: "jobSpecListingEditorGrid",
                    store : jobSpecDataStore,
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
                            dataIndex: 'jobSpecName',
                            header: 'Job Spec Name',
                            sortable: true,
                            width:200,
                            editable: false
                        },{
                            dataIndex: 'jobSpecDesc',
                            header: 'Job Spec Desc',
                            sortable: true,
                            width: 75
                        },
                        {
                            dataIndex: 'jobSpecDuties',
                            header: 'Job Spec Duties',
                            sortable: true,
                            width: 75
                        },{
                            header:'Action',
                            dataIndex: 'action',
                            width: 200,
                            header:'Action',
                            dataIndex: 'action',
                            width: 200,
                            renderer: function(v, p, record) {
                                var renderText  = "";

                                renderText = renderText + " <a href='${createLink(controller: "configuration",action: "editJobSpec")}/"+record.get('id')+"'><img title='Edit' src='${resource(dir:'images',file: 'edit.png')}'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

                                renderText = renderText + " <a href='${createLink(controller: 'configuration',action: "showJobSpecDetails")}/"+record.get('id')+"'><img title='Show' src='${resource(dir:'images',file: 'show.png')}'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";


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
                        store : jobSpecDataStore,
                        pageSize : 10,
                        displayInfo : true,
                        displaymsg : 'Displaying {0} - {1} of {2}',
                        emptyMsg : "No records found"
                    }),
                    tbar: [{
                        xtype: "button",
                        text: "Add Job Spec",
                        iconCls: "addRecord",
                        handler:function(){
                            window.location="${createLink(action:'createJobSpec')}"
                        }
                    }]
                });
                jobSpecDataStore.load({params: {start: 0, limit: 10}});
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
                <li>Job Spec List</li>
            </ul>
        </div>
        <br class="clear"/>

        <div class="widget">

            <div class="header" >
                <span><span class="ico gray jobSpec"></span>${type}</span>
            </div>

            <div id="list_user" class="content scaffold-list" role="main" style="width: 559px;">

            </div>
        </div>
    </content>
    </body>
</g:applyLayout>