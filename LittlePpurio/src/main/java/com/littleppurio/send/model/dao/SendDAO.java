package com.littleppurio.send.model.dao;

import java.util.Map;

public interface SendDAO {
	
	int insertSend(String param);
	int selectSendNo();
	int insertSms(Map param);

}
