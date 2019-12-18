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
	public int insertSend(Map param){
		
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
	
	@Override
	public int selectSmsNo() {
		return sendDAO.selectSmsNo();
	}
	
	@Override
	public int ingUpdate(int param) {
		return sendDAO.ingUpdate(param);
	}
	
	@Override
	public int compUpdate(int param) {
		return sendDAO.compUpdate(param);
	}
	
	@Override
	public int waitChecker() {
		return sendDAO.waitChecker();
	}
	
}
