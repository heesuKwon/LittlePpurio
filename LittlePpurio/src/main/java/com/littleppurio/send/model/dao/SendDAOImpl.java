package com.littleppurio.send.model.dao;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.littleppurio.send.model.vo.Send;

@Repository
public class SendDAOImpl implements SendDAO {

	@Autowired
	SqlSessionTemplate sqlSession;
	
	@Override
	public List<Send> sendSms() {
		return sqlSession.selectOne("result.selectSmsCnt");
	}
	
}
