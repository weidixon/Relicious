
<%@ page import="relicious.Favourite" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'favourite.label', default: 'Favourite')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-favourite" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-favourite" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="favourite.recipe.label" default="Recipe" /></th>
					
						<th><g:message code="favourite.user.label" default="User" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${favouriteInstanceList}" status="i" var="favouriteInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${favouriteInstance.id}">${fieldValue(bean: favouriteInstance, field: "recipe")}</g:link></td>
					
						<td>${fieldValue(bean: favouriteInstance, field: "user")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${favouriteInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
