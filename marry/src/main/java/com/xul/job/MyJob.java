package com.xul.job;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyJob {
	
//	private 
	
	// 任务实施方法
	public void work() {

		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ":这是第一个任务实施！");

	}
}
