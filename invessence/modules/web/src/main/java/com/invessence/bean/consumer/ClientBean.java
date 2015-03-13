package com.invessence.bean.consumer;

import java.io.Serializable;
import javax.faces.bean.*;

import com.invessence.converter.SQLData;
import com.invessence.dao.consumer.*;
import com.invessence.data.*;
import com.invessence.data.consumer.ClientData;
import com.invessence.util.*;

@ManagedBean(name = "clientBean")
@SessionScoped
public class ClientBean extends ClientData implements Serializable
{

   private Boolean formEdit = true;

   @ManagedProperty("#{consumerListDataDAO}")
   private ConsumerListDataDAO listDAO;

   @ManagedProperty("#{consumerSaveDataDAO}")
   private ConsumerSaveDataDAO saveDAO;

   @ManagedProperty("#{emailMessage}")
   private EmailMessage messageText;

   private WebUtil webutil = new WebUtil();





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

   private void loadData(Long acctnum) {

      try {
         setAcctnum(webutil.getAcctnum());
         setLogonid(webutil.getLogonid());
         System.out.println("LOGON ID :" + webutil.getLogonid());
         System.out.println("ACCOUNT NUMBER :" + webutil.getAcctnum());
         listDAO.getClientData(this);
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   public void saveClientInfo()
   {
      try
      {
         setAcctnum(webutil.getAcctnum());
         setLogonid(webutil.getLogonid());
         System.out.println("LOGON ID :" + webutil.getLogonid());
         System.out.println("ACCOUNT NUMBER :" + webutil.getAcctnum());
         saveDAO.saveClientInfo(this);

      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }


}