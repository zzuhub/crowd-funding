$(function(){     //只要是无刷新分页,就要进行Ajax动态设置
	ajaxInit() ;    //初始化AJAX设置
    loadData()  ;    //只要调用这个函数就可以实现表格数据填充以及分页的自动生成
})  ;

function loadData(){    //负责数据的加载 
           //此处既然是无刷新分页,那么就要进行异步数据加载
           $.ajax({
               url : "../Permission/findAll"  ,                      //Ajax提交路径
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
	                   for(var x=0;x<json.allPermissions.length;x++){
	                         var permissionId=json.allPermissions[x].permissionId   ;
	                         var name=json.allPermissions[x].name    ;
	                         var note=json.allPermissions[x].note    ;
	                         addTableRow(permissionId,name,note)  ;   //增加行
	                   }            	   
               }   
        });
}   ;

function addTableRow(permissionId,name,note){
      $("#permissionTab").append("<tr class=\"active\"><td>"+permissionId+"</td><td>"+name+"</td><td>"+note+"</td></tr>");
}    ;
function clearTable(){      //清空表格行 
      $("#permissionTab tr:gt(0)").remove();     ; 
}   ;