package com.littleppurio.common;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.littleppurio.client.ReportThread;
import com.littleppurio.client.SendThread;

@Configuration
@EnableAsync
public class SpringAsyncConfig implements AsyncConfigurer{
 
    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected Logger errorLogger = LoggerFactory.getLogger("error");
 
    @Bean(name="executor")
    @Override
    public Executor getAsyncExecutor() {
    	ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(1);
        taskExecutor.setMaxPoolSize(1);
        taskExecutor.setQueueCapacity(10);
        taskExecutor.setThreadNamePrefix("Executor-");
        taskExecutor.initialize();
        taskExecutor.submit(new ReportThread());
//        taskExecutor.submit(new ReportThread());
//        taskExecutor.submit(new ReportThread());
        return new HandlingExecutor(taskExecutor); // HandlingExecutor로 wrappingg함으로써 예외처리
    }

    @Bean(name="threadPoolTaskExecutor")
    @Qualifier
    public Executor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(3);
        taskExecutor.setMaxPoolSize(3);
        taskExecutor.setQueueCapacity(20);
        taskExecutor.setThreadNamePrefix("TaskExecutor-");
        taskExecutor.initialize();
        taskExecutor.submit(new SendThread());
        taskExecutor.submit(new SendThread());
        taskExecutor.submit(new SendThread());
//        taskExecutor.submit(new ReportThread());
        return new HandlingExecutor(taskExecutor); // HandlingExecutor로 wrappingg함으로써 예외처리
    }
 
    public class HandlingExecutor implements AsyncTaskExecutor {
        private AsyncTaskExecutor executor;
 
        public HandlingExecutor(AsyncTaskExecutor executor) {
            this.executor = executor;
        }
 
        @Override
        public void execute(Runnable task) {
            executor.execute(task);
        }
 
        @Override
        public void execute(Runnable task, long startTimeout) {
            executor.execute(createWrappedRunnable(task), startTimeout);
        }
 
        @Override
        public Future<?> submit(Runnable task) {
            return executor.submit(createWrappedRunnable(task));
        }
 
        @Override
        public <T> Future<T> submit(final Callable<T> task) {
            return executor.submit(createCallable(task));
        }
 
        private <T> Callable<T> createCallable(final Callable<T> task) {
            return new Callable<T>() {
                @Override
                public T call() throws Exception {
                    try {
                        return task.call();
                    } catch (Exception ex) {
                        handle(ex);
                        throw ex;
                    }
                }
            };
        }
 
        private Runnable createWrappedRunnable(final Runnable task) {
            return new Runnable() {
                @Override
                public void run() {
                    try {
                        task.run();
                    } catch (Exception ex) {
                        handle(ex);
                    }
                }
            };
        }
 
        private void handle(Exception ex) {
            errorLogger.info("Failed to execute task. : {}", ex.getMessage());
            errorLogger.error("Failed to execute task. ",ex);
        }
 
    }
 
}


