<%@ page import="relicious.Message" %>



<div class="fieldcontain ${hasErrors(bean: messageInstance, field: 'messageContent', 'error')} required">
	<label for="messageContent">
		<g:message code="message.messageContent.label" default="Message Content" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="messageContent" required="" value="${messageInstance?.messageContent}"/>
</div>

