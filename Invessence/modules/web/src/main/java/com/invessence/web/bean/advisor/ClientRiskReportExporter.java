package com.invessence.web.bean.advisor;

import java.io.Serializable;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.web.constant.WebConst;
import com.invessence.web.dao.advisor.*;
import com.invessence.web.dao.consumer.ConsumerListDataDAO;
import com.invessence.web.data.advisor.AdvisorDashData;
import com.invessence.web.data.common.AccountData;
import com.invessence.web.data.consumer.RiskCalculator;
import com.invessence.web.util.*;


@ManagedBean(name = "clientRiskReportExporter")
@SessionScoped
public class ClientRiskReportExporter implements Serializable
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

   @ManagedProperty("#{emailMessage}")
   private WebMessage messageSource;

   private Long logonid;

   private String filteredClient;
   private String errorMessage;
   private String filtereMenuList = "All";
   private Integer filteredBydate=0;

   public String getErrorMessage()
   {
      return errorMessage;
   }

   public void setErrorMessage(String errorMessage)
   {
      this.errorMessage = errorMessage;
   }

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

               logonid = webutil.getLogonid();

               if (logonid != null)
               {
                     reloadData();
               }
            }
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }


  public void reloadData() {
   }

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

   public void collectData(Long logonid)
   {
      try
      {
         String filterByAmount = "";
         if (logonid != null)
         {
            if(filteredBydate == 0){
               filtereMenuList = "All";
            }
            if(getFilteredClient().equalsIgnoreCase("VA")){
               filteredBydate = 1;
               filtereMenuList = "Today";
               filteredClient = "V";
            }
            if(getFilteredClient().equalsIgnoreCase("AA")){
               filterByAmount = "AMOUNT";
               filteredClient = "A";
            }else if(getFilteredClient().equalsIgnoreCase("A")){
               filterByAmount = "Acctnum";
               filteredClient = "A";
            }
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
      // consumerListDataDAO.getRiskProfileData(acctnum, riskCalculator);
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

   public String getFilteredClient()
   {
      return filteredClient;
   }

   public void setFilteredClient(String filteredClient)
   {
      this.filteredClient = filteredClient;
   }


   public void showList()
   {
      try {
         uiLayout.doMenuAction("advisor", "alist.xhtml?action=" + filteredClient);
      }
      catch (Exception ex) {

      }
   }



   public String doManagedAction()
   {
      try
      {
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
   public int rowKey(){

      return this.hashCode();
   }
}
