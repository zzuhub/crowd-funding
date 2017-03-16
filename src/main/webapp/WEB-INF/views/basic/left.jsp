<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/menu.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
<div class="left_menu">
	 <ul id="nav_dot">
	 		<li>
	          <h4 class="M8"><span></span>我的项目</h4>
	          <div class="list-item none">
	          </div>
	        </li>
	        <li>
	          <h4 class="M2"><span></span>类别管理</h4>
	          <div class="list-item none">
	           </div>
	        </li>
			<li>
	          <h4 class="M9"><span></span>我的照片</h4>
	          <div class="list-item none">
	          </div>
	        </li>	        
			<li>
	          <h4 class="M9"><span></span>支持列表</h4>
	          <div class="list-item none">
	          </div>
	        </li>	        
	        <li>
	          <h4 class="M3"><span></span>评论管理</h4>
	          <div class="list-item none">
	          </div>
	        </li>
	        <li>
	          <h4 class="M3"><span></span>日志维护</h4>
	          <div class="list-item none">
	          </div>
	        </li>
	        <!-- 使用shiro标签进行权限控制 -->
		    <shiro:hasRole name="admin">
				<li>
		          <h4 class="M10"><span></span>系统管理</h4>
		          <div class="list-item none">
		            <a href="#">用户列表</a>
		            <a href="${pageContext.request.contextPath}/Role/toListPage">角色列表</a>
		            <a href="${pageContext.request.contextPath}/Permission/toListPage">权限列表</a>
		            <a href="#">增加用户</a>
		            <a href="#">增加角色</a>
		          </div>
		        </li>
		  </shiro:hasRole>
    </ul>
  </div>