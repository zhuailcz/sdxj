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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<%=includeEasyUi%>
</head>
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
			$.messager.alert("提示", "用户名不能为空！", "warning", function() {
				$('#loginName').focus();
			});
			return false;
		}
		if (password == null || password == "") {
			$.messager.alert("提示", "密码不能为空！", "warning", function() {
				$('#password').focus();
			});
			return false;
		}
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
	<h3>登录页面</h3>
	<form action="" method="post">
		<font color="red">${requestScope.message}</font>
		<table>
			<tr>
				<td><label>登录名：</label></td>
				<td><input type="text" name="loginName" id="loginName" />
			</tr>
			<tr>
				<td><label>密码：</label></td>
				<td><input type="password" name="password" id="password" />
			</tr>
			<tr>
				<td><button onclick="doLogin()" type="button">登录</button></td>
				<td><button onclick="onResetClick()" type="button">重置</button></td>
			</tr>
			<tr>
				<td><input type="checkbox" id="rememberPW" name="rememberPW" />记住密码</td>
			</tr>
		</table>
	</form>
</body>
</html>