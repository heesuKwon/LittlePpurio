package com.littleppurio.send.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.littleppurio.client.SendingJobControlTask;
import com.littleppurio.send.model.service.SendService;

@Controller
public class SendController {

	@Autowired
	SendService sendService;
	
	@Autowired
	SendingJobControlTask sendQu;
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
    protected Logger errorLogger = LoggerFactory.getLogger("error");
	
	@RequestMapping("/send")
	public ModelAndView send(HttpServletRequest req, HttpServletResponse res, ModelAndView mav){
		
		// 메시지 내용 가져오기		
		Map<String,String> insertSend = new HashMap<>();
		boolean sucs = false;

		insertSend.put("sender", req.getParameter("sender"));
		insertSend.put("msg_content", req.getParameter("sendMessage"));
		
		try {
			
			if(sendService.insertSend(insertSend)==1)
			{
				int sendNo=sendService.selectSendNo();
				
				// 전송할 전화번호 받아오기
				String[] insertNumber = (req.getParameterValues("phoneList"));
				Map<String, Object> insertSms = new HashMap<>();		


				for(int i=0;i<insertNumber.length;i++)
				{
					insertSms.put("receiver", insertNumber[i]);
					insertSms.put("send_no_fk", sendNo);

					if(sendService.insertSms(insertSms)==1)
					{
						sucs = true;
					}
				}
				sendQu.signalQueue.add(sendNo);
				sendQu.startSending();
				mav.addObject("sendNo",sendNo);
			}
			
		}catch(Exception e){
			handle(e);
			System.out.println("발송에 실패하였습니다.");
		}
		
		mav.setViewName("send");
		mav.addObject("sucs", sucs);
		
		
		return mav;
	}
	
    private void handle(Exception ex) {
        errorLogger.info("Failed to execute task. : {}", ex.getMessage());
        errorLogger.error("Failed to execute task. ",ex);
    }
}
