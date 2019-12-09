package com.littleppurio.send.model.vo;

import java.util.Date;

import lombok.Data;

@Data
public class Send {
	private int sendNo;
	private String smsContent;
	private Date smsDate;
	private String receiver;
}
