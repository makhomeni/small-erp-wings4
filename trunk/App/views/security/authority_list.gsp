<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/19/12
  Time: 3:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.jabait.security.Authority"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<g:applyLayout name="app">
    <head>
        <meta name="layout" content="page">

        <title>${type}</title>
        <script type="text/javascript">
            var authorityDataStore;
            var checkBoxSelMod;
            var authorityListingEditorGrid;

            var featureModuleComboStore;
            var featureModuleCombo;
            var featureOperationComboStore;
            var featureOperationCombo;
            var featureAssignmentForm;
            var featureAssignmentWindow;

            //var userDataStore
           // var userListingEditorGrid;
            var authorityId;


            //for new window
            var featureOperationDataStoreForAssignment;
            var checkBoxSelModForFeatureAssignment;
            var featureListingEditorGridModuleForAssignment;
            var featureListingEditorGridOperationForAssignment;
            var gridPanel;
            var checkBoxSelModOperationForFeatureAssignment;
            var featureModuleDataStore;
            var featureOperationDataStoreForAssignment1;


            var loggedUsersAuthorityId;
            var editAuthority = "editAuthority";
            var deleteAuthority = "deleteAuthority";

            var selectedOperationArray = [];

            function deleteRec(id){

                Ext.Msg.show({
                    title:'Delete Authority?',
                    msg: 'You are deleting a authority. Would you like to delete?',
                    maxWidth:400,
                    buttons: Ext.Msg.YESNOCANCEL,
                    fn: function(btn){
                        if(btn == 'yes'){

                            window.location = "${createLink(action: 'deleteAuthority')}"+"/"+id;
                        }
                    }
                });
            }

            Ext.onReady(function(){


                //for checking menu items are authorized
                var authorityAddFeature = "";
                var featureAssignmentFeature = "";


				
				
                Ext.Ajax.request({
                    url: '${createLink(action: "jsonSessionValue")}',
                    params: {id : ${session.user.id}},
                    success: function(response, opts) {
                        var totalLength = Ext.util.JSON.decode(response.responseText).grantedAuthorityJson[0].length
                        for(i = 0; i < totalLength; i++){
                            if(Ext.util.JSON.decode(response.responseText).grantedAuthorityJson[0][i].operation == "saveAuthority"){
                                authorityAddFeature = "saveAuthority"
                            }

                            if(Ext.util.JSON.decode(response.responseText).grantedAuthorityJson[0][i].operation == "featureAssignment"){
                                featureAssignmentFeature = "featureAssignment"
                            }
                            if(Ext.util.JSON.decode(response.responseText).grantedAuthorityJson[0][i].operation == "editAuthority"){
                                editAuthority = "editAuthority"
                            }
                            if(Ext.util.JSON.decode(response.responseText).grantedAuthorityJson[0][i].operation == "deleteAuthority"){
                                deleteAuthority = "deleteAuthority"
                            }
                        }

                        //for save authority
                        if(authorityAddFeature == "saveAuthority"){
                            Ext.getCmp("authorityListingEditorGrid").doLayout();
                            var addBtn = new Ext.Button({
                                text: "Add an Authority",
                                iconCls: "addRecord",
                                tooltip: "Add an Authority",
                                handler: function(){
                                    window.location = "${createLink(action: 'createAuthority')}"
                                }
                            })

                            var separator = {xtype: 'tbseparator'};
                            Ext.getCmp("authorityListingEditorGrid").getTopToolbar().add(addBtn);
                            Ext.getCmp("authorityListingEditorGrid").getTopToolbar().add(separator);
                        }

                        //for assignment feature
                        if(featureAssignmentFeature == "featureAssignment"){
                            Ext.getCmp("authorityListingEditorGrid").doLayout();
                            var featureAssignmentBtn = new Ext.Button({
                                text: 'Feature Assignment',
                                iconCls:"assignment",
                                tooltip: 'Advanced Search',
                                handler: function(){
                                    try{
                                        var itemIndex = authorityListingEditorGrid.store.indexOf(authorityListingEditorGrid.getSelectionModel().getSelected());
                                        authorityId = authorityListingEditorGrid.getStore().getAt(itemIndex).get("id");

                                        featureAssignmentWindow.show(this);
                                    } catch(err){
                                        Ext.MessageBox.alert("Error", "Please select a row!!!");
                                    }
                                }
                            })

                            var separator = {xtype: 'tbseparator'};
                            Ext.getCmp("authorityListingEditorGrid").getTopToolbar().add(featureAssignmentBtn);
                            Ext.getCmp("authorityListingEditorGrid").getTopToolbar().add(separator);
                        }


                    },
                    failure: function(response, opts) {
//                        alert(response)
                    }
                });



                Ext.Ajax.request({
                    url:'${createLink(action: "jsonValueForUserDetails")}',
                    params: {id : ${session.user.id}},
                    success: function(response, opts) {
                        loggedUsersAuthorityId = Ext.util.JSON.decode(response.responseText).authorities[0].id

                    },
                    failure:function(respone, opts){

                    }
                })


               // alert(authorityAddFeature)
                //end

                // featureModuleCombo
                featureModuleComboStore = new Ext.data.Store({
                    url: '${createLink(action: "featureModuleJsonData")}',
                    reader: new Ext.data.JsonReader({root: "modules", id: "id", totalProperty: 'totalCount'}),
                    autoLoad: true
                });

                // featureOperationCombo
                featureOperationComboStore = new Ext.data.Store({
                    url: '${createLink(action: "featureComboJsonData")}',
                    reader: new Ext.data.JsonReader({root: "features", id: "id"}),
                    autoLoad: true
                });

                featureModuleCombo = new Ext.form.ComboBox({
                    id: "featureModuleCombo",
                    store: featureModuleComboStore,
                    hiddenName: "xmlType",
                    valueField: "module",
                    displayField: "module",
                    emptyText: "Please select a module...",
                    triggerAction: "all",
                    width: 200,
                    hideTrigger: false,
                    editable: true,
                    mode: "local",
                    lazyInit: true,
                    enableKeyEvents: true,
                    listeners: {select: {
                            fn:function(combo,value){
                                var operationCombo = Ext.getCmp("featureOperationCombo");
                                operationCombo.clearValue();
                                operationCombo.store.filter("module", combo.getValue());
                            }
                        }
                    }
                });

                featureOperationCombo = new Ext.ux.form.LovCombo({
                    id: "featureOperationCombo",
                    store : featureOperationComboStore,
                    hiddenName : 'xmlType',
                    valueField : 'operation',
                    displayField : 'operation',
                    emptyText : 'Please select operation(s)...',
                    triggerAction : 'all',
                    width : 200,
                    hideTrigger : false,
                    mode : 'local',
                    lazyInit: true,
                    editable: true,
                    enableKeyEvents: true,
                    lastQuery: ''
                });
                // featureOperationCombo

                featureAssignmentForm = new Ext.FormPanel({
                    items:[featureModuleCombo,featureOperationCombo]
                });
                //////////////////start changing form///////////////////////
                


                //data store for distinct module
                featureModuleDataStore = new Ext.data.Store({
                    url: '${createLink(action: "featureModuleJsonData")}',
                    reader: new Ext.data.JsonReader({root: "modules", id: "id", totalProperty: 'totalCount'},
                            [
                                {name:'id', type:'int', mapping:'id'},
                                {name:'module', type:'string', mapping:'module'}
                            ]),
                    autoLoad: true
                });


                //create the grid  for module
                checkBoxSelModForFeatureAssignment = new Ext.grid.CheckboxSelectionModel({
                    listeners: {
                        "cellclick" : function(grid, rowIndex, columnIndex, e){
                            var record = grid.getStore().getAt(rowIndex);  // Get the Record
                            var store = featureListingEditorGridOperationForAssignment.getStore()
                            //store.filter("module", "saveUser");
                            //store.removeAll()
                            store.setBaseParam('moduleName', record.get("module"));
                            store.load({
                                params : {start: 0, limit: 10}
                            })
                        }
                    }
                });
                featureListingEditorGridModuleForAssignment = new Ext.grid.EditorGridPanel({
                    id: 'authorityListingEditorGridModule',
                    store : featureModuleDataStore,
                    clicksToEdit: 2,
                    selModel : checkBoxSelModForFeatureAssignment,
                    height: 300,
                    width:350,
					//sm: new Ext.grid.CheckboxSelectionModel(),
                    columns: [
                        {
                            dataIndex: 'module',
                            header: 'Module',
                            width: 340
                        }
                    ],
                    stripeRows : true,
                    bbar: new Ext.PagingToolbar({
                        store : featureModuleDataStore,
                        pageSize : 10,
                        displayInfo : true,
                        displaymsg : 'Displaying {0} - {1} of {2}',
                        emptyMsg : "No records found"
                    }),
					listeners: {
						"cellclick" : function(grid, rowIndex, columnIndex, e){
							var record = grid.getStore().getAt(rowIndex);  // Get the Record
							var store = featureListingEditorGridOperationForAssignment.getStore()
							var selectedModule= featureListingEditorGridModuleForAssignment.getSelectionModel().getSelections();
							var selectedModuleArray = [];
							if (selectedModule.length > 0) {
								var des = [];
								Ext.each(selectedModule, function(sel) {
									selectedModuleArray.push(sel.get('module'));
								});
							}
							store.setBaseParam('moduleName', record.get("module"));
							store.load({
								params : {start: 0, limit: 10}
							})
						}
					}
                })
                featureModuleDataStore.load({params: {start: 0, limit: 10}});
                //// another grid ////////////////
                //create the grid
				featureOperationDataStoreForAssignment = new Ext.data.Store({
                    id: "authorityDataStoreForAssignment",
                    url: '${createLink(action: "featureOperationJsonData")}',
                    reader: new Ext.data.JsonReader({
                                root: 'operations',totalProperty: 'totalCount', id: 'id', module:'module' },
                            [
                                {name: 'id', type: 'int', mapping: 'id'},
                                {name: 'module', type: 'string', mapping: 'module'},
                                {name: 'operation', type: 'string', mapping: 'operation'}
                            ]),
                    autoLoad : true
                })
                //cut it from here

                checkBoxSelModOperationForFeatureAssignment = new Ext.grid.CheckboxSelectionModel({
                    listeners: {
                        "rowselect" : function(grid, rowIndex, columnIndex, e){
                            var selectedOperations= featureListingEditorGridOperationForAssignment.getSelectionModel().getSelections();

                                Ext.each(selectedOperations, function(sel) {
                                    selectedOperationArray.push(sel.get('id'));
                                });

                        }
                    }
                });


                featureListingEditorGridOperationForAssignment = new Ext.grid.EditorGridPanel({
                    id: 'operationEditorGrid',
                    store : featureOperationDataStoreForAssignment,
                    clicksToEdit: 2,
                    selModel : checkBoxSelModOperationForFeatureAssignment,
                    height: 300,
                    width: 370,
                    autoLoad: null,
                    columns: [
                        checkBoxSelModOperationForFeatureAssignment,
                        {
                            dataIndex: 'operation',
                            header: 'Operation',
                            sortable: true,
                            width: 340,
                            editable: true,
                            editor: new Ext.form.TextField()
                        }
                    ],
                    stripeRows : true,
                    bbar: new Ext.PagingToolbar({
                        store : featureOperationDataStoreForAssignment,
                        pageSize : 10,
                        displayInfo : true,
                        displaymsg : 'Displaying {0} - {1} of {2}',
                        emptyMsg : "No records found"
                    })
                })

                //featureOperationDataStoreForAssignment.load({params: {start: 0, limit: 10}});
                //featureListingEditorGridOperationForAssignment.hide();
                ///cut it above here //
                ///

                ///////////////////////////////////////////
                gridPanel = new Ext.Panel({
                    layout: "table",
                    layoutConfig: {
                        // The total column count must be specified here
                        columns: 2
                    },
                    items: [featureListingEditorGridModuleForAssignment, featureListingEditorGridOperationForAssignment]
                })

                gridPanel.doLayout()

                featureAssignmentWindow = new Ext.Window({
                    id: 'featureAssignmentWindow',
                    title: 'Assigning Feature to selected authority',
                    closable: false,
                    width: 720,
                    height: 380,
                    plain:true,
                    modal:true,
                    layout: 'fit',
                    resizable: false,
                    items: gridPanel,
                    buttons:[
                        {
                            text: 'Save and Close',
                            handler: function(){
                                //for getting selected modules
                                var selectedModule= featureListingEditorGridModuleForAssignment.getSelectionModel().getSelections();
                                var selectedModuleArray = [];
                                if (selectedModule.length > 0) {
                                    var des = [];
                                    Ext.each(selectedModule, function(sel) {
                                        selectedModuleArray.push(sel.get('id'));
                                    });
                                }

                                var itemIndex = authorityListingEditorGrid.store.indexOf(authorityListingEditorGrid.getSelectionModel().getSelected());
                                authorityId = authorityListingEditorGrid.getStore().getAt(itemIndex).get("id");
                                Ext.Ajax.request({

                                    url: '${createLink(action: "featureAssignment")}',
                                    params: {authorityId: authorityId, featureOperations: selectedOperationArray},
                                    success: function ( result, request ) {
                                        Ext.MessageBox.alert("Success", "Feature assigned successfully");
                                        featureAssignmentWindow.hide();
                                    },
                                    failure: function ( result, request) {
                                        Ext.MessageBox.alert("Failed", "Feature assignment failed");
                                        featureAssignmentWindow.hide();
                                    }
                                });


                                %{--featureAssignmentForm.getForm().submit({--}%
                                    %{--url:'${createLink(action: "featureAssignment")}',--}%
                                    %{--params: {--}%
                                        %{--authorityId: authorityId,--}%
                                        %{--featureModule: featureAssignmentForm.getForm().findField("featureModuleCombo").value,--}%
                                        %{--featureOperations: featureAssignmentForm.getForm().findField("featureOperationCombo").value--}%
                                    %{--},success: function(response){--}%
                                        %{--Ext.MessageBox.alert("Success", "Feature added successfully");--}%
                                        %{--featureAssignmentWindow.hide();--}%
                                    %{--},--}%
                                    %{--failure: function(response){--}%
                                        %{--Ext.MessageBox.alert("Failed", "Feature assignment failed");--}%
                                        %{--featureAssignmentWindow.hide();--}%
                                    %{--}--}%
                                %{--})--}%
                            }
                        }
                        ,
                        {
                            text: 'Cancel',
                            handler: function(){
                                featureAssignmentWindow.hide();
                            }
                        }
                    ]
                });
                //for table
                authorityDataStore = new Ext.data.Store({
                    id: "authorityDataStore",
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
                checkBoxSelMod = new Ext.grid.CheckboxSelectionModel();//EXTGS
                authorityListingEditorGrid = new Ext.grid.EditorGridPanel({
                    id: 'authorityListingEditorGrid',
                    store : authorityDataStore,
                    selModel : checkBoxSelMod,
                    clicksToEdit: 2,
                    renderTo: "authority_list",
                    height: 300,
                    columns : [
                        {
                            dataIndex: 'id',
                            header: 'ID',
                            width: 50
                        },{
                            dataIndex: 'description',
                            header: 'Description',
                            sortable: true,
                            width: 135,
                            editable: false,
                            editor: new Ext.form.TextField()
                        },{
                            dataIndex: 'roleTitle',
                            header: 'Role Title',
                            sortable: true,
                            width: 150
                        },{
                            header:'Action',
                            width: 200,
                            renderer: function(v, p, record) {
                                var renderText = "";
                                if(editAuthority == "editAuthority"){
                                    // alert("if success ")
                                    renderText = renderText + " <a href='${createLink(action: "editAuthority")}/"+record.get('id')+"'><img title='Edit' src='${resource(dir:'images',file: 'edit.png')}'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                                }
                                if(deleteAuthority == "deleteAuthority"){

                                    if(record.get('id') == loggedUsersAuthorityId){
                                        renderText = renderText;
                                    }else{
                                        renderText += " <a id='delete' onclick='deleteRec("+record.get('id')+")' href='#' ><img title='Delete' src='${resource(dir:'images',file: 'delete.png')}'/></a>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "
                                    }

                                }
                                // alert(renderText)
                                return renderText + " <a href='${createLink(action: "showAuthorityDetails")}/"+record.get('id')+"'><img title='Show' src='${resource(dir:'images',file: 'show.png')}'/></a>"


                            }
                        }
                    ],
                    stripeRows : true,
                    bbar: new Ext.PagingToolbar({
                        store : authorityDataStore,
                        pageSize : 10,
                        displayInfo : true,
                        displaymsg : 'Displaying {0} - {1} of {2}',
                        emptyMsg : "No records found"
                    }),
                    tbar: []
                })
                authorityDataStore.load({params: {start: 0, limit: 10}});
            })
        </script>
    </head>
    <body>

    <content tag="rightTag">

        <div class="bread_crumbs_ui_div" style="width: 611px">
            <ul id="crumbs_ui_custom">
                <li><g:link controller="application" action="index">Dashboard</g:link></li>
                <li><g:link controller="security" action="authorityList">Authority List</g:link></li>
                <li>Authority Create</li>
            </ul>
        </div>

       <br class="clear">
        <g:if test="${flash.message}">
            <div class="message" role="status" style="width: 609px; margin-left: 0px;">${flash.message}</div>
            <br class="clear">
        </g:if>

        <div class="widget">

            <div class="header" style="width: 610px;">
                <span><span class="ico gray authority"></span>${type}</span>
            </div>


        <div id="authority_list" class="content scaffold-list" role="main">


        </div>
        </div>
    </content>
    </body>
</g:applyLayout>