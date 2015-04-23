package com.invessence.bean.consumer;

import java.io.Serializable;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.bean.common.PositionBean;
import com.invessence.constant.Const;
import com.invessence.dao.ManageAccountDAO;
import com.invessence.dao.consumer.ConsumerListDataDAO;
import com.invessence.data.ManageAccount;
import com.invessence.data.common.ManageGoals;
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
public class ConsumerManageBean implements Serializable
{
   private static final long serialVersionUID = 1001L;

   WebUtil webutil = new WebUtil();
   Menu menu = new Menu();

   private List<ManageGoals> manageAccountList;

   private ManageGoals selectedAccount;

   private String selected;
   private Long acctnum;
   private Long logonid;
   private String actionSelected = "-";
   private Boolean dataCollected = false;
   private Boolean anyPending = false;
   private String greetings;

   @ManagedProperty("#{consumerListDataDAO}")
   private ConsumerListDataDAO manageAccountDAO;

   @ManagedProperty("#{emailMessage}")
   private EmailMessage messageSource;

   static Map<String, String> pendingActions = new LinkedHashMap<String, String>();

   static
   {
      // pendingActions.put("Confirm & Execute Strategy", "approvePortfolio.xhtml");
      pendingActions.put("Edit Investment Profile", "/consumer/add.xhtml");
      pendingActions.put("Account Management", "https://www.clientam.com/Universal/servlet/AccountAccess.Login?partnerID=Invessence");
   }

   static Map<String, String> activeActions = new LinkedHashMap<String, String>();

   static
   {
      //activeActions.put("Adjust Risk", "reviseRisk.xhtml");
      activeActions.put("Position", "/common/overview.xhtml");
      //activeActions.put("Reports", "reports.xhtml");
      activeActions.put("Account Management", "https://www.clientam.com/Universal/servlet/AccountAccess.Login?partnerID=Invessence");
   }

   public Boolean getAnyPending()
   {
      return anyPending;
   }

   public void setAnyPending(Boolean anyPending)
   {
      this.anyPending = anyPending;
   }

   public EmailMessage getMessageSource()
   {
      return messageSource;
   }

   public void setMessageSource(EmailMessage messageSource)
   {
      this.messageSource = messageSource;
   }

   public void preRenderView()
   {

      try {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            if (webutil.validatePriviledge(Const.ROLE_USER)) {
               logonid = webutil.getLogonid();

               if (logonid != null) {
                  String fetchStatus = collectData(logonid, null);
               }
            }
         }

      }
      catch (Exception e)
      {
      }
   }

   @PostConstruct
   public void init()
   {
      Long logonid;
      String fetchStatus;
      try
      {
         if (webutil.validatePriviledge(Const.ROLE_USER)) {
            logonid = webutil.getLogonid();
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   public void setManageAccountDAO(ConsumerListDataDAO manageAccountDAO)
   {
      this.manageAccountDAO = manageAccountDAO;
   }


/*
   public boolean isPostback()
   {
      FacesContext context = FacesContext.getCurrentInstance();
      return context.getRenderKit().getResponseStateManager().isPostback(context);
   }
*/

   public String collectData(Long acctnum, String userName)
   {
      try
      {
            manageAccountList = manageAccountDAO.getClientProfileData(logonid, null);
            for (int i = 0; i < manageAccountList.size(); i++)
            {
               dataCollected = true;
               ManageGoals myaccount = manageAccountList.get(i);
               Map<String, String> addAction;
               String mylogonid = myaccount.getLogonid().toString();
               String myacctnum = myaccount.getAcctnum().toString();
/*
               if (myaccount.getManaged())
               {
                  addAction = buildDropdownMenu(activeActions, mylogonid, myacctnum);
                  myaccount.setAction("View");
                  myaccount.setChoices(addAction);
               }
               else
               {
                  anyPending = true;
                  addAction = buildDropdownMenu(pendingActions, mylogonid, myacctnum);
                  myaccount.setAction("Edit");
                  myaccount.setChoices(addAction);
               }
*/
            }

         String welcomeText = "";
         try
         {
            if (!dataCollected)
            {
               welcomeText = messageSource.buildInternalMessage("welcome.firsttime", new Object[]{});
            }
            else
            {
               if (anyPending)
               {
                  welcomeText = messageSource.buildInternalMessage("welcome.pending", new Object[]{});
               }
               else
               {
                  welcomeText = messageSource.buildInternalMessage("welcome.general", new Object[]{});
               }
            }
         }
         catch (Exception ex)
         {
            welcomeText = "Welcome! Listed below are list of accounts, you have created. You can view/edit \n" +
               "these accounts. Please note, you'll not be able to add additional account until the pending \n" +
               "account is approved and funded.";
         }
         setGreetings(welcomeText);

      }
      catch (Exception ex)
      {
         System.out.println("Error in collecting data on ManageAccount Page:" + ex.getMessage());
         return "error";
      }
      return null;
   }

   private Map<String, String> buildDropdownMenu(Map<String, String> list, String logonid, String acctnum)
   {
      Map<String, String> dropdownMenu = new LinkedHashMap<String, String>();
      String key, val;
      String delimiter = "?";
      try
      {
         Iterator it = list.entrySet().iterator();
         while (it.hasNext())
         {
            Map.Entry pairs = (Map.Entry) it.next();
            key = pairs.getKey().toString();
            val = pairs.getValue().toString();
            // add account# and login if it does not exists. Exclude URL that starts with http
            if (!val.startsWith("http"))
            {
               // If there is already an arg, then add it
               if (val.contains("?"))
               {
                  delimiter = "&";
               }

               val = val + delimiter +
                  Const.LOGONID_PARAM + "=" + logonid + "&" +
                  Const.ACCTNO_PARAM + "=" + acctnum;
            }
            else
            {
               val = pairs.getValue().toString();
            }

            dropdownMenu.put(key, val);
         }

      }
      catch (Exception ex)
      {
         System.out.println("Error in building Dropdown List:" + ex.getMessage());
      }
      return dropdownMenu;
   }

   public List<ManageGoals> getManageAccountList()
   {
      return manageAccountList;
   }

/*
   public void setManageAccountList(List<ManageAccount> manageAccountList) {

        String userName;
        try {
            HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            userName = req.getRemoteUser();
            manageAccountList = manageAccountDAO.getManageAccount(null, userName);
        } catch (Exception ex) {
            System.out.println("Error in setManageAccountList data from DB :" + ex.getMessage());
        }
    }
   */

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

   public void setSelected(String selected)
   {
      this.selected = selected;
   }

   public String getSelected()
   {
      if (selected != null)
      {
         return selected;
      }
      else
      {
         return "0";
      }
   }

   public ManageGoals getSelectedAccount()
   {
      return selectedAccount;
   }

   public String getGreetings()
   {
      return greetings;
   }

   public void setGreetings(String greetings)
   {
      this.greetings = greetings;
   }

   public void setSelectedAccount(ManageGoals selectedAccount)
   {
      this.selectedAccount = selectedAccount;
      setSelected(selectedAccount.getAcctnum().toString());
      setAcctnum(selectedAccount.getAcctnum());
      setLogonid(selectedAccount.getLogonid());
   }

   public String getActionSelected()
   {
      return "-";
   }

   public void setActionSelected(String actionSelected)
   {
      this.actionSelected = actionSelected;
   }

   public String doSelectedAction()
   {
      String whichXML;

      try
      {
         if (selectedAccount == null)
         {
            return "failed";
         }

         if (selectedAccount.getManaged())
         {
            whichXML = "/common/overview.xhtml?acct="+selectedAccount.getAcctnum().toString();
         }
         else
         {
            whichXML = "/consumer/add.xhtml?acct="+selectedAccount.getAcctnum().toString();
         }


         menu.doMenuAction(whichXML);
      }
      catch (Exception ex)
      {
         return ("failed");
      }

      return ("success");
   }

}
