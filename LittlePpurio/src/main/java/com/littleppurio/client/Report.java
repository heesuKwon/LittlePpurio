package com.littleppurio.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.littleppurio.common.SHA256Util;

public class Report {
	private static Report report = null;
	private Socket socket;
	public static OutputStream sendReport;
	public static InputStream receReport;
	private InetSocketAddress ipep;
	
	private Report() {
		socket = new Socket();
		
		//리포트 초기화(연결대상 지정)
		ipep = new InetSocketAddress("123.2.134.81", 15100);
	}
	
	public static Report getInstance() {
		if(report == null) {
			report = new Report();
		}
		return report;
	}
	
	public void connectSocket() {
		try {
			socket.connect(ipep);
			
			//send,reciever 스트림 받아오기
			sendReport= socket.getOutputStream();
			receReport = socket.getInputStream();	
			
			String encodePwd = SHA256Util.getEncodePassword("daou12!!");
			String authInfo = "VERSION:=4.0\nUSERID:=daou_intern1\nPASSWD:="+encodePwd+"\nCV:=JD0001\n";
			packet("AU",authInfo);
			packet("ST","");			
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void closeSocket() {
		try{				
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String packet(String divCode, String message) throws IOException {
		//서버로 데이터 보내기
		StringBuffer data = new StringBuffer();

		//data의 기본 구조 : Length(8bytes) + 구분코드(2byte) + massage
		//length(8bytes)
		int len = 2+message.length();
		data.append(String.format("%08d", len));

		//구분코드(2bytes)
		data.append(divCode);

		//message
		data.append(message);

		//byte 변환		
		System.out.println(String.format("DATA send - %s", data));
		sendReport.write(data.toString().getBytes("euc-kr"), 0, data.length());
		
		//서버로부터 데이터 받기
		//11byte
		byte[] result = new byte[40];
		receReport.read(result,0,40);

		//수신메시지 출력
		message = new String(result);
		String out = String.format("DATA recieve - %s", message);
		System.out.println(out);	

		return message;
	}
	
	
	public String reportPacket() throws IOException{
		String message = "";

		breakOut : 
			while(true) {
				//서버로부터 데이터 받기
				//11byte
				byte[] result = new byte[180];
				receReport.read(result,0,180);

				//수신메시지 출력
				message = new String(result);
				String out = String.format("report recieve - %s", message);
				System.out.println(out);	

				String divCode = message.substring(8,10);

				byte[] responseOk = "00000002OK".getBytes("euc-kr");
				String response = new String(responseOk);

				//서버로 응답 보내기
				if(message.equals("")) {
					break;
				}
				else {
					switch(divCode) {
					case "PI" : sendReport.write(responseOk, 0, responseOk.length); 
					out = String.format("report send - %s", response);
					System.out.println(out);
					break;
					case "RE" : sendReport.write(responseOk, 0, responseOk.length); 
					out = String.format("report send - %s", response);
					System.out.println(out);
					break breakOut;		
					}
				}
			}

		return message;
	}
}
