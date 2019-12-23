package com.littleppurio.common;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendingJobControlTask {
    @Autowired
    SendingTask sendingTask;

    public Queue<Integer> signalQueue= new LinkedList<Integer>();
    
    

    public void startSending() {    	
    	
        signalQueue.stream()
            .forEach(sendNo -> {
            	System.out.println("sendNO는!!!:"+sendNo);
                sendingTask.sendingAsync(sendNo);
            });
    }
}