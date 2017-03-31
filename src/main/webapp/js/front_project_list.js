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
})  ;
function loadData(){    //负责数据的加载 
           //此处既然是无刷新分页,那么就要进行异步数据加载
	       var root=$("#root").val() ;   //取得根目录
           $.ajax({
               url : root+"/Project/findAll"  ,                      //Ajax提交路径
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
	               +"<td>"+projectArray[0]+"</td><td>"
	               +projectArray[1]+"</td><td>"+projectArray[2]+"</td><td>"+projectArray[3]+"</td><td>"
	               +projectArray[4]+"</td><td>"+projectArray[5]+"</td><td>"+projectArray[6]+"</td>"
	               +"<td>"+(projectArray[7]==0?"完成":"筹集中")+"</td><td>"+projectArray[8]+"</td>"
	               +"<td><a name=\"add\" href=\"#\">筹资&nbsp;"
	               +"<span class=\"glyphicon glyphicon-plus\"></span>"
	               +"</a></td><td><a name=\"detail\" href=\"#\">详情&nbsp;<span class=\"glyphicon glyphicon-road\"></span></td></tr>"   ;
      $("#projectTab").append(row) ;
}    ;
function clearTable(){      //清空表格行 
      $("#projectTab tr:gt(0)").remove();     ; 
}   ;
function bind(){
	$("a[name='add']").click(function(){
		var projectId=$(this).parent().parent().attr("id") ;           //取得角色编号,也是当前行的编号
		if($("tr[id="+projectId+"] td:eq(7)").text()=="完成"){
			layer.msg("众筹已完成,请选择其它", {icon: 5});
			return false ;
		}
		var root=$("#root").val() ;   //取得根目录
	    layer.open({
	        type: 2,
	        title: "项目筹资",
	        shadeClose: true,
	        shade: false,
	        maxmin: true, //开启最大化最小化按钮
	        area: ['800px', '500px'],
	        content: root+"/Approve/toGuestInsertPage/"+projectId,
	        end : function(){   //关闭窗口之后更新项目所在行数据
	            $.ajax({
	                url : root+"/Project/findEntityById"  ,                      //Ajax提交路径
	                data : {                                             //传递的参数
	             	    "projectId"  :   projectId  ,
	                    "time" : new Date()  
	                }    ,
	                type : "post"   ,                           //发送类型
	                dataType : "json"   ,                  //返回值类型
	                success : function(json){     //回调函数
	                	   var realMoney=json.realMoney ;
	                	   $("tr[id=\""+projectId+"\"] td:eq(5)").text(realMoney)  ;   //修改真实资金
	                }   
	           });
	       }
	    });
	    return true  ;
	});
	
	$("a[name='detail']").click(function(){
		var projectId=$(this).parent().parent().attr("id") ;           //取得角色编号,也是当前行的编号
		var root=$("#root").val() ;   //取得根目录
		window.location=root+"/Project/toDetailPageByGuest/"+projectId  ;
	});
	
}  ;
