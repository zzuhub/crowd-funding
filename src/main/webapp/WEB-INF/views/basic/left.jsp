<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/menu.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
<div class="left_menu">
	 <ul id="nav_dot">
	        <!-- 角色:boss,manager,clerk -->
	        <shiro:hasAnyRoles name="boss,manager">
		 		<li>
		          <h4 class="M8"><span></span>项目管理</h4>
		          <div class="list-item none">
		            <a href="${pageContext.request.contextPath}/Project/toListPage">项目列表</a>
		          </div>
		        </li>
	        </shiro:hasAnyRoles>
	 		<li>
	          <h4 class="M5"><span></span>批量导入</h4>
	          <div class="list-item none">
	            <shiro:hasPermission name="User:INSERT">
		            <a href="${pageContext.request.contextPath}/POI/User/toBatchInsertPage">导入用户</a>
	            </shiro:hasPermission>
	            <shiro:hasPermission name="User:SELECT">
		            <a href="${pageContext.request.contextPath}/POI/User/download">用户模板</a>
	            </shiro:hasPermission>
	            <shiro:hasPermission name="Comment:INSERT">
		            <a href="${pageContext.request.contextPath}/POI/Comment/toBatchInsertPage">导入评论</a>
	            </shiro:hasPermission>
	            <shiro:hasPermission name="Comment:SELECT">
		            <a href="${pageContext.request.contextPath}/POI/Comment/download">评论模板</a>
	            </shiro:hasPermission>
	          </div>
	        </li>
	        <shiro:hasAnyRoles name="boss,manager">
	        <li>
	          <h4 class="M2"><span></span>类别管理</h4>
	          <div class="list-item none">
	              <a href="${pageContext.request.contextPath}/Category/toListPage">类别列表</a>
	           </div>
	        </li>
	        </shiro:hasAnyRoles>
	        <shiro:hasAnyRoles name="manager,clerk">
			<li>
	          <h4 class="M9"><span></span>图片管理</h4>
	          <div class="list-item none">
             	 <a href="${pageContext.request.contextPath}/Photo/toListPage">图片列表</a>
                 <a href="${pageContext.request.contextPath}/Photo/toInsertPage">上传图片</a>
	          </div>
	        </li>	        
	        </shiro:hasAnyRoles>
	        <shiro:hasAnyRoles name="boss,manager">
			<li>
	          <h4 class="M9"><span></span>支持管理</h4>
	          <div class="list-item none">
	             <a href="${pageContext.request.contextPath}/Approve/toListPage">支持列表</a>
	          </div>
	        </li>	        
	        </shiro:hasAnyRoles>
	        <shiro:hasAnyRoles name="manager,clerk">
	        <li>
	          <h4 class="M3"><span></span>评论管理</h4>
	          <div class="list-item none">
	             <a href="${pageContext.request.contextPath}/Comment/toListPage">评论列表</a>
	          </div>
	        </li>
	        </shiro:hasAnyRoles>
	        <shiro:hasAnyRoles name="boss,manager">
	 		<li>
	          <h4 class="M1"><span></span>数据分析</h4>
	          <div class="list-item none">
	            <a href="${pageContext.request.contextPath}/Echarts/toCategoryEchartsPage">类别统计</a>
	          </div>
	        </li>
	        </shiro:hasAnyRoles>	        
	        <!-- 使用shiro标签进行权限控制 -->
		    <shiro:hasRole name="admin">
				<li>
		          <h4 class="M10"><span></span>系统管理</h4>
		          <div class="list-item none">
		            <a href="${pageContext.request.contextPath}/User/toListPage">用户列表</a>
		            <a href="${pageContext.request.contextPath}/Role/toListPage">角色列表</a>
		            <a href="${pageContext.request.contextPath}/Permission/toListPage">权限列表</a>
		          </div>
		        </li>
		  </shiro:hasRole>
         <!-- 最终使用Shiro动态生成这个菜单 -->
         <!-- 用户才会生成 -->
         <shiro:hasRole name="user">
			 <li>
	          <h4 class="M5"><span></span>我的众筹</h4>
	          <div class="list-item none">
	            <a href="${pageContext.request.contextPath}/User/toUserDetailPage/${sessionScope.user.userId}">个人信息</a>
	            <a href="${pageContext.request.contextPath}/User/toEditPassPage/${sessionScope.user.userId}">修改密码</a>
	            <a href="${pageContext.request.contextPath}/Project/toUserListPage/${sessionScope.user.userId}">我的项目</a>
	            <a href="${pageContext.request.contextPath}/Photo/toUserInsertPage/${sessionScope.user.userId}">上传图片</a>
	          </div>
	        </li>		  
         </shiro:hasRole>
    </ul>
  </div>