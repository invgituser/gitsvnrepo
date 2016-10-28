//package com.invessence.ws.util;
//
///**
// * Created by abhangp on 8/18/2016.
// */
//
//import com.invessence.ws.service.*;
//import org.springframework.beans.factory.FactoryBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//
//public class MyBeanFactoryBean implements FactoryBean<ServiceLayer>
//{
//
//// Using app context instead of bean references so that the unused
//// dependency can be left uninitialized if it is lazily initialized
//private ApplicationContext applicationContext;
//
//public ServiceLayerImpl getObject() {
//   MyBeanFactoryBean
//   ServiceLayer myBean = new ServiceLayerImpl();
//   if (true /* some condition */) {
//   myBean.setDependency(applicationContext.getBean(TD.class));
//   } else {
//   myBean.setDependency(applicationContext.getBean(DependencyY.class));
//   }
//   return myBean;
//   }
//
//   @Override
//   public Class<?> getObjectType()
//   {
//      return null;
//   }
//
//   @Override
//   public boolean isSingleton()
//   {
//      return false;
//   }
//
//
//   // Implementation of isSingleton => false and getObjectType
//
//   }
//
//
