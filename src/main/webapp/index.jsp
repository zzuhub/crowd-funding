<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/split_util.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/split_search.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/front_project_list.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/menu.js"></script>
   <!-- CSS -->
   <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
   <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
		<div class="container">
			      <br><br>
			       <form class="form-horizontal" role="form">
			           <!-- 隐藏根URL -->
			           <input type="hidden" value="${pageContext.request.contextPath}" id="root" name="root">
			           <div class="form-group">
			              <div id="searchDiv">
			                 <div class="col-sm-3"></div>
			                  <label class="control-label col-sm-1 text-success" for="kw"><h4>关键词</h4></label>
			                 <div class="col-sm-3" id="inputDiv"></div>
			                 <div class="col-sm-1" id="butDiv"></div>
			                 <div class="col-sm-1"></div>
			                 <div class="col-sm-3"></div>
			              </div>
			           </div>
			       </form>
			      <div class="alert alert-danger" id="infoDiv" role="alert"></div>
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
				                 <td colspan="2">操作</td>
				              </tr>
			              </thead>
			           </table>
			       </div>
	               <div id="splitBarDiv"></div>
	      </div>
</body>
</html>