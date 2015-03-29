package com.invessence.bean.consumer;

import java.io.Serializable;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.converter.SQLData;
import com.invessence.dao.consumer.*;
import com.invessence.data.*;
import com.invessence.data.common.UserInfoData;
import com.invessence.data.consumer.ClientData;
import com.invessence.util.*;

@ManagedBean(name = "clientBean")
@SessionScoped
public class ClientBean extends ClientData implements Serializable
{

   private Boolean formEdit = true;
   private Long  beanAcctnum;
   private ClientBean clientBeaninstance = null;

   private WebUtil webutil = new WebUtil();

   @ManagedProperty("#{consumerListDataDAO}")
   private ConsumerListDataDAO listDAO;

   @ManagedProperty("#{consumerSaveDataDAO}")
   private ConsumerSaveDataDAO saveDAO;

   @ManagedProperty("#{emailMessage}")
   private EmailMessage messageText;

   public Long getBeanAcctnum()
   {
      return beanAcctnum;
   }

   public void setBeanAcctnum(Long beanAcctnum)
   {
      SQLData converter = new SQLData();
      this.beanAcctnum = converter.getLongData(beanAcctnum);
   }

   public void setListDAO(ConsumerListDataDAO listDAO)
   {
      this.listDAO = listDAO;
   }

   public void setSaveDAO(ConsumerSaveDataDAO saveDAO)
   {
      this.saveDAO = saveDAO;
   }

   public void setMessageText(EmailMessage messageText)
   {
      this.messageText = messageText;
   }


   public ClientBean()
   {
      super();
      this.clientBeaninstance = this;
   }
   public ClientBean getInstance()
   {
      return clientBeaninstance;
   }
   public void preRenderView()
   {
      try {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            loadData();
         }
         else {
            loadNewClientData();
         }
         formEdit = false;
      }
      catch (Exception e)
      {

      }
   }

   private void loadNewClientData() {


      try {
         UserInfoData uid = webutil.getUserInfoData();
         if (uid != null) {
            setLogonid(uid.getLogonID());
         }
         listDAO.getNewClientProfileData((ClientBean) this.getInstance());
         formEdit = false;
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }
   private void loadData() {

      try {
         setAcctnum(getAcctnum());
         setLogonid(webutil.getLogonid());
         System.out.println("LOGON ID :" + webutil.getLogonid());
         listDAO.getClientData(this);
         listDAO.getClientEmpData(this);
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   public String saveClientInfo()
   {
      String result = "success";
      try
      {
         setAcctnum(getAcctnum());
         setLogonid(getLogonid());
         System.out.println("LOGON ID :" + getLogonid());
         System.out.println("ACCOUNT NUMBER :" + getAcctnum());
         saveDAO.saveClientInfo(this);
         FacesContext.getCurrentInstance().getExternalContext().redirect("/pages/consumer/personalprofile2.xhtml");
         result = "success";
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
         result = "failed";
      }
      return result;
   }

   public String saveClientInfo2()
   {
      String result = "success";
      try
      {
         setAcctnum(getAcctnum());
         setLogonid(getLogonid());
         System.out.println("LOGON ID :" + getLogonid());
         System.out.println("ACCOUNT NUMBER :" + getAcctnum());
         saveDAO.saveClientInfo2(this);
         FacesContext.getCurrentInstance().getExternalContext().redirect("/pages/consumer/employmentprofile.xhtml");
         result = "success";
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
         result = "failed";
      }
      return result;
   }
   public String saveClientEmpInfo()
   {
      String result = "success";
      try
      {
         setAcctnum(getAcctnum());
         setLogonid(getLogonid());
         System.out.println("LOGON ID :" + getLogonid());
         System.out.println("ACCOUNT NUMBER :" + getAcctnum());
         saveDAO.saveClientEmpInfo(this);
         FacesContext.getCurrentInstance().getExternalContext().redirect("/pages/consumer/personalprofile1.xhtml");
         result = "success";
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
         result = "failed";
      }
      return result;
   }
}