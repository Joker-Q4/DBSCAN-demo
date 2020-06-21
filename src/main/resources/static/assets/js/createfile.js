// 鼠标点击事件触发
function createFile(Points) {
	layer.msg('加载中', {
		  icon: 16,
		  shade: 0.01
	});
	$.ajax({   //请求
		url: '/DBSCAN/CFile',
		type : 'post', //请求方式
		data: {
			num : Points
		},
		dataType: 'text',
		success: function(msg) {
			setTimeout(function(){
				if(msg == 'success') {
					layer.alert('文件已成功生成！', {
						title: '恭喜你',
						icon: 6,
						skin: 'layui-layer-molv',
						shadeClose: true,
						closeBtn: 0
					}, function(){
						location.reload();
					});
				/*	layer.confirm('文件已成功生成！是否立刻进行聚类展示？', {
						  btn: ['好的','不需要'] //按钮
						}, function(index){
							layer.close(index);
							$("#tips").click();
						}, function(index){
							layer.close(index);
						});*/
				} else{
					layer.msg(msg, {time: 1300});
				}
			}, 500);
			
		}
	})
}