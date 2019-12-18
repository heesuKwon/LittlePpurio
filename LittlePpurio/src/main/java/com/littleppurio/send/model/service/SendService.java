package com.littleppurio.send.model.service;

import java.util.Map;

public interface SendService {
	
	int insertSend(Map param);
	int selectSendNo();
	int insertSms(Map param);
	int selectSmsNo();
	int ingUpdate(int param);
	int compUpdate(int param);
	int waitChecker();
	
}
