package com.littleppurio.client;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.littleppurio.send.model.service.SendService;

@Component
@Scope("prototype")
public class ReportThread implements Runnable{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	Report report = new Report();
	
	@Autowired
	SendService sendService;
	
	public ReportThread() {
		report.connectSocket();
		logger.info("=========== Report thread 생성=============");
	}
	
	@Override
	public void run() {
		logger.info("=========== Report thread run=============");
		while(/*!Thread.currentThread().isInterrupted()*/true) {
			recvReport();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
		
	public void recvReport() {
		logger.info("========== Report ===========");
		Map<String, Object> updateCode= new HashMap<>();
		
		String result = report.receiveReport();
		
		int start = result.lastIndexOf("MSGID:=");
		int end = result.indexOf("PHONE:=");
		String msgId = result.substring(start+7, end-1);
		
		if(result.charAt(0)=='R') {
			int sub= result.indexOf("RESULT");
			result=result.substring(sub+8,sub+12);
			System.out.println("result="+result);
			int isMsgId = sendService.msgIdChecker(msgId);
			System.out.println("isMsgId="+isMsgId);
			if(isMsgId==1)
			{
				System.out.println("isMsgId="+isMsgId);
				updateCode.put("result_code", result);
				updateCode.put("msg_id", msgId);
				sendService.codeUpdate(updateCode);
				sendService.compUpdate_report(msgId);
			}
			System.out.println("다 되는건가?");
		}			
	}
}
