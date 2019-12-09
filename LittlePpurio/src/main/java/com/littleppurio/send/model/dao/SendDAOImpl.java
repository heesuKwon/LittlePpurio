package com.littleppurio.send.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SendDAOImpl implements SendDAO {

	@Autowired
	SqlSessionTemplate sqlSession;
	
}
