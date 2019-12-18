package com.littleppurio.send.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.littleppurio.client.SMSSender2;
import com.littleppurio.send.model.service.SendService;

@Controller
public class SendController {

	@Autowired
	SendService sendService;
	
	
	@RequestMapping(value = "/send",method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView send(HttpServletRequest req, HttpServletResponse res, ModelAndView mav) throws IOException, ServletException {//throws InterruptedException {

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
		String sendnum=insertSend.get("sender");
		String content=insertSend.get("sms_content");

		
		sendService.insertSend(insertSend);
		int sendNo=sendService.selectSendNo();
		
		SMSSender2 smsSender = new SMSSender2();
		
		String[] insertNumber;
		
		Map<String, Object> insertSms = new HashMap<>();
		
		boolean sucs = false;
		
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
							
				String result_s=smsSender.send(insertNumber[i], sendnum, content, smsNo);
				
				if(result_s.charAt(8)=='O')
				{
					sendService.ingUpdate(smsNo);
					
					sucs = true;
					
					String result_r = smsSender.receiveReport();
					if(result_r.charAt(8)=='R') {
						sendService.compUpdate(smsNo);
						
					}
				}
				else {
					sendService.compUpdate(smsNo);
				}
			}
			else {
			}
		}
		
		mav.setViewName("send");
		mav.addObject("sucs", sucs);
		
		return mav;
	}
}
