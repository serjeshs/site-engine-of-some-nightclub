<%@ page import="temp.JSPHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="by">
<head>
<meta charset="utf-8">
<title>${title}</title>
<tiles:insertAttribute name="headScripts" />
</head>
<body>
	<header>
		<tiles:insertAttribute name="header" />
	</header>
	<tiles:insertAttribute name="body" />
	<footer>
		<tiles:insertAttribute name="footer" />
	</footer>
</body>
<tiles:insertAttribute name="footer2" />
</html>
