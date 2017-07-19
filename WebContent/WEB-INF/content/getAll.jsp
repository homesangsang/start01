<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>所有信息</title>
</head>
<body>
	<a href="${pageContext.request.contextPath }/phone/download.do" target="_blank">导出excel文件</a>
	<c:forEach var="list" items="${requestScope.list}">
		<form action="${pageContext.request.contextPath}/phone/modify.do">
		
		<span><input  type="text" name="hma" value="${list.hma }"/></span>
		<span><input  type="text" name="syqye" value="${list.syqye }"/></span>
		<span><input  type="text" name="sfzyong" value="${list.sfzyong }"/></span>
		<span><a href="${pageContext.request.contextPath}/phone/delete?hma=${list.hma}&syqye=${list.syqye}&sfzyong=${list.sfzyong}">删除</a></span>
		<span><input type="submit" value="修改" /></span>
		</form>
	</c:forEach>
</body>
</html>