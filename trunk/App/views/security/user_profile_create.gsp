<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/20/12
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.jabait.security.UserProfile" %>
<%@ page import="com.jabait.security.Email" %>
<%@ page import="com.jabait.security.Name" %>
<%@ page import="com.jabait.security.Address" %>
<%@ page contentType="text/html;charset=UTF-8" %>

%{--<g:javascript>--}%
    %{--$(document).ready(function(){--}%
        %{--$("#dateOfBirth").datepicker({--}%
            %{--dateFormat: 'dd-mm-yy',--}%
            %{--showOn: "button",--}%
            %{--buttonImage: "${resource(dir: 'images', file: 'ico-calendar.png')}",--}%
            %{--buttonImageOnly: true,--}%
            %{--changeMonth: true--}%
        %{--});--}%
    %{--})--}%
%{--</g:javascript>--}%


<g:applyLayout name="app">
    <head>
        <g:set var="entityName" value="${message(code:'user.description', default: 'User')}"/>
        <title><g:message code="default.create.label" args="[entityName]"/></title>

        <script type="text/javascript" src="../js/jscal2.js"></script>
        <script type="text/javascript" src="../js/en.js"></script>

        <link rel="stylesheet" type="text/css" href="../css/border-radius.css">
        <link rel="stylesheet" type="text/css" href="../css/jscal2.css">
        <link rel="stylesheet" type="text/css" href="../css/steel.css">

    </head>
    <body>
        <content tag="bannerTag">
            <h1>User Profile</h1>
        </content>
        <content tag="rightTag">

            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <g:form name="userProfileForm" controller="security" action="saveUserProfile" method="POST">
                <fieldset class="form">
                    <fieldset class="user-profile-fieldset">
                        <g:hiddenField name="ownerId" value="${userProfileInstance?.owner}" />

                        <legend class="legend-color">Name</legend>
                        <div class="fieldcontain ${hasErrors(bean: userProfileInstance, field: 'firstName', 'error')} required">
                            <label for="firstName">
                                <g:message code="name.firstName.label" default="First Name" />
                                <span class="required-indicator">*</span>
                            </label>
                            <g:textField name="firstName" required="" value="${userProfileInstance?.name?.firstName}"/>
                        </div>
                        <div class="fieldcontain ${hasErrors(bean: nameInstance, field: 'middleName', 'error')}">
                            <label for="middleName">
                                <g:message code="name.middleName.label" default="Middle Name" />
                            </label>
                            <g:textField name="middleName" required="" value="${userProfileInstance?.name?.firstName}"/>
                        </div>
                        <div class="fieldcontain ${hasErrors(bean: nameInstance, field: 'surname', 'error')}">
                            <label for="surname">
                                <g:message code="name.surname.label" default="Surname" />
                            </label>
                            <g:textField name="surname" required="" value="${userProfileInstance?.name?.surname}"/>
                        </div>
                        <div class="fieldcontain ${hasErrors(bean: nameInstance, field: 'nicname', 'error')} required">
                            <label for="nickName">
                                <g:message code="name.nickName.label" default="Nick Name" />

                            </label>
                            <g:textField name="nickName" required="" value="${userProfileInstance?.name?.nickname}"/>
                        </div>
                        <br class="clear" />
                    </fieldset>
                    <br class="clear" />
                    <%-- name fieldset--%>

                    <%-- email fieldset--%>
                    <fieldset class="user-profile-fieldset">
                        <legend class="legend-color">Email Address</legend>
                        <div class="fieldcontain ${hasErrors(bean: emailInstance, field: 'emailAddress', 'error')} required">
                            <label for="address">
                                <g:message code="email.address.label" default="Email Address" />
                                <span class="required-indicator">*</span>
                            </label>
                            <g:textField name="emailAddress" required="" value="${userProfileInstance?.emailAddress?.address}"/>
                        </div>
                        <br class="clear" />
                    </fieldset>
                    <br class="clear" />
                    <%-- email fieldset--%>

                    <%-- miscellaneous fieldset--%>
                    <fieldset class="user-profile-fieldset">
                        <legend class="legend-color">Miscellaneous Information</legend>
                        <div class="fieldcontain ${hasErrors(bean: userProfileInstance, field: 'gender', 'error')} required">
                            <label for="gender">
                                <g:message code="userProfile.gender.label" default="Gender" />
                                <span class="required-indicator">*</span>
                            </label>
                            <g:textField name="gender" required="" value="${userProfileInstance?.gender}"/>
                        </div>

                        <div class="fieldcontain ${hasErrors(bean: userProfileInstance, field: 'dateOfBirth', 'error')} required">
                            <label for="dateOfBirth">
                                <g:message code="userProfile.dateOfBirth.label" default="Date of Birth" />
                                <span class="required-indicator">*</span>
                            </label>
                            <g:textField id="dateOfBirth" name="dateOfBirth" value="${userProfileInstance?.dateOfBirth}" />
                            <img src="../images/calendar/calendar_add.png" alt="calender" id="showCalendar">
                        </div>


                        <div class="fieldcontain ${hasErrors(bean: userProfileInstance, field: 'title', 'error')} required">
                            <label for="title">
                                <g:message code="userProfile.title.label" default="Title" />
                                <span class="required-indicator">*</span>
                            </label>
                            <g:textField name="title" required="" value="${userProfileInstance?.title}"/>
                        </div>
                        <br class="clear" />
                    </fieldset>
                    <br class="clear" />
                    <%-- miscellaneous fieldset--%>

                    <script type="text/javascript">
                        //<![CDATA[
                        Calendar.setup({
                            inputField : "dateOfBirth",
                            trigger    : "showCalendar",
                            onSelect   : function() { this.hide() },
                            showTime   : 12,
                            dateFormat : "%d/%m/%Y %H:%m:%S"//this is a timestamp format
                        });
                        //]]>
                    </script>

                    <%-- address fieldset--%>
                    <fieldset class="user-profile-fieldset">
                        <legend class="legend-color">Address</legend>
                        <div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'country', 'error')} required">
                            <label for="country">
                                <g:message code="adress.gender.label" default="Country" />
                                <span class="required-indicator">*</span>
                            </label>
                            <g:textField name="country" required="" value="${userProfileInstance?.address?.country}"/>
                        </div>

                        <div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'extendedAddress', 'error')}">
                            <label for="extendedAddress">
                                <g:message code="adress.extendedAddress.label" default="Extended Address" />
                            </label>
                            <g:textField name="extendedAddress" required="" value="${userProfileInstance?.address?.extendedAddress}"/>
                        </div>

                        <div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'poBox', 'error')}">
                            <label for="poBox">
                                <g:message code="adress.poBox.label" default="PO Box" />
                            </label>
                            <g:textField name="poBox" required="" value="${userProfileInstance?.address?.poBox}"/>
                        </div>

                        <div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'postalCode', 'error')}">
                            <label for="postalCode">
                                <g:message code="adress.postalCode.label" default="Postal Code" />
                            </label>
                            <g:textField name="postalCode" required="" value="${userProfileInstance?.address?.postalCode}"/>
                        </div>

                        <div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'region', 'error')}">
                            <label for="region">
                                <g:message code="adress.region.label" default="Region" />
                            </label>
                            <g:textField name="region" required="" value="${userProfileInstance?.address?.region}"/>
                        </div>

                        <div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'streetAddress', 'error')}">
                            <label for="streetAddress">
                                <g:message code="adress.streetAddress.label" default="Street Address" />
                            </label>
                            <g:textField name="streetAddress" required="" value="${userProfileInstance?.address?.streetAddress}"/>
                        </div>
                        <br class="clear" />
                    </fieldset>
                    <br class="clear" />
                </fieldset>
                <br class="clear"/>
                <div class="grid_12">
                    <div class="grid_7 alpha">
                        &nbsp;&nbsp;
                    </div>
                    <fieldset class="grid_4 omega">
                        <g:submitButton name="create" class="button save" value="${message(code: 'default.button.save.label', default: 'Save')}"/>
                        <input class="button reset" type="reset" value="Clear"/>
                    </fieldset>
                </div>

            </g:form>
        </content>
    </body>
</g:applyLayout>