<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/19/12
  Time: 3:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="grails.converters.JSON; com.jabait.hrm.time.AttendanceAdjustment"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<g:applyLayout name="app">

<head>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'jscal2.js')}"></script>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'en.js')}"></script>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'exporter.js')}"></script>

    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'border-radius.css')}"/>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'jscal2.css')}"/>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'steel.css')}"/>
    <style>
        .excel{

            background-image: url(../images/page_excel.png) !important;
        }
    </style>

    <title>${type}</title>
    <script>

        var attendanceDataStore;
        var checkBoxSelMod;
        var departmentListingEditorGrid;
        var searchUsers;
        var linkButton;
        function exportExcel(){
            window.location="excelDataList?userCode=" + Ext.get('searchById').getValue()
                    + "&fromJobDate=" + Ext.get('fromDate').getValue()
                    + "&toJobDate=" + Ext.get('toDate').getValue()
            //if(Ext.get('toDate').getValue()!= '' && Ext.get('fromDate').getValue()!= '' && Ext.get('searchById').getValue()!=''){
            %{--if(Ext.get('searchById').getValue()==''){--}%
                %{--alert("null");--}%
                %{--Ext.Ajax.request({--}%
                    %{--url: '${createLink(controller: 'attendance',action: 'excelDataList')}',--}%

                    %{--success: function(response, opts) {--}%

                    %{--},--}%
                    %{--failure: function(response, opts) {--}%
                        %{--// alert(response)--}%
                    %{--}--}%
                %{--})--}%

            %{--}--}%
            %{--else{--}%
//                alert("not null");
                %{--Ext.Ajax.request({--}%
                    %{--url: '${createLink(controller: 'attendance',action: 'excelDataList')}',--}%
                    %{--params: {--}%
                        %{--userCode : Ext.get('searchById').getValue(),--}%
                        %{--fromJobDate:Ext.get('fromDate').getValue(),--}%
                        %{--toJobDate:Ext.get('toDate').getValue()--}%
                    %{--},--}%
                    %{--success: function(response, opts) {--}%
                        %{--alert("You have Exported");--}%

                    %{--},--}%
                    %{--failure: function(response, opts) {--}%
                        %{--// alert(response)--}%
                    %{--}--}%
                %{--})--}%
//            }
//            }
           %{--// else if(Ext.get('toDate').getValue()!= '' && Ext.get('fromDate').getValue()!= ''){--}%
                %{--Ext.Ajax.request({--}%
                    %{--url: '${createLink(controller: 'attendance',action: 'excelDataList')}',--}%
                    %{--params: {--}%

                        %{--jobDate:Ext.get('fromDate').getValue(),--}%
                        %{--jobDate:Ext.get('toDate').getValue()--}%
                    %{--},--}%
                    %{--success: function(response, opts) {--}%

                    %{--},--}%
                    %{--failure: function(response, opts) {--}%
                        %{--// alert(response)--}%
                    %{--}--}%
                %{--})--}%

            %{--}--}%
            %{--else if(Ext.get('searchById').getValue()!=''){--}%
                %{--Ext.Ajax.request({--}%
                    %{--url: '${createLink(controller: 'attendance',action: 'excelDataList')}',--}%
                    %{--params: {--}%
                        %{--userCode : Ext.get('searchById').getValue()--}%

                    %{--},--}%
                    %{--success: function(response, opts) {--}%

                    %{--},--}%
                    %{--failure: function(response, opts) {--}%
                        %{--// alert(response)--}%
                    %{--}--}%
                %{--})--}%

            %{--}--}%
//            else{
//
//            }


        }

        function deleteRec(id){
            alert("enter"+id);

            Ext.Msg.show({
                title:'Delete Department?',
                msg: 'You are deleting a department. Would you like to delete?',
                maxWidth:400,
                buttons: Ext.Msg.YESNOCANCEL,
                fn: function(btn){
                    if(btn == 'yes'){

                        //alert(1)

                        window.location = "${createLink(controller: 'attendance',action: 'deleteAttendanceAdjustment')}"+"/"+id;
                    }
                }
            });
        }

        Ext.onReady(function(){




            Ext.get('generalSearch').on("click",function(){


               if(Ext.get('toDate').getValue()!= '' && Ext.get('fromDate').getValue()!= '' && Ext.get('searchById').getValue()!=''){
                   // alert("test");
                    attendanceDataStore.setBaseParam('userCode', Ext.get('searchById').getValue());
                    attendanceDataStore.setBaseParam('toJobDate',Ext.get('toDate').getValue());
                    attendanceDataStore.setBaseParam('fromJobDate',Ext.get('fromDate').getValue());
                    attendanceDataStore.load({
                                param:{start:0,limit:10}
                            }

                    )
                }

               //alert("ddd");
               else if(Ext.get('toDate').getValue()!= '' && Ext.get('fromDate').getValue()!= ''){
                   //alert("Date Not NUll");
                   attendanceDataStore.setBaseParam('toJobDate',Ext.get('toDate').getValue());
                   attendanceDataStore.setBaseParam('fromJobDate',Ext.get('fromDate').getValue());
                   attendanceDataStore.load({
                               param:{start:0,limit:10}
                           }

                   )

               }
               else if( Ext.get('searchById').getValue()!=''){
                   //alert("sdsdsdd");

                   attendanceDataStore.setBaseParam('userCode', Ext.get('searchById').getValue());

                   attendanceDataStore.load({
                               param:{start:0,limit:10}
                           }

                   )


               }

                else{
                    alert("Please Enter Employee Id or From Date To Date or All")
                }

            })
            //for delete confirmation


            /////////////// start user group grid//////////////

            attendanceDataStore = new Ext.data.Store({
                id: "attendanceDataStore",
                url: '${createLink(action: 'attendanceJsonData')}',
                reader: new Ext.data.JsonReader({
                    root: 'attendances',
                    totalProperty: 'totalCount',
                    id: 'id'
                },[
                    {name: 'id', type: 'int', mapping: 'id'},
                    {name: 'inTime', type: 'string', mapping: 'inTime'},
                    {name: 'outTime', type: 'string', mapping: 'outTime'},
                    {name: 'jobDate', type: 'string', mapping: 'jobDate'},
                    {name: 'attendant', type: 'string', mapping: 'attendant'},
                    {name: 'attendanceStatus', type: 'string', mapping: 'attendanceStatus'}
                ]),
                autoLoad : true
            })

//                alert("user add feature " + userAddFeature)
            //create the grid
            //  confirmationMessage = Ext.MessageBox.confirm('Confirm', 'Do you really mean it?');
            //  var dialog = confirmationMessage.getDialog();
            // alert(dialog)


            checkBoxSelMod = new Ext.grid.CheckboxSelectionModel();
             linkButton = new Ext.Button({
                id: 'grid-excel-button',
                text: 'Export to Excel'
            });
            departmentListingEditorGrid = new Ext.grid.EditorGridPanel({
                id: "departmentListingEditorGrid",
                store : attendanceDataStore,
                selModel : checkBoxSelMod,
                viewConfig: {
                    forceFit: true,
                    getRowClass: function(record, index) {
                        if(record.get('attendanceStatus') == 'late') return 'late'
                        else if(record.get('attendanceStatus') == 'absent') return 'absent'
                        else return ''
                    }
                },
                clicksToEdit: 2,
                renderTo: "list_user",
                width: 750,
                height: 300,
                columns : [
                    {
                        dataIndex: 'inTime',
                        header: 'In Time',
                        sortable: true,
                        width:100,
                        editable: false
                    },{
                        dataIndex: 'outTime',
                        header: 'Out Time',
                        sortable: true,
                        width:100,
                        editable: false
                    },{
                        dataIndex: 'jobDate',
                        header: 'Job Date',
                        sortable: true,
                        width:145,
                        editable: false
                    },{
                        dataIndex: 'attendant',
                        header: 'Attendant',
                        sortable: true,
                        width:200,
                        editable: false
                    },{
                        header:'Action',
                        dataIndex: 'action',
                        width: 200,
                        renderer: function(v, p, record) {
                            var renderText  = "";

                            renderText = renderText + " <a href='#'><img title='Edit' src='${resource(dir:'images',file: 'edit.png')}'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

                            %{--renderText = renderText + " <a href='#'><img title='Show' src='${resource(dir:'images',file: 'show.png')}'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";--}%


                            %{--if(record.get('id') == ${session.user.id}){--}%
                                %{--renderText = renderText;--}%
                            %{--}else{--}%
                                %{--renderText += " <a id='delete' onclick='deleteRec("+record.get('id')+")' href='#' ><img title='Delete' src='${resource(dir:'images',file: 'delete.png')}'/></a>"--}%
                            %{--}--}%

                            return renderText;
                        }
                    }
                ] ,
                tbar: [{
//                    xtype: "button",
//                    text: "Export Excel Sheet",
//                    iconCls: "excel" ,
//                    handler:function(){
//                       // exportExcel();
//
//                    }
                }],


                stripeRows : false ,
                bbar: new Ext.PagingToolbar({
                    store : attendanceDataStore,
                    pageSize : 10,
                    displayInfo : true,
                    displaymsg : 'Displaying {0} - {1} of {2}',
                    emptyMsg : "No records found"
                })


            });


            function deleteUser(){
                alert();
            }


        })



    </script>
    <style type="text/css">
    .header{
        width: 950px;
    }
    .widget{
        width: 950px;
    }

    </style>
</head>
<body>
%{--<content tag="bannerTag"><h1>${type}</h1></content>--}%

<content tag="titleTag">

</content>
<content tag="rightTag">

    <div class="bread_crumbs_ui_div" style="width: 951px">
        <ul id="crumbs_ui_custom">
            <li><g:link controller="application" action="index">Dashboard</g:link></li>
            <li>Attendance List</li>
        </ul>
    </div>

    <br class="clear"/>


    <div class="widget">

        <div class="header" style="width: 950px;">
            <span><span class="ico gray attendanceList"></span>${type}</span>
        </div>

        <g:if test="${flash.message}">
            <div class="container_12">

                <div class="grid_4">

                </div>
                <div class="grid_4 message" role="status">
                    <g:message code="${flash.message}" args="${flash.args}" default="${flash.default}"></g:message>
                </div>
                <div class="clear"></div>
            </div>
        </g:if>
        <div class="clear"></div>

        <div class="clear"></div>

        <div id="list_user"  class="content scaffold-list" role="main" style="width: 900px;">
            <div id="searchArea" style="margin-bottom: 20px">
                <label>User Code:</label><g:textField name="searchById"  value=""/>
            &nbsp;&nbsp;&nbsp;
                <label>From Date</label>
                <g:textField name="fromDate"  value=""/>
                <img  src="${resource(dir: 'images/calendar', file: 'calendar_add.png')}" alt="calender" id="showCalendarfromDate" class="adjustImg" title="Click to select issue date" style="margin-bottom: -10px;">
                <script type="text/javascript">
                    //<![CDATA[
                    Calendar.setup({
                        inputField : "fromDate",
                        trigger    : "showCalendarfromDate",
                        onSelect   : function() { this.hide() },
                        showTime   : 12,
                        dateFormat : "%Y-%m-%d"//this is a timestamp format
                    });
                    //]]>
                </script>
                &nbsp;&nbsp;&nbsp;
                <label>To Date</label>
                <g:textField name="toDate"  value=""/>
                <img  src="${resource(dir: 'images/calendar', file: 'calendar_add.png')}" alt="calender" id="showCalendartoDate" class="adjustImg" title="Click to select issue date" style="margin-bottom: -10px;">
                <script type="text/javascript">
                    //<![CDATA[
                    Calendar.setup({
                        inputField : "toDate",
                        trigger    : "showCalendartoDate",
                        onSelect   : function() { this.hide() },
                        showTime   : 12,
                        dateFormat : "%Y-%m-%d"//this is a timestamp format
                    });
                    //]]>
                </script>
                <a id="generalSearch" class="button icon approve">Search</a>
                <a id="excelExport" class="button icon rss" onclick="jasperAttendanceReportGenerator()">Export</a>
            </div>
            <script>
                startDate = document.getElementById("toDate").value
                var oFormObject = document.forms['payRegisterReport'];
                oFormObject.elements["toJobDate"].value = startDate;

                function jasperAttendanceReportGenerator(){
                    if(Ext.get('searchById').getValue()!='' && Ext.get('toDate').getValue()!= '' && Ext.get('fromDate').getValue()!= ''){

                        window.location = "../report/attendanceRegisterExcelReport?userCode=" + Ext.get('searchById').getValue()
                                + "&fromJobDate=" + Ext.get('fromDate').getValue()
                                + "&toJobDate=" + Ext.get('toDate').getValue()
                                + "&_name=" + "pay_register"
                                + "&_file=" + "attendanceExcelExport.jasper"
                                + "&_format=XLS"
                    }
                    else if(Ext.get('toDate').getValue()!= '' && Ext.get('fromDate').getValue()!= ''){

                        window.location = "../report/attendanceRegisterExcelReport?userCode=" + Ext.get('searchById').getValue()
                                + "&fromJobDate=" + Ext.get('fromDate').getValue()
                                + "&toJobDate=" + Ext.get('toDate').getValue()
                                + "&_name=" + "pay_register"
                                + "&_file=" + "attendanceExcelExport.jasper"
                                + "&_format=XLS"

                    }
                    else{
                        alert("please Enter Export Criteria");
                    }


                }

            </script>
            <br class="clear"/>
        </div>
    </div>
</content>
</body>
</g:applyLayout>