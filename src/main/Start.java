package main;

import window.MainWin;

public class Start {
	
	/*
	 * <h3>本程序正式入口</h3>
	 * <p>在开发阶段，其他文件中的main作为临时调试。发行阶段将以此为正式入口，其余main删除。 </p>
	 * 
	 */
	public static void main(String[] args) {
		try {
			MainWin window = new MainWin();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
