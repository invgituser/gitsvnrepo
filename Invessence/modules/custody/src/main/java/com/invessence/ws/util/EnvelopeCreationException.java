package com.invessence.ws.util;

/**
 * Created by abhangp on 1/22/2016.
 */
public class EnvelopeCreationException extends Exception
{
   public EnvelopeCreationException(String message){
      super(message);

      System.out.println(message);
   }

}
