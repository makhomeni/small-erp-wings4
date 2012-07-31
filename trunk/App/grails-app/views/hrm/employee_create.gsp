<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/20/12
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="org.springframework.context.annotation.EnableAspectJAutoProxy; com.jabait.hrm.payroll.AllowanceType; com.jabait.hrm.Employee"%>
<%@ page import="com.jabait.hrm.EmployeeProfile"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<g:applyLayout name="app">
<head>
<g:set var="entityName" value="${message(code:'employee.description', default: 'Employee')}"/>
<title><g:message code="default.create.label" args="[entityName]"/></title>
<script type="text/javascript" src="${resource(dir: 'js', file: 'livevalidation_standalone.compressed.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js', file: 'jscal2.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js', file: 'en.js')}"></script>


<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'apps.css')}">



<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'border-radius.css')}"/>
<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'jscal2.css')}"/>
<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'steel.css')}"/>
<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'basic.css')}" media='screen'/>
<link type='text/css' href='${resource(dir: "css", file: "tabs.css")}' rel='stylesheet' media='screen' />

<script type="text/javascript">

</script>

<style type="text/css">
.content{

    width: 900px;
}

.slider{
    width: 900px;
}

.header{
    width: 900px;
}

.widget{
    width: 960px;
}

</style>


<script type="text/javascript">


    //var's for organization
    var organizationDataStore;
    var organizationGrid;
    var checkBoxSelModModelOrganization;
    var organizationWindow;

    //var's for department window
    var departmentDataStore;
    var departmentGrid;
    var checkBoxSelectionModelDepartment;
    var departmentWindow;

    //for section
    var sectionDataStore;
    var sectionGrid;
    var checkBoxSelectionModelSection;
    var sectionWindow;

    //for designation
    var designationDataStore;
    var designationGrid;
    var checkBoxSelectionModelDesignation;
    var designationWindow;


    %{--for employment--}%
    var employmentDataStore;
    var employmentGrid;
    var checkBoxSelectionModelEmployment;


    %{--for workhistory--}%
    var workHistoryDataStore;
    var workHistoryGrid;
    // var checkBoxSelectionModelWorkHistoryGrid


    %{--for skillset--}%
    var skillSetDataStore;
    var skillSetGrid;
    var checkBoxSelectionModelSkillSet;



    Ext.onReady(function(){
        organizationDataStore = new Ext.data.Store({
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
            store : organizationDataStore,
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
                store : organizationDataStore,
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
//                            alert(sel.get('id'))

                        $("#organization").val(sel.get('organizationName'))
                        $("#organizationId").val(sel.get('id'))

                        //to show department selection option
                        departmentStore.setBaseParam("orgId",sel.get('id'))

                        //show thw department selection part
                        $("#departmentSelect").show('slow')
                        //hide the window
                        organizationWindow.hide();

                    });


                    departmentStore.load({
                        params : {start: 0, limit: 10}
                    })
                }
            }
        })
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



        ////////////End organization section/////////////



        //for department //////////////////////////////
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
            id: 'organizationGrid',
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
                    width: 370,
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


                        //set the selected name in textfield
                        $("#department").val(sel.get('departmentName'))
                        $("#departmentId").val(sel.get('id'))

                        //set the base id for the selected department sections
                        sectionStore.setBaseParam("deptId",sel.get('id'))

                        //to show section selection option
                        $("#sectionSelect").show('slow')
                        //hide the window
                        departmentWindow.hide();
                    });


                    sectionStore.load({
                        params : {start: 0, limit: 10}
                    })
                }
            }
        })
        departmentDataStore.load({params: {start: 0, limit: 10}});

        departmentWindow = new Ext.Window({
            id: 'departmentWindow',
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
                        departmentWindow.hide();
                    }
                }
            ]
        });

        ////////////////////////////end department/////////////


        //for section

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
            //sm: new Ext.grid.CheckboxSelectionModel(),
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



        /////////////////////////////////////Designation///////////////////////////////////////////////////
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
//                                alert("section id "+sel.get('id'))
                        $("#designation").val(sel.get('jobTitleName'))
                        $("#designationId").val(sel.get('id'))

                    });
                    designationWindow.hide();

                }
            }
        })
        designationDataStore.load({params: {start: 0, limit: 10}});

        designationWindow = new Ext.Window({
            id: 'designationWindow',
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
                        designationWindow.hide();
                    }
                }
            ]
        });




        ////////////////////////Designation end/////////////////////////////


        %{--grid for Employment--}%

        employmentDataStore = new Ext.data.Store({
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
        })


        //create the grid
        checkBoxSelectionModelEmployment = new Ext.grid.CheckboxSelectionModel();
        employmentGrid = new Ext.grid.EditorGridPanel({
            id: 'employmentGrid',
            store : employmentDataStore,
            clicksToEdit: 2,
            renderTo: "employmentHistory",
            selModel : checkBoxSelectionModelEmployment,
            height: 250,
            width:700,
            //sm: new Ext.grid.CheckboxSelectionModel(),
            columns: [

                {

                    header: 'Period',
                    sortable: true,
                    width: 150,
                    editable: true,
                    editor: new Ext.form.TextField()
                },
                {

                    header: 'Start Event',
                    sortable: true,
                    width: 100,
                    editable: true,
                    editor: new Ext.form.TextField()
                },
                {

                    header: 'Position',
                    sortable: true,
                    width: 80,
                    editable: true,
                    editor: new Ext.form.TextField()
                },
                {

                    header: 'Employment Status',
                    sortable: true,
                    width: 100,
                    editable: true,
                    editor: new Ext.form.TextField()
                },
                {

                    header: 'Notes',
                    sortable: true,
                    width: 150,
                    editable: true,
                    editor: new Ext.form.TextField()
                }

            ],
            stripeRows : true,

            tbar:[{
                xtype: "button",
                text: "Add Employment",
                iconClass:"addRecord"
            }],

            bbar: new Ext.PagingToolbar({
                store : employmentDataStore,
                pageSize : 10,
                displayInfo : true,
                displaymsg : 'Displaying {0} - {1} of {2}',
                emptyMsg : "No records found"
            })
        })
        employmentDataStore.load({params: {start: 0, limit: 10}});


        %{--grid for work history--}%

        workHistoryDataStore = new Ext.data.Store({
            id: "workHistoryDataStore",
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
        })


        //create the grid
        checkBoxSelectionModelWorkHistoryGrid = new Ext.grid.CheckboxSelectionModel();
        workHistoryGrid = new Ext.grid.EditorGridPanel({
            id: 'employmentGrid',
            store : workHistoryDataStore,
            clicksToEdit: 2,
            renderTo: "workHistory",
            selModel : checkBoxSelectionModelWorkHistoryGrid,
            height: 250,
            width:700,
            //sm: new Ext.grid.CheckboxSelectionModel(),
            columns: [

                {

                    header: 'Company Name',
                    sortable: true,
                    width: 150,
                    editable: true,
                    editor: new Ext.form.TextField()
                },
                {

                    header: 'Job Title',
                    sortable: true,
                    width: 100,
                    editable: true,
                    editor: new Ext.form.TextField()
                },
                {

                    header: 'Start Date',
                    sortable: true,
                    width: 80,
                    editable: true,
                    editor: new Ext.form.TextField()
                },
                {

                    header: 'End Date',
                    sortable: true,
                    width: 100,
                    editable: true,
                    editor: new Ext.form.TextField()
                },
                {

                    header: 'Remark',
                    sortable: true,
                    width: 150,
                    editable: true,
                    editor: new Ext.form.TextField()
                }

            ],
            stripeRows : true,

            bbar: new Ext.PagingToolbar({
                store : workHistoryDataStore,
                pageSize : 10,
                displayInfo : true,
                displaymsg : 'Displaying {0} - {1} of {2}',
                emptyMsg : "No records found"
            })
        })
        workHistoryDataStore.load({params: {start: 0, limit: 10}});


        %{--grid for skillset--}%

        skillSetDataStore = new Ext.data.Store({
            id: "skillSetDataStore",
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
        })


        //create the grid
        checkBoxSelectionModelSkillSet = new Ext.grid.CheckboxSelectionModel();
        skillSetGrid = new Ext.grid.EditorGridPanel({
            id: 'skillSetGrid',
            store : skillSetDataStore,
            clicksToEdit: 2,
            renderTo: "skillSet",
            selModel : checkBoxSelectionModelSkillSet,
            height: 250,
            width:700,
            //sm: new Ext.grid.CheckboxSelectionModel(),
            columns: [

                {

                    header: 'Company Name',
                    sortable: true,
                    width: 150,
                    editable: true,
                    editor: new Ext.form.TextField()
                },
                {

                    header: 'Job Title',
                    sortable: true,
                    width: 100,
                    editable: true,
                    editor: new Ext.form.TextField()
                },
                {

                    header: 'Start Date',
                    sortable: true,
                    width: 80,
                    editable: true,
                    editor: new Ext.form.TextField()
                },
                {

                    header: 'End Date',
                    sortable: true,
                    width: 100,
                    editable: true,
                    editor: new Ext.form.TextField()
                },
                {

                    header: 'Remark',
                    sortable: true,
                    width: 150,
                    editable: true,
                    editor: new Ext.form.TextField()
                }

            ],
            stripeRows : true,
            bbar: new Ext.PagingToolbar({
                store : skillSetDataStore,
                pageSize : 10,
                displayInfo : true,
                displaymsg : 'Displaying {0} - {1} of {2}',
                emptyMsg : "No records found"
            })
        })
        skillSetDataStore.load({params: {start: 0, limit: 10}});


    })

    %{--end ext js--}%

    function submitForm(){
        alert(1);
        document.avatarForm.submit();
    }

</script>


</head>
<body>

<content tag="titleTag">

</content>
<content tag="rightTag">
<script type='text/javascript' src="${resource(dir: 'js', file: 'jquery.simplemodal.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.uniform.js')}" ></script>
<script type="text/javascript" src="${resource(dir: 'js', file: 'coda-slider.1.1.1.js')}"></script>


<noscript>
    <p>Unfortunately your browser does not hava JavaScript capabilities which are required to exploit full functionality of our site. This could be the result of two possible scenarios:</p>
    <ol>
        <li>You are using an old web browser, in which case you should upgrade it to a newer version. We recommend the latest version of <a href="http://www.getfirefox.com">Firefox</a>.</li>
        <li>You have disabled JavaScript in you browser, in which case you will have to enable it to properly use our site. <a href="http://www.google.com/support/bin/answer.py?answer=23852">Information on enabling JavaScript</a>.</li>
    </ol>
</noscript>

<div class="bread_crumbs_ui_div" style="width: 960px">
    <ul id="crumbs_ui_custom">
        <li><g:link controller="application" action="index">Dashboard</g:link></li>
        <li><g:link controller="employee" action="employeeList">Employee List</g:link></li>
        <li>Employee Create</li>
    </ul>
</div>

<br class="clear"/>

<div class="widget">

<div class="header" style="width: 960px;">
    <span><span class="ico gray add employee" style="margin-right: 8px;"></span>${titleOfPage}</span>
</div>

<div class="content" style="width: 910px">

<div class="container">
    <ul class="tabul" style="width: 507px;">
        <li id="tb1"><a >General Info</a></li>
        <li id="tb2"><a >Contacts</a></li>
        <li id="tb3"><a >Allowance</a></li>
        <li id="tb4"><a >Work History</a></li>
        <li id="tb5"><a >Skillset</a></li>
        <li id="tb6"><a >Avatar</a></li>
    </ul>
</div>

%{--start first tab--}%
<div id="tab1" class="tabul_content">

<div class="container_16" style="width: 701px;">

<div style="padding-top: 15px">

<div class="grid_6 alpha leftElement">

    <fieldset class="user-profile-fieldset">
        <legend class="legend-color">Employee Info</legend>

        %{--username--}%
        <div style="padding-top: 5px">

        </div>

        <div class="grid_2" style="padding-top: 5px;">
            <label for="username">
                <g:message code="username.label" default="User name" />
                <span class="required-indicator">*</span>
            </label>
        </div>
        <div class="grid_3">
            <g:textField name="username" id="username" required="" value=""/>
        </div>

        <br class="clear" />
        <br class="clear" />

        %{--password--}%

        <div class="grid_2" style="padding-top: 5px;">
            <label for="password">
                <g:message code="password.label" default="Password" />
                <span class="required-indicator">*</span>
            </label>
        </div>
        <div class="grid_3">
            <g:passwordField name="password" required="" value=""/>
        </div>

        <br class="clear" />
        <br class="clear" />

        %{--re password--}%

        <div class="grid_2" >
            <label for="rePassword">
                Confirm Password
                <span class="required-indicator">*</span>
            </label>
        </div>
        <div class="grid_3">
            <g:passwordField name="rePassword" id="rePassword" required="" value=""/>
        </div>

        <br class="clear" />
        <br class="clear" />

        %{--first name--}%

        <div class="grid_2" style="padding-top: 5px;">
            <label for="firstName">
                <g:message code="firstName.label" default="First Name" />
                <span class="required-indicator">*</span>
            </label>
        </div>

        <div class="grid_3">
            <g:textField name="firstName" id="firstName" required="" value=""/>
        </div>

        <br class="clear" />
        <br class="clear" />

        %{--Middle Name--}%

        <div class="grid_2" style="padding-top: 5px;">
            <label for="middleName">
                <g:message code="middleName.label" default="Middle Name" />
                <span class="required-indicator">*</span>
            </label>
        </div>

        <div class="grid_3">
            <g:textField name="middleName" id="middleName" required="" value=""/>
        </div>

        <br class="clear" />
        <br class="clear" />

        %{--Last Name--}%

        <div class="grid_2" style="padding-top: 5px;">
            <label for="lastName">
                <g:message code="lastName.label" default="Last Name" />
                <span class="required-indicator">*</span>
            </label>
        </div>

        <div class="grid_3">
            <g:textField name="lastName" id="lastName" required="" value=""/>
        </div>

        <br class="clear" />
        <br class="clear" />
    </fieldset>

</div>

<div class="grid_6 omega rightElement" >

    <fieldset class="user-profile-fieldset" style="height: 291px">
        <legend class="legend-color">Organization</legend>

        <div style="padding-top: 8px">

        </div>

        %{--Organization--}%

        <div class="grid_2" style="padding-top: 5px;">
            <label for="organization">
                <g:message code="organization.label" default="Organization" />
                <span class="required-indicator">*</span>
            </label>
        </div>

        <div class="grid_3">
            <g:textField id="organization" name="organization" required="" readonly="readonly" value=""/>
            <g:hiddenField name="organizationId" id="organizationId"></g:hiddenField>
        </div>

        <a id="organizationSelect" href="#">
            <img src="${resource(dir: 'images', file: 'organization_picker.png')}" alt="Organization Image" onclick="organizationWindow.show()" class="adjustImg" title="Click to select organization">
        </a>





        <br class="clear" />
        <br class="clear" />


        <div class="grid_2" style="padding-top: 5px;">
            <label for="Department">
                Department
                <span class="required-indicator">*</span>
            </label>
        </div>
        <div class="grid_3">
            <g:textField id="department" name="department" required="" value="" readonly="readonly" />
            <g:hiddenField name="departmentId" id="departmentId"></g:hiddenField>
        </div>
        <a id="departmentSelect" href="#" style="display: none;">
            <img src="${resource(dir: 'images', file: 'department.png')}" alt="Department Image" class="adjustImg" onclick="departmentWindow.show()" title="Click to select department">
        </a>





        <br class="clear" />
        <br class="clear" />

        %{--section--}%

        <div class="grid_2" style="padding-top: 5px;">
            <label for="sectionId">
                Section

            </label>
        </div>
        <div class="grid_3">
            <g:textField name="section" required="" value="" readonly="readonly"/>
            <g:hiddenField name="sectionId" id="sectionId"></g:hiddenField>
        </div>

        <a id="sectionSelect" href="#" style="display: none;" >
            <img src="${resource(dir: 'images/assets/icons', file: 'section.png')}" alt="Section Image" class="adjustImg" onclick="sectionWindow.show()"  title="Click to select section">
        </a>



        <br class="clear" />
        <br class="clear" />

        <div class="grid_2" style="padding-top: 5px;">
            <label for="designation">
                Designation
                <span class="required-indicator">*</span>
            </label>
        </div>
        <div class="grid_3">
            <g:textField name="designation" id="designation" readonly="readonly"></g:textField>
            <g:hiddenField name="designationId" id="designationId"></g:hiddenField>
        </div>

        <a id="designationSelect" href="#"  >
            <img src="${resource(dir: 'images/assets/icons', file: 'jobTitle.png')}" alt="Designation" class="adjustImg" onclick="designationWindow.show()" title="Click to select designation">
        </a>



        <br class="clear" />
        <br class="clear" />

        <div class="grid_2" style="padding-top: 5px;">
            <label for="salary">
                Salary
                <span class="required-indicator">*</span>
            </label>
        </div>
        <div class="grid_3">
            <g:textField name="salary" id="salary" required="" value=""/>
        </div>


        <br class="clear" />
        <br class="clear" />

        %{--region--}%
        <div class="grid_2" style="padding-top: 5px;">
            <label for="region">
                Region

            </label>
        </div>
        <div class="grid_3">
            <g:textField name="region" id="region" required="" value=""/>
        </div>

        <br class="clear" />

    </fieldset>

</div>

</div>  <!--div for padding end-->

</div><!-- grid16 container for top element-->

<div class="container_16" style="width: 701px;">
<div class="grid_6 alpha leftElement" style="padding-top: 20px;">

    <fieldset class="user-profile-fieldset">
        <legend class="legend-color">Miscellaneous</legend>

        <div style="padding-top: 8px">

        </div>
        %{--Gender--}%
        <div class="grid_2" style="padding-top: 5px;">
            <label for="gender">
                Gender
                <span class="required-indicator">*</span>
            </label>
        </div>
        <div class="grid_3">
            <g:select name="gender" id="gender" from="${['Male', 'Female']}"/>
        </div>

        <br class="clear" />
        <br class="clear" />


        <div class="grid_2" style="padding-top: 5px;">
            <label for="maritalStatus">
                Marital Status
                <span class="required-indicator">*</span>
            </label>
        </div>
        <div class="grid_3">
            %{--<g:textField name="maritalStatus" required="" value=""/>--}%
            <g:select name="maritalStatus" id="maritalStatus" from="${['Married', 'Single']}"/>
        </div>

        <br class="clear" />
        <br class="clear" />

        %{--DOB--}%
        <div class="grid_2" style="padding-top: 5px;">
            <label for="dateOfBirth">
                Date Of Birth
                <span class="required-indicator">*</span>
            </label>
        </div>
        <div class="grid_3">
            <g:textField name="dateOfBirth" id="dateOfBirth" required="" value="" readonly="readonly"/>
        </div>
        <img src="${resource(dir: 'images/calendar', file: 'calendar_add.png')}" alt="calender" id="showCalendarDateOfBirth" class="adjustImg" title="Click to select date of birth">
        <script type="text/javascript">
            //<![CDATA[
            Calendar.setup({
                inputField : "dateOfBirth",
                trigger    : "showCalendarDateOfBirth",
                onSelect   : function() { this.hide() },
                showTime   : 12,
                dateFormat : "%d/%m/%Y %H:%m:%S"//this is a timestamp format
            });
            //]]>
        </script>

        <br class="clear" />
        <br class="clear" />


        <div class="grid_2" style="padding-top: 5px;">
            <label for="dateOfHire">
                Date Of Hire
                <span class="required-indicator">*</span>
            </label>
        </div>
        <div class="grid_3">
            <g:textField name="dateOfHire" id="dateOfHire" required="" value="" readonly="readonly"/>
        </div>
        <img src="${resource(dir: 'images/calendar', file: 'calendar_add.png')}" alt="calender" id="showCalendarDateOfHire" class="adjustImg" title="Click to select date of hire">
        <script type="text/javascript">
            //<![CDATA[
            Calendar.setup({
                inputField : "dateOfHire",
                trigger    : "showCalendarDateOfHire",
                onSelect   : function() { this.hide() },
                showTime   : 12,
                dateFormat : "%d/%m/%Y %H:%m:%S"//this is a timestamp format
            });
            //]]>
        </script>

        <br class="clear" />
        <br class="clear" />

        <div class="grid_2" style="padding-top: 5px;">
            <label for="dateOfJoin">
                Date Of Join
                <span class="required-indicator">*</span>
            </label>
        </div>
        <div class="grid_3">
            <g:textField name="dateOfJoin" id="dateOfJoin" required="" value="" readonly="readonly"/>
        </div>
        <img src="${resource(dir: 'images/calendar', file: 'calendar_add.png')}" alt="calender" id="showCalendarDateOfJoin" class="adjustImg" title="Click to select date of Join">
        <script type="text/javascript">
            //<![CDATA[
            Calendar.setup({
                inputField : "dateOfJoin",
                trigger    : "showCalendarDateOfJoin",
                onSelect   : function() { this.hide() },
                showTime   : 12,
                dateFormat : "%d/%m/%Y %H:%m:%S"//this is a timestamp format
            });
            //]]>
        </script>

        <br class="clear" />
        <br class="clear" />



        <div class="grid_2" style="padding-top: 5px;">
            <label for="bloodGroup">
                Blood Group
                <span class="required-indicator">*</span>
            </label>
        </div>
        <div class="grid_3">

            <g:select name="bloodGroup" id="bloodGroup" from="${['A+', 'A-', 'AB+', 'AB-', 'B+', 'B-', 'O+', 'O-', 'Unknown']}"/>
        </div>

        <div class="grid_2" style="padding-top: 5px;">
            <label for="providentFund">
                Provident Fund
            </label>
        </div>
        <div class="grid_3">

            <g:checkBox name="providentFund" id="providentFund" value="${false}" />
        </div>


        <br class="clear" />
        <br class="clear" />

    </fieldset>

</div>

<div class="grid_6 omega rightElement"  style="padding-top: 20px;">

    <fieldset class="user-profile-fieldset" style="height: 296px">
        <legend class="legend-color">Identification</legend>

        <div style="padding-top: 8px">

        </div>

        <div class="grid_2" style="padding-top: 5px;">
            <label for="nationalID">
                National ID
                <span class="required-indicator">*</span>
            </label>
        </div>

        <div class="grid_3">
            <g:textField name="nationalID" id="nationalID" required="" value=""/>
        </div>

        <br class="clear" />
        <br class="clear" />

        <div class="grid_2" style="padding-top: 5px;">
            <label for="passportNo">
                Passport No
                <span class="required-indicator">*</span>
            </label>
        </div>
        <div class="grid_3">
            <g:textField name="passportNo" id="passportNo" required="" value=""/>
        </div>

        <br class="clear" />
        <br class="clear" />


        <div class="grid_2" style="padding-top: 5px;">
            <label for="issueDate">
                Issue Date
                <span class="required-indicator">*</span>
            </label>
        </div>
        <div class="grid_3">
            <g:textField name="issueDate" id="issueDate" required="" value="" readonly="readonly"/>
        </div>
        <img src="${resource(dir: 'images/calendar', file: 'calendar_add.png')}" alt="calender" id="showCalendarIssudate" class="adjustImg" title="Click to select issue date">
        <script type="text/javascript">
            //<![CDATA[
            Calendar.setup({
                inputField : "issueDate",
                trigger    : "showCalendarIssudate",
                onSelect   : function() { this.hide() },
                showTime   : 12,
                dateFormat : "%d/%m/%Y %H:%m:%S"//this is a timestamp format
            });
            //]]>
        </script>

        <br class="clear" />
        <br class="clear" />


        %{--Expired Date--}%
        <div class="grid_2" style="padding-top: 5px;">
            <label for="expireDate">
                Expired Date
                <span class="required-indicator">*</span>
            </label>
        </div>
        <div class="grid_3">
            <g:textField name="expireDate" id="expireDate" required="" value="" readonly="readonly"/>
        </div>
        <img src="${resource(dir: 'images/calendar', file: 'calendar_add.png')}" alt="calender" id="showCalendarExpireDate" class="adjustImg" title="Click to select date">
        <script type="text/javascript">
            //<![CDATA[
            Calendar.setup({
                inputField : "expireDate",
                trigger    : "showCalendarExpireDate",
                onSelect   : function() { this.hide() },
                showTime   : 12,
                dateFormat : "%d/%m/%Y %H:%m:%S"//this is a timestamp format
            });
            //]]>
        </script>

        <br class="clear" />
        <br class="clear" />


        <div class="grid_2" style="padding-top: 5px;">
            <label for="issueFrom">
                Issue From
                <span class="required-indicator">*</span>
            </label>
        </div>
        <div class="grid_3">
            <g:textField name="issueFrom" id="issueFrom" required="" value="" />
        </div>
        <br class="clear" />
        <br class="clear" />



        <br class="clear" />
    </fieldset>
    <br class="clear">



</div>
<fieldset class="grid_8" style="margin-left: 275px;">
    <a id="generalSave" class="button icon approve" onclick="">Save</a>
    <a id="clearForm" class="button danger icon remove" onclick="document.userForm.reset()">Clear</a>
    <g:hiddenField name="profileId" id="profileId"></g:hiddenField>
</fieldset>

<br class="clear"/>

<br class="clear"/>


</div> <!--container grid16 down element-->

</div><!--End tab1 div-->
%{--end first tab--}%
%{-- tab2 start --}%
<div id="tab2" class="tabul_content" >


    <div class="container_16" style="height: 380px">

    <div class="grid_8" style="padding-top: 20px;">

    <fieldset class="user-profile-fieldset">
        <legend class="legend-color">Contact Info</legend>

        <div style="padding-top: 8px">

        </div>

        <g:form name="contactForm" id="contactForm" >

        %{--home telephone--}%
            <div class="grid_3" style="padding-top: 5px;">
                <label for="homeTelephone">
                    Home Telephone
                    <span class="required-indicator">*</span>
                </label>
            </div>

            <div class="grid_3">
                <g:textField name="homeTelephone" id="homeTelephone"></g:textField>
            </div>

            <br class="clear" />
            <br class="clear" />

            <div class="grid_3" style="padding-top: 5px;">
                <label for="mobile">
                    Mobile
                    <span class="required-indicator">*</span>
                </label>
            </div>

            <div class="grid_3">
                <g:textField name="mobile" id="mobile"></g:textField>
            </div>

            <br class="clear" />
            <br class="clear" />

            <div class="grid_3" style="padding-top: 5px;">
                <label for="workTelephone">
                    Work Telephone
                    <span class="required-indicator">*</span>
                </label>
            </div>

            <div class="grid_3">
                <g:textField name="workTelephone" id="workTelephone"></g:textField>
            </div>

            <br class="clear" />
            <br class="clear" />

            <div class="grid_3" style="padding-top: 5px;">
                <label for="workEmail">
                    Work Email
                    <span class="required-indicator">*</span>
                </label>
            </div>

            <div class="grid_3">
                <g:textField name="workEmail" id="workEmail"></g:textField>
            </div>

            <br class="clear" />
            <br class="clear" />

            <div class="grid_3" style="padding-top: 5px;">
                <label for="generalEmail">
                    General Email
                    <span class="required-indicator">*</span>
                </label>
            </div>

            <div class="grid_3">
                <g:textField name="generalEmail" id="generalEmail"></g:textField>
            </div>

            <br class="clear" />
            <br class="clear" />


            </fieldset>

     </div><!-- end div for left element 2nd tab-->


            <br class="clear" />
            <br class="clear" />

            <!--for button-->
            <div class="grid_6" style="margin-left: 344px; margin-top: 20px;">
                <a id="contactSave" class="button icon approve" onclick="">Save</a>
                <a id="cotactReset" class="button danger icon remove" onclick="document.contactForm.reset()">Clear</a>
            </div>

        </g:form><!--end contact form-->


    </div><!--end grid 16 container for tab2 div-->

</div><!--end tab2 div-->

<!--start tab 3-->
<div id="tab3" class="tabul_content">

    %{--<div class="container16" style="height: 350px">--}%
    %{--<div class="grid_12">--}%
    %{--<div id="employmentHistory" style="margin-top: 22px; margin-left: 30px;">--}%

    %{--</div>--}%

    %{--</div>--}%

    %{--</div>--}%
    %{--<br class="clear" />--}%
    %{--<br class="clear" />--}%

    %{--<div class="container16" style="height: 350px">--}%
    %{--<div class="grid_6 alpha">--}%
    %{--<fieldset class="user-profile-fieldset" style="width: 196px; height: 162px; margin-left: 36px;">--}%
    %{--<legend class="legend-color">Pay</legend>--}%

    %{--<div class="grid_2">--}%
    %{--<g:checkBox name="myCheckbox" value="" />--}%
    %{--</div>--}%

    %{--<div class="grid_3">--}%
    %{--<label for="Pay">--}%
    %{--<g:message code="employee.username.label" default="Salaried" />--}%
    %{--</label>--}%
    %{--</div>--}%

    %{--<br class="clear" />--}%
    %{--<br class="clear" />--}%

    %{--<div class="grid_2">--}%
    %{--<g:checkBox name="myCheckbox" value="" />--}%
    %{--</div>--}%

    %{--<div class="grid_3">--}%
    %{--<label for="Pay">--}%
    %{--<g:message code="employee.username.label" default="Hourly" />--}%
    %{--</label>--}%
    %{--</div>--}%
    %{--</fieldset>--}%
    %{--</div>--}%
    %{--<div class="grid_6">--}%
    %{--<fieldset class="user-profile-fieldset" style="width: 192px; height: 162px; margin-left: 20px;">--}%
    %{--<legend class="legend-color">Deposit</legend>--}%

    %{--<div class="grid_2">--}%
    %{--<g:checkBox name="myCheckbox" value="" />--}%
    %{--</div>--}%

    %{--<div class="grid_3">--}%
    %{--<label for="Pay">--}%
    %{--<g:message code="employee.username.label" default="Account" />--}%
    %{--</label>--}%
    %{--</div>--}%

    %{--<br class="clear" />--}%
    %{--<br class="clear" />--}%

    %{--<div class="grid_2">--}%
    %{--<g:checkBox name="myCheckbox" value="" />--}%
    %{--</div>--}%

    %{--<div class="grid_3">--}%
    %{--<label for="Pay">--}%
    %{--<g:message code="employee.username.label" default="Cash" />--}%
    %{--</label>--}%
    %{--</div>--}%

    %{--</fieldset>--}%

    <div class="container_16" style="height: 380px">

    <div class="grid_8" style="padding-top: 20px;">

    <fieldset class="user-profile-fieldset">
        <legend class="legend-color">Allowance Information</legend>

        <div style="padding-top: 8px">

        </div>

        <g:form name="allowanceForm" id="allowanceForm" >

        %{--home telephone--}%
            <g:each in="${AllowanceType.list()}" var="allowanceType">


                <div class="grid_3" style="padding-top: 5px;">
                    <label for="${allowanceType.id}">
                        ${allowanceType.allowanceName}

                    </label>
                </div>

                <div class="grid_3 allowanceInfo">
                    <g:textField name="${allowanceType.id}" id="${allowanceType.id}" value=""></g:textField>
                </div>

                <br class="clear" />
                <br class="clear" />

            </g:each>


            </fieldset>

    </div><!-- end div for left element 2nd tab-->




           <!--for button-->
            <div class="grid_6" style="margin-left: 319px; margin-top: 20px;">
                <a id="allowanceSave" class="button icon approve" onclick="">Save</a>
                <a id="allowanceReset" class="button danger icon remove" onclick="document.allowanceForm.reset()">Clear</a>
            </div>

        </g:form><!--end allownce form-->


    </div><!--end grid 16 container for tab2 div-->





</div><!-- end tab3-->


%{--tab4--}%
<div id="tab4" class="tabul_content">
    <div class="container16" style="height: 300px">

        <div class="grid_12">
            <div id="workHistory" style="margin-top: 22px; margin-left: 30px;">

            </div>

        </div>

    </div> <!--end first container of tab4-->

</div><!--end tab4-->

<div id="tab5" class="tabul_content">
    <div class="container16" style="height: 300px">

        <div class="grid_12">
            <div id="skillSet" style="margin-top: 22px; margin-left: 30px;">

            </div>

        </div>

    </div> <!--end first container of tab5-->

</div>

%{--start script for file upload--}%

<script type="text/javascript">

    var upload = function() {
        var photo = document.getElementById("photo");
        alert(photo.value);
        // the file is the first element in the files property
//                    alert(photo.files[0].fileName)
        var file = photo.files[0];

        console.log("File name: " + file.fileName);
        console.log("File name: " + file.fileSize);
        console.log("Binary content: " + file.getAsBinary());

        return false;
    };

</script>

%{--end script for file upload--}%



%{--start tab 6--}%

<div id="tab6" class="tabul_content">

    <div class="container16" style="height: 300px">

        <g:form id="avatarForm" name="avatarForm" method="POST" controller="employee" action="saveEmployeeAvatar" enctype="multipart/form-data">


            <div class="grid_10" style="margin-top: 22px; margin-left: 30px;">
                %{--<div class="grid_8">--}%
                <input type="file" name="photo" id="photo" />
                %{--</div>--}%

            </div>

            <br class="clear" />
            <br class="clear" />

            <div class="grid_6" style="margin-left: 37px; margin-top: 20px;">
                <a id="uploadFile" class="button icon approve" onclick="return upload();">Upload</a>
                <a id="resetUpload" class="button danger icon remove" onclick="document.avatarForm.reset()">Clear</a>
            </div>

        </g:form>


    </div>

</div>   %{--end tab 6--}%





</div><!--end container all tabs between this div-->

</div> <!--widget div-->

<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.blockUI.js')}"></script>

<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'uniform.aristo.css')}">
<style>
div.selector{
    margin-top: -2px;
    margin-left: -3px;
    margin-bottom: 1px;
}
</style>

<script type="text/javascript">
    jQuery(window).bind("load", function() {
        //jQuery("div#slider1").codaSlider()
        // jQuery("div#slider2").codaSlider()
        // etc, etc. Beware of cross-linking difficulties if using multiple sliders on one page.



    });

    $(document).ajaxStop($.unblockUI);


    $(document).ready(function(){
        $(".tabul_content").hide(); //Hide all content
        $("ul.tabul li:first").addClass("active").show(); //Activate first tab
        $(".tabul_content:first").show(); //Show first tab content
//                $("ul.tabul li:second").addClass("active").show();
//                $("#tab3").show();





        $("input, textarea, select, button").uniform();

        $("#generalSave").bind("click",function(){
//                    alert(1+document.getElementById("username").value);
            var username =  document.getElementById("username").value;
            var password =  document.getElementById("password").value;
            var rePassword =  document.getElementById("rePassword").value;
            var firstName =  document.getElementById("firstName").value;
            var middleName =  document.getElementById("middleName").value;
            var lastName =  document.getElementById("lastName").value;

            var organization =  document.getElementById("organizationId").value;
            var department =  document.getElementById("departmentId").value;
            var section =  document.getElementById("sectionId").value;
            var designationId =  document.getElementById("designationId").value;

            var region =  document.getElementById("region").value;
            var salary =  document.getElementById("salary").value;
            var gender =  document.getElementById("gender").value;
            var maritalStatus =  document.getElementById("maritalStatus").value;
            var dateOfBirth =  document.getElementById("dateOfBirth").value;
            var dateOfHire =  document.getElementById("dateOfHire").value;
            var dateOfJoin =  document.getElementById("dateOfJoin").value;
            var bloodGroup =  document.getElementById("bloodGroup").value;


            var nationalID =  document.getElementById("nationalID").value;
            var passportNo =  document.getElementById("passportNo").value;
            var issueDate =  document.getElementById("issueDate").value;
            var issueFrom =  document.getElementById("issueFrom").value;
            var expireDate =  document.getElementById("expireDate").value;
            var providentFund =  document.getElementById("providentFund").checked;


//                    alert('username=' +username + '&password=' + password + '&rePassword='+ rePassword + '&firstName=' + firstName+
//                            '&lastName=' + lastName +'&middleName='+middleName+'&organizationId='+ organization +'&departmentId=' + department +'&sectionId=' + section +
//                            '&region=' + region +'&gender='+ gender +'&maritalStatus='+maritalStatus+'&dateOfBirth='+dateOfBirth+
//                            '&dateOfHire='+dateOfHire+'&dateOfJoin='+dateOfJoin+'&nationalId='+nationalID+"&issueDate="+issueDate+'&issueFrom='+issueFrom+
//                            '&expireDate='+expireDate)

//                    alert("username "+username+" password = "+password +"first name = "+firstName+ " middle name= "+middleName+" last name = "+lastName)
//                            alert(" organization"+organization+" department = "+ department+" section = "+section)
//                    alert(" region = "+region+" gender = "+gender+" maritalStatus = "+maritalStatus)
//                    alert(" dateOfBirth = "+dateOfBirth+" nationalID = "+nationalID+" issueDate = "+issueDate+" issueFrom = "+issueFrom+" expireDate= "+expireDate)
            $.blockUI({
                message: '<em>Activating your account... Please wait for a while...</em>',
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
                url:'${createLink(controller: 'employee', action: 'saveEmployee')}',
                dataType: "json",
                data: 'username=' +username + '&password=' + password + '&rePassword='+ rePassword + '&firstName=' + firstName+
                        '&lastName=' + lastName +'&middleName='+middleName+'&organizationId='+ organization +'&departmentId=' + department +'&sectionId=' + section +
                        '&salary=' + salary +'&region=' + region +'&designationId='+designationId+'&gender='+ gender +'&maritalStatus='+maritalStatus+'&dateOfBirth='+dateOfBirth+
                        '&dateOfHire='+dateOfHire+'&dateOfJoin='+dateOfJoin+'&bloodGroup='+bloodGroup+'&passportNo='+passportNo+'&nationalId='+nationalID+"&issueDate="+issueDate+'&issueFrom='+issueFrom+
                        '&expireDate='+expireDate+'&providentFund='+providentFund,
                success: function(response){
                    $.unblockUI();
//                            alert(response.result);
                    if(response.result){
                        Ext.MessageBox.alert("Success", "General information saved successfully");
                        $("#profileId").val(response.result)

                        $("#tab1").hide(); //Hide tab1 content
                        $("#tb1").removeClass("active");
                        $("#tab2").show(); //Show first tab content
                        $("#tab2").addClass("active");
                        $("#tb2").addClass("active");
                        var activeTab = $("#tab2").find("a").attr("href");
                        $(activeTab).fadeIn();
                    }else{
                        Ext.MessageBox.alert("Failed", "General information saved failed");
                        window.location = "${createLink(controller:'employee', action: 'createEmployee')}";
                    }
                }
            })
        })

        %{--start contact info save part--}%
        $("#contactSave").bind("click",function(){
            var homeTelephone = $("#homeTelephone").val();
            var mobile = $("#mobile").val();
            var workTelephone = $("#workTelephone").val();
            var workEmail = $("#workEmail").val();
            var generalEmail = $("#generalEmail").val();
            var profileId = $("#profileId").val();
//                    alert(profileId);


            $.blockUI({
                message: '<em>Saving contact information... Please wait for a while...</em>',
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
                url:'${createLink(controller: 'employee', action: 'saveEmployeeContactInfo')}',
                dataType: "json",
                data: 'homeTelephone=' +homeTelephone + '&mobile=' + mobile + '&workTelephone='+ workTelephone + '&workEmail='+ workEmail +
                        '&generalEmail=' + generalEmail+ '&profileId='+profileId,
                success: function(response){
                    $.unblockUI();

                    if(response.result){
                        Ext.MessageBox.alert("Success", "Contact information saved successfully");
                    %{--window.location = "${createLink(controller: 'employee',action: 'employeeList')}"--}%
                        $("#tab2").hide(); //Hide tab1 content
                        $("#tb2").removeClass("active");
                        $("#tab3").show(); //Show third tab content
                        $("#tab3").addClass("active");
                        $("#tb3").addClass("active");
                        var activeTab = $("#tab3").find("a").attr("href");
                        $(activeTab).fadeIn();
                    }else{
                        Ext.MessageBox.alert("Failed", "Contact information saved failed");
                    }
                }
            })

        })

        $("#allowanceSave").bind("click",function(){
//                    alert("click in allowance save");

            //make a JSON Array
            var jsonObj = [];


            //assign the input values in  JSON Array
            $(".allowanceInfo").each(function() {
//                        alert($(this).children().attr('id')+" = "+$(this).children().val());

                jsonObj.push({"id": $(this).children().attr('id'), "optionValue": $(this).children().val()});

            });



            var serverJsonData = JSON.stringify(jsonObj)


            var allowanceTypeId = $("#allowanceType").val();
            var allowanceAmount = $("#allowanceAmount").val();
            var salaryAmount = $("#salaryAmount").val();
            var profileId = $("#profileId").val();

//                    alert("allowance save click" + allowanceTypeId +" "+allowanceAmount + " "+salaryAmount);

            $.blockUI({
                message: '<em>Saving allowance information... Please wait for a while...</em>',
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
                url:'${createLink(controller: 'employee', action: 'saveEmployeeAllowance')}',
                dataType: "json",
                data: 'serverJsonData=' +serverJsonData + '&profileId='+profileId,
                success: function(response){
                    $.unblockUI();

                    if(response.result){

                        var resultString = "";
                        $.each(response.details, function(key, value){
                            $.each(value, function(key, value){
                                console.log(key, value);
                                resultString = resultString + key +" allowance " + value+"\n";
                            });
                        });

//                                Ext.MessageBox.alert("Success", resultString);
                        alert(resultString);
                        window.location = "${createLink(controller: 'employee',action: 'employeeList')}";

                    }else{
                        var failedResultString = "";
                        $.each(response.details, function(key, value){
                            $.each(value, function(key, value){
                                console.log(key, value);
                                failedResultString = failedResultString + key +" allowance " + value+"\n";
                            });
                        });

                        Ext.MessageBox.alert("Failed", failedResultString);
                    }
                }
            })

        })

    })
</script>
</content>

</body>
</g:applyLayout>