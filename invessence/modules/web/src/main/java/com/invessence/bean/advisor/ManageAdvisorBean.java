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


   @ManagedProperty("#{advisorBean}")
   private AdvisorBean abean;

   @ManagedProperty("#{advisorListDataDAO}")
   private AdvisorListDataDAO advisorListDataDAO;

   @ManagedProperty("#{positionBean}")
   private PositionBean positionBean;

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
            FacesContext.getCurrentInstance().getExternalContext().redirect("/login.xhtml");
         }

         if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(Const.LOGONID_PARAM) != null)
         {
            setLogonid((Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(Const.LOGONID_PARAM));
            collectData(getLogonid());
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   public AdvisorBean getAbean()
   {
      return abean;
   }

   public void setAbean(AdvisorBean abean)
   {
      this.abean = abean;
   }

   public PositionBean getPositionBean()
   {
      return positionBean;
   }

   public void setPositionBean(PositionBean positionBean)
   {
      this.positionBean = positionBean;
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
            advisorManagedAccountList = advisorListDataDAO.getListOfAccounts(logonid, "Active");
            advisorPendingAccountList = advisorListDataDAO.getListOfAccounts(logonid, "Pending");
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
      setAcctnum(selectedAccount.getAcctnum());
      setLogonid(selectedAccount.getLogonid());
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
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Const.ACCTNO_PARAM, getSelectedAccount().getAcctnum());
            positionBean.findPosition(getLogonid(), getSelectedAccount().getAcctnum());
            whichXML = "/advisor/position.xhtml";
            //advisorpositionBean.findPosition(getLogonid(), getAcctnum());
         }
         else
         {
            abean.loadData(getSelectedAccount().getAcctnum());
            whichXML = "/advisor/add.xhtml";
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
