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
		<div class="inner">
			<div class="container_12">
				<div class="wrapper">
					<div class="block">
						<div class="grid-inner">
							<h2>
								<c:out value="${event.name}" />
							</h2>
							<div class="wrapper">
								<figure class="img-indent">
								<img src="<c:out value="${event.imageUri}" />" alt="">
								</figure>
								<c:out value="${event.description}" />
							</div>
							Старт : <c:out value="${event.startEvent}" /> <br>
							Конец : <c:out value="${event.endEvent}" /> <br>   
							COST : <c:out value="${event.cost}" /> <br> 
							<c:out value="${event.costText}" /> <br> 
							Где : <c:out value="${event.place_Name}" /> <br>
							<c:out value="${event.region_Name}" /> <br>
						</div>

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
	<%@include file='footer.jsp'%>
</body>
</html>
