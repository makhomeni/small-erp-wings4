<%--
  Created by IntelliJ IDEA.
  User: masum
  Date: 6/25/12
  Time: 12:40 PM
  To change this template use File | Settings | File Templates.
--%>



<div class="fieldcontain ${hasErrors(bean: leaveInstance, field: 'leaveType', 'error')} required">
    <label for="leaveTitle">
        <g:message code="leaveType.label" default="Leave Type" />
        <span class="required-indicator">*</span>
    </label>
    <g:if test="${params.action == 'editLeave'}">
        <g:textField name="leaveType" required="" value="${leaveInstance?.leaveType}" readonly="readonly"/>
    </g:if>
    <g:else>
        <g:textField name="leaveType" required="" value="${leaveInstance?.leaveType}" />
    </g:else>

</div>
<div class="fieldcontain ${hasErrors(bean: leaveInstance, field: 'leaveDuration', 'error')} required">
    <label for="leaveDuration">
        <g:message code="leaveDuration.label" default="Leave Duration" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="leaveDuration" required="" value="${leaveInstance?.leaveDuration}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: leaveInstance, field: 'leaveNotes', 'error')} required">
    <label for="leaveNotes">
        <g:message code="leaveNotes.label" default="Leave Notes" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="leaveNotes" required="" value="${leaveInstance?.leaveNotes}"/>
</div>
