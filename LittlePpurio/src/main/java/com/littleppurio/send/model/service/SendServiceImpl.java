package com.littleppurio.send.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.littleppurio.send.model.dao.SendDAO;
import com.littleppurio.send.model.vo.Send;

@Service
public class SendServiceImpl implements SendService {

	@Autowired
	SendDAO sendDAO;
	
	@Override
	public List<Send> sendSms(){
		
		return sendDAO.sendSms();
		
	}
}
