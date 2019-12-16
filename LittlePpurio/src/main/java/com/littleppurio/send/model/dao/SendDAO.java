package com.littleppurio.send.model.dao;

import java.util.Map;

public interface SendDAO {
	
	int insertSend(Map param);
	int selectSendNo();
	int insertSms(Map param);
	int selectSmsNo();
	int ingUpdate(int param);
	int compUpdate(int param);

}
