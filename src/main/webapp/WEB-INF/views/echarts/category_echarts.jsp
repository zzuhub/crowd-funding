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
   <title>类别统计信息</title>
   <!-- JQuery+Bootstrap前端技术 -->
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/menu.js"></script>
   <!-- CSS -->
   <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
   <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
   <!-- 导入百度Echarts -->
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.js"></script>
</head>
<body>
      <jsp:include page="../basic/header.jsp"></jsp:include> 
      <div id="content">
          <jsp:include page="../basic/left.jsp"></jsp:include>
          <div class="m-right">
			   <div class="main container">
			      <br><br>
			       <div id="echartsDiv" style="width: 1200px;height: 600px"></div>
			       <!-- 隐藏根目录 -->
			       <input type="hidden" value="${pageContext.request.contextPath}" id="root" name="root">
	           </div>
		  </div>
	   </div>
       <jsp:include page="../basic/foot.jsp"></jsp:include>
     <script type="text/javascript">
         var myChart=echarts.init(document.getElementById("echartsDiv"))  ;
         var url=$("#root").val()+"/Echarts/findCategoryEcharts"  ;
         $.ajax({
             url : url  ,                         //Ajax提交路径
             data : {                                             //传递的参数
                 "time" : new Date()     //保证每次AJAX请求都发送到服务器 
             }    ,
             type : "post"   ,                           //发送类型
             dataType : "json"   ,                  //返回值类型
             success : function(json){     //回调函数
            	 myChart.setOption({
                     title :{
                         text:'筹金类别统计分析',  
                         subtext:'百度ECharts报表技术,改变未来!' , 
                         x:'center'
                     }  ,
                    tooltip:{
                         trigger:'item'  ,
                         formatter:'{a} <br/> {b} :{c} ({d}%)'
                    } ,
                    legend:{
                         orient:'vertical' ,
                         left:'left'   ,
                         data:json.legend
                    } ,
                    series:[
                        {
                            name:'筹金类别', 
                            type:'pie'   ,
                            radius:'55%'   ,
                            center:['50%','60%'],
                            data:json.data,
                            itemStyle:{
                                emphasis:{
                                    shadowBlur:10 ,
                                    shadowOffsetX:0 ,
                                    shadowColor:'rgba(0,0,0,0.5)'
                                }
                            }
                        }
                    ]
            	 })  ;
             },
            error : function(){
				 layer.alert('对不起系统异常,请刷新后重试', {
	                 skin: 'layui-layer-molv'
	               , closeBtn: 0
	               , shift: 4 //动画类型
	            });     
			 }
      });	    	
     </script>
</body>
</html>