$(function(){
	     //===================================role_edit.jsp start==============================================
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
	     $("#roleEditForm").validate({    //定义验证规则
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
	    	    	    	     required: true  ,
	    	    	    	     remote : {
	    	    	    	    	   url : "../findByName"  ,
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
	    	    	    	    		      var oldName=$("#oldName").val();  //源角色名
	    	    	    	    		      var newName=$("#name").val(); //新角色名
	    	    	    	    		      if(json.status==0 || oldName==newName){  //AJAX验证角色名不存在,可用！
	    	    	    	    		    	  flag=true   ;
	    	    	    	    		      }
	    	    	    	    		      return flag ;
	    	    	    	    	   }
	    	    	    	    	   
	    	    	    	      } 	    	    	    	     
	    	    	      },
	    	    	      "note" :{
	    	    	    	     required: true  
	    	    	      } ,
	    	    	      "permissionId" : {
	    	    	    	     required : true 
	    	    	      }
	    	       }   ,
	    	   //表单提交时候使用弹层加载
	    	   submitHandler:function(form){
	    	                 //我们在这里使用AJAX提交表单
	    		             var url=$("#roleEditForm").attr("action")  ;   //获取Action
	    		             var roleId=$("#roleId").val() ;   //角色ID
	    		             var name=$("#name").val()  ;     //角色名称
	    		             var note=$("#note").val()  ;     //角色简介 
	    		             var checkedbox=$(":checkbox[name='permissionId']:checked")  ;
	    		             var perms=new Array(checkedbox.length) ;  //保存权限ID
	    		             var index=0 ;  //索引 
	    		             checkedbox.each(function(){
                            	 perms[index++]=$(this).val()  ;
                             });
	    		             $.ajax({
	    		                 //url : "../doUpdate"  ,                         //Ajax提交路径
	    		                 url : url ,                         //Ajax提交路径
	    		                 data : {                                             //传递的参数
	    		              	     "roleId"  :  roleId  ,
	    		                     "name"    :   name   ,
	    		                     "note" :   note ,
	    		                      "perms":  perms,
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
	     //===================================role_edit.jsp end==============================================
	     
	     
	      
	     
	     //===================================role_insert.jsp end==============================================
	     $("#roleInsertForm").validate({    //定义验证规则
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
	    	    	    	   url : "../Role/findByName"  ,
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
	    	    	    		      if(json.status==0){  //AJAX验证角色名不存在,可用！
	    	    	    		    	  flag=true   ;
	    	    	    		      }
	    	    	    		      return flag ;
	    	    	    	   }
	    	    	    	   
	    	    	      }   	    	    	       
   	    	      },
   	    	      "note" :{
   	    	    	     required: true 
   	    	    	     
   	    	      } ,
   	    	      "permissionId" : {
   	    	    	     required : true 
   	    	      }
   	       }   ,
   	   //表单提交时候使用弹层加载
   	   submitHandler:function(form){
   	                 //我们在这里使用AJAX提交表单
   		             var url=$("#roleInsertForm").attr("action")  ;   //获取Action
   		             var name=$("#name").val()  ;     //角色名称
   		             var note=$("#note").val()  ;     //角色简介 
   		             var checkedbox=$(":checkbox[name='permissionId']:checked")  ;
   		             var perms=new Array(checkedbox.length) ;  //保存权限ID
   		             var index=0 ;  //索引 
   		             checkedbox.each(function(){
                       	 perms[index++]=$(this).val()  ;
                        });
   		             $.ajax({
   		                // url : "../Role/doCreate"  ,                         //Ajax提交路径
   		                 url : url  ,                         //Ajax提交路径
   		                 data : {                                             //传递的参数
   		                     "name"    :   name   ,
   		                     "note" :   note ,
   		                      "perms":  perms,
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
  //===================================role_insert.jsp end==============================================
	     
	     
	     
})   ;