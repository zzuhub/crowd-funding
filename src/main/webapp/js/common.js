function ajaxInit(){
	//配置Ajax加载数据弹层(通用)
	/**
	 * author 王成键
	 * 这里有一点需要说明的是，当同时存在多个ajax时，可能会一个加载完了，就把其他的全都关掉了，对于这个，我能想到的有如下两种解决方案：
     *1.我目前的解决办法是让他打开多个（坐标都一样，看不出来），然后关的时候哪个结束了就关哪个，
     *  我这里的做法是给ajaxSetup里面增加了个index参数（这玩意儿只能写到设置的对象里面，不
     *  然还是所有的ajax共享同一个）,有了index,应该就怎么玩都行了。
     *2.还有一种解决方案适合于自己去写这个控制逻辑，只显示一个加载框,
     *  在加载框上写个当前有多少ajax在执行的参数类似于<div data-ajax-count='0'></div>,
     *  每次开始或结束的时候，去维护ajax-cout的值，并且在ajax结束的时候去判断,
     *  如果这个data-ajax-count小于等于0了，把div隐藏起来，应该也是可以的,
     *  这种办法我并没有实践.
	 * 
	 * 
	 */
	$.ajaxSetup({
		 layerIndex : -1   ,
		 beforeSend : function(){
			 this.layerIndex = layer.load(0, {shade: false});
		 }  ,
		 complete : function(){
			 layer.close(this.layerIndex)  ;
		 }   ,
		 error : function(){
			 layer.alert('对不起系统异常,请刷新后重试', {
                  skin: 'layui-layer-molv'
                , closeBtn: 0
                , shift: 4 //动画类型
             });     
		 }
	});
} ;



