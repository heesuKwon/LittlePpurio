package com.littleppurio.result.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ResultDAOImpl implements ResultDAO {

	@Autowired
	SqlSessionTemplate sqlSession;

	@Override
	public int selectSmsCnt() {
		return sqlSession.selectOne("result.selectSmsCnt");
	}
	
	@Override
	public int selectM_total() {
		return sqlSession.selectOne("result.selectM_total");
	}
	
	@Override
	public int selectM_suc() {
		return sqlSession.selectOne("result.selectM_suc");
	}
}
