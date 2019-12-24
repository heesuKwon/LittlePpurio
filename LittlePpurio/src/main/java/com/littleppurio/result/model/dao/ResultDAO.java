package com.littleppurio.result.model.dao;

public interface ResultDAO {

	int selectSmsCnt();
	int selectM_suc();
	int selectM_total();
	int selectW_4410();
	int selectW_4413();
	int selectW_4420();
	int selectW_4421();
	int selectResult_t(int param);
	int selectResult_s(int param);
	int selectResult_f(int param);
	int selectResult_i(int param);

}
