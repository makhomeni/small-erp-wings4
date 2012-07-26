<%--
  Created by IntelliJ IDEA.
  User: Masum
  Date: 6/4/12
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.jabait.hrm.time.AttendanceAdjustment"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<g:applyLayout name="app">
    <head>
        <g:set var="entityName" value="${message(code:'configuration.description', default: 'Adjustment Threshold')}"/>
        <title><g:message code="thresholdType.label" args="[entityName]"/></title>
        <script type="text/javascript" src="${resource(dir: 'js', file: 'livevalidation_standalone.compressed.js')}">

        </script>

        <script type="text/javascript">

            function submitForm(){
                document.adjustmentThresholdForm.submit();
            }
        </script>

    </head>
    <body>

    <content tag="rightTag">

        <script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.uniform.js')}" ></script>
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'uniform.aristo.css')}">

        <div class="bread_crumbs_ui_div" style="width: 611px">
            <ul id="crumbs_ui_custom">
                <li><g:link controller="application" action="index">Dashboard</g:link></li>
                <li><g:link controller="configuration" action="attendanceConfigHome">Attendance Index</g:link></li>
                <li><g:link controller="attendance" action="attendanceAdjustmentList">Attendance Adjustment List</g:link></li>
                <li>Create Late Threshold</li>
            </ul>
        </div>
        <br class="clear"/>

        <div class="widget">

            <div class="header" style="width: 610px;">
                <span><span class="ico gray attendance"></span>${type}</span>
            </div>

            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:form name="adjustmentThresholdForm" id="adjustmentThresholdForm" controller="attendance" action="saveAdjustmentThreshold" method="POST">
                <fieldset class="lateThreshold">
                    <div class="grid_8">
                        <div class="grid_4 alpha">
                            <label for="lateThreshold">
                                <g:message code="lateThreshold.label" default="Adjustment Type" />
                                %{--<span class="required-indicator">*</span>--}%
                            </label>
                        </div>

                        <div class="grid_4 omega">
                            <select name="adjustmentType">
                                <option value="late">Late</option>

                                <option value="absent">Absent</option>
                                <option value="leave">Leave</option>
                            </select>
                        </div>
                    </div>



                    <div class="fieldcontain ${hasErrors(bean: adjustmentThresholdInstance, field: 'adjustmentThreshold', 'error')} required">
                        <label for="lateThreshold">
                            <g:message code="lateThreshold.label" default="Adjustment Threshold" />
                            <span class="required-indicator">*</span>
                        </label>

                        <g:textField name="adjustmentThreshold" required=""  value="" />

                    </div>


                    <div class="grid_4 alpha">&nbsp;&nbsp;</div>

                    <div class="grid_4 omega" style="padding-left: 194px;padding-top: 15px">

                        <a id="thresholdType" class="button icon approve" onclick="submitForm()">Save</a>
                        <a id="ff" class="button danger icon remove" onclick="document.adjustmentThresholdForm.reset()">Clear</a>

                    </div>

                </fieldset>
                <br class="clear"/>

            </g:form>
        </div>

        <script type="text/javascript">
            $(document).ready(function(){
                $("input, textarea, select, button").uniform();
            })
        </script>
    </content>
    </body>
</g:applyLayout>