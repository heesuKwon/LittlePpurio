package com.littleppurio.send.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.littleppurio.LittlePpurioService;
import com.littleppurio.client.SMSSender;
import com.littleppurio.common.SendingJobControlTask;
import com.littleppurio.send.model.service.SendService;
import com.littleppurio.send.model.vo.SMS;

@Controller
public class SendController {

	@Autowired
	SendService sendService;
	
	@Autowired
	SendingJobControlTask sjct;
	
	@RequestMapping(value = "/send",method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView send(HttpServletRequest req, HttpServletResponse res, ModelAndView mav){

		
//		// 메시지 내용 가져오기		
//		Map<String,String> insertSend = new HashMap<>();
//		boolean sucs = false;
//		
//		insertSend.put("sender", req.getParameter("sender"));
//		insertSend.put("sms_content", req.getParameter("sendMessage"));
//		
//		if(sendService.insertSend(insertSend)==1)
//		{
//			int sendNo=sendService.selectSendNo();
//			
//			// 전송할 전화번호 받아오기
//			String[] insertNumber = (req.getParameterValues("phoneList"));
//			Map<String, Object> insertSms = new HashMap<>();		
//
//
//			for(int i=0;i<insertNumber.length;i++)
//			{
//				insertSms.put("receiver", insertNumber[i]);
//				insertSms.put("send_no", sendNo);
//
//				if(sendService.insertSms(insertSms)==1)
//				{
//					sucs = true;
//				}
//			}
//			
//			sjct.signalQueue.add(sendNo);
//			
//		}
//		
//		
//		mav.setViewName("send");
//		mav.addObject("sucs", sucs);
		
		sjct.startSending();
		
		return mav;
	}



//	public void sendMsg() throws Exception{
//		Map<String, Object> updateCode= new HashMap<>();
//		Map<String, Object> updateMsgid =new HashMap<>();
//		
//		SMSSender smsSender = new SMSSender();
////		sendService.sending();
//		
//		SMS sms = sendService.waitChecker();
////				
//		if(sms!=null) {
//			String result=smsSender.send(sms.getReceiver(), sms.getSender(),
//					sms.getSmsContent(), sms.getSmsNo());
//			if(result.charAt(8)=='O')
//			{				
//				int sub=result.indexOf("OK");
//				String msgId_s=result.substring(sub+2).trim();
//				
//				updateMsgid.put("msg_id",msgId_s);
//				updateMsgid.put("sms_no", sms.getSmsNo());
//				
//				sendService.msgIdUpdate(updateMsgid);
//				sendService.ingUpdate(sms.getSmsNo());
//				
//			}
//			else if(result.charAt(8)=='N') {
//				int sub=result.indexOf("NO");
//				result=result.substring(sub+2);
//				updateCode.put("result_code", result);
//				updateCode.put("sms_no", sms.getSmsNo());
//				sendService.codeUpdate(updateCode);
//				sendService.compUpdate(sms.getSmsNo());
//			}
//		}				
//	}
	
	
	public void recvReport() {
		Map<String, Object> updateCode= new HashMap<>();
		SMSSender smsSender = new SMSSender();
		
		String result = smsSender.receiveReport();
		
		int start = result.lastIndexOf("MSGID:=");
		int end = result.indexOf("PHONE:=");
		String msgId = result.substring(start+7, end-1);
		
		if(result.charAt(8)=='R') {
			int sub= result.indexOf("RESULT");
			result=result.substring(sub+8,sub+12);
			updateCode.put("result_code", result);
			updateCode.put("msg_id", msgId);
			sendService.codeUpdate(updateCode);
			sendService.compUpdate2(msgId);
			//sendService.compUpdate(msgId);
		}
	}
}
