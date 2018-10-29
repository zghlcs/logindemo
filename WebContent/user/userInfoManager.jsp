<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

	function $(_id) {
		return document.getElementById(_id);
	}
	function getXmlHttpRequest() {
		var xhr;
		if (window.XMLHttpRequest) {
			//判断成立说明用户用的是ie7+ ,以及其他的浏览器
			xhr = new XMLHttpRequest();
		} else {
			//说明用户使用的ie7以下的版本
			xhr = new ActiveXObject("Microsoft.XMLHTTP");
		}
		return xhr;
	}

	function changeName(t,id) {
		var xhr = getXmlHttpRequest();
		
		if (t.id=="n") {
			xhr.open("post", "${pageContext.request.contextPath}/changeUsername",true);
			xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
			xhr.send("username=" + t.value+"&userid="+id);
		} else {
			xhr.open("post", "${pageContext.request.contextPath}/changeNickname",true);
			xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
			xhr.send("nickname=" + t.value+"&userid="+id);
		}
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				if (xhr.responseText) {
					alert(xhr.responseText);
				}
			}
		}
	}
	
	function childWindow(id,name){ 
		window.open(
			"changePw.jsp?userid="+id+"&name="+name,
			"",
			"height=300,width=500,top=80, left=100");
	}
	
	window.onload = function(){
		if ($("returnMsg").value!="") {
			alert($("returnMsg").value);
		}
		window.close();//只对当前父窗口通过js创建的窗口有效,因此servlet转发到哪里都行，反正将要被关闭
	}
</script>
</head>
<body>
	<!-- 当前目录是“/项目名/user/XXX”,img的src是写相对路径 -->
	<h2 align="center">
		<a href="/logindemo01/changeLogo"><img alt="头像" src="head.gif"></a>
	</h2>
	<table align="center">
		<tr>
			<td>用户名：</td>
			<td><input id="n" type="text" value="${loginUser.name }" onchange="changeName(this,'${loginUser.id }')"/></td>
			<td></td>
		</tr>
		<tr>
			<td>昵称：</td>
			<td><input id="nic" type="text" value="${loginUser.nickname }" onchange="changeName(this,'${loginUser.id }')"/></td>
			<td></td>
		</tr>
		<tr>
			<td colspan="2"><a id="p" href="javascript:;" onclick="childWindow(${loginUser.id},'${loginUser.name }')">修改密码</a></td>
			<td></td>
		</tr>
		<tr>
			<td>权限：</td>
			<td>
				<c:if test="${loginUser.role==1 }">管理员</c:if>
				<c:if test="${loginUser.role==0 }">普通用户</c:if>
			</td>
			<td></td>
		</tr>
	</table>
	<input type="hidden" id="returnMsg" value="${msg }">
	<h2 align="center"><a href="/logindemo01">返回</a></h2>
</body>
</html>