package com.littleppurio.send.model.dao;


import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	public int compUpdate(int param) {
		return sqlSession.update("send.compUpdate", param);
	}
	
}
