package com.littleppurio.send.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.littleppurio.result.model.service.ResultService;
import com.littleppurio.send.model.service.SendService;
import com.littleppurio.send.model.vo.Send;

@Controller
public class SendController {

	@Autowired
	SendService sendService;
	
	@GetMapping("/send")
	public ModelAndView result(ModelAndView mav) {
		List<Send> sendSms = sendService.sendSms();
		mav.setViewName("send");
		return mav;
	}
}
