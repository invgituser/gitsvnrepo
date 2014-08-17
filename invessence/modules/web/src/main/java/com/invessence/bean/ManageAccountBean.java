package com.invessence.bean;

import java.io.Serializable;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;

import com.invessence.constant.Const;
import com.invessence.dao.ManageAccountDAO;
import com.invessence.data.ManageAccount;
import com.invessence.util.EmailMessage;
import org.primefaces.event.SelectEvent;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

import static javax.faces.context.FacesContext.getCurrentInstance;


public class ManageAccountBean implements Serializable
{
   private static final long serialVersionUID = 1001L;
   private List<ManageAccount> manageAccountList;
   private ManageAccount selectedInvestment;
   private ManageAccountDAO manageAccountDAO;
   private String selected;
   private Long acctnum;
   private Long logonid;
   private String actionSelected = "-";
   private Boolean dataCollected = false;
   private Boolean anyPending = false;
   private String greetings;

   ManageGoalsBean managegoals;
   PositionBean positionBean;

   private EmailMessage messageSource;

   static Map<String, String> pendingActions = new LinkedHashMap<String, String>();

   static
   {
      // pendingActions.put("Confirm & Execute Strategy", "approvePortfolio.xhtml");
      pendingActions.put("Edit Investment Profile", "createInvestment.xhtml");
      pendingActions.put("Account Management", "https://www.clientam.com/Universal/servlet/AccountAccess.Login?partnerID=Invessence");
   }

   static Map<String, String> activeActions = new LinkedHashMap<String, String>();

   static
   {
      //activeActions.put("Adjust Risk", "reviseRisk.xhtml");
      activeActions.put("Position", "position.xhtml");
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

   @PostConstruct
   public void init()
   {
      String userName;
      try
      {
         if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(Const.LOGONID_PARAM) == null)
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");

         if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(Const.LOGONID_PARAM) != null)
         {
            setLogonid((Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(Const.LOGONID_PARAM));
            String fetchStatus = collectData(getLogonid(), null);
         }
         else
         {
            HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            if (req.getRemoteUser() != null)
            {
               userName = req.getRemoteUser();
               // System.out.println("USER ID :" + userName);
               String fetchStatus = collectData(null, userName);
            }
            //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Const.LOGONID_PARAM, null);
            //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Const.ACCTNO_PARAM, null);
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   public void setManageAccountDAO(ManageAccountDAO manageAccountDAO)
   {
      this.manageAccountDAO = manageAccountDAO;
   }


   public void setPositionBean(PositionBean positionBean)
   {
      this.positionBean = positionBean;
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
         if (!dataCollected)
         {
            manageAccountList = manageAccountDAO.getManageAccount(acctnum, userName);
            for (int i = 0; i < manageAccountList.size(); i++)
            {
               dataCollected = true;
               ManageAccount myaccount = manageAccountList.get(i);
               Map<String, String> addAction;
               String mylogonid = myaccount.getLogonid().toString();
               String myacctnum = myaccount.getAcctnum().toString();
               if (myaccount.getAcctstate().contains("Active"))
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
            }
         }

         String welcomeText = "";
         try
         {
            if (!dataCollected)
            {
               welcomeText = messageSource.getMessagetext("welcome.firsttime", new Object[]{});
            }
            else
            {
               if (anyPending)
               {
                  welcomeText = messageSource.getMessagetext("welcome.pending", new Object[]{});
               }
               else
               {
                  welcomeText = messageSource.getMessagetext("welcome.general", new Object[]{});
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

   public List<ManageAccount> getManageAccountList()
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

   public ManageAccount getSelectedInvestment()
   {
      return selectedInvestment;
   }

   public String getGreetings()
   {
      return greetings;
   }

   public void setGreetings(String greetings)
   {
      this.greetings = greetings;
   }

   public void setSelectedInvestment(ManageAccount selectedInvestment)
   {
      this.selectedInvestment = selectedInvestment;
      setSelected(selectedInvestment.getAcctnum().toString());
      setAcctnum(selectedInvestment.getAcctnum());
      setLogonid(selectedInvestment.getLogonid());
   }

   public void setManagegoals(ManageGoalsBean managegoals)
   {
      this.managegoals = managegoals;
   }

   public String createNewAcct()
   {
      FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Const.ACCTNO_PARAM, null);
      return ("createAccount.xhtml?faces-redirect=true");
   }

   public void onRowSelect(SelectEvent event)
   {
      this.selectedInvestment = (ManageAccount) event.getObject();
      setAcctnum(selectedInvestment.getAcctnum());
      setLogonid(selectedInvestment.getLogonid());
      FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Const.LOGONID_PARAM, getLogonid());
      FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Const.ACCTNO_PARAM, getAcctnum());
   }

   public String editAccount()
   {
      //if (this.selectedInvestment != null) {
      //  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Const.LOGONID_PARAM, this.selectedInvestment.getLogonid());
      //  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Const.ACCTNO_PARAM, this.selectedInvestment.getAcctnum());
      return ("/createAccount.xhtml?faces-redirect=true");
      //return("success");
      //} else
      //   return "failed";
   }

   public String getActionSelected()
   {
      return "-";
   }

   public void setActionSelected(String actionSelected)
   {
      this.actionSelected = actionSelected;
   }

   public String addPortfolio()
   {
      FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Const.ACCTNO_PARAM, null);
      managegoals.resetGoals();
      try
      {
         FacesContext.getCurrentInstance().getExternalContext().redirect("createInvestment.xhtml");
      }
      catch (Exception ex)
      {
         return ("failed");
      }
      return ("success");
   }

   public String doSelectedAction()
   {
      String whichXML;

      try
      {
         if (selectedInvestment == null)
         {
            return "failed";
         }

         if (selectedInvestment.getAcctstate().equals("Active"))
         {
            whichXML = "position.xhtml";
            positionBean.findPosition(getLogonid(), getAcctnum());
         }
         else
         {
            whichXML = "createInvestment.xhtml";
            managegoals.findGoals(getLogonid(), getAcctnum());
         }


         FacesContext.getCurrentInstance().getExternalContext().redirect(whichXML);
      }
      catch (Exception ex)
      {
         return ("failed");
      }

      return ("success");
   }

   public void selectedActionEvent(ValueChangeEvent event)
   {
      String actionValue;
      String myURL = "";
      String oldValue = null;
      try
      {
         if ((event.getNewValue() == null) || (event.getNewValue().toString().equals("-")))
         {
            return;
         }

         oldValue = "-";
         if (event.getOldValue() != null)
         {
            oldValue = event.getOldValue().toString();
         }

         actionValue = event.getNewValue().toString();
         // This is to ignore all already selected items.
         /*
         if (!actionSelected.equals(actionValue))
         {
            return;
         }
         */

         if (oldValue.equals(actionValue))
         {
            return;
         }

         if ((actionValue != null) && (!actionValue.equals("-")))
         {
            if (actionValue.startsWith("http"))
            {
               FacesContext.getCurrentInstance().getExternalContext().redirect(actionValue);
            }
            else
            {
               String[] urlStr = actionValue.split("\\?", 2); // Split URL and Args
               myURL = urlStr[0];
               if (urlStr.length > 0)
               {
                  String[] tokens = urlStr[1].split("&");
                  for (int i = 0; i < tokens.length; i++)
                  {
                     String[] data = tokens[i].split("=", 2);
                     if (data.length > 1)
                     {
                        if (data[0].equals(Const.ACCTNO_PARAM))
                        {
                           setAcctnum(Long.parseLong(data[1]));
                           FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Const.ACCTNO_PARAM, getAcctnum());
                        }
                        else if (data[0].equals(Const.LOGONID_PARAM))
                        {
                           setLogonid(Long.parseLong(data[1]));
                           FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Const.LOGONID_PARAM, getLogonid());
                        }
                        else
                        {
                           FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(data[0], data[1]);
                        }
                     }
                     else
                     {
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(data[0], "true");
                     }
                  }

                  if (myURL.equals("createInvestment.xhtml"))
                  {
                     managegoals.findGoals(getLogonid(), getAcctnum());
                  }
                  else if (myURL.equals("position.xhtml"))
                  {
                     positionBean.findPosition(getLogonid(), getAcctnum());
                  }

                  FacesContext.getCurrentInstance().getExternalContext().redirect(myURL);
               }

            }

         }
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public String menuSelection(ValueChangeEvent event)
   {
      String actionValue;
      try
      {
         actionValue = event.getNewValue().toString();
         if ((actionValue != null) && (!actionValue.equals("-")))
         {
            return actionValue;
         }
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
         return "";
      }
      return "";
   }
}
