package com.littleppurio.result.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.littleppurio.result.model.service.ResultService;

@Controller
public class ResultController {

	@Autowired
	ResultService resultService;
	
	@RequestMapping("/result")
	public ModelAndView result(ModelAndView mav, HttpServletRequest req) {
//		int smsCnt = resultService.selectSmsCnt();
//		float price= (float) (smsCnt*(15.45));
		int sendNoFk = 0;
		
		if(req.getParameter("sendNo")!="")
		{
			sendNoFk = Integer.parseInt(req.getParameter("sendNo"));
		}
		
		
		double m_total = resultService.selectM_total();
		double m_suc = resultService.selectM_suc();
		
		m_suc=(m_suc/m_total)*100;
		m_suc=(Math.round(m_suc*100))/100.0;
		
		double m_fail= 100- m_suc;
		
		int w_4410=resultService.selectW_4410();
		int w_4413=resultService.selectW_4413();
		int w_4420=resultService.selectW_4420();
		int w_4421=resultService.selectW_4421();
		
		int result_t=resultService.selectResult_t(sendNoFk);
		int result_s=resultService.selectResult_s(sendNoFk);
		int result_f=resultService.selectResult_f(sendNoFk);
		int result_i=resultService.selectResult_i(sendNoFk);
		
		mav.addObject("result_t",result_t);
		mav.addObject("result_s",result_s);
		mav.addObject("result_f",result_f);
		mav.addObject("result_i",result_i);
		mav.addObject("success",m_suc);
		mav.addObject("fail",m_fail);
		mav.addObject("w_4410", w_4410);
		mav.addObject("w_4413", w_4413);
		mav.addObject("w_4420", w_4420);
		mav.addObject("w_4421", w_4421);
		
//		mav.addObject("smsCnt", smsCnt);
//		mav.addObject("price",price);
		mav.setViewName("result");
		return mav;
	}
	
//	@RequestMapping("/sendBtn")
//	public ModelAndView sendBtn(ModelAndView mav) {
//		System.out.println("sendBtn메소드");
//		SMSSender smsSender = new SMSSender();
//		String phone = "01012341234";
//		String callBack = "01062531573";
//		String massage = "안녕하세요.";
//		int smsNo = 3;
//		smsSender.send(phone, callBack, massage, smsNo);
//		String result = smsSender.receiveReport();
//		System.out.println(result);
//		System.out.println("sendBtn메소드 종료");
//		
//		mav.setViewName("result");
//		return mav;
//	}
//	
//	@RequestMapping("/removeReport")
//	public ModelAndView removeReport(ModelAndView mav) {
//		System.out.println("removeReport메소드");
//		SMSSender smsSender = new SMSSender();
//		String result = smsSender.receiveReport();
////		System.out.println(result);
//		System.out.println("removeReport메소드 종료");
//		
//		mav.setViewName("result");
//		return mav;
//	}
	
}
