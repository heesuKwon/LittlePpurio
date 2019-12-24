package com.littleppurio.client;

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
        signalQueue.stream()
            .forEach(sendNo -> {
                sendingTask.sendingAsync(sendNo);
            });
    }
}