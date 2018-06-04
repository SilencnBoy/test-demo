package com.xul.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service("TaskJob")
public class TaskJob {
	
	@Scheduled(cron="0/1 * * * * ?")
	public void run(){
		
		System.out.println("running......");
	}
}
