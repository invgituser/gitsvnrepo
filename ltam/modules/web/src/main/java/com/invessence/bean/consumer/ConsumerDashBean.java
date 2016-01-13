package com.invessence.bean.consumer;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.constant.Const;
import com.invessence.dao.consumer.*;
import com.invessence.data.consumer.DashboardData;
import com.invessence.util.*;

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

   private List<DashboardData> dashboardList;

   private DashboardData selectedAccount;

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
            if (webutil.validatePriviledge(Const.ROLE_USER)) {
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

/*
   @PostConstruct
   public void init()
   {
      Long logonid;
      String fetchStatus;
      try
      {
         if (webutil.validatePriviledge(Const.ROLE_USER)) {
            logonid = webutil.getLogonid();

            if (logonid != null)
               collectData(logonid);
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }
*/

   public String getLoggedUserName() {
      return webutil.getFullname();
   }

   public void collectData(Long logonid)
   {
      dashboardList = listDAO.getClientProfileData(logonid);
    }


   public List<DashboardData> getDashboardList()
   {
      return dashboardList;
   }

   public DashboardData getSelectedAccount()
   {
      return selectedAccount;
   }

   public void setSelectedAccount(DashboardData selectedAccount)
   {
      this.selectedAccount = selectedAccount;
   }

   public String doSelectedAction()
   {
      String whichXML;
      Map<String,String> obj = new HashMap<String, String>();
      try
      {
         if (getSelectedAccount().getManaged() == null || getSelectedAccount().getManaged().isEmpty())
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

}
