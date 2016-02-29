package com.invessence.broker.util;

/**
 * Created by IntelliJ IDEA.
 * User: shrests
 * Date: Mar 3, 2008
 * Time: 9:22:42 AM
 * To change this template use File | Settings | File Templates.
 */

import java.security.MessageDigest;

public class MsgDigester
{

    public static String getMessageDigest(String input) {

        //System.out.println("Input = " + input);

        try {
            //return input;
             // -- (not used) MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            
           // Create the message
            byte[] msgBytes = input.getBytes();

            // Update the message digest with some more bytes
            // This can be performed multiple times before creating the hash
            messageDigest.update(msgBytes);

            // Create the digest from the message
            byte[] messageDigestBytes = messageDigest.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigestBytes.length; i++) {
                String hex = Integer.toHexString(0xFF & messageDigestBytes[i]);

                if (hex.length() == 1) {
                    hexString.append('0');
                }

                hexString.append(hex);
            }

            //System.out.println("output = " + hexString.toString());


            return hexString.toString();

        } catch (Exception e) {
            System.out.println(e);

            return null;
        }
    }

}
