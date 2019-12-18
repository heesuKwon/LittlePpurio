package com.littleppurio.send.model.vo;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("sms")
@Data 
public class SMS {
	private int smsNo;
	private String smsContent;
	private String receiver;
	private String sender;	
	
}



