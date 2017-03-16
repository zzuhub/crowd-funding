var jsCommonCp=1   ;      //默认显示第一页
var jsCommonLs=10 ;      //默认显示10条数据 
var jsCommonKw=""  ;   //关键词 
function createSplitBar(data){    //创建分页条
          // console.log("allRecords:"+allRecords) ;
           calcPageSize(data.allRecords)   ;   //分页开始之后首先要计算总页数
           clearBar()  ;    //清空已有的分页数据条,避免重复生成 
           //创建分页工具条 
          // console.log("jsCommonCp:"+jsCommonCp) ;
           previousPageBar()   ;
           addBar(1)   ;   //第一页应该一直出现
           var seed=3  ;   //定义种子数
           if(jsCommonCp>seed*2){   //超过预期的内容才会出现
               addDetailsPageBar()  ;     //增加省略页面 
               var startPage=jsCommonCp-seed   ;      //开始页
               for(var x=startPage;x<=jsCommonCp+seed;x++){
                       if(x<jsCommonPageSize){
                            addBar(x)   ;
                       }
               }
               if(jsCommonCp+seed*2<jsCommonPageSize){    //后面还有很多页
                      addDetailsPageBar()  ;     //增加省略页面 
               }
           }else{    //现在的页数还小于种子数
                    for(var x=2;x<jsCommonCp+seed;x++){
                          addBar(x)   ;
                    }
                    if(jsCommonCp+seed<jsCommonPageSize){    //后面还有很多页
                          addDetailsPageBar()  ;     //增加省略页面 
                   }
           }
           addBar(jsCommonPageSize)   ;   //尾页应该一直出现
           nextPageBar()  ;  
}   ;
function addBar(index){    //定义一个函数专门负责追加每一个li元素实现分页的控制颗粒
        var liObj=$("<li></li>") ;    //先创建元素在配置,因为后面牵扯到事件问题 
        var aObj=$("<a style=\"cursor:pointer\">"+index+"</a>") ;   
        if(jsCommonCp == index){   //现在为当前所在页
              aObj.addClass("active")  ;  //表示当前页为可用页
        }else{
            aObj.on("click",function(){
                        jsCommonCp=index ;      //改变当前页为可用页
                        loadData()  ;    //重新加载新的数据 
            })   ;
        }
        liObj.append(aObj)  ;
        $("#pageControl").append(liObj) ;   //追加到分页控制条 
}   ;
//如果要想进行分页,那么必须知道到底有多少页,那么这个总页数是通过计算得来的
function calcPageSize(allRecords){   //计算总页数
             if(allRecords==0){
                 jsCommonPageSize=1 ;   //保持1页
             }else{
                 //此时计算总页数时一定会存在小数点问题,将其用parseInt()函数解决掉
                 jsCommonPageSize=parseInt((allRecords+jsCommonLs-1)/jsCommonLs);
             }
}   ;

function clearBar(){
        $("#splitBarDiv").empty()  ;    //清空分页条所有的内容 
        $("#splitBarDiv").append("<ul id=\"pageControl\" class=\"pagination\"></ul>") ;   //追加一个ul
}   ;

function previousPageBar(){
        var liObj=$("<li></li>") ;    //先创建元素在配置,因为后面牵扯到事件问题 
        var aObj=$("<a style=\"cursor:pointer\">上一页</a>") ;   
        if(jsCommonCp == 1){   //现在为第一页
              aObj.addClass("disabled")  ;  //如果为第一页,则上一页不可用 
        }else{
            aObj.on("click",function(){
                  if(jsCommonCp>1){
                          jsCommonCp-- ;      //改变当前页为上一页
                          loadData()  ;    //重新加载新的数据 
                  }
            })   ;
        }
        liObj.append(aObj)  ;
        $("#pageControl").append(liObj) ;   //追加到分页控制条 
}   ;


function nextPageBar(){
    var liObj=$("<li></li>") ;    //先创建元素在配置,因为后面牵扯到事件问题 
    var aObj=$("<a style=\"cursor:pointer\">下一页</a>") ;   
    if(jsCommonCp == jsCommonPageSize){   //现在为尾页
          aObj.addClass("disabled")  ;  //如果为尾页,则下一页不可用 
    }else{
        aObj.on("click",function(){
              if(jsCommonCp < jsCommonPageSize){
                      jsCommonCp++ ;      //改变当前页为下一页
                      loadData()  ;    //重新加载新的数据 
              }
        })   ;
    }
    liObj.append(aObj)  ;
    $("#pageControl").append(liObj) ;   //追加到分页控制条 
}   ;

function addDetailsPageBar(){   //增加省略页
         $("#pageControl").append("<li><span>...</span></li>");
}   ;