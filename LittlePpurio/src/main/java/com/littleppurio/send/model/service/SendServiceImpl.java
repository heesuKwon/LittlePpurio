package com.littleppurio.send.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.littleppurio.client.SMSSender;
import com.littleppurio.send.model.dao.SendDAO;
import com.littleppurio.send.model.vo.SMS;

@Service
public class SendServiceImpl implements SendService {

	@Autowired
	SendDAO sendDAO;
	
	@Override
	public int insertSend(Map param){
		
		return sendDAO.insertSend(param);
		
	}
	@Override
	public int selectSendNo() {
		return sendDAO.selectSendNo();
	}
	
	@Override
	public int insertSms(Map param){
		return sendDAO.insertSms(param);
	}
	
	@Override
	public int selectSmsNo() {
		return sendDAO.selectSmsNo();
	}
	
	@Override
	public int ingUpdate(int param) {
		return sendDAO.ingUpdate(param);
	}
	
	@Override
	public int compUpdate(int param) {
		return sendDAO.compUpdate(param);
	}
	
	@Override
	public SMS waitChecker() {
		return sendDAO.waitChecker();
	}
	
	@Override
	public int codeUpdate(Map param) {
		return sendDAO.codeUpdate(param);
	}
	
	@Override
	public SMS pickData(int param) {
		return sendDAO.pickData(param);
	}
	
	@Override
	public int msgIdUpdate(Map param) {
		return sendDAO.msgIdUpdate(param);
	}
	
	@Override
	public int compUpdate2(String param) {
		return sendDAO.compUpdate2(param);
	}
	
//	@Override
//	@Async("threadPoolTaskExecutor")
//	public void sending() {
//
//		Map<String, Object> updateCode= new HashMap<>();
//
//		SMSSender smsSender = new SMSSender();
//		
//		System.out.println("쓰레드풀 가동?");

//		SMS sms = this.waitChecker();
//
//		if(sms!=null) {
//			String result_s=smsSender.send(sms.getReceiver(), sms.getSender(),
//					sms.getSmsContent(), sms.getSmsNo());
//			if(result_s.charAt(8)=='O')
//			{
//				this.ingUpdate(sms.getSmsNo());
//
//				int sub=result_s.indexOf("OK");
//				String msgId=result_s.substring(sub+2).trim();
//
//				String result_r = smsSender.receiveReport(msgId);
//				if(result_r.charAt(8)=='R') {
//					sub= result_r.indexOf("RESULT");
//					result_r=result_r.substring(sub+8,sub+12);
//					updateCode.put("result_code", result_r);
//					updateCode.put("sms_no", sms.getSmsNo());
//					this.codeUpdate(updateCode);
//					this.compUpdate(sms.getSmsNo());
//				}
//			}
//			else if(result_s.charAt(8)=='N') {
//				int sub=result_s.indexOf("NO");
//				result_s=result_s.substring(sub+2);
//				updateCode.put("result_code", result_s);
//				updateCode.put("sms_no", sms.getSmsNo());
//				this.codeUpdate(updateCode);
//				this.compUpdate(sms.getSmsNo());
//			}
//		}				
//	}
	
}
