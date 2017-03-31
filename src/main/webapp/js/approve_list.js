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
	        title: "增加支持",
	        shadeClose: true,
	        shade: false,
	        maxmin: true, //开启最大化最小化按钮
	        area: ['800px', '500px'],
	        content: "../Approve/toInsertPage"
	      });
    });
}
function loadData(){    //负责数据的加载 
           //此处既然是无刷新分页,那么就要进行异步数据加载
           $.ajax({
               url : "../Approve/findAll"  ,                      //Ajax提交路径
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
	                   for(var x=0;x<json.allApproves.length;x++){
	                	   addTableRow(json.allApproves[x]) ;
	                   } 
	                   bind()  ;  //绑定超链接事件:删除、编辑、查看详情
               }   
        });
}   ;

function addTableRow(jsonApprove){
	  var row="<tr class=\"active\" id=\""+(jsonApprove.approveId)+"\"><td>"+"<a href=\"#\" name=\"detail\">"
	               +(jsonApprove.approveId)+"</a></td><td>"+(jsonApprove.projectName)+"</td><td>"+((jsonApprove.tel))+"</td><td>"
	               +(jsonApprove.name)+"</td><td>"+(jsonApprove.money)+"</td><td>"
	               +(jsonApprove.approveTime)+"</td><td>"
	               +"<a name=\"edit\" href=\"#\">编辑&nbsp;"
	               +"<span class=\"glyphicon glyphicon-edit\"></span>"
	               +"</a></td><td><a name=\"del\" href=\"#\">删除&nbsp;"
	               +"<span class=\"glyphicon glyphicon-trash\"></span></a></td></tr>" ;
      $("#approveTab").append(row) ;
}    ;
function clearTable(){      //清空表格行 
      $("#approveTab tr:gt(0)").remove();     ; 
}   ;
function bind(){
	$("a[name='del']").click(function(){  //这里面可以完成删除的功能
		 var approveId=$(this).parent().parent().attr("id") ;           //取得角色编号,也是当前行的编号
		 layer.confirm("您确定删除支持["+approveId+"]?", {
			  btn: ["取消",'确定'] //按钮
			},function(){
				 layer.msg("已取消", {
					    time: 1000, //20s后自动关闭
					  });
			},
		      function(){
		           $.ajax({
		               url : "../Approve/doRemove"  ,                           //Ajax提交路径
		               data : {                                             //传递的参数
		            	   "approveId"  :   approveId  ,
		                   "time" : new Date()  
		               }    ,
		               type : "post"   ,                           //发送类型
		               dataType : "json"   ,                  //返回值类型
		               success : function(json){     //回调函数
			                     if(json.status==1){
			            	         //如果删除成功,就调用DOM删除当前行
			            	         //代码放在这
			            	         $("tr[id='"+approveId+"']").remove()  ;
			                    	 layer.msg("删除成功!", {icon: 1}) ;
			                     }else{
			                    	 layer.msg("删除失败!", {icon: 2}) ;
			                     }
		               }   
		          });
		      });
	});
	$("a[name='edit']").click(function(){
		     var approveId=$(this).parent().parent().attr("id") ;           //取得角色编号,也是当前行的编号
		    //使用IFrame加载页面
		   //为什么要在页面CRUD全上AJAX?1.减少服务器端压力;2.防止表单重复提交这种蛋疼的事情;
		    layer.open({
		        type: 2,
		        title: "编辑支持",
		        shadeClose: true,
		        shade: false,
		        maxmin: true, //开启最大化最小化按钮
		        area: ['800px', '500px'],
		        content: "../Approve/toEditPage/"+approveId,
		        end : function(){
			           $.ajax({
			               url : "../Approve/findById"  ,                           //Ajax提交路径
			               data : {                                             //传递的参数
			            	   "approveId"  :   approveId  ,
			                   "time" : new Date()  
			               }    ,
			               type : "post"   ,                           //发送类型
			               dataType : "json"   ,                  //返回值类型
			               success : function(json){     //回调函数
				                     var name=json.name  ;     //姓名
				                     var tel=json.tel  ;    //联系方式
				                     //重新更新tr行每个td中的内容
				                     $("tr[id=\""+approveId+"\"]"+" td:eq(2)").text(tel)   ;  //修改联系方式DOM内容
				                     $("tr[id=\""+approveId+"\"]"+" td:eq(3)").text(name)   ;  //修改姓名DOM内容
			               }   
			          });		            
		        }
		      });
	});
	$("a[name='detail']").click(function(){
		 var approveId=$(this).parent().parent().attr("id") ;           //取得角色编号,也是当前行的编号
	    layer.open({
	        type: 2,
	        title: "支持详情",
	        shadeClose: true,
	        shade: false,
	        maxmin: true, //开启最大化最小化按钮
	        area: ['700px', '400px'],
	        content: "../Approve/toDetailPage/"+approveId
	      });		 
	});
}  ;
