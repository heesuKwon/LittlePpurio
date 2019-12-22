package com.littleppurio.send.model.dao;

import java.util.Map;

import com.littleppurio.send.model.vo.SMS;

public interface SendDAO {
	
	int insertSend(Map param);
	int selectSendNo();
	int insertSms(Map param);
	int selectSmsNo();
	int ingUpdate(int param);
	int compUpdate(int param);
	SMS waitChecker();
	int codeUpdate(Map param);
	SMS pickData(int param);

}
