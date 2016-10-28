package com.invessence.web.bean.consumer.ltam;

import java.io.Serializable;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.util.*;
import com.invessence.web.constant.*;
import com.invessence.web.dao.consumer.ConsumerListDataDAO;
import com.invessence.web.data.common.CustomerData;
import com.invessence.web.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 1/3/15
 * Time: 6:52 PM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "cdash")
@SessionScoped
public class ConsumerDashBean implements Serializable
{
   private static final long serialVersionUID = 1001L;

   @ManagedProperty("#{uiLayout}")
   private UILayout uiLayout;
   public void setUiLayout(UILayout uiLayout)
   {
      this.uiLayout = uiLayout;
   }

   @ManagedProperty("#{webutil}")
   private WebUtil webutil;
   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   private List<CustomerData> dashboardList;

   private CustomerData selectedAccount;

   @ManagedProperty("#{consumerListDataDAO}")
   private ConsumerListDataDAO listDAO;
   public void setListDAO(ConsumerListDataDAO listDAO)
   {
      this.listDAO = listDAO;
   }
   public void preRenderView()
   {

      Long logonid;
      String fetchStatus;
      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            if (webutil.validatePriviledge(WebConst.WEB_USER)) {
               logonid = webutil.getLogonid();

               if (logonid != null)
                  collectData(logonid);
            }
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   public String getLoggedUserName() {
      return webutil.getFullname();
   }

   public void collectData(Long logonid)
   {
      dashboardList = listDAO.getClientProfileList(logonid, null, null);
    }


   public List<CustomerData> getDashboardList()
   {
      return dashboardList;
   }

   public CustomerData getSelectedAccount()
   {
      return selectedAccount;
   }

   public void setSelectedAccount(CustomerData selectedAccount)
   {
      this.selectedAccount = selectedAccount;
   }

   public String doSelectedAction()
   {
      String whichXML;
      Map<String,String> obj = new HashMap<String, String>();
      try
      {
         if (getSelectedAccount().getManaged() == null)
         {
            whichXML = "/start.xhtml";
            obj.put("acct", selectedAccount.getAcctnum().toString());
         }
         else {
            whichXML = "/common/overview.xhtml";
            obj.put("acct", selectedAccount.getAcctnum().toString());
         }
         webutil.redirect(whichXML, obj);
      }
      catch (Exception ex)
      {
         return ("failed");
      }

      return ("success");
   }

   public void doAction(String action)
   {
      String whichXML;
      FacesMessage  message;
      Map<String,String> obj = new HashMap<String, String>();
      try
      {
         if (action != null) {
            if (action.equalsIgnoreCase("N")) {
               obj.put("act", "N");
               whichXML = "/pages/consumer/cedit.xhtml";
            }
            else {
               obj.put("acctnum", selectedAccount.getAcctnum().toString());
               whichXML = "/pages/consumer/overview.xhtml?";

               if (action.equalsIgnoreCase("E")) {
                  obj.put("act", "E");
                  whichXML = "/pages/consumer/cedit.xhtml";
               }

               if (action.equalsIgnoreCase("A")) {
                  whichXML = "/pages/consumer/fund.xhtml";
               }

               if (action.equalsIgnoreCase("C")) {
                  whichXML = "/pages/consumer/address.xhtml";
               }

            }
            webutil.redirect(whichXML, obj);
         }
         else {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid Action.", "Invalid Action.");
            FacesContext.getCurrentInstance().addMessage(null, message);

         }
      }
      catch (Exception ex)
      {
         message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Exception raised, contact support.", "Exception raised, contact support.");
         FacesContext.getCurrentInstance().addMessage(null, message);
      }

   }
}
