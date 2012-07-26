<%--
  Created by IntelliJ IDEA.
  User: Shohag-pc
  Date: 5/30/12
  Time: 8:29 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>

<g:applyLayout name="app">
    <head>
        <title>${type}</title>

        <script type="text/javascript">

            var employeeDataStore;
            var employeeGrid;
            var checkBoxSelectionModel;



            function deleteRec(id){
                Ext.Msg.show({
                    title:'Delete Employee?',
                    msg: 'You are deleting a employee. Would you like to delete?',
                    maxWidth:400,
                    buttons: Ext.Msg.YESNOCANCEL,
                    fn: function(btn){
                        if(btn == 'yes'){

                            window.location = "${createLink(controller: 'employee',action: 'deleteEmployee')}"+"/"+id;
                        }
                    }
                });
            }

            Ext.onReady(function(){
                //for search employee
                Ext.get('searchButton').on("click", function(){
//                    alert(1);
                    if(Ext.get('userCode').getValue() != '' || Ext.get('designation').getValue() != ''
                            || Ext.get('active').getValue() != '' || Ext.get('firstName').getValue() != ''){
//                        alert(Ext.get('userCode').getValue())

                        if(Ext.get('userCode').getValue() != ''){
                            employeeDataStore.setBaseParam('userCode', Ext.get('userCode').getValue());
                            employeeDataStore.load({
                                        param:{start:0,limit:10}
                                    }

                            )
                        }
                        if(Ext.get('designation').getValue() != ''){
                            employeeDataStore.setBaseParam('designation', Ext.get('designation').getValue());
                            employeeDataStore.load({
                                        param:{start:0,limit:10}
                                    }

                            );
                        }
                        if(Ext.get('active').getValue() != ''){
                            employeeDataStore.setBaseParam('active', Ext.get('active').getValue());
                            employeeDataStore.load({
                                        param:{start:0,limit:10}
                                    }

                            );
                        }
                        if(Ext.get('firstName').getValue() != ''){
                            employeeDataStore.setBaseParam('firstName', Ext.get('firstName').getValue());
                            employeeDataStore.load({
                                        param:{start:0,limit:10}
                                    }

                            );
                        }


                    }else{
                        alert("Please Enter Value");
                    }
                })


                employeeDataStore = new Ext.data.Store({
                    id: "employeeDataStore",
                    url: '${createLink(controller: 'employee',action: 'employeeJsonData')}',
                    reader: new Ext.data.JsonReader({
                        root: 'employees',
                        totalProperty: 'totalCount',
                        id: 'id'
                    },[
                        {name: 'id', type: 'int', mapping: 'id'},
                        {name: 'userCode', type: 'string', mapping: 'userCode'},
                        {name: 'active', type: 'boolean', mapping: 'active'},
                        {name: 'fullName', type: 'string', mapping: 'fullName'},
                        {name: 'designation', type: 'string', mapping: 'designation'},
                        {name: 'salary', type: 'string', mapping: 'salary'}
                    ]),
                    autoLoad : true
                })




                checkBoxSelectionModel= new Ext.grid.CheckboxSelectionModel();
                employeeGrid = new Ext.grid.EditorGridPanel({
                    id: "userListingEditorGrid",
                    store : employeeDataStore,
                    selModel : checkBoxSelectionModel,
                    clicksToEdit: 2,
                    renderTo: "list_employee",
                    width: 930,
                    height: 339,
                    columns : [
                        {
                            dataIndex: 'id',
                            header: 'ID',
                            width: 30
                        },{
                            dataIndex: 'userCode',
                            header: 'Username',
                            sortable: true,
                            width:120,
                            editable: false
                        },{
                            dataIndex: 'fullName',
                            header: 'Full Name',
                            sortable: true,
                            width:200,
                            editable: false
                        },
                        {
                            dataIndex: 'designation',
                            header: 'Designation',
                            sortable: true,
                            width:200,
                            editable: false
                        },
                        {
                            dataIndex: 'salary',
                            header: 'Salary',
                            sortable: true,
                            width:80,
                            editable: false
                        },{
                            dataIndex: 'active',
                            header: 'Active',
                            sortable: true,
                            width: 70
                        },{
                            header:'Action',
                            dataIndex: 'action',
                            width: 100,
                            renderer: function(v, p, record) {

                                %{-- later on add condition if the user is authorized to do the actioon--}%
                                var renderText  = "";



                                renderText = renderText + " <a href='${createLink(controller: 'employee',action: "showEmployeeDetails")}/"+record.get('id')+"'><img title='Show' src='${resource(dir:'images',file: 'show.png')}'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";


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
                        store : employeeDataStore,
                        pageSize : 10,
                        displayInfo : true,
                        displaymsg : 'Displaying {0} - {1} of {2}',
                        emptyMsg : "No records found"
                    }),

                    tbar: [{
                        xtype: "button",
                        text: "Add Employee",
                        iconCls: "addRecord" ,
                        handler:function(){
                            window.location="${createLink(controller: 'employee',action: 'createEmployee')}"
                        }
                    }]
                });
                employeeDataStore.load({params: {start: 0, limit: 10}});
            })

        </script>
    </head>
    <body>

    <content tag="rightTag">
        <div class="bread_crumbs_ui_div" style="width: 993px">
            <ul id="crumbs_ui_custom">
                <li><g:link controller="application" action="index">Dashboard</g:link></li>
                <li><g:link controller="employee" action="employeeList">Employee List</g:link></li>
            </ul>
        </div>
        <br class="clear"/>
        <g:if test="${flash.message}">
            <div class="message" role="status" style="width: 608px; margin-left: 0px;">${flash.message}</div>
            <br class="clear"/>
        </g:if>

        <div class="widget" style="width: 991px;">

            <div class="header" style="width: 991px;">
                <span><span class="ico gray user"></span>${type}</span>
            </div>

            <g:hiddenField name="username" value="${session?.user?.userCode}"></g:hiddenField>
            <div id="list_employee" class="content scaffold-list" role="main">

                <div id="search" style="margin-bottom: 13px;">
                    <label for="userCode" title="Enter user code to search employee">
                        User Code:
                        <g:textField name="userCode" id="userCode"></g:textField>
                    </label>

                    <label for="designation" title="Enter designation to search employee according designation">
                        Designation:
                        <g:textField name="designation" id="designation"></g:textField>
                    </label>

                    <label for="active" title="Enter true or false to search employee according their active status">
                        Active:
                        <g:textField name="active" id="active"></g:textField>
                    </label>

                    <label for="firstName" title="Enter first name to search employee">
                        First Name:
                        <g:textField name="firstName" id="firstName"></g:textField>
                    </label>

                    <label for="searchButton" title="Click to search" style="margin-top: 5px">
                        <a id="searchButton" class="button icon approve">Search</a>
                    </label>

                </div>

            </div>
        </div>

    </content>

    </body>
</g:applyLayout>

