<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 5/27/12
  Time: 2:29 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title></title>
    <sfu:generateConfiguration fileSize="30" form="bookForm" buttonImageFile="../static/images/browse-button-sprite.png" buttonWidth="104" buttonHeight="30"/>
</head>
<body>

<g:form name="imageUpload" id="imageUpload" onsubmit="return sfuSubmitForm(this);">
    Choose file: <sfu:fileUploadControl/>
    <br/>
    Progress bar: <sfu:fileUploadProgressBar/>
    <br/>

    <input type="submit" value="Save">
</g:form>

</body>
</html>