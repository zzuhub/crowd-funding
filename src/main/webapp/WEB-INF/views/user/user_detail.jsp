<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 使用JSTL标签敏捷开发 -->    
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <meta charset="utf-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <title>用户列表</title>
   <!-- JQuery+Bootstrap前端技术 -->
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
   <!-- 导入Layer前端弹层框架 -->
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/layer/layer.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/menu.js"></script>
   <!-- CSS -->
   <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
   <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
<title>用户信息</title>
</head>
<body>
    <jsp:include page="../basic/header.jsp"></jsp:include> 
    <div id="content">
        <jsp:include page="../basic/left.jsp"></jsp:include>
        <div class="m-right">
          <div class="main container"><br><br>
		    <table class="table table-bordered table-hover">
		           <tr class="warning">
		             <td>编号</td>
		             <td>${user.userId}</td>
		           </tr>        
		           <tr class="danger">
		             <td>名称</td>
		             <td>${user.name}</td>
		           </tr> 
		           <tr class="info">
		             <td>密码</td>
		             <td>${user.password}</td>
		           </tr>
		           <tr class="success">
		             <td>登陆时间</td>
		             <td>
		              <fmt:formatDate value="${user.lastLogin}" pattern="yyyy-MM-dd HH:mm:ss"/>
		             </td>
		           </tr>
		           <tr class="warning">
		             <td>登录地址</td>
		             <td>${user.lastIP}</td>
		           </tr>
		           <tr class="danger">
		             <td>状态</td>
		             <td> ${user.status==1?"正常":"锁定"}</td>
		           </tr>
		           <tr class="info">
		            <td>出错次数</td>
		            <td>${user.loginErr}</td>
		           </tr>
		           <tr class="success">
		            <td>创建时间</td>
		            <td>
		             <fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
		            </td>
		           </tr>
		           <tr class="warning">
		              <td>角色</td>
		              <td>
		                <c:forEach items="${user.roles}" var="role">
		                   ${role.name}&nbsp;
		                </c:forEach>
		              </td>
		           </tr>                   
		    </table>
          </div>
        </div>
    </div>
    <jsp:include page="../basic/foot.jsp"></jsp:include>
</body>
</html>