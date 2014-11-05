<%@page import="temp.JSPHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="by">
<%@include file='head.jsp'%>
<body>
<%@include file='header.jsp'%>


	<div id="content">
		<div class="inner pad1">
			<div class="container_12">
				<div class="wrapper h-pad">
					<div class="grid_7">
						<h2>Введите информацию о событии.</h2>
						<form id="contact-form" action="/<c:out value="${appName}" />/event/add" method="POST">
						<input type="hidden" name="id" value="<c:out value="${eventid}"/>" />
						<fieldset>
							<%=JSPHelper.getInputlabel("Ссылка на картинку события", "imageUri") %>
							<%=JSPHelper.getInputlabel("Название события", "name") %>
							<%=JSPHelper.getTextArea("Описание события", "description") %>
							<%=JSPHelper.getInputlabel("Цена чиcлом", "cost") %>
							<%=JSPHelper.getTextArea("Цена c описанием", "costText") %>

								<br>Место<br> <select multiple name="Place_id">
									<c:forEach var="place" items="${places}">
										<option value="<c:out value="${place.id}"/>"><c:out
												value="${place.name}" /></option>
									</c:forEach>
								</select>
                            <input id="datetimepicker" type="text" >                            
							<%=JSPHelper.getInputlabel("Время начала 2014-01-02T10:15:30", "startEvent") %>
							<%=JSPHelper.getInputlabel("Время окончания 2014-01-02T10:15:30", "endEvent") %>
								<input class="button1" type="submit" value="Submit">
							</fieldset>
						</form>
					</div>
				</div>
			</div>	
		</div>
	</div>
	<%@include file='footer.jsp'%>
	
</body>
</html>
