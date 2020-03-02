package server;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileComparison {
	
	//获取path目录下一层的所有文件
	public static File[] getChildFiles(String path) {
		File file = new File(path);
		return file.listFiles();
	}
	
	//文件差集
	public static List<File> difference(File[] sourceChildFiles,File[] targetChildFiles){
		String fileName = "";
		boolean flag = false;
		List<File> fileList = new ArrayList<File>();
		for(File source : sourceChildFiles) {
			flag = false;
			fileName = source.getName();
			for(File target : targetChildFiles) {
				if(fileName.equals(target.getName())) {
					flag = true;
					break;
				}
			}
			if(!flag) {
				fileList.add(source);
			}
		}
		return fileList;
	}

}
