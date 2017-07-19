<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试 接受 JSON格式的数据</title>
<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="js/json2.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
	<h3>查询号码是否被使用</h3>
	<form action="${pageContext.request.contextPath}/phone/ifHmaUsed.do" method="post">
		<table>
			<tr>
				<td>输入查询号码：</td>
				<td> <input type="text" name="hma"> </td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="submit"/> </td>
			</tr>
		</table>
	</form>
	
	
	
	<h3>插入一条记录</h3>
	<form action="${pageContext.request.contextPath}/phone/add.do" method="post">
		<table>
			<tr>
				<td>请输入号码</td>
				<td><input type="text" name="hma"> </td>
			</tr>
			<tr>
				<td>请输入企业名称</td>
				<td><input type="text" name="syqye"> </td>
			</tr>
			<tr>
				<td>请输入备注</td>
				<td><input type="text" name="sfzyong"> </td>
			</tr>
			<tr>
				<td>提交</td>
				<td><input type="submit" value="submit"/> </td>
			</tr>
		</table>
	</form>
	<h3>查询列表</h3>
	<a href="${pageContext.request.contextPath}/phone/getAll.do">查询列表</a>
	<h3>推荐一个可用的号码</h3>
	<a href="${pageContext.request.contextPath}/phone/recommand.do">查询列表</a>
</body>
</html>