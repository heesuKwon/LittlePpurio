package com.littleppurio.send.model.service;

import java.util.List;
import java.util.Map;

import com.littleppurio.send.model.vo.Send;

public interface SendService {
	
	int insertSend(String param);
	int selectSendNo();
	int insertSms(Map param);

}
