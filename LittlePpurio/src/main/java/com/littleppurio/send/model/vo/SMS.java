package com.littleppurio.send.model.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Alias("sms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SMS {
	private int smsNo;
	private String smsContent;
	private String receiver;
	private String sender;	
}



