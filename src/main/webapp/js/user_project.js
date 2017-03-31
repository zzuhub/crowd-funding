$(function(){
	//配置Ajax加载数据弹层
    //AJAX加载动画效果配置(因为特殊业务,需要独立封装这段代码)
	$.ajaxSetup({
		 layerIndex : -1   ,
		 beforeSend : function(){
			 this.layerIndex = layer.load(0, {shade: false});
		 }  ,
		 complete : function(){
			 layer.close(this.layerIndex)  ;
		 }   
	});
	toInsertPage() ;  //到达增加页
	
	$("a[name='edit']").click(function(){
		var projectId=$(this).parent().parent().attr("id") ;   //项目ID
	    layer.open({
	        type: 2,
	        title: "编辑项目",
	        shadeClose: true,
	        shade: false,
	        maxmin: true, //开启最大化最小化按钮
	        area: ['800px', '500px'],
	        content: "../toUserEditPage/"+projectId,
	        end : function(){
		           $.ajax({
		               url : "../findEntityById"  ,                           //Ajax提交路径
		               data : {                                             //传递的参数
		            	   "projectId"  :  projectId  ,
		                   "time"       :  new Date()  
		               }    ,
		               type : "post"   ,                           //发送类型
		               dataType : "json"   ,                  //返回值类型
		               success : function(json){     //回调函数
		            	   //只能修改标题、说明、电话、状态 
	                	   var title=json.title  ;   //标题
	                	   var note=json.note  ;    //简介
	                	   var tel=json.tel  ;   //电话
	                	   var status=json.status  ;  //状态
	                	   $("tr[id="+projectId+"] td:eq(1)").text(title)      ;
	                	   $("tr[id="+projectId+"] td:eq(8)").text(note)     ;
	                	   $("tr[id="+projectId+"] td:eq(6)").text(tel)   ;
	                	   $("tr[id="+projectId+"] td:eq(7)").text(json.status==0?"完成":"筹集中")   ;
	                  }
	          });
	      }
  });
	});
	
	//自定义验证规则
	$.validator.addMethod("categoryIdSelect",function(value, element){
		 return (value != "-1")   ;
	},"请选择类别");
	
	$.validator.addMethod("statusSelect",function(value, element){
		return (value != "-1")   ;
	},"请选择状态");
	
	$.validator.addMethod("telephone",function(value, element){
		var isMobile=/^(?:13\d|15\d|18\d)\d{5}(\d{3}|\*{3})$/     ;    //手机   
		var isPhone=/^((0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/  ;    //电话
		return (isMobile.test(value) || isPhone.test(value))   ;
	},"请输入正确的电话号码");
	
    //===================================project_insert.jsp start==============================================
    $("#projectInsertForm").validate({    //定义验证规则
	      debug : false   ,   //采用调试模式,表单不会自动提交 
	      errorPlacement : function(error,element){   //error表示自动生成的错误信息文字,element表示触发的元素(JQuery对象)
	    	       //调试专用语句  console.log("#"+element.attr("id")+"Msg");
	    	      //一行三目运算符搞定
	    	      var objName=element.is(':checkbox')?element.attr("name")+"Msg":element.attr("id")+"Msg"   ;     
   	    	       if(objName.indexOf(".") != -1) {    //现在名字里包含.
   	    	    	      objName=objName.replace(".","\\.") ;    //修改对象名字
   	    	       }
   	    	       $("#"+objName).attr("class","text-danger")    ;
   	    	       $("#"+objName).append(error)   ;
	    	      
	      }   ,
	      highlight :function(element,errorClass) {      //高亮显示,element:DOM对象 
	    	  var objName=$(element).attr("id")+"Div"   ;
	    	  $("#"+objName).attr("class","form-group  has-error")    ; 
	      }    ,
	      unhighlight : function(element,errorClass){
	    	  var objName=$(element).attr("id")+"Div"   ;
	    	  $("#"+objName).attr("class","form-group has-success")    ; 
	      }   ,
	    //  errorClass : "wrong"    ,   //错误样式
	    //  validClass  : "right"    ,    //正确样式 
	       rules :{            //为每一个表单编写验证规则
	   	    	   "categoryId"  : {
	   	    		   categoryIdSelect : true 
	   	    	   } ,
	   	    	   "title" : {
	   	    		   required : true 
	   	    	   }  ,
	   	    	   "expectMoney"  :{
	   	    		   required : true   ,
	   	    		   number   : true
	   	    	   }  ,
	   	    	   "note"  :{
	   	    		    required : true  
	   	    	   }  ,
	   	    	   "tel"   :{
	   	    		    required : true ,
	   	    		    telephone : true
	   	    	   }
	   	    	      
	       }   ,
	   //表单提交时候使用弹层加载
	   submitHandler:function(form){
	                 //我们在这里使用AJAX提交表单
		             var url=$("#projectInsertForm").attr("action")  ;   //获取Action
		             var userId=$("#userId").val()  ;     //用户ID
		             var categoryId=$("#categoryId").val()  ;     //类别ID
		             var title=$("#title").val()   ;   //标题
		             var expectMoney=$("#expectMoney").val()  ;  //目标资金
		             var note=$("#note").val()   ;  //简介
		             var tel=$("#tel").val()  ;  //联系方式
		             $.ajax({
		                 url : url  ,                         //Ajax提交路径
		                 data : {                                             //传递的参数
		                     "user.userId"      :  userId        ,
		                     "category.id"   :  categoryId     ,
		                     "title"     :  title       , 
		                     "expectMoney" : expectMoney  ,
		                     "note"  : note   ,
		                     "tel"   : tel    ,
		                     "time" : new Date()  
		                 }    ,
		                 type : "post"   ,                           //发送类型
		                 dataType : "json"   ,                  //返回值类型
		                 success : function(json){     //回调函数
		                	 if(json.status==1){  //成功修改
		                		 //在这里完成提示信息和表单禁用
		                		 layer.msg("增加成功,请刷新页面重新显示!", {   //提示
		                			  icon: 1 ,
		                			  time: 2000 //2秒关闭（如果不配置，默认是3秒）
		                			}, function(){
		                			   //表单禁用
		                				//1.禁用按钮
		                				$("#subBtn").attr("disabled","disabled");
		                				$("#rstBtn").attr("disabled","disabled");
		                				//2.禁用输入框
		                				$(":input").attr("disabled","disabled")  ;
		                			});   
		                	 }else{ //修改失败
		                		 layer.msg("增加失败,请稍后重试!", {icon: 5});
		                	 }
		                 },
  		              error : function(){
  						 layer.alert('对不起系统异常,请刷新后重试', {
  			                 skin: 'layui-layer-molv'
  			               , closeBtn: 0
  			               , shift: 4 //动画类型
  			            });     
  					 }
		          });	    		             
	                 return false ;
	   }
});
//===================================project_insert.jsp.jsp end==============================================
	
    
  //===================================project_edit.jsp start==============================================      
    $("#projectEditForm").validate({    //定义验证规则
	      debug : false   ,   //采用调试模式,表单不会自动提交 
	      errorPlacement : function(error,element){   //error表示自动生成的错误信息文字,element表示触发的元素(JQuery对象)
	    	       //调试专用语句  console.log("#"+element.attr("id")+"Msg");
	    	           //一行三目运算符搞定
	    	    	   var objName=element.is(':checkbox')?element.attr("name")+"Msg":element.attr("id")+"Msg"   ;     
  	    	       if(objName.indexOf(".") != -1) {    //现在名字里包含.
  	    	    	      objName=objName.replace(".","\\.") ;    //修改对象名字
  	    	       }
  	    	       $("#"+objName).attr("class","text-danger")    ;
  	    	       $("#"+objName).append(error)   ;
	    	      
	      }   ,
	      highlight :function(element,errorClass) {      //高亮显示,element:DOM对象 
	    	  var objName=$(element).attr("id")+"Div"   ;
	    	  $("#"+objName).attr("class","form-group  has-error")    ; 
	      }    ,
	      unhighlight : function(element,errorClass){
	    	  var objName=$(element).attr("id")+"Div"   ;
	    	  $("#"+objName).attr("class","form-group has-success")    ; 
	      }   ,
	    //  errorClass : "wrong"    ,   //错误样式
	    //  validClass  : "right"    ,    //正确样式 
	       rules :{            //为每一个表单编写验证规则
   	    	   "title" : {
   	    		   required : true 
   	    	   }  ,
   	    	   "note"  :{
  	    		    required : true  
  	    	   }  ,
   	    	   "tel"   :{
  	    		    required : true ,
  	    		    telephone : true
  	    	   },
   	    	   "status"   :{  
   	    		   statusSelect : true 
  	    	   }
	       }   ,
	   //表单提交时候使用弹层加载
	   submitHandler:function(form){
	                 //我们在这里使用AJAX提交表单
		             var url=$("#projectEditForm").attr("action")  ;   //获取Action
		             var projectId=$("#projectId").val() ;      //项目ID
		             var title=$("#title").val() ;      //标题
		             var note=$("#note").val() ;          //简介
		             var tel=$("#tel").val()       ;     //联系电话
		             var status=$("#status").val()     ;  //状态
		             $.ajax({
		                 //url : "../doUpdate"  ,                         //Ajax提交路径
		                 url : url ,                         //Ajax提交路径
		                 data : {                                             //传递的参数
		              	     "projectId" :  projectId    ,
		                     "title"     :  title   ,
		                     "note"      :  note   ,
		                     "tel"       :  tel   ,
		                     "status"    :  status   ,
		                     "time"      :  new Date()  
		                 }    ,
		                 type : "post"   ,                           //发送类型
		                 dataType : "json"   ,                  //返回值类型
		                 success : function(json){     //回调函数
		                	 if(json.status==1){  //成功修改
		                		 //在这里完成提示信息和表单禁用
		                		 layer.msg("修改成功!", {   //提示
		                			  icon: 1 ,
		                			  time: 2000 //2秒关闭（如果不配置，默认是3秒）
		                			}, function(){
		                			   //表单禁用
		                				//1.禁用按钮
		                				$("#subBtn").attr("disabled","disabled");
		                				$("#rstBtn").attr("disabled","disabled");
		                				//2.禁用输入框
		                				$(":input").attr("disabled","disabled")  ;
		                			});   
		                	 }else{ //修改失败
		                		 layer.msg("修改失败,请稍后重试!", {icon: 5});
		                	 }
		                 }  ,
 	   		             error : function(){
	   						 layer.alert('对不起系统异常,请刷新后重试', {
	   			                 skin: 'layui-layer-molv'
	   			               , closeBtn: 0
	   			               , shift: 4 //动画类型
	   			            });     
	   					 }	    		                 
		          });	    		             
	                 return false ;
	   }
});
//===================================project_edit.jsp end==============================================  
    
    
});
function toInsertPage(){   //到达增加页面
    $("#add").click(function(){
    	var userId=$("#userId").val() ;   //用户ID
	    layer.open({
	        type: 2,
	        title: "增加项目",
	        shadeClose: true,
	        shade: false,
	        maxmin: true, //开启最大化最小化按钮
	        area: ['800px', '500px'],
	        content: "../toUserInsertPage/"+userId	
	      });
    });
}
