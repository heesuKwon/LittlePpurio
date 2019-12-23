package com.littleppurio.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.littleppurio.client.Report;
import com.littleppurio.send.model.service.SendService;

@Component
public class ReportThread implements Runnable{
	
	Report report = new Report();
	
	@Autowired
	SendService sendService;
	
	public ReportThread() {
		report.connectSocket();
		System.out.println("Report thread 생성");
	}
	
	@Override
	public void run() {
		while(true) {
			recvReport();
		}
	}
		
	public void recvReport() {
		Map<String, Object> updateCode= new HashMap<>();
		
		String result = report.receiveReport();
		
		int start = result.lastIndexOf("MSGID:=");
		int end = result.indexOf("PHONE:=");
		String msgId = result.substring(start+7, end-1);
		
		if(result.charAt(8)=='R') {
			int sub= result.indexOf("RESULT");
			result=result.substring(sub+8,sub+12);
			updateCode.put("result_code", result);
			updateCode.put("msg_id", msgId);
			sendService.codeUpdate(updateCode);
			sendService.compUpdate2(msgId);
			//sendService.compUpdate(msgId);
		}
	}
}
