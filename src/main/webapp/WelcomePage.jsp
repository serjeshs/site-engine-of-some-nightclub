<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="springtags" uri="http://www.springframework.org/tags" %>
<springtags:message code="sim" text="default text sim" />
<html>
<body>
<h1>Spring MVC internationalization example</h1>
 
Language : <a href="?language=en">English</a>|<a href="?language=by">BY</a>|<a href="?language=ru">RU</a>
 
<h3>
hello : 
</h3>
<br>
hello : 
<br> 
Current Locale : ${pageContext.response.locale} | Current Locale : ${locale}
<br>
${ language }
<br>
<spring:message var="lang"/>
<br>
</body>
</html>