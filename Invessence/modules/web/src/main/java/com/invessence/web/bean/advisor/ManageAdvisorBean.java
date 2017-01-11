package com.invessence.web.bean.advisor;

import java.io.Serializable;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.web.constant.*;
import com.invessence.web.dao.advisor.*;
import com.invessence.web.dao.consumer.ConsumerListDataDAO;
import com.invessence.web.data.common.*;
import com.invessence.web.data.consumer.RiskCalculator;
import com.invessence.web.util.*;
import org.apache.commons.lang.ObjectUtils;


@ManagedBean(name = "manageAdvisorBean")
@SessionScoped
public class ManageAdvisorBean implements Serializable
{
   @ManagedProperty("#{webutil}")
   private WebUtil webutil;
   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   @ManagedProperty("#{uiLayout}")
   private UILayout uiLayout;
   public void setUiLayout(UILayout uiLayout)
   {
      this.uiLayout = uiLayout;
   }

/*
   @ManagedProperty("#{advisorBean}")
   private AdvisorBean abean;
*/

   @ManagedProperty("#{consumerListDataDAO}")
   private ConsumerListDataDAO consumerListDataDAO;
   public void setConsumerListDataDAO(ConsumerListDataDAO consumerListDataDAO)
   {
      this.consumerListDataDAO = consumerListDataDAO;
   }

   @ManagedProperty("#{advisorListDataDAO}")
   private AdvisorListDataDAO advisorListDataDAO;

   @ManagedProperty("#{advisorSaveDataDAO}")
   private AdvisorSaveDataDAO advisorSaveDataDAO;
   private int rowKey;
/*
   @ManagedProperty("#{positionBean}")
   private PositionBean positionBean;
*/

   private ArrayList<AccountData> accountDataList = new ArrayList<AccountData>();
   private ArrayList<AccountData> filteredDataList = new ArrayList<AccountData>();
   private ArrayList<AccountData> selectedDataList = new ArrayList<AccountData>();
   private String filteredClient;
   private String filtereMenuList = "All";
   private AccountData selectedAccount;
   private RiskCalculator riskCalculator;
   private Integer filteredBydate=0;

   @ManagedProperty("#{emailMessage}")
   private WebMessage messageSource;


   public WebMessage getMessageSource()
   {
      return messageSource;
   }

   public void setMessageSource(WebMessage messageSource)
   {
      this.messageSource = messageSource;
   }

   public void preRenderView()
   {
      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            if (webutil.validatePriviledge(WebConst.ROLE_ADVISOR)) {
               Long logonid;
               logonid = webutil.getLogonid();

               if (logonid != null)
               {
                     filterData();
               }
            }
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

/*
   public void setAbean(AdvisorBean abean)
   {
      this.abean = abean;
   }

   public void setPositionBean(PositionBean positionBean)
   {
      this.positionBean = positionBean;
   }
*/

   public String getFiltereMenuList()
   {
      return filtereMenuList;
   }

   public void setFiltereMenuList(String filtereMenuList)
   {
      this.filtereMenuList = filtereMenuList;
   }

   public Integer getFilteredBydate()
   {
      return filteredBydate;
   }

   public void setFilteredBydate(Integer filteredBydate)
   {
      this.filteredBydate = filteredBydate;
   }

   public void setAdvisorListDataDAO(AdvisorListDataDAO advisorListDataDAO)
   {
      this.advisorListDataDAO = advisorListDataDAO;
   }

   public void setAdvisorSaveDataDAO(AdvisorSaveDataDAO advisorSaveDataDAO)
   {
      this.advisorSaveDataDAO = advisorSaveDataDAO;
   }

   public AdvisorSaveDataDAO getAdvisorSaveDataDAO()
   {
      return advisorSaveDataDAO;
   }

   public ArrayList<AccountData> getAccountDataList()
   {
      return accountDataList;
   }

   public void setAccountDataList(ArrayList<AccountData> accountDataList)
   {
      this.accountDataList = accountDataList;
   }

   public ArrayList<AccountData> getSelectedDataList()
   {
      return selectedDataList;
   }

   public void setSelectedDataList(ArrayList<AccountData> selectedDataList)
   {
      this.selectedDataList = selectedDataList;
   }

   public void collectData(Long logonid)
   {
      try
      {
         if (logonid != null)
         {
            if (accountDataList == null)
               accountDataList = new ArrayList<AccountData>();

            if (filteredDataList == null)
               filteredDataList = new ArrayList<AccountData>();

            accountDataList.clear();
            if(filteredBydate == 0){
               filtereMenuList = "All";
            }
            accountDataList = advisorListDataDAO.getListOfAccounts(logonid, filteredClient, (filteredBydate == 0 ? null : filteredBydate));
            filteredBydate=0;

         }
      }
      catch (Exception ex)
      {
         System.out.println("Error in Advisor collecting data:" + ex.getMessage());
      }
   }

   private void loadRiskData(Long acctnum)
   {
      riskCalculator = new RiskCalculator();
      consumerListDataDAO.getRiskProfileData(acctnum, riskCalculator);
   }

   public void filterDataByDate(Integer noOfDate){
      if(noOfDate==0){
         filtereMenuList = "All";
      }else if(noOfDate == 1){
         filtereMenuList = "Today";
      }else if(noOfDate == 5){
         filtereMenuList = "Last 5 days";
      }else if(noOfDate == 30){
         filtereMenuList = "Last 30 days";
      }
      filteredBydate = noOfDate;
      refreshPage();
   }

   public void filterData() {

      collectData(webutil.getLogonid());
      filteredDataList = accountDataList;
/*
      if (getFilteredClient() == null || getFilteredClient().length() == 0)
        filteredDataList = accountDataList;
      else {
         filteredDataList.clear();
         for (AccountData adata: accountDataList){
            if (adata.getAcctStatus().startsWith(getFilteredClient()))
               filteredDataList.add(adata);
         }
      }
*/
   }

   public void refreshPage() {
      String url;
      if (getFilteredClient() != null) {
         uiLayout.doMenuAction("advisor", "pendingAdv.xhtml?action=" + getFilteredClient());
       }
   }

   public AdvisorListDataDAO getAdvisorListDataDAO()
   {
      return advisorListDataDAO;
   }

   public ArrayList<AccountData> getFilteredDataList()
   {
      return filteredDataList;
   }

   public void setFilteredDataList(ArrayList<AccountData> filteredDataList)
   {
      this.filteredDataList = filteredDataList;
   }

   public String getFilteredClient()
   {
      return filteredClient;
   }

   public void setFilteredClient(String filteredClient)
   {
      this.filteredClient = filteredClient;
   }

   public AccountData getSelectedAccount()
   {
      return selectedAccount;
   }

   public void setSelectedAccount(AccountData selectedAccount)
   {
      this.selectedAccount = selectedAccount;
   }

   public RiskCalculator getRiskCalculator()
   {
      return riskCalculator;
   }

   public void setRiskCalculator(RiskCalculator riskCalculator)
   {
      this.riskCalculator = riskCalculator;
   }

   public void showList()
   {
      try {
         uiLayout.doMenuAction("advisor", "alist.xhtml?action=" + filteredClient);
      }
      catch (Exception ex) {

      }
   }

   public void doEditAction()
   {
      try
      {
         if (getSelectedAccount() == null)
         {
            return;
         }

         if (getSelectedAccount().getAdvisor_priviledge() == null ||
             getSelectedAccount().getAdvisor_priviledge().equalsIgnoreCase("V")) {
            loadRiskData(getSelectedAccount().getAcctnum());
            uiLayout.doMenuAction("advisor", "vwProfile.xhtml");
         }
         else {
            uiLayout.doMenuAction("consumer", "cadd.xhtml?acct=" + selectedAccount.getAcctnum().toString());
         }
      }
      catch (Exception ex)
      {
      }

   }


   public String doManagedAction()
   {
      try
      {
         if (getSelectedAccount() == null)
         {
            return "failed";
         }

         if (getSelectedAccount().getAcctStatus().equals("Active"))
         {
            uiLayout.doMenuAction("consumer", "overview.xhtml?acct=" + selectedAccount.getAcctnum().toString());
         }
         else {
            if (getSelectedAccount().getAdvisor_priviledge().equalsIgnoreCase("V")) {
               loadRiskData(getSelectedAccount().getAcctnum());
               uiLayout.doMenuAction("advisor", "vwProfile.xhtml");
            }
            else {
               uiLayout.doMenuAction("consumer", "cadd.xhtml?acct=" + selectedAccount.getAcctnum().toString());
            }
         }
      }
      catch (Exception ex)
      {
         return ("failed");
      }

      return ("success");
   }

   public String doDelete(AccountData selected)
   {
      try
      {
         if (selected == null)
         {
            return "failed";
         }

         if (selected.getAccttype().equals("Active"))
         {
            FacesContext.getCurrentInstance().addMessage(null,
                                                         new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                                          "",
                                                                          "Can not delete ACTIVE account.  Please close the account."));
         }
         else
         {
            advisorSaveDataDAO.deleteUserAccount(selected.getAcctnum());
            filterData();
         }

      }
      catch (Exception ex)
      {
         return ("failed");
      }

      return ("success");
   }

   public String doDelete()
   {
      try{
         if (selectedDataList == null)
         {
            return "failed";
         }
         for (AccountData data: selectedDataList) {
            if (data.getAccttype()!=null && data.getAccttype().equals("Active"))
            {
               FacesContext.getCurrentInstance().addMessage(null,
                                                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                                             "",
                                                                             "Can not delete ACTIVE account.  Please close the account."));
            }
            else
            {
               advisorSaveDataDAO.deleteUserAccount(data.getAcctnum());
               filterData();
            }
         }
      }
      catch (Exception ex)
      {
         return ("failed");
      }

      return ("success");
   }

   public int getRowKey()
   {
      return this.hashCode();
   }

   public void setRowKey(int rowKey)
   {
      this.rowKey = rowKey;
   }

   public int rowKey(){

      return this.hashCode();
   }


}
