package cn.edu.sau.pojo;

public class Point {
	
	private double x;
	private double y;
	private int key = 0;   //为1则为核心点（后端用不到，放在前端）
	private int classed = 0;  //0为噪声点，1为已经被聚类
	private boolean isKey = false;
	private boolean isClassed = false;

	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public int getClassed() {
		return classed;
	}
	public void setClassed(int classed) {
		this.classed = classed;
	}
	public boolean isKey() {
		return isKey;
	}
	public void setKey(boolean isKey) {
		this.isKey = isKey;
		this.key = 1;
		this.isClassed = true;
		this.classed = 1;
	}
	public boolean isClassed() {
		return isClassed;
	}
	public void setClassed(boolean isClassed) {
		this.isClassed = isClassed;
		this.classed = 1;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public Point() {
		x = 0;
		y = 0;
	}
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public Point(String str) {
		String[] p = str.split(",");
		this.x = Double.parseDouble(p[0]);
		this.y = Double.parseDouble(p[1]);
	}
	public String print() {
		return "(" + this.x + "," + this.y + ")";
	}
	public String printString() {
		return "\"x\":" + "\"" + this.x + "\"" + ","
						+ "\"y\":"+ "\"" + this.y + "\"" + ","
						+ "\"key\":"+ "\"" + this.key + "\"" + ","
						+ "\"classed\":"+ "\"" + this.classed + "\"";
	}
}