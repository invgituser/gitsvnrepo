package com.invessence.ws.util;

/**
 * Created by abhangp on 1/22/2016.
 */
public class NoServiceSupportException extends Exception
{
   public NoServiceSupportException(String message){
      super(message);

      System.out.println(message);
   }

}
