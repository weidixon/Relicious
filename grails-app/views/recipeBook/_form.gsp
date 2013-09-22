<%@ page import="relicious.RecipeBook" %>



<div class="fieldcontain ${hasErrors(bean: recipeBookInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="recipeBook.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="32" required="" value="${recipeBookInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: recipeBookInstance, field: 'recipe', 'error')} ">
	<label for="recipe">
		<g:message code="recipeBook.recipe.label" default="Recipe" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${recipeBookInstance?.recipe?}" var="r">
    <li><g:link controller="recipe" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="recipe" action="create" params="['recipeBook.id': recipeBookInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'recipe.label', default: 'Recipe')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: recipeBookInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="recipeBook.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${relicious.User.list()}" optionKey="id" required="" value="${recipeBookInstance?.user?.id}" class="many-to-one"/>
</div>

