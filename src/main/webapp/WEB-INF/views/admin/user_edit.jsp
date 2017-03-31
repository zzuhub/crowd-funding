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
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/user.js"></script>
   <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
         <!-- 这里放置Action只是为了AJAX调用Action作为URL发送异步请求,避免恶心的相对路径(使用了IFrame技术和AJAX的缺点) -->
         <form action="${pageContext.request.contextPath}/User/doUpdate" method="post" id="userEditForm" class="form-horizontal">
            <fieldset>
               <legend >
                    <label >
                        <span class="glyphicon glyphicon-wrench"></span>
                        &nbsp;编辑用户
                    </label>
                </legend>
                <div class="form-group" id="userIdDiv">
                    <label class="control-label col-sm-3" for="userId">用户编号</label>
                    <div class="col-sm-5">
                        <input type="text" name="userId" id="userId" class="form-control" value="${user.userId}" disabled="disabled">
                    </div>
                    <span class="col-sm-4" id="nameMsg"></span>
                </div>                
                <div class="form-group" id="nameDiv">
                    <label class="control-label col-sm-3" for="name">用户名</label>
                    <div class="col-sm-5">
                        <input type="text" name="name" id="name" class="form-control" value="${user.name}" disabled="disabled">
                    </div>
                    <span class="col-sm-4" id="nameMsg"></span>
                </div>
                <div class="form-group" id="statusDiv">
                    <label class="control-label col-sm-3" for="status">&nbsp;状态</label>
                    <div class="col-sm-5 checkbox">
                        <!-- 状态是锁定 -->
                        <c:if test="${user.status==0}">
                          <label class="checkbox-inline">
	                          <input type="radio" value="1" name="status" >正常
	                          <input type="radio" value="0" name="status" checked>锁定
                          </label>
                        </c:if>
                        <!-- 状态是正常 -->
                        <c:if test="${user.status==1}">
                          <label class="checkbox-inline">
                            <input type="radio" value="1" name="status" checked>正常
                            <input type="radio" value="0" name="status" >锁定
                          </label>
                        </c:if>
                    </div>
                    <span class="col-sm-4" id="statusMsg" ></span>
                </div>
                <div class="form-group" id="roleIdDiv">
                   <label class="control-label col-sm-3" >&nbsp;角色</label>
                   <div class="col-sm-5 checkbox">
                       <!-- 设置count统计个数,每4个一行输出 -->
                       <c:set var="count" value="0"/>                   
                       <c:forEach items="${allRoles}" var="role">
                              <!-- 对比角色标记位 -->
                             <c:set var="checked" value="0"/>
                             <label class="checkbox-inline">
                             <c:forEach items="${user.roles}" var="myRole">
                                  <!-- 有我选中的角色 -->
                                  <c:if test="${myRole.roleId==role.roleId}">
							        <input type="checkbox" name="roleId" value="${role.roleId}" checked="checked">${role.name}
	                                  <!-- 修改标记位 -->
	                                  <c:set var="checked" value="${checked+1}"/>							        
                                  </c:if>
                             </c:forEach>
                             <!-- 内层循环没有一致的角色,那么单选框直接打印即可 -->
                             <c:if test="${checked==0}">
                               <input type="checkbox" name="roleId" value="${role.roleId}">${role.name}
                             </c:if>
                              </label>
                             <c:set var="count" value="${count+1}"/>
	                         <!-- 亲自测试使用3个一行逐行打印最科学 -->
	                         <c:if test="${count%3==0}">
	                             <br>
	                         </c:if>                             
                       </c:forEach>
                   </div>
                   <span class="col-sm-4" id="roleIdMsg"></span>
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