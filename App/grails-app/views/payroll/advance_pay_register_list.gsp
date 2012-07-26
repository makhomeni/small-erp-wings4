<%--
  Created by IntelliJ IDEA.
  User: Md Dablu Hossain
  Date: 2/19/12
  Time: 3:21 PM
  To change this template use File | Settings | File Templates.
--%>
%{--<%@ page import="grails.converters.JSON; com.jabait.hrm.time.AttendanceAdjustment"%>--}%
<%@ page import="grails.converters.JSON; com.jabait.hrm.payroll.PayRegister"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<g:applyLayout name="app">

<head>
<script type="text/javascript" src="${resource(dir: 'js', file: 'jscal2.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js', file: 'en.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js', file: 'exporter.js')}"></script>

<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'border-radius.css')}"/>
<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'jscal2.css')}"/>
<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'steel.css')}"/>




<title>${type}</title>
<script>

    var payRegisterDataStore;
    var checkBoxSelectionModel;
    var payRegisterEditorGrid;
    var searchUsers;
    var linkButton;
    var startDate = "";
    var isEmployee;
    var reason;
    var disApproveReason;
    var leaveRegisterId;
    var userId;
    var advanceRegisterId;


    function setFromDate(){
        document.getElementById("fromJobDate").value = document.getElementById("fromDate").value;
    }

    function setToDate(){
        document.getElementById("toJobDate").value = document.getElementById("toDate").value;
    }

    function excelExportPayroll(){
        if(Ext.get('toDate').getValue()!= '' && Ext.get('fromDate').getValue()!= ''){

            window.location="exportExcelPayRoll?userCode=" + Ext.get('searchById').getValue()
                    + "&fromJobDate=" + Ext.get('fromDate').getValue()
                    + "&toJobDate=" + Ext.get('toDate').getValue()

        }
        else{
            alert("Select From Date and To Date") ;

        }

//                + "&fromJobDate=" + Ext.get('fromDate').getValue()


    %{--window.location="${createLink(controller: "payroll",action: "exportExcelPayRoll")}"--}%

    %{--Ext.Ajax.request({--}%
    %{--url: '${createLink(controller: 'payroll',action: 'exportExcelPayRoll')}',--}%
    %{--//            params: {--}%
    %{--//                userCode : Ext.get('searchById').getValue(),--}%
    %{--//                fromJobDate:Ext.get('fromDate').getValue(),--}%
    %{--//                toJobDate:Ext.get('toDate').getValue()--}%
    %{--//            },--}%
    %{--success: function(response, opts) {--}%
    %{--alert("You have Exported");--}%

    %{--},--}%
    %{--failure: function(response, opts) {--}%
    %{--// alert(response)--}%
    %{--}--}%
    %{--})--}%
    }


    //    {
    //        if (Ext.isEmpty(loadingMessage))
    //            loadText = 'Loading... Please wait';
    ////Use the mask function on the Ext.getBody() element to mask the body element during Ajax calls
    //        Ext.Ajax.on('beforerequest',function(){Ext.getBody().mask(loadText, 'loading') }, Ext.getBody());
    //        Ext.Ajax.on('requestcomplete',Ext.getBody().unmask ,Ext.getBody());
    //        Ext.Ajax.on('requestexception', Ext.getBody().unmask , Ext.getBody());
    //    }

    function salaryProcess(){

        Ext.Ajax.request({
            url:'${createLink(controller: 'payroll',action: 'savePayRegister')}',

            params:{
                userCode:Ext.get('searchById').getValue(),
                fromJobDate:Ext.get('fromDate').getValue(),
                toJobDate: Ext.get('toDate').getValue()
//                payDate:Ext.get('fromDate').getValue()
//                Ext.get('toDate').getValue()
            },

            success:function(response,opts){
                var myMask = new Ext.LoadMask(Ext.getBody(), {msg:"Please wait..."});
                myMask.hide();
                payRegisterEditorGrid.getStore().reload();
                payRegisterEditorGrid.getView().refresh();
            },
            failure:function(response,opts){

            }
        })

    }



    Ext.onReady(function(){

//        Ext.get('generalSearch').on("click",function(){
//
//            alert("test");
//        }

        Ext.Ajax.request({
            url:'${createLink(controller: "leave", action: "isEmployee")}',
            params: {
                userCode:"${session?.user?.userCode}"
            },
            success: function(response, opts) {

                isEmployee = Ext.util.JSON.decode(response.responseText).success;
            },
            failure: function(response, opts) {
                // alert(response)
            }
        })


        payRegisterDataStore = new Ext.data.Store({
            url: '${createLink(action: 'advancePayRollJsonData')}',
            reader: new Ext.data.JsonReader({
                root: 'advancePayRegister',
                totalProperty: 'totalCount',
                id: 'id'
            },[
                {name:'id',type:'int',mapping:'id'},
                {name:'advanceRequester',type:'string',mapping:'advanceRequester'},
                {name: 'advanceBy',type:'string',mapping:'advanceBy'},
                {name: 'advanceAmount',type:'string',mapping:'advanceAmount'},
                {name: 'account',type:'string',mapping:'account'},
                {name: 'requestedDate',type:'string',mapping:'requestedDate'},
                {name:'approvedDate',type:'string',mapping:'approvedDate'} ,
                {name: 'reasonToApproveReject',type:'string',mapping:'reasonToApproveReject'},
                {name: 'status',type:'string',mapping:'status'}

            ]),
            autoLoad : true
        })







        checkBoxSelectionModel= new Ext.grid.CheckboxSelectionModel();
        payRegisterEditorGrid = new Ext.grid.EditorGridPanel({
            id: "payRegisterEditorGrid",
            store : payRegisterDataStore,
            selModel : checkBoxSelectionModel,
            clicksToEdit: 2,
            renderTo: "list_user",
            width: 950,
            height: 300,

            columns : [
                {
                    dataIndex: 'advanceRequester',
                    header: 'Requested By',
                    width: 140
                },{
                    dataIndex: 'advanceBy',
                    header: 'Authorized By',
                    sortable: true,
                    width: 140
                }
                ,{
                    dataIndex: 'advanceAmount',
                    header: 'Advance Amount',
                    sortable: true,
                    width: 100
                },{
                    dataIndex: 'account',
                    header: 'Account',
                    sortable: true,
                    width: 100
                },{
                    dataIndex: 'requestedDate',
                    header: 'Requested Date',
                    sortable: true,
                    width: 100
                } ,{
                    dataIndex: 'approvedDate',
                    header: 'Approved Date',
                    sortable: true,
                    width: 100
                } ,{
                    dataIndex: 'status',
                    header: 'Status',
                    sortable: true,
                    width:100,
                    renderer: function(v, p, record) {
                        var renderText = renderText = "<img title='Pending' src='${resource(dir:'images',file: 'pending.png')}'/>";
                        var status = record.get('status');
                        if(status == 'Approved'){
                            renderText = "<img title='Approved' src='${resource(dir:'images',file: 'approved.png')}'/>"
                        }else if(status == 'Disapproved'){
                            renderText = "<img title='Disapproved' src='${resource(dir:'images',file: 'disapprove.png')}'/>"
                        }

                        return renderText;
                    }
                } ,{
                    header:'Action',
                    dataIndex: '',
                    width: 120,
                    renderer: function(v, p, record) {

                        %{-- later on add condition if the user is authorized to do the actioon--}%

                        userId = ${session.user.id};
                        var renderText  = "";

                        if(!isEmployee){
                            renderText = renderText + " <a href='#' onclick='approveWindow.show()'><img title='Approve' src='${resource(dir:'images',file: 'accept_16.png')}'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

                        %{--alert(record.get('id')+" "+${session.user.id})--}%

                            renderText += " <a id='delete' onclick='disApproveWindow.show()' href='#' ><img title='Disapprove' src='${resource(dir:'images',file: 'reject.png')}'/></a>"


                            return renderText;
                        }else{
                            return renderText;
                        }


                    }
                }
                %{--,{--}%
                %{--header:'Action',--}%
                %{--dataIndex: '',--}%
                %{--width: 120,--}%
                %{--renderer: function(v, p, record) {--}%

                %{-- later on add condition if the user is authorized to do the actioon--}%

                %{--userId = ${session.user.id};--}%
                %{--//                        var renderText  = "";--}%

                %{--if(true){--}%
                %{--renderText = renderText + " <a href='#' onclick='approveWindow.show()'><img title='Approve' src='${resource(dir:'images',file: 'accept_16.png')}'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";--}%

                %{--alert(record.get('id')+" "+${session.user.id})--}%

                %{--renderText += " <a id='delete' onclick='disApproveWindow.show()' href='#' ><img title='Disapprove' src='${resource(dir:'images',file: 'reject.png')}'/></a>"--}%


                %{--return renderText;--}%
                %{--}else{--}%
                %{--return renderText;--}%
                %{--}--}%


                %{--}--}%
                %{--}--}%
            ],
            stripeRows : true,
            bbar: new Ext.PagingToolbar({
                store : payRegisterDataStore,
                pageSize : 10,
                displayInfo : true,
                displaymsg : 'Displaying {0} - {1} of {2}',
                emptyMsg : "No records found"
            }),

            tbar: [{
                xtype: "button",
                text: "Create Advance Payment Request",
                iconCls: "addRecord" ,
                handler:function(){
                window.location="${createLink(controller: 'payroll',action: 'createAdvancePayRequest')}"
                }
            }]
        });
        payRegisterDataStore.load({params: {start: 0, limit: 10}});


        approveForm = new Ext.form.FormPanel({
            items: [
                {
                    xtype: "textarea",
                    name:"approvedReason",
                    id:"approvedReason",
                    fieldLabel: "Reason",
                    width: 250,
                    height: 120
                }]
        });
        approveWindow = new Ext.Window({
            id: 'authorityAssignmentWindow',
            title: 'Reason',
            closable: false,
            width: 400,
            height: 200,
            plain:true,
            modal:true,
            layout: 'fit',
            resizable: false,
            items: approveForm,
            buttons:[
                {
                    text: 'Save and Close',
                    handler: function(){
                        reason =  Ext.getCmp('approvedReason').getValue();
                        var itemIndex = payRegisterEditorGrid.store.indexOf(payRegisterEditorGrid.getSelectionModel().getSelected());
                        advanceRegisterId = payRegisterEditorGrid.getStore().getAt(itemIndex).get("id");
                        approveForm.getForm().submit({
                            url:'${createLink(controller: "payroll",action: "advanceApprove")}',
                            params: {
                                userId: userId,
                                advanceRegisterId:advanceRegisterId,
                                reason: reason,
                                advanceStatus:1
                            },
                            success: function(response){
                                Ext.MessageBox.alert("Success", "Advance paid successfully");
                                payRegisterEditorGrid.getStore().reload();
                                payRegisterEditorGrid.getView().refresh();
                                approveWindow.hide();
                            },
                            failure: function(response){
                                Ext.MessageBox.alert("Failed", "Advance approved failed");
                                payRegisterEditorGrid.getStore().reload();
                                payRegisterEditorGrid.getView().refresh();
                                approveWindow.hide();
                            }
                        })
                    }
                },{
                    text: 'Cancel',
                    handler: function(){
                        approveWindow.hide();
                    }
                }
            ]
        });




        disApproveForm= new Ext.form.FormPanel({
            items: [
                {
                    xtype: "textarea",
                    fieldLabel: "Reason",
                    id:"disApprovedReason",
                    name:"disApprovedReason",
                    width: 250,
                    height: 120
                }]
        });


        disApproveWindow = new Ext.Window({
            id: 'leavedisApproveWindow',
            title: 'Reason',
            closable: false,
            width: 400,
            height: 200,
            plain:true,
            modal:true,
            layout: 'fit',
            resizable: false,
            items: disApproveForm,
            buttons:[
                {
                    text: 'Save and Close',
                    handler: function(){
                        reason = Ext.getCmp("disApprovedReason").getValue()
                        var itemIndex = payRegisterEditorGrid.store.indexOf(payRegisterEditorGrid.getSelectionModel().getSelected());
                        advanceRegisterId = payRegisterEditorGrid.getStore().getAt(itemIndex).get("id");

                        disApproveForm.getForm().submit({
                            url:'${createLink(controller: 'payroll',action: "advanceDisapprove")}',
                            params: {
                                userId: userId,
                                advanceRegisterId:advanceRegisterId,
                                reason: reason,
                                advanceStatus:2
                            },
                            success: function(response){
                                Ext.MessageBox.alert("Success", "Advance disapproved successfully");
                                payRegisterEditorGrid.getStore().reload();
                                payRegisterEditorGrid.getView().refresh();
                                disApproveWindow.hide();
                            },
                            failure: function(response){
                                Ext.MessageBox.alert("Failed", "Advance disapproved failed");
                                payRegisterEditorGrid.getStore().reload();
                                payRegisterEditorGrid.getView().refresh();
                                disApproveWindow.hide();
                            }
                        })
                    }
                },{
                    text: 'Cancel',
                    handler: function(){
                        disApproveWindow.hide();
                    }
                }
            ]
        });




    })



</script>
<style type="text/css">
.header{
    width: 990px;
}
.widget{
    width: 990px;
}

</style>
</head>
<body>
%{--<content tag="bannerTag"><h1>${type}</h1></content>--}%

<content tag="titleTag">

</content>
<content tag="rightTag">

    <div class="bread_crumbs_ui_div" style="width: 992px">
        <ul id="crumbs_ui_custom">
            <li><g:link controller="application" action="index">Dashboard</g:link></li>
            <li><g:link controller="payroll" action="home">Payroll Home</g:link></li>
            <li>Advance Pay Register List</li>
        </ul>
    </div>

    <br class="clear"/>


    <div class="widget">

        <div class="header" style="width: 990px;">
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

        <div id="list_user"  class="content scaffold-list" role="main" style="width: 940px;">
            <div id="searchArea" style="margin-bottom: 20px">
                <label>User Code:</label>
                <g:textField name="searchById"  value=""/>
                &nbsp;&nbsp;&nbsp;
                <label>From Date</label>
                <g:textField name="fromDate"  value="" onchange="setFromDate()"/>
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
                <g:textField name="toDate"  value="" onchange="setToDate()"/>
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
                <div style="margin-left: 800px;margin-top: -35px;">
                    <a id="excelExport" onclick="excelExportPayroll()" title="Generate Excel Salary Report"><img src="${resource(dir: 'images', file: 'excel.png')}" height="40px"></a>
                </div>
                <div style="margin-left: 845px;margin-top: -44px;">
                    <a id="jasperGenerator" onclick="jasperReportGenerator()" title="Generate PDF Salary Report"> <img src="${resource(dir: 'images', file: 'file_pdf.png')}" height="40px"> </a>
                </div>

            </div>
            <script>
                startDate = document.getElementById("toDate").value
                var oFormObject = document.forms['payRegisterReport'];
                oFormObject.elements["toJobDate"].value = startDate;

                function jasperReportGenerator(){
                    if(Ext.get('searchById').getValue()!='' && Ext.get('toDate').getValue()!= '' && Ext.get('fromDate').getValue()!= ''){

                        window.location = "../report/advanceVoucherRangeReport?userCode=" + Ext.get('searchById').getValue()
                                + "&fromDate=" + Ext.get('fromDate').getValue()
                                + "&toDate=" + Ext.get('toDate').getValue()
                                + "&_name=" + "pay_register"
                                + "&_file=" + "voucher.jasper"
                                + "&_format=PDF"
                    }
                    else if(Ext.get('toDate').getValue()!= '' && Ext.get('fromDate').getValue()!= ''){

                        window.location = "../report/advanceVoucherRangeReport?userCode=" + Ext.get('searchById').getValue()
                                + "&fromDate=" + Ext.get('fromDate').getValue()
                                + "&toDate=" + Ext.get('toDate').getValue()
                                + "&_name=" + "pay_register"
                                + "&_file=" + "voucher.jasper"
                                + "&_format=PDF"

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