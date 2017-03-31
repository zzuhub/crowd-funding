<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 使用JSTL标签敏捷开发 -->    
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
   <!-- 导入JQuery验证框架 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.metadata.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/additional-methods.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Message_zh_CN.js"></script>
    <!-- 导入Layer前端弹层框架 -->
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/layer/layer.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/user_project.js"></script>
   <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
         <!-- 这里放置Action只是为了AJAX调用Action作为URL发送异步请求,避免恶心的相对路径(使用了IFrame技术和AJAX的缺点) -->
         <form action="${pageContext.request.contextPath}/Project/doCreate" method="post" id="projectInsertForm" class="form-horizontal">
            <!-- 隐藏用户ID,方便增加 -->
		    <input type="hidden" value="${sessionScope.user.userId}" id="userId" name="userId">	
            <fieldset>
               <legend >
                    <label >
                        <span class="glyphicon glyphicon-plus"></span>
                        &nbsp;增加项目
                    </label>
                </legend>
                <div class="form-group" id="categoryIdDiv">
                    <label class="control-label col-sm-3" for="categoryId">类别</label>
                    <div class="col-sm-5">
                       <select class="form-control" id="categoryId" name="categoryId" >
						       <option value="-1">==========请选择类别===========</option>
						   <!-- 迭代类别 -->
						   <c:forEach var="category" items="${categories}">
						          <option value="${category.id}"> ${category.name}</option>
						   </c:forEach>
						</select>
                    </div>
                    <span class="col-sm-4" id="categoryIdMsg"></span>
                </div>  
                <div class="form-group" id="titleDiv">
                    <label class="control-label col-sm-3" for="title">标题</label>
                    <div class="col-sm-5">
                        <input type="text" name="title" id="title" class="form-control" placeholder="请输入标题">
                    </div>
                    <span class="col-sm-4" id="titleMsg"></span>
                </div>                                             
                <div class="form-group" id="expectMoneyDiv">
                    <label class="control-label col-sm-3" for="expectMoney">目标资金</label>
                    <div class="col-sm-5">
                        <input type="text" name="expectMoney" id="expectMoney" class="form-control" placeholder="请输入目标资金" >
                    </div>
                    <span class="col-sm-4" id="expectMoneyMsg"></span>
                </div>
                <div class="form-group" id="noteDiv">
                    <label class="control-label col-sm-3" for="note">简介</label>
                    <div class="col-sm-5">
                        <input type="text" name="note" id="note" class="form-control" placeholder="请输入简介" >
                    </div>
                    <span class="col-sm-4" id="noteMsg"></span>
                </div>  
                <div class="form-group" id="telDiv">
                    <label class="control-label col-sm-3" for="tel">联系电话</label>
                    <div class="col-sm-5">
                        <input type="text" name="tel" id="tel" class="form-control" placeholder="请输入电话号码" >
                    </div>
                    <span class="col-sm-4" id="telMsg"></span>
                </div>                                      
                <div class="form-group" id="btnDiv">
                    <div class="col-sm-5 col-sm-offset-3">
                        <button type="submit" id="subBtn" class="btn btn-sm btn-primary">增加</button>
                        <button type="reset" id="rstBtn" class="btn btn-sm btn-warning">重置</button>
                    </div>
                </div>
            </fieldset>
         </form>
     </div>  
</body>
</html>