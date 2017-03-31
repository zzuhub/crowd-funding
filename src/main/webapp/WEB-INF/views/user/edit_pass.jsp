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
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/user.js"></script>
   <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
</head>
<body> 
     <div class="main container" id="main">
		         <!-- 这里放置Action只是为了AJAX调用Action作为URL发送异步请求,避免恶心的相对路径(使用了IFrame技术和AJAX的缺点) -->
		         <form action="${pageContext.request.contextPath}/User/doUpdatePassword" method="post" id="userPassEditForm" class="form-horizontal">
		            <!-- 保存用户ID -->
		            <input type="hidden" value="${user.userId}" id="userId" name="userId">
		            <!-- 保存用户名称 -->
		            <input type="hidden" value="${user.name}" id="name" name="name">
		            <fieldset>
		               <legend >
		                    <label >
		                        <span class="glyphicon glyphicon-wrench"></span>
		                        &nbsp;修改密码
		                    </label>
		                </legend>
		                <div class="form-group" id="srcPassDiv">
		                    <label class="control-label col-sm-3" for="srcPass">原密码</label>
		                    <div class="col-sm-5">
		                        <input type="password" name="srcPass" id="srcPass" class="form-control">
		                    </div>
		                    <span class="col-sm-4" id="srcPassMsg"></span>
		                </div>   
		                <div class="form-group" id="passwordDiv">
		                    <label class="control-label col-sm-3" for=password>新密码</label>
		                    <div class="col-sm-5">
		                        <input type="password" name="password" id="password" class="form-control">
		                    </div>
		                    <span class="col-sm-4" id="passwordMsg"></span>
		                </div>
		                <div class="form-group" id="confPasswordDiv">
		                    <label class="control-label col-sm-3" for=confPassword>确认新密码</label>
		                    <div class="col-sm-5">
		                        <input type="password" name="confPassword" id="confPassword" class="form-control">
		                    </div>
		                    <span class="col-sm-4" id="confPasswordMsg"></span>
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