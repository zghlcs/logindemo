<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	//setTimeout() 参数：1.延迟的任务--时间结束后要做的事；2.延迟时间--毫秒
	document.writeln("js:&nbsp;没找到。。。3秒后返回首页");
	setTimeout(
		function(){window.history.go(-1);},
		3000
	)
</script>
</head>
<body>
	<h1> < h1 >:没找到。。。2秒后返回首页<!-- 标签符号多添了空格就失效，成普通文本 -->
	<%
		response.getWriter().println("java:没找到。。。2秒后返回首页");
		response.getWriter().println("<br>");
		//response.setHeader("refresh", "2;url=/logindemo01/user/userInfoManager.jsp");
	%>
	</h1>
	
</body>
</html>