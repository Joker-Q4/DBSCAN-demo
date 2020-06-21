// 计算两点间距离
function getDistance(p, q) {
	let dx = p.x - q.x;
	let dy = p.y - q.y;
	let distance = Math.sqrt(dx*dx + dy*dy);
	return distance;
}
// 判断是否为核心节点 如果不是返回空 如果是返回直接可达节点集合
function isKeyPoint(p) {
	let newlist = [];
	let count = 1;
	for(var i=0;i<allpoints.length;i++) {
		if(getDistance(p, allpoints[i]) <= rr) {
			newlist.push(allpoints[i]);
			++count;
		}
	}
	if(count >= counts) {
		return newlist;
	}
	return null;
}

//拓展部分
function expand(point) {
	var list = isKeyPoint(point);
	let i;
	// 自身为核节点
	if(list != null) {
		//遍历所有的点
		//循环遍历周围点是否为核节点
		//第一次判断原有核节点
		var yflag = 0;
		//第一次找到核节点
		var fflag = 0;
		var newcolor1; //变成的颜色
		for(i=0;i<list.length;i++) {
			//该点为核节点
			if(isKeyPoint(list[i]) != null) {
				//原有的核节点
				if(list[i].key == 1) {
					if(fflag == 0) {
						fflag++;
						//令颜色为该类颜色
						for(var j=0;j<allpoints.length;j++) {
							if(allpoints[j].x==list[i].x && allpoints[j].y==list[i].y)
								break;
						}
						let id = allpoints[j].id;
						newcolor1 = document.getElementById(id).getAttribute("fill");//找到聚类颜色
						//p变色
						var circle = document.createElementNS('http://www.w3.org/2000/svg','circle');
					//	circle.setAttribute('id', id);
					//	circle.setAttribute('name', 1);
						circle.setAttribute('r', 16);
						circle.setAttribute('cx', point.x);
						circle.setAttribute('cy', point.y);
						circle.setAttribute('fill', newcolor); //用白色填充,用绿色覆盖
						circle.setAttribute('stroke', 'black');
						circle.setAttribute('stroke-width', 1);
						circle.setAttribute('fill-opacity', 1);
				        mysvg.appendChild(circle);
				        //将p加入该聚类
						//p周围所有核节点变色
					}
					yflag = 1;
				}
				//新核节点
				else {
					if(fflag == 0) {}
				}
			}
			
			
			if(list[i].key == 1) {
				break;
			}
		}
		//说明有核
		if(i != list.length) {
			//判断该核原有
			//遍历核
			var pflag = 0; 
			while (pflag == 0) {
				
				//说明新增
				if(j == allpoints.length) {
					
				}
				//说明原有
				else {}
			}
			
			
			
			
			let id = allpoints[j].id;
			newcolor = document.getElementById(id).getAttribute("fill");   //找到聚类颜色
			//生成点
			var circle = document.createElementNS('http://www.w3.org/2000/svg','circle');
		//	circle.setAttribute('id', id);
		//	circle.setAttribute('name', 1);
			circle.setAttribute('r', 16);
			circle.setAttribute('cx', point.x);
			circle.setAttribute('cy', point.y);
			circle.setAttribute('fill', newcolor); //用白色填充,用绿色覆盖
			circle.setAttribute('stroke', 'black');
			circle.setAttribute('stroke-width', 1);
			circle.setAttribute('fill-opacity', 1);
	        mysvg.appendChild(circle);
			//循环改颜色
			/*for(var oi=0;oi<list.length;oi++) {
				for(var oj=0;oj<okpoints.length;oj++) {
					for(var ok=0;ok<okpoints[oj].location.length;ok++) {
						// 找和核节点在哪一类里面
						if(list[oi].x==okpoints[oj].location[ok].x && list[oi].y==okpoints[oj].location[ok].y) {
							//非噪声点
							if(okpoints[oj].id != "999") {
								//如果相等  则这一类改颜色
								for(ok=0;ok<okpoints[oj].location.length;ok++) {
									// 找这一类点里面的id,从 allpoints 里找
									for(var ob=0;ob<allpoints.length;ob++) {
										if(allpoints[ob].x==okpoints[oj].location[ok].x && allpoints[ob].y==okpoints[oj].location[ok].y)
											break;
									}
									let oneid = allpoints[ob].id;  // 找到点id
									document.getElementById(id).setAttribute("fill", newcolor);  //改颜色
								}
							} else {
								// 噪声点改颜色
								for(ok=0;ok<okpoints[oj].location.length;ok++) {
									// 找这一类点里面的id,从 allpoints 里找
									for(var ob=0;ob<allpoints.length;ob++) {
										if(allpoints[ob].x==okpoints[oj].location[ok].x && allpoints[ob].y==okpoints[oj].location[ok].y)
											break;
									}
									let oneid = allpoints[ob].id;  // 找到点id
									$("#" + id).empty(); // 清空jquery动态效果
									document.getElementById(id).setAttribute("fill", newcolor);  //改颜色
								}
							}
							break;  //找到之后，跳出这一类，进行下一个
						}
					}
				}
			}*/
		}
		//说明没核
		else {}
	}
}
/*
//找聚类  递归  当前判断的核心节点 当前已聚类的集合
function st(p) {
	List<Point> ppp = new ArrayList<Point>();
	//将该点加入集合中
	all.add(p);
	p.setClassed(true);
	//判断p是否为核心节点，获取集合
	if ((ppp = Method.isKeyPoint(pointsList, p, e, minp, 0)) != null) {
		System.out.println(p.print());
		//遍历p下面的list进行递归
		for (Iterator<Point> it = ppp.iterator(); it.hasNext();) {
			Point qqq = it.next();
			if(qqq.isClassed() == false) {
				all = st(qqq, all);
			}
		}
	}
	return all;
}*/
//将p加入一个聚类

//p周的核心节点变色
function changeAllcolor(point) {
	var list = isKeyPoint(point);
	for(i=0;i<list.length;i++) {
		//令节点所在聚类都变色
	}
}
