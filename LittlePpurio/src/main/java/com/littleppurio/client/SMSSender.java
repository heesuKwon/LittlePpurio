package com.littleppurio.client;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;

public class SMSSender {

	//static String propsFilePath = "./smssender.cfg";

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
				
				//서버로 데이터 보내기
				String message = "hello";
				//data의 기본 구조 : Length (8 bytes) + 구분코드 (2 byte) + massage
				byte[] data = new byte[10+message.getBytes().length];
				//length(8bytes) = 2+message.getBytes().length;
				data = ByteBuffer.allocate(8).putInt(2+message.getBytes().length).array();
				//구분코드(2bytes) : AU인 경우
				data[8] = 'A';
				data[9] = 'U';
				byte[] msg = message.getBytes();
				System.arraycopy(msg, 0, data, 10, data.length);
				sender.write(data, 0, data.length);
				
				//서버로부터 데이터 받기
				//11byte
				byte[] result = new byte[10];
				receiver.read(result,0,10);
				//수신메시지 출력
				message = new String(result);
				String out = String.format("recieve - %s", message);
				System.out.println(out);
				
			}
		}catch(Throwable e){
			e.printStackTrace();
		}
	}

}
