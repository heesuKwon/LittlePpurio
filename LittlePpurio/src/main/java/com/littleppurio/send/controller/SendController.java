package com.littleppurio.send.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.littleppurio.send.model.service.SendService;

@Controller
public class SendController {

	@Autowired
	SendService sendService;
	
	@RequestMapping(value = "/send",method = {RequestMethod.GET,RequestMethod.POST})
	public String send(HttpServletRequest req) {
				
		// 메시지 내용 가져오기
		String insertSend=req.getParameter("sendMessage");
		System.out.println(insertSend);

		
		sendService.insertSend(insertSend);
		int sendNo=sendService.selectSendNo();
		
		
		String[] insertNumber;
		
		Map<String, Object> insertSms = new HashMap<>();
		
		// 전송할 전화번호 받아오기
		insertNumber=(req.getParameterValues("phoneList"));

		for(int i=0;i<insertNumber.length;i++)
		{
			insertSms.put("receiver", insertNumber[i]);
			insertSms.put("send_no", sendNo);
			System.out.println(insertSms);
			sendService.insertSms(insertSms);
			//sendSerivce.selectSmsNo();
		}
		
		
		return "send";
	}
}
