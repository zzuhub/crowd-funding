<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	  <!-- 导入CSS -->
	  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" type="text/css" media="screen, projection" />
	  <link rel="stylesheet" type="text/css" media="all" href="${pageContext.request.contextPath}/css/tipsy.css" />
	  <!-- 导入JS -->
	  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
	  <script type="text/javascript" src="${pageContext.request.contextPath}/js/custom-scripts.js"></script>
	  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.tipsy.js"></script>
	  <!-- 自定义JS -->
	  <script type="text/javascript">
			$(document).ready(function(){
						
				universalPreloader();
									   
			});
			
			$(window).load(function(){
			
				//remove Universal Preloader
				universalPreloaderRemove();
				
				rotate();
			    dogRun();
				dogTalk();
				
				//Tipsy implementation
				$('.with-tooltip').tipsy({gravity: $.fn.tipsy.autoNS});
									   
			});
      </script>
	  <title>权限不足</title>
	</head>
	<body> 
		<!-- Universal preloader -->
		<div id="universal-preloader">
		    <div class="preloader">
		        <img src="images/universal-preloader.gif" alt="universal-preloader" class="universal-preloader-preloader" />
		    </div>
		</div>
		<!-- Universal preloader -->
		
		<div id="wrapper">
		<!-- 404 graphic -->
			<div class="graphic"></div>
		<!-- 404 graphic -->
		<!-- 提示文本↓ -->
			<div class="not-found-text">
		    	<h1 class="not-found-text">对不起,权限不足!</h1>
		    </div>
		<!-- 提示文本↑ -->
		
		
		
		<div class="dog-wrapper">
		<!-- dog running -->
			<div class="dog"></div>
		<!-- dog running -->
			
		<!-- dog bubble talking -->
			<div class="dog-bubble">
		    	
		    </div>
		    
		    <!-- The dog bubble rotates these -->
		    <div class="bubble-options">
		    	<p class="dog-bubble">
		        	你的权限认证失败,我可以帮助您的!
		        </p>
		    	<p class="dog-bubble">
			        <br />
		        	您可以选择
		        	<a href="${pageContext.request.contextPath}/login.jsp">
		        	   <font color="red">重新登陆</font>
		        	</a>
		        </p>
		        <p class="dog-bubble">
		        	<br />
		        	您还可以联系
		        	<a href="http://blog.csdn.net/qq_34378776">
		        	   <font color="red">管理员</font> 
		        	</a>
		        </p>
		    </div>
		    <!-- The dog bubble rotates these -->
		<!-- dog bubble talking -->
		</div>
		
		<!-- planet at the bottom -->
			<div class="planet"></div>
		<!-- planet at the bottom -->
		</div>		    
	</body>
</html>