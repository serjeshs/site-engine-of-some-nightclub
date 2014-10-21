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
						<h2>${title}</h2>
						<br>
						<h3>
						<font color="red">${result}</font>
						</h3>
						<form method="POST" action="j_spring_security_check" id="contact-form">
							<fieldset>
								<label><input onblur="if(this.value==''){this.value='Email'}" onfocus="if(this.value=='Email'){this.value=''}" name="j_username" value="Никнейм" type="text">	</label>
								<label><input onblur="if(this.value==''){this.value='Пароль'}" onfocus="if(this.value=='Пароль'){this.value=''}" name="j_password" value="Пароль" type="text">	</label>
								<input value="Войти" class="button1" type="submit">
							</fieldset>
						</form>
					</div>
					
				</div>
			</div>	
		</div>
	</div>
	


<%@include file='footer.jsp'%>
</body>
</html>