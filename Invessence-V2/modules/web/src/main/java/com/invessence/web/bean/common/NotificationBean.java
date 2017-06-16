package com.invessence.web.bean.common;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.web.constant.*;
import com.invessence.web.dao.common.CommonDAO;
import com.invessence.web.data.common.NotificationData;
import com.invessence.web.util.*;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "notificationBean")
@SessionScoped
public class NotificationBean implements Serializable
{
   private ArrayList<NotificationData> notificationDataList;
   // private NotificationData notificationData = new NotificationData();
   private List<NotificationData> selectionList;
   private NotificationData selectedMessage;
   private String filterNotice;
   private String notificationType;

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

   public List<NotificationData> getSelectionList()
   {
      return selectionList;
   }

   public void setSelectionList(List<NotificationData> selectionList)
   {
      this.selectionList = selectionList;
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

/*
   public NotificationData getNotificationData()
   {
      return notificationData;
   }

   public void setNotificationData(NotificationData notificationData)
   {
      this.notificationData = notificationData;
   }
*/

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
         notificationDataList = commonDao.getUserNotification(logonid, notificationType, filterNotice);
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
         if (webutil.hasRole(WebConst.ROLE_ADVISOR))
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

   public void archiveMessage()
   {
      NotificationData data = selectedMessage;
      if (data != null) {
         data.setStatus("A");
         commonDao.saveUserNotice(data);
         // webutil.redirect("/pages/common/notification.xhtml", null);
         filterNotice = "N";
         collectNotification();
      }
   }

   public void archiveMessages()
   {
      if (selectionList != null) {
         for (NotificationData data: selectionList) {
            data.setStatus("A");
            commonDao.saveUserNotice(data);
         }
         // webutil.redirect("/pages/common/notification.xhtml", null);
         filterNotice = "N";
         collectNotification();
      }
   }

   public void markRead()
   {
      NotificationData data = selectedMessage;
      if (data != null) {
         data.setStatus("A");
         commonDao.saveUserNotice(data);
         // webutil.redirect("/pages/common/notification.xhtml", null);
         filterNotice = "N";
         collectNotification();
      }
   }

   public void markUnRead()
   {
      NotificationData data = selectedMessage;
      if (data != null) {
         data.setStatus("N");
         commonDao.saveUserNotice(data);
         // webutil.redirect("/pages/common/notification.xhtml", null);
         filterNotice = "A";
         collectNotification();
      }
   }

/*
   public void saveNotification()
   {
      if (notificationData != null) {
         notificationData.setStatus("N");
         notificationData.setTagid("T");
         notificationData.setAdvisorlogonid(webutil.getLogonid());
         notificationData.setNoticetype(notificationData.getNoticetype().substring(1,1));
         commonDao.saveUserNotice(notificationData);
         collectNotification();
         // RequestContext.getCurrentInstance().closeDialog("addNotification");
      }
   }
*/



}