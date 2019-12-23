package com.littleppurio.common;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

@Component
public class SendingTask {
    @Autowired
    SendThread sendThread;
    
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Async("threadPoolTaskExecutor")
    public Future<String> sendingAsync(int sendNo) {
    	logger.info(sendThread.sendMsg(sendNo));
         
        return new AsyncResult<String>("Success");
    }
}
