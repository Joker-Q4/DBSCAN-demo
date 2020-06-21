function show() {
	if(showflag != 0) {
		layer.msg("已显示数据，切勿重复点击~");
		return false;
	}
	$.ajax({   //请求
		url: '/DBSCAN/SimpleData',
		type : 'post', //请求方式
		dataType: 'json',
		success: function(msg) {
			var obj1 = JSON.stringify(msg);
			var obj2 = JSON.parse(obj1);
			var flag = 0;
			var id;
			var count = 0;
			var allcolor = new Array();
			var s = 0;
			for (var i=0;i<obj2.length;i++) {
				//id = "circle" + count;
				//count++;
				var xx = parseFloat(obj2[i].x)/1000*$("#mydiv").innerWidth();
				var yy = parseFloat(obj2[i].y)/500*$("#mydiv").innerHeight();

				var circle = document.createElementNS('http://www.w3.org/2000/svg','circle');
				//circle.setAttribute('id', id);
				circle.setAttribute('r', 4);
				circle.setAttribute('cx', xx);
				circle.setAttribute('cy', yy);
				circle.setAttribute('fill', 'white'); //用白色填充,用绿色覆盖
				circle.setAttribute('stroke', 'black');
				circle.setAttribute('stroke-width', 1);
				circle.setAttribute('fill-opacity', 1);
		        mysvg.appendChild(circle);
			}
			if(showflag == 0) {
				layer.msg("已显示~");
				startclick = 0;
				showflag = 1;
			}
			appendPoints = msg;
		}
	})
}