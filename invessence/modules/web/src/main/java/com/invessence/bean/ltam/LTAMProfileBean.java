package com.invessence.bean.ltam;

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
import com.invessence.data.ltam.LTAMCustomerData;
import com.invessence.util.EmailMessage;
import com.invessence.util.Impl.PagesImpl;
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
public class LTAMProfileBean extends LTAMCustomerData implements Serializable
{
   private Long  beanAcctnum;
   private PagesImpl pagemanager;
   private Integer ltammenu;
   private LTAMCharts charts;

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

   public PagesImpl getPagemanager()
   {
      return pagemanager;
   }

   public Boolean getDisplayMeter()
   {
      if (pagemanager != null && pagemanager.getPage() > 0)
         return true;
      else
         return false;
   }

   public LTAMCharts getCharts()
   {
      return charts;
   }

   public Integer getLtammenu()
   {
      switch (pagemanager.getPage()) {
         case 0:
         case 1:
         case 2:
         case 3:
            ltammenu = pagemanager.getPage();
            break;
         case 4:
         case 5:
         case 6:
         case 7:
         case 8:
         case 9:
         case 10:
            ltammenu = 4;
            break;
         default:
            ltammenu = 0;
            break;
      }
      return ltammenu;
   }

   public void preRenderView()
   {
      try {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            charts = new LTAMCharts();
            pagemanager = new PagesImpl(10);
         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

}

