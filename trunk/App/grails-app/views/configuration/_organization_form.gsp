<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/20/12
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.jabait.hrm.Organization" %>
<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'organizationName', 'error')} required">
    <label for="organizationName">
        Organization Name
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
    <label for="organization">
       Parent Organization
        <span class="required-indicator">*</span>
    </label>
    <select name="organization" id="organization" style="width: 50%" from="${grailsApplication.controllerClasses.size()}">
        <g:each var="controllerName" in="${grailsApplication.controllerClasses}">
            <option >${controllerName.logicalPropertyName}</option>
        </g:each>
    </select>

</div>


<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'country', 'error')} required">
    <label for="country">
        Country
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


<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'extendedAddress', 'error')} required">
    <label for="country">
        Extended Address
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

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'poBox', 'error')} required">
    <label for="poBox">
        Po.Box
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



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'postalCode', 'error')} required">
    <label for="poBox">
       Postal Code
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


<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'region', 'error')} required">
    <label for="region">
        Region
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


<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'streetAddress', 'error')} required">
    <label for="streetAddress">
        Street Address
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
       E-mail
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
        Phone
        <span class="required-indicator">*</span>
    </label>
    <g:passwordField name="phone" required="" value=""/>
</div>



