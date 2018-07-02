package com.sprint.demo.utils;

public class Utils {

	    public static void main(String[] args) {
	        System.out.println(generateString());
	    }

	    public static String generateString() {
//	        String uuid = UUID.randomUUID().toString();
//	        return "uuid = " + uuid.replace("-", "");
	    	return org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric(20).toUpperCase();

	    }
	    
	    public void decodeContentMD5(String contentMD5){
	    	
	    }

}
