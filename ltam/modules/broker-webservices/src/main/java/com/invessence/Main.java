package com.invessence;

import java.util.*;

import com.invessence.util.EncryDecryAES;
import com.invessence.ws.util.SysParameters;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
         System.out.println(EncryDecryAES.decrypt("h6ynsRLMxU9XKV2qCp/mvA==", SysParameters.encryDecryKey));
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }

   }
}
