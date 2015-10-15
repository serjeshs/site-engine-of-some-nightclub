<%@page import="temp.JSPHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="springtags"%>
<div class="container_12">
	<div class="wrapper">
		<div class="grid_7">
			<c:if test="${guest}">
				<springtags:message code="hello_guest" text="Привет, Гость." />
				<a href="/${appName}/registration"> 
				<springtags:message code="register_please" text="Зарегистрируйся пожалуйста" />
				</a>
				<springtags:message code="or" text=" или " />
				<a href="/<c:out value="${appName}" />/login"><springtags:message
						code="sing_in" text=" войди " /></a>.
						</c:if>
			<c:if test="${user}">
		Привет. ${appUserName}. 
							<a href="/<c:out value="${appName}" />/event/add">Добавить
					событие</a>. 
							<a href="/<c:out value="${appName}" />/profile">Личный
					кабинет</a>.
							<a href="/<c:out value="${appName}" />/logout">Выйти</a>
			</c:if>
		</div>
		<div class="grid_5">
			<div class="social">
				<a href="https://www.facebook.com/havefunby"><img
					src="/<c:out value="${appName}" />/images/facebook-icon.png"
					alt="Facebook"></a> <a href="https://vk.com/havefunby"><img
					src="/<c:out value="${appName}" />/images/vk.png" alt="Vkontakte"></a>
				<a href="https://twitter.com/havefunby"><img
					src="/<c:out value="${appName}" />/images/twitter-icon.png"
					alt="Twitter"></a>


			</div>
		</div>
	</div>
</div>
<!-- ${timeNow} -->

<!-- Yandex.Metrika counter -->
<script type="text/javascript">
	(function(d, w, c) {
		(w[c] = w[c] || []).push(function() {
			try {
				w.yaCounter26868918 = new Ya.Metrika({
					id : 26868918,
					webvisor : true,
					clickmap : true,
					trackLinks : true,
					accurateTrackBounce : true,
					trackHash : true
				});
			} catch (e) {
			}
		});

		var n = d.getElementsByTagName("script")[0], s = d
				.createElement("script"), f = function() {
			n.parentNode.insertBefore(s, n);
		};
		s.type = "text/javascript";
		s.async = true;
		s.src = (d.location.protocol == "https:" ? "https:" : "http:")
				+ "//mc.yandex.ru/metrika/watch.js";

		if (w.opera == "[object Opera]") {
			d.addEventListener("DOMContentLoaded", f, false);
		} else {
			f();
		}
	})(document, window, "yandex_metrika_callbacks");
</script>
<noscript>
	<div>
		<img src="//mc.yandex.ru/watch/26868918"
			style="position: absolute; left: -9999px;" alt="" />
	</div>
</noscript>
<!-- /Yandex.Metrika counter -->
