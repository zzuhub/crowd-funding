<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
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
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/user_login.js"></script>
   <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <!-- 导入Layer前端弹层框架 -->
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/layer/layer.js"></script>
<title>互联网众筹-登录</title>
</head>
<body>
     <div class="container">
         <c:if test="${not empty errMsg}">
             <div class="alert alert-danger" role="alert" id="errDiv">${errMsg}</div>
         </c:if>
         <form action="${pageContext.request.contextPath}/User/doLogin" method="post" id="loginForm" class="form-horizontal">
            <fieldset>
                <legend >
                    <label >
                        <span class="glyphicon glyphicon-user"></span>
                        &nbsp;用户登录
                    </label>
                </legend>
                <div class="form-group" id="nameDiv">
                    <label class="control-label col-md-3" for="name">用户名</label>
                    <div class="col-md-5">
                        <input type="text" name="name" id="name" class="form-control" placeholder="请输入用户名">
                    </div>
                    <span class="col-md-4" id="nameMsg"></span>
                </div>
                <div class="form-group" id="passwordDiv">
                    <label class="control-label col-md-3" for="password">&nbsp;密码</label>
                    <div class="col-md-5">
                        <input type="password" name="password" id="password" class="form-control"
                               placeholder="请输入密码" >
                    </div>
                    <span class="col-md-4" id="passwordMsg" ></span>
                </div>
                <div class="form-group" id="codeDiv">
                     <label class="control-label col-md-3" for="code">验证码</label>
                     <div  class="col-md-3">
                        <input type="text" name="code" id="code" class="form-control"
                               placeholder="请输入验证码" >
                     </div>
                     <div  class="col-md-2">
                         <img alt="验证码" src="${pageContext.request.contextPath}/common/image.jsp" >
                     </div>
                     <span id="codeMsg" class="col-md-4"></span>
                </div>
                <div class="form-group" id="btnDiv">
                    <div class="col-md-5 col-md-offset-3">
                        <button type="submit" id="subBtn" class="btn btn-sm btn-primary">登录</button>
                        <button type="reset" id="rstBtn" class="btn btn-sm btn-warning">重置</button>
                    </div>
                </div>
            </fieldset>
         </form>
     </div>  
</body>
</html>