package com.invessence.bean.consumer;

import java.io.Serializable;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import com.invessence.LTAMOptimizer;
import com.invessence.bean.ltam.LTAMCharts;
import com.invessence.constant.Const;
import com.invessence.converter.SQLData;
import com.invessence.dao.common.UserInfoDAO;
import com.invessence.dao.consumer.*;
import com.invessence.dao.ltam.*;
import com.invessence.data.LTAMTheme;
import com.invessence.data.common.AccountData;
import com.invessence.data.ltam.LTAMCustomerData;
import com.invessence.util.Impl.PagesImpl;
import com.invessence.util.WebUtil;
import org.primefaces.event.*;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 2/4/15
 * Time: 1:18 PM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "ctob")
@SessionScoped
public class CTOBean implements Serializable
{

   private Long beanacctnum;
   private Long beanlogonid;
   private List<AccountData> accountDataList = null;
   private AccountData accountdata = new AccountData();

   @Autowired
   UserInfoDAO userInfoDAO;
   @Autowired
   ConsumerSaveDAO consumerSaveDAO;

   @Autowired
   WebUtil webutil;

   public Long getBeanacctnum()
   {
      return beanacctnum;
   }

   public void setBeanacctnum(Long beanacctnum)
   {
      this.beanacctnum = beanacctnum;
   }

   public Long getBeanlogonid()
   {
      return beanlogonid;
   }

   public void setBeanlogonid(Long beanlogonid)
   {
      this.beanlogonid = beanlogonid;
   }

   public AccountData getAccountdata()
   {
      return accountdata;
   }

   public void setAccountdata(AccountData accountdata)
   {
      this.accountdata = accountdata;
   }

   public void preRenderView()
   {
      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            // If beanacctnum is null or empty, then it must be a visitor
               if (webutil.validatePriviledge(Const.WEB_USER)) {
                  Long logonid = webutil.getLogonid();
                  collectAccountData(logonid, beanacctnum);
               }
         }
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public void collectAccountData(Long logonid, Long beanacctnum)
   {
      try {
         accountDataList = userInfoDAO.getListofAccount(logonid, beanacctnum);
         if (accountDataList != null) {
            accountdata = accountDataList.get(0);
         }
      }
      catch (Exception ex) {
         webutil.redirecttoMessagePage("Warning", "",
                                       "There is no data associated with this account number. <br/>" +
                                          "Please contact support."
         );
      }
   }

}

