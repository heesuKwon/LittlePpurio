package com.littleppurio.send.model.vo;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("msg")
@Data 
public class Message {
	private int msgNo;
	private String msgContent;
	private String receiver;
	private String sender;	
	
}



