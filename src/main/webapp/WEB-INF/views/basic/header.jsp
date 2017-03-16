<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/menu.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
<div class="top"></div>
<div id="header">
	<div class="logo">互联网众筹平台</div>
	<div class="navigation">
		<ul>
		 	<li>欢迎您！</li>
		 	<li>${user.name}</li>
			<li><a href="${pageContext.request.contextPath}/User/doLogout">安全退出</a></li>
		</ul>
	</div>
</div>