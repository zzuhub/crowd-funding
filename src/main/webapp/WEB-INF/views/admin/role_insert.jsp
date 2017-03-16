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
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/role.js"></script>
   <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <!-- 导入Layer前端弹层框架 -->
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/layer/layer.js"></script>
   <!-- 独立封装的通用JS,例如AJAX加载动画 -->
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
</head>
<body>
    <div class="container">
         <form action="${pageContext.request.contextPath}/Role/doCreate" method="post" id="roleInsertForm" class="form-horizontal">
            <fieldset>
               <legend >
                    <label >
                        <span class="glyphicon glyphicon-plus"></span>
                        &nbsp;增加角色
                    </label>
                </legend>
                <div class="form-group" id="nameDiv">
                    <label class="control-label col-sm-3" for="name">角色名称</label>
                    <div class="col-sm-5">
                        <input type="text" name="name" id="name" class="form-control" 
                               placeholder="请输入角色名称" >
                    </div>
                    <span class="col-sm-4" id="nameMsg"></span>
                </div>
                <div class="form-group" id="noteDiv">
                    <label class="control-label col-sm-3" for="note">&nbsp;备注</label>
                    <div class="col-sm-5">
                        <input type="text" name="note" id="note" class="form-control"
                               placeholder="请输入角色备注">
                    </div>
                    <span class="col-sm-4" id="noteMsg" ></span>
                </div>
                <div class="form-group" id="permissionIdDiv">
                   <label class="control-label col-sm-3" >&nbsp;权限</label>
                   <div class="col-sm-5">
                       <!-- 设置count统计个数,每4个一行输出 -->
                       <c:set var="count" value="0"/>
                       <c:forEach items="${allPermissions}" var="perm">
                           <label class="checkbox-inline">
							   <input type="checkbox"  name="permissionId" value="${perm.permissionId}"> ${perm.note}
                           </label>
                           <c:set var="count" value="${count+1}"/>
                           <!-- 亲自测试使用3个一行逐行打印最科学 -->
                           <c:if test="${count%3==0}">
                              <br>
                           </c:if>
                       </c:forEach>
                   </div>
                   <span class="col-sm-4" id="permissionIdMsg"></span>
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