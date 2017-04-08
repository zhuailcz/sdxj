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
<title>Insert title here</title>
<%=includeEasyUi%>
</head>
<script type="text/javascript">
 function exit(){
	 document.forms[0].action="logout";
	 document.forms[0].submit();
 }
</script>
<body>
<form action="" method="post">
欢迎[${sessionScope.user}]访问</br>
TOKEN:[${sessionScope.token}]</br>
<input type="hidden" value="${sessionScope.user}" name="user" id="user">
<input type="hidden" value="${sessionScope.token}" name="token" id="token">
<input type="button" value="退出" onclick="exit()" />
</form>
</body>
</html>