$(function(){
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
	
	
    //===================================user_insert.jsp start==============================================
    $("#userInsertForm").validate({    //定义验证规则
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
		    	      "name" :{
		    	    	       required: true ,
		    	    	       remote : {
			   	    	    	   url : "../User/findByName"  ,
			   	    	    	   type : "post" ,
			   	    	    	   dataType : "text"  ,
			   	    	    	   data : {                      //要发送的数据
			   	    	    		       "name" : function(){
			   	    	    		    	                   return $("#name").val()   ;
			   	    	    		       }
		   	    	    	        }  ,
			   	    	    	   dataFilter :function(jsonStr,type){       //处理完成执行的函数
			   	    	    		      var flag=false ;
			   	    	    		      var json=$.parseJSON(jsonStr);
			   	    	    		      if(json.status==0){  //AJAX验证用户名不存在,可用！
			   	    	    		    	  flag=true   ;
			   	    	    		      }
			   	    	    		      return flag ;
			   	    	    	   }
		    	    	       }
	   	    	      } ,
	   	    	      "password" : {
	   	    	    	          required : true 
	   	    	      } ,
	   	    	      "confPassword" : {
	   	    	    	          required : true   ,
	   	    	    	          equalTo  : "#password"
	   	    	      },
	   	    	      "roleId"  : {
	   	    	    	     required : true 
	   	    	      }
	       }   ,
	   //表单提交时候使用弹层加载
	   submitHandler:function(form){
	                 //我们在这里使用AJAX提交表单
		             var url=$("#userInsertForm").attr("action")  ;   //获取Action
		             var name=$("#name").val()  ;     //用户名称
		             var password=$("#password").val()  ;     //密码
		             var checkedbox=$(":checkbox[name='roleId']:checked")  ;
		             var roles=new Array(checkedbox.length) ;  //保存角色ID
		             var index=0 ;  //索引 
		             checkedbox.each(function(){
		            	 roles[index++]=$(this).val()  ;
                   });
		             $.ajax({
		                 url : url  ,                         //Ajax提交路径
		                 data : {                                             //传递的参数
		                     "name"     :  name     ,
		                     "password" :  password ,
		                     "roles"    :  roles    ,
		                     "time" : new Date()  
		                 }    ,
		                 type : "post"   ,                           //发送类型
		                 dataType : "json"   ,                  //返回值类型
		                 success : function(json){     //回调函数
		                	 if(json.status==1){  //成功修改
		                		 //在这里完成提示信息和表单禁用
		                		 layer.msg("增加成功!", {   //提示
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
//===================================user_insert.jsp end==============================================

  //===================================user_edit.jsp start==============================================      
    $("#userEditForm").validate({    //定义验证规则
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
	    	      "roleId" : {
	    	    	     required : true 
	    	      }
	       }   ,
	   //表单提交时候使用弹层加载
	   submitHandler:function(form){
	                 //我们在这里使用AJAX提交表单
		             var url=$("#userEditForm").attr("action")  ;   //获取Action
		             var userId=$("#userId").val() ;   //用户ID
		             var status=$("input:radio[name='status']:checked").val()  ;     //状态 
		             var checkedbox=$(":checkbox[name='roleId']:checked")  ;
		             var roles=new Array(checkedbox.length) ;  //保存角色ID
		             var index=0 ;  //索引 
		             checkedbox.each(function(){
		            	 roles[index++]=$(this).val()  ;
                      });
		             $.ajax({
		                 //url : "../doUpdate"  ,                         //Ajax提交路径
		                 url : url ,                         //Ajax提交路径
		                 data : {                                             //传递的参数
		              	     "userId"  :  userId  ,
		                     "status" :   status ,
		                      "roles":  roles,
		                     "time" : new Date()  
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
//===================================user_edit.jsp end==============================================    
    
    //===============================user_edit_pass.jsp start=============================================================
    
    $("#userPassEditForm").validate({    //定义验证规则
	      debug : true   ,   //采用调试模式,表单不会自动提交 
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
	    	   "srcPass" :  {
	    		   required : true,
	    	       remote : {
   	    	    	   url : "../findByIdAndPass"  ,
   	    	    	   type : "post" ,
   	    	    	   dataType : "text"  ,
   	    	    	   data : {                      //要发送的数据
   	    	    		       "userId" : function(){
   	    	    		    	            return $("#userId").val()   ;
   	    	    		       }  ,
   	    	    		       "name"   : function(){
   	    	    		    	            return $("#name").val()      ;
   	    	    		       }  ,
   	    	    		       "password"  :  function(){
   	    	    		    	             return $("#srcPass").val()   ;  
   	    	    		       }
	    	    	        }  ,
   	    	    	   dataFilter :function(jsonStr,type){       //处理完成执行的函数
   	    	    		      var flag=false ;
   	    	    		      var json=$.parseJSON(jsonStr);
   	    	    		      if(json.status==1){  //AJAX验证原密码输入正确,可用！
   	    	    		    	  flag=true   ;
   	    	    		      }
   	    	    		      return flag ;
   	    	    	   }
	    	       }	    		   
	    	   }  ,
	          "password" :{
	        	   required : true  
	          }  ,
	          "confPassword" :{
	        	   required : true  ,
	        	   equalTo  : "#password"
	          }
	       }  ,
		   //表单提交时候使用弹层加载
		   submitHandler:function(form){
		                 //我们在这里使用AJAX提交表单
			             var url=$("#userPassEditForm").attr("action")  ;   //获取Action
			             var userId=$("#userId").val() ;   //用户ID
			             var name=$("#name").val() ;   //用户名,盐值
			             var password=$("#password").val() ;   //密码
			             $.ajax({
			                 //url : "../doUpdate"  ,                         //Ajax提交路径
			                 url : url ,                         //Ajax提交路径
			                 data : {                                             //传递的参数
			              	     "userId"  :   userId    ,
			                     "name"    :   name      ,
			                     "password":   password  ,
			                     "time"    :   new Date()  
			                 }    ,
			                 type : "post"   ,                           //发送类型
			                 dataType : "json"   ,                  //返回值类型
			                 success : function(json){     //回调函数
			                	 if(json.status==1){  //成功修改
			                		 //在这里完成提示信息和表单禁用
			                		 layer.msg("密码修改成功,请重新登录!", {   //提示
			                			  icon: 1 ,
			                			  time: 4000 //2秒关闭（如果不配置，默认是3秒）
			                			}, function(){
			                			   //表单禁用
			                				//1.禁用按钮
			                				$("#subBtn").attr("disabled","disabled");
			                				$("#rstBtn").attr("disabled","disabled");
			                				//2.禁用输入框
			                				$(":input").attr("disabled","disabled")  ;
			                				$("#main").append("<h2><a href=\"../doLogout\">重新登录</a></2>");
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
    //===============================user_edit_pass.jsp end===============================================================
    
    //================================用户上传图片===========================================================================
    
	$.validator.addMethod("projectSelect", function(value, element) {  
	    return (value != "-1")? true:false;  
	}, "请选择项目");  
    
    $("#userPhotoInsertForm").validate({    //定义验证规则
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
		    	      "projectId" :{
		    	    	       required: true       ,
		    	    	       projectSelect : true
	   	    	      },
	   	    	      "photoFile" :{
	   	    	    	   required : true 
	   	    	      }
	       }  
  });
    //================================用户上传图片===========================================================================
    
    
    
});