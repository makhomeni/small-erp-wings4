<%--
  Created by IntelliJ IDEA.
  User: masum
  Date: 6/26/12
  Time: 2:46 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.jabait.hrm.payroll.Frequency; com.jabait.hrm.payroll.AllowanceType"%>
<g:applyLayout name="app">
    <head>

        <title>${type}</title>
        <script type="text/javascript" src="${resource(dir: 'js', file: 'livevalidation_standalone.compressed.js')}">

        </script>

        <script type="text/javascript" src="${resource(dir: 'js', file: 'jscal2.js')}"></script>
        <script type="text/javascript" src="${resource(dir: 'js', file: 'en.js')}"></script>

        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'border-radius.css')}"/>
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'jscal2.css')}"/>
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'steel.css')}"/>

        <script type="text/javascript">

            function submitForm(){
                document.allowanceForm.submit();
            }
        </script>

    </head>
    <body>
    <content tag="bannerTag">
        <h1>${type}</h1>
    </content>
    <content tag="rightTag">

        <script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.uniform.js')}" ></script>
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'uniform.aristo.css')}">

        <div class="bread_crumbs_ui_div" style="width: 611px">
            <ul id="crumbs_ui_custom">
                <li><g:link controller="application" action="index">Dashboard</g:link></li>
                %{--<li><g:link controller="configuration" action="jobConfigHome">Job Config Home</g:link></li>--}%
                <li><g:link controller="configuration" action="allowanceTypeList">Allowance List</g:link></li>
                <li>Create Allowance </li>
            </ul>
        </div>
        <br class="clear"/>

        <div class="widget">

            <div class="header" style="width: 610px;">
                <span><span class="ico gray jobTitle"></span>${type}</span>
            </div>

            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:form name="allowanceForm" id="allowanceForm" controller="configuration" action="saveAllowanceType" method="POST">
                <fieldset class="form">
                    %{--<g:render template="organization_form"/>--}%
                    <div class="fieldcontain ${hasErrors(bean: allowance, field: 'allowanceName', 'error')} required">
                        <label for="allowanceName">
                            Allowance Name
                            <span class="required-indicator">*</span>
                        </label>


                        <g:textField name="allowanceName" required=""  value="" />

                    </div>






                    <div class="fieldcontain ${hasErrors(bean: allowance, field: 'allowanceDescription', 'error')} required">
                        <label for="allowanceDescription">
                            Allowance Description
                            <span class="required-indicator">*</span>
                        </label>

                        <g:textField name="allowanceDescription" required=""  value="" />

                    </div>






                    <div class="grid_4 alpha">&nbsp;&nbsp;</div>
                    <div class="grid_4 omega" style="padding-left: 194px;padding-top: 15px">

                        <a id="allowane" class="button icon approve" onclick="submitForm()">Save</a>
                        <a id="ff" class="button danger icon remove" onclick="document.allowaneForm.reset()">Clear</a>

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