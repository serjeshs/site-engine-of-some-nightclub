<%@page import="temp.JSPHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	<header>
		<div class="container_12">
			<div class="grid_12">
				<div class="wrapper">
					<a href="/afisha" class="logo">AFISHA</a>
					<nav>
						<ul class="menu">
							<c:forEach var="item" items="${menuItems}">
								<li><a href="/<c:out value="${appName}" />/<c:out value="${item.link}" />"><c:out
											value="${item.name}" /></a></li>
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