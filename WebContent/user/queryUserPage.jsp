<%@page import="com.lcs.pojo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html><!-- 这是HTML5的文档类型 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function $(_id){
		return document.getElementById(_id);
	}
	function getXmlHttpRequest(){
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
	
	function changeNickname(t,uid){
		var xhr = getXmlHttpRequest();
		xhr.open("get", "${pageContext.request.contextPath}/changeNickname?userid="+uid+"&nickname="+t.value, true);
		xhr.send();
		xhr.onreadystatechange = function() {
			if (xhr.readyState==4 && xhr.status==200) {
				if (xhr.responseText) {
					alert(xhr.responseText);
				}
			}
		}
	}
	function changeUsername(t,uid){
		var xhr = getXmlHttpRequest();
		xhr.open("post", "${pageContext.request.contextPath}/changeUsername", true);
		xhr.setRequestHeader( 'Content-Type', 'application/x-www-form-urlencoded' );
		xhr.send("userid="+uid+"&username="+t.value);
		xhr.onreadystatechange = function() {
			if (xhr.readyState==4 && xhr.status==200) {
				if (xhr.responseText) {
					alert(xhr.responseText);
				}
			}
		}
	}
	
	function changeRole(uid){
		//location.href = "${pageContext.request.contextPath}/changeRole?userid="+uid+"&location.search.subString(1)";//舍弃了第0位的‘?’                         
		location.href = "${pageContext.request.contextPath}/changeRole"+location.search+"&userid="+uid;                                        
	}
	
	function del(uid,name){
		if (confirm("确定删除用户:"+name)) {
			location.href = "${pageContext.request.contextPath}/delete"+location.search+"&userid="+uid;                                        
		}
	}
	function checkAllorNot(t){
		var ids = document.getElementsByName("checkboxName");
		if (t==null) {
			if ($("call").checked) {
				$("call").checked = false;
			} else {
				$("call").checked = true;
			}
			t = $("call");
		}		
		for (var i = 0; i < ids.length; i++) {
			if (t.checked) {
				ids[i].checked = true;
			}else{
				ids[i].checked = false;
			}
		}
	}
	function checkConverse(){
		var ids = document.getElementsByName("checkboxName");
		for (var i = 0; i < ids.length; i++) {
			if (ids[i].checked) {
				ids[i].checked = false;
			} else {
				ids[i].checked = true;
			}
		}
	}
	function delBatch(){
		var ids = document.getElementsByName("checkboxName");
		for (var i = 0; i < ids.length; i++) {
			if (ids[i].checked) {
				if (confirm("确认批量删除？")) {
					$("ps").value = location.search;
					return true;
				} else{
					return false;
				}
			}
		}
		return false;
	}
	
	function childWindow(id,name){
		open(
			"user/changePw.jsp?userid="+id+"&name="+name, 
			"", 
			"height=300,width=500,top=80,left=100"
		);
	}
	
	function conditionQuery(pageNum){
		var val = $("cn").options[$("cn").selectedIndex].value;
		var param = $("cv").value;
		if (val!=""&&param!=""&&param!=null) {
			location.search = "?pageNum="+pageNum+"&val="+val+"&param="+param;
		}else{
			location.search = "";
			//location.href = "${pageContext.request.contextPath}/pageQuery";
		}
	}
	function pageQuery(pageNum){
		var val = $("cn").options[$("cn").selectedIndex].value;
		var param = $("cv").value;
		location.search = "?pageNum="+pageNum+"&val="+val+"&param="+param;
	}
</script>
<style type="text/css">
	.header{text-align: center;width: 100%}
	.header span{float: left;}
</style>
</head>
<%-- <%
	User loginUser = (User)request.getSession().getAttribute("loginUser");
	if(loginUser.getRole()==0){
		response.getWriter().print("权限不足，请联系管理员解决");
		response.setHeader("refresh", "3;url="+request.getContextPath()+"/index.jsp");
	}
%> --%>
<body>
<center><h2>用户管理</h2></center>
<div class="header">
	<span><a href="/logindemo01/index.jsp" style="color: red;font-weight: bold; ">返回首页</a></span>
	<select id="cn">
		<option selected="selected" value="">*查询项*</option>
		<option value="1" 
			<c:if test="${conditionName==1 }">selected="selected"</c:if>
		>用户名</option>
		<option value="2"
			<c:if test="${conditionName==2 }">selected="selected"</c:if>
		>昵称</option>
		<option value="3"
			<c:if test="${conditionName==3 }">selected="selected"</c:if>
		>权限(0/1)</option>
	</select>
	<input type="text" id="cv" value="${conditionParam }"><input type="button" value="查询" onclick="conditionQuery(1)">
</div><hr>
<form action="${pageContext.request.contextPath }/delBatch" onsubmit="return delBatch()">
	<input type="hidden" id="ps" name="params">
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="批量删除">
	<table border="1px" cellpadding="5px" cellspacing="0" align="center" width="90%">
		<tr>
			<th><input id="call" type="checkbox" onclick="checkAllorNot(this)"><a onclick="checkAllorNot()">全选</a>/<input type="button" value="反选" onclick="checkConverse()"></th>
			<th>用户名</th>
			<th>昵称</th>
			<th>密码</th>
			<th>权限</th>
			<th>删除</th>
		</tr>
		<c:forEach var="user" items="${page.list }">
			<tr>
				<td align="center"><input name="checkboxName" type="checkbox" value="${user.id }" ></td>
				<td><input type="text" value="${user.name }" onchange="changeUsername(this,'${user.id }')"></td>
				<td><input type="text" value="${user.nickname }" onchange="changeNickname(this,'${user.id }')"></td>
				<td><a href="javascript:void(0)" onclick="childWindow(${user.id},'${user.name }')">修改密码</a></td>
				<td>${user.role }/<a href="javascript:;" onclick="changeRole(${user.id })">修改</a></td>
				<td><a href="javascript:;" onclick="del('${user.id }','${user.name }')">删除</a></td>
			</tr>
		</c:forEach>
	</table>
</form>
<div style="text-align: center;margin: 20px">
	<!-- 设置起止参数 -->
	<c:if test="${page.totalPageCount<=10 }">
		<c:set var="start" value="1"></c:set>
		<c:set var="end" value="${page.totalPageCount }"></c:set>
	</c:if>
	<c:if test="${page.totalPageCount>10 }">
		<c:if test="${page.pageNum<=6 }">
			<c:set var="start" value="1"></c:set>
			<c:set var="end" value="10"></c:set>
		</c:if>
		<c:if test="${page.pageNum>6 && !(page.pageNum+4>page.totalPageCount) }">
			<c:set var="start" value="${page.pageNum-5 }"></c:set>
			<c:set var="end" value="${page.pageNum+4 }"></c:set>
		</c:if>
		<c:if test="${page.pageNum+4>page.totalPageCount }">
			<c:set var="start" value="${page.totalPageCount-9 }"></c:set>
			<c:set var="end" value="${page.totalPageCount }"></c:set>
		</c:if>
	</c:if>
		
	<!-- 显示 -->
	<table align="center">
		<tr>
			<td>
				<a href="javascript:void(0)" onclick="pageQuery(1)">首页</a>
			</td>
			<td>
				<c:if test="${page.pageNum!=1 }">
					<a href="javascript:void(0)" onclick="pageQuery(${page.pageNum-1})">上一页</a>
				</c:if>
			</td>
			<td>
				<c:forEach begin="${start }" end="${end }" step="1" var="i">
					<!-- 当前页普通文本，其他是超链接 -->
					<c:if test="${page.pageNum==i }"><font color="red">${i }</font></c:if>
					<c:if test="${page.pageNum!=i }">
						<a  href="javascript:void(0)" onclick="pageQuery(${i })">${i }</a>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:if test="${page.pageNum!=page.totalPageCount && page.totalPageCount!=0 }">
					<a href="javascript:void(0)" onclick="pageQuery(${page.pageNum+1})">下一页</a>
				</c:if>
			</td>
			<td>
				<a href="javascript:void(0)" onclick="pageQuery(${page.totalPageCount})">尾页</a>
			</td>
			<td>
				<select onchange="pageQuery(this.options[this.selectedIndex].value)">
					<c:forEach begin="1" end="${page.totalPageCount }" step="1" var="j">
						<option value="${j }"
							<c:if test="${page.pageNum==j }">selected="selected"</c:if>
						>${j }</option>
					</c:forEach>
				</select>
				共${page.totalCount }条/${page.totalPageCount }页
			</td>
		</tr>
	</table>
</div>
</body>
</html>