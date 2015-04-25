package com.invessence.bean.consumer;

import java.io.Serializable;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.bean.*;

import com.invessence.constant.Const;
import com.invessence.dao.consumer.*;
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
public class ConsumerDashBean implements Serializable
{
   private static final long serialVersionUID = 1001L;

   WebUtil webutil = new WebUtil();
   Menu menu = new Menu();

   private List<ManageGoals> manageAccountList;

   private ManageGoals selectedAccount;

   @ManagedProperty("#{consumerListDataDAO}")
   private ConsumerListDataDAO listDAO;
   public void setListDAO(ConsumerListDataDAO listDAO)
   {
      this.listDAO = listDAO;
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

            if (logonid != null)
               collectData(logonid);
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
       manageAccountList = listDAO.getClientProfileData(logonid,null);
    }


   public List<ManageGoals> getManageAccountList()
   {
      return manageAccountList;
   }

   public ManageGoals getSelectedAccount()
   {
      return selectedAccount;
   }

   public void setSelectedAccount(ManageGoals selectedAccount)
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
            whichXML = "/common/overview.xhtml?acct="+selectedAccount.getAcctnum().toString();
         }
         else {
            whichXML = "/consumer/cadd.xhtml?acct="+selectedAccount.getAcctnum().toString();
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
