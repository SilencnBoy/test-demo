package com.xl.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * TODO 为了实现服务器启动即执行某些操作，只需要实现spring boot中的CommandLineRunner接口即可
 * 
 * @author xl
 */
@Component
@Order(value = 2) // 设置启动执行顺序
public class MyCommandRunner2 implements CommandLineRunner {

	/**
	 * TODO 系统启动即会执行Run方法
	 */
	public void run(String... arg0) throws Exception {

		System.out.println("执行启动任务2...");

	}

}
