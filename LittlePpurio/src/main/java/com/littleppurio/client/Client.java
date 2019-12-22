package com.littleppurio.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.NoRouteToHostException;
import java.net.Socket;

import com.littleppurio.common.SHA256Util;
import com.littleppurio.common.Scheduler;

public class Client {
	
//	private static Client client = null;
	private Socket socket;
	private OutputStream sender;
	private InputStream receiver;
	private InetSocketAddress ipep;
	
	public Scheduler scheduler = new Scheduler();
	
	//private Client(){
	public Client() {
		//클라이언트 초기화(연결대상 지정)
		ipep = new InetSocketAddress("123.2.134.81", 15001);
	}
	
//	public static Client getInstance() {
//		if(client == null) {
//			client = new Client();
//		}
//		return client;
//	}
	
	public void connectSocket() {
		socket = new Socket();
		
		try {
			
			socket.connect(ipep);
			socket.setSoTimeout(1000);	//1초 타임아웃 설정
			
			//send,reciever 스트림 받아오기
			sender= socket.getOutputStream();
			receiver = socket.getInputStream();	
			
			String encodePwd = SHA256Util.getEncodePassword("daou12!!");
			String authInfo = "VERSION:=4.0\nUSERID:=daou_intern1\nPASSWD:="+encodePwd+"\nCV:=JD0001\n";
			packet("AU",authInfo);
			packet("ST","");			
			scheduler.startScheduler();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void closeSocket() {
		try{				
			packet("EN","");
			scheduler.stopScheduler();
			sender.close();
			receiver.close();
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void ping() {
		try {
			packet("PI","");
		} 
		//1초가 지나서 타임아웃 됐을 때
		catch(NoRouteToHostException e) {
			//소켓 재연결
			closeSocket();
			connectSocket();
		}
		//원격 호스트가 연결을 거부한 경우
		catch (ConnectException e) {
			//3초 후 소켓 연결
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			closeSocket();
			connectSocket();
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String send(String phone, String callBack, String message, int smsNo) {

		scheduler.stopScheduler();

		//문자 전송		
		String data = "VERSION:=4.0\nDEVICE:=sms\n"
				+"CMSGID:="+smsNo+"\nPHONE:="+phone+"\nSENDER_NAME:=\n"
				+"TO_NAME:=\nSUBJECT:=\nCOVER_FLAG:=\nUNIXTIME:=\n"
				+"CALLBACK:="+callBack+"\nTEMPLATE_FILE:=\nFAX_FILE:=\n"
				+"MSG:=<<__START__\n" + message+"__END__>>\nWAP_URL:=\n" 
				+"RETRYCNT:=\nSMS_FLAG:=\nREPLY_FLAG:=\nUSERDATA:=\n"
				+"EXT_DATA:=\n";
		String result = null;

	
		try {
			result = packet("DS",data);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		scheduler.startScheduler();

		return result;
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
		sender.write(data.toString().getBytes("euc-kr"), 0, data.length());
		
		//서버로부터 데이터 받기
		//11byte
		byte[] result = new byte[40];
		receiver.read(result,0,40);

		//수신메시지 출력
		message = new String(result);
		String out = String.format("DATA recieve - %s", message);
		System.out.println(out);	

		return message;
	}

}
