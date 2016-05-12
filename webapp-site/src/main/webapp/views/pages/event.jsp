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
					<div class="block">
						<div class="grid_11">
							<h2>
								<c:out value="${event.name}" />
							</h2>
							<div class="wrapper">
								<figure class="img-indent">
								<div align="center" ><img src="<c:out value="${event.imageUri}" />" height="400" alt=""></div>
								</figure>
								
							</div>
							<c:out value="${event.description}" escapeXml="false" />
							Старт : <c:out value="${event.startEvent}" /> <br>
							Конец : <c:out value="${event.endEvent}" /> <br>   
							COST : <c:out value="${event.cost}" /> <br> 
							<c:out value="${event.costText}" escapeXml="false"/> <br> 
							Где : <c:out value="${event.place_Name}" /> <br>
							<c:out value="${event.region_Name}" /> <br>
							
							<c:if test="${likeStatus == 0}">
							<a href="<c:out value="${appName}" />/like/BETHERE/<c:out value="${event.id}" />">Пойду</a>
							|
							<a href="<c:out value="${appName}" />/like/MAYATTEND/<c:out value="${event.id}" />">Мне интересно</a>
							|
							<a href="<c:out value="${appName}" />/like/DIZLIKE/<c:out value="${event.id}" />">Не интересно</a>
							</c:if>
							
							<c:if test="${likeStatus == 1}">
							Вы идёте на данное событие
							 | <a href="<c:out value="${appName}" />/like/NOTHING/<c:out value="${event.id}" />">Изменить решение</a>
							</c:if>
							
							<c:if test="${likeStatus == 2}">
							Вы интересуетесь данным событием 
							 | <a href="<c:out value="${appName}" />/like/NOTHING/<c:out value="${event.id}" />">Изменить решение</a>
							</c:if>
							
							<c:if test="${likeStatus == 3}">
							 | <a href="<c:out value="${appName}" />/like/NOTHING/<c:out value="${event.id}" />">Изменить решение</a>
							Вам не интересно данное событие 
							</c:if>
						</div>
						<c:if test="${canEdit}">
						<div class="grid_1"> <a href="<c:out value="${appName}" />/event/edit/<c:out value="${event.id}" />" >
						<img src="<c:out value="${appName}" />/images/edit-icon.png" height="50" alt=""></a></div>
						</c:if>
						
					</div>
					
				</div>
				<div class="wrapper">
					<div class="grid_12">
						<h2 class="h-pad">Comments</h2>
						<div class="blog">
							<script type="text/javascript">
								VK.init({
									apiId : 4585079,
									onlyWidgets : true
								});
							</script>

							<!-- Put this div tag to the place, where the Comments block will be -->
							<div id="vk_comments"></div>
							<script type="text/javascript">
								VK.Widgets.Comments("vk_comments", {
									limit : 20,
									width : "650",
									attach : "*"
								});
							</script>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>