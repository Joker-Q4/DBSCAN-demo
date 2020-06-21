package cn.edu.sau.dbscan;

import java.io.*;

public class AppFile {
	
	private static String fname = "D:/point.txt";
	
	//追加
	public static boolean writeFile(String newStr) throws IOException {
		//写入操作
		boolean flag = false;
		String filein = newStr;
		FileOutputStream fos = null;
		PrintWriter pw = null;
		BufferedReader in = null;
		String line ="";
		StringBuilder allmsg = new StringBuilder();
		try {
			// 文件路径
			File file = new File(fname);
			StringBuffer buf = new StringBuffer();
			
			in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf8"));
			while((line = in.readLine())!=null){
				allmsg.append(line);
				allmsg.append("\n");
				//allmsg += line;
				//allmsg += "\n";
			}
			buf.append(allmsg);
			buf.append(filein);
			fos = new FileOutputStream(file);
			pw = new PrintWriter(fos);
			pw.write(buf.toString().toCharArray());
			pw.flush();
			flag = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				in.close();
			}
			if (pw != null) {
				pw.close();
			}
			if (fos != null) {
				fos.close();
			}
		}
		return flag;
	}
	
	//覆盖
	public static boolean writeAFile(String newStr) throws IOException {
		//写入操作
		boolean flag = false;
		String filein = newStr;
		FileOutputStream fos = null;
		PrintWriter pw = null;
		StringBuilder allmsg = new StringBuilder();
		try {
			// 文件路径
			File file = new File(fname);
			StringBuffer buf = new StringBuffer();
			buf.append(allmsg);
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
