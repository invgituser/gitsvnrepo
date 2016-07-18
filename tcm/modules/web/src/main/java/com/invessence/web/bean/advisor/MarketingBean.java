package com.invessence.web.bean.advisor;

import java.io.Serializable;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.web.dao.common.CommonDAO;
import com.invessence.web.dao.consumer.ConsumerListDataDAO;
import com.invessence.web.data.common.CustomerData;
import com.invessence.web.util.*;

@ManagedBean(name = "marketingBean")
@SessionScoped
public class MarketingBean implements Serializable
{
   private static final long serialVersionUID = 100080L;
   private ArrayList <CustomerData> fetchedDataList = new ArrayList<CustomerData>();
   private ArrayList <CustomerData> displayDataList = new ArrayList<CustomerData>();
   private CustomerData selectedAccount;
   private String selectedFilter;

   @ManagedProperty("#{webutil}")
   private WebUtil webutil;
   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   private final static String[] tmpStatus;

   static {
      tmpStatus = new String[2];
      tmpStatus[0] = "Pending";
      tmpStatus[1] = "Active";
   }


   @ManagedProperty("#{consumerListDataDAO}")
   private ConsumerListDataDAO cldDAO;

   @ManagedProperty("#{emailMessage}")
   private WebMessage messageText;

   public MarketingBean() {
   }

   public void setMessageText(WebMessage messageText)
   {
      this.messageText = messageText;
   }

   public void setCldDAO(ConsumerListDataDAO cldDAO)
   {
      this.cldDAO = cldDAO;
   }

   public List<String> getAcctStatus()
   {
      return Arrays.asList(tmpStatus);
   }

   @PostConstruct
   public void init()
   {
      Long logonid;
      try
      {
         if (webutil.validatePriviledge("SALES")) {
            logonid = webutil.getLogonid();

            if (logonid != null)
            {
               if (fetchedDataList == null || fetchedDataList.size() == 0) {
                  collectData();
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


   public void collectData()
   {
      try
      {
         if (webutil.getLogonid() != null)
         {
            if (fetchedDataList == null)
               fetchedDataList = new ArrayList<CustomerData>();
            fetchedDataList.clear();
            fetchedDataList = cldDAO.getClientProfileData(webutil.getLogonid(), null, null);
/*
            for (int i=0; i < accountDataList.size() ; i++)
                 setAcctStatus(accountDataList.get(i).getAcctStatus());
*/
         }
      }
      catch (Exception ex)
      {
         String msg="Error in Marketing collecting data:" + ex.getMessage();
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
      }
   }

   public void filterData() {

      try
      {
         if (getSelectedFilter() == null || getSelectedFilter().length() == 0)
            displayDataList = fetchedDataList;
         else {
            displayDataList.clear();
            for (int i=0; i < getFetchedDataList().size(); i++) {
               if (getFetchedDataList().get(i).getAcctstatus().startsWith(getSelectedFilter()))
                  displayDataList.add(getFetchedDataList().get(i));
            }
         }
      }
      catch (Exception ex)
      {
         String msg="Error in Marketing Filtering data:" + ex.getMessage();
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
      }

   }

   public CustomerData getSelectedAccount()
   {
      return selectedAccount;
   }

   public void setSelectedAccount(CustomerData selectedAccount)
   {
      this.selectedAccount = selectedAccount;
   }

   public ArrayList<CustomerData> getFetchedDataList()
   {
      return fetchedDataList;
   }

   public void setFetchedDataList(ArrayList<CustomerData> fetchedDataList)
   {
      this.fetchedDataList = fetchedDataList;
   }

   public ArrayList<CustomerData> getDisplayDataList()
   {
      return displayDataList;
   }

   public void setDisplayDataList(ArrayList<CustomerData> displayDataList)
   {
      this.displayDataList = displayDataList;
   }

   public String getSelectedFilter()
   {
      return selectedFilter;
   }

   public void setSelectedFilter(String selectedFilter)
   {
      this.selectedFilter = selectedFilter;
   }

   public void refreshButton()
   {
      collectData();
      filterData();
   }

   public String doReload()
   {
      try {
         collectData();
         filterData();
      }
      catch (Exception ex) {
         return "failed";
      }
      return "success";
   }
}