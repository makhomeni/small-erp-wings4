<%--
  Created by IntelliJ IDEA.
  User: Shohag-pc
  Date: 5/27/12
  Time: 11:22 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title></title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap.min.css')}">

    <r:require modules="bootstrap-file-upload"/>


</head>
<body>
<script>
    $(document).ready(function(){
        alert(1)
        $('#fileupload').fileupload();
    })
</script>
<bsfu:fileUpload action="upload" controller="image"/>

<bsfu:imageGallery/>

</body>
</html>