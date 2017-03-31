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
	
	//自定义验证规则
	$.validator.addMethod("projectIdSelect",function(value, element){
		 return (value != "-1")   ;
	},"请选择项目");
	
	$.validator.addMethod("isMobile",function(value, element){
	    var length = value.length  ;
	    var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/   ;
	    return this.optional(element) || (length == 11 && mobile.test(value))     ;
	},"请输入正确的电话号码");
	
	
	
    //===================================category_insert.jsp start==============================================
    $("#approveInsertForm").validate({    //定义验证规则
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
		    	      "userId" :{
		    	    	       required: true ,
		    	    	       digits : true ,
		    	    	       remote : {
			   	    	    	   url : "../User/findById"  ,
			   	    	    	   type : "post" ,
			   	    	    	   dataType : "text"  ,
			   	    	    	   data : {                      //要发送的数据
			   	    	    		       "userId" : function(){
			   	    	    		    	           return $("#userId").val()   ;
			   	    	    		       }
		   	    	    	        }  ,
			   	    	    	   dataFilter :function(json,type){       //处理完成执行的函数
			   	    	    		       var flag=false ;
			   	    	    		       if(json){  //AJAX验证User存在
			   	    	    		    	  //修改标记位
			   	    	    		    	  flag=true   ;
			   	    	    		    	  //根据UserID获取全部项目
			   	    			             $.ajax({
			   	    			                 url : "../Project/findByUserId"  ,                         //Ajax提交路径
			   	    			                 data : {                                             //传递的参数
			   	    			                     "userId"     :  function(){
			   	    			                    	               return $("#userId").val()   ;
			   	    			                     }     ,
			   	    			                     "time" : new Date()  
			   	    			                 }    ,
			   	    			                 type : "post"   ,                           //发送类型
			   	    			                 dataType : "json"   ,                  //返回值类型
			   	    			                 success : function(json){     //回调函数
			   	    			                	       $("#projectId option[value!='-1']").remove() ;   //移除提示项以后的内容
					   	    			                	for(var x=0;x<json.length;x++){
					   	    			                		$("#projectId").append("<option value=\""+json[x].projectId
					   	    			                				                +"\">"+json[x].title+"</option>");
					   	    			                    }
			   	    			                 }
			   	    			             });
			   	    	    		      }
			   	    	    		      return flag ;
			   	    	    	   }
		    	    	       }
	   	    	      } ,
	   	    	   "projectId"  : {
	   	    		  projectIdSelect : true 
	   	    	   } ,
	   	    	   "name" : {
	   	    		   required : true 
	   	    	   }  ,
	   	    	   "money" : {
	   	    		   required : true  ,
	   	    		   number  : true  
	   	    	   }  ,
	   	    	   "tel" :{
	   	    		   required : true  ,
	   	    		   isMobile  : true
	   	    	   }
	       }   ,
	   //表单提交时候使用弹层加载
	   submitHandler:function(form){
	                 //我们在这里使用AJAX提交表单
		             var url=$("#approveInsertForm").attr("action")  ;   //获取Action
		             var projectId=$("#projectId").val()  ;     //项目ID
		             var tel=$("#tel").val()  ;     //手机号码
		             var name=$("#name").val()  ;     //名称
		             var money=$("#money").val()  ;     //名称
		             $.ajax({
		                 url : url  ,                         //Ajax提交路径
		                 data : {                                             //传递的参数
		                     "project.projectId" :  projectId        ,
		                     "tel"       :  tel     ,
		                     "name"      :  name       ,  
		                     "money"     : money       ,  
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
//===================================ucategory_insert.jsp end==============================================

//===================================category_edit.jsp start==============================================      
    $("#approveEditForm").validate({    //定义验证规则
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
	    	    	 required : true 
	    	     }  ,
	   	    	 "tel" :{
	   	    		     required : true  ,
	   	    		     isMobile  : true
	   	    	 }
	       }   ,
	   //表单提交时候使用弹层加载
	   submitHandler:function(form){
	                 //我们在这里使用AJAX提交表单
		             var url=$("#approveEditForm").attr("action")  ;   //获取Action
		             var approveId=$("#approveId").val()  ;     //支持ID
		             var tel=$("#tel").val()  ;     //手机号码
		             var name=$("#name").val()  ;     //名称
		             $.ajax({
		                 //url : "../doUpdate"  ,                         //Ajax提交路径
		                 url : url ,                         //Ajax提交路径
		                 data : {                                             //传递的参数
		              	     "approveId" :  approveId    ,
		                     "tel" :  tel   ,
		                     "name"    :  name   ,
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
//===================================category_edit.jsp end==============================================    
    
    
    
});