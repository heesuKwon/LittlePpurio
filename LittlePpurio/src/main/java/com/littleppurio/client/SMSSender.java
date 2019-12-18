package com.littleppurio.client;

import java.io.IOException;
import java.net.ConnectException;
import java.net.NoRouteToHostException;

public class SMSSender {

	Client client = Client.getInstance();
	Report report = Report.getInstance();

	public void ping() {
		boolean flag = true;
		int cnt=0;
//		while(flag){
			try {
				client.packet("PI","");
				flag = false;
				//타임아웃 테스트용 
//				if(!flag&&cnt==0) {
//					throw new NoRouteToHostException();
//				}
			} 
			//2초가 지나서 타임아웃 됐을 때
			catch(NoRouteToHostException e) {
				//소켓 재연결
				client.closeSocket();
				client.connectSocket();
				flag = true;
				cnt++;
			}
			//원격 호스트가 연결을 거부한 경우
			catch (ConnectException e) {
				//시간이 조금 지난 후 소켓 연결
			}
			catch (IOException e) {
				e.printStackTrace();
			}
//		}
	}

	public String send(String phone, String callBack, String message, int smsNo) {

		client.scheduler.stopScheduler();

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
			result = client.packet("DS",data);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		client.scheduler.startScheduler();

		return result;
	}
		
	public String receiveReport() {
		String result = "";

		try{
			result = report.reportPacket();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

}
