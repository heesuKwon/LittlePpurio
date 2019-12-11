package com.littleppurio.result.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.littleppurio.client.SMSSender;
import com.littleppurio.result.model.service.ResultService;

@Controller
public class ResultController {

	@Autowired
	ResultService resultService;
	
	@GetMapping("/result")
	public ModelAndView result(ModelAndView mav) {
		int smsCnt = resultService.selectSmsCnt();
		mav.addObject("smsCnt", smsCnt);
		mav.setViewName("result");
		return mav;
	}
	
	@RequestMapping("/sendBtn")
	public ModelAndView sendBtn(ModelAndView mav) {
		System.out.println("sendBtn메소드");
		SMSSender smsSender = new SMSSender();
		smsSender.send();
		System.out.println("sendBtn메소드 종료");
		
		mav.setViewName("result");
		return mav;
	}
}
