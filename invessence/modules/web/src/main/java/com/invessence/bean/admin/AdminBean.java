package com.invessence.bean.admin;

import java.io.*;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.data.admin.*;
import com.invessence.util.WebUtil;


import com.invessence.dao.common.AdminDAO;

@ManagedBean(name = "adminbean")
@SessionScoped
public class AdminBean extends AdminData implements Serializable
{
   private static final long serialVersionUID = -8009L;

   @ManagedProperty("#{adminDAO}")
   private AdminDAO adminDAO;

   //private Map<String,List<TradeDetails>> tradesDetails = new HashMap<String,List<TradeDetails>>();
   @ManagedProperty("#{webutil}")
   private WebUtil webutil;
   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   public AdminDAO getAdminDAO()
   {
      return adminDAO;
   }

   public void setAdminDAO(AdminDAO adminDAO)
   {
      this.adminDAO = adminDAO;
   }

   @PostConstruct
   public void init()
   {
      Long logonid;
      try
      {
         if (webutil.validatePriviledge("ADMIN")) {
            collectData();
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   private void collectData()
   {
      String IBfilter = "All";
      try
      {

         IBfilter = getFilter();

         if (IBfilter == null || IBfilter.isEmpty())
         {
            IBfilter = "All";
         }
         setIbList(adminDAO.collectIBData(IBfilter));
         setInvessenceList(adminDAO.collectInvData(IBfilter));
      }
      catch (Exception ex)
      {
         System.out.println("Error in fetching list for admin match function");
         ex.printStackTrace();
      }
   }

   public void refreshButton()
   {
      try
      {
         collectData();
      }
      catch (Exception ex)
      {
         System.out.println("Refresh failed:" + ex.getMessage());
      }
   }

   public void saveMatch()
   {
      adminDAO.addDelIBPair("A", getAcctnum(), getIBStatus(), getClientAccountID());
      String msg = "IB Account# (" + getClientAccountID() + ") and Invessence Account# (" + getAcctnum() + ") are linked.";
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));

   }

}