package com.littleppurio.send.model.service;

import java.util.Map;

import org.springframework.scheduling.annotation.Async;

import com.littleppurio.send.model.vo.SMS;

public interface SendService {
	
	int insertSend(Map param);
	int selectSendNo();
	int insertSms(Map param);
	int selectSmsNo();
	int ingUpdate(int param);
	int compUpdate(int param);
	SMS waitChecker();
	int codeUpdate(Map param);
	SMS pickData(int param);
//	void sending()throws Exception;
	int msgIdUpdate(Map param);
	int compUpdate2(String param);
	
}
