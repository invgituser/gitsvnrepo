package com.invessence.bean.common;

import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.constant.Const;
import com.invessence.dao.common.CommonDAO;
import com.invessence.data.common.NotificationData;
import com.invessence.util.*;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "commonbean")
@RequestScoped
public class CommonBean
{
   private static final long serialVersionUID = 900L;
   private ArrayList<NotificationData> notificationDataList = null;
   private NotificationData selectedMessage = null;
   private String filterNotice = "N";

   @ManagedProperty("#{menu}")
   private Menu menu;
   public void setMenu(Menu menu)
   {
      this.menu = menu;
   }

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

   @PostConstruct
   public void init()
   {
      try
      {
               filterNotice = "N";
               collectNotification();
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
         notificationDataList = commonDao.getNotification(logonid, filterNotice);
      }
   }

   public void showNotification() {
      RequestContext.getCurrentInstance().openDialog("/pages/common/notification");
   }

   public void filterData()
   {
      collectNotification();
   }

   public String markDone()
   {
      NotificationData data = selectedMessage;
      if (data != null) {
         data.setStatus("A");
         commonDao.saveNotice(data);
         webutil.redirect("/pages/common/notification.xhtml", null);
/*
         selectedMessage = null;
         collectNotification();
         RequestContext.getCurrentInstance().update("messageDT");
*/
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
         webutil.redirect("/pages/common/notification.xhtml", null);
/*
         selectedMessage = null;
         collectNotification();
         RequestContext.getCurrentInstance().update("messageDT");
*/
         return "success";
      }
      return "failed";
   }



}