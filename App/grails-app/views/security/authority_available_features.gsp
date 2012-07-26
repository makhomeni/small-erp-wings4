<%--
  Created by IntelliJ IDEA.
  User: Shohag
  Date: 4/17/12
  Time: 12:16 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.jabait.security.Authority"%>
<%@ page import="com.jabait.security.Feature"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<g:applyLayout name="app">
    <head>

        <title>Authority Features</title>
        <script>
            var authorityFeatureDataStore;
            var featureListingEditorGrid;
            var checkBoxSelMod;
            Ext.onReady(function(){
                authorityFeatureDataStore = new Ext.data.Store({
                    id: "authorityFeatureDataStore",
                    baseParams: {authorityId: ${authorityId} },
                    url: '${createLink(action: 'featureJsonDataForAuthority')}',
                    reader: new Ext.data.JsonReader({
                                root: 'features',totalProperty: 'totalCount', id: 'id', module:'module',operation:'operation', description:'description'},
                            [
                                {name: 'id', type: 'int', mapping: 'id'},
                                {name: 'module', type: 'string', mapping: 'module'},
                                {name: 'operation', type: 'string', mapping: 'operation'},
                                {name: 'description', type: 'string', mapping: 'description'}
                            ]),
                    autoLoad : true
                })

                checkBoxSelMod = new Ext.grid.CheckboxSelectionModel();
                featureListingEditorGrid = new Ext.grid.EditorGridPanel({
                    id: "authoritiesFeatureGrid",
                    store : authorityFeatureDataStore,
                    selModel : checkBoxSelMod,
                    clicksToEdit: 2,
                    renderTo: "feature_list",
                    height: 350,
                    columns : [
                        {
                            dataIndex: 'module',
                            header: 'Module',
                            sortable: true,
                            width:150,
                            editable: false
                        }
                        ,{
                            dataIndex: 'operation',
                            header: 'Features',
                            sortable: true,
                            width:140,
                            editable: false
                        }
                        ,
                        {
                            header:'Action',
                            width: 70,
                            renderer: function(v, p, record) {
                                var renderText = "";

                                return renderText += " <a id='delete' onclick='deleteRec("+record.get('id')+")' href='#' ><img title='Delete' src='${resource(dir:'images',file: 'delete.png')}'/></a>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "

                            }
                        }
                    ],
                    stripeRows : true,
                    bbar: new Ext.PagingToolbar({
                        store : authorityFeatureDataStore,
                        pageSize : 10,
                        displayInfo : true,
                        displaymsg : 'Displaying {0} - {1} of {2}',
                        emptyMsg : "No records found"
                    }),

                    tbar: []
                });
                authorityFeatureDataStore.load({params: {start: 0, limit: 10}});
            })

            function deleteRec(id){

                Ext.Msg.show({
                    title:'Delete Authority?',
                    msg: 'You are deleting a authority. Would you like to delete?',
                    maxWidth:400,
                    buttons: Ext.Msg.YESNOCANCEL,
                    fn: function(btn){
                        if(btn == 'yes'){


                            window.location = "${createLink(action: 'deleteFeatureFromAuthority')}"+"?featureId="+id+"&authorityId="+${authorityId};
                        }
                    }
                });
            }


        </script>
        <style type="text/css">
             .left_1{
                 width: 462px;
             }
        </style>
    </head>
    <body>

    <content tag="rightTag">

        <div class="bread_crumbs_ui_div" style="width: 934px">
            <ul id="crumbs_ui_custom">
                <li><g:link controller="application" action="index">Dashboard</g:link></li>
                <li><g:link controller="security" action="authorityList">Authority List</g:link></li>
                <li>Authority Details</li>
            </ul>
        </div>
        <br class="clear" />


        <div class="container_16">
            <div class="grid_7 alpha">
                <div class="widget left_1">
                    <div class="header">
                        <span><span class="ico gray feature"></span>Available Features</span>
                    </div>
                    <g:if test="${flash.message}">
                        <div class="message" role="status">${flash.message}</div>
                    </g:if>
                    <div id="feature_list" class="content" role="main"></div>
                </div>

            </div>

            <div class="grid_7 omega">
                <div class="widget" style="width: 445px; margin-left: 64px;">
                    <div class="header">
                        <span><span class="ico gray user"></span>Assigned Users</span>

                    </div>
                    <div class="content tableName">
                        <table class="display data_table" >
                            <tbody>

                            <g:if test="${!users}">
                                <tr>
                                    <td >
                                        <div class="msg">

                                            <div class="msg_topic" style="width: 378px">No user assigned to this group </div>
                                        </div>
                                    </td>

                                </tr>
                            </g:if>

                            <g:each in="${users}" var="userList">
                                <g:each in="${userList}" var="user">

                                    <tr>
                                        <td ><div class="msg">

                                            <div class="msg_topic" style="width: 378px"><strong>${fieldValue(bean: user, field: "userCode")}</strong></div>
                                        </div>
                                        </td>

                                    </tr>

                                </g:each>
                            </g:each>

                            </tbody>
                        </table>

                    </div>

                </div>
            </div>
            <br class="clear">

            <div class="grid_7 alpha">
                <div class="widget" style="width: 463px">
                    <div class="header">
                        <span><span class="ico gray userGroup"></span>Assigned Groups</span>

                    </div>
                    <div class="content tableName">
                        <table class="display data_table" >
                            <tbody>

                            <g:if test="${!userGroups}">
                                <tr>
                                    <td >
                                        <div class="msg">

                                            <div class="msg_topic" style="width: 378px;">No user group assigned to this user </div>
                                        </div>
                                    </td>

                                </tr>
                            </g:if>
                            <g:each in="${userGroups}" var="userGroupList">
                                <g:each in="${userGroupList}" var="userGroup">

                                    <tr>
                                        <td ><div class="msg">

                                            <div class="msg_topic" style="width: 378px;"><strong>${fieldValue(bean: userGroup, field: "groupName")}</strong></div>
                                        </div>
                                        </td>

                                    </tr>

                                </g:each>
                            </g:each>

                            </tbody>
                        </table>

                    </div>

                </div>
            </div>

        </div>


    </content>
    </body>
</g:applyLayout>