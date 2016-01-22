package com.invessence.broker;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by abhangp on 1/17/2016.
 */
public class Main {

   public static void main(String[] args) {
      try {

         ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("brokerBeanConf.xml");
         BrokerFileProcessor pp = context.getBean(BrokerFileProcessor.class);
         pp.process();

         context.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

   }


}
