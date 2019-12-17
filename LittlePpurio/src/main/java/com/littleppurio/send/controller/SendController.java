package com.littleppurio.send.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.littleppurio.client.SMSSender;
import com.littleppurio.send.model.service.SendService;

@Controller
public class SendController {

	@Autowired
	SendService sendService;
	
	@RequestMapping(value = "/send",method = {RequestMethod.GET,RequestMethod.POST})
	public String send(HttpServletRequest req) {//throws InterruptedException {
		
		/*SMSSender sender=new SMSSender();
		
		sender.createSocket();
		
		
		Thread[] thread = new Thread[100];
		Runnable[] run = new Runnable[100];

		
		for(int j=0;j<100;j++) {
			run[j]=new Runnable() {
				@Override
				public void run() {
					
			}
		};
		}
		
		long start = System.currentTimeMillis();
		for(int i=0;i<100;i++)
		{
			thread[i]= new Thread(run[i]);
			thread[i].start();
			thread[i].join();
		}
		long end = System.currentTimeMillis();
		System.out.println(end-start);*/

		
		// 메시지 내용 가져오기
		
		Map<String,String> insertSend = new HashMap<>();
		
		insertSend.put("sender", req.getParameter("sender"));
		insertSend.put("sms_content", req.getParameter("sendMessage"));
		System.out.println(insertSend);

		
		sendService.insertSend(insertSend);
		int sendNo=sendService.selectSendNo();
		
		SMSSender smsSender = new SMSSender();
		
		String[] insertNumber;
		
		Map<String, Object> insertSms = new HashMap<>();
		
		// 전송할 전화번호 받아오기
		insertNumber=(req.getParameterValues("phoneList"));

		for(int i=0;i<insertNumber.length;i++)
		{
			insertSms.put("receiver", insertNumber[i]);
			insertSms.put("send_no", sendNo);
			System.out.println(insertSms);
			if(sendService.insertSms(insertSms)==1)
			{
				int smsNo = sendService.selectSmsNo();
				smsSender.send(insertNumber[i], insertSend.get("sender"), insertSend.get("sms_content"), smsNo);
				if(true)
				{
					sendService.ingUpdate(smsNo);
					String result = smsSender.receiveReport();
					if(true) {
						sendService.compUpdate(smsNo);
						
					}//else if(true)리포트서버에 전송실패 했을 때 에러코드를 알 수 없고 (이거는 다시 시도할 수 있나 모르겠네) 그리고 실패면 언제 comp로 전환해야할까
				}//else if(true) 발송서버 전송에 실패했을 때 --> 아직 wait 그리고 다시 전송시도(횟수 정해야함) 및 소켓 열/닫 횟수?
			}
		}
		
		
		return "send";
	}
}
