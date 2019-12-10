package com.littleppurio;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LittlePpurioController {
	
	@RequestMapping(value = "/",method = {RequestMethod.GET,RequestMethod.POST})
	public String home() {
		return "send";
	}
	
	
}
