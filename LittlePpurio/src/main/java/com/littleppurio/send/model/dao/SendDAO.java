package com.littleppurio.send.model.dao;

import java.util.Map;

import com.littleppurio.send.model.vo.Message;

public interface SendDAO {
	
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
	int compUpdate_report(String param);
	int msgIdChecker(String param);

}
