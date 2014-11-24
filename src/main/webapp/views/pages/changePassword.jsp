<%@page import="temp.JSPHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="false"%>
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
						<form method="POST" action="/<c:out value="${appName}" />/updatepassword" id="contact-form">
							<fieldset>
								<c:if test="${changePassword}">
								Старый пароль
								<input id="strex_old" type="password">
								<input id="strex_old_HID" type="hidden" name="password_old">
								</c:if>
								Новый пароль
								<input id="password_new1" type="password">
								<input id="strexHid" type="hidden" name="password_new1">
								Повторите ввод нового пароля
								<input id="password_new2" type="password">
								<input id="strexAgainHid" type="hidden" name="password_new2">
								<input type="hidden" name="tocken" id="tocken" value="${tocken}" />
								<input type="hidden" name="email" id="email" value="${userId}"/>
								
								<button id="cryptstr" class="button1">Задать новый пароль</button>
							</fieldset>
						</form>
						
<script type="text/javascript">
<%@include file='../elements/sha512.jsp'%>


//register onclick events for Encrypt button
document.getElementById('cryptstr').onclick = function() {
	
    // gets data from input text
	var txt_string_new1 = document.getElementById('password_new1').value;
	var txt_string_new2 = document.getElementById('password_new2').value;

	// encrypts data and adds it in #strcrypt element
	document.getElementById('strexAgainHid').value = SHA512(txt_string_new1);
	document.getElementById('strexHid').value = SHA512(txt_string_new2);
	
	var txt_string_old = document.getElementById('strex_old').value;
	document.getElementById('strex_old_HID').value = SHA512(txt_string_old);
	
return true;
}
//--&gt;
</script>
					</div>
					
				</div>
			</div>	
		</div>
	</div>
	