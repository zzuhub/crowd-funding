$(function(){     //ֻҪ����ˢ�·�ҳ,��Ҫ����Ajax��̬����
    loadData()  ;    //ֻҪ������������Ϳ���ʵ�ֱ����������Լ���ҳ���Զ����� 
})  ;

function loadData(){    //�������ݵļ��� 
           //�˴���Ȼ����ˢ�·�ҳ,��ô��Ҫ�����첽���ݼ���
           $.post("../Permission/findAll",{
                   "cp"  :   jsCommonCp  ,
                   "ls"    :   jsCommonLs   ,
                   "kw" :   jsCommonKw ,
                   "date" : new Date()
           }  ,function(json){
               createSplitBar(json);    //���ܼ�¼������������ҳ��� 
               createSearchBar(json)  ;    //���ɼ�����  
               //������ɷ�ҳ������,��������ͬ
               clearTable()    ;   //��ձ����
               for(var x=0;x<json.allPermissions.length;x++){
                     var permissionId=json.allPermissions[x].permissionId   ;
                     var name=json.allPermissions[x].name    ;
                     var note=json.allPermissions[x].note    ;
                     addTableRow(permissionId,name,note)  ;   //������
               }
           },"json");
}   ;

function addTableRow(permissionId,name,note){
      $("#permissionTab").append("<tr class=\"active\"><td>"+permissionId+"</td><td>"+name+"</td><td>"+note+"</td></tr>");
}    ;
function clearTable(){      //��ձ���� 
      $("#permissionTab tr:gt(0)").remove();     ; 
}   ;