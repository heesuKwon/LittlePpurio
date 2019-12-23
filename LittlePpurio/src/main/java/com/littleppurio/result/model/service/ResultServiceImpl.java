package com.littleppurio.result.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.littleppurio.result.model.dao.ResultDAO;

@Service
public class ResultServiceImpl implements ResultService{

	@Autowired
	ResultDAO resultDAO;

	@Override
	public int selectSmsCnt() {
		return resultDAO.selectSmsCnt();
	}
	
	@Override
	public int selectM_total() {
		return resultDAO.selectM_total();
	}
	
	@Override
	public int selectM_suc() {
		return resultDAO.selectM_suc();
	}
}
