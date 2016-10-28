package com.invessence.web.bean.consumer.tcm;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.web.constant.WebConst;
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

@ManagedBean(name = "tcmdash")
@SessionScoped
public class TCMDashBean implements Serializable
{
   private static final long serialVersionUID = 1001L;

/*
   @Autowired
   private WebUtil webutil;
   @Autowired
   private ConsumerListDataDAO listDAO;
*/
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

   @ManagedProperty("#{consumerListDataDAO}")
   private ConsumerListDataDAO listDAO;
   public void setListDAO(ConsumerListDataDAO listDAO)
   {
      this.listDAO = listDAO;
   }

   private ArrayList<CustomerData> manageAccountList;

   private CustomerData selectedAccount;


   public void preRenderView()
   {

      Long logonid;
      String fetchStatus;
      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            if (webutil.validatePriviledge(WebConst.ROLE_USER)) {
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
      return webutil.getLastFirstName();
   }

   public void collectData(Long logonid)
   {
       manageAccountList = listDAO.getClientProfileList(logonid,null, null);
    }


   public List<CustomerData> getManageAccountList()
   {
      return manageAccountList;
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
      try
      {
         if (getSelectedAccount().getManaged())
         {
            uiLayout.doMenuAction("consumer", "overview.xhtml?acct=" + selectedAccount.getAcctnum().toString());
         }
         else {
            uiLayout.doMenuAction("consumer", "cadd.xhtml?app=E&acct=" + selectedAccount.getAcctnum().toString());
         }
      }
      catch (Exception ex)
      {
         return ("failed");
      }

      return ("success");
   }

}
