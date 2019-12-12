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
	public int insertSend(String param) {
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
	
}
