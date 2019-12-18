package com.littleppurio.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.littleppurio.Scheduler;
import com.littleppurio.common.SHA256Util;

public class SMSSender {
	
	public static Socket client;
	public static Socket report;
	public static OutputStream sender;
	public static InputStream receiver;
	public static OutputStream sendReport;
	public static InputStream receReport;
	
	static Scheduler  scheduler = new Scheduler();
	
	public static void createSocket() {
		client = new Socket();
		
		//클라이언트 초기화(연결대상 지정)
		InetSocketAddress ipep = new InetSocketAddress("123.2.134.81", 15001);
		try{
			//접속		
			client.connect(ipep);
			//send,reciever 스트림 받아오기
			//자동 close
			sender= client.getOutputStream();
			receiver = client.getInputStream();	
			String encodePwd = SHA256Util.getEncodePassword("daou12!!");
			String authInfo = "VERSION:=4.0\nUSERID:=daou_intern1\nPASSWD:="+encodePwd+"\nCV:=JD0001\n";
			packet("AU",authInfo, sender, receiver);
			packet("ST","", sender, receiver);
			scheduler.startScheduler();
					
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void createReport() {
		report = new Socket();
		
		//클라이언트 초기화(연결대상 지정)
		InetSocketAddress ipep = new InetSocketAddress("123.2.134.81", 15100);
		try{
			//접속		
			report.connect(ipep);
			//send,reciever 스트림 받아오기
			//자동 close
			sendReport= report.getOutputStream();
			receReport = report.getInputStream();	
			String encodePwd = SHA256Util.getEncodePassword("daou12!!");
			String authInfo = "VERSION:=4.0\nUSERID:=daou_intern1\nPASSWD:="+encodePwd+"\nCV:=JD0001\n";
			packet("AU",authInfo, sendReport, receReport);
			packet("ST","", sendReport, receReport);
			//removePacket();

			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeSocket() {
		try{				
			packet("EN","", sender, receiver);	
			client.close();
			report.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeReport() {
		try{				
			report.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	public void send2(String phone, String callBack, String message) {
//		//자동 close
//		try(Socket client = new Socket()){
//			//클라이언트 초기화(연결대상 지정)
//			InetSocketAddress ipep = new InetSocketAddress("123.2.134.81", 15001);
//			//접속
//			client.connect(ipep);
//			//send,reciever 스트림 받아오기
//			//자동 close
////			try(OutputStream sender = client.getOutputStream();
////					InputStream receiver = client.getInputStream();){				
//				String encodePwd = SHA256Util.getEncodePassword("daou12!!");
//				String authInfo = "VERSION:=4.0\nUSERID:=daou_intern1\nPASSWD:="+encodePwd+"\nCV:=JD0001\n";
//				packet("AU",authInfo);
//				packet("ST","");
//				//packet("PI","",sender,receiver);
//				
//				//문자 전송
//				String data = "VERSION:=4.0\nDEVICE:=sms\n"
//							+"CMSGID:=1\nPHONE:="+phone+"\nSENDER_NAME:=\n"
//							+"TO_NAME:=\nSUBJECT:=\nCOVER_FLAG:=\nUNIXTIME:=\n"
//							+"CALLBACK:="+callBack+"\nTEMPLATE_FILE:=\nFAX_FILE:=\n"
//							+"MSG:=<<__START__\n" + message+"__END__>>\nWAP_URL:=\n" 
//							+"RETRYCNT:=\nSMS_FLAG:=\nREPLY_FLAG:=\nUSERDATA:=\n"
//							+"EXT_DATA:=\n";
//				packet("DS",data);
//				
//				packet("EN","");				
////			}
//		}catch(Throwable e){
//			e.printStackTrace();
//		}
//	}
	
	public void ping() {
		try {
			packet("PI","",sender,receiver);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String send(String phone, String callBack, String message, int smsNo) {
		//packet("PI","");		
		
		//문자 전송
		
		scheduler.stopScheduler();
		
		String data = "VERSION:=4.0\nDEVICE:=sms\n"
					+"CMSGID:="+smsNo+"\nPHONE:="+phone+"\nSENDER_NAME:=\n"
					+"TO_NAME:=\nSUBJECT:=\nCOVER_FLAG:=\nUNIXTIME:=\n"
					+"CALLBACK:="+callBack+"\nTEMPLATE_FILE:=\nFAX_FILE:=\n"
					+"MSG:=<<__START__\n" + message+"__END__>>\nWAP_URL:=\n" 
					+"RETRYCNT:=\nSMS_FLAG:=\nREPLY_FLAG:=\nUSERDATA:=\n"
					+"EXT_DATA:=\n";
		String result = null;
		
		try {
			result= packet("DS",data,sender,receiver);
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			scheduler.startScheduler();
		}
		
		return result;
	}
	

	public String receiveReport() {
		String result = "";
		
		try{
			result = reportPacket();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String packet(String divCode, String message, OutputStream sender, InputStream receiver) throws IOException {
		
		//서버로 데이터 보내기
		StringBuffer data = new StringBuffer();
		
		//data의 기본 구조 : Length(8bytes) + 구분코드(2byte) + massage
		
		//length(8bytes) = 2+message.getBytes().length;
		int len = 2+message.length();
		data.append(String.format("%08d", len));
		
		//구분코드(2bytes)
		data.append(divCode);
		
		//message
		data.append(message);
		
		//byte 변환		
		System.out.println(String.format("sender data - %s", data));
		sender.write(data.toString().getBytes("euc-kr"), 0, data.length());
		
		//서버로부터 데이터 받기
		//11byte
		byte[] result = new byte[40];
		receiver.read(result,0,40);
		
		//수신메시지 출력
		message = new String(result);
//		if(message.charAt(8)=='N')
//		{
//			client.close();
//		}
		String out = String.format("recieve - %s", message);
		System.out.println(out);
		
		return message;
	}
	
	
	public static String reportPacket() throws IOException {
		String resultMsg = "";
		
		breakOut : 
		while(true) {
			//서버로부터 데이터 받기
			//11byte
			byte[] result = new byte[180];
			receReport.read(result,0,180);
			
			//수신메시지 출력
			resultMsg = new String(result);
			String out = String.format("report recieve - %s", resultMsg);
			System.out.println(out);	
			
			String divCode = resultMsg.substring(8,10);
//			System.out.println("divCode : "+divCode);
			
			byte[] responseOk = "00000002OK".getBytes("euc-kr");
			byte[] responseNo = "00000002NO".getBytes("euc-kr");
			String message = new String(responseOk);
			//서버로 응답 보내기
			if(!message.equals("")) {
			switch(divCode) {
				case "PI" : sendReport.write(responseOk, 0, responseOk.length); 
							out = String.format("report send - %s", message);
							System.out.println(out);
							break;
				case "RE" : sendReport.write(responseOk, 0, responseOk.length); 
							out = String.format("report send - %s", message);
							System.out.println(out);
							break breakOut;
	//			default : sendReport.write(responseNo, 0, responseNo.length); break breakOut;
				}
			}
		}
		
		return resultMsg;
	}

}
