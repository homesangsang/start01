<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>${requestScope.message }</h2>
	<table>
		<tr>
			<td>号码：</td>
			<td>${requestScope.phone.hma}</td>
		</tr>
		<tr>
			<td>使用企业：</td>
			<td>${requestScope.phone.syqye}</td>
		</tr>
		<tr>
			<td>是否占用：</td>
			<td>${requestScope.phone.sfzyong}</td>
		</tr>
	</table>
</body>
</html>