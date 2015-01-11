<%@page import="temp.JSPHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="springtags" %>
<%@ page session="false"%>
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

								<%
									int a = 0;
								%>
<table border="0">

								<c:forEach var="event" items="${events}">
									<%=JSPHelper.getOpenP(a) %>

									<li class="<%=JSPHelper.getColumnPosition(a)%>">
									<div align="center" >
									<figure>
									
									<a href="/<c:out value="${appName}" />/event/<c:out	value="${event.id}" />" class="link">
										
										<img src="<c:out value="${event.imageUri}" />" height="200" alt="">
											
										
										<br><br>
											<c:out value="${event.name}" />
											
										</a>
										
										</figure> <br> <c:out value="${event.startEvent}" /> <br>
										
										<!--  <c:out
										
											value="${fn:substring(event.description,0,200)}" /> ..... <br> -->
										
										
										COST : <c:out value="${event.cost}" /> <br> 
										<p>
											<a href="/<c:out value="${appName}" />/event/<c:out	value="${event.id}" />" class="button">Читать
												далее</a>
										</p>
									</div>
									</li>
									
									<%=JSPHelper.getCloseP(a) %>
									<%
										if (a == 2) {
												a = 0;
											} else {
												a++;
											}
											;
									%>
									
								</c:forEach>
								<%=JSPHelper.getClosePEND(a) %>
</table>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>