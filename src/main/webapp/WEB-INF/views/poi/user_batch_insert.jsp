<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>批量添加用户</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js">
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/menu.js"></script>
<!--  Bootstrap-->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="../basic/header.jsp"></jsp:include>
	<div id="content">
		    <jsp:include page="../basic/left.jsp"></jsp:include>
			<div class="m-right">
				<div class="main container">
				    <br><br>
				    <form class="form-horizontal" role="form" 
				         action="${pageContext.request.contextPath}/POI/User/doBatchCreate"
				         method="POST"
				         enctype="multipart/form-data">
				          <div class="form-group" id="uploadDiv">
							    <label for="dataFile" class="col-sm-2 control-label">上传模板</label>
							    <div class="col-sm-5">
							       <input type="file" id="dataFile" name="dataFile">
							    </div>
							    <div class="col-sm-5"><span id="uploadSpan"></span></div>
                          </div>
                          <div class="form-group" id="btnDiv">
		                  <div class="col-md-5 col-md-offset-2">
		                        <button type="submit" id="subBtn" class="btn btn-sm btn-primary">批量增加</button>
		                        <button type="reset" id="rstBtn" class="btn btn-sm btn-warning">重置</button>
		                   </div>
		              </div>
				    </form>
				    <div class="alert alert-danger" role="alert">警告:请先下载模板填充数据!</div>
				</div>
			</div>
	</div>
	<jsp:include page="../basic/foot.jsp"></jsp:include>
</body>
</html>