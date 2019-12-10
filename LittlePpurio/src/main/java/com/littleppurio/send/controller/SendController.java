package com.littleppurio.send.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.littleppurio.result.model.service.ResultService;
import com.littleppurio.send.model.service.SendService;
import com.littleppurio.send.model.vo.Send;

@Controller
public class SendController {

	@Autowired
	SendService sendService;
	
	@RequestMapping(value = "/send",method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView send(HttpServletRequest req) {
		

		Send send = new Send();
		
		String insertSend=req.getParameter("sendMessage");
		System.out.println(insertSend);
//		Map param = new HashMap();
//		param.put("send_no", send.getSendNo());
//		param.put("sms_content", req.getParameter("SendMessage"));
		
		//int sendSms = sendService.sendSms();
		//mav.addObject("sms", sendSms);
		//mav.setViewName("send");
		
		sendService.insertSend(insertSend);
		int sendNo=sendService.selectSendNo();
		Map insertSms = new HashMap();
		System.out.println(insertSms);
		insertSms.put("receiver", req.getParameter("sendReceiver"));
		insertSms.put("send_no", sendNo);
		System.out.println(insertSms);
		
		sendService.insertSms(insertSms);
		
		
		
		ModelAndView mav= new ModelAndView("send");
		
		return mav;
	}
}
