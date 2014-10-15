<%@page import="temp.JSPHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<head>
<title>${title}</title>
<meta charset="utf-8">
<link
	href='http://fonts.googleapis.com/css?family=Lato:300,400,700,300italic'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="/<c:out value="${appName}" />/css/reset.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="/<c:out value="${appName}" />/css/grid.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="/<c:out value="${appName}" />/css/style.css" type="text/css"
	media="screen">
<script type="text/javascript" src="/<c:out value="${appName}" />/js/jquery.js"></script>
<script type="text/javascript" src="/<c:out value="${appName}" />/js/jquery-migrate-1.1.1.js"></script>

<!-- Put this script tag to the <head> of your page -->
<script type="text/javascript" src="//vk.com/js/api/openapi.js?115"></script>
<!-- 
<link rel="stylesheet" href="/<c:out value="${appName}" />/css/camera.css" type="text/css" media="screen">
<script type="text/javascript" src="/<c:out value="${appName}" />/js/camera.js"></script>
<script type="text/javascript" src="/<c:out value="${appName}" />/js/jquery.easing.1.3.js"></script>
<script>
    jQuery(function(){      
      jQuery('#camera_wrap_1').camera({
        height: '317px',
        loader: false,
        pagination: false,
        thumbnails: false
      });
    });
</script>
-->

<!--[if lt IE 8]><div style='text-align:center'><a href="http://www.microsoft.com/windows/internet-explorer/default.aspx?ocid=ie6_countdown_bannercode"><img src="http://www.theie6countdown.com/img/upgrade.jpg"border="0"alt=""/></a></div><![endif]-->
<!--[if lt IE 9]>
	   	<script src="/<c:out value="${appName}" />/js/html5shiv.js"></script>
	   	<link rel="stylesheet" href="/<c:out value="${appName}" />/css/ie.css" type="text/css" media="screen">
	   	<link href='http://fonts.googleapis.com/css?family=Lato:300italic' rel='stylesheet' type='text/css'>
	   	<link href='http://fonts.googleapis.com/css?family=Lato:300' rel='stylesheet' type='text/css'>
	   	<link href='http://fonts.googleapis.com/css?family=Lato:400' rel='stylesheet' type='text/css'>
	   	<link href='http://fonts.googleapis.com/css?family=Lato:700' rel='stylesheet' type='text/css'>
    <![endif]-->
</head>