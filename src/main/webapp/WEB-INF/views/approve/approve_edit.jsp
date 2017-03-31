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
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/approve.js"></script>
   <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
         <!-- 这里放置Action只是为了AJAX调用Action作为URL发送异步请求,避免恶心的相对路径(使用了IFrame技术和AJAX的缺点) -->
         <form action="${pageContext.request.contextPath}/Approve/doUpdate" method="post" id="approveEditForm" class="form-horizontal">
            <!-- 隐藏支持ID -->
            <input type="hidden" value="${approve.approveId}" id="approveId" name="approveId">
            <fieldset>
               <legend >
                    <label >
                        <span class="glyphicon glyphicon-wrench"></span>
                        &nbsp;编辑支持
                    </label>
                </legend>
                <!-- 不可用 -->
                <div class="form-group" id="projectIdDiv">
                    <label class="control-label col-sm-3" for="projectId">&nbsp;项目</label>
                    <div class="col-sm-5">
                         <select class="form-control" id="projectId" name="projectId" disabled="disabled">
						   <option value="${approve.project.projectId}">${approve.project.title}</option>
						</select>
                    </div>
                    <span class="col-sm-4" id="projectIdMsg" ></span>
                </div>
                <div class="form-group" id="nameDiv">
                    <label class="control-label col-sm-3" for="name">&nbsp;姓名</label>
                    <div class="col-sm-5">
                        <input type="text" name="name" id="name" class="form-control"
                               placeholder="请输入姓名" value="${approve.name}">
                    </div>
                    <span class="col-sm-4" id="nameMsg" ></span>
                </div>                
                <div class="form-group" id="telDiv">
                    <label class="control-label col-sm-3" for="tel">&nbsp;联系电话</label>
                    <div class="col-sm-5">
                        <input type="text" name="tel" id="tel" class="form-control"
                               placeholder="请输入电话号码" value="${approve.tel}">
                    </div>
                    <span class="col-sm-4" id="telMsg" ></span>
                </div>
                <div class="form-group" id="moneyDiv">
                    <label class="control-label col-sm-3" for="money">&nbsp;金额</label>
                    <div class="col-sm-5">
                        <input type="text" name="money" id="money" class="form-control" value="${approve.money}" disabled="disabled">
                    </div>
                    <span class="col-sm-4" id="moneyMsg" ></span>
                </div>                                
                <div class="form-group" id="btnDiv">
                    <div class="col-sm-5 col-sm-offset-3">
                        <button type="submit" id="subBtn" class="btn btn-sm btn-primary">修改</button>
                        <button type="reset" id="rstBtn" class="btn btn-sm btn-warning">重置</button>
                    </div>
                </div>
            </fieldset>
         </form>
     </div>  
</body>
</html>