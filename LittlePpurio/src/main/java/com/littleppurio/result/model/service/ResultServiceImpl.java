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
}
