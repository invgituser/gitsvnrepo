package com.invessence.bean.consumer;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.converter.SQLData;
import com.invessence.dao.consumer.*;
import com.invessence.data.consumer.CTO.InvestorData;
import com.invessence.util.*;
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

   @ManagedProperty("#{webMessage}")
   private WebMessage messageText;
   public void setMessageText(WebMessage messageText)
   {
      this.messageText = messageText;
   }

   @ManagedProperty("#{webutil}")
   private WebUtil webutil;
   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
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
            // New form, make sure that all data is reset.
         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   public void changeView()
   {
      try {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            pagemanager = new PagesImpl(4);
            // fetch all Data to change.
         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   public void custReview()
   {
      try {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            pagemanager = new PagesImpl(4);
            // fetch Customer Data
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
      save();
      pagemanager.nextPage();
   }

   public void save()
   {
   }

   public void cancel()
   {
   }

   public void setDisclosure(Integer pos) {
      if ((pos != null) && (pos > 0) && (pos < disclosure.length)) {
         if (pos == 1) {
            popupDialog(pos);
         }
         markedChecked(pos);
      }
   }

   public void markedChecked(Integer pos) {
      if ((pos != null) && (pos > 0) && (pos < disclosure.length)) {
         if (disclosure[pos] == null || disclosure[pos] == 0)
            allApproved++;
         disclosure[pos] = 1;
      }
   }

   public Boolean getAllApproved() {
      if (allApproved >= 1)
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

   public void acceptandClose() {
      if ((whichDisclosure != null) && (whichDisclosure > 0) && (whichDisclosure < disclosure.length)) {
         markedChecked(whichDisclosure);
         RequestContext.getCurrentInstance().openDialog("/pages/consumer/cto/disclosure");
      }
   }

   public void acceptAll() {
      allApproved = 0;
      for (int i=0; i < disclosure.length; i++) {
         disclosure[i] = 1;
         allApproved++;
      }
   }

   public void accept() {
      markedChecked(whichDisclosure);
   }

   public void accept(Integer pos) {
      markedChecked(pos);
   }

   public void popupDialog(Integer whichone) {
      whichDisclosure = whichone;

      Map<String,Object> options = new HashMap<String, Object>();
      options.put("resizable", true);
      options.put("draggable", true);
      options.put("modal", true);
      RequestContext.getCurrentInstance().openDialog("/pages/consumer/cto/disclosure", options, null);
   }


}

