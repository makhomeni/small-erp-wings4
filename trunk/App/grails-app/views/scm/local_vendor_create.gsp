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


            function submitForm(){
                document.organizationForm.submit();
            }


            Ext.onReady(function(){

            });
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

        <style>
        div.selector{
            margin-top: -20px;
            margin-left: 156px;
        }
        </style>
        <script>
            $(document).ready(function(){
                $("input, textarea, select, button").uniform();
            })
        </script>

        <div class="bread_crumbs_ui_div" style="width: 611px">
            <ul id="crumbs_ui_custom">
                <li><g:link controller="application" action="index">Dashboard</g:link></li>
                %{--<li><g:link controller="configuration" action="jobConfigHome">Job Config Home</g:link></li>--}%
                <li><g:link controller="configuration" action="organizationList">Organization List</g:link></li>
                <li>Create Vendor </li>
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
            <g:form name="vendorForm" id="vendorForm" controller="inventory" action="saveVendor" method="POST">
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
                        <label for="organization">
                            <g:message code="organization.label" default="Organization" />
                            <span class="required-indicator">*</span>
                        </label>

                        <g:textField name="organization" required=""  value="" />
                        <a id="organizationSelect" href="#">
                            <img src="${resource(dir: 'images', file: 'organization_picker.png')}" alt="Organization Image" onclick="organizationWindow.show()" class="adjustImg" title="Click to select organization">
                        </a>
                    </div>


                    <div class="fieldcontain">
                        <label for="billingAddress">
                            Billing Address
                            <span class="required-indicator">*</span>
                        </label>

                        <g:textField name="billingAddress" required=""  value="" />

                    </div>

                    <div class="fieldcontain">
                        <label for="mobileNo">
                            Mobile No
                            <span class="required-indicator">*</span>
                        </label>

                        <g:textField name="mobileNo" required=""  value="" />

                    </div>



                    <div class="fieldcontain">
                        <label for="description">
                            Description
                            <span class="required-indicator">*</span>
                        </label>
                        %{--
                          <g:if test="${userInstance.userCode}">
                            </g:if>
                            <g:else>
                            </g:else>
                        --}%

                        <g:textField name="description" required=""  value="" />

                    </div>


                    <div class="fieldcontain">
                        <label for="emailId">
                            Email Id
                            <span class="required-indicator">*</span>
                        </label>

                        <g:textField name="emailId" required=""  value="" />

                    </div>


                    <div class="fieldcontain">
                        <label for="phoneNo">
                            Phone No
                            <span class="required-indicator">*</span>
                        </label>
                        <g:textField name="phoneNo" required=""  value="" />

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