package com.littleppurio.common;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendingJobControlTask {
    @Autowired
    SendingTask sendingTask;

	public Queue<Integer> signalQueue = new LinkedList<>();

    public void startSending() {    	
    	signalQueue.add(1);
    	signalQueue.add(2);
    	signalQueue.add(3);
    	signalQueue.add(4);
    	signalQueue.add(5);
    	signalQueue.add(6);
    	signalQueue.add(7);
    	signalQueue.add(8);
    	signalQueue.add(9);
    	signalQueue.add(10);
        signalQueue.stream()
            .forEach(sendNo -> {
            	System.out.println("queue에는 들어갔냐!!!!:"+sendNo);
                sendingTask.sendingAsync(sendNo);
            });
    }
}