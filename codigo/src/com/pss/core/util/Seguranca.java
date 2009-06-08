package com.pss.core.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class Seguranca {
	
	public static String encriptar(String plainText) {
		byte[] sbe = null;
		MessageDigest md5;

		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Nao foi possivel criptografar o " + plainText, e);
		}

		md5.update(plainText.getBytes());
		sbe = md5.digest();

		BASE64Encoder base64 = new BASE64Encoder();
		return base64.encode(sbe);
	}

}
