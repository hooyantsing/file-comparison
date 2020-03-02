package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Transfer extends Thread {

	private static int directory = 0;
	private static int file = 0;

	
	public static void clone(String sourse,String target) {
		File sourseFile = new File(sourse);		
		String newDirectory = target + "\\" + sourseFile.getName();
		if(sourseFile.isDirectory()) {			
			new File(newDirectory).mkdir();
			directory++;
			File[] fileList = sourseFile.listFiles();
			for(File file : fileList) {
				clone(file.getAbsolutePath(),newDirectory);
			}			
		}
		if(sourseFile.isFile()) {
			moveFile(sourse,newDirectory);
			file++;
		}

	}
	
	public static void moveFile(String sourse,String target) {
		FileInputStream fin = null;
		FileOutputStream fout = null;
		try {
			File s = new File(sourse);
			File t = new File(target);
			
			fin = new FileInputStream(s);
			fout = new FileOutputStream(t);
 
			byte[] a = new byte[1024];	
			int b = -1;
			
			while((b = fin.read(a))!=-1) {
				fout.write(a,0,b);
			}	
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
			if(fin!=null) fin.close();
			if(fout!=null) fout.close();
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
	}

}
