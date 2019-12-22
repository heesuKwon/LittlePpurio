package com.littleppurio.common;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

@Component
public class SendingTask {
//    @Autowired
    SendThread sendThread;

    @Async("threadPoolTaskExecutor")
    public Future<String> sendingAsync(int sendNo) {
    	sendThread = new SendThread(sendNo);
        sendThread.start();
        
        return new AsyncResult<String>("Success");
    }
}
