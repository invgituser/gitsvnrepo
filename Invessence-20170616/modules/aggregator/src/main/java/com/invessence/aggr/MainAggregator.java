package com.invessence.aggr;

import com.invessence.aggr.bean.*;
import com.invessence.aggr.service.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by abhangp on 12/1/2016.
 */
public class MainAggregator
{
   public static void main(String[] args)
   {
      ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("aggregatorConfig.xml");
      AggregationTrafficImpl aggregationTraffic = (AggregationTrafficImpl) context.getBean("aggregator");
      UserProfile userProfile=new UserProfile(new Long(1), "INV-12", "XXXXX","abhangp@invessence.com","Abhang","Patil", new Credentials("INVESSENCE","PASSWORD","BUILDINGBANJAMINS"),"");
//      UserProfile userProfile=new UserProfile(new Long(11), "INV-DEMO", "XXXXX","demo@invessence.com","User","Demo", new Credentials("INVESSENCE","PASSWORD","BUILDINGBANJAMINS"));
//      aggregationTraffic.registerUser(userProfile);
//      aggregationTraffic.readUser(userProfile);
      aggregationTraffic.deleteUser(userProfile);

//      System.out.println("URLLLLLLLLLLLLLLLLLLL :"+aggregationTraffic.getWidgetUrl(userProfile));
   }

}
