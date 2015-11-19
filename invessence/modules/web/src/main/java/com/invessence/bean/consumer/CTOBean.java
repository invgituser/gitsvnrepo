package com.invessence.bean.consumer;

import java.io.Serializable;
import java.util.*;
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
import org.primefaces.context.RequestContext;


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
   private Integer[] disclosure = new Integer[6];
   private Integer allApproved = 0;
   private Integer whichDisclosure;

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
            pagemanager = new PagesImpl(5);
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

   public void setDisclosure(Integer pos) {
      if ((pos != null) && (pos > 0) && (pos < disclosure.length)) {
         if (pos == 1) {
            popupDialog(pos);
         }
         else {

         }

      }
   }

   public Boolean getAllApproved() {
      if (allApproved >= 5)
         return true;
      return false;
   }

   public Boolean disclosure(Integer pos) {
      if ((pos != null) && (pos > 0) && (pos < disclosure.length)) {
         if (disclosure[pos] != null && disclosure[pos] == 1)
            return true;
      }
      return false;
   }

   public void accept() {
      if ((whichDisclosure != null) && (whichDisclosure > 0) && (whichDisclosure < disclosure.length)) {
         if (disclosure[whichDisclosure] > 0)
            allApproved++;
         disclosure[whichDisclosure] = 1;
      }
   }

   public void accept(Integer pos) {
      if ((pos != null) && (pos > 0) && (pos < disclosure.length)) {
         if (disclosure[pos] > 0)
            allApproved++;
         disclosure[pos] = 1;
      }
   }

   public void popupDialog(Integer whichone) {
      whichDisclosure = whichone;
      Map<String,Object> options = new HashMap<String, Object>();
      options.put("resizable", false);
      options.put("draggable", false);
      options.put("modal", true);
      RequestContext.getCurrentInstance().openDialog("/pages/consumer/cto/disclosure", options, null);
   }


}

