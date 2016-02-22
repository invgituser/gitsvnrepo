package com.invessence.bean.common;

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.constant.Const;
import com.invessence.dao.common.CommonDAO;
import com.invessence.data.common.NotificationData;
import com.invessence.util.*;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "notificationBean")
@RequestScoped
public class NotificationBean implements Serializable
{
   private ArrayList<NotificationData> notificationDataList = null;
   private NotificationData notificationData = new NotificationData();
   private NotificationData selectedMessage = null;
   private String filterNotice = "N";
   private String notificationType = "";

   @ManagedProperty("#{webutil}")
   private WebUtil webutil;
   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   @ManagedProperty("#{commonDAO}")
   private CommonDAO commonDao;

   public void setCommonDao(CommonDAO commonDao)
   {
      this.commonDao = commonDao;
   }

   public ArrayList<NotificationData> getNotificationDataList()
   {
      return notificationDataList;
   }

   public NotificationData getSelectedMessage()
   {
      return selectedMessage;
   }

   public void setSelectedMessage(NotificationData selectedMessage)
   {
      this.selectedMessage = selectedMessage;
   }

   public String getFilterNotice()
   {
      return filterNotice;
   }

   public void setFilterNotice(String filterNotice)
   {
      this.filterNotice = filterNotice;
   }

   public String getNotificationType()
   {
      return notificationType;
   }

   public void setNotificationType(String notificationType)
   {
      this.notificationType = notificationType.substring(0,1);
   }

   public NotificationData getNotificationData()
   {
      return notificationData;
   }

   public void setNotificationData(NotificationData notificationData)
   {
      this.notificationData = notificationData;
   }

   public void preRenderView()
   {

      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            filterNotice = "N";
            collectNotification();
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   public void collectNotification()
   {
      Long logonid;
      if (webutil != null) {
         logonid = webutil.getLogonid();
         if (notificationType == null || notificationType.isEmpty())
            notificationType = "M";
         notificationDataList = commonDao.getNotification(logonid, notificationType, filterNotice);
      }
   }

   public void showNotification() {
      RequestContext.getCurrentInstance().openDialog("/pages/common/notification");
   }

   public void filterData()
   {
      collectNotification();
   }

   public Boolean getAddAllowed() {
      if (notificationType != null && notificationType.toUpperCase().startsWith("T")) {
         if (webutil.hasRole(Const.ROLE_ADVISOR))
            return true;
      }
      return false;
   }

   public String getMessageHeader() {
      if (notificationType != null && notificationType.toUpperCase().startsWith("T"))
         return "Tasks";
      else
         return "Messages";
   }

   public String markDone()
   {
      NotificationData data = selectedMessage;
      if (data != null) {
         data.setStatus("A");
         commonDao.saveNotice(data);
//         webutil.redirect("/pages/common/notification.xhtml", null);

         collectNotification();
         selectedMessage = null;
         RequestContext.getCurrentInstance().update("messageDT");

         return "success";
      }
      return "failed";
   }

   public String markUnDone()
   {
      NotificationData data = selectedMessage;
      if (data != null) {
         data.setStatus("N");
         commonDao.saveNotice(data);

//         webutil.redirect("/pages/common/notification.xhtml", null);

         selectedMessage = null;
         collectNotification();
         RequestContext.getCurrentInstance().update("messageDT");

         return "success";
      }
      return "failed";
   }

   public void saveNotification()
   {
      if (notificationData != null) {
         notificationData.setStatus("N");
         notificationData.setTagid("T");
         notificationData.setAdvisorlogonid(webutil.getLogonid());
         notificationData.setNoticetype(notificationData.getNoticetype().substring(1,1));
         commonDao.saveNotice(notificationData);
         collectNotification();
         // RequestContext.getCurrentInstance().closeDialog("addNotification");
      }
   }



}