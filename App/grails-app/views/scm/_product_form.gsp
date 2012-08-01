<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/20/12
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.jabait.util.UnitOfMeasure; com.jabait.scm.inventory.ProductClassification; com.jabait.scm.inventory.ProductType; com.jabait.scm.inventory.Product;com.jabait.scm.inventory.Category;" %>

<div class="fieldcontain ${hasErrors(bean: product, field: 'userCode', 'error')} required">
    <label for="productName">
        <g:message code="product.productName.label" default="Product Name" />
        <span class="required-indicator">*</span>
    </label>


    <g:textField name="productName" required=""  value="" />

</div>
<div class="fieldcontain ${hasErrors(bean: product, field: 'password', 'error')} required">
    <label for="category">
        <g:message code="product.category.label" default="Category" />
        <span class="required-indicator">*</span>
    </label>
    <g:select id="categoryList" name="category" from="${Category.list()}"
              optionKey="id" optionValue="categoryName" noSelection="['':'Select Category...']"></g:select>
</div>
<div class="fieldcontain ${hasErrors(bean: product, field: 'password', 'error')} required">
    <label for="productType">
        <g:message code="product.type.label" default="Product Type" />
        <span class="required-indicator">*</span>
    </label>
    <g:select id="productType" name="productType" from="${ProductType.list()}"
              optionKey="id" optionValue="name" noSelection="['':'Select Product Type...']"></g:select>
</div>
<div class="fieldcontain ${hasErrors(bean: product, field: 'password', 'error')} required">
    <label for="licenseInfo">
        <g:message code="product.licenseInfo.label" default="License Info" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="licenseInfo" required=""  value="" />
</div>
<div class="fieldcontain ${hasErrors(bean: product, field: 'password', 'error')} required">
    <label for="productType">
        <g:message code="product.classification.label" default="Classification" />
        <span class="required-indicator">*</span>
    </label>
    <g:select id="productClassification" name="productClassification" from="${ProductClassification.list()}"
              optionKey="id" optionValue="classification" noSelection="['':'Select Product Class...']"></g:select>
</div>
<div class="fieldcontain ${hasErrors(bean: product, field: 'password', 'error')} required">
    <label for="productType">
        <g:message code="product.uom.label" default="Unit of Measure" />
        <span class="required-indicator">*</span>
    </label>
    <g:select id="uom" name="uom" from="${UnitOfMeasure.list()}"
              optionKey="id" optionValue="uom" noSelection="['':'Unit Of Measure...']"></g:select>
</div>


