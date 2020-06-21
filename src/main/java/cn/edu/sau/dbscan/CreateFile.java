package cn.edu.sau.dbscan;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class CreateFile {
 
	private static String path = "D:/";
	private static String fname;
 
	public static void main(String[] args) {
		String s = "";
		Random rand = new Random();
		// 默认生成300个点
		for(int i=0; i<299; i++) {
			s += rand.nextInt(10000)*1.0/10;
			s += ",";
			s += rand.nextInt(5000)*1.0/10;
			s += "\n";
		}
		s += rand.nextInt(10000)*1.0/10;
		s += ",";
		s += rand.nextInt(5000)*1.0/10;
		
		try {
			creatFile("point");
			CreateFile.writeFile(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean autoCreate(int points) {
		boolean result = true;
		String s = "";
		Random rand = new Random();
		for(int i=0; i<points-1; i++) {
			s += rand.nextInt(10000)*1.0/10;
			s += ",";
			s += rand.nextInt(5000)*1.0/10;
			s += "\n";
		}
		s += rand.nextInt(10000)*1.0/10;
		s += ",";
		s += rand.nextInt(5000)*1.0/10;
		
		try {
			CreateFile.creatFile("point");
			CreateFile.writeFile(s);
		} catch (IOException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
	/**
	 * 创建文件
	 * @throws IOException
	 */
	public static boolean creatFile(String name) {
		boolean flag = false;
		fname = path + name + ".txt";
		File filename = new File(fname);
		if (!filename.exists()) {
			try {
				filename.createNewFile();
				flag = true;
			} catch (IOException e) {
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}
 
	/**
	 * 写文件
	 * @param newStr 新内容
	 * @throws IOException
	 */
	public static boolean writeFile(String newStr) throws IOException {
		//写入操作
		boolean flag = false;
		String filein = newStr;
		FileOutputStream fos = null;
		PrintWriter pw = null;
		try {
			// 文件路径
			File file = new File(fname);
			StringBuffer buf = new StringBuffer();
			buf.append(filein);
			fos = new FileOutputStream(file);
			pw = new PrintWriter(fos);
			pw.write(buf.toString().toCharArray());
			pw.flush();
			flag = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
			if (fos != null) {
				fos.close();
			}
		}
		return flag;
	}
 
}