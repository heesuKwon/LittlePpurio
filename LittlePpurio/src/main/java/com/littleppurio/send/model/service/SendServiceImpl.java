package com.littleppurio.send.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.littleppurio.send.model.dao.SendDAO;
import com.littleppurio.send.model.vo.Message;

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
	public int compUpdate_send(int param) {
		return sendDAO.compUpdate_send(param);
	}
	
	@Override
	public Message waitChecker() {
		return sendDAO.waitChecker();
	}
	
	@Override
	public int codeUpdate(Map param) {
		return sendDAO.codeUpdate(param);
	}
	
	@Override
	public Message pickData(int param) {
		return sendDAO.pickData(param);
	}
	
	@Override
	public int msgIdUpdate(Map param) {
		return sendDAO.msgIdUpdate(param);
	}
	
	@Override
	public int compUpdate_report(String param) {
		return sendDAO.compUpdate_report(param);
	}
	
	@Override
	public int msgIdChecker(String param) {
		return sendDAO.msgIdChecker(param);
	}
	
}
