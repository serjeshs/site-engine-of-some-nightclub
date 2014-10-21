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
						<h2>${result}</h2>
					</div>
					
				</div>
			</div>	
		</div>
	</div>
	


<%@include file='footer.jsp'%>
</body>
</html>