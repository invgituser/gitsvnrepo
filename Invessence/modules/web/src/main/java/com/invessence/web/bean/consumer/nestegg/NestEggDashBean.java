package com.invessence.web.bean.consumer.nestegg;

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

@ManagedBean(name = "neggdash")
@SessionScoped
public class NestEggDashBean implements Serializable
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

   public String getLoggedUserName() {
      return webutil.getLastFirstName();
   }

   public void collectData(Long logonid)
   {
       manageAccountList = listDAO.getClientProfileList(logonid, null, null, webutil.getUserInfoData().getAdvisor(), webutil.getUserInfoData().getRep() );
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

   public String showPosition()
   {
      String whichXML;
      try
      {
            uiLayout.doMenuAction("consumer", "overview.xhtml?acct=" + selectedAccount.getAcctnum().toString());
      }
      catch (Exception ex)
      {
         return ("failed");
      }

      return ("success");
   }
   public String doEditAction()
   {
      String whichXML;
      try
      {
         uiLayout.doMenuAction("custody", "cedit.xhtml?app=E&acct=" + selectedAccount.getAcctnum().toString());
      }
      catch (Exception ex)
      {
         return ("failed");
      }

      return ("success");
   }
   public String doPortfolioSelectedAction()
   {
      String whichXML;
      try
      {
         if (getSelectedAccount().isUnopened())
         {
            uiLayout.doMenuAction("consumer", "cadd.xhtml?app=E&acct=" + selectedAccount.getAcctnum().toString());
         }
         else
         {
            uiLayout.doMenuAction("consumer", "portfolioedit.xhtml?app=E&acct=" + selectedAccount.getAcctnum().toString());
         }
      }
      catch (Exception ex)
      {
         return ("failed");
      }

      return ("success");
   }


   public String doFundingAction()
   {
      String whichXML;
      try
      {
         uiLayout.doMenuAction("custody", "editfunding.xhtml?app=E&acct=" + selectedAccount.getAcctnum().toString());
      }
      catch (Exception ex)
      {
         return ("failed");
      }

      return ("success");
   }



   public String doOptionMenuAction()
   {
      String whichXML;
      try
      {
         System.out.println("Inside doOptionMenuAction >> ");
         System.out.println("Inside doOptionMenuAction >> ");

         uiLayout.doMenuAction("custody", "editaddress.xhtml?app=E&acct=" + selectedAccount.getAcctnum().toString());
      }
      catch (Exception ex)
      {
         return ("failed");
      }

      return ("success");
   }
}
