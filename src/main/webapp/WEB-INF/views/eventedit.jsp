<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><c:out value="${event.name}" /></title>
<style>
div {
	border: 1px solid black; /* Параметры рамки */
	padding: 5px; /* Поля вокруг текста */
	margin-bottom: 5px; /* Отступ снизу */
}

#left {
	text-align: left;
}

#right {
	text-align: right;
}

#center {
	text-align: center;
}
}
</style>
</head>
<body>
	<a href="/store/">
		<div id="center">
			<canvas id="myCanvas" width="800" height="100"></canvas>
		</div> <script>
			var canvas = document.getElementById('myCanvas');
			var context = canvas.getContext('2d');
			var x = canvas.width / 2;
			var y = canvas.height / 2;
			context.font = '30pt Calibri';
			context.textAlign = 'center';
			context.fillStyle = 'blue';
			context.fillText('Афиша', x, y);
		</script>
	</a>
	<p>
		<c:if test="${guest}">
			<p>
				Привет, Гость.<br> <a href="/store/register">Зарегистрируйся
					пожалуйста</a> или <a href="/store/login">войди</a>.
		</c:if>
		<c:if test="${user}">
Привет. ${appUserName}.<br>
			<a href="/store/cabinet">Личный кабинет</a>. <a href="/store/cart">Корзина</a>
			<a href="/store/logout">Выйти</a>
		</c:if>
	</p>
	
	<form method="POST" action="event/add">
					<input type="hidden" name="id" value="<c:out value="${eventid}"/>" /> 
					<br>Название события<br>
					<input size="100" type="text" name="name" /> 
					<br>Описание события <br>
					<textarea accesskey="m" rows="4" cols="48" id="mes-inp" name="description"></textarea> 
					<br>Место<br>
					<select multiple name="Place_id">
						<c:forEach var="place" items="${places}">
							<option value="<c:out value="${place.id}"/>"><c:out
									value="${place.name}" /></option>
						</c:forEach>
					</select>
					<br>Цена чиcлом<br>
					<input size="100"  type="text" name="cost" /> 
					<br>Цена c описанием<br>
					<input size="100"  type="text" name="costText" />
					
					<br>Время начала 2014-01-02T10:15:30<br>
					<input size="100"  type="text" name="startEvent" />
					
					<br>Время окончания 2014-01-02T10:15:30<br>
					<input size="100"  type="text" name="endEvent" />
					
					<input alt="Add" type="submit" />

				</form>



</body>
</html>