//package com.invessence.util;
//
//import java.security.Key;
//import java.security.MessageDigest;
//import java.util.Arrays;
//
//import javax.crypto.Cipher;
//import javax.crypto.spec.SecretKeySpec;
//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;
//
//public class EncryptionDecryptionAES
//{
//
//   private static final String ALGO = "AES";
//
//    public static String encrypt(String Data,String keyValue) throws Exception {
//    	//System.out.println("EncryptionDecryptionAES.encrypt()");
//        Key key = generateKey(keyValue);
//        //System.out.println("keyValue :"+keyValue);
//        Cipher c = Cipher.getInstance(ALGO);
//        c.init(Cipher.ENCRYPT_MODE, key);
//        byte[] encVal = c.doFinal(Data.getBytes());
//        String encryptedValue = new BASE64Encoder().encode(encVal);
//        return encryptedValue;
//    }
//
//    public static String decrypt(String encryptedData,String keyValue) throws Exception {
//    	//System.out.println("EncryptionDecryptionAES.decrypt()");
//        Key key = generateKey(keyValue);
//        //System.out.println("keyValue :"+keyValue);
//        Cipher c = Cipher.getInstance(ALGO);
//        c.init(Cipher.DECRYPT_MODE, key);
//        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
//        byte[] decValue = c.doFinal(decordedValue);
//        String decryptedValue = new String(decValue);
//        return decryptedValue;
//    }
//
//    private static Key generateKey(String keyValue) throws Exception {
//    	MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
//    	byte[] key1 = sha1.digest(keyValue.getBytes());
//    	//System.out.println(key1.length);
//		key1 = Arrays.copyOf(key1, 16);
//        Key key = new SecretKeySpec(key1, ALGO);
//       // Key key1=new SecretKeySpec(key, algorithm)
//        //System.out.println(key.toString()+": Key");
//        return key;
//    }
//
//    /**
//     * @param args
//     */
//     public static void main(String args[]){
//
//    	new EncryptionDecryptionAES().testEncrDecr("aRXDugfr4WQpVrxu");
//    	new EncryptionDecryptionAES().testEncrDecr("9");
//    }
//
//     private void testEncrDecr(String keyValue){
//
//    	 try {
//
//     		String s1=encrypt("Password@2015",keyValue);
//     		String s2=encrypt("Just4fun",keyValue);
//
//     		System.out.println("encrypt String : " + s1);
// 			System.out.println("encrypt String : " + s2);
//
//
// 			System.out.println("decrypt String : " + decrypt(s1,keyValue));
// 			System.out.println("decrypt String : " + decrypt(s2,keyValue));
//
//
// 		} catch (Exception e) {
// 			System.out.println("errrr:  " + e.getMessage());
//
// 		}
//     }
//}
