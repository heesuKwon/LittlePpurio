package com.littleppurio;

import java.util.LinkedList;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.catalina.connector.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.littleppurio.client.Client;
import com.littleppurio.client.Report;
import com.littleppurio.common.AsyncConfig;
import com.littleppurio.send.controller.SendController;
import com.littleppurio.send.model.service.SendService;

@Service
public class LittlePpurioService implements CommandLineRunner, ApplicationListener<ContextClosedEvent>{
    
	private static final Logger log = LoggerFactory.getLogger(LittlePpurioService.class);
	private static final long TIMEOUT = 300_000L;	//5초
	private volatile Connector connector;
	Client client = Client.getInstance(/* "123.2.134.81", 15001 */);
	Report report = Report.getInstance();

	@Autowired
	SendController sendController;
    
	@Autowired
	SendService sendService;
	
//	int count=0;
//	long start=0;

	
//    @Scheduled(cron="*/5 * * * * *")
//    public void dataChecker(){    	
//    	
////    	if(count==0)
////    	{
////           	start = System.currentTimeMillis(); //시작하는 시점 계산
////    	}
////    	 
////    	/*
////    	실행시간을 측정하고싶은 코드
////    	*/
//    	sendController.sendMsg();
////    	count++;
////    	
////    	if(count==100)
////    	{
////        	long end = System.currentTimeMillis(); //프로그램이 끝나는 시점 계산
////        	System.out.println( "실행 시간 : " + ( end - start )/1000.0 +"초");
////    	}
//    	
//    }
    


    
    
    @Override
    public void run(String... args) throws Exception {
    	//애플리케이션 생성시 한번만 실행
    	client.connectSocket();
    	report.connectSocket();
    	//SendController sendController = new SendController();
    	
    	//sendController.sendMsg();
 
    }
    
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
    	//애플리케이션 종료시 한번만 실행
    	client.closeSocket();
    	report.closeSocket();
        
        //애플리케이션이 graceful하게 종료하기 위한 코드
        this.connector.pause();
        Executor executor = this.connector.getProtocolHandler().getExecutor();
        if (executor instanceof ThreadPoolExecutor) {
            try {
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
                threadPoolExecutor.shutdown();
                if (!threadPoolExecutor.awaitTermination(TIMEOUT, TimeUnit.SECONDS)) {
                    log.warn("Tomcat thread pool did not shut down gracefully within "
                            + TIMEOUT + " seconds. Proceeding with forceful shutdown");

                    threadPoolExecutor.shutdownNow();

                    if (!threadPoolExecutor.awaitTermination(TIMEOUT, TimeUnit.SECONDS)) {
                        log.error("Tomcat thread pool did not terminate");
                    }
                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
