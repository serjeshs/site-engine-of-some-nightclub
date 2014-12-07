<%@page import="temp.JSPHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="springtags"%>
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
					<img alt="" src="/<c:out value="${appName}" />${appUser.photoURI}"
						height="150">
					<form method="POST" action="saveprofileupdate" id="contact-form"
						enctype="multipart/form-data">
						<fieldset>
							<label> Фоточка <input type="file" name="photo"
								accept="image/*">
							</label> <label> Почта <input id="email" type="text" name="email"
								value="${appUser.email}">
							</label> <label> Никнейм <input id="nick" type="text" name="nick"
								value="${appUser.nick}">
							</label> <label> Фамилия <input id="surname" type="text"
								name="surname" value="${appUser.surname}">
							</label> <label> Имя <input id="firstname" type="text"
								name="firstname" value="${appUser.firstname}">
							</label> <label> Отчество <input id="fathername" type="text"
								name="fathername" value="${appUser.fathername}">
							</label> <select required name="lang">

								<option></option>
								<option value="1">Беларуская мова</option>
								<option value="2">English</option>
								<option value="3">Русский язык</option>
							</select>

							<button id="cryptstr" class="button1">Обновить данные</button>
						</fieldset>
					</form>
					<a href="/<c:out value="${appName}" />/setnewpassword">Сменить
						пароль</a>.
				</div>

			</div>
		</div>
	</div>
</div>
