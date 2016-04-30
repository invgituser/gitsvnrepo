package com.invessence.bean.consumer;

import java.io.Serializable;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import com.invessence.LTAMOptimizer;
import com.invessence.bean.ltam.LTAMCharts;
import com.invessence.constant.Const;
import com.invessence.converter.SQLData;
import com.invessence.dao.common.UserInfoDAO;
import com.invessence.dao.consumer.*;
import com.invessence.dao.ltam.*;
import com.invessence.data.LTAMTheme;
import com.invessence.data.common.AccountData;
import com.invessence.data.ltam.LTAMCustomerData;
import com.invessence.util.*;
import com.invessence.util.Impl.PagesImpl;
import com.invessence.ws.bean.WSCallStatus;
import com.invessence.ws.service.ServiceLayer;
import org.primefaces.event.*;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 2/4/15
 * Time: 1:18 PM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "ctob")
@SessionScoped
public class CTOBean implements Serializable
{
   private Long beanacctnum;
   private Long beanlogonid;
   private List<AccountData> accountDataList = null;
   private AccountData accountdata = new AccountData();

   @ManagedProperty("#{userInfoDAO}")
   UserInfoDAO userInfoDAO;
   public void setUserInfoDAO(UserInfoDAO userInfoDAO)
   {
      this.userInfoDAO = userInfoDAO;
   }

   @ManagedProperty("#{consumerSaveDAO}")
   ConsumerSaveDAO consumerSaveDAO;
   public void setConsumerSaveDAO(ConsumerSaveDAO consumerSaveDAO)
   {
      this.consumerSaveDAO = consumerSaveDAO;
   }

   @ManagedProperty("#{webutil}")
   WebUtil webutil;
   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   @ManagedProperty("#{serviceLayer}")
   private ServiceLayer serviceLayer;
   public void setServiceLayer(ServiceLayer serviceLayer)
   {
      this.serviceLayer = serviceLayer;
   }


   public Long getBeanacctnum()
   {
      return beanacctnum;
   }

   public void setBeanacctnum(Long beanacctnum)
   {
      this.beanacctnum = beanacctnum;
   }

   public Long getBeanlogonid()
   {
      return beanlogonid;
   }

   public void setBeanlogonid(Long beanlogonid)
   {
      this.beanlogonid = beanlogonid;
   }

   public AccountData getAccountdata()
   {
      return accountdata;
   }

   public void setAccountdata(AccountData accountdata)
   {
      this.accountdata = accountdata;
   }

   public void preRenderView()
   {
      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            // If beanacctnum is null or empty, then it must be a visitor
               if (webutil.validatePriviledge(Const.WEB_USER)) {
                  Long logonid = webutil.getLogonid();
                  collectAccountData(logonid, beanacctnum);
               }
         }
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public void collectAccountData(Long logonid, Long beanacctnum)
   {
      try {
         accountDataList = userInfoDAO.getListofAccount(logonid, beanacctnum);
         if (accountDataList != null) {
            accountdata = accountDataList.get(0);
         }
      }
      catch (Exception ex) {
         webutil.redirecttoMessagePage("Warning", "",
                                       "There is no data associated with this account number. <br/>" +
                                          "Please contact support."
         );
      }
   }

   public void saveAddress()
   {
      FacesMessage message;
      try {
         if (accountDataList == null) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                       "Invalid function:  Cannot save, not valid account number", "Invalid Account");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
         }

         Integer numofacct = accountDataList.size();
         Integer counter = 0;
         for (AccountData data: accountDataList) {
            if (serviceLayer != null && serviceLayer.isServiceActive()) {
               WSCallStatus callStatus = serviceLayer.updateMailingAddress(data.getClientAccountID(),
                                             data.getFirstname(), data.getMiddle(), data.getLastname(),
                                             data.getMailAddrs1(), data.getMailAddrs2(), data.getMailAddrs3(),
                                             data.getMailCity(), data.getState(),
                                             data.getMailZipCode(), (short) 1, /* data.getMailCountry(), */
                                             data.getPrimaryPhoneNbr(), data.getWorkPhoneNbr(),
                                             data.getFaxNbr(), data.getEmail());
               if (callStatus.getErrorCode() != 0) {
                  message = new FacesMessage(FacesMessage.SEVERITY_ERROR, callStatus.getErrorMessage(), "Web-service");
                  FacesContext.getCurrentInstance().addMessage(null, message);
                  return;
               }
               counter ++;
            }
            consumerSaveDAO.saveAddress(accountdata);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Account Information saved, Successfully", "Saved.");
            FacesContext.getCurrentInstance().addMessage(null, message);
         }
      }
      catch (Exception ex) {
      }
   }

}

