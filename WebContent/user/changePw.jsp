<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function checkPw(){
		var pw = document.getElementById("pw").value;
		if (pw.match("^\\s*$")) {//一个或多个空白字符
			document.getElementById("pw_msg").innerHTML = "不能为空";
			return false;
		}else{
			document.getElementById("pw_msg").innerHTML = "";
			return true;
		}
	}
	function checkRePw(){
		var pw = document.getElementById("pw").value;
		var repw = document.getElementById("repw").value;
		if (pw==repw) {
			document.getElementById("repw_msg").innerHTML = "<font color='blue'>OK</font>";
			return true;
		}else{
			document.getElementById("repw_msg").innerHTML = "<font color='red'>两次输入不一致</font>";
			return false;
		}
	}

	function checkForm(){
		var cp = checkPw();
		var crep = checkRePw();
		if (cp&&crep) {
			return true;
		} else {
			return false;
		}
	}
</script>
</head>
<%-- <body onload="ha('${_id}')"> --%>
<body>
<%
	/**
	  为了queryUserPage/userInfoManager两个jsp页面共用此页面，得接收userid
	*/
	String id = request.getParameter("userid");
	String name = request.getParameter("name");
	request.setAttribute("_id", id);//参数中的值与域中的值不同
	request.setAttribute("_name", name);
%>
<h2 align="center">修改用户‘<%=request.getAttribute("_name") %>’的密码</h2>
<form action="${pageContext.request.contextPath }/changePw" method="post" onsubmit="return checkForm()">
	<%-- <input type="hidden" name="userid" value="<%=id%>"> --%>
	<input type="hidden" name="userid" value="${requestScope._id }">
	<table>
		<tr>
			<td>新密码：</td>
			<td><input id="pw" type="password" name="pwname" onblur="checkPw()"></td>
			<td style="color:red" id="pw_msg"></td>
		</tr>
		<tr>
			<td>确认密码：</td>
			<td><input id="repw" type="password" onblur="checkRePw()"></td>
			<td id="repw_msg"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="提交" ></td>
			<td></td>
		</tr>
	</table>
</form> 
</body>
</html>