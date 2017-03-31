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
<title>支持详情</title>
</head>
<body>
    <table class="table table-bordered table-hover">
           <tr class="warning">
             <td>编号</td>
             <td>${approve.approveId}</td>
           </tr>        
           <tr class="danger">
             <td>项目编号</td>
             <td>${approve.project.projectId}</td>
           </tr> 
           <tr class="info">
             <td>项目名称</td>
             <td>${approve.project.title}</td>
           </tr>
           <tr class="success">
             <td>联系电话</td>
             <td>${approve.tel}</td>
           </tr>
           <tr class="warning">
             <td>姓名</td>
             <td>${approve.name}</td>
           </tr>
           <tr class="danger">
             <td>金额</td>
             <td>${approve.money}</td>
           </tr>
           <tr class="success">
            <td>创建时间</td>
            <td>
             <fmt:formatDate value="${approve.approveTime}" pattern="yyyy-MM-dd"/>
            </td>
           </tr>
    </table>
</body>
</html>