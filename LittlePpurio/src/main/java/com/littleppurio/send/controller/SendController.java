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

import com.littleppurio.client.SMSSender;
import com.littleppurio.send.model.service.SendService;
import com.littleppurio.send.model.vo.SMS;

@Controller
public class SendController {

	@Autowired
	SendService sendService;
	
	
	@RequestMapping(value = "/send",method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView send(HttpServletRequest req, HttpServletResponse res, ModelAndView mav) throws IOException, ServletException {//throws InterruptedException {
	
		// 메시지 내용 가져오기
		
		Map<String,String> insertSend = new HashMap<>();
		
		insertSend.put("sender", req.getParameter("sender"));
		insertSend.put("sms_content", req.getParameter("sendMessage"));
		//System.out.println(insertSend);
		insertSend.get("sender");
		insertSend.get("sms_content");

		sendService.insertSend(insertSend);
		int sendNo=sendService.selectSendNo();
			
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
				sucs = true;
			}
		}
		
		mav.setViewName("send");
		mav.addObject("sucs", sucs);
		
		return mav;
	}

	
	public void sendMsg() {
		
		Map<String, Object> updateCode= new HashMap<>();
		
		SMSSender smsSender = new SMSSender();
		//System.out.println("===sendMsg 메소드 시작===");
		
		SMS sms = sendService.waitChecker();
				
		if(sms!=null) {
			System.out.println(sms.toString());
			System.out.println("scheduler 발동!");
			String result_s=smsSender.send(sms.getReceiver(), sms.getSender(), sms.getSmsContent(), sms.getSmsNo());
			if(result_s.charAt(8)=='O')
			{
				sendService.ingUpdate(sms.getSmsNo());
				
				int sub=result_s.indexOf("OK");
				String msgId=result_s.substring(sub+2).trim();
				
				String result_r = smsSender.receiveReport(msgId);
				if(result_r.charAt(8)=='R') {
					sub= result_r.indexOf("RESULT");
					result_r=result_r.substring(sub+8,sub+12);
					updateCode.put("result_code", result_r);
					updateCode.put("sms_no", sms.getSmsNo());
					sendService.codeUpdate(updateCode);
					sendService.compUpdate(sms.getSmsNo());
				}
			}
			else if(result_s.charAt(8)=='N') {
				int sub=result_s.indexOf("NO");
				result_s=result_s.substring(sub+2);
				updateCode.put("result_code", result_s);
				updateCode.put("sms_no", sms.getSmsNo());
				sendService.codeUpdate(updateCode);
				sendService.compUpdate(sms.getSmsNo());
			}
		}
		else {
//			System.out.println("보낼 메세지가 없습니다.");
		}
		
		//System.out.println("===sendMsg 메소드 종료===");
		
	}
}
