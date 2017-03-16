//<div id="searchDiv"></div>

function createSearchBar(data){   //生成检索框 
       jsCommonKw=data.kw    ;   //当前使用的默认关键字 
       clearSearchBar()   ;    //清空原有的搜索框 
       addSearchText(jsCommonKw,data.allRecords,data.currentSize)   ;   //创建搜索框 
} ;

function clearSearchBar(){
          $("#inputDiv").empty()   ;
          $("#butDiv").empty()   ;
          $("#infoDiv").empty()   ;
}   ;

function addSearchText(kw,allRecords,currentSize){
          $("#inputDiv").append("<input type=\"text\" name=\"kw\" id=\"kw\" value=\""+kw+"\" class=\"form-control\">") ;   
          var butObj=$("<input type=\"button\" value=\"检索\" class=\"btn btn-success\">") ;   //检索按钮
          butObj.on("click",function(){
                  jsCommonCol=$("#col").val()   ;    //重新读取下拉列表的值
                  jsCommonKw=$("#kw").val()   ;     //重新读取关键词
                  loadData()   ;   //重新加载数据 
          }) ;
          $("#butDiv").append(butObj) ;   
          $("#infoDiv").append("<div><span class=\"glyphicon glyphicon-search\"></span>[查询分析]&nbsp;&nbsp;&nbsp;总记录数 : "+allRecords+"&nbsp;&nbsp;&nbsp;当前页记录数 : "+currentSize+"</div>")  ;   
}   ;


