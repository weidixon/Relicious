<%@ page import="relicious.Country" %>



<div class="fieldcontain ${hasErrors(bean: countryInstance, field: 'country_name', 'error')} ">
	<label for="country_name">
		<g:message code="country.country_name.label" default="Countryname" />
		
	</label>
	<g:textField name="country_name" value="${countryInstance?.country_name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: countryInstance, field: 'user', 'error')} ">
	<label for="user">
		<g:message code="country.user.label" default="User" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${countryInstance?.user?}" var="u">
    <li><g:link controller="user" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="user" action="create" params="['country.id': countryInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'user.label', default: 'User')])}</g:link>
</li>
</ul>

</div>

