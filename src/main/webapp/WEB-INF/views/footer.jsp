<%@page import="temp.JSPHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
		<footer>
			<div class="container_12">
				<div class="wrapper">
					<div class="grid_8">
						<c:if test="${guest}">
								Привет, Гость. <a href="/<c:out value="${appName}" />/register">Зарегистрируйся
									пожалуйста</a> или <a href="/<c:out value="${appName}" />/login">войди</a>.
						</c:if>
						<c:if test="${user}">
		Привет. ${appUserName}. 
							<a href="/<c:out value="${appName}" />/cabinet">Личный кабинет</a>. 
							<a href="/<c:out value="${appName}" />/logout">Выйти</a>
						</c:if>
					</div>
					<div class="grid_4">
						<div class="social">
							<a href="#"><img src="/<c:out value="${appName}" />/images/facebook-icon.png" alt=""></a>
							<a href="https://vk.com/club73822272"><img src="/<c:out value="${appName}" />/images/vk.png" alt="Vkontakte"></a>
							<a href="#"><img src="/<c:out value="${appName}" />/images/twitter-icon.png" alt=""></a>
							
						    
						</div>
					</div>
				</div>
			</div>
		</footer>
		<!-- ${timeNow} -->