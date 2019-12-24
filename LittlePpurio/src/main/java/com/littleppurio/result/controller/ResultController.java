package com.littleppurio.result.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.littleppurio.result.model.service.ResultService;

@Controller
public class ResultController {

	@Autowired
	ResultService resultService;
	
	@GetMapping("/result")
	public ModelAndView result(ModelAndView mav) {
		int smsCnt = resultService.selectSmsCnt();
		float price= (float) (smsCnt*(15.45));
		
		double m_total = resultService.selectM_total();
		double m_suc = resultService.selectM_suc();
		
		m_suc=(m_suc/m_total)*100;
		m_suc=(Math.round(m_suc*100))/100.0;
		
		double m_fail= 100- m_suc;
		
		int w_4410=resultService.selectW_4410();
		int w_4413=resultService.selectW_4413();
		int w_4420=resultService.selectW_4420();
		int w_4421=resultService.selectW_4421();
		
		
		mav.addObject("success",m_suc);
		mav.addObject("fail",m_fail);
		mav.addObject("w_4410", w_4410);
		mav.addObject("w_4413", w_4413);
		mav.addObject("w_4420", w_4420);
		mav.addObject("w_4421", w_4421);
		
		mav.addObject("smsCnt", smsCnt);
		mav.addObject("price",price);
		mav.setViewName("result");
		return mav;
	}
	
}
