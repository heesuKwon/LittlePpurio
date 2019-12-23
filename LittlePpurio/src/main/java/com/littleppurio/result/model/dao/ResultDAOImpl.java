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
		return sqlSession.selectOne("result.selectmessage_tbCnt");
	}
	
	@Override
	public int selectM_total() {
		return sqlSession.selectOne("result.selectM_total");
	}
	
	@Override
	public int selectM_suc() {
		return sqlSession.selectOne("result.selectM_suc");
	}
	
	@Override
	public int selectW_4410() {
		return sqlSession.selectOne("result.selectW_4410");
	}
	
	@Override
	public int selectW_4413() {
		return sqlSession.selectOne("result.selectW_4413");
	}
	
	@Override
	public int selectW_4420() {
		return sqlSession.selectOne("result.selectW_4420");
	}
	
	@Override
	public int selectW_4421() {
		return sqlSession.selectOne("result.selectW_4421");
	}
}
