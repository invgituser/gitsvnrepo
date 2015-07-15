package com.invessence.bean.consumer;

import java.io.Serializable;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.*;
import javax.servlet.http.HttpSession;

import com.invessence.constant.*;
import com.invessence.converter.SQLData;
import com.invessence.dao.consumer.*;
import com.invessence.data.common.*;
import com.invessence.util.EmailMessage;
import com.invmodel.Const.InvConst;
import com.invmodel.performance.data.PerformanceData;
import org.primefaces.component.tabview.Tab;
import org.primefaces.context.RequestContext;
import org.primefaces.event.*;


/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 2/4/15
 * Time: 1:18 PM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "ltamprofile")
@SessionScoped
public class LTAMProfileBean extends CustomerData implements Serializable
{
   private Long  beanAcctnum;

   private USMaps usstates = USMaps.getInstance();

   @ManagedProperty("#{consumerListDataDAO}")
   private ConsumerListDataDAO listDAO;
   public void setListDAO(ConsumerListDataDAO listDAO)
   {
      this.listDAO = listDAO;
   }

   @ManagedProperty("#{consumerSaveDataDAO}")
   private ConsumerSaveDataDAO saveDAO;
   public void setSaveDAO(ConsumerSaveDataDAO saveDAO)
   {
      this.saveDAO = saveDAO;
   }

   @ManagedProperty("#{emailMessage}")
   private EmailMessage messageText;
   public void setMessageText(EmailMessage messageText)
   {
      this.messageText = messageText;
   }

   public Long getBeanAcctnum()
   {
      return beanAcctnum;
   }

   public void setBeanAcctnum(Long beanAcctnum)
   {
      SQLData converter = new SQLData();
      this.beanAcctnum = converter.getLongData(beanAcctnum);
   }

   public USMaps getUsstates()
   {
      return usstates;
   }

   private Integer pTab = 0;


   public Integer getpTab()
   {
      return pTab;
   }

   public void setpTab(Integer pTab)
   {
      this.pTab = pTab;
   }

   public String getEnableNextButton() {
      if (pTab == 3)
         return "false";
      return "true";
   }

   public String getEnablePrevButton() {
      if (pTab == 0)
         return "false";
      return "true";
   }

   public void gotoPrevTab() {
       pTab--;
   }

   public void gotoNextTab() {
            pTab ++;
   }

   public void preRenderView()
   {

      try {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            pTab = 0;
         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }
}

