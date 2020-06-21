function appendfile() {
	// let r = rr;
	// let minp = minpoints;
	// fun_do(r, minp);
	// debugger
	layer.msg('加载中', {
		  icon: 16,
		  shade: 0.01
	});
	alert(JSON.stringify(appendPoints))
	var app = appendPoints;
	$.ajax({   //请求
		url: '/DBSCAN/AppendFile',
		type : 'post', //请求方式
		data: {
			json : JSON.stringify(app)
		},
		dataType: 'text',
		success: function(msg) {
			if(msg == 'success') {
				let r = rr;
				let minp = minpoints;
				fun_do(r, minp);
			}
		},
		error: function(msg) {
			layer.alert('似乎出现错误了呢~', {
				title: '很遗憾',
				icon: 5,
				anim: 1,
				skin: 'layui-layer-molv',
				closeBtn: 0
			});
		}
	});
}

function afile() {
	layer.msg('加载中', {
		  icon: 16,
		  shade: 0.01
	});
	var app = appendPoints;
	$.ajax({   //请求
		url: '/DBSCAN/AFile',
		type : 'post', //请求方式
		data: {
			json: JSON.stringify(app)
		},
		dataType: 'text',
		success: function(msg) {
			let r = rr;
			let minp = minpoints;
			fun_do(r, minp);
		}
	});
}