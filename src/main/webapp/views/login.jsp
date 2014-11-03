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
								<label><input id="emailId" type="text" onblur="if(this.value==''){this.value='Email'}" onfocus="if(this.value=='Email'){this.value=''}" name="j_username" value="Email">	</label>
								<label><input id="strex" type="password" value="Пароль" name="j_password" onfocus="if(this.value=='Пароль'){this.value=''}" onblur="if(this.value==''){this.value='Пароль'}"></label>
								<button id="cryptstr" class="button1">Войти</button>
							</fieldset>
						</form>
						<form method="POST" action="/<c:out value="${appName}" />/sendrestorepassword">
						<input type="hidden" name="email" id="email_hidden" />
						<button id="sendEmail" class="button1">Восстановить пароль</button>
						</form>
						
						
<script type="text/javascript">
<%@include file='sha512.jsp'%>


//register onclick events for Encrypt button
document.getElementById('cryptstr').onclick = function() {
	
    // gets data from input text
	var txt_string = document.getElementById('strex').value;

	// encrypts data and adds it in #strcrypt element
	document.getElementById('strex').value = SHA512(txt_string);
	
return true;
}


document.getElementById('sendEmail').onclick = function() {
	
    // gets data from input text
	document.getElementById('email_hidden').value = document.getElementById('emailId').value;
	
return true;
}

</script>
					</div>
					
				</div>
			</div>	
		</div>
	</div>
	


<%@include file='footer.jsp'%>
</body>
</html>