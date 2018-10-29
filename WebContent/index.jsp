<%@page import="com.lcs.pojo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>首页</h1>
<% //java注释 %>
<!-- 判断展示内容 -->
<c:if test="${not empty loginUser }">
	<h2>欢迎</h2> 
	<span style="color:blue"><a href="/logindemo01/user/userInfoManager.jsp">${loginUser.nickname }</a></span>
	<a href="${pageContext.request.contextPath }/invalidate.jsp">注销</a>
	<hr/>
	<c:if test="${loginUser.role==1 }">
		<a href="${pageContext.request.contextPath }/pageQuery">用户管理</a>
	</c:if>
</c:if>
<c:if test="${empty loginUser }">
	<a href="${pageContext.request.contextPath }/login.jsp">请登录</a>&nbsp;
	<a href="${pageContext.request.contextPath }/register.jsp">注册</a>
</c:if>
</body>
</html>