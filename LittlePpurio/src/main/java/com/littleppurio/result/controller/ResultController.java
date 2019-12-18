package com.littleppurio.result.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.littleppurio.client.Client;
import com.littleppurio.client.SMSSender;
import com.littleppurio.result.model.service.ResultService;

@Controller
public class ResultController {

	@Autowired
	ResultService resultService;
	
	@GetMapping("/result")
	public ModelAndView result(ModelAndView mav) {
		int smsCnt = resultService.selectSmsCnt();
		float price= (float) (smsCnt*(15.45));
		mav.addObject("smsCnt", smsCnt);
		mav.addObject("price",price);
		mav.setViewName("result");
		return mav;
	}
	
	@RequestMapping("/sendBtn")
	public ModelAndView sendBtn(ModelAndView mav) {
		System.out.println("sendBtn메소드");
		SMSSender smsSender = new SMSSender();
		String phone = "01012341234";
		String callBack = "01062531573";
		String massage = "안녕하세요.";
		int smsNo = 3;
		smsSender.send(phone, callBack, massage, smsNo);
		String result = smsSender.receiveReport();
		System.out.println(result);
		System.out.println("sendBtn메소드 종료");
		
		mav.setViewName("result");
		return mav;
	}
	
	@RequestMapping("/removeReport")
	public ModelAndView removeReport(ModelAndView mav) {
		System.out.println("removeReport메소드");
		SMSSender smsSender = new SMSSender();
		String result = smsSender.receiveReport();
//		System.out.println(result);
		System.out.println("removeReport메소드 종료");
		
		mav.setViewName("result");
		return mav;
	}
}
