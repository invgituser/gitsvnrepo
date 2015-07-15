package com.invessence.data.advisor;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 1/9/14
 * Time: 8:58 PM
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("UnusedDeclaration")
public class AdvisorDashData
{
     private String advisorname;
     private String title;
     private Map<String, Double> securityInfo;
     private Map<String, Integer> statInfo;
     private Map<String, String> newsInfo;

   public AdvisorDashData()
   {
   }

   public String getAdvisorname()
   {
      return advisorname;
   }

   public void setAdvisorname(String advisorname)
   {
      this.advisorname = advisorname;
   }

   public String getTitle()
   {
      return title;
   }

   public void setTitle(String title)
   {
      this.title = title;
   }

   public Map<String, Double> getSecurityInfo()
   {
      return securityInfo;
   }

   public void setSecurityInfo(Map<String, Double> securityInfo)
   {
      this.securityInfo = securityInfo;
   }

   public Map<String, Integer> getStatInfo()
   {
      return statInfo;
   }

   public void setStatInfo(Map<String, Integer> statInfo)
   {
      this.statInfo = statInfo;
   }

   public Map<String, String> getNewsInfo()
   {
      return newsInfo;
   }

   public void setNewsInfo(Map<String, String> newsInfo)
   {
      this.newsInfo = newsInfo;
   }
}
