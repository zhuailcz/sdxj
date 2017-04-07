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
<%=includeEasyUi%>
</head>
<script type="text/javascript">
//test
function doLoginA(){
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
}
//重置
function onResetClick() {
	$("#loginNameDiv").removeClass("has-error");
	$("#pwdDiv").removeClass("has-error");
	$('#loginName').val('');
	$('#password').val('');
	$('#loginName').focus();
}
</script>
<body>
<div class="container">
   <div class="row" >
      <div class="col-xs-12 col-sm-6" >
					<h4 class="text-left text-success">哈哈哈哈哈哈</h4>
      </div>
      <div class="col-xs-12 col-sm-6" >
      <form class="form" id="loginForm" action=""
						method="post">
						<div class="form-group-sm" id="loginNameDiv">
							<label class="sr-only">账号</label>
							<div class="input-group">
							 	<div class="input-group-addon">
									<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
								</div> 
								<input class="form-control" id="loginName" name="loginName" style="width:200px;
									type="text" placeholder="账号" required autofocus>
							</div>
						</div>
						<br>
						<div class="form-group-sm" id="pwdDiv">
							<label class="sr-only">密码</label>
							<div class="input-group">
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-lock"></span>
								</div>
								<input class="form-control" id="password" name="password" style="width:200px;"
									type="password" placeholder="密码" required>
							</div>
						</div>
						<div class="checkbox">
							<label><input type="checkbox" id="rememberPW"
								name="rememberPW"/>记住我</label>
						</div>
						<div>
							<button class="btn btn-success  btn-sm col-md-6" id="btn_login" style="width:110px;"
								onclick="doLoginA()" type="button">登录</button>
							<button class="btn btn-default  btn-sm col-md-6" id="btn_reset" style="width:110px;margin-left:20px;"
								onclick="onResetClick()" type="button">重置</button>
						</div>
					</form>
      </div>

    
   </div>
</div>

</body>
</html>