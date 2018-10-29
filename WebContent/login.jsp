<%@page import="java.net.URLDecoder"%>
<%@page import="com.lcs.util.CookieUtils"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function $(_id){
		return document.getElementById(_id);
	}

	function checkRequired(_id,errorMsg){
		var val = $(_id).value;//获取标签内的值
		if (val.match("^\\s*$")) {
			$(_id+"_msg").innerHTML = errorMsg;
			return false;
		} else {
			$(_id+"_msg").innerHTML = "";
			return true;
		}
	}
	
	function checkForm(){
		var checkUsername = checkRequired("username", "用户名不能为空");
		var checkPw = checkRequired("password", "密码不能为空");
		if (checkUsername&&checkPw) {
			return true;
		} else {
			return false;
		}
	}
	
	function rememberPw(){
		var c = $("rempw").checked;
		if (c) {
			$("rempw").checked=false;
		} else {
			$("rempw").checked=true;
		}
	}
</script>
</head>
<body>
<h1>请登录</h1>
<%
	Cookie[] cookies = request.getCookies();
	Cookie uCookie = CookieUtils.getCookieByName(cookies,"usernameCookie");
	Cookie pCookie = CookieUtils.getCookieByName(cookies, "pwCookie");
%>
<form action="${pageContext.request.contextPath }/login" method="post" onsubmit="return checkForm()">
	<table>
		<tr>
			<td>用户名：</td>
			<td><input type="text" name="username" id="username" value="<%=uCookie==null?"20个以内的字符":URLDecoder.decode(uCookie.getValue(), "utf-8") %>" onfocus="if(value!=null){value=''}" onblur="checkRequired('username','用户名不能为空')"/></td>
			<td  style="color:red"><span id="username_msg"></span></td>
		</tr>
		<tr>
			<td>密码：</td>
			<td><input type="password" name="password" id="password" value="<%=pCookie==null?"":pCookie.getValue() %>" onblur="checkRequired('password','密码不能为空')"/></td>
			<td ><div style="color:red" id="password_msg"></div></td>
		</tr>
		<tr>
			<td><input type="checkbox" id="rempw" name="rememberPw"><a onclick="rememberPw()">记住密码</a></td>
			<td><center><button>登录</button></center></td>
			<td></td>
		</tr>
	</table>
	<a href="/logindemo01/index.jsp">返回</a>
	<div style="color:red">${msg }</div>
</form>
</body>
</html>