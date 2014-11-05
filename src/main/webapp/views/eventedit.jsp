<%@page import="java.time.LocalDateTime"%>
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
						<div class="wrapper">
								<figure class="img-indent">
								<div align="center" ><img src="/<c:out value="${appName}" /><c:out value="${event.imageUri}" />" height="400" alt=""></div>
								</figure>
								
							</div>
						<br>
						<form id="contact-form" action="/<c:out value="${appName}" />/event/edit" method="POST" enctype="multipart/form-data">
						<input type="hidden" name="id" value="<c:out value="${eventid}"/>" />
						<fieldset>
						
							<br>
							<label>
							
							Загрузите новое изображение, если текущего нету или его нужно изменить.
							<br>
							<input type="file" name="photo" accept="image/*">
							<br>
							</label>
							<br><br>
							
							<label>
							Название события<br>
							<input type="text" name="name" value="<c:out value="${event.name}"/>">
							</label>
							Описание события<br>
							<textarea name="description"><c:out value="${event.description}"/></textarea><br><br>
							Минимальная цена входа числом.<br>
							<label><input type="text" name="cost" value="<c:out value="${event.cost}"/>">	</label>
							Цена c описанием. С сылками, где можно купить билеты, когда и какие.<br>
							<textarea name="costText"><c:out value="${event.costText}"/></textarea><br><br>

								<br>Место<br> <select multiple name="Place_id" >
									<c:forEach var="place" items="${places}">
										<option value="<c:out value="${place.id}"/>"><c:out
												value="${place.name}" /></option>
									</c:forEach>
								</select>
								
							<label>
							Время начала <% LocalDateTime.now().toString(); %><br>
							<input type="text" name="startEvent" value="<c:out value="${event.startEvent}"/>">	</label>
							<label>
							Время окончания <% LocalDateTime.now().plusHours(4).toString(); %><br>
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
