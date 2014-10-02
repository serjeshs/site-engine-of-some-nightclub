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
			context.fillText('Афиша', x, y);
		</script>
	</a>
	<p>
		<c:if test="${guest}">
			<p>
				Привет, Гость.<br> <a href="/afisha/register">Зарегистрируйся
					пожалуйста</a> или <a href="/store/login">войди</a>.
		</c:if>
		<c:if test="${user}">
Привет. ${appUserName}.<br>
			<a href="/store/cabinet">Личный кабинет</a>. <a href="/store/cart">Корзина</a>
			<a href="/store/logout">Выйти</a>
		</c:if>
	</p>
	<h1>
		<c:out value="${event.name}" />
	</h1>
	<h3>
		<c:out value="${event.description}" />
	</h3>

</body>
</html>