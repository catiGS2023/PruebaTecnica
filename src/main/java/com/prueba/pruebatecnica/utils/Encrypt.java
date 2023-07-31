package com.prueba.pruebatecnica.utils;

import java.security.MessageDigest;


public class Encrypt {
	
	
public String getMD5(String input) {
    String md5=null;
	try {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input.getBytes(), 0, input.length());
        md5 = new java.math.BigInteger(1, md.digest()).toString(16);
    }
    catch (java.security.NoSuchAlgorithmException e) {
    	throw new RuntimeException(e);
           
    }
    return md5;     
}
}