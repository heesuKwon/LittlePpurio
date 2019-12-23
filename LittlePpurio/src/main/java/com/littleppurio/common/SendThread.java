package com.littleppurio.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.littleppurio.client.Client;
import com.littleppurio.send.model.service.SendService;
import com.littleppurio.send.model.vo.SMS;

@Component
public class SendThread implements Runnable{
	
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
			SMS sms = sendService.pickData(sendNo);
			
			System.out.println(sms);
			
			if(sms==null) {
				break;
			}
			
			String result=client.send(sms.getReceiver (), sms.getSender(),
					sms.getSmsContent(), sms.getSmsNo());
			if(result.charAt(8)=='O')
			{				
				int sub=result.indexOf("OK");
				String msgId_s=result.substring(sub+2).trim();
				
				updateMsgid.put("msg_id",msgId_s);
				updateMsgid.put("sms_no", sms.getSmsNo());
				
				sendService.msgIdUpdate(updateMsgid);
				sendService.ingUpdate(sms.getSmsNo());
				
			}
			else if(result.charAt(8)=='N') {
				int sub=result.indexOf("NO");
				result=result.substring(sub+2);
				updateCode.put("result_code", result);
				updateCode.put("sms_no", sms.getSmsNo());
				sendService.codeUpdate(updateCode);
				sendService.compUpdate(sms.getSmsNo());
			}
		}
		return sendNo+"thread--------";
	}
}
