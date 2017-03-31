<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 使用JSTL标签敏捷开发 -->    
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <meta charset="utf-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <!-- 导入JQuery -->
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
   <!-- 导入Bootstrap -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
   <!-- 导入Bootstrap-CSS -->
   <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<title>用户详情</title>
</head>
<body>
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
</body>
</html>