<%@page import="temp.JSPHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="/<c:out value="${appName}" />/autocomplete-master/demo/demo.css">
<script src="/<c:out value="${appName}" />/autocomplete-master/demo/prettify.js" type="text/javascript"></script>
<script type="text/javascript">
window.prettyPrint && prettyPrint()
</script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/<c:out value="${appName}" />/autocomplete-master/jquery.autocomplete.css">
<script src="/<c:out value="${appName}" />/autocomplete-master/jquery.autocomplete.js" type="text/javascript"></script>
<script>



/*********************************** remote start *****************************************************/
$('#remote_input').autocomplete({
valueKey:'name',
source:[{
	url:"/<c:out value="${appName}" />/places?&s=%QUERY%",
	type:'remote',
	getValueFromItem:function(item){
		return item.name
	},
	ajax:{
		dataType : 'json'	
	}
}]});

$('#open').click(function(){
	$('#remote_input').trigger('updateContent.xdsoft');
	$('#remote_input').trigger('open.xdsoft');
	$('#remote_input').focus();
});
/*********************************** remote end *****************************************************/

</script>