package com.littleppurio.common;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Util {

	/**
	 * 사용자 비밀번호를 Sha256 알고리즘을 이용하여 해쉬(단방향 암호화)처리함.
	 * @param password
	 * @return
	 */
	public static String getEncodePassword(String password) {
		String encPwd = null;
		//1.암호화
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		//바이트배열로 변환
		byte[] bytes = password.getBytes(Charset.forName("utf-8"));
		//md객체 업데이트
		md.update(bytes);
		//해싱처리
		byte[] encryptedBytes = md.digest();
		
		StringBuffer sb = new StringBuffer();
        for (int i = 0; i < encryptedBytes.length; i++) {
            sb.append(Integer.toString((encryptedBytes[i] & 0xFF) + 256, 16).substring(1));
        }
         
        encPwd = sb.toString();
        
		return encPwd;
	}
}
