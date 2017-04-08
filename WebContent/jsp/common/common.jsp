<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();  //输出内容:"/xj"
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";    //输出内容:"http://127.0.0.1:8087/gongping/"
	request.setCharacterEncoding("UTF-8");

	String includeEasyUi="";
//	includeEasyUi+="<script type='text/javascript' src='"+path+"/skin/js/easyui/jquery.min.js'></script>";
//	includeEasyUi+="<script type='text/javascript' src='"+path+"/skin/js/easyui/jquery.easyui.min.js'></script>";
//	includeEasyUi+="<script type='text/javascript' src='"+path+"/skin/js/easyui/locale/easyui-lang-zh_CN.js'></script>";
//	includeEasyUi+="<link rel='stylesheet' type='text/css' href='"+path+"/skin/js/easyui/themes/default/easyui.css'/>";
//	includeEasyUi+="<link rel='stylesheet' type='text/css' href='"+path+"/skin/js/easyui/themes/icon.css'/>";
	

	// 新 Bootstrap 核心 CSS 文件
	includeEasyUi+="<link rel='stylesheet' type='text/css' href='"+path+"/bootstrap/css/bootstrap.css'/>";
	// 可选的Bootstrap主题文件（一般不用引入）
	includeEasyUi+="<link rel='stylesheet' href='"+path+"/bootstrap/css/bootstrap-theme.min.css'>";
	//jQuery文件。务必在bootstrap.min.js 之前引入 
	includeEasyUi+="<script src='"+path+"/js/jquery.min.js'></script>";
 	//最新的 Bootstrap 核心 JavaScript 文件 
	includeEasyUi+="<script src='"+path+"/bootstrap/js/bootstrap.min.js'></script>";
%>