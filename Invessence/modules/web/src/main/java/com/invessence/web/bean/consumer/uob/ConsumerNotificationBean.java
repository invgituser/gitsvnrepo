package com.invessence.web.bean.consumer.uob;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.web.constant.WebConst;
import com.invessence.web.dao.advisor.*;
import com.invessence.web.dao.common.CommonDAO;
import com.invessence.web.data.common.NotificationData;
import com.invessence.web.util.WebUtil;
import org.primefaces.context.RequestContext;

/**
 * Created by sagar on 9/20/2017.
 */

@ManagedBean(name = "consumernotificationBean")
@SessionScoped
public class ConsumerNotificationBean implements Serializable
{
   private ArrayList<NotificationData> notificationDataList;
   // private NotificationData notificationData = new NotificationData();
   private List<NotificationData> selectionList;
   private NotificationData selectedMessage;
   private String filterNotice;
   private String previousfilterNotice;
   private String notificationType;
   private String errorMessage;

   @ManagedProperty("#{webutil}")
   private WebUtil webutil;
   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   @ManagedProperty("#{advisorSaveDataDAO}")
   private AdvisorSaveDataDAO advisorSaveDAO;
   public void setAdvisorSaveDAO(AdvisorSaveDataDAO advisorSaveDAO)
   {
      this.advisorSaveDAO = advisorSaveDAO;
   }


   @ManagedProperty("#{commonDAO}")
   private CommonDAO commonDAO;
   public void setCommonDAO(CommonDAO commonDAO)
   {
      this.commonDAO = commonDAO;
   }

//   @ManagedProperty("#{advisorListDataDAO}")
//   private AdvisorListDataDAO advisorListDAO;
//   public void setAdvisorListDAO(AdvisorListDataDAO advisorListDAO)
//   {
//      this.advisorListDAO = advisorListDAO;
//   }

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

   public String getErrorMessage()
   {
      return errorMessage;
   }

   public void setErrorMessage(String errorMessage)
   {
      this.errorMessage = errorMessage;
   }

   public String getPreviousfilterNotice()
   {
      return previousfilterNotice;
   }

   public void setPreviousfilterNotice(String previousfilterNotice)
   {
      this.previousfilterNotice = previousfilterNotice;
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
         selectionList = new ArrayList<>();
         if(previousfilterNotice != null){
            if(!previousfilterNotice.equalsIgnoreCase(filterNotice))
            {
               this.setErrorMessage("");
            }
         }else{
            this.setErrorMessage("");
         }
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
//         notificationDataList = advisorListDAO.getAdvisorNotification(logonid, notificationType, filterNotice);
         notificationDataList = commonDAO.getNotificationDtls(webutil.getLogonid(),notificationType, filterNotice,webutil.getAccess(),false);
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
         advisorSaveDAO.saveAdvisorNotice(data);
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
            advisorSaveDAO.saveAdvisorNotice(data);
         }
         // webutil.redirect("/pages/common/notification.xhtml", null);
         filterNotice = "N";
         collectNotification();
         selectionList = null;
      }
   }

   public void markRead()
   {
      this.setErrorMessage("");
      if (getSelectionList().size() != 0 && getSelectionList() != null) {

         for (NotificationData data: selectionList) {
            data.setStatus("A");
            commonDAO.saveConsumerNotification(data,webutil.getLogonid());
         }
         // webutil.redirect("/pages/common/notification.xhtml", null);
         filterNotice = "N";
         collectNotification();
      }else{
         //FacesContext context = FacesContext.getCurrentInstance();
         previousfilterNotice = "N";
         this.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.acctType.requiredMsg", "Please select atleast one checkbox!", null));
         // context.addMessage(null, new FacesMessage("Second Message", "Please select atleast one checkbox"));
      }
   }

   public void markUnRead()
   {
      if (getSelectionList().size() != 0 && getSelectionList() != null) {
         this.setErrorMessage("");
         for (NotificationData data: selectionList) {
            data.setStatus("N");
//            advisorSaveDAO.saveAdvisorNotice(data);
            commonDAO.saveConsumerNotification(data,webutil.getLogonid());
         }
         filterNotice = "A";
         collectNotification();
      }else{
         // FacesContext context = FacesContext.getCurrentInstance();
         previousfilterNotice = "A";
         //context.addMessage(null, new FacesMessage("Second Message", "Please select atleast one checkbox"));
         this.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.acctType.requiredMsg", "Please select atleast one checkbox!", null));
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