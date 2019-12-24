package com.littleppurio.send.model.service;

import java.util.Map;

import com.littleppurio.send.model.vo.Message;

public interface SendService {
	
	int insertSend(Map param);
	int selectSendNo();
	int insertSms(Map param);
	int selectSmsNo();
	int ingUpdate(int param);
	int compUpdate_send(int param);
	Message waitChecker();
	int codeUpdate(Map param);
	Message pickData(int param);
	int msgIdUpdate(Map param);
	int msgIdChecker(String param);
	int compUpdate_report(String param);
	
}
