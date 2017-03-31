<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
   <meta charset="utf-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <title>项目列表</title>
   <!-- JQuery+Bootstrap前端技术 -->
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
   <!-- 导入Layer前端弹层框架 -->
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/layer/layer.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/menu.js"></script>
    <!-- 导入JQuery验证框架 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.metadata.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/additional-methods.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Message_zh_CN.js"></script>
   <!-- CSS -->
   <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
   <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
   <!-- 加入自定义JS -->
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/user_project.js"></script>
</head>
<body>
      <jsp:include page="../basic/header.jsp"></jsp:include> 
      <div id="content">
          <jsp:include page="../basic/left.jsp"></jsp:include>
          <div class="m-right">
			   <div class="main container">
			      <br><br>
			       <!-- 隐藏用户ID,方便增加 -->
		           <input type="hidden" value="${sessionScope.user.userId}" id="userId" name="userId">	
		           <div class="headerDiv">
		                 <div class="col-sm-1"><input type="button" value="增加项目" class="btn btn-warning" id="add"></div>
		                 <div class="col-sm-3"></div>
		                 <div class="col-sm-4"></div>
		                 <div class="col-sm-4"></div>
		           </div><br><br>	
			      <div id="projectDiv">
			           <table border="1" id="projectTab" class="table table-hover table-bordered">
			              <thead>
				              <tr class="success">
				                 <td>项目编号</td>
				                 <td>标题</td>
				                 <td>类别</td>
				                 <td>用户</td>
				                 <td>目标资金</td>
				                 <td>实际资金</td>
				                 <td>电话</td>
				                 <td>状态</td>
				                 <td>简介</td>
				                 <td>操作</td>
				              </tr>
			              </thead>
			              <tbody id="dataBody">
			                  <c:forEach items="${projects}" var="project">
			                    <tr id="${project.projectId}" class="active">
				                     <td>${project.projectId}</td>
					                 <td>${project.title}</td>
					                 <td>${project.category.name}</td>
					                 <td>${project.user.name}</td>
					                 <td>${project.expectMoney}</td>
					                 <td>${project.realMoney}</td>
					                 <td>${project.tel}</td>
					                 <td>${project.status==0?"完成":"筹集中"}</td>
					                 <td>${project.note}</td>
					                 <td>
					                  <a href="#" name="edit">
					                                           编辑&nbsp;
					                   <span class="glyphicon glyphicon-edit"></span>
					                  </a>
					                </td>
			                    </tr>
			                  </c:forEach>
			              </tbody>
			           </table>
			       </div>
	           </div>
		  </div>
	  </div>
       <jsp:include page="../basic/foot.jsp"></jsp:include>
</body>
</html>