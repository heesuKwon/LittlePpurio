package com.littleppurio.send.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.littleppurio.send.model.dao.SendDAO;

@Service
public class SendServiceImpl implements SendService {

	@Autowired
	SendDAO sendDAO;
}
