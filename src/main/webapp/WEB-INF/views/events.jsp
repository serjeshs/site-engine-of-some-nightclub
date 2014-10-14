<%@page import="temp.JSPHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="by">
<head>
<title></title>
<meta charset="utf-8"><link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,300italic' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="css/reset.css" type="text/css" media="screen">
<link rel="stylesheet" href="css/grid.css" type="text/css" media="screen">
<link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-migrate-1.1.1.js"></script>
  <!--[if lt IE 8]><div style='text-align:center'><a href="http://www.microsoft.com/windows/internet-explorer/default.aspx?ocid=ie6_countdown_bannercode"><img src="http://www.theie6countdown.com/img/upgrade.jpg"border="0"alt=""/></a></div><![endif]-->
  	<!--[if lt IE 9]>
	   	<script src="js/html5shiv.js"></script>
	   	<link rel="stylesheet" href="css/ie.css" type="text/css" media="screen">
	   	<link href='http://fonts.googleapis.com/css?family=Lato:300italic' rel='stylesheet' type='text/css'>
	   	<link href='http://fonts.googleapis.com/css?family=Lato:300' rel='stylesheet' type='text/css'>
	   	<link href='http://fonts.googleapis.com/css?family=Lato:400' rel='stylesheet' type='text/css'>
	   	<link href='http://fonts.googleapis.com/css?family=Lato:700' rel='stylesheet' type='text/css'>
    <![endif]-->
</head>

<body>
	<header>
		<div class="container_12">
			<div class="grid_12">
				<div class="wrapper">
					<a href="/afisha" class="logo">AFISHA</a>
					<nav>
						<ul class="menu">
						<c:forEach var="item" items="${menuItems}">
						<li><a href="<c:out value="${item.link}" />"><c:out value="${item.name}" /></a></li>
						</c:forEach>
							<!-- <li><a href="about.html">about</a></li>
							<li class="active"><a href="works.html">works</a></li>
							<li><a href="clients.html">clients</a></li>
							<li><a href="blog.html">blog</a></li>
							<li><a href="contacts.html">contacts</a></li> -->
						</ul>
					</nav>
				</div>
			</div>
		</div>
	</header>
	<div id="content">
		<div class="inner">
			<div class="container_12">
				<div class="wrapper">
					<div class="grid_12">
						<div class="block">
							<div class="info-block">
							<h1>Тут могла бы быть Ваша реклама!</h1>
						</div>
					</div>
				</div>
				<div class="wrapper">
					<div class="grid_12">
						<h2 class="h-pad1">События</h2>
						<ul class="wrapper works">
						
						<% int a = 0 ; %>



						
<!-- 						 Example : 
<li class="grid_4">
								<figure><img src="images/works2.jpg" alt=""></figure>
								<p><a href="#" class="link">Project 2</a></p>
								Lorem ipsum dolor sit amet, consectetur adipisicing elit, seiusmod tempor incididunt ut labore et dolore magna.
								<p><a href="#" class="button">Read more</a></p>
							</li>
							 -->
							<c:forEach var="event" items="${events}">
	
								<li class="<%= JSPHelper.getColumnPosition(a) %>">
									<figure>
										<img src="images/works1.jpg" alt="">
									</figure>
									<p>
										<a href="event/<c:out	value="${event.id}" />" class="link"><c:out value="${event.name}" /></a>
									</p> <br> <c:out value="${event.startEvent}" /> <br> <c:out
										value="${fn:substring(event.description,0,200)}" /> ..... <br> <c:out
										value="${event.cost}" /> <br> <c:out
										value="${event.costText}" /> <br> <c:out
										value="${event.endEvent}" /> <br> <c:out
										value="${event.place_Name}" /> <br> <c:out
										value="${event.region_Name}" /> <br>
									<p>
										<a href="event/<c:out	value="${event.id}" />" class="button">Читать далее</a>
									</p>
								</li>
								<% if (a==2) {
									a=0;
								} else {
									a++;
								} ; %>
							</c:forEach>


						</ul>
					</div>
				</div>
			</div>			
		</div>
	</div>
		<footer>
			<div class="container_12">
				<div class="wrapper">
					<div class="grid_8">
						<c:if test="${guest}">
								Привет, Гость. <a href="/afisha/register">Зарегистрируйся
									пожалуйста</a> или <a href="/afisha/login">войди</a>.
						</c:if>
						<c:if test="${user}">
		Привет. ${appUserName}. 
							<a href="/afisha/cabinet">Личный кабинет</a>. 
							<a href="/afisha/logout">Выйти</a>
						</c:if>
					</div>
					<div class="grid_4">
						<div class="social">
							<a href="#"><img src="images/facebook-icon.png" alt=""></a>
							<a href="https://vk.com/club73822272"><img src="images/vk.png" alt="Vkontakte"></a>
							<a href="#"><img src="images/twitter-icon.png" alt=""></a>
							
						    
						</div>
					</div>
				</div>
			</div>
		</footer></body>
</html>
