<%@ page import="relicious.Favourite" %>



<div class="fieldcontain ${hasErrors(bean: favouriteInstance, field: 'recipe', 'error')} required">
	<label for="recipe">
		<g:message code="favourite.recipe.label" default="Recipe" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="recipe" name="recipe.id" from="${relicious.Recipe.list()}" optionKey="id" required="" value="${favouriteInstance?.recipe?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: favouriteInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="favourite.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${relicious.User.list()}" optionKey="id" required="" value="${favouriteInstance?.user?.id}" class="many-to-one"/>
</div>

