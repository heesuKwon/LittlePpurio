package com.littleppurio.send.model.service;

import java.util.Map;

public interface SendService {
	
	int insertSend(String param);
	int selectSendNo();
	int insertSms(Map param);

}
