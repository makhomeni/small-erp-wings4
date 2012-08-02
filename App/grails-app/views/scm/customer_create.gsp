<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 8/1/12
  Time: 10:12 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>

<g:applyLayout name="app">
    <head>

        <title>Create Customer</title>
        <script type="text/javascript" src="${resource(dir: 'js', file: 'livevalidation_standalone.compressed.js')}">

        </script>

        <script type="text/javascript">

            function submitForm(){
                document.customerForm.submit();
            }
        </script>

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

        <div class="bread_crumbs_ui_div" style="width: 611px">
            <ul id="crumbs_ui_custom">
                <li><g:link controller="application" action="index">Dashboard</g:link></li>
                <li><g:link controller="configuration" action="organizationList">Customer List</g:link></li>
                <li>Create Customer </li>
            </ul>
        </div>
        <br class="clear" />
        <br class="clear" />

        <div class="widget">

            <div class="header" style="width: 610px;">
                <span><span class="ico gray organization"></span>${type}</span>
            </div>

            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:form name="customerForm" id="customerForm" controller="inventory" action="saveCustomer" method="POST">
                <fieldset class="form">

                    <div class="fieldcontain">
                        <label for="firstName">
                            First Name
                            <span class="required-indicator">*</span>
                        </label>

                        <g:textField name="firstName" required=""  value="" />

                    </div>



                    <div class="fieldcontain">
                        <label for="lastName">
                            Last Name
                            <span class="required-indicator">*</span>
                        </label>

                        <g:textField name="lastName" required=""  value="" />

                    </div>


                    <div class="fieldcontain">
                        <label for="emailId">
                            Email Id
                            <span class="required-indicator">*</span>
                        </label>

                        <g:textField name="emailId" required=""  value="" />

                    </div>

                    <div class="fieldcontain">
                    <div class="grid_2" style="padding-top: 0px;">
                        <label for="organization">
                            Organization
                            <span class="required-indicator">*</span>
                        </label>
                    </div>

                    <div class="grid_3">

                        <g:select name="organization" id="organization" from="${['A+', 'A-', 'AB+']}"/>
                    </div>
                    </div>


                    <div class="fieldcontain">
                        <label for="mobileNo">
                            Mobile No
                            <span class="required-indicator">*</span>
                        </label>

                        <g:textField name="mobileNo" required=""  value="" />

                    </div>

                    <div class="fieldcontain">
                        <label for="phoneNumber">
                            Phone Number
                            <span class="required-indicator">*</span>
                        </label>

                        <g:textField name="phoneNumber" required=""  value="" />

                    </div>



                    <div class="fieldcontain">
                        <label for="address">
                            Address
                            <span class="required-indicator">*</span>
                        </label>

                        <g:textField name="address" required=""  value="" />

                    </div>


                    <div class="fieldcontain">
                        <label for="contact">
                            Contact
                            <span class="required-indicator">*</span>
                        </label>

                        <g:textField name="contact" required=""  value="" />

                    </div>


                    <div class="fieldcontain">
                        <div class="grid_2" style="padding-top: 0px;">
                            <label for="reference">
                                Reference
                                <span class="required-indicator">*</span>
                            </label>
                        </div>

                        <div class="grid_3">

                            <g:select name="reference" id="reference" from="${['A+', 'A-', 'AB+']}"/>
                        </div>

                    </div>

                    <div class="fieldcontain">
                        <label for="billingAddress">
                            billingAddress
                            <span class="required-indicator">*</span>
                        </label>
                        <g:textField name="billingAddress" required=""  value="" />

                    </div>



                    <div class="grid_4 alpha">&nbsp;&nbsp;</div>

                    <div class="grid_4 omega" style="padding-left: 194px;padding-top: 15px">



                        <a id="customerForm" class="button icon approve" onclick="submitForm()">Save</a>
                        <a id="ff" class="button danger icon remove" onclick="document.customerForm.reset()">Clear</a>

                    </div>

                </fieldset>
                <br class="clear"/>
            </g:form>
        </div>
    </content>
    </body>
</g:applyLayout>