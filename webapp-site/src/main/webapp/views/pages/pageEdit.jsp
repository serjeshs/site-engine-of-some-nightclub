<%@page import="temp.JSPHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="springtags" %>
<%@ page session="false"%>


	<div id="content">
		<div class="inner pad1">
			<div class="container_12">
				<div class="wrapper h-pad">
					<div class="grid_7">
						<form id="contact-form" action="<c:out value="${appName}" />/p/${page.uriName}/edit" method="POST">
						<input type="hidden" name="id" value="<c:out value="${page.id}"/>" />
						<fieldset>
							<br>
							<label>
							${lang_template.page_title_edit}<br>
							<input type="text" name="title" value="<c:out value="${page.title}"/>">
							</label>
							${lang_template.page_text_edit}<br>
							<textarea name="text"><c:out value="${page.text}"/></textarea><br><br>
							<label>
							${lang_template.page_URL_edit}<br>
							<input type="text" name="uriName" value="<c:out value="${page.uriName}"/>">
							</label>
								<input type="submit" value="Submit" class="button1">
							</fieldset>
						</form>
					</div>
				</div>
			</div>	
		</div>
	</div>