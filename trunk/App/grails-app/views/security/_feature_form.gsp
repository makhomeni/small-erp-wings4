<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/19/12
  Time: 10:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@page import="com.jabait.security.Feature" %>

<div class="fieldcontain ${hasErrors(bean: featureInstance, field: 'module', 'error')} required">
	<label for="module">
		<g:message code="feature.module.label" default="module" />
		<span class="required-indicator">*</span>
	</label>

    <g:if test="${params.action == 'editFeature'}">
        <g:textField name="operation" required="" value="${featureInstance?.module}" readonly="readonly"/>
    </g:if>

    <g:else>
        <select name="module" id="module" style="width: 100%" from="${grailsApplication.controllerClasses.size()}">
            <g:each var="controllerName" in="${grailsApplication.controllerClasses}">
                <option >${controllerName.logicalPropertyName}</option>
            </g:each>
        </select>
    </g:else>

</div>

<div class="fieldcontain ${hasErrors(bean: featureInstance, field: 'operation', 'error')} required">
    <label for="operation">
        <g:message code="feature.operation.label" default="operation" />
        <span class="required-indicator">*</span>
    </label>
    <g:if test="${params.action == 'editFeature'}">
        <g:textField name="operation" required="" value="${featureInstance?.operation}" readonly="readonly"/>
    </g:if>
    <g:else>
        <g:textField name="operation" required="" value="${featureInstance?.operation}"/>
    </g:else>

</div>

<div class="fieldcontain ${hasErrors(bean: featureInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="feature.description.label" default="description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="description" cols="40" rows="5" maxlength="300" required="" value="${featureInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: featureInstance, field: 'documents', 'error')} required">
    <label for="documents">
        <g:message code="feature.operation.label" default="documents" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="documents" required="" value="${featureInstance?.documents}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: featureInstance, field: 'fields', 'error')} required">
    <label for="fields">
        <g:message code="feature.fields.label" default="fields" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="fields" required="" value="${featureInstance?.fields}"/>
</div>
