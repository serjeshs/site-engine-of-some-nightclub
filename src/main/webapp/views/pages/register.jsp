<%@page import="temp.JSPHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="springtags" %>
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
						<form method="POST" action="registration" id="contact-form">
							<fieldset>
								<label><input onblur="if(this.value==''){this.value='Никнейм'}" onfocus="if(this.value=='Никнейм'){this.value=''}" name="nick" value="${nick}" type="text">	</label>
								<label><input onblur="if(this.value==''){this.value='Email'}" onfocus="if(this.value=='Email'){this.value=''}" name="email" value="${Email}" type="text">	</label>
								
								<label>
									<input id="strex" type="password" value="Пароль" onfocus="if(this.value=='Пароль'){this.value=''}" onblur="if(this.value==''){this.value='Пароль'}">
									<input id="strexHid" type="hidden" name="password2">
								</label>
								<label>
									<input id="strexAgain" type="password" value="Введите пароль ещё раз" name="password1" onfocus="if(this.value=='Введите пароль ещё раз'){this.value=''}" onblur="if(this.value==''){this.value='Введите пароль ещё раз'}">
									<input id="strexAgainHid" type="hidden" name="password1">
								</label>
                				<button id="cryptstr" class="button1">Зарегистрироваться</button>
							</fieldset>
						</form>
						
<script type="text/javascript">
<%@include file='../elements/sha512.jsp'%>

//register onclick events for Encrypt button
document.getElementById('cryptstr').onclick = function() {
	
    // gets data from input text
	var txt_string = document.getElementById('strex').value;
	var txt_string_Again = document.getElementById('strexAgain').value;

	// encrypts data and adds it in #strcrypt element
	document.getElementById('strexHid').value = SHA512(txt_string);
	
	document.getElementById('strexAgainHid').value = SHA512(txt_string_Again);
	
return true;
}
</script>
						
					</div>
					
				</div>
			</div>	
		</div>
	</div>
	
