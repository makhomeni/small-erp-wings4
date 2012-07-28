<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/20/12
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.jabait.hrm.payroll.Allowance; com.jabait.hrm.payroll.AllowanceType; com.jabait.security.Authority"%>
<%@ page import="com.jabait.security.UserGroup"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<g:applyLayout name="app">
<head>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'jscal2.js')}"></script>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'en.js')}"></script>

    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'border-radius.css')}"/>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'jscal2.css')}"/>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'steel.css')}"/>

    <title>${titleOfPage}</title>
    <style type="text/css">

    .miscInfoText{
        text-align: left;
    }
    .orgInfoText{
        text-align: left;
    }
    .adjustOrgImg{
        margin-bottom: -8px;
        margin-left: 2px;
    }
    .adjustCalendarImg {
        margin-bottom: -11px;
    }
    </style>

    <script type="text/javascript">
        //var's for organization
        var organizationDataStore;
        var organizationGrid;
        var checkBoxSelModModelOrganization;
        var organizationAssignmentWindow;

        //var's for department window
        var departmentDataStore;
        var departmentGrid;
        var checkBoxSelectionModelDepartment;
        var departmentAssignmentWindow;



        //org end


        //for designation
        var designationDataStore;
        var designationGrid;
        var checkBoxSelectionModelDesignation;
        var designationAssignmentWindow;

        //for section
        var sectionDataStore;
        var checkBoxSelectionModelSection;
        var sectionGrid;
        var sectionWindow;



        Ext.onReady(function(){


            //for organization
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
            })


            //create the grid
            checkBoxSelModModelOrganization = new Ext.grid.CheckboxSelectionModel();
            organizationGrid = new Ext.grid.EditorGridPanel({
                id: 'organizationGrid',
                store : productDataStore,
                clicksToEdit: 2,
                selModel : checkBoxSelModModelOrganization,
                height: 250,

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
                        var departmentStore = departmentGrid.getStore()
                        Ext.each(selectedOrganization, function(sel) {
                            $("#organization").val(sel.get('organizationName'))
                            $("#organizationId").val(sel.get('id'))
                            //to show department selection option

                            departmentStore.setBaseParam("orgId",sel.get('id'))
                            organizationAssignmentWindow.hide();
                        });


                        departmentStore.load({
                            params : {start: 0, limit: 10}
                        })
                    }
                }
            })
            productDataStore.load({params: {start: 0, limit: 10}});

            organizationAssignmentWindow = new Ext.Window({
                id: 'organizationAssignmentWindow',
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
                            organizationAssignmentWindow.hide();
                        }
                    }
                ]
            });


            //////////// organization end ////////////////////////////////


            //////for department///////////

            departmentDataStore = new Ext.data.Store({
                id: "departmentDataStore",
                url: '${createLink(controller: 'employee',action: 'departmentJsonData')}',
                reader: new Ext.data.JsonReader({
                    root: 'departments',
                    totalProperty: 'totalCount',
                    id: 'id'
                },[
                    {name: 'id', type: 'int', mapping: 'id'},
                    {name: 'departmentName', type: 'string', mapping: 'departmentName'}
                ]),
                autoLoad : true
            })


            //create the grid
            checkBoxSelectionModelDepartment = new Ext.grid.CheckboxSelectionModel();
            departmentGrid = new Ext.grid.EditorGridPanel({
                id: 'departmentGrid',
                store : departmentDataStore,
                clicksToEdit: 2,
                selModel : checkBoxSelectionModelDepartment,
                height: 250,
                //sm: new Ext.grid.CheckboxSelectionModel(),
                columns: [

                    {
                        dataIndex: 'departmentName',
                        header: 'Department Name',
                        sortable: true,
                        width: 250,
                        editable: true,
                        editor: new Ext.form.TextField()
                    }
                ],
                stripeRows : true,
                bbar: new Ext.PagingToolbar({
                    store : departmentDataStore,
                    pageSize : 10,
                    displayInfo : true,
                    displaymsg : 'Displaying {0} - {1} of {2}',
                    emptyMsg : "No records found"
                }),
                listeners: {
                    "cellclick" : function(grid, rowIndex, columnIndex, e){
                        var selectedDepartment= departmentGrid.getSelectionModel().getSelections();
                        var sectionStore = sectionGrid.getStore()
                        Ext.each(selectedDepartment, function(sel) {

                            $("#department").val(sel.get('departmentName'));
                            $("#departmentId").val(sel.get('id'));

                            sectionStore.setBaseParam("deptId",sel.get('id'))

                            departmentAssignmentWindow.hide();
                        });

                    }
                }
            })
            departmentDataStore.load({params: {start: 0, limit: 10}});

            departmentAssignmentWindow = new Ext.Window({
                id: 'departmentAssignmentWindow',
                title: 'Select Department for Employee',
                closable: false,
                width: 400,
                height: 400,
                plain:true,
                modal:true,
                layout: 'fit',
                resizable: false,
                items: departmentGrid,
                buttons:[
                    {
                        text: 'Cancel',
                        handler: function(){
                            departmentAssignmentWindow.hide();
                        }
                    }
                ]
            });

            //////////// department end /////////////////////////////////


            /////////////////// section ////////////
            sectionDataStore = new Ext.data.Store({
                id: "sectionDataStore",
                url: '${createLink(controller: 'employee',action: 'sectionJsonData')}',
                reader: new Ext.data.JsonReader({
                    root: 'sections',
                    totalProperty: 'totalCount',
                    id: 'id'
                },[
                    {name: 'id', type: 'int', mapping: 'id'},
                    {name: 'departmentName', type: 'string', mapping: 'departmentName'}
                ]),
                autoLoad : true
            });


            //create the grid
            checkBoxSelectionModelSection = new Ext.grid.CheckboxSelectionModel();
            sectionGrid = new Ext.grid.EditorGridPanel({
                id: 'sectionGrid',
                store : sectionDataStore,
                clicksToEdit: 2,
                selModel : checkBoxSelectionModelSection,
                height: 250,

                columns: [

                    {
                        dataIndex: 'departmentName',
                        header: 'Section Name',
                        sortable: true,
                        width: 370,
                        editable: true,
                        editor: new Ext.form.TextField()
                    }
                ],
                stripeRows:true,
                bbar: new Ext.PagingToolbar({
                    store : sectionDataStore,
                    pageSize : 10,
                    displayInfo : true,
                    displaymsg : 'Displaying {0} - {1} of {2}',
                    emptyMsg : "No records found"
                }),
                listeners: {
                    "cellclick" : function(grid, rowIndex, columnIndex, e){
                        var selectedSection= sectionGrid.getSelectionModel().getSelections();
                        Ext.each(selectedSection, function(sel) {

                            $("#section").val(sel.get('departmentName'))
                            $("#sectionId").val(sel.get('id'))

                            sectionWindow.hide();

                        });


                    }
                }
            })
            sectionDataStore.load({params: {start: 0, limit: 10}});


            sectionWindow = new Ext.Window({
                id: 'sectionWindow',
                title: 'Select Section for Employee',
                closable: false,
                width: 400,
                height: 400,
                plain:true,
                modal:true,
                layout: 'fit',
                resizable: false,
                items: sectionGrid,
                buttons:[
                    {
                        text: 'Cancel',
                        handler: function(){
                            sectionWindow.hide();
                        }
                    }
                ]
            });

            ///////////////////////// Section end ///////////////////////////////////




            /////////////////for designation//////////////////////

            designationDataStore = new Ext.data.Store({
                id: "designationDataStore",
                url: '${createLink(controller: 'employee',action: 'designationJsonData')}',
                reader: new Ext.data.JsonReader({
                    root: 'designations',
                    totalProperty: 'totalCount',
                    id: 'id'
                },[
                    {name: 'id', type: 'int', mapping: 'id'},
                    {name: 'jobTitleName', type: 'string', mapping: 'jobTitleName'},
                    {name: 'jobTitleDescription', type: 'string', mapping: 'jobTitleDescription'}
                ]),
                autoLoad : true
            })


            %{--grid for designation--}%

            checkBoxSelectionModelDesignation= new Ext.grid.CheckboxSelectionModel();
            designationGrid = new Ext.grid.EditorGridPanel({
                id: 'designationGrid',
                store : designationDataStore,
                clicksToEdit: 2,
                selModel : checkBoxSelectionModelDesignation,
                height: 250,
                //sm: new Ext.grid.CheckboxSelectionModel(),
                columns: [

                    {
                        dataIndex: 'jobTitleName',
                        header: 'Title',
                        sortable: true,
                        width: 370,
                        editable: true,
                        editor: new Ext.form.TextField()
                    }
                ],
                stripeRows:true,
                bbar: new Ext.PagingToolbar({
                    store : designationDataStore,
                    pageSize : 10,
                    displayInfo : true,
                    displaymsg : 'Displaying {0} - {1} of {2}',
                    emptyMsg : "No records found"
                }),
                listeners: {
                    "cellclick" : function(grid, rowIndex, columnIndex, e){
                        var selectedDesignation= designationGrid.getSelectionModel().getSelections();
                        Ext.each(selectedDesignation, function(sel) {

                            $("#designation").val(sel.get('jobTitleName'))
                            $("#designationId").val(sel.get('id'))
                            designationAssignmentWindow.hide();
                        });


                    }
                }
            })
            designationDataStore.load({params: {start: 0, limit: 10}});


            designationAssignmentWindow = new Ext.Window({
                id: 'designationAssignmentWindow',
                title: 'Select Designation for Employee',
                closable: false,
                width: 400,
                height: 400,
                plain:true,
                modal:true,
                layout: 'fit',
                resizable: false,
                items: designationGrid,
                buttons:[
                   {
                        text: 'Cancel',
                        handler: function(){
                            designationAssignmentWindow.hide();
                        }
                    }
                ]
            });


        })
    </script>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'basic.css')}" media='screen'/>

</head>

<body>
<content tag="titleTag">

</content>
<content tag="rightTag">

<div class="bread_crumbs_ui_div" style="width: 819px">
    <ul id="crumbs_ui_custom">
        <li><g:link controller="application" action="index">Dashboard</g:link></li>
        <li><g:link controller="employee" action="employeeList">Employee List</g:link></li>
        <li>Employee Details</li>
    </ul>
</div>

<br class="clear"/>
<div class="container_16">
<div class="grid_7 alpha">


    <div class="widget">

        <div class="header">
            <span><span class="ico gray genInfo"></span>General Information</span>
            <span style="margin-left: 174px;"><a id="editGeneralInfo"><img id="editGeneralInfoImg" src="${resource(dir: 'images',file: 'edit.png')}" alt="Edit" /></a></span>
            %{--<span  style="margin-left: 166px;"><a id="editGeneralInfo">Edit</a></span>--}%
        </div>

        <g:hiddenField name="profileId" id="profileId" value="${employeeProfile.id}"></g:hiddenField>

        <div class="content tableName" >

            <table class="display data_table" >
                <tbody>

                <tr>
                    <td >
                        <div class="msg">

                            <div class="msg_topic"><strong>Username:</strong></div>
                        </div>
                    </td>
                    <td >
                        <div class="msg_date" > ${fieldValue(bean: employeeProfile, field: "owner.userCode")}</div>

                    </td>
                </tr>


                <tr>
                    <td >
                        <div class="msg">

                            <div class="msg_topic"><strong>First Name:</strong></div>
                        </div>
                    </td>
                    <td >
                        <div class="msg_date detailsInfoDiv" id="firstNameDiv" > ${fieldValue(bean: employeeProfile, field: "name.firstName")}</div>
                        <div class="msg_date detailsInfoText" >
                            <g:textField name="firstName" id="firstName" value="${fieldValue(bean: employeeProfile, field: "name.firstName")}" style="display: inline;"></g:textField>
                        </div>
                    </td>
                </tr>

                <tr>
                    <td >
                        <div class="msg">

                            <div class="msg_topic"><strong>Middle Name:</strong></div>
                        </div>
                    </td>
                    <td >
                        <div class="msg_date detailsInfoDiv" id="middleNameDiv" >${fieldValue(bean: employeeProfile, field: "name.middleName")}</div>
                        <div class="msg_date detailsInfoText" >
                            <g:textField name="middleName" id="middleName" value="${fieldValue(bean: employeeProfile, field: "name.middleName")}"></g:textField>
                        </div>

                    </td>
                </tr>

                <tr>
                    <td >
                        <div class="msg">

                            <div class="msg_topic"><strong>Surname:</strong></div>
                        </div>
                    </td>
                    <td >
                        <div class="msg_date detailsInfoDiv" id="surnameDiv" >${fieldValue(bean: employeeProfile, field: "name.surname")}</div>
                        <div class="msg_date detailsInfoText" >
                            <g:textField name="surname" id="surname" value="${fieldValue(bean: employeeProfile, field: "name.surname")}"></g:textField>
                        </div>

                    </td>
                </tr>


                %{--button for save --}%

                <tr class="showButton">
                    <td >

                    </td>
                    <td >
                        <div style="margin-left: 38px;">
                            <a id="nameSave" class="button icon approve" onclick="">Save</a>
                            <a id="nameReset" class="button danger icon remove" onclick="javascript: showGeneralInfo()">Cancel</a>
                        </div>

                    </td>
                </tr>



                </tbody>

            </table>

        </div><!-- End content -->
    </div><!--end widget -->
</div><!--end left column -->

%{--left column--}%

<div class="grid_7 omega">

    <div class="widget">

        <div class="header">
            <span><span class="ico gray organization"></span>Organization Information</span>
            <span style="margin-left: 140px;"><a id="editOrganizationInfo"><img id="editOrganizationInfoImg" src="${resource(dir: 'images',file: 'edit.png')}" alt="Edit" /></a></span>
        </div>

        <div class="content tableName">

            <table class="display data_table" >
                <tbody>

                <tr>
                    <td >
                        <div class="msg">

                            <div class="msg_topic"><strong>Organization Name:</strong></div>
                        </div>
                    </td>
                    <td >
                        <div class="msg_date orgInfoDiv" id="organizationDiv">${fieldValue(bean: employeeProfile, field: "organization.organizationName")}</div>
                        <div class="msg_date orgInfoText" >
                            <g:textField name="organization" id="organization" readonly="readonly" value="${fieldValue(bean: employeeProfile, field: "organization.organizationName")}"></g:textField>
                            <g:hiddenField name="organizationId" id="organizationId"></g:hiddenField>

                                <img src="${resource(dir: 'images', file: 'organization_picker.png')}" alt="Organization Image" onclick="organizationAssignmentWindow.show()" class="adjustOrgImg" title="Click to select organization" />

                        </div>
                    </td>
                </tr>



                <tr>
                    <td >
                        <div class="msg">

                            <div class="msg_topic"><strong>Department Name:</strong></div>
                        </div>
                    </td>
                    <td >
                        <div class="msg_date orgInfoDiv" id="departmentDiv" >${fieldValue(bean: employeeProfile, field: "department.departmentName")}</div>
                        <div class="msg_date orgInfoText" >
                            <g:textField name="department" id="department" readonly="readonly" value="${fieldValue(bean: employeeProfile, field: "department.departmentName")}"></g:textField>
                            <g:hiddenField name="departmentId" id="departmentId"></g:hiddenField>

                                <img src="${resource(dir: 'images', file: 'department.png')}" alt="Department Image" class="adjustOrgImg" onclick="departmentAssignmentWindow.show()" title="Click to select department">

                        </div>
                    </td>
                </tr>


                <tr>
                    <td >
                        <div class="msg">

                            <div class="msg_topic"><strong>Section Name:</strong></div>
                        </div>
                    </td>
                    <td >
                        <div class="msg_date orgInfoDiv" id="sectionDiv">${fieldValue(bean: employeeProfile, field: "section.departmentName")}</div>
                        <div class="msg_date orgInfoText" >
                            <g:textField name="section" id="section" readonly="readonly" value="${fieldValue(bean: employeeProfile, field: "section.departmentName")}"></g:textField>
                            <g:hiddenField name="sectionId" id="sectionId"></g:hiddenField>
                        </div>
                    </td>
                </tr>

                <tr>
                    <td >
                        <div class="msg">

                            <div class="msg_topic"><strong>Designation:</strong></div>
                        </div>
                    </td>
                    <td >
                        <div class="msg_date orgInfoDiv" id="designationDiv">${fieldValue(bean: employeeProfile, field: "jobTitle.jobTitleName")}</div>
                        <div class="msg_date orgInfoText" >
                            <g:textField name="designation" id="designation" readonly="readonly" value="${fieldValue(bean: employeeProfile, field: "jobTitle.jobTitleName")}"></g:textField>
                            <g:hiddenField name="designationId" id="designationId"></g:hiddenField>

                                <img src="${resource(dir: 'images/assets/icons', file: 'jobTitle.png')}" alt="Designation" class="adjustOrgImg" onclick="designationAssignmentWindow.show()" title="Click to select designation">

                        </div>

                    </td>
                </tr>


                <tr>
                    <td >
                        <div class="msg">

                            <div class="msg_topic"><strong>Salary:</strong></div>
                        </div>
                    </td>
                    <td >
                        <div class="msg_date orgInfoDiv" id="salaryDiv" >${fieldValue(bean: employeeProfile, field: "salary")}</div>
                        <div class="msg_date orgInfoText" >
                            <g:textField name="salary" id="salary"  value="${fieldValue(bean: employeeProfile, field: "salary")}"></g:textField>
                        </div>
                    </td>
                </tr>

                <tr>
                    <td >
                        <div class="msg">

                            <div class="msg_topic"><strong>Region:</strong></div>
                        </div>
                    </td>
                    <td >
                        <div class="msg_date orgInfoDiv" id="regionDiv" >${fieldValue(bean: employeeProfile, field: "region")}</div>
                        <div class="msg_date orgInfoText" >
                            <g:textField name="region" id="region"  value="${fieldValue(bean: employeeProfile, field: "region")}"></g:textField>
                        </div>
                    </td>
                </tr>

                %{--button for save --}%

                <tr class="showButtonOrg">
                    <td >

                    </td>
                    <td >
                        <div style="margin-left: 53px;">
                            <a id="organizationSave" class="button icon approve" onclick="">Save</a>
                            <a id="organizationReset" class="button danger icon remove" onclick="">Clear</a>
                        </div>

                    </td>
                </tr>

                </tbody>

            </table>

        </div><!-- End content -->
    </div><!--end widget -->
</div><!--end left column -->

<br class="clear">

%{--first column 2nd row--}%

<div class="grid_7 alpha">

    <div class="widget">

        <div class="header">
            <span><span class="ico gray miscellaneous"></span>Miscellaneous</span>
            <span style="margin-left: 208px;"><a id="editMiscInfo"><img id="editMiscInfoImg" src="${resource(dir: 'images',file: 'edit.png')}" alt="Edit" /></a></span>
        </div>

        <div class="content tableName">

            <table class="display data_table" >
                <tbody>
                <tr>
                    <td >
                        <div class="msg">

                            <div class="msg_topic"><strong>Gender:</strong></div>
                        </div>
                    </td>
                    <td >
                        <div class="msg_date miscInfoDiv" id="employeeGenderDiv" >${fieldValue(bean: employeeProfile, field: "employeeGender")}</div>

                        <div class="msg_date miscInfoText" >
                            <g:textField name="employeeGender" id="employeeGender" value="${fieldValue(bean: employeeProfile, field: "employeeGender")}"></g:textField>
                        </div>
                    </td>
                </tr>


                <tr>
                    <td >
                        <div class="msg">

                            <div class="msg_topic"><strong>Marital Status:</strong></div>
                        </div>
                    </td>
                    <td >
                        <div class="msg_date miscInfoDiv" id="employeeMaritalStatusDiv" >${fieldValue(bean: employeeProfile, field: "employeeMaritalStatus")}</div>

                        <div class="msg_date miscInfoText" >
                            <g:textField name="employeeMaritalStatus" id="employeeMaritalStatus" value="${fieldValue(bean: employeeProfile, field: "employeeMaritalStatus")}"></g:textField>
                        </div>


                    </td>
                </tr>

                <tr>
                    <td >
                        <div class="msg">

                            <div class="msg_topic"><strong>Blood Group:</strong></div>
                        </div>
                    </td>
                    <td >
                        <div class="msg_date miscInfoDiv" id="bloodGroupDiv" >${fieldValue(bean: employeeProfile, field: "bloodGroup")}</div>

                        <div class="msg_date miscInfoText" >
                            <g:textField name="bloodGroup" id="bloodGroup" value="${fieldValue(bean: employeeProfile, field: "bloodGroup")}"></g:textField>
                        </div>


                    </td>
                </tr>

                <tr>
                    <td >
                        <div class="msg">

                            <div class="msg_topic"><strong>Date Of Birth:</strong></div>
                        </div>
                    </td>
                    <td >
                        <div class="msg_date miscInfoDiv" id="employeeBirthDateDiv" >${fieldValue(bean: employeeProfile, field: "employeeBirthDate")}</div>

                        <div class="msg_date miscInfoText" >
                            <g:textField name="employeeBirthDate" id="employeeBirthDate" readonly="readonly" value="${fieldValue(bean: employeeProfile, field: "employeeBirthDate")}"></g:textField>
                            <img src="${resource(dir: 'images/calendar', file: 'calendar_add.png')}" alt="calender" id="showCalendarBirthDate" class="adjustCalendarImg"  title="Click to select date">
                        </div>


                        <script type="text/javascript">
                            //<![CDATA[
                            Calendar.setup({
                                inputField : "employeeBirthDate",
                                trigger    : "showCalendarBirthDate",
                                onSelect   : function() { this.hide() },
                                showTime   : 12,
                                dateFormat : "%Y-%m-%d %H:%m:%S"//this is a timestamp format
                            });
                            //]]>
                        </script>
                    </td>
                </tr>

                <tr>
                    <td >
                        <div class="msg">

                            <div class="msg_topic"><strong>Date Of Hire:</strong></div>
                        </div>
                    </td>
                    <td >
                        <div class="msg_date miscInfoDiv" id="hireDateDiv" >${fieldValue(bean: employeeProfile, field: "hireDate")}</div>

                        <div class="msg_date miscInfoText" >
                            <g:textField name="hireDate" id="hireDate" readonly="readonly" value="${fieldValue(bean: employeeProfile, field: "hireDate")}"></g:textField>
                            <img src="${resource(dir: 'images/calendar', file: 'calendar_add.png')}" alt="calender" id="showCalendarhireDate" class="adjustCalendarImg"  title="Click to select date">
                        </div>

                        <script type="text/javascript">
                            //<![CDATA[
                            Calendar.setup({
                                inputField : "hireDate",
                                trigger    : "showCalendarhireDate",
                                onSelect   : function() { this.hide() },
                                showTime   : 12,
                                dateFormat : "%Y-%m-%d %H:%m:%S"//this is a timestamp format
                            });
                            //]]>
                        </script>

                    </td>
                </tr>

                <tr>
                    <td >
                        <div class="msg">

                            <div class="msg_topic"><strong>Date Of Join:</strong></div>
                        </div>
                    </td>
                    <td >
                        <div class="msg_date miscInfoDiv" id="joiningDateDiv" >${fieldValue(bean: employeeProfile, field: "joiningDate")}</div>

                        <div class="msg_date miscInfoText" >
                            <g:textField name="joiningDate" id="joiningDate" readonly="readonly" value="${fieldValue(bean: employeeProfile, field: "joiningDate")}"></g:textField>
                            <img src="${resource(dir: 'images/calendar', file: 'calendar_add.png')}" alt="calender" id="showCalendarJoiningDate" class="adjustCalendarImg"  title="Click to select date">
                        </div>

                        <script type="text/javascript">
                            //<![CDATA[
                            Calendar.setup({
                                inputField : "joiningDate",
                                trigger    : "showCalendarJoiningDate",
                                onSelect   : function() { this.hide() },
                                showTime   : 12,
                                dateFormat : "%Y-%m-%d %H:%m:%S"//this is a timestamp format
                            });
                            //]]>
                        </script>

                    </td>
                </tr>


                %{--button for save --}%

                <tr class="showButtonMisc">
                    <td >

                    </td>
                    <td >
                        <div style="margin-left: 53px;">
                            <a id="miscSave" class="button icon approve" onclick="">Save</a>
                            <a id="miscReset" class="button danger icon remove" onclick="">Clear</a>
                        </div>

                    </td>
                </tr>



                </tbody>

            </table>

        </div><!-- End content -->
    </div><!--end widget -->
</div><!--end left column -->



%{--2nd column 2nd row--}%
<div class="grid_7 omega" >

    <!-- full width -->
    <div class="widget">
        <div class="header">
            <span><span class="ico gray contact"></span>Contact Info</span>
            <span style="margin-left: 227px;"><a id="editContactInfo"><img id="editContactInfoImg" src="${resource(dir: 'images',file: 'edit.png')}" alt="Edit" /></a></span>
        </div>

        <div class="content tableName">
            <table class="display data_table" >
                <tbody>

                <tr>
                    <td >
                        <div class="msg">

                            <div class="msg_topic"><strong>Home Telephone:</strong></div>
                        </div>
                    </td>
                    <td >
                        <div class="msg_date contactInfoDiv" id="homePhoneDiv">${fieldValue(bean: employeeProfile, field: "homePhone.number")}</div>
                        <div class="msg_date contactInfoText" >
                            <g:textField name="homePhone" id="homePhone" value="${fieldValue(bean: employeeProfile, field: "homePhone.number")}"></g:textField>

                        </div>
                        <g:hiddenField name="homePhoneId" id="homePhoneId" value="${fieldValue(bean: employeeProfile, field: "homePhone.id")}"></g:hiddenField>
                    </td>
                </tr>

                <tr>
                    <td >
                        <div class="msg">

                            <div class="msg_topic"><strong>Mobile:</strong></div>
                        </div>
                    </td>
                    <td >
                        <div class="msg_date contactInfoDiv" id="mobileDiv">${fieldValue(bean: employeeProfile, field: "mobile.number")}</div>
                        <div class="msg_date contactInfoText" >
                            <g:textField name="mobile" id="mobile"  value="${fieldValue(bean: employeeProfile, field: "mobile.number")}"></g:textField>


                        </div>
                        <g:hiddenField name="mobileId" id="mobileId" value="${fieldValue(bean: employeeProfile, field: "mobile.id")}"></g:hiddenField>
                    </td>
                </tr>

                <tr>
                    <td >
                        <div class="msg">

                            <div class="msg_topic"><strong>Work Telephone:</strong></div>
                        </div>
                    </td>
                    <td >
                        <div class="msg_date contactInfoDiv" id="workPhoneDiv">${fieldValue(bean: employeeProfile, field: "workPhone.number")}</div>
                        <div class="msg_date contactInfoText" >
                            <g:textField name="workPhone" id="workPhone"  value="${fieldValue(bean: employeeProfile, field: "workPhone.number")}"></g:textField>

                        </div>
                        <g:hiddenField name="workPhoneId" id="workPhoneId" value="${fieldValue(bean: employeeProfile, field: "workPhone.id")}"></g:hiddenField>
                    </td>
                </tr>

                <tr>
                    <td >
                        <div class="msg">

                            <div class="msg_topic"><strong>Work Email:</strong></div>
                        </div>
                    </td>
                    <td >
                        <div class="msg_date contactInfoDiv" id="workEmailDiv">${fieldValue(bean: employeeProfile, field: "workEmail.address")}</div>

                        <div class="msg_date contactInfoText" >
                            <g:textField name="workEmail" id="workEmail" value="${fieldValue(bean: employeeProfile, field: "workEmail.address")}"></g:textField>

                        </div>
                        <g:hiddenField name="workEmailId" id="workEmailId" value="${fieldValue(bean: employeeProfile, field: "workEmail.id")}"></g:hiddenField>
                    </td>
                </tr>

                <tr>
                    <td >
                        <div class="msg">

                            <div class="msg_topic"><strong>General Email:</strong></div>
                        </div>
                    </td>
                    <td >
                        <div class="msg_date contactInfoDiv" id="generalEmailDiv">${fieldValue(bean: employeeProfile, field: "generalEmail.address")}</div>
                        <div class="msg_date contactInfoText" >
                            <g:textField name="generalEmail" id="generalEmail" value="${fieldValue(bean: employeeProfile, field: "generalEmail.address")}"></g:textField>
                            <g:hiddenField name="generalEmailId" id="generalEmailId" value="${fieldValue(bean: employeeProfile, field: "generalEmail.id")}"></g:hiddenField>
                        </div>

                    </td>
                </tr>

                %{--button for save --}%
                <tr class="showButtonContact">
                    <td >

                    </td>
                    <td >
                        <div style="margin-left: 53px;">
                            <a id="contactSave" class="button icon approve" onclick="">Save</a>
                            <a id="contactReset" class="button danger icon remove" onclick="">Clear</a>
                        </div>

                    </td>
                </tr>




                </tbody>
            </table>

        </div><!-- End content -->

    </div><!-- End full width -->





</div><!-- End column_right -->

<br class="clear" />

<div class="grid_7 alpha">

    <div class="widget">

        <div class="header">
            <span><span class="ico gray allowance"></span>&nbsp;&nbsp;Allowance</span>
            <span style="margin-left: 227px;"><a id="editPaymentInfo"><img id="editPaymentInfoImg" src="${resource(dir: 'images',file: 'edit.png')}" alt="Edit" /></a></span>
        </div>

        <div class="content tableName">

            <table class="display data_table" >
                <tbody>

                %{--<g:set var="allowanceTypes" value="${AllowanceType.list()}"></g:set>--}%

                <g:each in="${AllowanceType.list()}" var="allowanceType">

                    <g:set var="textField" value="${true}"></g:set>

                    <tr>
                        <td >
                            <div class="msg">

                                <div class="msg_topic"><strong>${allowanceType.allowanceName}</strong></div>
                            </div>
                        </td>

                        <td >


                            <g:each in="${employeeProfile.owner.allowances}" var="allowance">



                                <g:if test="${allowance.allowanceType.allowanceName == allowanceType.allowanceName}">

                                    <div class="msg_date paymentInfoDiv" id="allowanceTypeDiv" >
                                        ${allowance.allowanceAmount}

                                    </div>
                                    <div class="msg_date paymentInfoText" >

                                            <g:textField name="${allowanceType.id}" id="${allowanceType.id}" value="${allowance.allowanceAmount}"></g:textField>
                                            <g:set var="textField" value="${false}"></g:set>

                                    </div>


                                </g:if>

                            </g:each>
                            <g:if test="${textField == true}">
                                <div class="msg_date paymentInfoText" >

                                    <g:textField name="${allowanceType.id}" id="${allowanceType.id}" value=""></g:textField>
                                </div>
                            </g:if>
                        </td>
                    </tr>

                </g:each>

                <tr class="showButtonPayment">
                    <td >

                    </td>
                    <td >
                        <div style="margin-left: 53px;">
                            <a id="paymentSave" class="button icon approve" onclick="">Save</a>
                            <a id="paymentReset" class="button danger icon remove" onclick="javascript: showPaymentInfo()">Clear</a>
                        </div>

                    </td>
                </tr>

                </tbody>

            </table>

        </div><!-- End content -->
    </div><!--end widget -->
</div><!--end left column -->



</div>

<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.blockUI.js')}"></script>
<script type='text/javascript' src="${resource(dir: 'js', file: 'jquery.simplemodal.js')}"></script>
<script type="text/javascript">
    $(document).ready(function(){

        %{--at first hide all textfield and button--}%
        var profileId = $("#profileId").val();


//        hide all text
        $(".detailsInfoText").hide();
        $(".orgInfoText").hide();
        $(".miscInfoText").hide();
        $(".contactInfoText").hide();
        $(".paymentInfoText").hide();

        %{--hide all button--}%
        $(".showButton").hide();
        $(".showButtonMisc").hide();
        $(".showButtonOrg").hide();
        $(".showButtonContact").hide();
        $(".showButtonPayment").hide();


        %{--edit and show toggle operation for --}%

        $("#editGeneralInfo").bind("click", function(){

            var imageSrcArrayMisc = $("#editGeneralInfoImg").attr("src").split('/');
            var imageNameMisc = imageSrcArrayMisc[imageSrcArrayMisc.length-1];
            if(imageNameMisc == "edit.png"){
                editGeneralInfo();
            }else{
                showGeneralInfo();
            }
        })




        %{--For organization click handle--}%
        $("#editOrganizationInfo").bind("click", function(){
            var imageSrcArrayOrg= $("#editOrganizationInfoImg").attr("src").split('/');
            var imageNameOrg = imageSrcArrayOrg[imageSrcArrayOrg.length-1];
            if(imageNameOrg == "edit.png"){
                editOrganization();
            }else{
                showOrgInfo();
            }
        })


        function showOrgInfo(){
            $(".orgInfoDiv").show();
            $(".orgInfoText").hide();
            $("#editOrganizationInfoImg").attr("src", "${resource(dir:'images', file: 'edit.png')}");
            $(".showButtonOrg").hide();
        }
        function editOrganization(){
            $(".orgInfoText").show();
            $(".orgInfoDiv").hide();
            $(".showButtonOrg").show();
            $("#editOrganizationInfoImg").attr("src", "${resource(dir:'images', file: 'show.png')}");
        }


        %{--For miscalleneous info--}%
        $("#editMiscInfo").bind("click", function(){
            var imageSrcArray = $("#editMiscInfoImg").attr("src").split('/');
            var imageName = imageSrcArray[imageSrcArray.length-1];
            if(imageName == "edit.png"){
                editMiscInfo();
            }else{
                showMiscInfo();
            }

        })

        function showMiscInfo(){
            $(".miscInfoDiv").show();
            $(".miscInfoText").hide();
//                $("#editMiscInfo").html("Edit");
            $("#editMiscInfoImg").attr("src", "${resource(dir:'images', file: 'edit.png')}");
            $(".showButtonMisc").hide();
        }
        function editMiscInfo(){
            $(".miscInfoText").show();
            $(".miscInfoDiv").hide();
            $(".showButtonMisc").show();
            $("#editMiscInfoImg").attr("src", "${resource(dir:'images', file: 'show.png')}");
        }

//            for contact

        $("#editContactInfo").bind("click", function(){

            var imageSrcArrayContact = $("#editContactInfoImg").attr("src").split('/');
            var imageNameContact = imageSrcArrayContact[imageSrcArrayContact.length-1];
            if(imageNameContact == "edit.png"){
                showContactInfoEdit();
            }else{
                showContactInfo();
            }

        })

        function showContactInfo(){
            $(".contactInfoDiv").show();
            $(".contactInfoText").hide();
            $("#editContactInfoImg").attr("src", "${resource(dir:'images', file: 'edit.png')}");
            $(".showButtonContact").hide();
        }
        function showContactInfoEdit(){
            $(".contactInfoDiv").hide();
            $(".contactInfoText").show();
            $("#editContactInfoImg").attr("src", "${resource(dir:'images', file: 'show.png')}");
            $(".showButtonContact").show();
        }



        $("#editPaymentInfo").bind("click", function(){

            var imageSrcArrayContact = $("#editPaymentInfoImg").attr("src").split('/');
            var imageNamePayment = imageSrcArrayContact[imageSrcArrayContact.length-1];
            if(imageNamePayment == "edit.png"){
                showPaymentInfoEdit();
            }else{
                showPaymentInfo();
            }

        })

        function showPaymentInfo(){
            $(".paymentInfoDiv").show();
            $(".paymentInfoText").hide();
            $("#editPaymentInfoImg").attr("src", "${resource(dir:'images', file: 'edit.png')}");
            $(".showButtonPayment").hide();
        }
        function showPaymentInfoEdit(){
            $(".paymentInfoDiv").hide();
            $(".paymentInfoText").show();
            $("#editPaymentInfoImg").attr("src", "${resource(dir:'images', file: 'show.png')}");
            $(".showButtonPayment").show();
        }




        //modal
        $('#basic-modal').click(function (e) {
            $('#basic-modal-content').modal();
            return false;
        });


        $('#basic-modal-department').click(function (e) {
            $('#basic-modal-content-department').modal();
            return false;
        });

        $('#basic-modal-designation').click(function (e) {
            $('#basic-modal-content-designation').modal();
            return false;
        });
        ////modal end

        %{--ajax call for save individual save --}%


        $("#nameSave").bind("click", function(){
            var firstName = $("#firstName").val();
            var middleName = $("#middleName").val();
            var surname = $("#surname").val();

//                alert(firstName +  "  "+ middleName +" "+ surname)
            $.blockUI({
                message: '<em>Saving Name... Please wait for a while...</em>',
                css: {
                    border: 'none',
                    padding: '15px',
                    backgroundColor: '#000',
                    '-webkit-border-radius': '10px',
                    '-moz-border-radius': '10px',
                    opacity: .5,
                    color: '#fff'
                }
            });
            $.ajax({
                type:'POST',
                url:'${createLink(controller: 'employee', action: 'updateEmployeeName')}',
                data:'profileId=' +profileId+ '&firstName=' +firstName+ '&middleName='+middleName+'&surname='+surname,
                success: function(response){
                    $.unblockUI();
                    if(response.result){
                        Ext.MessageBox.alert("Success", "Name saved successfully");
                        showGeneralInfo();

                        $("#firstNameDiv").html(response.result.firstName);
                        $("#firstName").html(response.result.firstName);
                        $("#middleNameDiv").html(response.result.middleName);
                        $("#middleName").html(response.result.middleName);
                        $("#surnameDiv").html(response.result.surname);
                        $("#surname").html(response.result.surname);

                    }else{
                        Ext.MessageBox.alert("Failed", "Name save failed");
                    }
                }

            })
        })
        $("#organizationSave").bind("click", function(){
            var designationId = $("#designationId").val();
            var departmentId = $("#departmentId").val();
            var organizationId = $("#organizationId").val();
            var region = $("#region").val();
            var salary = $("#salary").val();
            $.blockUI({
                message: '<em>Saving Miscellaneous... Please wait for a while...</em>',
                css: {
                    border: 'none',
                    padding: '15px',
                    backgroundColor: '#000',
                    '-webkit-border-radius': '10px',
                    '-moz-border-radius': '10px',
                    opacity: .5,
                    color: '#fff'
                }
            });

            $.ajax({
                type:'POST',
                url:'${createLink(controller: 'employee', action: 'updateOrgInfo')}',
                data: 'profileId=' +profileId+ '&designationId=' +designationId+ '&region='+region+ '&departmentId=' +departmentId
                        + '&organizationId=' +organizationId+ '&salary=' +salary,
                success: function(response){
                    $.unblockUI();
                    if(response.result){
                        Ext.MessageBox.alert("Success", "Organization Info saved successfully");
                        showOrgInfo()
                        $("#regionDiv").html(response.result.region);
                        $("#region").html(response.result.region);
                        $("#salaryDiv").html(response.result.salary);
                        $("#salary").html(response.result.salary);
                        $("#designationDiv").html(response.jobTitle.jobTitleName);
                        $("#designation").html(response.jobTitle.jobTitleName);
                        $("#departmentDiv").html(response.department.departmentName);
                        $("#department").html(response.department.departmentName);
                        $("#organizationDiv").html(response.organization.organizationName);
                        $("#organization").html(response.organization.organizationName);


                    }else{
                        Ext.MessageBox.alert("Failed", "Organization Info save failed");
                    }
                }

            })
        })
        %{--end contact save--}%
        %{--start miscellaneous contact save--}%
        $("#miscSave").bind("click", function(){
            var employeeGender = $("#employeeGender").val();
            var employeeMaritalStatus = $("#employeeMaritalStatus").val();
            var bloodGroup = $("#bloodGroup").val();
            var employeeBirthDate = $("#employeeBirthDate").val();
            var hireDate = $("#hireDate").val();
            var joiningDate = $("#joiningDate").val();

            $.blockUI({
                message: '<em>Saving Miscellaneous... Please wait for a while...</em>',
                css: {
                    border: 'none',
                    padding: '15px',
                    backgroundColor: '#000',
                    '-webkit-border-radius': '10px',
                    '-moz-border-radius': '10px',
                    opacity: .5,
                    color: '#fff'
                }
            });

            $.ajax({
                type:'POST',
                url:'${createLink(controller: 'employee', action: 'updateMiscellaneous')}',
                data: 'profileId=' +profileId+ '&employeeGender=' +employeeGender+ '&employeeMaritalStatus='+employeeMaritalStatus+'&employeeBirthDate='+employeeBirthDate
                        +'&bloodGroup='+bloodGroup+'&hireDate='+hireDate+"&joiningDate="+joiningDate,
                success: function(response){
                    $.unblockUI();
                    if(response.result){
                        Ext.MessageBox.alert("Success", "Miscellaneous saved successfully");
                        showMiscInfo();
                        $("#employeeGenderDiv").html(response.result.employeeGender);
                        $("#employeeGender").html(response.result.employeeGender);
                        $("#employeeMaritalStatusDiv").html(response.result.employeeMaritalStatus);
                        $("#employeeMaritalStatus").html(response.result.employeeMaritalStatus);
                        $("#employeeBirthDateDiv").html(response.result.employeeBirthDate);
                        $("#employeeBirthDate").html(response.result.employeeBirthDate);
                        $("#bloodGroupDiv").html(response.result.bloodGroup);
                        $("#bloodGroup").html(response.result.bloodGroup);
                        $("#hireDateDiv").html(response.result.hireDate);
                        $("#hireDate").html(response.result.hireDate);
//                            Calendar.getInstance(TimeZone.getTimeZone("CST")).time
                        $("#joiningDateDiv").html(response.result.joiningDate);
                        $("#joiningDate").html(response.result.joiningDate);

                    }else{
                        Ext.MessageBox.alert("Failed", "Miscellaneous save failed");
                    }
                }

            })


        })

        %{--contact save--}%
        $("#contactSave").bind("click", function(){
            var homePhone = $("#homePhone").val();
            var mobile = $("#mobile").val();
            var workPhone = $("#workPhone").val();
            var workEmail = $("#workEmail").val();
            var generalEmail = $("#generalEmail").val();

            var homePhoneId = $("#homePhoneId").val();
            var mobileId = $("#mobileId").val();
            var workPhoneId = $("#workPhoneId").val();
            var workEmailId = $("#workEmailId").val();
            var generalEmailId = $("#generalEmailId").val();

//                alert(homePhone + " "+ mobile + " "+workPhone+ " "+workEmail +" "+
//                        generalEmail+" "+homePhoneId+" "+mobileId+" "+workPhoneId+" "+workEmailId+" "+generalEmailId)

            $.blockUI({
                message: '<em>Saving Miscellaneous... Please wait for a while...</em>',
                css: {
                    border: 'none',
                    padding: '15px',
                    backgroundColor: '#000',
                    '-webkit-border-radius': '10px',
                    '-moz-border-radius': '10px',
                    opacity: .5,
                    color: '#fff'
                }
            });
            $.ajax({
                type:'POST',
                url:'${createLink(controller: 'employee', action: 'updateContactInfo')}',
                data: 'profileId=' +profileId+ '&homePhone=' +homePhone+ '&mobile='+mobile+'&workPhone='+workPhone
                        +'&workEmail='+workEmail+"&generalEmail="+generalEmail+'&homePhoneId='+homePhoneId+"&mobileId="+mobileId
                        +'&workPhoneId='+workPhoneId+"&workEmailId="+workEmailId+'&generalEmailId='+generalEmailId,
                success: function(response){
                    $.unblockUI();
                    if(response.result){
//                            alert("Contact update successfully");
                        Ext.MessageBox.alert("Success", "Contact saved successfully");
                        showContactInfo();


                        $("#homePhoneDiv").html(response.homePhone.number);
                        $("#homePhone").html(response.homePhone.number);

                        $("#mobileDiv").html(response.mobile.number);
                        $("#mobile").html(response.mobile.number);

                        $("#workPhoneDiv").html(response.workPhone.number);
                        $("#workPhone").html(response.workPhone.number);

                        $("#workEmailDiv").html(response.workEmail.address);
                        $("#workEmail").html(response.workEmail.address);

                        $("#generalEmailDiv").html(response.generalEmail.address);
                        $("#generalEmail").html(response.generalEmail.address);



                    }else{
                        Ext.MessageBox.alert("Failed", "Contact save failed");
                    }
                }

            })
        })
        //contact click

       $("#paymentSave").bind("click", function(){

           var jsonObj = [];


           $(".paymentInfoText").each(function() {

               jsonObj.push({"id": $(this).children().attr('id'), "optionValue": $(this).children().val()});

           });

           var serverJsonData = JSON.stringify(jsonObj)

           $.ajax({
               type:'POST',
               url:'${createLink(controller: 'employee', action: 'updateAllowanceInfo')}',
               data: 'profileId=' +profileId+ '&serverJsonData=' +serverJsonData,
               success: function(response){
                   $.unblockUI();
                   if(response.result){

                       Ext.MessageBox.alert("Success", "Employee allowance saved successfully");
                       showPaymentInfo();

                   }else{

                       Ext.MessageBox.alert("Failed", "Employee allowance save failed");
                       showPaymentInfo();

                   }

               }

           })

       })

    });
    function showGeneralInfo(){
        $(".detailsInfoDiv").show();
        $(".detailsInfoText").hide();
        $("#editGeneralInfoImg").attr("src", "${resource(dir:'images', file: 'edit.png')}");
        $(".showButton").hide();
    }
    function editGeneralInfo(){
        $(".detailsInfoText").show();
        $(".detailsInfoDiv").hide();
        $(".showButton").show();
        $("#editGeneralInfoImg").attr("src", "${resource(dir:'images', file: 'show.png')}");
    }


</script>



</content>
</body>

</g:applyLayout>