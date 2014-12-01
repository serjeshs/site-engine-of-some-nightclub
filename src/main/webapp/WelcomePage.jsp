<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<body>
<h1>Spring MVC internationalization example</h1>
 
Language : <a href="?language=en">English</a>|<a href="?language=by">BY</a>|<a href="?language=ru">RU</a>
 
<h3>
hello : <spring:message code="hello" text="default text"  />
</h3>
 
Current Locale : ${pageContext.response.locale}
 
</body>
</html>