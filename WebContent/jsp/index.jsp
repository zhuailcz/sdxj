<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page isELIgnored="false"%>
<%@ include file="/jsp/common/common.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>登录页面</title>
<%=includeUI%>
</head>
<style type="text/css">
.wrapper {
	padding-top: 10px;
}
body {
	background-color:rgba(226, 230, 226, 0.52);
}

form {
	margin-top: 220px;
}
</style>
<script type="text/javascript">
	//设置Cookie保存时间
	var time = 1;
	$(document).ready(function() {
		//获取Cookie保存的用户名和密码
		var loginName = getCookieValue("cookUser");
		var password = getCookieValue("cookPass");
		if (loginName != '' && password != '') {
			$("#loginName").val(loginName);
			$("#password").val(password);
			$("#rememberPW").attr("checked", true);
		} else
			$("#rememberPW").attr("checked", false);
		$("#rememberPW").click(function() {//记住密码
			if ($(this).attr("checked") == 'checked') {
				time = 60 * 60 * 60;
			}
		});
	});
	
	
	//登录
	function doLogin() {
		var loginName = $("#loginName").val();
		var password = $("#password").val();
		if (loginName == null || loginName == "") {
			$('#loginName').focus();
			$("#loginNameDiv").addClass("has-error");
			$("#pwdDiv").removeClass("has-error");
			return false;
		}
		if (password == null || password == "") {
			$('#password').focus();
			$("#pwdDiv").addClass("has-error");
			$("#loginNameDiv").removeClass("has-error");
			return false;
		}
		//是否选择记住密码
		if ($("#rememberPW").is(':checked')) {
			setCookie('cookUser', loginName, time, '/');//set 获取用户名和密码 传给cookie
			setCookie('cookPass', password, time, '/');
		} else {
			setCookie('cookUser', "", -1, '/');
			setCookie('cookPass', "", -1, '/');  
		}

		document.forms[0].action = "login";
		document.forms[0].submit();
	}
	//重置
	function onResetClick() {
		$("#loginNameDiv").removeClass("has-error");
		$("#pwdDiv").removeClass("has-error");
		$('#loginName').val('');
		$('#password').val('');
		$('#loginName').focus();
	}
	//hours为空字符串时,cookie的生存期至浏览器会话结束。hours为数字0时,建立的是一个失效的cookie,这个cookie会覆盖已经建立过的同名、同path的cookie（如果这个cookie存在）。   
	function setCookie(name, value, hours, path) {
		var name = escape(name);
		var value = escape(value);
		var expires = new Date();
		expires.setTime(expires.getTime() + hours * 3600000);
		path = path == "" ? "" : ";path=" + path;
		_expires = (typeof hours) == "string" ? "" : ";expires="
				+ expires.toUTCString();
		document.cookie = name + "=" + value + _expires + path;
	}
	//获取cookie值    方法
	function getCookieValue(name) {
		var name = escape(name);
		//读cookie属性，这将返回文档的所有cookie   
		var allcookies = document.cookie;
		//查找名为name的cookie的开始位置   
		name += "=";
		var pos = allcookies.indexOf(name);
		//如果找到了具有该名字的cookie，那么提取并使用它的值   
		if (pos != -1) { //如果pos值为-1则说明搜索"version="失败   
			var start = pos + name.length; //cookie值开始的位置   
			var end = allcookies.indexOf(";", start); //从cookie值开始的位置起搜索第一个";"的位置,即cookie值结尾的位置   
			if (end == -1)
				end = allcookies.length; //如果end值为-1说明cookie列表里只有一个cookie   
			var value = allcookies.substring(start, end); //提取cookie的值   
			return unescape(value); //对它解码         
		} else
			return ""; //搜索失败，返回空字符串   
	}
</script>
<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-6" >
				<img alt="" src="<%=path%>/images/logo.jpg">
			</div>
		</div>
		<div class="form row">
			<div class="col-xs-12 col-sm-6" >
				<div class="form-horizontal col-sm-6 col-md-6"  style="margin-top:80px;">
				<img alt="" src="<%=path%>/images/login_bg.jpg">
				</div>
			</div>
			<div class="col-xs-12 col-sm-6">
				<form class="form-horizontal col-sm-offset-4 col-md-offset-4"
					id="loginForm" action="" method="post">
					<h3 class="form-title">系统登录</h3>
					<font color="red">${requestScope.message}</font>
					<div class="form-group-sm" id="loginNameDiv">
						<label class="sr-only">账号</label>
						<div class="input-group">
							<div class="input-group-addon">
								<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
							</div>
							<input class="form-control" id="loginName" name="loginName"
								style="width: 200px;" text" placeholder="账号" required autofocus>
						</div>
					</div>
					<br>
					<div class="form-group-sm" id="pwdDiv">
						<label class="sr-only">密码</label>
						<div class="input-group">
							<div class="input-group-addon">
								<span class="glyphicon glyphicon-lock"></span>
							</div>
							<input class="form-control" id="password" name="password"
								style="width: 200px;" type="password" placeholder="密码" required>
						</div>
					</div>
					<div class="checkbox">
						<label><input type="checkbox" id="rememberPW"
							name="rememberPW" />记住我</label>
					</div>
					<div>
						<button class="btn btn-success  btn-sm col-md-6" id="btn_login"
							style="width: 110px;" onclick="doLogin()" type="button">登录</button>
						<button class="btn btn-default  btn-sm col-md-6" id="btn_reset"
							style="width: 110px; margin-left: 20px;" onclick="onResetClick()"
							type="button">重置</button>
					</div>
				</form>
			</div>

		</div>
	</div>

</body>
</html>