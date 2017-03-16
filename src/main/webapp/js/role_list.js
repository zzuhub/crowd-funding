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
	        title: "增加角色",
	        shadeClose: true,
	        shade: false,
	        maxmin: true, //开启最大化最小化按钮
	        area: ['800px', '500px'],
	        content: "../Role/toInsertPage",
	        end : function(){
	        	layer.msg("数据已录入,请刷新页面或关键词检索");
	        }
	      });
    });
}
function loadData(){    //负责数据的加载 
           //此处既然是无刷新分页,那么就要进行异步数据加载
           $.ajax({
               url : "../Role/findAll"  ,                      //Ajax提交路径
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
	                   for(var x=0;x<json.allRoles.length;x++){
	                         var roleId=json.allRoles[x].roleId   ;
	                         var name=json.allRoles[x].name    ;
	                         var note=json.allRoles[x].note    ;
	                         var permissionSize=json.allRoles[x].permissionSize    ;
	                         addTableRow(roleId,name,note,permissionSize)  ;   //增加行
	                   } 
	                   bind()  ;  //绑定超链接事件:删除、编辑、查看详情
               }   
        });
}   ;

function addTableRow(roleId,name,note,permissionSize){
	  var row="<tr class=\"active\" id=\""+roleId+"\"><td>"+roleId+"</td><td><a href=\"#\" name=\"detail\">"
	               +name+"</a></td><td>"+note+"</td><td>"+permissionSize+"</td><td>"
	               +"<a name=\"edit\" href=\"#\">编辑&nbsp;"
	               +"<span class=\"glyphicon glyphicon-edit\"></span>"
	               +"</a></td><td><a name=\"del\" href=\"#\">删除&nbsp;"
	               +"<span class=\"glyphicon glyphicon-trash\"></span></a></td></tr>" ;
      $("#roleTab").append(row) ;
}    ;
function clearTable(){      //清空表格行 
      $("#roleTab tr:gt(0)").remove();     ; 
}   ;
function bind(){
	$("a[name='del']").click(function(){  //这里面可以完成删除的功能
		 var roleId=$(this).parent().parent().attr("id") ;           //取得角色编号,也是当前行的编号
		 layer.confirm("您确定删除角色["+roleId+"]?", {
			  btn: ["取消",'确定'] //按钮
			},function(){
				 layer.msg("已取消", {
					    time: 1000, //20s后自动关闭
					  });
			},
		      function(){
		           $.ajax({
		               url : "../Role/doRemove"  ,                           //Ajax提交路径
		               data : {                                             //传递的参数
		            	   "roleId"  :   roleId  ,
		                   "time" : new Date()  
		               }    ,
		               type : "post"   ,                           //发送类型
		               dataType : "json"   ,                  //返回值类型
		               success : function(json){     //回调函数
			                     if(json.status==1){
			            	         //如果删除成功,就调用DOM删除当前行
			            	         //代码放在这
			            	         $("tr[id='"+roleId+"']").remove()  ;
			                    	 layer.msg("删除成功!", {icon: 1}) ;
			                     }else{
			                    	 layer.msg("删除失败!", {icon: 2}) ;
			                     }
		               }   
		          });
		      });
	});
	$("a[name='edit']").click(function(){
		     var roleId=$(this).parent().parent().attr("id") ;           //取得角色编号,也是当前行的编号
		    //使用IFrame加载页面
		   //为什么要在页面CRUD全上AJAX?1.减少服务器端压力;2.防止表单重复提交这种蛋疼的事情;
		    layer.open({
		        type: 2,
		        title: "编辑角色",
		        shadeClose: true,
		        shade: false,
		        maxmin: true, //开启最大化最小化按钮
		        area: ['800px', '500px'],
		        content: "../Role/toEditPage/"+roleId,
		        end : function(){
			           $.ajax({
			               url : "../Role/findById"  ,                           //Ajax提交路径
			               data : {                                             //传递的参数
			            	   "roleId"  :   roleId  ,
			                   "time" : new Date()  
			               }    ,
			               type : "post"   ,                           //发送类型
			               dataType : "json"   ,                  //返回值类型
			               success : function(json){     //回调函数
				                     var name=json.name  ;     //角色名称
				                     var note=json.note  ;    //说明
				                     var permissionSize=json.permissions.length  ;   //权限个数
				                     //重新更新tr行每个td中的内容
				                     $("tr[id="+roleId+"] a[name='detail']").text(name)   ;  //修改角色名称
				                     $("#"+roleId+" td:nth-child(3)").text(note)   ;  //修改角色说明
				                     $("#"+roleId+" td:nth-child(4)").text(permissionSize)   ;  //修改角色说明
			               }   
			          });		            
		        }
		      });
	});
	$("a[name='detail']").click(function(){
		 var roleId=$(this).parent().parent().attr("id") ;           //取得角色编号,也是当前行的编号
	    layer.open({
	        type: 2,
	        title: "角色详情",
	        shadeClose: true,
	        shade: false,
	        maxmin: true, //开启最大化最小化按钮
	        area: ['600px', '300px'],
	        content: "../Role/toDetailPage/"+roleId
	      });		 
	});
}  ;
