<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/20/12
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.jabait.security.UserProfile"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<g:applyLayout name="app">
    <head>
        <g:set var="entityName" value="${message(code:'userProfile.description', default: 'User Profile')}"/>
        <title><g:message code="default.show.label" args="[entityName]"/></title>
    </head>

    <body>
    <content tag="bannerTag">
        <h1>Welcome to OceanERP</h1>
    </content>
    <content tag="rightTag">
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <fieldset class="user-profile-fieldset">

            <legend class="legend-color">Name</legend>


            <div class="fieldcontain ${hasErrors(bean: userProfileInstance, field: 'name', 'error')} required">
            <label for="firstName">
                <g:message code="name.firstName.label" default="First Name" />
            </label>
            ${userProfileInstance?.name?.firstName}

            </div>

            <div class="fieldcontain ${hasErrors(bean: userProfileInstance, field: 'middleName', 'error')}">

                <label for="middleName">
                    <g:message code="name.middleName.label" default="Middle Name" />
                </label>
                ${userProfileInstance?.name?.middleName}
            </div>

        <div class="fieldcontain ${hasErrors(bean: userProfileInstance, field: 'surname', 'error')}">
            <label for="surname">
                <g:message code="name.surname.label" default="Surname" />
            </label>
            ${userProfileInstance?.name?.surname}
        </div>
        <div class="fieldcontain ${hasErrors(bean: userProfileInstance, field: 'nicname', 'error')} required">
            <label for="nickName">
                <g:message code="name.nickName.label" default="Nick Name" />

            </label>
            ${userProfileInstance?.name?.nickname}
        </div>
        <br class="clear" />

    </fieldset>

        <br class="clear" />
        %{--for email--}%
        <fieldset class="user-profile-fieldset">
        <legend class="legend-color">Email</legend>
        <div class="fieldcontain ${hasErrors(bean: userProfileInstance, field: 'nicname', 'error')} required">
            <label for="emailAddress">
                <g:message code="name.nickName.label" default="Email Address" />

            </label>
            ${userProfileInstance?.emailAddress?.address}
        </div>
        <br class="clear" />
    </fieldset>
        <br class="clear" />

        %{--miscleniuos info--}%
        <fieldset class="user-profile-fieldset">
            <legend class="legend-color">Miscellaneous Information</legend>
            <div class="fieldcontain ${hasErrors(bean: userProfileInstance, field: 'gender', 'error')} required">
                <label for="gender">
                    <g:message code="userProfile.gender.label" default="Gender" />

                </label>
                ${userProfileInstance?.gender}
            </div>

            <div class="fieldcontain ${hasErrors(bean: userProfileInstance, field: 'dateOfBirth', 'error')} required">
                <label for="dateOfBirth">
                    <g:message code="userProfile.dateOfBirth.label" default="Date of Birth" />

                </label>
                ${userProfileInstance?.dateOfBirth}
            </div>

            <div class="fieldcontain ${hasErrors(bean: userProfileInstance, field: 'title', 'error')} required">
                <label for="title">
                    <g:message code="userProfile.title.label" default="Title" />

                </label>
                ${userProfileInstance?.title}
            </div>
            <br class="clear" />
        </fieldset>
        <br class="clear" />
        %{--miscleniuos info end--}%

        <%-- address fieldset--%>
        <fieldset class="user-profile-fieldset">
            <legend class="legend-color">Address</legend>
            <div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'country', 'error')} required">
                <label for="country">
                    <g:message code="adress.gender.label" default="Country" />

                </label>
                ${userProfileInstance?.address?.country}
            </div>

            <div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'extendedAddress', 'error')}">
                <label for="extendedAddress">
                    <g:message code="adress.extendedAddress.label" default="Extended Address" />
                </label>
                ${userProfileInstance?.address?.extendedAddress}
            </div>

            <div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'poBox', 'error')}">
                <label for="poBox">
                    <g:message code="adress.poBox.label" default="PO Box" />
                </label>
                ${userProfileInstance?.address?.poBox}
            </div>

            <div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'postalCode', 'error')}">
                <label for="postalCode">
                    <g:message code="adress.postalCode.label" default="Postal Code" />
                </label>
                ${userProfileInstance?.address?.postalCode}
            </div>

            <div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'region', 'error')}">
                <label for="region">
                    <g:message code="adress.region.label" default="Region" />
                </label>
                ${userProfileInstance?.address?.region}
            </div>

            <div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'streetAddress', 'error')}">
                <label for="streetAddress">
                    <g:message code="adress.streetAddress.label" default="Street Address" />
                </label>
                ${userProfileInstance?.address?.streetAddress}
            </div>
            <br class="clear" />
        </fieldset>
        <br class="clear" />
        <%-- address fieldset--%>

    </content>
    </body>

</g:applyLayout>