package com.invessence.data.advisor;

import java.util.*;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import com.invessence.dao.advisor.AdvisorListDataDAO;
import com.invessence.dao.common.CommonDAO;

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
     private Map<String, Double> securityInfo;
     private Map<String, Integer> statInfo;
     private Map<String, Integer> salesInfo;

   @ManagedProperty("#{advisorListDataDAO}")
   private AdvisorListDataDAO listDAO;
   public void setListDAO(AdvisorListDataDAO listDAO)
   {
      this.listDAO = listDAO;
   }

   @ManagedProperty("#{commonDAO}")
   private CommonDAO commonDAO;
   public void setCommonDAO(CommonDAO commonDAO)
   {
      this.commonDAO = commonDAO;
   }

   public AdvisorDashData()
   {
   }

   public AdvisorDashData(Long logonid)
   {
      this.logonid = logonid;
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

   public Map<String, Integer> getSalesInfo()
   {
      return salesInfo;
   }

   public void setSalesInfo(Map<String, Integer> salesInfo)
   {
      this.salesInfo = salesInfo;
   }

   public void preRenderView()
   {

      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            reloadData();
         }
      }
      catch (Exception e)
      {
      }
   }

   public void reloadData() {
      if (listDAO != null) {
         listDAO.reloadAdvisorDashBoard(this);
      }
      if (commonDAO != null) {
         statInfo = commonDAO.getNotificationInfo(logonid);
      }
   }

   public void addSalesInfo(String src, Integer value) {
      if (salesInfo == null) {
         salesInfo = new HashMap<String, Integer>();
      }
      salesInfo.put(src, value);
   }

   public void addSecurityInfo(String src, Double value) {
      if (securityInfo == null) {
         securityInfo = new HashMap<String, Double>();
      }
      securityInfo.put(src, value);
   }

   public Integer getMessage(String src) {
      if (statInfo != null) {
         if (statInfo.containsKey(src))
            return statInfo.get(src);
         else
            return 0;
      }
      else
         return 0;
   }



}
