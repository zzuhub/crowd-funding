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
   <title>用户列表</title>
   <!-- JQuery+Bootstrap前端技术 -->
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
   <!-- 导入Layer前端弹层框架 -->
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/layer/layer.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/split_util.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/split_search.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/user_list.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/menu.js"></script>
   <!-- CSS -->
   <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
   <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
      <jsp:include page="../basic/header.jsp"></jsp:include> 
      <div id="content">
          <jsp:include page="../basic/left.jsp"></jsp:include>
          <div class="m-right">
			   <div class="main container">
			      <br><br>
			       <form class="form-horizontal" role="form">
			           <div class="form-group">
			              <div id="searchDiv">
			                 <div class="col-sm-3"></div>
			                  <label class="control-label col-sm-1 text-success" for="kw"><h4>关键词</h4></label>
			                 <div class="col-sm-3" id="inputDiv"></div>
			                 <div class="col-sm-1" id="butDiv"></div>
			                 <div class="col-sm-1"><input type="button" value="增加数据" class="btn btn-warning" id="add"></div>
			                 <div class="col-sm-3" id="butDiv"></div>
			              </div>
			           </div>
			       </form>
			      <div class="alert alert-danger" id="infoDiv" role="alert"></div>
			      <div id="userDiv">
			           <table border="1" id="userTab" class="table table-hover table-bordered">
			              <thead>
				              <tr class="success">
				                 <td>编号</td>
				                 <td>名称</td>
				                 <td>密码</td>
				                 <td>登录时间</td>
				                 <td>状态</td>
				                 <td>登录出错</td>
				                 <td>创建时间</td>
				                 <td>登录地址</td>
				                 <td>角色个数</td>
				                 <td colspan="2">操作</td>
				              </tr>
			              </thead>
			           </table>
			       </div>
	               <div id="splitBarDiv"></div>
	           </div>
		  </div>
	  </div>
       <jsp:include page="../basic/foot.jsp"></jsp:include>
</body>
</html>