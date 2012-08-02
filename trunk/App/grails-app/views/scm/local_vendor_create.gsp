<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 7/31/12
  Time: 1:04 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>

<g:applyLayout name="app">
    <head>

        <title>Create Vendor</title>

        <script type="text/javascript">

            var organizationDataStore;
            var organizationGrid;
            var checkBoxSelectionModelOrganization;
            var organizationWindow;


            Ext.onReady(function(){

                organizationDataStore = new Ext.data.Store({
                    id: "organizationDataStore",
                    url: '${createLink(controller: 'employee', action: 'organizationJsonData')}',
                    reader: new Ext.data.JsonReader({
                        root:'organizations',
                        id: 'id',
                        totalProperty:'totalCount'
                    },[
                        {name: 'id', type: 'int', mapping: 'id'},
                        {name: 'organizationName', type: 'string', mapping: 'organizationName'}
                    ]),
                    autoLoad: true

                });

                checkBoxSelectionModelOrganization = new Ext.grid.CheckboxSelectionModel();
                organizationGrid = new Ext.grid.EditorGridPanel({
                    id:'organizationGrid',
                    store: organizationDataStore,
                    clicksToEdit: 2,
                    selModel: checkBoxSelectionModelOrganization,
                    height: 500,
                    columns: [
                        {
                            dataIndex:'organizationName',
                            header: 'Organization Name',
                            sortable: true,
                            width: 370,
                            editor: new Ext.form.TextField()
                        }
                    ],
                    stripeRows: true,
                    bbar: new Ext.PagingToolbar({
                        store : organizationDataStore,
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

                                $("#organization").val(sel.get('organizationName'))
                                $("#organizationId").val(sel.get('id'))

                                organizationWindow.hide();

                            });

                        }
                    }
                });
                organizationDataStore.load({params: {start: 0, limit: 10}});
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

            });


            function submitForm(){
                document.vendorForm.submit();
            }

        </script>
        <style type="text/css">
        .adjustImg{

            margin-left: -4px;
            margin-bottom: -9px;
        }
        </style>

    </head>
    <body>
    <content tag="bannerTag">
        <h1>${type}</h1>
    </content>
    <content tag="rightTag">

        <style type="text/css">
            div.selector{
                margin-top: -20px;
                margin-left: 156px;
            }
        </style>
        <script type="text/javascript">

        </script>

        <div class="bread_crumbs_ui_div" style="width: 611px">
            <ul id="crumbs_ui_custom">
                <li><g:link controller="application" action="index">Dashboard</g:link></li>
                <li><g:link controller="scm" action="home">Supply Chain Management Home</g:link></li>
                <li><g:link controller="procurement" action="vendorHome">Vendor Home</g:link></li>
                <li><g:link controller="vendor" action="localVendorList">Local Vendor List</g:link></li>
                <li>Create Local Vendor </li>
            </ul>
        </div>
        <br class="clear" />

        <div class="widget">

            <div class="header" style="width: 610px;">
                <span><span class="ico gray organization"></span>${type}</span>
            </div>

            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:form name="vendorForm" id="vendorForm" controller="vendor" action="saveLocalVendor" method="POST">
                <fieldset class="form">

                    <div class="fieldcontain">
                        <label for="firstName">
                            <g:message code="firstName.label" default="First Name" />
                            <span class="required-indicator">*</span>
                        </label>
                        <g:textField name="firstName" required=""  value="" />
                    </div>

                    <div class="fieldcontain">
                        <label for="lastName">
                            <g:message code="lastName.label" default="Last Name" />
                            <span class="required-indicator">*</span>
                        </label>
                        <g:textField name="lastName" required=""  value="" />
                    </div>
                    <div class="fieldcontain">
                        <label for="mobileNo">
                            Mobile No
                            <span class="required-indicator">*</span>
                        </label>

                        <g:textField name="mobileNo" id="mobileNo" required=""  value="" />

                    </div>

                    <div class="fieldcontain">
                        <label for="phoneNo">
                            Phone No
                            <span class="required-indicator">*</span>
                        </label>
                        <g:textField name="phoneNo" required=""  value="" />

                    </div>

                    <div class="fieldcontain">
                        <label for="emailId">
                            Email Id
                            <span class="required-indicator">*</span>
                        </label>

                        <g:textField name="emailId" required=""  value="" />

                    </div>



                    <div class="fieldcontain">
                        <label for="description">
                            Description
                            <span class="required-indicator">*</span>
                        </label>
                        <g:textField name="description" required=""  value="" />

                    </div>

                    <div class="fieldcontain">
                        <label for="organization">
                            <g:message code="organization.label" default="Organization" />
                            <span class="required-indicator">*</span>
                        </label>
                        <g:textField name="organization" id="organization" required=""  readonly="readonly" onclick="organizationWindow.show()" value="" />
                        <g:hiddenField name="organizationId" id="organizationId"></g:hiddenField>
                        <a id="organizationSelect" href="#">
                            <img src="${resource(dir: 'images', file: 'organization_picker.png')}" alt="Organization Image" onclick="organizationWindow.show()" class="adjustImg" title="Click to select organization">
                        </a>
                    </div>



                    <div class="fieldcontain">
                        <label for="address">
                            House/Road
                            %{--database ar street address--}%
                            <span class="required-indicator">*</span>
                        </label>

                        <g:textArea name="address" id="address" required="" cols="10" rows="10"  value="" ></g:textArea>

                    </div>

                    <div class="fieldcontain">
                        <label for="country">
                            Country
                            <span class="required-indicator">*</span>
                        </label>

                        <g:textField name="country" id="country" required=""  value="" />

                    </div>

                    <div class="grid_4 alpha">&nbsp;&nbsp;</div>

                    <div class="grid_4 omega" style="padding-left: 194px;padding-top: 15px">



                        <a id="vendorForm" class="button icon approve" onclick="submitForm()">Save</a>
                        <a id="ff" class="button danger icon remove" onclick="document.vendorForm.reset()">Clear</a>

                    </div>

                </fieldset>
                <br class="clear"/>
            </g:form>
        </div>
    </content>
    </body>
</g:applyLayout>