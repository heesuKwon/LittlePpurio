package com.littleppurio.send.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.littleppurio.send.model.service.SendService;

@Controller
public class SendController {

	@Autowired
	SendService sendService;
}
