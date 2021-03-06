package com.littleppurio.send.model.dao;


import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.littleppurio.send.model.vo.Message;

@Repository
public class SendDAOImpl implements SendDAO {

	@Autowired
	SqlSessionTemplate sqlSession;
	
	@Override
	public int insertSend(Map param) {
		return sqlSession.insert("send.insertSend",param);
	}
	
	@Override
	public int selectSendNo() {
		return sqlSession.selectOne("send.selectSendNo");
	}
	
	@Override
	public int insertSms(Map param) {
		return sqlSession.insert("send.insertSms",param);
	}
	
	@Override
	public int selectSmsNo() {
		return sqlSession.selectOne("send.selectSmsNo");
	}
	
	@Override
	public int ingUpdate(int param) {
		return sqlSession.update("send.ingUpdate", param);
	}
	
	@Override
	public int compUpdate_send(int param) {
		return sqlSession.update("send.compUpdate_send", param);
	}
	
	@Override
	public Message waitChecker() {
		return sqlSession.selectOne("send.waitChecker");
	}
	
	@Override
	public int codeUpdate(Map param) {
		return sqlSession.update("send.codeReport", param);
	}
	
	@Override
	public Message pickData(int param) {
		return sqlSession.selectOne("send.pickData", param);
	}
	
	@Override
	public 	int msgIdUpdate(Map param) {
		return sqlSession.update("send.msgIdUpdate", param);
	}
	
	@Override
	public int compUpdate_report(String param) {
		return sqlSession.update("send.compUpdate_report",param);
	}
	
	@Override
	public int msgIdChecker(String param) {
		return sqlSession.selectOne("send.msgIdChecker",param);
	}
	
}
