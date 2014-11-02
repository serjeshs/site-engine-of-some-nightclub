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
							<label><input type="text" onblur="if(this.value==''){this.value='Ссылка на картинку события'}" onfocus="if(this.value=='Ссылка на картинку события'){this.value=''}" name="imageUri" value="
							
							<c:choose>
  <c:when test="${condition1}">
    <c:out value="${event.imageUri}"/>
  </c:when>
  <c:otherwise>
    Ссылка на картинку события
  </c:otherwise>
</c:choose>
							
							
							">	</label>
							<label><input type="text" onblur="if(this.value==''){this.value='Название события'}" onfocus="if(this.value=='Название события'){this.value=''}" name="name" value="Название события">	</label>
							<textarea onblur="if(this.value==''){this.value='Описание события'}" onfocus="if(this.value=='Описание события'){this.value=''}" name="description">Описание события</textarea><br><br>
							<label><input type="text" onblur="if(this.value==''){this.value='Цена чиcлом'}" onfocus="if(this.value=='Цена чиcлом'){this.value=''}" name="cost" value="Цена чиcлом">	</label>
							<textarea onblur="if(this.value==''){this.value='Цена c описанием'}" onfocus="if(this.value=='Цена c описанием'){this.value=''}" name="costText">Цена c описанием</textarea><br><br>

								<br>Место<br> <select name="Place_id" multiple="">
									
								</select>
								<br>Место<br> <select multiple name="Place_id">
									<c:forEach var="place" items="${places}">
										<option value="<c:out value="${place.id}"/>"><c:out
												value="${place.name}" /></option>
									</c:forEach>
								</select>
							<label><input type="text" onblur="if(this.value==''){this.value='Время начала 2014-01-02T10:15:30'}" onfocus="if(this.value=='Время начала 2014-01-02T10:15:30'){this.value=''}" name="startEvent" value="Время начала 2014-01-02T10:15:30">	</label>
							<label><input type="text" onblur="if(this.value==''){this.value='Время окончания 2014-01-02T10:15:30'}" onfocus="if(this.value=='Время окончания 2014-01-02T10:15:30'){this.value=''}" name="endEvent" value="Время окончания 2014-01-02T10:15:30">	</label>
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
