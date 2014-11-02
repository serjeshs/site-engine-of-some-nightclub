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
						<form id="contact-form" action="/<c:out value="${appName}" />/event/edit" method="POST">
						<input type="hidden" name="id" value="<c:out value="${eventid}"/>" />
						<fieldset>
							<label>
							Ссылка на картинку события
							<input type="text" name="imageUri" value="<c:out value="${event.imageUri}"/>">
							</label>
							<label>
							Название события
							<input type="text" name="name" value="<c:out value="${event.name}"/>">
							</label>
							Описание события
							<textarea name="description"><c:out value="${event.description}"/></textarea><br><br>
							Минимальная цена входа числом.
							<label><input type="text" name="cost" value="<c:out value="${event.cost}"/>">	</label>
							Цена c описанием. С сылками, где можно купить билеты, когда и какие.
							<textarea name="costText"><c:out value="${event.costText}"/></textarea><br><br>

								<br>Место<br> <select multiple name="Place_id" >
									<c:forEach var="place" items="${places}">
										<option value="<c:out value="${place.id}"/>"><c:out
												value="${place.name}" /></option>
									</c:forEach>
								</select>
								
							<label>
							Время начала 2014-01-02T10:15:30
							<input type="text" name="startEvent" value="<c:out value="${event.startEvent}"/>">	</label>
							<label>
							Время окончания 2014-01-02T10:15:30
							<input type="text" name="endEvent" value="<c:out value="${event.endEvent}"/>">	</label>
								<input type="submit" value="Submit" class="button1">
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
