<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/20/12
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.jabait.hrm.Organization"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<g:applyLayout name="app">
    <head>
        <g:set var="entityName" value="${message(code:'configuration.description', default: 'Organization')}"/>
        <title><g:message code="default.create.label" args="[entityName]"/></title>
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
            <li>Create Organization </li>
        </ul>
    </div>
        <div class="widget">

            <div class="header" style="width: 610px;">
                <span><span class="ico gray organization"></span>${type}</span>
            </div>

        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <g:form name="organizationForm" id="organizationForm" controller="configuration" action="saveOrganization" method="POST">
            <fieldset class="form">
                %{--<g:render template="organization_form"/>--}%
                <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'organizationName', 'error')} required">
                    <label for="organizationName">
                        <g:message code="organization.organizationName.label" default="Organization Name" />
                        <span class="required-indicator">*</span>
                    </label>
                    %{--
                      <g:if test="${userInstance.userCode}">
                        </g:if>
                        <g:else>
                        </g:else>
                    --}%

                    <g:textField name="organizationName" required=""  value="" />

                </div>



                <div class="fieldcontain ${hasErrors(bean: featureInstance, field: 'organization', 'error')} required">
                    <label for="module">
                        <g:message code="organization.organizationName.label" default="Parent Organization" />
                        <span class="required-indicator">*</span>
                    </label>

            <g:select id="organizationList" name="organization" from="${Organization.list()}" optionKey="id" optionValue="organizationName" noSelection="['':'Select Organization...']"></g:select>


                </div>


                <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'country', 'error')} required">
                    <label for="country">
                        <g:message code="organization.country.label" default="Country" />
                        <span class="required-indicator">*</span>
                    </label>
                    %{--
                      <g:if test="${userInstance.userCode}">
                        </g:if>
                        <g:else>
                        </g:else>
                    --}%

                    <g:textField name="country" required=""  value="" />

                </div>


                <div class="fieldcontain ${hasErrors(bean: arrdessInstance, field: 'extendedAddress', 'error')} required">
                    <label for="country">
                        <g:message code="organization.extendedAddress.label" default="Extended Address" />
                        <span class="required-indicator">*</span>
                    </label>
                    %{--
                      <g:if test="${userInstance.userCode}">
                        </g:if>
                        <g:else>
                        </g:else>
                    --}%

                    <g:textField name="extendedAddress" required=""  value="" />

                </div>

                <div class="fieldcontain ${hasErrors(bean: arrdessInstance, field: 'poBox', 'error')} required">
                    <label for="poBox">
                        <g:message code="organization.poBox.label" default="Po.Box" />
                        <span class="required-indicator">*</span>
                    </label>
                    %{--
                      <g:if test="${userInstance.userCode}">
                        </g:if>
                        <g:else>
                        </g:else>
                    --}%

                    <g:textField name="poBox" required=""  value="" />

                </div>



                <div class="fieldcontain ${hasErrors(bean: arrdessInstance, field: 'postalCode', 'error')} required">
                    <label for="postalCode">
                        <g:message code="organization.postalCode.label" default="Postal Code" />
                        <span class="required-indicator">*</span>
                    </label>
                    %{--
                      <g:if test="${userInstance.userCode}">
                        </g:if>
                        <g:else>
                        </g:else>
                    --}%

                    <g:textField name="postalCode" required=""  value="" />

                </div>


                <div class="fieldcontain ${hasErrors(bean: arrdessInstance, field: 'region', 'error')} required">
                    <label for="region">
                        <g:message code="organization.region.label" default="Region" />
                        <span class="required-indicator">*</span>
                    </label>
                    %{--
                      <g:if test="${userInstance.userCode}">
                        </g:if>
                        <g:else>
                        </g:else>
                    --}%

                    <g:textField name="region" required=""  value="" />

                </div>


                <div class="fieldcontain ${hasErrors(bean: arrdessInstance, field: 'streetAddress', 'error')} required">
                    <label for="streetAddress">
                        <g:message code="organization.streetAddress.label" default="Street Address" />
                        <span class="required-indicator">*</span>
                    </label>
                    %{--
                      <g:if test="${userInstance.userCode}">
                        </g:if>
                        <g:else>
                        </g:else>
                    --}%

                    <g:textField name="streetAddress" required=""  value="" />

                </div>


                <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'email', 'error')} required">
                    <label for="streetAddress">
                        <g:message code="organization.email.label" default="E-mail" />
                        <span class="required-indicator">*</span>
                    </label>
                    %{--
                      <g:if test="${userInstance.userCode}">
                        </g:if>
                        <g:else>
                        </g:else>
                    --}%

                    <g:textField name="email" required=""  value="" />

                </div>




                <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'phone', 'error')} required">
                    <label for="phone">
                        <g:message code="organization.phone.label" default="Phone" />
                        <span class="required-indicator">*</span>
                    </label>
                    <g:textField name="phone" required="" value=""/>
                </div>

                <div class="grid_4 alpha">&nbsp;&nbsp;</div>
                %{--<div class="grid_4 omega">--}%
                    %{--<g:submitButton name="create" class="button save" value="${message(code: 'default.button.create.label', default: 'Create')}"/>--}%
                    %{--<input class="button reset" type="reset" value="Clear" />--}%
                %{--</div>--}%
                <div class="grid_4 omega" style="padding-left: 194px;padding-top: 15px">
                    %{--<g:submitButton name="create" class="button save" value="${message(code: 'default.button.create.label', default: 'Create')}"/>--}%
                    %{--<input class="button reset" type="reset" value="Clear" />--}%



                    <a id="organizationForm" class="button icon approve" onclick="submitForm()">Save</a>
                    <a id="ff" class="button danger icon remove" onclick="document.organizationForm.reset()">Clear</a>

                </div>

            </fieldset>
            <br class="clear"/>
            %{--<fieldset class="grid_8">--}%

            %{--</fieldset>--}%
        </g:form>
            </div>
    </content>
    </body>
</g:applyLayout>