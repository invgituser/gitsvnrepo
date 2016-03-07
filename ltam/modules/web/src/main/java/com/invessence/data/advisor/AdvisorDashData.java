package com.invessence.data.advisor;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 1/9/14
 * Time: 8:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class AdvisorDashData
{
   private Long logonid;
   private String advisorname;
   private String title;
   private ArrayList<SecurityData> activeList;
   private Map<String, Integer> statInfo;
   private Map<String, Integer> notification;

   public AdvisorDashData()
   {
      logonid = null;
      advisorname = null;
      title = null;
      activeList = new ArrayList<SecurityData>();
      statInfo = new HashMap<String, Integer>();
      notification = new HashMap<String, Integer>();

   }

   public AdvisorDashData(Long logonid, String advisorname)
   {
      this.logonid = logonid;
      this.advisorname =  advisorname;
   }

   public Long getLogonid()
   {
      return logonid;
   }

   public void setLogonid(Long logonid)
   {
      this.logonid = logonid;
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

   public ArrayList<SecurityData> getActiveList()
   {
      return activeList;
   }

   public void setActiveList(ArrayList<SecurityData> activeList)
   {
      this.activeList = activeList;
   }


   public Integer getStats(String key)
   {
      if (statInfo == null)
         return 0;

      if (statInfo.containsKey(key))
         return (statInfo.get(key));
      else
         return 0;
   }

   public void setStatInfo(Map<String, Integer> statInfo)
   {
      this.statInfo = statInfo;
   }

   public void setStatInfo(String key, Integer value)
   {
      if (statInfo == null) {
         statInfo = new HashMap<String, Integer>();
      }

      if (key != null && value != null) {
         statInfo.put(key.toUpperCase(),value);
      }
   }

   public Integer getMessage()
   {
      if (notification == null)
         return 0;

      if (notification.containsKey("Message"))
         return  notification.get("Message");
      else
         return 0;
   }

   public Map<String, Integer> getNotification()
   {
      return notification;
   }

   public void setNotification(Map<String, Integer> notification)
   {
      this.notification = notification;
   }

   public void addSecurityInfo(String source,
                               String cusip, String ticker, String description,
                               Double price, Double position, Integer count)
   {
      if (activeList == null) {
         activeList = new ArrayList<SecurityData>();
      }

      activeList.add(new SecurityData(source,
                                      cusip, ticker, description,
                                      price, position, count));
   }

   public Integer getMessage(String src)
   {
      if (statInfo != null)
      {
         if (statInfo.containsKey(src))
         {
            return statInfo.get(src);
         }
         else
         {
            return 0;
         }
      }
      else
      {
         return 0;
      }
   }


}
