<%--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/28/12
  Time: 2:59 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="error_page"/>
    <title></title>
</head>
<body>
    <div id="error-wrapper">
        <div id="error-code">401 Unauthorized</div>
        <div id="error-message">
            Sorry, you do not seem to have access privilege to this resource!
        </div>
        <div id="error-actions">
            <g:link name="back_to_dashboard" controller="application" action="index">Back to Dashboard</g:link>
        </div>
    </div>
</body>
</html>