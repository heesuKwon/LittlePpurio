package com.littleppurio;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.catalina.connector.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Service;

import com.littleppurio.client.SMSSender;

@Service
public class LittlePpurioService implements CommandLineRunner, ApplicationListener<ContextClosedEvent>{
    
	private static final Logger log = LoggerFactory.getLogger(LittlePpurioService.class);
	private static final long TIMEOUT = 300_000L;	//5초
	private volatile Connector connector;
	
    @Override
    public void run(String... args) throws Exception {
    	//애플리케이션 생성시 한번만 실행
        SMSSender.createSocket();
        SMSSender.createReport();
    }
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
    	//애플리케이션 종료시 한번만 실행
        SMSSender.closeSocket();
        SMSSender.closeReport();
        
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
