package com.invessence.bean.consumer;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.constant.USMaps;
import com.invessence.converter.SQLData;
import com.invessence.dao.consumer.*;
import com.invessence.data.common.CustomerData;
import com.invessence.data.consumer.CTO.InvestorData;
import com.invessence.util.EmailMessage;
import com.invessence.util.Impl.PagesImpl;


/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 2/4/15
 * Time: 1:18 PM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "ctobean")
@SessionScoped
public class CTOBean extends InvestorData implements Serializable
{
   private Long  beanAcctnum;
   private PagesImpl pagemanager;

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

   public void preRenderView()
   {
      try {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            pagemanager = new PagesImpl(4);
         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   public void prevPage()
   {
      pagemanager.prevPage();
   }

   public void nextPage()
   {
      pagemanager.nextPage();
   }


}

