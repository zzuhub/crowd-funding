/**
 * 
 * 完成AJAX操作,节省服务器端压力
 * 
 * WangCJ
 * 
 * SoftwareCollege Of ZhengZhouUniversity
 * 
 * 
 */
$(function(){     //只要是无刷新分页,就要进行Ajax动态设置
	//配置Ajax加载数据弹层
	ajaxInit() ;    //初始化AJAX设置  
    loadData()  ;    //只要调用这个函数就可以实现表格数据填充以及分页的自动生成
    toInsertPage() ;
})  ;
function toInsertPage(){   //到达增加页面
    $("#add").click(function(){
	    layer.open({
	        type: 2,
	        title: "增加评论",
	        shadeClose: true,
	        shade: false,
	        maxmin: true, //开启最大化最小化按钮
	        area: ['800px', '500px'],
	        content: "../Comment/toInsertPage"
	      });
    });
}
function loadData(){    //负责数据的加载 
           //此处既然是无刷新分页,那么就要进行异步数据加载
           $.ajax({
               url : "../Comment/findAll"  ,                      //Ajax提交路径
               data : {                                             //传递的参数
            	   "cp"  :   jsCommonCp  ,
                   "ls"    :   jsCommonLs   ,
                   "kw" :   jsCommonKw ,
                   "time" : new Date()  
               }    ,
               type : "post"   ,                           //发送类型
               dataType : "json"   ,                  //返回值类型
               success : function(json){     //回调函数
	                   createSplitBar(json);    //有总记录数才能驱动分页组件 
	                   createSearchBar(json)  ;    //生成检索框  
	                   //随后生成分页的数据,会有所不同
	                   clearTable()    ;   //清空表格行
	                   for(var x=0;x<json.allComments.length;x++){
	                	   //准备数组,存储User数据
	                	   var commentArray=new Array(4) ;   //保存每一个User对象
	                	   commentArray[0]=json.allComments[x].commentId  ;   //评论ID
	                	   commentArray[1]=json.allComments[x].userName  ;   //用户名称
	                	   commentArray[2]=json.allComments[x].projectTitle  ; //项目标题
	                	   commentArray[3]=json.allComments[x].content  ; //项目内容 
	                	   addTableRow(commentArray)  ;  //添加一行 
	                   } 
	                   bind()  ;  //绑定超链接事件:删除、编辑、查看详情
               }   
        });
}   ;

function addTableRow(commentArray){   //放置数据
	  var row="<tr class=\"active\" id=\""+commentArray[0]+"\">"
	               +"<td><a href=\"#\" name=\"detail\">"+commentArray[0]+"</a></td><td>"
	               +commentArray[1]+"</td><td>"+commentArray[2]+"</td><td>"+commentArray[3]+"</td><td>"
	               +"<a name=\"edit\" href=\"#\">编辑&nbsp;"
	               +"<span class=\"glyphicon glyphicon-edit\"></span>"
	               +"</a></td><td><a name=\"del\" href=\"#\">删除&nbsp;"
	               +"<span class=\"glyphicon glyphicon-trash\"></span></a></td></tr>" ;
      $("#commentTab").append(row) ;
}    ;
function clearTable(){      //清空表格行 
      $("#commentTab tr:gt(0)").remove();     ; 
}   ;
function bind(){
	$("a[name='del']").click(function(){  //这里面可以完成删除的功能
		 var commentId=$(this).parent().parent().attr("id") ;           //取得用户编号,也是当前行的编号
		 layer.confirm("您确定删除评论["+commentId+"]?", {
			  btn: ["取消",'确定'] //按钮
			},function(){
				 layer.msg("已取消", {
					    time: 1000, //20s后自动关闭
					  });
			},
		      function(){
		           $.ajax({
		               url : "../Comment/doRemove"  ,                           //Ajax提交路径
		               data : {                                             //传递的参数
		            	   "commentId"  :   commentId  ,
		                   "time" : new Date()  
		               }    ,
		               type : "post"   ,                           //发送类型
		               dataType : "json"   ,                  //返回值类型
		               success : function(json){     //回调函数
			                     if(json.status==1){
			            	         //如果删除成功,就调用DOM删除当前行
			            	         //代码放在这
			            	         $("tr[id='"+commentId+"']").remove()  ;
			                    	 layer.msg("删除成功!", {icon: 1}) ;
			                     }else{
			                    	 layer.msg("删除失败!", {icon: 2}) ;
			                     }
		               }   
		          });
		      });
	});
	$("a[name='edit']").click(function(){
		     var commentId=$(this).parent().parent().attr("id") ;           //取得用户编号,也是当前行的编号
		    //使用IFrame加载页面
		   //为什么要在页面CRUD全上AJAX?1.减少服务器端压力;2.防止表单重复提交这种蛋疼的事情;
		    layer.open({
		        type: 2,
		        title: "编辑评论",
		        shadeClose: true,
		        shade: false,
		        maxmin: true, //开启最大化最小化按钮
		        area: ['800px', '500px'],
		        content: "../Comment/toEditPage/"+commentId,
		        end : function(){
			           $.ajax({
			               url : "../Comment/findById"  ,                           //Ajax提交路径
			               data : {                                             //传递的参数
			            	   "commentId"  :  commentId  ,
			                   "time"       :  new Date()  
			               }    ,
			               type : "post"   ,                           //发送类型
			               dataType : "json"   ,                  //返回值类型
			               success : function(json){     //回调函数
		                	   var commentId=json.commentId  ;
		                	   var name=json.user.name  ;
		                	   var title=json.project.title  ;
		                	   var content=json.content  ;
		                	   $("tr[id="+commentId+"] a[name='detail']").text(commentId)   ;
		                	   $("tr[id="+commentId+"] td:eq(1)").text(name)      ;
		                	   $("tr[id="+commentId+"] td:eq(2)").text(title)     ;
		                	   $("tr[id="+commentId+"] td:eq(3)").text(content)   ;
		                  }
		          });
		      }
	  });
});	    
	$("a[name='detail']").click(function(){
		 var commentId=$(this).parent().parent().attr("id") ;           //取得角色编号,也是当前行的编号
	    layer.open({
	        type: 2,
	        title: "评论详情",
	        shadeClose: true,
	        shade: false,
	        maxmin: true, //开启最大化最小化按钮
	        area: ['700px', '400px'],
	        content: "../Comment/toDetailPage/"+commentId
	      });		 
	});
}  ;
