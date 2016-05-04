package com.invessence.bean.consumer;

import java.io.Serializable;
import javax.faces.bean.*;

import com.invessence.data.consumer.TradeData;


/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 2/4/15
 * Time: 1:18 PM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "tradeinfobean")
@SessionScoped
public class TradeInfoBean implements Serializable
{
   TradeData tradeData = new TradeData();
   private String beanacctnum;
   private String beantranno;
   private String beantype;
   private String beanfund;
   private String beanamt;


   public TradeData getTradeData()
   {
      return tradeData;
   }

   public String getBeanacctnum()
   {
      return beanacctnum;
   }

   public void setBeanacctnum(String beanacctnum)
   {
      this.beanacctnum = beanacctnum;
   }

   public String getBeantranno()
   {
      return beantranno;
   }

   public void setBeantranno(String beantranno)
   {
      this.beantranno = beantranno;
   }

   public String getBeanamt()
   {
      return beanamt;
   }

   public void setBeanamt(String beanamt)
   {
      this.beanamt = beanamt;
   }

   public String getBeantype()
   {
      return beantype;
   }

   public void setBeantype(String beantype)
   {
      this.beantype = beantype;
   }

   public String getBeanfund()
   {
      return beanfund;
   }

   public void setBeanfund(String beanfund)
   {
      this.beanfund = beanfund;
   }

   public void setTradeData(TradeData tradeData)
   {
      this.tradeData = tradeData;
   }

   public void initTradeData(TradeData tradeData) {
      if (tradeData != null) {
         this.tradeData = tradeData;

      }
   }



}

