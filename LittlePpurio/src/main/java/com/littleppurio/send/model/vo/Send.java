package com.littleppurio.send.model.vo;

import java.util.Date;

import lombok.Data;

@Data
public class Send {
	private int sendNo;
	private String smsContent;
	private Date smsDate;
	//private String receiver;
	
	public int getSendNo() {
		return sendNo+1;
	}
	
	public void setSendNo(int sendNo) {
		this.sendNo=sendNo;
	}
	
	public String getSmsContent() {
		return smsContent;
	}
	
	public void setSmsContent(String smsContent) {
		this.smsContent=smsContent;
	}
	
	public Date getSmsDate() {
		return smsDate;
	}
	
	public void setSmsDate(Date smsDate) {
		this.smsDate=smsDate;
	}
	
//	public String getReceiver() {
//		return receiver;
//	}
//	
//	public void setReceiver(String receiver) {
//		this.receiver=receiver;
//	}
	
}



