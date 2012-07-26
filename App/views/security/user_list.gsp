<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/19/12
  Time: 3:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="grails.converters.JSON; com.jabait.security.User"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<g:applyLayout name="app">
    <head>
        <g:set var="entityName" value="${message(code: 'user.label', default:'User')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <script>
            var authorityComboStore;
            var authorityCombo;
            var authorityAssignmentForm;
            var authorityAssignmentWindow;
            var userDataStore;
            var checkBoxSelMod;
            var userListingEditorGrid;
            var userId;
            var userGroupAssignmentWindow;
            var userGroupAssignmentForm;
            var userGroupCombo;
            var userGroupComboStore;
            var confirmationMessage;


            var userAddFeature;
            var authorityAssignmentFeature;
            var userGroupAssignmentFeature;
            var editUserFeature = "editUser";
            var deleteUserFeature = "deleteUser";

            var checkBoxSelModForGroupAssignment;
            var groupListingEditorGridForAssignment;
            var userGroupStore;

            //
            var authorityDataStoreForAssignment;
            var authorityListingEditorGridForAssignment;
            var checkBoxSelModForAuthorityAssignment;


            //var for to panel in one window
            var gridPanel;
            var featureOperationDataStore
            var checkBoxSelModOperation;
            var featureOperationGrid;

            function deleteRec(id){

                Ext.Msg.show({
                    title:'Delete User?',
                    msg: 'You are deleting a user. Would you like to delete?',
                    maxWidth:400,
                    buttons: Ext.Msg.YESNOCANCEL,
                    fn: function(btn){
                        if(btn == 'yes'){

                            window.location = "${createLink(action: 'deleteUser')}"+"/"+id;
                        }
                    }
                });
            }

            Ext.onReady(function(){


                Ext.Ajax.request({
                    url: '${createLink(action: "jsonSessionValue")}',
                    params: {id : ${session.user.id}},
                    success: function(response, opts) {
                        var totalLength = Ext.util.JSON.decode(response.responseText).grantedAuthorityJson[0].length
                        for(i = 0; i < totalLength; i++){
                            if(Ext.util.JSON.decode(response.responseText).grantedAuthorityJson[0][i].operation == "saveUser"){
                                userAddFeature = "saveUser"
                            }

                            if(Ext.util.JSON.decode(response.responseText).grantedAuthorityJson[0][i].operation == "authorityAssignment"){
                                authorityAssignmentFeature = "authorityAssignment"
                            }

                            if(Ext.util.JSON.decode(response.responseText).grantedAuthorityJson[0][i].operation == "userGroupAssignment"){
                                userGroupAssignmentFeature = "userGroupAssignment"

                            }

                            ////
                            if(Ext.util.JSON.decode(response.responseText).grantedAuthorityJson[0][i].operation == "editUser"){

                                editUserFeature = "editUser"
                            }
                            if(Ext.util.JSON.decode(response.responseText).grantedAuthorityJson[0][i].operation == "deleteUser"){

                                deleteUserFeature = "deleteUser"
                            }
                        }
                       // alert(editUserFeature)
                        if(userAddFeature == "saveUser"){
                            Ext.getCmp("userListingEditorGrid").doLayout();
                            var addBtn = new Ext.Button({
                                text: "Add an User",
                                iconCls: "addRecord",
                                tooltip: "Add a new User",
                                handler: function(){
                                    window.location = "${createLink(action: 'createUser')}"
                                }
                            })

                            var separator = {xtype: 'tbseparator'};
                            Ext.getCmp("userListingEditorGrid").getTopToolbar().add(addBtn);
                            Ext.getCmp("userListingEditorGrid").getTopToolbar().add(separator);
                        }

                        if(authorityAssignmentFeature == "authorityAssignment"){
                            Ext.getCmp("userListingEditorGrid").doLayout();
                            var authorityAssignmentBtn = new Ext.Button({
                                text: 'Authority Assignment',
                                iconCls:"assignment",
                                tooltip: 'Advanced Search',
                                handler: function(){
                                    try{
                                        var itemIndex = userListingEditorGrid.store.indexOf(userListingEditorGrid.getSelectionModel().getSelected());
                                        userId = userListingEditorGrid.getStore().getAt(itemIndex).get("id");
                                        authorityAssignmentWindow.show(this);
                                    } catch(err){
                                        Ext.MessageBox.alert("Error", "Please select a row!!!");
                                    }
                                }
                            })

                            var separator = {xtype: 'tbseparator'};
                            Ext.getCmp("userListingEditorGrid").getTopToolbar().add(authorityAssignmentBtn);
                            Ext.getCmp("userListingEditorGrid").getTopToolbar().add(separator);
                        }

                        if(userGroupAssignmentFeature == "userGroupAssignment"){

                            Ext.getCmp("userListingEditorGrid").doLayout();
                            var userGroupAssignmentFeatureBtn = new Ext.Button({
                                text: "User Group Assignment",
                                iconCls: "userGroupAssignment",
                                tooltip: "Assign to a User Group",
                                handler: function(){
                                    try{
                                        var itemIndex = userListingEditorGrid.store.indexOf(userListingEditorGrid.getSelectionModel().getSelected());
                                        userId = userListingEditorGrid.getStore().getAt(itemIndex).get("id");
                                        userGroupAssignmentWindow.show(this);
                                    } catch(err){
                                        Ext.MessageBox.alert("Error", "Pleas" +
                                                "e select a row!!!");
                                    }
                                }
                            })

                            var separator = {xtype: 'tbseparator'};
                            Ext.getCmp("userListingEditorGrid").getTopToolbar().add(userGroupAssignmentFeatureBtn);
                            Ext.getCmp("userListingEditorGrid").getTopToolbar().add(separator);
                        }
                    },
                    failure: function(response, opts) {
                       // alert(response)
                    }
                });

                authorityComboStore = new Ext.data.Store({
                    url: '${createLink(action: "authorityJsonData")}',
                    reader: new Ext.data.JsonReader({root: "authorityList", id: "id"},
                            ["id", "description", "roleTitle"]),
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
                });


                featureOperationDataStore = new Ext.data.Store({
                    id: "featureOpearationData",
                    url: '${createLink(action: "featureJsonDataForAuthority")}',
                    reader: new Ext.data.JsonReader({
                                root: 'features',totalProperty: 'totalCount', id: 'id', module:'module' },
                            [
                                {name: 'id', type: 'int', mapping: 'id'},
                                {name: 'module', type: 'string', mapping: 'module'},
                                {name: 'operation', type: 'string', mapping: 'operation'}
                            ]),
//
                    autoLoad : true
                })
//
                checkBoxSelModOperation = new Ext.grid.CheckboxSelectionModel();
                featureOperationGrid = new Ext.grid.EditorGridPanel({
                    id: 'operationEditorGrid',
                    store : featureOperationDataStore,
                    clicksToEdit: 2,
                    selModel : checkBoxSelModOperation,
                    height: 300,
                    width: 370,
                    autoLoad: null,
                    columns: [
                        {
                            dataIndex: 'operation',
                            header: 'Feature',
                            sortable: true,
                            width: 365,
                            editable: true,
                            editor: new Ext.form.TextField()
                        }
                    ],
                    stripeRows : true,
                    bbar: new Ext.PagingToolbar({
                        store : featureOperationDataStore,
                        pageSize : 10,
                        displayInfo : true,
                        displaymsg : 'Displaying {0} - {1} of {2}',
                        emptyMsg : "No records found"
                    })
                })


                ///////////////////





                //code changing comobox to check box
                //////////////////////
                authorityDataStoreForAssignment = new Ext.data.Store({
                    id: "authorityDataStoreForAssignment",
                    url: '${createLink(action: 'authorityJsonData')}',
                    reader: new Ext.data.JsonReader({
                        root: 'authorityList',
                        totalProperty: 'totalCount',
                        id: 'id'
                    },[
                        {name: 'id', type: 'int', mapping: 'id'},
                        {name: 'description', type: 'string', mapping: 'description'},
                        {name: 'roleTitle', type: 'string', mapping: 'roleTitle'}
                    ]),
                    autoLoad : true
                })


                //create the grid
                checkBoxSelModForAuthorityAssignment = new Ext.grid.CheckboxSelectionModel();
                authorityListingEditorGridForAssignment = new Ext.grid.EditorGridPanel({
                    id: 'authorityListingEditorGrid',
                    store : authorityDataStoreForAssignment,
                    clicksToEdit: 2,
                    selModel : checkBoxSelModForAuthorityAssignment,
                    height: 300,
                    //sm: new Ext.grid.CheckboxSelectionModel(),


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
                        store : authorityDataStoreForAssignment,
                        pageSize : 10,
                        displayInfo : true,
                        displaymsg : 'Displaying {0} - {1} of {2}',
                        emptyMsg : "No records found"
                    }),
                    listeners: {
                        "cellclick" : function(grid, rowIndex, columnIndex, e){
                            var record = grid.getStore().getAt(rowIndex);  // Get the Record
//                            featureOperationGrid.update();
                            var store = featureOperationGrid.getStore()

                            var selectedAuthority= authorityListingEditorGridForAssignment.getSelectionModel().getSelections();
//                            alert("selected authority"+selectedAuthority)
                            var selectedModuleArray = [];
                            if (selectedAuthority.length > 0) {
                                var des = [];
                                Ext.each(selectedAuthority, function(sel) {
                                    selectedModuleArray.push(sel.get('id'));
//                                    alert(sel.get('id'))
                                });
                            }
//                            alert(record.get("id"))
                            //first parameter is what we sending in controller as a parameter
                            store.setBaseParam('authorityId', record.get("id"));
                            store.load({
                                params : {start: 0, limit: 10}
                            })
                        }
                    }
                })
                authorityDataStoreForAssignment.load({params: {start: 0, limit: 10}});
//                authorityListingEditorGridForAssignment.render();


                //panel for authority assignment to user
                gridPanel = new Ext.Panel({
                    layout: "table",
                    layoutConfig: {
                        // The total column count must be specified here
                        columns: 2
                    },
                    items: [authorityListingEditorGridForAssignment, featureOperationGrid]
                })
                gridPanel.doLayout();


                authorityAssignmentWindow = new Ext.Window({
                    id: 'authorityAssignmentWindow',
                    title: 'Assigning Authority to selected user',
                    closable: true,
                    width: 720,
                    height: 380,
                    plain:true,
                    modal:true,
                    layout: 'fit',
                    resizable: false,
                    items: gridPanel,
                    closeAction: 'hide',
                    buttons:[
                        {
                            text: 'Save and Close',
                            handler: function(){
                                var sels = authorityListingEditorGridForAssignment.getSelectionModel().getSelections();
                                var selectedAuthorityIdArray = [];
                                if (sels.length > 0) {
                                    var des = [];
                                    Ext.each(sels, function(sel) {
                                        selectedAuthorityIdArray.push(sel.get('id'));
                                        des.push(sel.get('des'));
                                    });
                                }
                                var itemIndex = userListingEditorGrid.store.indexOf(userListingEditorGrid.getSelectionModel().getSelected());
                                userId = userListingEditorGrid.getStore().getAt(itemIndex).get("id");
                                Ext.Ajax.request({
                                    %{--shohag--}%
                                    %{--'${createLink(action: "jsonSessionValue")}',--}%

                                    url: '${createLink(action: "authorityAssignment")}',
                                    params: {userId: userId, authorities: selectedAuthorityIdArray},
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


                ////////////////////////       combo changing code finish /////////////

                /////////////// start user group grid//////////////



                userGroupStore = new Ext.data.Store({
                    url: '${createLink(action: "userGroupJsonData")}',
                    reader: new Ext.data.JsonReader({root: "userGroups", id: "id"},
                            ["id", "description", "groupName"]),
                    autoLoad: true
                });

                //create the grid

                checkBoxSelModForGroupAssignment = new Ext.grid.CheckboxSelectionModel();
                groupListingEditorGridForAssignment = new Ext.grid.EditorGridPanel({
                    id: 'groupListingEditorGridForAssignment ',
                    store : userGroupStore,
                    clicksToEdit: 2,
                    selModel : checkBoxSelModForGroupAssignment,
                    height: 300,
                    //sm: new Ext.grid.CheckboxSelectionModel(),


                    columns: [
                        checkBoxSelModForGroupAssignment,
                        {
                            dataIndex: 'id',
                            header: 'ID',
                            width: 50
                        },
                        {
                            header: 'Description',
                            dataIndex: 'description',
                            sortable: true,
                            width: 305,
                            editable: true,
                            editor: new Ext.form.TextField()
                        }
                    ],
                    stripeRows : true,
                    bbar: new Ext.PagingToolbar({
                        store : userGroupStore,
                        pageSize : 10,
                        displayInfo : true,
                        displaymsg : 'Displaying {0} - {1} of {2}',
                        emptyMsg : "No records found"
                    })
                })
                userGroupStore.load({params: {start: 0, limit: 10}});
//                authorityListingEditorGridForAssignment.render();


                userGroupAssignmentWindow = new Ext.Window({
                    id: 'userGroupAssignmentWindow',
                    title: 'Assign User to a User Group',
                    closable: false,
                    width: 400,
                    height: 500,
                    plain:true,
                    modal:true,
                    layout: 'fit',
                    resizable: false,
                    items: groupListingEditorGridForAssignment,
                    buttons:[
                        {
                            text: 'Save and Close',
                            handler: function(){
                                var selectedGroup = groupListingEditorGridForAssignment.getSelectionModel().getSelections();
                                var selectedGroupIdArray = [];
                                if (selectedGroup.length > 0) {
                                    var des = [];
                                    Ext.each(selectedGroup, function(sel) {
                                        selectedGroupIdArray.push(sel.get('id'));

                                        des.push(sel.get('des'));
                                    });
                                }
                                var itemIndex = userListingEditorGrid.store.indexOf(userListingEditorGrid.getSelectionModel().getSelected());
                                userId = userListingEditorGrid.getStore().getAt(itemIndex).get("id");
                                Ext.Ajax.request({

                                    url: '${createLink(action: "userGroupAssignment")}',
                                    params: {userId: userId, userGroups: selectedGroupIdArray},
                                    success: function ( result, request ) {
                                        Ext.MessageBox.alert("Success", "Group assigned successfully");
                                        userGroupAssignmentWindow.hide();
                                    },
                                    failure: function ( result, request) {
                                        Ext.MessageBox.alert("Failed", "Group assignment failed");
                                        userGroupAssignmentWindow.hide();
                                    }
                                });
                            }
                        },{
                            text: 'Cancel',
                            handler: function(){
                                userGroupAssignmentWindow.hide();
                            }
                        }
                    ]
                });
                /////////////// start user group grid//////////////


                Ext.get('searchButton').on("click", function(){
//                    alert(1);
                    if(Ext.get('userCode').getValue() != ''){
//                        alert(Ext.get('userCode').getValue())

                        userDataStore.setBaseParam('userCode', Ext.get('userCode').getValue());
                        userDataStore.load({
                                    param:{start:0,limit:10}
                                }

                        )

                    }else{
                        alert("Please Enter User Code");
                    }
                })


                userDataStore = new Ext.data.Store({
                    id: "userDataStore",
                    url: '${createLink(action: 'userJsonData')}',
                    reader: new Ext.data.JsonReader({
                        root: 'users',
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
                    renderTo: "list_user",
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
                        },{
                            header:'Action',
                            dataIndex: 'action',
                            width: 200,
                            renderer: function(v, p, record) {
                                var renderText = "";

                                if(editUserFeature== "editUser"){

                                   // alert("if success ")
                                    renderText = renderText + " <a href='${createLink(action: "editUser")}/"+record.get('id')+"'><img title='Edit' src='${resource(dir:'images',file: 'edit.png')}'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                                }
                                renderText = renderText + " <a href='${createLink(action: "showUserDetails")}/"+record.get('id')+"'><img title='Show' src='${resource(dir:'images',file: 'show.png')}'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                                if(deleteUserFeature == "deleteUser"){

                                    if(record.get('id') == ${session.user.id}){
                                         renderText = renderText;
                                    }else{
                                        renderText += " <a id='delete' onclick='deleteRec("+record.get('id')+")' href='#' ><img title='Delete' src='${resource(dir:'images',file: 'delete.png')}'/></a>"
                                    }

                                }
                               // alert(renderText)
                                return renderText;
                            }
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
                    tbar: []
                });
                userDataStore.load({params: {start: 0, limit: 10}});
                function deleteUser(){
//                    alert();
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


        <content tag="rightTag">

        <div class="bread_crumbs_ui_div" style="width: 610px">
            <ul id="crumbs_ui_custom">
                <li><g:link controller="application" action="index">Dashboard</g:link></li>

                <li>User List</li>
            </ul>
        </div>
        <br class="clear"/>

            <div class="widget">

                <div class="header">
                    <span><span class="ico gray user"></span>${type}</span>
                </div>

                <g:if test="${flash.message}">
                    <div class="message" role="status" style="width: 605px; margin-left: 0px;">${flash.message}</div>
                    <br class="clear">
                </g:if>
            <div id="list_user" class="content scaffold-list" role="main">

                <div id="search" style="margin-bottom: 13px;">
                    <label for="userCode">
                        User Code:
                        <g:textField name="userCode" id="userCode"></g:textField>
                    </label>
                    <a id="searchButton" class="button icon approve">Search User</a>
                </div>

            </div>
            </div>
        </content>
    </body>
</g:applyLayout>