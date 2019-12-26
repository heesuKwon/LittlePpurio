package com.littleppurio.client;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.littleppurio.send.model.service.SendService;
import com.littleppurio.send.model.vo.Message;

@Component
public class SendThread extends Thread{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	protected Logger errorLogger = LoggerFactory.getLogger("error");

	Client client = new Client();
	
	private boolean stopped = false;
	//19초 sleep을 제거하기 위한 시간 간격
	private LocalDateTime beforeTime; 
	private LocalDateTime nowTime;
	
	@Autowired
	SendService sendService;
	
	
	public SendThread() {
		client.connectSocket();
		logger.info("===========thread 생성=============");
	}
	
	@Override
	public void run() {
		logger.info("===========thread run=============");
//		ping();
	}
	
	public void ping() {
		try {
			while(!stopped && !Thread.currentThread().isInterrupted()) {
				logger.info("===========ping()=============");
				client.ping();
				Thread.sleep(19000);//19초간 멈춤
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String sendMsg(int sendNo) {

		logger.info(sendNo+" 전송 실행");
		Map<String, Object> updateCode = new HashMap<>();
		Map<String, Object> updateMsgid = new HashMap<>();
		
		while(true) {
			Message sms = sendService.pickData(sendNo);
			
			if(sms==null) {
				break;
			}
			
			String result=client.send(sms.getReceiver (), sms.getSender(),
					sms.getMsgContent(), sms.getMsgNo());
			if(result.charAt(0)=='O')
			{				
				int sub=result.indexOf("OK");
				String msgId=result.substring(sub+2).trim();
				
				updateMsgid.put("msg_id",msgId);
				updateMsgid.put("msg_no", sms.getMsgNo());
				try {
					sendService.msgIdUpdate(updateMsgid);
				}catch(Exception e) {
					handle(e);
					System.out.println("msgId 업데이트에 실패하였습니다.");

				}
				try {
					sendService.ingUpdate(sms.getMsgNo());
				}catch(Exception e) {
					handle(e);
					System.out.println("ing 업데이트에 실패하였습니다.");
				}
			}
			else if(result.charAt(0)=='N') {
				int sub=result.indexOf("NO");
				result=result.substring(sub+2);
				updateCode.put("result_cd", result);
				updateCode.put("msg_no", sms.getMsgNo());
				try {
					sendService.codeUpdate(updateCode);
				}catch(Exception e) {
					handle(e);
					System.out.println("No응답에 대한 result code 업데이트에 실패하였습니다.");
				}
				try {
					sendService.compUpdate_send(sms.getMsgNo());
				}catch(Exception e) {
					handle(e);
					System.out.println("NO응답에 대한 comp 업데이트에 실패하였습니다.");					
				}
			}
			
			beforeTime = LocalDateTime.now();
		}
		
		return sendNo+" 전송 종료";
	}
	
    private void handle(Exception ex) {
        errorLogger.info("Failed to execute task. : {}", ex.getMessage());
        errorLogger.error("Failed to execute task. ",ex);
    }
}
