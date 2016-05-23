package com.invessence;

import java.util.*;

import com.invessence.service.util.ServiceParameters;
import com.invessence.util.EncryDecryAES;

/**
 * Created by abhangp on 3/11/2016.
 */
public class Main
{
   public static void main(String[] args)
   {
      try
      {
List<String> lst=new ArrayList<>();
         System.out.println("lst.size() = " + lst.size());
         System.out.println(EncryDecryAES.decrypt("h6ynsRLMxU9XKV2qCp/mvA==", ServiceParameters.BROKER_SERVICES_GEMINI_ENCRY_DECRY_KEY));
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }

   }
}
