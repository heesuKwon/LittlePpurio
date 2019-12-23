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
	
	@Override
	public int selectW_4410() {
		return resultDAO.selectW_4410();
	}
	
	@Override
	public int selectW_4413() {
		return resultDAO.selectW_4413();
	}
	
	@Override
	public int selectW_4420() {
		return resultDAO.selectW_4420();
	}
	
	@Override
	public int selectW_4421() {
		return resultDAO.selectW_4421();
	}
}
