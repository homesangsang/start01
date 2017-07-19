<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<c:set var="excel" value="${requestScope.excel }"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/FileSaver.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.2.1.js"></script>
</head>
<body>
	<b id="text">${excel}</b>
	<script type="text/javascript">
	$(function(){
		$("#text").hide();
		console.log($("#text").text());
		
	});
	var blob = new Blob([$("#text").text()], {type: "text/plain;charset=utf-8"});
	saveAs(blob, "号码使用情况.xlsx");
	</script>
</body>
</html>