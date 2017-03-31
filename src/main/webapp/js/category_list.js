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
	        title: "增加类别",
	        shadeClose: true,
	        shade: false,
	        maxmin: true, //开启最大化最小化按钮
	        area: ['800px', '500px'],
	        content: "../Category/toInsertPage"
	      });
    });
}
function loadData(){    //负责数据的加载 
           //此处既然是无刷新分页,那么就要进行异步数据加载
           $.ajax({
               url : "../Category/findAll"  ,                      //Ajax提交路径
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
	                   for(var x=0;x<json.allCategories.length;x++){
	                	   //准备数组,存储User数据
	                	   var categoryArray=new Array(3) ;   //保存每一个User对象
	                	   categoryArray[0]=json.allCategories[x].id  ;
	                	   categoryArray[1]=json.allCategories[x].name  ;
	                	   categoryArray[2]=json.allCategories[x].projectSize  ;
	                	   addTableRow(categoryArray)  ;  //添加一行 
	                   } 
	                   bind()  ;  //绑定超链接事件:删除、编辑、查看详情
               }   
        });
}   ;

function addTableRow(categoryArray){   //放置数据
	  var row="<tr class=\"active\" id=\""+categoryArray[0]+"\"><td>"+categoryArray[0]+"</td><td><a href=\"#\" name=\"detail\">"
	               +categoryArray[1]+"</a></td><td>"+categoryArray[2]+"</td><td>"
	               +"<a name=\"edit\" href=\"#\">编辑&nbsp;"
	               +"<span class=\"glyphicon glyphicon-edit\"></span>"
	               +"</a></td><td><a name=\"del\" href=\"#\">删除&nbsp;"
	               +"<span class=\"glyphicon glyphicon-trash\"></span></a></td></tr>" ;
      $("#categoryTab").append(row) ;
}    ;
function clearTable(){      //清空表格行 
      $("#categoryTab tr:gt(0)").remove();     ; 
}   ;
function bind(){
	$("a[name='del']").click(function(){  //这里面可以完成删除的功能
		 var id=$(this).parent().parent().attr("id") ;           //取得类别编号,也是当前行的编号
		 layer.confirm("您确定删除类别["+id+"]?", {
			  btn: ["取消",'确定'] //按钮
			},function(){
				 layer.msg("已取消", {
					    time: 1000, //20s后自动关闭
					  });
			},
		      function(){
		           $.ajax({
		               url : "../Category/doRemove"  ,                           //Ajax提交路径
		               data : {                                             //传递的参数
		            	   "id"  :   id  ,
		                   "time" : new Date()  
		               }    ,
		               type : "post"   ,                           //发送类型
		               dataType : "json"   ,                  //返回值类型
		               success : function(json){     //回调函数
			                     if(json.status==1){
			            	         //如果删除成功,就调用DOM删除当前行
			            	         //代码放在这
			            	         $("tr[id='"+id+"']").remove()  ;
			                    	 layer.msg("删除成功!", {icon: 1}) ;
			                     }else{
			                    	 layer.msg("删除失败!", {icon: 2}) ;
			                     }
		               }   
		          });
		      });
	});
	$("a[name='edit']").click(function(){
		     var id=$(this).parent().parent().attr("id") ;           //取得类别编号,也是当前行的编号
		    //使用IFrame加载页面
		   //为什么要在页面CRUD全上AJAX?1.减少服务器端压力;2.防止表单重复提交这种蛋疼的事情;
		    layer.open({
		        type: 2,
		        title: "编辑类别",
		        shadeClose: true,
		        shade: false,
		        maxmin: true, //开启最大化最小化按钮
		        area: ['800px', '500px'],
		        content: "../Category/toEditPage/"+id,
		        end : function(){
			           $.ajax({
			               url : "../Category/findById"  ,                           //Ajax提交路径
			               data : {                                             //传递的参数
			            	   "id"  :   id  ,
			                   "time" : new Date()  
			               }    ,
			               type : "post"   ,                           //发送类型
			               dataType : "json"   ,                  //返回值类型
			               success : function(json){     //回调函数
		                	   var id=json.id  ;
		                	   var name=json.name  ;
		                	   var projectSize=json.projectSize  ;
		                	   $("tr[id="+id+"] a[name='detail']").text(name)   ;
		                	   //$("tr[id="+id+"] td:eq(1)").text(name)   ;
		                	   $("tr[id="+id+"] td:eq(2)").text(projectSize)   ;
			               }   
			          });
		        }
		      });
	});
	$("a[name='detail']").click(function(){
		 var id=$(this).parent().parent().attr("id") ;           //取得角色编号,也是当前行的编号
	    layer.open({
	        type: 2,
	        title: "类别详情",
	        shadeClose: true,
	        shade: false,
	        maxmin: true, //开启最大化最小化按钮
	        area: ['700px', '400px'],
	        content: "../Category/toDetailPage/"+id
	      });		 
	});
}  ;
