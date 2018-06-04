package com.xl.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
  * TODO MyJobConfig任务调度器
  * 
  * @author xl
  * @date 2017年3月3日 下午8:40:59
  */
@Configuration // 声明类为系统配置类
@EnableScheduling // 开启调度任务
public class MyJobConfig {
	
	@Scheduled(cron = "0 0/1 * * * ?") // 定义调度器
	public void job1() {

		System.out.println("任务开启!"+new SimpleDateFormat("yyyy年MM月dd日 HH:ss:mm").format(new Date()));

	}
}
