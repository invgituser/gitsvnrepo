//package com.invessence.docServices;
//
//import com.invessence.custody.uob.UOBDataMaster;
//import com.invessence.custody.uob.dao.UOBDaoImpl;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
///**
// * Created by abhangp on 3/11/2016.
// */
//
//public class CallingProgramme
//{
//   public static void main(String[] args)
//   {
//      try
//      {
//         ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("webServicesConfig.xml");
//         UOBDaoImpl uobDao = (UOBDaoImpl) context.getBean("uobDaoImpl");
//         UOBDataMaster uobDataMaster=(UOBDataMaster) uobDao.fetch(new Long(0));
//         System.out.println("uobDataMaster = " + uobDataMaster);
//
//         context.close();
//      }
//      catch (Exception e)
//      {
//         e.printStackTrace();
//      }
//
//   }
//}
