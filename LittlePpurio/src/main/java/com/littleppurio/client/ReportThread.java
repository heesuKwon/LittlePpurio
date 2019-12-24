package com.littleppurio.client;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.littleppurio.send.model.service.SendService;

@Component
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
		while(true) {
			recvReport();
			try {
				Thread.sleep(5000);//5초 일시정지
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
		
	public void recvReport() {
		Map<String, Object> updateCode= new HashMap<>();
		
		String result = report.receiveReport();
		
		int start = result.lastIndexOf("MSGID:=");
		int end = result.indexOf("PHONE:=");
		String msgId = result.substring(start+7, end-1);
		
		if(result.charAt(0)=='R') {
			int sub= result.indexOf("RESULT");
			result=result.substring(sub+8,sub+12);
			updateCode.put("result_code", result);
			updateCode.put("msg_id", msgId);
//			if(sendService.selectMsgId()==1) {
				sendService.codeUpdate(updateCode);
				sendService.compUpdate2(msgId);
//			}
		}
	}
}
