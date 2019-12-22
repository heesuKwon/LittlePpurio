package com.littleppurio.common;

import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendingJobControlTask {
    @Autowired
    SendingTask sendingTask;

    public Queue<Integer> signalQueue;

    public void startSending() {
        signalQueue.stream()
            .forEach(sendNo -> {
                sendingTask.sendingAsync(sendNo);
            });
    }
}