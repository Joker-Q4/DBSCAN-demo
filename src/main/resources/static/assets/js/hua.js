// 鼠标点击事件触发
function fun_do(r, minPoints) {
	
    var jsonobj = {};
    var colo = ["#00FF7F","#FFFF00","#FF8C00","#FF4500","#BA55D3"
    		,"#696969","#1E90FF","#BA55D3","#FF1493","#DC143C"
    		,"#2E8B57","#D2691E","#FA8072","#FF0000","#D8BFD8"
    		,"#00008B","#708090","#008B8B","#7CFC00","#FF6347"
    		,"#DB7093","#DA70D6","#000080","#F0F8FF","#5F9EA0"
    		,"#2F4F4F","#7FFFAA","#8FBC8F","#7FFF00","#F0E68C"];
    jsonobj.r = r;
    jsonobj.minPoints = minPoints;
    editflag = 1;
 //   jsonobj.r = '2';
 //   jsonobj.minPoints = '3';
	$.ajax({   //请求
		url: '/DBSCAN/Data',
		type : 'post', //请求方式
		data: {
			json : JSON.stringify(jsonobj)
		},
		dataType: 'json',
		success: function(msg) {
			var obj1 = JSON.stringify(msg);
			var obj2 = JSON.parse(obj1);
			okpoints = obj2;
			//将文件坐标转换为真实坐标
			/*okpointsreal = obj2;
			for (var i=0;i<okpointsreal.length;i++) {
				for(var j=0;j<okpointsreal[i].location.length;j++) {
					okpointsreal[i].location[j].x = parseFloat(okpointsreal[i].location[j].x)/1000*$("#mydiv").innerWidth();
					okpointsreal[i].location[j].y = parseFloat(okpointsreal[i].location[j].y)/500*$("#mydiv").innerHeight();
				}
			}*/
		//	var dela = 1000;
			var flag = 0;
			var id;
			var count = 0;
			var allcolor = new Array();
			var s = 0;
			$("#mysvg").empty();
			for (var i=0;i<obj2.length;i++) {
				var num = count;
				s += obj2[i].location.length;
				allcolor[i] = s;
				for(var j=0;j<obj2[i].location.length;j++) {
					id = "circle" + count;
					count++;
					var xx = parseFloat(obj2[i].location[j].x)/1000*$("#mydiv").innerWidth();
					var yy = parseFloat(obj2[i].location[j].y)/500*$("#mydiv").innerHeight();
					var name = 0;
					if(parseInt(obj2[i].location[j].key) == 1)
						name = 1;
					// 算进所有点
					let point = {};
					point.id = id;
					point.x = xx;
					point.y = yy;
					point.key = obj2[i].location[j].key;
					point.classed = obj2[i].location[j].classed;
					allpoints.push(point);
					
					var circle = document.createElementNS('http://www.w3.org/2000/svg','circle');
					circle.setAttribute('id', id);
					circle.setAttribute('name', name);
					circle.setAttribute('r', 4);
					circle.setAttribute('cx', xx);
					circle.setAttribute('cy', yy);
					circle.setAttribute('fill', 'white'); //用白色填充,用绿色覆盖
					circle.setAttribute('stroke', 'black');
					circle.setAttribute('stroke-width', 1);
					circle.setAttribute('fill-opacity', 1);
			        mysvg.appendChild(circle);
				}
			}
			layer.alert('设置完毕~', {
				title: '恭喜你',
				icon: 6,
				anim: 1,
				shadeClose: true,
				skin: 'layui-layer-molv',
				closeBtn: 0
			});
			$("#tipss").click(function(){
				showflag=0;
				clicktimes=0;
				if(flag == 1) {
					layer.msg("您已经聚类过，请勿重复点击！");
					return false;
				}
				layer.msg("预备");
				setTimeout(function(){
					layer.msg("开始",{time: 500});
				}, 300);
				// 只要被聚类就清空数组
				setTimeout(function(){
					appendPoints = [];  //清空附加数据
					newpoints = [];   //清空未生成文件，直接点击屏幕数据
					if(flag == 0) {
						console.log(flag);
						startclick = 1;
						flag = 1;
						rgo(0);
					}
				}, 1500);
			});
			
			//校验
			$("#checkbtn").click(function(){
				checkdistance(0);
				// 只要被聚类就清空数组
			/*	setTimeout(function(){
					appendPoints = [];  //清空附加数据
					newpoints = [];   //清空未生成文件，直接点击屏幕数据
					if(flag == 0) {
						console.log(flag);
						startclick = 1;
						flag = 1;
						rgo(0);
					}
				}, 1500);*/
			});
			
			function rgo(num) {
				var num2 = 0;
				//var flag = 0;
			    if(num == count) {    //函数结束条件$("#mydiv").innerHeight()
			    	var lev_x = rr/1000*$("#mydiv").innerWidth(); //横坐标应占长度
			    	var lev_y = rr/500*$("#mydiv").innerHeight(); //纵坐标应占长度
			    	//画横线
			    	var line = document.createElementNS('http://www.w3.org/2000/svg','line');
			    	line.setAttribute('x1', ($("#mydiv").innerWidth()-lev_x)/2);
			    	line.setAttribute('y1', $("#mydiv").innerHeight()/2);
			    	line.setAttribute('x2', ($("#mydiv").innerWidth()+lev_x)/2);
			    	line.setAttribute('y2', $("#mydiv").innerHeight()/2);
			    	line.setAttribute('style','stroke:#000');
			        mysvg.appendChild(line);
			        var line = document.createElementNS('http://www.w3.org/2000/svg','line');
			        line.setAttribute('x1', $("#mydiv").innerWidth()/2);
			    	line.setAttribute('y1', ($("#mydiv").innerHeight()-lev_y)/2);
			    	line.setAttribute('x2', $("#mydiv").innerWidth()/2);
			    	line.setAttribute('y2', ($("#mydiv").innerHeight()+lev_y)/2);
			    	line.setAttribute('style','stroke:#000');
			    	mysvg.appendChild(line);
			    	layer.msg('聚类完毕！',{time: 1300});
			        return false;
			    }
			    var curc = document.getElementById("circle" + num);
			    var r = curc.getAttribute('r');
			    var key = parseInt(curc.getAttribute ("name"));
			    for(var ii=0;ii<allcolor.length;ii++){
			    	if(num>=allcolor[ii] && num<allcolor[ii+1]) {
		    			curc.setAttribute('fill', colo[ii+1]);
		    		}else if(num<allcolor[0]) {
		    			curc.setAttribute('fill', colo[0]);
		    		}
		    	}
			    var tt = setInterval(function() {
			    	if(num > count)    //函数结束条件
	                	clearInterval(tt);
			    	if(num>=(s-obj2[obj2.length-1].location.length) && obj2[obj2.length-1].id=="999") {
			    		curc.setAttribute('fill', 'white');
			    		var htm = '<animate attributeName="r" from="1" to="13" dur="1.5s" repeatCount="indefinite">';
			    		$("#circle" + num).html(htm);
			    		clearInterval(tt);
			    		num++;
			    		rgo(num);
	                 //   var t = setTimeout(function(){rgo(num)}, 0);
			    	} else{
		                r *= 5;       //注意：*=已经自动把字符串变成浮点数
		                curc.setAttribute('r',r);
		                
		            //    var p = curc.getAttribute('fill-opacity');
		            //    p *= 0.95;
		            //    curc.setAttribute('fill-opacity',p);
		                if(r >= 11){    //当前圆形扩散到一定程度,停掉定时器
		                	if(key == 0) {
		                		clearInterval(tt);
			                    num++;
			                    var t = setTimeout(function(){rgo(num)}, 1);
		                	} else if(r >= 15){
		                		clearInterval(tt);
			                    num++;
			                    var t = setTimeout(function(){rgo(num)}, 1);
		                	}
		                }
			    	}
	            }, 25);
			};
			/*
			var temp = 0;
			//画线
			function checkdistance(temp) {
				var curc = document.getElementById("circle" + temp);
				var key = parseInt(curc.getAttribute ("name"));
				var temp1 = temp+1;
				var curc1 = document.getElementById("circle" + temp1);
				if(!(temp>=(s-obj2[obj2.length-1].location.length-1) && obj2[obj2.length-1].id=="999")) {
					let x1 = curc.getAttribute('cx');
					let y1 = curc.getAttribute('cy');
					let x2 = curc1.getAttribute('cx');
					let y2 = curc1.getAttribute('cy');
					if(key != 0) { //核心节点
						if(Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1)) <= r) {
							var line = document.createElementNS('http://www.w3.org/2000/svg','line');
					    	line.setAttribute('x1', curc.getAttribute('cx'));
					    	line.setAttribute('y1', curc.getAttribute('cy'));
					    	line.setAttribute('x2', curc1.getAttribute('cx'));
					    	line.setAttribute('y2', curc1.getAttribute('cy'));
					    	line.setAttribute('style','stroke:#000');
					        mysvg.appendChild(line);
						}
					} else {
						var fflag = 0;
						for(var temp2=temp-1;temp2>=0;temp2--) {}
						if(temp2 < 0) {flag = 1;}  //说明有核节点
						for(var temp2=temp-1;temp2>=0;temp2--) {
							var curc2 = document.getElementById("circle" + temp2);
							var key1 = parseInt(curc2.getAttribute ("name"));//前一个
							var key2 = parseInt(curc1.getAttribute ("name"));//当前
							if()
							if(key1 != 0) { //核节点
								var x0 = curc2.getAttribute('cx');
								var y0 = curc2.getAttribute('cy');
								if(key2 == 0) {
									if(Math.sqrt((x2-x0)*(x2-x0) + (y2-y0)*(y2-y0)) <= r) {
										var line = document.createElementNS('http://www.w3.org/2000/svg','line');
								    	line.setAttribute('x1', curc.getAttribute('cx'));
								    	line.setAttribute('y1', curc.getAttribute('cy'));
								    	line.setAttribute('x2', curc1.getAttribute('cx'));
								    	line.setAttribute('y2', curc1.getAttribute('cy'));
								    	line.setAttribute('style','stroke:#000');
								        mysvg.appendChild(line);
									}
								} else {}
								flag = 0;
								break;
							}
						}
						
					}
					
			        temp++;
			        var t = setTimeout(function(){checkdistance(temp)}, 25);
		    	}
			};*/
		}
	})
}