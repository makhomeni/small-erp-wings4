<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/19/12
  Time: 3:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.jabait.security.Feature"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<g:applyLayout name="app">
    <head>
        <meta name="layout" content="page">

        <title>${type}</title>
        <script type="text/javascript">
            var featureDataStore;
            var checkBoxSelMod;
            var featureListingEditorGrid;
            var featureAddFeature = "";
            var deleteFeature = "deleteFeature";
            var editFeature = "editFeature";
            var featureAddButton;

            //for deleting confirmation
            function deleteRec(id){

                Ext.Msg.show({
                    title:'Delete Feature?',
                    msg: 'You are deleting a feature. Would you like to delete?',
                    maxWidth:400,
                    buttons: Ext.Msg.YESNOCANCEL,
                    fn: function(btn){
                        if(btn == 'yes'){

                            window.location = "${createLink(action: 'deleteFeature')}"+"/"+id;
                        }
                    }
                });
            }


            Ext.onReady(function(){

                //code for dynamic tbar

                //var featureAssignmentFeature = "";
                var addFeature = "";

                Ext.Ajax.request({
                    url: '${createLink(action: "jsonSessionValue")}',
                    params: {id : ${session.user.id}},
                    success: function(response, opts) {
                        var totalLength = Ext.util.JSON.decode(response.responseText).grantedAuthorityJson[0].length
                        for(i = 0; i < totalLength; i++){
                            if(Ext.util.JSON.decode(response.responseText).grantedAuthorityJson[0][i].operation == "saveFeature"){
                                addFeature = "saveFeature"
                            }
                            if(Ext.util.JSON.decode(response.responseText).grantedAuthorityJson[0][i].operation == "deleteFeature"){
                                deleteFeature = "deleteFeature"
                            }
                            if(Ext.util.JSON.decode(response.responseText).grantedAuthorityJson[0][i].operation == "editFeature"){
                                editFeature = "editFeature"
                            }
                        }
                        
                        if(addFeature.match("saveFeature")){
                            Ext.getCmp("featureListingEditorGrid").doLayout();
                            var featSaveBtn = new Ext.Button({
                                text: "Add a Feature",
                                iconCls: "addRecord",
                                tooltip: "Add a Feature",
								handler: function(){
									window.location = "${createLink(action: 'createFeature')}"
								}
                            })

                            var separator = {xtype: 'tbseparator'};
                            Ext.getCmp("featureListingEditorGrid").getTopToolbar().add(featSaveBtn);
                            Ext.getCmp("featureListingEditorGrid").getTopToolbar().add(separator);
                        }
						
						if(addFeature.match("saveFeature")){
                            Ext.getCmp("featureListingEditorGrid").doLayout();
                        }


                    },
                    failure: function(response, opts) {
                        // alert(response)
                    }
                });

                //alert(authorityAddFeature)
                //end dynamic tbar
                featureDataStore = new Ext.data.Store({
                    id: "featureDataStore",
                    url: '${createLink(action: 'featureJsonData')}',
                    reader: new Ext.data.JsonReader({
                        root: 'features',
                        totalProperty: 'totalCount',
                        id: 'id'
                    },[
                        {name: 'id', type: 'int', mapping: 'id'},
                        {name: 'module', type: 'string', mapping: 'module'},
                        {name: 'operation', type: 'string', mapping: 'operation'}
                    ]),
                    autoLoad : true
                })

                //create the grid
                checkBoxSelMod = new Ext.grid.CheckboxSelectionModel();
                featureListingEditorGrid = new Ext.grid.EditorGridPanel({
                    id: 'featureListingEditorGrid',
                    store : featureDataStore,
                    selModel : checkBoxSelMod,
                    clicksToEdit: 2,
                    renderTo: "feature_list",
                    height: 340,
                    columns : [
                        {
                            dataIndex: 'id',
                            header: 'ID',
                            width: 50
                        },{
                            dataIndex: 'module',
                            header: 'Module',
                            sortable: true,
                            width: 130,
                            editable: false
                        },{
                            dataIndex: 'operation',
                            header: 'Operation',
                            sortable: true,
                            width: 160
                        },{
                            header:'Action',
                            width: 190,
                            renderer: function(v, p, record) {
                                var renderText = "";
                                if(editFeature == "editFeature"){
                                    // alert("if success ")
                                    renderText = renderText + " <a href='${createLink(action: "editFeature")}/"+record.get('id')+"'><img title='Edit' src='${resource(dir:'images',file: 'edit.png')}'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                                }
                                if(deleteFeature == "deleteFeature"){
                                %{--<a id='delete' onclick='deleteFeature("+record.get('id')+")' href='#' ><img title='Delete' src='${resource(dir:'images',file: 'delete.png')}'/></a>  --}%
                                    renderText += " <a id='delete' onclick='deleteRec("+record.get('id')+")' href='#' ><img title='Delete' src='${resource(dir:'images',file: 'delete.png')}'/></a>"


                                }
                                // alert(renderText)
                                 return renderText
                                 }
                        }
                    ],
                    stripeRows : true,
                    bbar: new Ext.PagingToolbar({
                        store : featureDataStore,
                        pageSize : 10,
                        displayInfo : true,
                        displaymsg : 'Displaying {0} - {1} of {2}',
                        emptyMsg : "No records found"
                    }),
                    tbar: []
                })

                featureDataStore.load({params: {start: 0, limit: 10}});

            })
        </script>
    </head>
    <body>

    <content tag="bannerTag">
        <h1>${type}</h1>
    </content>
    <content tag="rightTag">

        <div class="bread_crumbs_ui_div" style="width: 611px">
            <ul id="crumbs_ui_custom">
                <li><g:link controller="application" action="index">Dashboard</g:link></li>
                <li>Feature List</li>
            </ul>
        </div>
        <br class="clear" />

        <div class="widget">

            <div class="header" style="width: 610px">

                <span><span class="ico gray feature"></span>${type}</span>
            </div>
            <g:if test="${flash.message}">
                <div class="message" role="status" style="width: 606px; margin-left: 0px;">${flash.message}</div>
            </g:if>

        <div id="feature_list" class="content scaffold-list" role="main"></div>

            <div id="search" style="margin-bottom: 13px;">
                <label for="userCode">
                    User Code:
                    <g:textField name="userCode" id="userCode"></g:textField>
                </label>
                <a id="searchButton" class="button icon approve">Search User</a>
            </div>

        </div>
    </content>
    </body>
</g:applyLayout>