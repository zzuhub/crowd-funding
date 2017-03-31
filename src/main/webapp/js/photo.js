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
	
	
	
	
	
    $("#photoInsertForm").validate({    //定义验证规则
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
		    	    	       required: true ,
		    	    	       digits : true ,
		    	    	       remote : {
			   	    	    	   url : "../Project/findById"  ,
			   	    	    	   type : "post" ,
			   	    	    	   dataType : "text"  ,
			   	    	    	   data : {                      //要发送的数据
			   	    	    		       "projectId" : function(){
			   	    	    		    	           return $("#projectId").val()   ;
			   	    	    		       }
		   	    	    	        }  ,
			   	    	    	   dataFilter :function(jsonStr,type){       //处理完成执行的函数
			   	    	    		       var flag=false ;
			   	    	    		       var json=$.parseJSON(jsonStr)   ;
			   	    	    		       if(json.status==1)    //项目存在,flag修改为true
			   	    	    		    	         flag=true    ;
			   	    	    		       return flag ;
			   	    	    	   }
		    	    	       }
	   	    	      },
	   	    	      "photoFile" :{
	   	    	    	   required : true 
	   	    	      }
	       }  
    });
    
});