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
	        title: "增加图片",
	        shadeClose: true,
	        shade: false,
	        maxmin: true, //开启最大化最小化按钮
	        area: ['800px', '500px'],
	        content: "../Photo/toInsertPage"
	      });
    });
}
function loadData(){    //负责数据的加载 
           //此处既然是无刷新分页,那么就要进行异步数据加载
           $.ajax({
               url : "../Photo/findAll"  ,                      //Ajax提交路径
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
	                   for(var x=0;x<json.allPhotos.length;x++){
	                	   addTableRow(json.allPhotos[x]) ;
	                   } 
	                   bind()  ;  //绑定超链接事件:删除、编辑、查看详情
               }   
        });
}   ;

function addTableRow(jsonPhoto){
	  var row="<tr class=\"active\" id=\""+(jsonPhoto.photoId)+"\"><td>"+"<a href=\"#\" name=\"detail\">"
	               +(jsonPhoto.photoId)+"</a></td><td>"+(jsonPhoto.project.title)+"</td><td>"+(jsonPhoto.name)+"</td>"
	               +"<td><a name=\"del\" href=\"#\">删除&nbsp;"
	               +"<span class=\"glyphicon glyphicon-trash\"></span></a></td></tr>" ;
      $("#photoTab").append(row) ;
}    ;
function clearTable(){      //清空表格行 
      $("#photoTab tr:gt(0)").remove();     ; 
}   ;
function bind(){
	$("a[name='del']").click(function(){  //这里面可以完成删除的功能
		 var photoId=$(this).parent().parent().attr("id") ;           //取得角色编号,也是当前行的编号
		 layer.confirm("您确定删除图片["+photoId+"]?", {
			  btn: ["取消",'确定'] //按钮
			},function(){
				 layer.msg("已取消", {
					    time: 1000, //20s后自动关闭
					  });
			},
		      function(){
		           $.ajax({
		               url : "../Photo/doRemove"  ,                           //Ajax提交路径
		               data : {                                             //传递的参数
		            	   "photoId"  :   photoId  ,
		                   "time" : new Date()  
		               }    ,
		               type : "post"   ,                           //发送类型
		               dataType : "json"   ,                  //返回值类型
		               success : function(json){     //回调函数
			                     if(json.status==1){
			            	         //如果删除成功,就调用DOM删除当前行
			            	         //代码放在这
			            	         $("tr[id='"+photoId+"']").remove()  ;
			                    	 layer.msg("删除成功!", {icon: 1}) ;
			                     }else{
			                    	 layer.msg("删除失败!", {icon: 2}) ;
			                     }
		               }   
		          });
		      });
	});
	$("a[name='detail']").click(function(){
		var photoId=$(this).parent().parent().attr("id") ;           //取得角色编号,也是当前行的编号
	    layer.open({
	        type: 2,
	        title: "支持详情",
	        shadeClose: true,
	        shade: false,
	        maxmin: true, //开启最大化最小化按钮
	        area: ['300px', '300px'],
	        content: "../Photo/toDetailPage/"+photoId
	      });		 
	});
}  ;
