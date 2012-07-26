<%--
  Created by IntelliJ IDEA.
  User: Masum
  Date: 2/20/12
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>

<g:applyLayout name="app">
    <head>
        <g:set var="entityName" value="${message(code:'leave.description', default: 'Leave')}"/>
        <title><g:message code="default.edit.label" args="[entityName]"/></title>

        <script type="text/javascript">
            function submitForm()
            {
                document.leaveForm.submit();
            }

        </script>

    </head>


    <body>
    <content tag="rightTag">
        <div class="widget">

            <div class="header" style="width: 610px;">
                <span><span class="ico gray user"></span>${type}</span>
            </div>

            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <g:form name="leaveForm" controller="configuration" action="updateLeave" method="POST">
                <fieldset class="form">
                    <g:hiddenField name="leaveId" value="${leaveInstance?.id}" />
                    <g:render template="leave_form"/>
                    <div class="grid_4 alpha">&nbsp;&nbsp;</div>
                    <div class="grid_4 omega" style="padding-left: 193px;padding-top: 20px;">


                        <a id="leave" class="button icon approve" onclick="submitForm()">Save</a>
                        <a id="ff" class="button danger icon remove" onclick="document.leaveForm.reset()">Clear</a>

                    </div>
                </fieldset>
                <br class="clear"/>

            </g:form>
        </div>
    </content>
    </body>

</g:applyLayout>