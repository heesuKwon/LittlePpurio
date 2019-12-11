package com.littleppurio.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.littleppurio.common.SHA256Util;

public class SMSSender {
	
	public void send() {
		//자동 close
		try(Socket client = new Socket()){
			//클라이언트 초기화(연결대상 지정)
			InetSocketAddress ipep = new InetSocketAddress("123.2.134.81", 15001);
			//접속
			client.connect(ipep);
			//send,reciever 스트림 받아오기
			//자동 close
			try(OutputStream sender = client.getOutputStream();
					InputStream receiver = client.getInputStream();){				
				String encodePwd = SHA256Util.getEncodePassword("daou12!!");
				String authInfo = "VERSION:=4.0\nUSERID:=daou_intern1\nPASSWD:="+encodePwd+"\nCV:=JD0001\n";
				packet("AU",authInfo,sender,receiver);
				packet("ST","",sender,receiver);
				packet("PI","",sender,receiver);
				packet("EN","",sender,receiver);				
			}
		}catch(Throwable e){
			e.printStackTrace();
		}
	}
	
	public void packet(String divCode, String message, OutputStream sender, InputStream receiver) throws IOException {
		
		//서버로 데이터 보내기
		StringBuffer data = new StringBuffer();
		
		//data의 기본 구조 : Length(8bytes) + 구분코드(2byte) + massage
		
		//length(8bytes) = 2+message.getBytes().length;
		int len = 2+message.length();
		data.append(String.format("%08d", len));
		System.out.println("Length 넣은 뒤 data: "+data);
		
		//구분코드(2bytes) : AU인 경우
		data.append(divCode);
		System.out.println("구분코드 넣은 뒤 data: "+data);
		
		//message
		data.append(message);
		System.out.println("message 넣은 뒤 data: "+data);
		
		//byte 변환				
		sender.write(data.toString().getBytes(), 0, data.length());
		
		//서버로부터 데이터 받기
		//11byte
		byte[] result = new byte[15];
		receiver.read(result,0,15);
		
		//수신메시지 출력
		message = new String(result);
		String out = String.format("recieve - %s", message);
		System.out.println(out);	
		
	}

}