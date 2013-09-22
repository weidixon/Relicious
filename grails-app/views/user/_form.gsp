<%@ page import="relicious.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'firstName', 'error')} required">
	<label for="firstName">
		<g:message code="user.firstName.label" default="First Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="firstName" maxlength="32" required="" value="${userInstance?.firstName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'lastName', 'error')} ">
	<label for="lastName">
		<g:message code="user.lastName.label" default="Last Name" />
		
	</label>
	<g:textField name="lastName" maxlength="32" value="${userInstance?.lastName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'userName', 'error')} required">
	<label for="userName">
		<g:message code="user.userName.label" default="User Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="userName" maxlength="32" required="" value="${userInstance?.userName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="user.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="password" name="password" maxlength="32" required="" value="${userInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'confirmPassword', 'error')} required">
	<label for="confirmPassword">
		<g:message code="user.confirmPassword.label" default="Confirm Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="password" name="confirmPassword" maxlength="32" required="" value="${userInstance?.confirmPassword}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'email', 'error')} required">
	<label for="email">
		<g:message code="user.email.label" default="Email" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="email" name="email" required="" value="${userInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'hideEmail', 'error')} ">
	<label for="hideEmail">
		<g:message code="user.hideEmail.label" default="Hide Email" />
		
	</label>
	<g:checkBox name="hideEmail" value="${userInstance?.hideEmail}" />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'gender', 'error')} ">
	<label for="gender">
		<g:message code="user.gender.label" default="Gender" />
		
	</label>
	<g:select name="gender" from="${userInstance.constraints.gender.inList}" value="${userInstance?.gender}" valueMessagePrefix="user.gender" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'dateOfBirth', 'error')} required">
	<label for="dateOfBirth">
		<g:message code="user.dateOfBirth.label" default="Date Of Birth" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateOfBirth" precision="day"  value="${userInstance?.dateOfBirth}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'hideDOB', 'error')} ">
	<label for="hideDOB">
		<g:message code="user.hideDOB.label" default="Hide DOB" />
		
	</label>
	<g:checkBox name="hideDOB" value="${userInstance?.hideDOB}" />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'nationality', 'error')} required">
	<label for="nationality">
		<g:message code="user.nationality.label" default="Nationality" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nationality" required="" value="${userInstance?.nationality}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'country', 'error')} required">
	<label for="country">
		<g:message code="user.country.label" default="Country" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="country" name="country.id" from="${relicious.Country.list()}" optionKey="id" required="" value="${userInstance?.country?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'city', 'error')} ">
	<label for="city">
		<g:message code="user.city.label" default="City" />
		
	</label>
	<g:textField name="city" value="${userInstance?.city}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'state', 'error')} ">
	<label for="state">
		<g:message code="user.state.label" default="State" />
		
	</label>
	<g:textField name="state" value="${userInstance?.state}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'badge', 'error')} ">
	<label for="badge">
		<g:message code="user.badge.label" default="Badge" />
		
	</label>
	<g:select name="badge" from="${relicious.Badge.list()}" multiple="multiple" optionKey="id" size="5" value="${userInstance?.badge*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'message', 'error')} ">
	<label for="message">
		<g:message code="user.message.label" default="Message" />
		
	</label>
	<g:select name="message" from="${relicious.Message.list()}" multiple="multiple" optionKey="id" size="5" value="${userInstance?.message*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'recipe', 'error')} ">
	<label for="recipe">
		<g:message code="user.recipe.label" default="Recipe" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${userInstance?.recipe?}" var="r">
    <li><g:link controller="recipe" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="recipe" action="create" params="['user.id': userInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'recipe.label', default: 'Recipe')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'recipeBook', 'error')} ">
	<label for="recipeBook">
		<g:message code="user.recipeBook.label" default="Recipe Book" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${userInstance?.recipeBook?}" var="r">
    <li><g:link controller="recipeBook" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="recipeBook" action="create" params="['user.id': userInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'recipeBook.label', default: 'RecipeBook')])}</g:link>
</li>
</ul>

</div>

