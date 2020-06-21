package cn.edu.sau.dbscan;

import cn.edu.sau.pojo.Point;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DBScan {

	private static List<Point> pointsList = new ArrayList<Point>(); //存储所有点的集合
	private static List<List<Point>> resultList = new ArrayList<List<Point>>(); //存储DBSCAN算法返回的结果集
	private static List<Point> rList = new ArrayList<Point>(); //存储噪声点
	private static double e = 50; //默认e半径
	private static int minp = 3; //默认密度阈值
	
	/**
	 * 设置需要的变量
	 * @param r
	 * @param minPoints
	 */
	public static void setNum(double r, int minPoints) {
		e = r;
		minp = minPoints;
	}

	/**
	 * 提取文本中的的所有点并存储在pointsList中
	 * @throws IOException
	 */
	public static String display() {
		//getEndResult();
		firstDbscan(); //找到所有直达的聚类
		noisePoints(); //找出噪声点
		String s = "";
		String ss = "[";
		int index = 1;
		// int index1 = 1;
		int flag1=0,flag2=0,flag3=0;
		for (Iterator<List<Point>> it = resultList.iterator(); it.hasNext();) {
			List<Point> lst = it.next();
			if (lst.isEmpty()) {
				continue;
			}
			// s+=String.valueOf(index);
			if(flag1 == 0) {
				ss += "{\"id\":" + "\"" + index + "\"" + ",";
				flag1++;
			}
			else
				ss += ",{\"id\":" + "\"" + index + "\"" + ",";
			flag2=0;
			System.out.println("====第" + index + "个聚类====");
			s = "\"location\":[";
			for (Iterator<Point> it1 = lst.iterator(); it1.hasNext();) {
				Point p = it1.next();
				if(flag2 == 0) {
					s = s + "{" + p.printString() + "}";
					flag2++;
				}
				else
					s = s + ",{" + p.printString() + "}";
				//ss = ss + "{" + "id:" + index + "," + s + "}";
				System.out.println(p.print());
			}
			s += "]}";
			ss = ss + s;
			index++;
		}
	//	return ss + "]";
		
		
		if(flag1 == 0) {
			ss += "{\"id\":" + "\"" + 999 + "\"" + ",";
			flag1++;
		}
		else
			ss += ",{\"id\":" + "\"" + 999 + "\"" + ",";
		int ii = 0;
		s = "\"location\":[";
		for (Iterator<Point> it = rList.iterator(); it.hasNext();ii++) {
			Point lt = it.next();
			if (lt.isClassed()) {
				continue;
			}
			if(flag3 == 0) {
				System.out.println("====以下是噪声点====");
				s = s + "{" + lt.printString() + "}";
				flag3++;
			}
			else
				s = s + ",{" + lt.printString() + "}";
			
			System.out.println(lt.print());
		}
		s += "]}";
		ss = ss + s;
		if(flag3 != 0)
			System.out.println("====共" + ii + "个====");
		return ss + "]";
	}
	
	// 遍历所有的点
	public static String allPoints() {
		String s = "[";
		int flag = 0;
		List<Point> allpoints = new ArrayList<Point>();
		try {
			allpoints = Method.getPointsList();
			for (Iterator<Point> one = allpoints.iterator(); one.hasNext();) {
				Point p = one.next();
				if(flag == 0) {
					s = s + "{" + p.printString() + "}";
					flag++;
				}
				else
					s = s + ",{" + p.printString() + "}";
			}
			s = s + "]";
		} catch (IOException e) {
			s = "[]";
			e.printStackTrace();
		}
		return s;
	}
	
	
	//找聚类  递归  当前判断的核心节点 当前已聚类的集合
	private static List<Point> st(Point p, List<Point> all) {
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
	}
	
	//聚类合并
	/*public static List<Point> merList(List<Point> a, List<Point> b) {
		if(b != null)
			for (int index = 0; index < b.size(); ++index) {
				if (!a.contains(b.get(index))) {
					a.add(b.get(index));
				}
			}
		return a;
	}*/

	//找出所有可以直达的聚类
	private static void firstDbscan() {
		resultList.clear();
		rList.clear();  //使用之前清空,否则二次聚类会重复
		try {
			pointsList = Method.getPointsList();
			// 这里要加flag
			int flag = 1;
			while(flag == 1) {
				flag = 0;
				for (Iterator<Point> it = pointsList.iterator(); it.hasNext();) {
					Point p = it.next();
					if (Method.isKeyPoint(pointsList, p, e, minp , 1) != null) {
						if(!p.isClassed()) {
							List<Point> test = new ArrayList<Point>();
							resultList.add(st(p,test));
							flag = 1;
						}
					}
					/*
						List<Point> tmpLst = new ArrayList<Point>();
						if ((tmpLst = Method.isKeyPoint(pointsList, p, e, minp)) != null) {
							//为所有聚类完毕的点做标示
							Method.setListClassed(tmpLst);
							resultList.add(tmpLst);
						}*/
				//	test1 = st(p,test);
				//	resultList.add(test1);
				/*	for(Iterator<Point> zs = test1.iterator(); zs.hasNext();) {
						Point pwee = zs.next();
						System.out.println(pwee.print());
					}*/
						
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//查找噪声点
	private static void noisePoints() {
		for (Iterator<Point> zs = pointsList.iterator(); zs.hasNext();) {
			Point p = zs.next();
			if (!p.isClassed()) {
				rList.add(p);
			}
		}
	}

	//对所有可以直达的聚类进行合并,即找出相连的点并进行合并
	/*private static List<List<Point>> getEndResult() {
		int flag = 1;
		firstDbscan(); //找到所有直达的聚类
		noisePoints(); //找出噪声点
		int length = resultList.size();
		while(flag != 0) {
			flag = 0;
			for (int i = 0; i < length; ++i) {
				for (int j = i + 1; j < length; ++j) {
					if (Method.mergeList(resultList.get(i), resultList.get(j))) {
						flag++;
					//	resultList.get(j).clear();
						resultList.remove(j--);
						length--;
					}
				}
			}
		}
	//	return Method.Sorting(resultList);
		return resultList;
	}*/

	/**
	 * 程序主函数
	 * @param args
	 */
	public static void main(String[] args) {
		// getResult();
		display();
	}
}
