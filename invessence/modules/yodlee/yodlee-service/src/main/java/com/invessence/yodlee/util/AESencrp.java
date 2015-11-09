package com.invessence.yodlee.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AESencrp {
    
    private static final String ALGO = "AES";
    private static final byte[] keyValue = new byte[] { 'L', 'o', 'a', 'N', 'E', 'Q', 'u' ,'i', 'V', 'L', 'e', 'n', 'T', '@', '%', '*'};
//private static final byte[] keyValue = new byte[] { 'I', 'n', 'v', 'e', 's', 's', 'e' ,'n', 'c', 'e', 'Y', 'o', 'd', 'l', 'e', 'e'};


    public static String encrypt(String Data) throws Exception {
        Key key = generateKey();        
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);       
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }

    public static String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }
    
    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGO);
        //System.out.println(key.toString()+": Key");
        return key;
    }

    /**
     * @param args
     */
     public static void main(String args[]){
    	
    	try {
    		
    		
    		
    		
    		System.out.println("encrypt String : " + encrypt("jdbc:oracle:thin:@10.144.18.90:1521:TRAPPS"));
    		System.out.println("encrypt String : " + encrypt("pdr"));    		
			System.out.println("encrypt String : " + encrypt("Just4fun"));
			
			System.out.println("decrypt String : " + decrypt("BK681kzI8zSQwGD/yzgrqRoqF9vKWjV1StE1GF/k+MHcriCvaRzfrpZlLPxT8fzb"));
			System.out.println("decrypt String : " + decrypt("bhjXYKfr8dgKcsE8Cv5rqA=="));
			System.out.println("decrypt String : " + decrypt("R4RTgzBSnjkmcGTt18WlZA=="));
		} catch (Exception e) {
			System.out.println("errrr:  " + e.getMessage());
			
		}
    }
}
