package com.littleppurio.common;

import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Component;

import com.littleppurio.client.SendThread;

@Component
public class Scheduler {
	
//	private ThreadPoolTaskScheduler scheduler;
//	
//	public void stopScheduler() {
//		scheduler.shutdown();
//	}
//	
//	public void startScheduler() {
//		scheduler = new ThreadPoolTaskScheduler();
//		scheduler.initialize();
//		
//		scheduler.schedule(getRunnable(),getTrigger());
//	}
//	
//	public Runnable getRunnable() {
//		return ;
//	}
//	
//	private Trigger getTrigger() {
//		return new PeriodicTrigger(19,TimeUnit.SECONDS);
//	}

}
