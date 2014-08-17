package com.invessence.bean.advisor;

import java.io.Serializable;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.bean.*;
import com.invessence.constant.Const;
import com.invessence.dao.advisor.AdvisorListDataDAO;
import com.invessence.data.ManageAccount;
import com.invessence.data.advisor.AdvisorData;
import com.invessence.util.EmailMessage;


@ManagedBean(name = "manageAdvisorBean")
@ViewScoped
public class ManageAdvisorBean implements Serializable
{
   private static final long serialVersionUID = 100003L;

   @ManagedProperty("#{advisorListDataDAO}")
   private AdvisorListDataDAO advisorListDataDAO;

   private List<AdvisorData> advisorManagedAccountList;
   private List<AdvisorData> advisorPendingAccountList;
   private List<AdvisorData> filteredManagedAccountList;
   private List<AdvisorData> filteredPendingAccountList;
   private AdvisorData selectedAccount;


   private Long acctnum;
   private Long logonid;

   private EmailMessage messageSource;


   public EmailMessage getMessageSource()
   {
      return messageSource;
   }

   public void setMessageSource(EmailMessage messageSource)
   {
      this.messageSource = messageSource;
   }

   @PostConstruct
   public void init()
   {
      String userName;
      try
      {
         if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(Const.LOGONID_PARAM) == null)
         {
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
         }

         if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(Const.LOGONID_PARAM) != null)
         {
            setLogonid((Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(Const.LOGONID_PARAM));
            collectData(getLogonid());
         }
         else
         {
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   public void setAdvisorListDataDAO(AdvisorListDataDAO advisorListDataDAO)
   {
      this.advisorListDataDAO = advisorListDataDAO;
   }

   public void collectData(Long logonid)
   {
      try
      {
         if (logonid != null)
         {
            if (advisorManagedAccountList != null)
               advisorManagedAccountList.clear();
            if (advisorPendingAccountList != null)
               advisorPendingAccountList.clear();
            advisorManagedAccountList = advisorListDataDAO.getAccountData(logonid,"Active");
            advisorPendingAccountList = advisorListDataDAO.getAccountData(logonid,"Pending");
         }

      }
      catch (Exception ex)
      {
         System.out.println("Error in Advisor collecting data on ManageAdvisorBean:" + ex.getMessage());
      }
   }

   public List<AdvisorData> getAdvisorManagedAccountList()
   {
      return advisorManagedAccountList;
   }

   public void setAdvisorManagedAccountList(List<AdvisorData> advisorManagedAccountList)
   {
      this.advisorManagedAccountList = advisorManagedAccountList;
   }

   public List<AdvisorData> getAdvisorPendingAccountList()
   {
      return advisorPendingAccountList;
   }

   public void setAdvisorPendingAccountList(List<AdvisorData> advisorPendingAccountList)
   {
      this.advisorPendingAccountList = advisorPendingAccountList;
   }

   public List<AdvisorData> getFilteredManagedAccountList()
   {
      return filteredManagedAccountList;
   }

   public void setFilteredManagedAccountList(List<AdvisorData> filteredManagedAccountList)
   {
      this.filteredManagedAccountList = filteredManagedAccountList;
   }

   public List<AdvisorData> getFilteredPendingAccountList()
   {
      return filteredPendingAccountList;
   }

   public void setFilteredPendingAccountList(List<AdvisorData> filteredPendingAccountList)
   {
      this.filteredPendingAccountList = filteredPendingAccountList;
   }

   public AdvisorData getSelectedAccount()
   {
      return selectedAccount;
   }

   public void setSelectedAccount(AdvisorData selectedAccount)
   {
      this.selectedAccount = selectedAccount;
   }

   public Long getAcctnum()
   {
      return acctnum;
   }

   public void setAcctnum(Long acctnum)
   {
      this.acctnum = acctnum;
   }

   public Long getLogonid()
   {
      return logonid;
   }

   public void setLogonid(Long logonid)
   {
      this.logonid = logonid;
   }

   public String doManagedAction()
   {
      String whichXML;

      try
      {
         if (getSelectedAccount() == null)
         {
            return "failed";
         }

         if (getSelectedAccount().getAcctstatus().equals("Active"))
         {
            whichXML = "/advisor/position.xhtml";
            //advisorpositionBean.findPosition(getLogonid(), getAcctnum());
         }
         else
         {
            whichXML = "/advisor/custProfile.xhtml";
            //advisorBean.findGoals(getLogonid(), getAcctnum());
         }


         FacesContext.getCurrentInstance().getExternalContext().redirect(whichXML);
      }
      catch (Exception ex)
      {
         return ("failed");
      }

      return ("success");
   }


}
