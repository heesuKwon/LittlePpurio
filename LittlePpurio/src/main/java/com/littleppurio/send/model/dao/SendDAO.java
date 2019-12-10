package com.littleppurio.send.model.dao;

import java.util.List;
import java.util.Map;

import com.littleppurio.send.model.vo.Send;

public interface SendDAO {
	
	int insertSend(String param);
	int selectSendNo();
	int insertSms(Map param);

}
