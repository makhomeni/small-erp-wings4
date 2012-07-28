<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/20/12
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.jabait.hrm.Organization"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<g:applyLayout name="app">
<head>

    <title>${title}</title>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'livevalidation_standalone.compressed.js')}">

    </script>

    <script type="text/javascript">

        function submitForm(){
            document.leaveEncashmentForm.submit();
        }

    </script>

    <script type="text/javascript">

        //var's for organization
        var organizationDataStore;
        var organizationGrid;
        var checkBoxSelModModelOrganization;
        var organizationWindow;

        Ext.onReady(function(){
            productDataStore = new Ext.data.Store({
                id: "organizationDataStore",
                url: '${createLink(controller: 'employee',action: 'organizationJsonData')}',
                reader: new Ext.data.JsonReader({
                    root: 'organizations',
                    totalProperty: 'totalCount',
                    id: 'id'
                },[
                    {name: 'id', type: 'int', mapping: 'id'},
                    {name: 'organizationName', type: 'string', mapping: 'organizationName'},
                    {name: 'phone', type: 'string', mapping: 'phone'}
                ]),
                autoLoad : true
            });


            //create the grid
            checkBoxSelModModelOrganization = new Ext.grid.CheckboxSelectionModel();
            organizationGrid = new Ext.grid.EditorGridPanel({
                id: 'organizationGrid',
                store : productDataStore,
                clicksToEdit: 2,
                selModel : checkBoxSelModModelOrganization,
                height: 250,
                //sm: new Ext.grid.CheckboxSelectionModel(),
                columns: [

                    {
                        dataIndex: 'organizationName',
                        header: 'Organization',
                        sortable: true,
                        width: 370,
                        editable: true,
                        editor: new Ext.form.TextField()
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
                listeners: {
                    "cellclick" : function(grid, rowIndex, columnIndex, e){
                        var selectedOrganization= organizationGrid.getSelectionModel().getSelections();

                        Ext.each(selectedOrganization, function(sel) {
//                            alert(sel.get('id'))

                            $("#organizationName").val(sel.get('organizationName'));
                            $("#organizationId").val(sel.get('id'));

                            //to show department selection option


                            //show thw department selection part

                            //hide the window
                            organizationWindow.hide();

                        });



                    }
                }
            });
            productDataStore.load({params: {start: 0, limit: 10}});

            organizationWindow = new Ext.Window({
                id: 'organizationWindow',
                title: 'Select Organization for Employee',
                closable: false,
                width: 400,
                height: 400,
                plain:true,
                modal:true,
                layout: 'fit',
                resizable: false,
                items: organizationGrid,
                buttons:[
                    {
                        text: 'Cancel',
                        handler: function(){
                            organizationWindow.hide();
                        }
                    }
                ]
            });



            ////////////End organization section/////////////


        });


    </script>

</head>
<body>

<content tag="rightTag">
    <script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.uniform.js')}" ></script>


    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'uniform.aristo.css')}">

    <style type="text/css">
        div.selector{
            margin-top: -20px;
            margin-left: 156px;
        }
        .adjustImg{
            margin-bottom: -10px;
            margin-top: -6px;
            margin-left: -4px;
        }
    </style>

    <script type="text/javascript">
        $(document).ready(function(){
            $("input, textarea, select, button").uniform();
        })
    </script>

    <div class="bread_crumbs_ui_div" style="width: 611px">
        <ul id="crumbs_ui_custom">
            <li><g:link controller="application" action="index">Dashboard</g:link></li>

            <li><g:link controller="configuration" action="allowanceTypeList">Payroll Config Home</g:link></li>
            <li>Leave Encashment</li>
        </ul>
    </div>

    <br class="clear">
    <g:if test="${flash.message}">
        <div class="message" style="width: 610px; margin-left: 0px;">${flash.message}</div>
    </g:if>

    <div class="widget">

        <div class="header" style="width: 610px;">
            <span><span class="ico gray encash"></span>Leave Encashment</span>
        </div>


        <g:form name="leaveEncashmentForm" id="leaveEncashmentForm" controller="configuration" action="leaveEncashmentAssignment" method="POST">
            <fieldset class="form">

                <div class="fieldcontain">
                    <label for="organizationName">
                        Organization Name
                        <span class="required-indicator">*</span>
                    </label>

                    <g:textField name="organizationName" id="organizationName" required=""  value="" />
                    <g:hiddenField name="organizationId" id="organizationId"></g:hiddenField>
                    <img src="${resource(dir: 'images', file: 'organization_picker.png')}" alt="Organization Image" onclick="organizationWindow.show()" class="adjustImg" title="Click to select organization">

                </div>

                 <g:hiddenField name="systemKey" id="systemKey" value="leave encashment"></g:hiddenField>

                <div class="fieldcontain">
                    <label for="status">
                        Encashment status
                        <span class="required-indicator">*</span>
                    </label>


                    <g:select name="status" id="status" from="${['True', 'False']}"/>

                </div>

                <br class="clear" />
                <br class="clear" />

                <div class="grid_4 omega" style="margin-left: 227px;margin-bottom: 10px;">
                    <a id="organizationForm" class="button icon approve" onclick="submitForm()">Save</a>
                    <a id="ff" class="button danger icon remove" onclick="document.leaveEncashmentForm.reset()">Clear</a>
                </div>




            </fieldset>
            <br class="clear"/>

        </g:form>
    </div>
</content>
</body>
</g:applyLayout>