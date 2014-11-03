<%@page import="temp.JSPHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
		<footer>
			<div class="container_12">
				<div class="wrapper">
					<div class="grid_7">
						<c:if test="${guest}">
								Привет, Гость. <a href="/<c:out value="${appName}" />/registration">Зарегистрируйся
									пожалуйста</a> или <a href="/<c:out value="${appName}" />/login">войди</a>.
						</c:if>
						<c:if test="${user}">
		Привет. ${appUserName}. 
							<a href="/<c:out value="${appName}" />/event/add">Добавить событие</a>. 
							<a href="/<c:out value="${appName}" />/setnewpassword">Сменить пароль</a>.
							<a href="/<c:out value="${appName}" />/logout">Выйти</a>
						</c:if>
					</div>
					<div class="grid_5">
						<div class="social">
							<a href="https://www.facebook.com/minskafisha"><img src="/<c:out value="${appName}" />/images/facebook-icon.png" alt=""></a>
							<a href="https://vk.com/club73822272"><img src="/<c:out value="${appName}" />/images/vk.png" alt="Vkontakte"></a>
							<a href="#"><img src="/<c:out value="${appName}" />/images/twitter-icon.png" alt=""></a>
							
						    
						</div>
					</div>
				</div>
			</div>
		</footer>
		<!-- ${timeNow} -->

<!-- Yandex.Metrika counter -->
<script type="text/javascript">
(function (d, w, c) {
    (w[c] = w[c] || []).push(function() {
        try {
            w.yaCounter26868918 = new Ya.Metrika({id:26868918,
                    webvisor:true,
                    clickmap:true,
                    trackLinks:true,
                    accurateTrackBounce:true,
                    trackHash:true});
        } catch(e) { }
    });

    var n = d.getElementsByTagName("script")[0],
        s = d.createElement("script"),
        f = function () { n.parentNode.insertBefore(s, n); };
    s.type = "text/javascript";
    s.async = true;
    s.src = (d.location.protocol == "https:" ? "https:" : "http:") + "//mc.yandex.ru/metrika/watch.js";

    if (w.opera == "[object Opera]") {
        d.addEventListener("DOMContentLoaded", f, false);
    } else { f(); }
})(document, window, "yandex_metrika_callbacks");
</script>
<noscript><div><img src="//mc.yandex.ru/watch/26868918" style="position:absolute; left:-9999px;" alt="" /></div></noscript>
<!-- /Yandex.Metrika counter -->
