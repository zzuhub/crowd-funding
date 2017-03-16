$(function(){
	     $("#loginForm").validate({    //定义验证规则
	    	      debug : false   ,   //采用调试模式,表单不会自动提交 
	    	      errorPlacement : function(error,element){   //error表示自动生成的错误信息文字,element表示触发的元素(JQuery对象)
	    	    	       //调试专用语句  console.log("#"+element.attr("id")+"Msg");
	    	    	       var objName=element.attr("id")+"Msg"   ;     
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
	    	    	    	     required: true  
	    	    	      },
	    	    	      "password" :{
	    	    	    	     required: true  
	    	    	      }  ,
	    	    	      "code":{
	    	    	    	     required : true   ,
	    	    	    	     maxlength : 4    ,
	    	    	    	     remote :{
	    	    	    	    	   url: "common/code.jsp"   ,
	    	    	    	    	   type : "post"    ,
	    	    	    	    	   dataType : "text"   ,
	    	    	    	    	   data : {
	    	    	    	    		     "code" : function (){
	    	    	    	    		    	    return $("#code").val()  ;
	    	    	    	    		     }
	    	    	    	    	   },
	    	    	    	    	   dataFilter : function (data,type){
	    	    	    	    		      var flag=false ; 
	    	    	    	    		      if(data.trim()=="true"){
	    	    	    	    		    	    flag=true   ;
	    	    	    	    		       }
	    	    	    	    		      return flag   ;
	    	    	    	    	   }
	    	    	    	     }
	    	    	      }
	    	       }   ,
	    	   //表单提交时候使用弹层加载
	    	   submitHandler:function(form){
	    	                 form.submit() ;   //提交表单
	    	                 layer.msg('正在登陆...请稍后', {icon: 4}) ;
	    	   }
	     });
})   ;