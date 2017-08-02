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
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/b.page.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/b.page.bootstrap3.css">
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
			<li><a id="recommand">推荐</a></li>
		</ul>
		<div class="layui-tab-content">
			<!-- 查询号码是被使用 -->
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
			<!-- 新增 -->
			<div class="layui-tab-item">
				<h3>插入一条记录</h3>

				<form class="layui-form">
					<div class="layui-form-item">
						<label class="layui-form-label">电话号码</label>
						<div class="layui-input-block">
							<input id="insert_hma" class="layui-input" type="text" name="hma"
								placeholder="输入号码" required lay-verify="required">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">企业名称</label>
						<div class="layui-input-block">
							<input id="insert_syqye" class="layui-input" type="text" name="syqye"
								placeholder="输入企业名称" required lay-verify="required">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">使用说明</label>
						<div class="layui-input-block">
							<input id="insert_sfzyong" class="layui-input" type="text" name="sfzyong"
								placeholder="输入备注" required lay-verify="required">
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block">
							<!-- <input class="layui-btn layui-btn-primary" type="submit"
								value="submit" /> -->
								<a id="insert" class="layui-btn">添加</a>
						</div>
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
									<td id="insert_show_hma"></td>
									<td id="insert_show_syqye"></td>
									<td id="insert_show_sfzyong"></td>
								</tr>
							</tbody>
						</table>
					</div>
				</form>
				<form action="${pageContext.request.contextPath}/phone/add.do"
					method="post"></form>
			</div>
			<!-- 查询 -->
			<div class="layui-tab-item">
				<h3>查询列表</h3>

				<a href="${pageContext.request.contextPath }/phone/download.do"
					target="_blank">导出excel文件</a>
				<div id="appendData" style="margin-top: 5px"></div>
				<div id="appendData_page"></div>
			</div>
			<!-- 推荐 -->
			<div class="layui-tab-item">
				<h3>推荐一个可用的号码</h3>
				可用号码：<span id="show_recommand"></span>
			</div>
		</div>
	</div>


	<script src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
	<script src="${pageContext.request.contextPath }/layui/layui.js"></script>
	<script src="${pageContext.request.contextPath }/laypage/laypage.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/b.page.js"></script>
	<script src="${pageContext.request.contextPath }/js/json2.js"></script>
	<script>
		layui.use(['element','layer'], function(){
	        var element = layui.element();
	        var layer = layui.layer;
	        element.on('tab(demo)', function (data) {
	        });
	    });
		var count;
		$(document).ready(function(){
			$.ajax("${pageContext.request.contextPath}/phone/count.do",
					{
						dataType:"json",
						type:"post",
						contentType:"application/json",
						//data:JSON.stringify({hma:value}),
						async:true,
						success:function(data){
							count = data;
							console.log("count:"+count);
						},
						error:function(){
							console.log("获取记录总数失败，请检查刷新当前页面并检查数据库是否开启");
						}
					});
		});
		/**
		* 查询列表的ajax
		*/
		$("#req").on("click",function (){
			function demo(curr){
			$("#appendData").empty();
				$.getJSON('${pageContext.request.contextPath}/phone/getAll.do',
						{
							pageNumber:curr || 1,
							pageSize:5
						},
						function (data) {
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
							laypage({
								cont: 'appendData_page',
								pages: Math.ceil(count/5),
								curr : curr || 1,
								jump: function(obj,first){
									if(!first){
										demo(obj.curr);
										console.log(obj.curr);
									}
								}
							});
						});
				}
				demo();
		});
		/*$("#req").on("click",function(){
			$("#appendDataIn").empty();
			$("#appendData").bPage({
				url:'${pageContext.request.contextPath}/phone/getAll.do',
				asyncLoad:true,
				pageSizeMenu:[5,10,20,40,100,200],
				serverSidePage:false,
				render:function(data){
					$("#appendDataIn").empty();
					console.log(data);
					$.each(data,function(index,data){
						var one = '<div>'+'<form method="post" action="${pageContext.request.contextPath}/phone/modify.do">'+
						'<input class="layui-input-block" style="margin-left:2px" type="text" name="hma" value="'+data.hma+'"/>'+
						'<input class="layui-input-block" style="margin-left:2px" type="text" name="syqye" value="'+data.syqye+'"/>'+
						'<input class="layui-input-block" style="margin-left:2px" type="text" name="sfzyong" value="'+data.sfzyong+'"/>'+
						'<a class="layui-btn layui-btn-denger" style="margin-left:3px" href="${pageContext.request.contextPath}/phone/delete.do?hma='+data.hma+'&syqye='+data.syqye+'&sfzyong='+data.sfzyong+'">删除</a></span>'+
								'<span><input class="layui-btn layui-btn-primary" style="margin-left:5px" type="submit" value="修改" /></span>'
								+'</div>';
						$("#appendDataIn").append(one);
					});
				}
			});
		});*/
		/**
		* 修改bug，在增加号码的时候，如果号码被占用，则返回“号码被占用的错误”
		*/
		$("#insert_hma").blur(function(){
			var value=$("#insert_hma").val();
			console.log(value); 
			$.ajax("${pageContext.request.contextPath}/phone/ifHmaUsed.do",
					{
						dataType:"json",
						type:"post",
						contentType:"application/json",
						data:JSON.stringify({hma:value}),
						async:true,
						success:function(data){
							console.log(data);
							//$("#show_hma").text(data.hma);
							if(data.phone!=null){
								layer.msg("号码已经使用，被分配给了"+data.phone.syqye+":"+data.phone.sfzyong+"若不更改号码，则原始号码的信息会被覆盖！");
							}
						},
						error:function(){
							console.log("数据发送失败");
						}
					});
		});
		/* $("#req").on("click",function(){
			$("#appendData").empty();
			$.ajax("${pageContext.request.contextPath}/phone/getAll.do",
					{
						dataType:"json",
						type:"post",
						contentType:"application/json",
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
		}); */
		/**
		* 查询号码是否被使用
		*/
		$("#queryIfUsed").on("click",function(){
			var value=$("#selectValue").val();
			console.log(value); 
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
		/**
		* 添加一条记录
		*/
		$("#insert").on("click",function(){
			var insert_hma = $("#insert_hma").val();
			var insert_syqye = $("#insert_syqye").val();
			var insert_sfzyong = $("#insert_sfzyong").val();
			console.log(insert_hma);console.log(insert_syqye);console.log(insert_sfzyong);
			$.ajax("${pageContext.request.contextPath}/phone/add.do",
					{
						dataType:"json",
						type:"post",
						contentType:"application/json",
						data:JSON.stringify({hma:insert_hma,syqye:insert_syqye,sfzyong:insert_sfzyong}),
						async:true,
						success:function(message){
							console.log(message);
							
							$("#insert_show_hma").text(message.hma);
							layer.msg(message.status);
							if(message.phone!=null){
								$("#insert_show_syqye").text(message.phone.syqye);
								$("#insert_show_sfzyong").text(message.phone.sfzyong);
							}
						},
						error:function(){
							console.log("数据发送失败");
						}
					});
		});
		/**
		* 号码推荐
		*/
		$("#recommand").on("click",function(){
			$.ajax("${pageContext.request.contextPath}/phone/recommand.do",
					{
						dataType:"json",
						type:"post",
						contentType:"application/json",
						async:true,
						success:function(data){
							console.log(data);
							$("#show_recommand").text(data);
						},
						error:function(){
							console.log("数据发送失败");
						}
					});
		});
	</script>

</body>
</html>