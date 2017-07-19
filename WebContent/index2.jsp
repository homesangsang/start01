<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试 接受 JSON格式的数据</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/layui/css/layui.css">
</head>
<body>
	<ul class="layui-nav">
		<li class="layui-nav-item">最新活动</li>
		<li class="layui-nav-item layui-this"><a href="">产品</a></li>
		<li class="layui-nav-item"><a href="">大数据</a></li>
		<li class="layui-nav-item"><a href="javascript:;">解决方案</a>
			<dl class="layui-nav-child">
				<dd>
					<a href="">移动模块</a>
				</dd>
				<dd>
					<a href="">后台模板</a>
				</dd>
				<dd>
					<a href="">电商平台</a>
				</dd>
			</dl></li>
	</ul>
	<div class="layui-tab layui-tab-brief" lay-filter="demo">
		<ul class="layui-tab-title">
			<li class="layui-this">查询号码是被使用</li>
			<li>新增</li>
			<li><a id="req">查询</a></li>
			<li>推荐</li>
		</ul>
		<div class="layui-tab-content">
			<div class="layui-tab-item layui-show">
				<form class="layui-form">
					<!--  input type="submit" value="submit"/>-->
					<div class="layui-form-item">
						<label class="layui-form-label">查询</label>
						<div class="layui-input-block">
							<input id="selectValue" class="layui-input" type="text"
								name="hma" required lay-verify="required" placeholder="输入要查询的号码">
						</div>
					</div>
					<div class="layui-form-item">
						<a id="queryIfUsed" class="layui-btn">查询</a>
					</div>
					<div>
						<table class="layui-table" lay-even lay-skin="nob">
							<colgroup>
								<col width="150">
								<col width="200">
								<col>
							</colgroup>
							<thead>
								<tr>
									<th>号码</th>
									<th>使用企业</th>
									<th>使用说明</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td id="show_hma"></td>
									<td id="show_syqye"></td>
									<td id="show_sfzyong"></td>
								</tr>
							</tbody>
						</table>
					</div>
				</form>
			</div>
			<div class="layui-tab-item">
				<h3>插入一条记录</h3>

				<form class="layui-form"
					action="${pageContext.request.contextPath}/phone/add.do"
					method="post">
					<div class="layui-form-item">
						<label class="layui-form-label">电话号码</label>
						<div class="layui-input-block">
							<input class="layui-input" type="text" name="hma"
								placeholder="输入号码" required lay-verify="required">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">企业名称</label>
						<div class="layui-input-block">
							<input class="layui-input" type="text" name="syqye"
								placeholder="输入企业名称" required lay-verify="required">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">使用说明</label>
						<div class="layui-input-block">
							<input class="layui-input" type="text" name="sfzyong"
								placeholder="输入备注" required lay-verify="required">
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block">
							<input class="layui-btn layui-btn-primary" type="submit"
								value="submit" />
						</div>
					</div>


				</form>
				<form action="${pageContext.request.contextPath}/phone/add.do"
					method="post"></form>
			</div>
			<div class="layui-tab-item">
				<h3>查询列表</h3>

				<a href="${pageContext.request.contextPath }/phone/download.do"
					target="_blank">导出excel文件</a>
				<div id="appendData" style="margin-top: 5px"></div>
				<%-- <c:forEach var="list" items="${requestScope.list}">
					<form action="${pageContext.request.contextPath}/phone/modify.do">
					
					<span><input  type="text" name="hma" value="${list.hma }"/></span>
					<span><input  type="text" name="syqye" value="${list.syqye }"/></span>
					<span><input  type="text" name="sfzyong" value="${list.sfzyong }"/></span>
					<span><a href="${pageContext.request.contextPath}/phone/delete?hma=${list.hma}&syqye=${list.syqye}&sfzyong=${list.sfzyong}">删除</a></span>
					<span><input type="submit" value="修改" /></span>
					</form>
				</c:forEach> --%>
			</div>
			<div class="layui-tab-item">
				<h3>推荐一个可用的号码</h3>
				<a href="${pageContext.request.contextPath}/phone/recommand.do">查询列表</a>
			</div>
		</div>
	</div>


	<script
		src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
	<script src="${pageContext.request.contextPath }/layui/layui.js"></script>
	<script src="${pageContext.request.contextPath }/js/json2.js"></script>
	<script>
		layui.use(['element','layer'], function(){
	        var element = layui.element();
	        var layer = layui.layer;
	        element.on('tab(demo)', function (data) {
	            //layer.msg(this.innerHTML);
	            //console.log(data);
	        });
	    });
		/**
		* 查询列表的ajax
		*/
		$("#req").on("click",function(){
			$.ajax("${pageContext.request.contextPath}/phone/getAll.do",
					{
						dataType:"json",
						type:"post",
						contentType:"application/json",
						/* data:JSON.stringify({type:"list"}), */
						async:true,
						success:function(data){
							console.log(data);
							$.each(data,function(index,phone1){
								var one = '<div>'+'<form action="${pageContext.request.contextPath}/phone/modify.do">'+
								'<input class="layui-input-block" style="margin-left:2px" type="text" name="hma" value="'+phone1.hma+'"/>'+
								'<input class="layui-input-block" style="margin-left:2px" type="text" name="hma" value="'+phone1.syqye+'"/>'+
								'<input class="layui-input-block" style="margin-left:2px" type="text" name="hma" value="'+phone1.sfzyong+'"/>'+
								'<a class="layui-btn layui-btn-denger" style="margin-left:3px" href="${pageContext.request.contextPath}/phone/delete.do?hma='+phone1.hma+'&syqye='+phone1.syqye+'&sfzyong='+phone1.sfzyong+'">删除</a></span>'+
										'<span><input class="layui-btn layui-btn-primary" style="margin-left:5px" type="submit" value="修改" /></span>'
										+'</div>';
								$("#appendData").append(one);
										
							});
						},
						error:function(){
							console.log("数据发送失败");
						}
					});
		});
		/**
		* 查询号码是否被使用
		*/
		$("#queryIfUsed").on("click",function(){
			var value=$("#selectValue").val();
			console.log(value); 
			/* $.ajax("${pageContext.request.contextPath}/phone/ifHmaUsed.do",
					{
						dataType:"json",
						type:"post",
						contentType:"application/json",
						data:{hma:95078000003}, 
						async:true,
						success:function(data){
							console.log(data);
						},
						error:function(){
							console.log("失败失败失败");
						}
					}); */
			$.ajax("${pageContext.request.contextPath}/phone/ifHmaUsed.do",
					{
						dataType:"json",
						type:"post",
						contentType:"application/json",
						data:JSON.stringify({hma:value}),
						async:true,
						success:function(data){
							console.log(data);
							$("#show_hma").text(data.hma);
							layer.msg(data.status);
							if(data.phone!=null){
								$("#show_syqye").text(data.phone.syqye);
								$("#show_sfzyong").text(data.phone.sfzyong);
							}
						},
						error:function(){
							console.log("数据发送失败");
						}
					});
		});	
	</script>

</body>
</html>