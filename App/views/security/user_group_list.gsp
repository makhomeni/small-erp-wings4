<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/19/12
  Time: 3:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.jabait.security.UserGroup"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<g:applyLayout name="app">
    <head>
        <g:set var="entityName" value="${message(code: 'userGroup.title', default:'User Group')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <script>
            var authorityComboStore;
            var authorityCombo;
            var authorityAssignmentForm;
            var authorityAssignmentWindow;
            var userGroupDataStore;
           // var userGroupDetailsDataStore;
            var checkBoxSelMod;
            var userGroupListingEditorGrid;
            var userGroupId;


            var authorityDataStore;
            var checkBoxSelModForAuthorityAssignment;
            var authorityListingEditorGridForAssignment;


            var loggedUsersGroupId;
            var editUserGroup = "editUserGroup";
            var deleteUserGroup = "deleteUserGroup";


            function deleteRec(id){

                Ext.Msg.show({
                    title:'Delete User Group?',
                    msg: 'You are deleting a User Group. Would you like to delete?',
                    maxWidth:400,
                    buttons: Ext.Msg.YESNOCANCEL,
                    fn: function(btn){
                        if(btn == 'yes'){

                            window.location = "${createLink(action: 'deleteUserGroup')}"+"/"+id;
                        }
                    }
                });
            }

            Ext.onReady(function(){

                //for checking menu items are authorized
                var userGroupAddFeature = "";
                var authorityAssignmentFeature = "";


                Ext.Ajax.request({
                    url: '${createLink(action: "jsonSessionValue")}',
                    params: {id : ${session.user.id}},
                    success: function(response, opts) {
                        var totalLength = Ext.util.JSON.decode(response.responseText).grantedAuthorityJson[0].length
                        for(i = 0; i < totalLength; i++){
                            if(Ext.util.JSON.decode(response.responseText).grantedAuthorityJson[0][i].operation == "saveUserGroup"){
                                userGroupAddFeature = "saveUserGroup"
                            }

                            if(Ext.util.JSON.decode(response.responseText).grantedAuthorityJson[0][i].operation == "authorityAssignment"){
                                authorityAssignmentFeature = "authorityAssignment"
                            }
                            if(Ext.util.JSON.decode(response.responseText).grantedAuthorityJson[0][i].operation == "editUserGroup"){
                                editUserGroup = "editUserGroup"
                            }else{
                                editUserGroup = ""
                            }
                            if(Ext.util.JSON.decode(response.responseText).grantedAuthorityJson[0][i].operation == "deleteUserGroup"){
                                deleteUserGroup = "deleteUserGroup"
                            }else{
                                deleteUserGroup = ""
                            }
                        }

//                        alert(userGroupAddFeature)

                        //for save authority
                        if(userGroupAddFeature == "saveUserGroup"){
                            Ext.getCmp("userGroupListingEditorGrid").doLayout();
                            var addBtn = new Ext.Button({
                                text: "Add an Group",
                                iconCls: "addRecord",
                                tooltip: "Add an Group",
                                handler: function(){
                                    window.location = "${createLink(action: 'createUserGroup')}"
                                }
                            })

                            var separator = {xtype: 'tbseparator'};
                            Ext.getCmp("userGroupListingEditorGrid").getTopToolbar().add(addBtn);
                            Ext.getCmp("userGroupListingEditorGrid").getTopToolbar().add(separator);
                        }

                        //for assignment feature
                        if(authorityAssignmentFeature == "authorityAssignment"){
                            Ext.getCmp("userGroupListingEditorGrid").doLayout();
                            var authorityAssignmentBtn = new Ext.Button({
                                text: 'Authority Assignment',
                                iconCls:"assignment",
                                tooltip: 'Advanced Search',
                                handler: function(){
                                    try{
                                        var itemIndex = userGroupListingEditorGrid.store.indexOf(userGroupListingEditorGrid.getSelectionModel().getSelected());
                                        userGroupId = userGroupListingEditorGrid.getStore().getAt(itemIndex).get("id");
                                        authorityAssignmentWindow.show(this);
                                    } catch(err){
                                        Ext.MessageBox.alert("Error", "Please select a row!!!");
                                    }
                                }
                            })

                            var separator = {xtype: 'tbseparator'};
                            Ext.getCmp("userGroupListingEditorGrid").getTopToolbar().add(authorityAssignmentBtn);
                            Ext.getCmp("userGroupListingEditorGrid").getTopToolbar().add(separator);
                        }


                    },
                    failure: function(response, opts) {
                        alert(response)
                    }
                });

                Ext.Ajax.request({
                    url:'${createLink(action: "jsonValueForUserDetails")}',
                    params: {id : ${session.user.id}},
                    success: function(response, opts) {
                        loggedUsersGroupId = Ext.util.JSON.decode(response.responseText).groups[0].id

                    },
                    failure:function(respone, opts){

                    }
                })

                // alert(authorityAddFeature)
                //end


                authorityComboStore = new Ext.data.Store({
                    url: '${createLink(action: "authorityJsonData")}',
                    reader: new Ext.data.JsonReader({root: "authorityList", id: "id"},
                            ["id", "roleTitle", "description"]),
                    autoLoad: true
                })

                authorityCombo = new Ext.ux.form.LovCombo({
                    id: "authorityCombo",
                    store : authorityComboStore,
                    hiddenName : 'xmlType',
                    valueField : 'roleTitle',
                    displayField : 'description',
                    emptyText : 'Please select authority type...',
                    triggerAction : 'all',
                    width : 200,
                    hideTrigger : false,
                    editable : false,
                    mode : 'local',
                    lazyInit: true,
                    editable: true,
                    enableKeyEvents: true
                })
                authorityAssignmentForm = new Ext.FormPanel({
                    items:[authorityCombo]
                });

                //////////////////////
                authorityDataStore = new Ext.data.Store({
                    url: '${createLink(action: "authorityJsonData")}',
                    reader: new Ext.data.JsonReader({root: "authorityList", id: "id"},
                            ["id", "roleTitle", "description"]),
                    autoLoad: true
                })

                //create the grid

                checkBoxSelModForAuthorityAssignment = new Ext.grid.CheckboxSelectionModel();
                authorityListingEditorGridForAssignment = new Ext.grid.EditorGridPanel({
                    id: 'authorityListingEditorGridForAssignment ',
                    store : authorityDataStore,
                    clicksToEdit: 2,
                    selModel : checkBoxSelModForAuthorityAssignment,
                    height: 300,
                    //sm: new Ext.grid.CheckboxSelectionModel(),

                    tbar: [
                        {
                            text: 'Refresh',
                            icon:    '$nroot/includes/extjs3/resources/images/slate/button/table_refresh.png',
                            cls: 'x-btn-text-icon',
                            handler: function() {
//                                    window.location = '$nroot/index.php/imaging/index';
                            },
                            scope: this
                        }
                    ],
                    columns: [
                        checkBoxSelModForAuthorityAssignment,
                        {
                            dataIndex: 'id',
                            header: 'ID',
                            width: 50
                        },
                        {
                            dataIndex: 'description',
                            header: 'Description',
                            sortable: true,
                            width: 250,
                            editable: true,
                            editor: new Ext.form.TextField()
                        }
                    ],
                    stripeRows : true,
                    bbar: new Ext.PagingToolbar({
                        store : authorityDataStore,
                        pageSize : 10,
                        displayInfo : true,
                        displaymsg : 'Displaying {0} - {1} of {2}',
                        emptyMsg : "No records found"
                    })
                })
                authorityDataStore.load({params: {start: 0, limit: 10}});

                ////////////////////


                authorityAssignmentWindow = new Ext.Window({
                    id: 'authorityAssignmentWindow',
                    title: 'Assigning Authority to selected user group',
                    closable: false,
                    width: 400,
                    height: 500,
                    plain:true,
                    modal:true,
                    layout: 'fit',
                    resizable: false,
                    items: authorityListingEditorGridForAssignment,
                    autoDestroy: true,
                    buttons:[
                        {
                            text: 'Save and Close',
                            handler: function(){
                               // alert("got it");
                                var selectedAuthority = authorityListingEditorGridForAssignment.getSelectionModel().getSelections();
                                var selectedAuthorityIdArray = [];
                                if (selectedAuthority.length > 0) {
                                    var des = [];
                                    Ext.each(selectedAuthority, function(sel) {
                                        selectedAuthorityIdArray.push(sel.get('id'));
                                        //alert("authority id = "+sel.get('id'))
                                        des.push(sel.get('des'));
                                    });
                                }
                                var itemIndex = authorityListingEditorGridForAssignment.store.indexOf(authorityListingEditorGridForAssignment.getSelectionModel().getSelected());
                               // alert(itemIndex)
                                //alert("user group id = "+userGroupId)


                                Ext.Ajax.request({

                                    url: '${createLink(action: "authorityAssignmentToGroup")}',
                                    params: {userGroupId: userGroupId, authorities: selectedAuthorityIdArray},
                                    success: function ( result, request ) {
                                        Ext.MessageBox.alert("Success", "Authority assigned successfully");
                                        authorityAssignmentWindow.hide();
                                    },
                                    failure: function ( result, request) {
                                        Ext.MessageBox.alert("Failed", "Authority assignment failed");
                                        authorityAssignmentWindow.hide();
                                    }
                                });
                            }
                        },{
                            text: 'Cancel',
                            handler: function(){
                                authorityAssignmentWindow.hide();
                            }
                        }
                    ]
                });



                //for user group data
                userGroupDataStore = new Ext.data.Store({
                    id: "userGroupDataStore",
                    url: '${createLink(action: 'userGroupJsonData')}',
                    reader: new Ext.data.JsonReader({
                        root: 'userGroups',
                        totalProperty: 'totalCount',
                        id: 'id'
                    },[
                        {name: 'id', type: 'int', mapping: 'id'},
                        {name: 'groupName', type: 'string', mapping: 'groupName'},
                        {name: 'description', type: 'string', mapping: 'description'}
                    ]),
                    autoLoad : true
                })
                    /*
                userGroupDetailsDataStore=new Ext.data.Store({
                    id: "userGroupDetailsDataStore",
                    url:'${createLink(action: 'userGroupDetailsJsonData')}'
                    reader:new Ext.data.JsonReader({
                        root:'userGroupDetails',
                        totalProperty:'totalCount',
                        id:'id'
                    },[
                        {name:'id',type:'int',mapping:'id'},
                        {name:'groupName',type:'string',mapping:'groupName'},
                        {name:'description',type:'strng',mapping:'description'}
                    ]),
                    autoLoad:true
                })
                     */
                //create the grid
                checkBoxSelMod = new Ext.grid.CheckboxSelectionModel();
                userGroupListingEditorGrid = new Ext.grid.EditorGridPanel({
                    id:'userGroupListingEditorGrid',
                    store : userGroupDataStore,
                    selModel : checkBoxSelMod,
                    clicksToEdit: 2,
                    renderTo: "list_user_group",
                    height: 300,
                    columns : [
                        {
                            dataIndex: 'id',
                            header: 'ID',
                            width: 50
                        },{
                            dataIndex: 'groupName',
                            header: 'Group Name',
                            sortable: true,
                            width:100,
                            editable: false
                        },{
                            dataIndex: 'description',
                            header: 'Description',
                            sortable: true,
                            width: 200
                        },{
                            header:'Action',
                            dataIndex: 'action',
                            width: 150,
                            renderer: function(v, p, record) {
                                var renderText = "";
                                if(editUserGroup == "editUserGroup"){
                                    // alert("if success ")
                                    renderText = renderText + " <a href='${createLink(action: "editUserGroup")}/"+record.get('id')+"'><img title='Edit' src='${resource(dir:'images',file: 'edit.png')}'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                                }
                                if(deleteUserGroup == "deleteUserGroup"){

                                    if(record.get('id') == loggedUsersGroupId){
                                        renderText = renderText;
                                    }else{
                                        renderText += " <a id='delete' onclick='deleteRec("+record.get('id')+")' href='#' ><img title='Delete' src='${resource(dir:'images',file: 'delete.png')}'/></a>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "
                                    }

                                }
                                // alert(renderText)
                                return renderText + " <a href='${createLink(action: "userGroupDetails")}/"+record.get('id')+"'><img title='Show' src='${resource(dir:'images',file: 'show.png')}'/></a>"

                            }
                        }
                    ],
                    stripeRows : true,
                    bbar: new Ext.PagingToolbar({
                        store : userGroupDataStore,
                        pageSize : 10,
                        displayInfo : true,
                        displaymsg : 'Displaying {0} - {1} of {2}',
                        emptyMsg : "No records found"
                    }),
                    tbar: []
                })
                userGroupDataStore.load({params: {start: 0, limit: 10}});

            })

        </script>
    </head>
    <body>

        <content tag="rightTag">

            <div class="bread_crumbs_ui_div" style="width: 611px">
                <ul id="crumbs_ui_custom">
                    <li><g:link controller="application" action="index">Dashboard</g:link></li>
                    <li><g:link controller="security" action="userGroupList">Group List</g:link></li>

                </ul>
            </div>
            <br class="clear" />

            <div class="widget">

                <div class="header" style="width: 609px;">
                    <span><span class="ico gray userGroup"></span>${type}</span>
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
            <div id="list_user_group" class="content scaffold-list" role="main"></div>
                </div>
        </content>
    </body>
</g:applyLayout>