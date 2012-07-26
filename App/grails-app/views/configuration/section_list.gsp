<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/19/12
  Time: 3:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="grails.converters.JSON; com.jabait.hrm.Department"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<g:applyLayout name="app">
    <head>
        <g:set var="entityName" value="${message(code: 'Section List', default:'Section')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <script>
            var sectionDataStore;
            var checkBoxSelMod;
            var sectionListingEditorGrid;
            var userId;
            var confirmationMessage;
            var sectionGroupStore;


            function deleteRec(id){
                alert("enter"+id);

                Ext.Msg.show({
                    title:'Delete Section?',
                    msg: 'You are deleting section. Would you like to delete?',
                    maxWidth:400,
                    buttons: Ext.Msg.YESNOCANCEL,
                    fn: function(btn){
                        if(btn == 'yes'){

                            alert(1)

                            window.location = "${createLink(controller: 'configuration',action: 'deleteSection')}"+"/"+id;
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


                /////////////// start user group grid//////////////

                sectionDataStore = new Ext.data.Store({
                    id: "sectionDataStore",
                    url: '${createLink(action: 'sectionJsonData')}',
                    reader: new Ext.data.JsonReader({
                        root: 'section',
                        totalProperty: 'totalCount',
                        id: 'id'
                    },[
                        {name: 'id', type: 'int', mapping: 'id'},
                        {name: 'departmentName', type: 'string', mapping: 'departmentName'},
                        {name: 'organization', type: 'string', mapping: 'organization'}

                    ]),
                    autoLoad : true
                })

//                alert("user add feature " + userAddFeature)
                //create the grid
                //  confirmationMessage = Ext.MessageBox.confirm('Confirm', 'Do you really mean it?');
                //  var dialog = confirmationMessage.getDialog();
                // alert(dialog)


                checkBoxSelMod = new Ext.grid.CheckboxSelectionModel();
                sectionListingEditorGrid = new Ext.grid.EditorGridPanel({
                    id: "sectionListingEditorGrid",
                    store : sectionDataStore,
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
                            dataIndex: 'departmentName',
                            header: 'Department Name',
                            sortable: true,
                            width:200,
                            editable: false
                        },{
                            header:'Action',
                            dataIndex: 'action',
                            width: 200,
                            renderer: function(v, p, record) {
                                var renderText  = "";

                                renderText = renderText + " <a href='${createLink(controller: "employee",action: "editUser")}/"+record.get('id')+"'><img title='Edit' src='${resource(dir:'images',file: 'edit.png')}'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

                                renderText = renderText + " <a href='${createLink(controller: 'configuration',action: "showSectionDetails")}/"+record.get('id')+"'><img title='Show' src='${resource(dir:'images',file: 'show.png')}'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";


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
                        store : sectionDataStore,
                        pageSize : 10,
                        displayInfo : true,
                        displaymsg : 'Displaying {0} - {1} of {2}',
                        emptyMsg : "No records found"
                    }),
                    tbar: [{
                        xtype: "button",
                        text: "Add Section",
                        iconCls: "addRecord",
                        handler:function(){
                            window.location="${createLink(action:'createSection')}"
                        }

                    }]
                });
                sectionDataStore.load({params: {start: 0, limit: 10}});
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
    %{--<content tag="bannerTag"><h1>${type}</h1></content>--}%

    <content tag="titleTag">

    </content>
    <content tag="rightTag">


        <div class="bread_crumbs_ui_div" style="width: 852px">
            <ul id="crumbs_ui_custom">
                <li><g:link controller="application" action="index">Dashboard</g:link></li>
                %{--<li><g:link controller="configuration" action="jobConfigHome">Job Config Home</g:link></li>--}%
                <li><g:link controller="configuration" action="organizationConfigHome">Organization Config Home</g:link></li>

                <li>Section List </li>
            </ul>
        </div>

        <div class="widget">

            <div class="header" style="width: 849px;">
                <span><span class="ico gray section"></span>${type}</span>
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
            <div id="list_user" class="content scaffold-list" role="main" style="width: 800px;">

            </div>
        </div>
    </content>
    </body>
</g:applyLayout>