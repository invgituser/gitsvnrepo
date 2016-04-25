package com.invessence.bean.consumer;

import java.io.Serializable;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.constant.Const;
import com.invessence.converter.JavaUtil;
import com.invessence.dao.common.UserInfoDAO;
import com.invessence.dao.consumer.*;
import com.invessence.data.common.AccountData;
import com.invessence.data.consumer.ConsumerData;
import com.invessence.util.WebUtil;
import com.invessence.ws.bean.*;
import com.invessence.ws.service.ServiceLayer;


/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 2/4/15
 * Time: 1:18 PM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "fundbean")
@SessionScoped
public class FundAccountBean implements Serializable
{

   private JavaUtil jutil = new JavaUtil();

   private String beanacctnum;

   private AccountData selectedAccount;

   private List<BankAcctDetails> bankaccountlist = null;
   private Map<String, String> bankNames;
   private Map<String, BankAcctDetails> bankInfoMap;

   private String bankacctnum;
   private Double investment;
   @ManagedProperty("#{serviceLayer}")
   private ServiceLayer serviceLayer;
   public void setServiceLayer(ServiceLayer serviceLayer)
   {
      this.serviceLayer = serviceLayer;
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

   @ManagedProperty("#{consumerListDataDAO}")
   private ConsumerListDataDAO listDAO;
   public void setListDAO(ConsumerListDataDAO listDAO)
   {
      this.listDAO = listDAO;
   }


   public String getBeanacctnum()
   {
      return beanacctnum;
   }

   public void setBeanacctnum(String beanacctnum)
   {
      this.beanacctnum = beanacctnum;
   }

   public List<BankAcctDetails> getBankaccountlist()
   {
      return bankaccountlist;
   }

   public void setBankaccountlist(List<BankAcctDetails> bankaccountlist)
   {
      this.bankaccountlist = bankaccountlist;
   }

   public Map<String, String> getBankNames()
   {
      return bankNames;
   }

   public Map<String, BankAcctDetails> getBankInfoMap()
   {
      return bankInfoMap;
   }

   public String getBankacctnum()
   {
      return bankacctnum;
   }

   public void setBankacctnum(String bankacctnum)
   {
      this.bankacctnum = bankacctnum;
   }

   public Double getInvestment()
   {
      return investment;
   }

   public void setInvestment(Double investment)
   {
      this.investment = investment;
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

   public void collectAccountData(Long logonid, String beanacctnum)
   {
      try {
         WSCallResult serviceStatus;
         Long acctnum = null;
         if (beanacctnum != null && ! beanacctnum.isEmpty()) {
            acctnum = Long.valueOf(beanacctnum);
         }
         selectedAccount = listDAO.getAccountData(logonid, acctnum);
         if (selectedAccount == null) {
            webutil.redirect("/access-denied.xhtml", null);
            return;
         }


         if (serviceLayer == null) {
            webutil.redirect("/access-denied.xhtml", null);
            return;
         }

         if (! serviceLayer.isServiceActive()) {
            webutil.redirect("/access-denied.xhtml", null);
            return;
         }

         serviceStatus = serviceLayer.getUserBankAcctDetails(selectedAccount.getClientAccountID());

         if (serviceStatus == null) {
            webutil.redirect("/access-denied.xhtml", null);
            return;
         }

         /* Note: Check service status non zero, print error message to the user */

         bankaccountlist = (List<BankAcctDetails>) serviceStatus.getGenericObject();
         setBankInfo(bankaccountlist);

      }
      catch (Exception ex) {
         webutil.redirecttoMessagePage("Warning", "",
                                       "There is no data associated with this account number. <br/>" +
                                          "Please contact support."
         );
      }
   }

   private void setBankInfo(List<BankAcctDetails> bankaccountlist) {
      try {
         if (bankaccountlist == null) {
            bankNames = null;
            bankInfoMap = null;
         }
         else {
            bankNames = new HashMap<String, String>();
            bankInfoMap = new HashMap<String, BankAcctDetails>();
            int i = 0;
            for (BankAcctDetails detail : bankaccountlist) {
               bankNames.put(detail.getBankName(), detail.getAccountNumber());
               bankInfoMap.put(detail.getAccountNumber(), detail);
               if (i == 0) {
                  bankacctnum = detail.getAccountNumber();
               }
            }
         }

      }
      catch (Exception ex) {

      }
   }

   public String getBankname() {
      if (bankacctnum == null)
         return null;

      return bankInfoMap.get(bankacctnum).getBankName();
   }

   public String getAccountHolder() {
      if (bankacctnum == null)
         return null;

      return bankInfoMap.get(bankacctnum).getNameOnAccount();
   }

   public String getRouting() {
      if (bankacctnum == null)
         return null;
      return bankInfoMap.get(bankacctnum).getBankRoutingNumber();
   }

   public String getDisplayRoutingNum() {
      if (bankacctnum == null)
         return null;
      String routinenum = jutil.getDisplayHiddenID(bankInfoMap.get(bankacctnum).getBankRoutingNumber());
      return routinenum;
   }

   public String getBankAccountNum() {
      if (bankacctnum == null)
         return null;

      return bankInfoMap.get(bankacctnum).getBankAccountNumber();
   }

   public String getHiddenBankAccountNum() {
      if (bankacctnum == null)
         return null;

      String acctnum = jutil.getDisplayHiddenID(bankInfoMap.get(bankacctnum).getBankAccountNumber());
      return acctnum;
   }

   public Integer getFundID() {
      if (bankacctnum == null)
         return 0;

      if (selectedAccount == null)
         return 0;

      Integer fundID = selectedAccount.getFundID();
      return fundID;
   }

   public void saveFund()
   {
      FacesMessage message;
      try {
         WSCallStatus wscallStatus;
         if (serviceLayer == null || ! serviceLayer.isServiceActive()) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Service Down.", "Access to Web-Service is down.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
         }

         if (bankacctnum != null && ! bankacctnum.isEmpty())  {
            wscallStatus = serviceLayer.fundAccount(bankInfoMap.get(bankacctnum).getBankAccountNumber(),
                                     getFundID(),
                                     investment,
                                     bankacctnum) ;
            if (wscallStatus.getErrorCode() != 0) {
               message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unable to Fund", wscallStatus.getErrorMessage());
               FacesContext.getCurrentInstance().addMessage(null, message);
               return;
            }
            else {
               message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Successful", "Amount posted, starting bank transfer.  It may take up to 3 days to post.");
               FacesContext.getCurrentInstance().addMessage(null, message);
               return;

            }
         }

      }
      catch (Exception ex) {
         message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System Error", "System Error:" + ex.getMessage());
         FacesContext.getCurrentInstance().addMessage(null, message);
         return;
      }
   }

}

