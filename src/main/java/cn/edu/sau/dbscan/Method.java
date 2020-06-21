package cn.edu.sau.dbscan;

import cn.edu.sau.pojo.Point;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Method {
	
	/**
	 * 测试两个点之间的距离 
	 * @param p 点
	 * @param q 点
	 * @return 返回两个点之间的距离
	 */
	public static double getDistance(Point p, Point q) {
		double dx = p.getX() - q.getX();
		double dy = p.getY() - q.getY();
		double distance = Math.sqrt(dx * dx + dy * dy);
		return distance;
	}

	/**
	 * 检查给定点是不是核心点
	 * @param lst 存放点的链表
	 * @param p 待测试的点
	 * @param e e半径
	 * @param minp 密度阈值 
	 * @return 暂时存放访问过的点
	 */
	public static List<Point> isKeyPoint(List<Point> lst, Point p, double e, int minp , int flag) {
		int count = 1;
		List<Point> list = new ArrayList<Point>();
		for (Iterator<Point> it = lst.iterator(); it.hasNext();) {
			Point q = it.next();
			if (getDistance(p, q) <= e) {
				//判断不重复点
				if(!(p.getX() == q.getX() && p.getY() == q.getY())) {
					++count;
					if (!list.contains(q)) {
						list.add(q);
					}
				}
			}
		}
		if (count >= minp) {
			if(flag == 1)
				return list;
			p.setKey(true);
		/*	if (!list.contains(p)) {
				list.add(p);
			}*/
			return list;
		}
		return null;
	}

	/**
	 * 为点标记已被聚类
	 * @param lst
	 */
	public static void setListClassed(List<Point> lst) {
		for (Iterator<Point> it = lst.iterator(); it.hasNext();) {
			Point p = it.next();
			if (!p.isClassed()) {
				p.setClassed(true);
			}
		}
	}

	/**
	 * 如果b中含有a中包含的元素，则把两个集合合并
	 * @param a
	 * @param b
	 * @return a
	 */
	public static boolean mergeList(List<Point> a, List<Point> b) {
		boolean merge = false;
		for (int index = 0; index < b.size(); ++index) {
			if (a.contains(b.get(index))) {
				merge = true;
				break;
			}
		}
		if (merge) {
			for (int index = 0; index < b.size(); ++index) {
				if (!a.contains(b.get(index))) {
					a.add(b.get(index));
				}
			}
		}
		return merge;
	}
	
	/**
	 * 返回文本中的点集合
	 * @return 返回文本中点的集合
	 * @throws IOException
	 */
	public static List<Point> getPointsList() throws IOException {
		List<Point> lst = new ArrayList<Point>();
		String txtPath = "D:\\Point.txt";
		BufferedReader br = new BufferedReader(new FileReader(txtPath));
		String str = "";
		while ((str = br.readLine()) != null && str != "" && !str.equals("")) {
			lst.add(new Point(str));
		}
		br.close();
		return lst;
	}
	
	/**
	 * 对已分类信息进行排序（噪点没必要）
	 * @param list
	 * @return
	 */
	public static List<List<Point>> Sorting(List<List<Point>> ls){
		List<List<Point>> allList = new ArrayList<List<Point>>();
		for(int k=0;k<ls.size();k++) {
			List<Point> list = ls.get(k);
			double a,a1,a2,b,b1,b2;
			for(int i=0;i<list.size()-1;i++){
				for(int j=0;j<list.size()-1-i;j++){
					a=list.get(j).getX();
					b=list.get(j).getY();
					a1=list.get(j+1).getX();
					b1=list.get(j+1).getY();
					if(a>a1) {
						a2=a1;  //交换横坐标
						list.get(j+1).setX(a);
						list.get(j).setX(a2);
						b2=b1;  //交换纵坐标
						list.get(j+1).setY(b);
						list.get(j).setY(b2);
					} else if(a==a1) {
						if(b>b1) {
							a2=a1;  //交换横坐标
							list.get(j+1).setX(a);
							list.get(j).setX(a2);
							b2=b1;  //交换纵坐标
							list.get(j+1).setY(b);
							list.get(j).setY(b2);
						}
					}
				}
			}
			allList.add(list);
		}
		return allList;
	//	return list;
	}
	
}