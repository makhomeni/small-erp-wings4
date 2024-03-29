<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/19/12
  Time: 3:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="grails.converters.JSON; com.jabait.scm.inventory.Category"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<g:applyLayout name="app">
    <head>
        <g:set var="entityName" value="${message(code: 'category.description', default:'Category')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <script>


            function deleteRec(id){

                Ext.Msg.show({
                    title:'Delete User?',
                    msg: 'You are deleting a user. Would you like to delete?',
                    maxWidth:400,
                    buttons: Ext.Msg.YESNOCANCEL,
                    fn: function(btn){
                        if(btn == 'yes'){

                            window.location = "${createLink(controller:'inventory', action: 'deleteProduct')}"+"/"+id;
                        }
                    }
                });
            }

            Ext.onReady(function(){
                //for checking menu items are authorized
                var userAddFeature = "";
                var authorityAssignmentFeature = "";
                var userGroupAssignmentFeature = "";

                //for showing inrenderer
                var editUserFeature = "";
                var deleteUserFeature = "";
                /////////////// start user group grid//////////////

                productDataStore = new Ext.data.Store({
                    id: "productDataStore",
                    url: '${createLink(controller:'inventory', action: 'categoryJsonData')}',
                    reader: new Ext.data.JsonReader({
                        root: 'categories',
                        totalProperty: 'totalCount',
                        id: 'id'
                    },[
                        {name: 'id', type: 'int', mapping: 'id'},
                        {name: 'categoryName', type: 'string', mapping: 'categoryName'},
                        {name: 'parentCategory', type: 'string', mapping: 'parentCategory'}
                    ]),
                    autoLoad : true
                })
                checkBoxSelMod = new Ext.grid.CheckboxSelectionModel();
                productListingEditorGrid = new Ext.grid.EditorGridPanel({
                    id: "productListingEditorGrid",
                    store : productDataStore,
                    selModel : checkBoxSelMod,
                    clicksToEdit: 2,
                    renderTo: "list_category",
                    width: 900,
                    height: 300,
                    columns : [
                        {
                            dataIndex: 'id',
                            header: 'ID',
                            width: 50
                        },{
                            dataIndex: 'categoryName',
                            header: 'Category Name',
                            sortable: true,
                            width:160,
                            editable: false
                        },{
                            dataIndex: 'parentCategory',
                            header: 'Parent Category',
                            sortable: true,
                            width: 150
                        },{
                            header:'Action',
                            dataIndex: 'action',
                            width: 200,
                            header:'Action',
                            dataIndex: 'action',
                            width: 200,
                            renderer: function(v, p, record) {
                                var renderText  = "";

                                renderText = renderText + " <a href='${createLink(controller: "employee",action: "editUser")}/"+record.get('id')+"'><img title='Edit' src='${resource(dir:'images',file: 'edit.png')}'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

                                renderText = renderText + " <a href='${createLink(controller: 'configuration',action: "showOrganizationDetails")}/"+record.get('id')+"'><img title='Show' src='${resource(dir:'images',file: 'show.png')}'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";


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
                        store : productDataStore,
                        pageSize : 10,
                        displayInfo : true,
                        displaymsg : 'Displaying {0} - {1} of {2}',
                        emptyMsg : "No records found"
                    }),
                    tbar: [{
                        xtype: "button",
                        text: "Create Category",
                        iconCls: "addRecord" ,
                        handler:function(){
                            window.location="${createLink(controller: 'inventory', action: 'createCategory')}"
                        }
                    }]
                });
                productDataStore.load({params: {start: 0, limit: 10}});
                function deleteUser(){
                    alert();
                }
            })


        </script>
        <style type="text/css">
        .header{
            width: 940px;
        }
        .widget{
            width: 940px;
        }

        </style>
    </head>
    <body>
    %{--<content tag="bannerTag"><h1>${type}</h1></content>--}%


    <content tag="rightTag">

        <div class="bread_crumbs_ui_div" style="width: 942px">
            <ul id="crumbs_ui_custom">
                <li><g:link controller="application" action="index">Dashboard</g:link></li>
                <li><g:link controller="scm" action="home">Supply Chain Management Home</g:link></li>
                <li><g:link controller="inventory" action="home">Inventory Home</g:link></li>
                <li>${type}</li>
            </ul>
        </div>
        <br class="clear"/>

        <div class="widget">

            <div class="header">
                <span><span class="ico gray user"></span>${type}</span>
            </div>

            <g:if test="${flash.message}">
                <div class="message" role="status" style="width: 940px; margin-left: 0px;">${flash.message}</div>
                <br class="clear">
            </g:if>
            <div id="list_category" class="content scaffold-list" role="main">

                <div id="search" style="margin-bottom: 13px;">
                    <label for="categoryName">
                        Category Name:
                        <g:textField name="categoryName" id="categoryName"></g:textField>
                    </label>
                    <a id="searchButton" class="button icon approve">Search Category</a>
                </div>

            </div>
        </div>
    </content>
    </body>
</g:applyLayout>