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
	<title>项目详情</title>
</head>
<body>
    <table class="table table-bordered table-hover">
           <tr class="warning">
             <td>项目编号</td>
             <td>${project.projectId}</td>
           </tr>
           <tr class="danger">
             <td>标题</td>
             <td> ${project.title}</td>
           </tr>                   
           <tr class="danger">
             <td>类别名称</td>
             <td> ${project.category.name}</td>
           </tr> 
           <tr class="info">
             <td>用户名</td>
             <td> ${project.user.name}</td>
           </tr>
           <tr class="success">
             <td>目标资金</td>
             <td> ${project.expectMoney}</td>
           </tr>
           <tr class="warning">
             <td>真实资金</td>
             <td>${project.realMoney}</td>
           </tr>
           <tr class="danger">
             <td>简介</td>
             <td> ${project.note}</td>
           </tr>
           <tr class="info">
             <td>联系方式</td>
             <td>${project.tel}</td>
           </tr>       
           <tr class="success">
             <td>状态</td>
             <td>${project.status==0?"完成":"筹集中"}</td>
           </tr>               
    </table>
    <c:forEach items="${project.photos}" var="photo">
       <img src="${pageContext.request.contextPath}/upload/${photo.name}" alt="图片${photo.photoId}" class="img-rounded">
    </c:forEach>
    <c:forEach items="${proejct.comments}" var="comment">
        <div class="alert alert-success" role="alert">${comment.content}</div>
    </c:forEach>
    <div class="alert alert-info" role="alert">
      <a href="${pageContext.request.contextPath}/index.jsp" class="alert-link">返回首页</a>
    </div>
</body>
</html>