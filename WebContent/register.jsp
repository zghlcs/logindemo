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
	
	function checkNotNull(id){
		var val = $(id).value;
		if (val.match("^\\s*$")) {
			$(id+"msg").innerHTML = "<font color='red'>不能为空</font>";
			return false;
		} else {
			$(id+"msg").innerHTML = "";
			return true;
		}
	}
	function checkUsername(){
		if (!checkNotNull("un")) {
			return;
		}
		var xhr = getXmlHttpRequest();
		xhr.open("post", "${pageContext.request.contextPath}/checkName", true);
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xhr.send("name="+$("un").value);
		xhr.onreadystatechange = function(){
			if (xhr.readyState==4 && xhr.status==200) {
				if (xhr.responseText) {
					$("unmsg").innerHTML = xhr.responseText;
				}
			}
		}
	}
	function checkRePw(){
		if ($("pw").value==$("repw").value) {
			$("repwmsg").innerHTML = "<font color='blue'>OK</font>";
			return true;
		} else {
			$("repwmsg").innerHTML = "<font color='red'>两次密码输入不一致</font>";
			return false;
		}
	}
	
	function checkForm(){
		if ($("unmsg").innerHTML.indexOf("可用")>=0&&checkNotNull("pw")&&checkRePw()
			&&checkNotNull("nn")) {
			return true;
		} else {
			$("msg").innerHTML = "输入正确再提交";
			return false;
		}
	}
</script>
</head>
<body>
<form action="${pageContext.request.contextPath }/register" method="post" onsubmit="return checkForm()">
	<table>
		<tr>
			<td>用户名：</td>
			<td><input type="text" id="un" name="name" onblur="checkUsername()"></td>
			<td id="unmsg"></td>
		</tr>
		<tr>
			<td>密码：</td>
			<td><input type="password" id="pw" name="password" onblur="checkNotNull('pw')"></td>
			<td id="pwmsg"></td>
		</tr>
		<tr>
			<td>确认密码：</td>
			<td><input type="password" id="repw" onblur="checkRePw()"></td>
			<td id="repwmsg"></td>
		</tr>
		<tr>
			<td>昵称：</td>
			<td><input type="text" id="nn" name="nickname" onblur="checkNotNull('nn')"></td>
			<td id="nnmsg"></td>
		</tr>
		<tr>
			<td><input type="submit" value="注册"></td>
			<td><a href="/logindemo01/index.jsp">取消</a></td>
		</tr>
	</table>
</form>
<div id="msg" style="color:red">${msg }</div>
</body>
</html>