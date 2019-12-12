package com.littleppurio.send.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.littleppurio.send.model.dao.SendDAO;

@Service
public class SendServiceImpl implements SendService {

	@Autowired
	SendDAO sendDAO;
	
	@Override
	public int insertSend(String param){
		
		return sendDAO.insertSend(param);
		
	}
	@Override
	public int selectSendNo() {
		return sendDAO.selectSendNo();
	}
	
	@Override
	public int insertSms(Map param){
		return sendDAO.insertSms(param);
	}
}
