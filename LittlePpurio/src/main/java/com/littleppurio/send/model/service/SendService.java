package com.littleppurio.send.model.service;

import java.util.Map;

import com.littleppurio.send.model.vo.SMS;

public interface SendService {
	
	int insertSend(Map param);
	int selectSendNo();
	int insertSms(Map param);
	int selectSmsNo();
	int ingUpdate(int param);
	int compUpdate(int param);
	SMS waitChecker();
	
}
