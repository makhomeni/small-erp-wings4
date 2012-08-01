<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/20/12
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.jabait.scm.inventory.Product;com.jabait.scm.inventory.Category;" %>

<div class="fieldcontain ${hasErrors(bean: product, field: 'userCode', 'error')} required">
    <label for="productName">
        <g:message code="user.userCode.label" default="Product Name" />
        <span class="required-indicator">*</span>
    </label>


    <g:textField name="productName" required=""  value="" />

</div>
<div class="fieldcontain ${hasErrors(bean: product, field: 'password', 'error')} required">
    <label for="password">
        <g:message code="user.password.label" default="Password" />
        <span class="required-indicator">*</span>
    </label>
    <g:select id="categoryList" name="category" from="${Category.list()}"
              optionKey="id" optionValue="categoryName" noSelection="['':'Select Category...']"></g:select>
</div>
<div class="fieldcontain ${hasErrors(bean: product, field: 'password', 'error')} required">
    <label for="password">
        <g:message code="user.password.label" default="Confirm Password" />
        <span class="required-indicator">*</span>
    </label>
    <g:passwordField name="confirmPassword" required="" value=""/>
</div>


