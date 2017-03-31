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
	        title: "增加用户",
	        shadeClose: true,
	        shade: false,
	        maxmin: true, //开启最大化最小化按钮
	        area: ['800px', '500px'],
	        content: "../User/toInsertPage"
	      });
    });
}
function loadData(){    //负责数据的加载 
           //此处既然是无刷新分页,那么就要进行异步数据加载
           $.ajax({
               url : "../User/findAll"  ,                      //Ajax提交路径
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
	                   for(var x=0;x<json.allUsers.length;x++){
	                	   //准备数组,存储User数据
	                	   var userArray=new Array(9) ;   //保存每一个User对象
	                	   userArray[0]=json.allUsers[x].userId  ;
	                	   userArray[1]=json.allUsers[x].name  ;
	                	   userArray[2]=json.allUsers[x].password  ;
	                	   userArray[3]=json.allUsers[x].lastLogin  ;
	                	   userArray[4]=json.allUsers[x].status  ;
	                	   userArray[5]=json.allUsers[x].loginErr  ;
	                	   userArray[6]=json.allUsers[x].createTime  ;
	                	   userArray[7]=json.allUsers[x].lastIP  ;
	                	   userArray[8]=json.allUsers[x].roleSize  ;
	                	   addTableRow(userArray)  ;  //添加一行 
	                   } 
	                   bind()  ;  //绑定超链接事件:删除、编辑、查看详情
               }   
        });
}   ;

function addTableRow(userArray){   //放置数据
	  var row="<tr class=\"active\" id=\""+userArray[0]+"\"><td>"+userArray[0]+"</td><td><a href=\"#\" name=\"detail\">"
	               +userArray[1]+"</a></td><td>"+userArray[2]+"</td><td>"+userArray[3]+"</td><td>"
	               +(userArray[4]==0?"锁定":"正常")+"</td><td>"+userArray[5]+"</td><td>"+userArray[6]+"</td><td>"
	               +userArray[7]+"</td><td>"+userArray[8]+"</td><td>"
	               +"<a name=\"edit\" href=\"#\">编辑&nbsp;"
	               +"<span class=\"glyphicon glyphicon-edit\"></span>"
	               +"</a></td><td><a name=\"del\" href=\"#\">删除&nbsp;"
	               +"<span class=\"glyphicon glyphicon-trash\"></span></a></td></tr>" ;
      $("#userTab").append(row) ;
}    ;
function clearTable(){      //清空表格行 
      $("#userTab tr:gt(0)").remove();     ; 
}   ;
function bind(){
	$("a[name='del']").click(function(){  //这里面可以完成删除的功能
		 var userId=$(this).parent().parent().attr("id") ;           //取得用户编号,也是当前行的编号
		 layer.confirm("您确定删除用户["+userId+"]?", {
			  btn: ["取消",'确定'] //按钮
			},function(){
				 layer.msg("已取消", {
					    time: 1000, //20s后自动关闭
					  });
			},
		      function(){
		           $.ajax({
		               url : "../User/doRemove"  ,                           //Ajax提交路径
		               data : {                                             //传递的参数
		            	   "userId"  :   userId  ,
		                   "time" : new Date()  
		               }    ,
		               type : "post"   ,                           //发送类型
		               dataType : "json"   ,                  //返回值类型
		               success : function(json){     //回调函数
			                     if(json.status==1){
			            	         //如果删除成功,就调用DOM删除当前行
			            	         //代码放在这
			            	         $("tr[id='"+userId+"']").remove()  ;
			                    	 layer.msg("删除成功!", {icon: 1}) ;
			                     }else{
			                    	 layer.msg("删除失败!", {icon: 2}) ;
			                     }
		               }   
		          });
		      });
	});
	$("a[name='edit']").click(function(){
		     var userId=$(this).parent().parent().attr("id") ;           //取得用户编号,也是当前行的编号
		    //使用IFrame加载页面
		   //为什么要在页面CRUD全上AJAX?1.减少服务器端压力;2.防止表单重复提交这种蛋疼的事情;
		    layer.open({
		        type: 2,
		        title: "编辑用户",
		        shadeClose: true,
		        shade: false,
		        maxmin: true, //开启最大化最小化按钮
		        area: ['800px', '500px'],
		        content: "../User/toEditPage/"+userId,
		        end : function(){
			           $.ajax({
			               url : "../User/findById"  ,                           //Ajax提交路径
			               data : {                                             //传递的参数
			            	   "userId"  :   userId  ,
			                   "time" : new Date()  
			               }    ,
			               type : "post"   ,                           //发送类型
			               dataType : "json"   ,                  //返回值类型
			               success : function(json){     //回调函数
			            	   if(json){
			                	   var userId=json.userId  ;
			                	   var name=json.name  ;
			                	   var password=json.password  ;
			                	   var lastLogin=json.lastLogin  ;
			                	   var status=json.status  ;
			                	   var loginErr=json.loginErr  ;
			                	   var createTime=json.createTime  ;
			                	   var lastIP=json.lastIP  ;
			                	   var roleSize=json.roleSize  ;
			                	   $("tr[id="+userId+"] a[name='detail']").text(name)   ;
			                	   $("tr[id="+userId+"] td:eq(2)").text(password)   ;
			                	   $("tr[id="+userId+"] td:eq(3)").text(lastLogin)   ;
			                	   $("tr[id="+userId+"] td:eq(4)").text(status==0?"锁定":"正常")   ;
			                	   $("tr[id="+userId+"] td:eq(5)").text(loginErr)   ;
			                	   $("tr[id="+userId+"] td:eq(6)").text(createTime)   ;
			                	   $("tr[id="+userId+"] td:eq(7)").text(lastIP)   ;
			                	   $("tr[id="+userId+"] td:eq(8)").text(roleSize)   ;
			            	   }
			               }   
			          });
		        }
		      });
	});
	$("a[name='detail']").click(function(){
		 var userId=$(this).parent().parent().attr("id") ;           //取得角色编号,也是当前行的编号
	    layer.open({
	        type: 2,
	        title: "用户详情",
	        shadeClose: true,
	        shade: false,
	        maxmin: true, //开启最大化最小化按钮
	        area: ['700px', '400px'],
	        content: "../User/toDetailPage/"+userId
	      });		 
	});
}  ;
