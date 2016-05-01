package com.invessence.bean.advisor;

import java.io.*;
import java.util.*;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.constant.Const;
import com.invessence.dao.consumer.ConsumerListDataDAO;
import com.invessence.data.consumer.ConsumerData;
import com.invessence.util.WebUtil;

@ManagedBean(name = "manageAdvisorBean")
@SessionScoped
public class ManageAdvisorBean implements Serializable
{
   private String filter;
   private String sort;
   private List<ConsumerData> consumerData;

   @ManagedProperty("#{webutil}")
   private WebUtil webutil;
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

   public String getFilter()
   {
      return filter;
   }

   public void setFilter(String filter)
   {
      this.filter = filter;
   }

   public String getSort()
   {
      return sort;
   }

   public void setSort(String sort)
   {
      this.sort = sort;
   }

   public List<ConsumerData> getConsumerData()
   {
      return consumerData;
   }

   public void setConsumerData(List<ConsumerData> consumerData)
   {
      this.consumerData = consumerData;
   }

   public void preRenderView()
   {

      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            if (webutil.validatePriviledge(Const.WEB_ADVISOR)) {
               loadData();
            }
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   private void loadData() {
      try {
         // consumerData = new ArrayList<ConsumerData>();
         consumerData = listDAO.getClientProfileData(webutil.getLogonid(), getFilter(), null);
         sortData();
      }
      catch (Exception ex) {
      }
   }

   private void sortData() {
      try {
         if (consumerData != null) {
            if (sort == null || sort.equalsIgnoreCase("1")) {
               Collections.sort(consumerData, ConsumerData.ConsumerDataDateOpenedComparator);
            }
            if (sort != null) {
               Collections.sort(consumerData);
            }
         }
      }
      catch (Exception ex) {
      }
   }

}