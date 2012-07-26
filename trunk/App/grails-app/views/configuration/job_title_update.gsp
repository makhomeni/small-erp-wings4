<%--
  Created by IntelliJ IDEA.
  User: masum
  Date: 6/27/12
  Time: 11:22 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.jabait.hrm.JobRole"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<g:applyLayout name="app">
    <head>

        <title></title>

        <script type="text/javascript">
            function submitForm()
            {
                document.jobTitleForm.submit();
            }


        </script>


    </head>
    <body>

    <content tag="rightTag">

        <div class="widget">

            <div class="header" style="width: 610px;">
                <span><span class="ico gray home"></span>${type}</span>
            </div>

            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:form name="jobTitleForm" controller="configuration" action="updateJobTitle" method="POST">
                <fieldset class="form">
                    <g:hiddenField name="jobTitleId" value="${jobTitleInstance?.id}" />

                    <g:render template="jobTitle_update_form"/>

                    <div class="grid_4 alpha">&nbsp;&nbsp;</div>
                    <div class="grid_4 omega" style="padding-left: 195px; padding-top: 12px;">

                        <a id="jobTitle" class="button icon approve" onclick="submitForm()">Save</a>
                        <a id="ff" class="button danger icon remove" onclick="document.jobTitleForm.reset()">Clear</a>

                    </div>

                </fieldset>
                <br class="clear"/>

            </g:form>
        </div>
    </content>
    </body>
</g:applyLayout>