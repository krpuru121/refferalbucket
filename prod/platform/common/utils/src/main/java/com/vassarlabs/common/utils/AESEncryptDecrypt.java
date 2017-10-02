package com.vassarlabs.common.utils;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;


public class AESEncryptDecrypt {
	static Cipher cipher;
	static String mykey;
	static SecretKey secretKey;
	
	static {
		mykey ="Thisismykey12345";
		secretKey = new SecretKeySpec(mykey.getBytes(), "AES"); //keyGenerator.generateKey();	
		    
	    try {
			cipher = Cipher.getInstance("AES/ECB/NoPadding");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}	
	}
	
	/**
	 * 
	 * @param plainText
	 * @return
	 * @throws Exception
	 */
	
	public static String encrypt(String plainText)
			throws Exception {
		byte[] plainTextByte = plainText.getBytes();
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] encryptedByte = cipher.doFinal(plainTextByte);
		String hex = DatatypeConverter.printHexBinary(encryptedByte);		
		return new String(hex);
	}
	
	
	/**
	 * First decoding The base64 String
	 * After than applying aes decryption
	 * @param encryptedText
	 * @return
	 * @throws Exception
	 */
	
	public static String decrypt(String encryptedText)
			throws Exception {
		
		//byte[] base64decodedBytes = Base64.getDecoder().decode(encryptedText);	
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decryptedByte = cipher.doFinal(DatatypeConverter.parseHexBinary(encryptedText));
		String decryptedText = new String(decryptedByte);
		return decryptedText;
	}
	
	
//	public void simulate(){
//		String uri = "http://localhost:999/iwmsolution?";
//		int sid;
//		double tag0,tag1,tag2;
//		
//		sid = 12345;
//		
//		String start = "LEN=0082pid-OSSB;sid-"+sid+";dt-20160113132600;:mid-1;";
//		
//		
//		tag0 = Math.random()*100;
//		tag1 = Math.random()*100;
//		tag2 = Math.random()*100;
//		
//		String tags = "TAG0-"+tag0+";"+"TAG1-"+tag1+";TAG2-"+tag2+";|";
//        
//		String message = start+tags;
//		
//        int len = message.length();
//		
//		String repeated = new String(new char[16-len%16]).replace("\0", "#");
//		
//		message = message+repeated;
//		
//		try {
//			System.out.println(AESEncryptDecrypt.encrypt(message));
//			
//			message = AESEncryptDecrypt.encrypt(message);
//			
//			String data = "dat="+message;
//			
//			String httpreq = uri+data;
//			
//			System.out.println(httpreq);
//			
//			byte[] encString = Base64.getEncoder().encode(message.getBytes());
//			
//					
//			URL url = new URL(httpreq);
//			  HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
//			  httpCon.setDoOutput(true);
//			  httpCon.setRequestMethod("POST");
//			  OutputStreamWriter out = new OutputStreamWriter(
//			      httpCon.getOutputStream());
//			  System.out.println(httpCon.getResponseCode());
//			  System.out.println(httpCon.getResponseMessage());
//			  out.close();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public static void main(String args[]){
//		
//		String m1 = "LEN=0082pid‐OSSB;sid‐12345;dt‐20151201131100;:mid‐1;TAG0‐3.000000;TAG1‐10.202614;TAG2‐73;|";
//		System.out.println(m1.length());
//		
//		AESEncryptDecrypt ae = new AESEncryptDecrypt();
//		ae.simulate();
//	}
	


}
