<%--
  Created by IntelliJ IDEA.
  User: Md Dablu Hossain
  Date: 2/19/12
  Time: 3:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="grails.converters.JSON; com.jabait.security.User"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<g:applyLayout name="app">
<head>
<g:set var="entityName" value="${message(code: 'user.label', default:'User')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>

<style type="text/css">
.header{
    width: 609px;
}
.widget{
    width: 609px;
}

</style>
</head>
<body>
%{--<content tag="bannerTag"><h1>${type}</h1></content>--}%

<content tag="titleTag">

</content>
<content tag="rightTag">
    <script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.uniform.js')}" ></script>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'uniform.aristo.css')}">

    <div class="widget" style="width: 805px">

        <div class="header" style="width: 805px">

            <span><span class="ico gray user"></span>${titleOfPage}</span>
        </div>

        <g:if test="${flash.message}">
            <div class="container_12">

                <div class="grid_4">

                </div>
                <div class="grid_4 message" role="status">
                    <g:message code="${flash.message}" args="${flash.args}" default="${flash.default}"></g:message>

                </div>
                <div class="clear"></div>
            </div>
        </g:if>
        <div id="list_user" class="content scaffold-list" role="main">
            <div class="container_16" style="height: 900px">

                <div class="grid_7" style="padding-top: 20px;">
					<g:form name="leaveRequisition" id="leaveRequisition" controller="leave" action="saveLeave" method="POST">
						%{--<fieldset class="user-profile-fieldset">--}%
                            %{--<legend class="legend-color">Leave Requisition</legend>--}%
							<g:render template="/hrm/leave_requisition_form"/>
							<div class="grid_4 alpha">&nbsp;&nbsp;</div>
							<div class="grid_4 omega" style="padding-left: 181px;padding-top: 20px;">
								%{--<g:submitButton name="create" class="button save btn" value="${message(code: 'default.button.create.label', default: 'Create')}"/>--}%
								%{--<input class="button reset" type="reset" value="Clear" />--}%

								%{--<a id="userCreate" class="button icon approve" onclick="submitForm()">Save</a>--}%
								%{--<a id="ff" class="button danger icon remove" onclick="document.userForm.reset()">Clear</a>--}%
								%{--<g:link action="userGroupDetails" controller="security" class="btn">Link</g:link>--}%
							</div>
						%{--</fieldset>--}%
					</g:form>
                </div>
            </div>
            
        </div>
    </div>
    <script>
        $(document).ready(function(){
            $("input, textarea, select, button").uniform();
        })
    </script>
</content>
</body>
</g:applyLayout>