package com.littleppurio.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.littleppurio.client.Client;
import com.littleppurio.send.model.service.SendService;
import com.littleppurio.send.model.vo.Message;

@Component
public class SendThread extends Thread{
	
	Client client = new Client();
	
	@Autowired
	SendService sendService;
	
	
	public SendThread() {
		client.connectSocket();
		System.out.println("=======thread 생성=======");
	}
	
	@Override
	public void run() {
//		System.out.println("스타트가 되는걸까?");
	}
	
	public String sendMsg(int sendNo) {
		System.out.println(sendNo+" thread 동작");

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
				String msgId_s=result.substring(sub+2).trim();
				
				System.out.println("msgID!!!!!!:"+msgId_s);
				
				updateMsgid.put("msg_id",msgId_s);
				updateMsgid.put("msg_no", sms.getMsgNo());
				
				sendService.msgIdUpdate(updateMsgid);
				sendService.ingUpdate(sms.getMsgNo());
				
			}
			else if(result.charAt(0)=='N') {
				int sub=result.indexOf("NO");
				result=result.substring(sub+2);
				updateCode.put("result_cd", result);
				updateCode.put("msg_no", sms.getMsgNo());
				sendService.codeUpdate(updateCode);
				sendService.compUpdate(sms.getMsgNo());
			}
		}
		
		return sendNo+"thread--------";
	}
}
