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
    <g:set var="entityName" value="${message(code:'vendor.description', default: 'Vendor')}"/>
    <title><g:message code="vendor.label" args="[entityName]"/></title>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'livevalidation_standalone.compressed.js')}">

    </script>

    <script type="text/javascript">

        $(document).ready(function(){
            var liveValidation = new LiveValidation('confirmPassword');
//                alert(liveValidation)
            liveValidation.add( Validate.Confirmation, { match: 'password' } );
        })
        function submitForm(){
            document.organizationForm.submit();
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
<div class="widget">

    <div class="header" style="width: 610px;">
        <span><span class="ico gray organization"></span>${type}</span>
    </div>

    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:form name="vendorForm" id="vendorForm" controller="inventory" action="saveVendor" method="POST">
        <fieldset class="form">
            %{--<g:render template="organization_form"/>--}%
            <div class="fieldcontain ${hasErrors(bean: vendorInstance, field: 'firstName', 'error')} required">
                <label for="firstName">
                    <g:message code="firstName.label" default="First Name" />
                    <span class="required-indicator">*</span>
                </label>

                <g:textField name="firstName" required=""  value="" />

            </div>



            <div class="fieldcontain ${hasErrors(bean: vendorInstance, field: 'lastName', 'error')} required">
                <label for="lastName">
                    <g:message code="lastName.label" default="Last Name" />
                    <span class="required-indicator">*</span>
                </label>

                <g:textField name="lastName" required=""  value="" />

            </div>


            <div class="fieldcontain ${hasErrors(bean: vendorInstance, field: 'organization', 'error')} required">
                <label for="organization">
                    <g:message code="organization.label" default="Organization" />
                    <span class="required-indicator">*</span>
                </label>

                <g:textField name="organization" required=""  value="" />

            </div>


            <div class="fieldcontain ${hasErrors(bean: vendorInstance, field: 'billingAddress', 'error')} required">
                <label for="billingAddress">
                    <g:message code="billingAddress.label" default="Billing Address" />
                    <span class="required-indicator">*</span>
                </label>
                %{--
                  <g:if test="${userInstance.userCode}">
                    </g:if>
                    <g:else>
                    </g:else>
                --}%

                <g:textField name="billingAddress" required=""  value="" />

            </div>

            <div class="fieldcontain ${hasErrors(bean: vendorInstance, field: 'mobileNo', 'error')} required">
                <label for="mobileNo">
                    <g:message code="mobileNo.label" default="Mobile No" />
                    <span class="required-indicator">*</span>
                </label>
                %{--
                  <g:if test="${userInstance.userCode}">
                    </g:if>
                    <g:else>
                    </g:else>
                --}%

                <g:textField name="mobileNo" required=""  value="" />

            </div>



            <div class="fieldcontain ${hasErrors(bean: vendorInstance, field: 'description', 'error')} required">
                <label for="description">
                    <g:message code="description.label" default="Description" />
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


            <div class="fieldcontain ${hasErrors(bean: vendorInstance, field: 'emailId', 'error')} required">
                <label for="emailId">
                    <g:message code="emailId.label" default="Email Id" />
                    <span class="required-indicator">*</span>
                </label>
                %{--
                  <g:if test="${userInstance.userCode}">
                    </g:if>
                    <g:else>
                    </g:else>
                --}%

                <g:textField name="emailId" required=""  value="" />

            </div>


            <div class="fieldcontain ${hasErrors(bean: vendorInstance, field: 'phoneNo', 'error')} required">
                <label for="phoneNo">
                    <g:message code="phoneNo.label" default="Phone No" />
                    <span class="required-indicator">*</span>
                </label>
                %{--
                  <g:if test="${userInstance.userCode}">
                    </g:if>
                    <g:else>
                    </g:else>
                --}%

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