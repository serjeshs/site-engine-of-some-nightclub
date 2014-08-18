<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>События</title>
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
	<a href="/afisha/">
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
			context.fillText('AFISHA', x, y);
		</script>
	</a>
	<p>
		<c:if test="${guest}">
			<p>
				Привет, Гость.<br> <a href="/afisha/register">Зарегистрируйся
					пожалуйста</a> или <a href="/afisha/login">войди</a>.
		</c:if>
		<c:if test="${user}">
		Привет. ${appUserName}.<br>
			<a href="/afisha/cabinet">Личный кабинет</a>. <a href="/afisha/cart">Корзина</a>
			<a href="/afisha/logout">Выйти</a>
		</c:if>
	</p>
	<c:forEach var="event" items="${events}">
		<hr>
		<c:out value="${event.startEvent}" />
		<br>
		<c:out value="${event.name}" />
		<br>
		<c:out value="${event.description}" />
		<br>
		<c:out value="${event.cost}" />
		<br>
		<c:out value="${event.costText}" />
		<br>
		<c:out value="${event.endEvent}" />
		<br>
		<c:out value="${event.place_Name}" />
		<br>
		<c:out value="${event.region_Name}" />
		<br>
	</c:forEach>
</body>
</html>