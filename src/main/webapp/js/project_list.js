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
	        title: "增加项目",
	        shadeClose: true,
	        shade: false,
	        maxmin: true, //开启最大化最小化按钮
	        area: ['800px', '500px'],
	        content: "../Project/toInsertPage"
	      });
    });
}
function loadData(){    //负责数据的加载 
           //此处既然是无刷新分页,那么就要进行异步数据加载
           $.ajax({
               url : "../Project/findAll"  ,                      //Ajax提交路径
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
	                   for(var x=0;x<json.allProjects.length;x++){
	                	   //准备数组
	                	   var projectArray=new Array(9) ;   
	                	   projectArray[0]=json.allProjects[x].projectId  ;  
	                	   projectArray[1]=json.allProjects[x].title  ;  
	                	   projectArray[2]=json.allProjects[x].category.name  ;   
	                	   projectArray[3]=json.allProjects[x].user.name  ; 
	                	   projectArray[4]=json.allProjects[x].expectMoney  ; 
	                	   projectArray[5]=json.allProjects[x].realMoney  ; 
	                	   projectArray[6]=json.allProjects[x].tel  ; 
	                	   projectArray[7]=json.allProjects[x].status  ; 
	                	   projectArray[8]=json.allProjects[x].note  ; 
	                	   addTableRow(projectArray)  ;  //添加一行 
	                   } 
	                   bind()  ;  //绑定超链接事件:删除、编辑、查看详情
               }   
        });
}   ;

function addTableRow(projectArray){   //放置数据
	  var row="<tr class=\"active\" id=\""+projectArray[0]+"\">"
	               +"<td><a href=\"#\" name=\"detail\">"+projectArray[0]+"</a></td><td>"
	               +projectArray[1]+"</td><td>"+projectArray[2]+"</td><td>"+projectArray[3]+"</td><td>"
	               +projectArray[4]+"</td><td>"+projectArray[5]+"</td><td>"+projectArray[6]+"</td>"
	               +"<td>"+(projectArray[7]==0?"完成":"筹集中")+"</td><td>"+projectArray[8]+"</td>"
	               +"<td><a name=\"edit\" href=\"#\">编辑&nbsp;"
	               +"<span class=\"glyphicon glyphicon-edit\"></span>"
	               +"</a></td><td><a name=\"del\" href=\"#\">删除&nbsp;"
	               +"<span class=\"glyphicon glyphicon-trash\"></span></a></td></tr>" ;
      $("#projectTab").append(row) ;
}    ;
function clearTable(){      //清空表格行 
      $("#projectTab tr:gt(0)").remove();     ; 
}   ;
function bind(){
	$("a[name='del']").click(function(){  //这里面可以完成删除的功能
		 var projectId=$(this).parent().parent().attr("id") ;           //取得用户编号,也是当前行的编号
		 layer.confirm("您确定删除项目["+projectId+"]?", {
			  btn: ["取消",'确定'] //按钮
			},function(){
				 layer.msg("已取消", {
					    time: 1000, //20s后自动关闭
					  });
			},
		      function(){
		           $.ajax({
		               url : "../Project/doRemove"  ,                           //Ajax提交路径
		               data : {                                             //传递的参数
		            	   "projectId"  :   projectId  ,
		                   "time" : new Date()  
		               }    ,
		               type : "post"   ,                           //发送类型
		               dataType : "json"   ,                  //返回值类型
		               success : function(json){     //回调函数
			                     if(json.status==1){
			            	         //如果删除成功,就调用DOM删除当前行
			            	         //代码放在这
			            	         $("tr[id='"+projectId+"']").remove()  ;
			                    	 layer.msg("删除成功!", {icon: 1}) ;
			                     }else{
			                    	 layer.msg("删除失败!", {icon: 2}) ;
			                     }
		               }   
		          });
		      });
	});
	$("a[name='edit']").click(function(){
		     var projectId=$(this).parent().parent().attr("id") ;           //取得用户编号,也是当前行的编号
		    //使用IFrame加载页面
		   //为什么要在页面CRUD全上AJAX?1.减少服务器端压力;2.防止表单重复提交这种蛋疼的事情;
		    layer.open({
		        type: 2,
		        title: "编辑项目",
		        shadeClose: true,
		        shade: false,
		        maxmin: true, //开启最大化最小化按钮
		        area: ['800px', '500px'],
		        content: "../Project/toEditPage/"+projectId,
		        end : function(){
			           $.ajax({
			               url : "../Project/findEntityById"  ,                           //Ajax提交路径
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
	$("a[name='detail']").click(function(){
		 var projectId=$(this).parent().parent().attr("id") ;           //取得角色编号,也是当前行的编号
	    layer.open({
	        type: 2,
	        title: "项目详情",
	        shadeClose: true,
	        shade: false,
	        maxmin: true, //开启最大化最小化按钮
	        area: ['700px', '400px'],
	        content: "../Project/toDetailPage/"+projectId
	      });		 
	});
}  ;
