package com.invessence.web.bean.common;

import java.util.*;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.web.constant.WebConst;
import com.invessence.web.dao.common.CommonDAO;
import com.invessence.web.data.common.*;
import com.invessence.web.util.WebUtil;

/**
 * Created by sagar on 9/20/2017.
 */
@ManagedBean(name = "cmnBn")
@SessionScoped
public class CommonBean
{
   @ManagedProperty("#{webutil}")
   private WebUtil webutil;

   @ManagedProperty("#{commonDAO}")
   private CommonDAO commonDAO;
   private long notificationCount;
   private Map<String, Integer> statInfo;
   private ArrayList<NotificationData> notificationDataList;

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
         e.printStackTrace();
      }
   }
   public void reloadData() {
      statInfo = commonDAO.getNotificationCount(webutil.getLogonid(),webutil.getAccess());
      notificationDataList = commonDAO.getNotificationDtls(webutil.getLogonid(), "M", "N",webutil.getAccess());
   }

   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   public void setCommonDAO(CommonDAO commonDAO)
   {
      this.commonDAO = commonDAO;
   }

   public long getNotificationCount()
   {
      return notificationCount;
   }

   public Map<String, Integer> getStatInfo()
   {
      return statInfo;
   }

   public void setStatInfo(Map<String, Integer> statInfo)
   {
      this.statInfo = statInfo;
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

   public ArrayList<NotificationData> getNotificationDataList()
   {
      return notificationDataList;
   }

   public void setNotificationDataList(ArrayList<NotificationData> notificationDataList)
   {
      this.notificationDataList = notificationDataList;
   }
}
