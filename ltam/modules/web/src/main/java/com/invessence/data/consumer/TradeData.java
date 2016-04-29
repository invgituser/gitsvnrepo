package com.invessence.data.consumer;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: pichaimanir
 * Date: 8/19/13
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class TradeData implements Serializable
{
   private Long    transactionnum;
   private Long    acctnum;
   private String  clientAccountID;
   private String  tradetype;
   private String  webservicenum;
   private String  cusip;
   private Double  investment;
   private String  bankname;
   private String  bankinfo;
   private String  info1;
   private String  info2;
   private String  info3;

   public TradeData()
   {
   }

   public TradeData(Long transactionnum, Long acctnum, String clientAccountID,
                    String tradetype, String webservicenum, String cusip,
                    Double investment, String bankname, String bankinfo,
                    String info1, String info2, String info3)
   {
      this.transactionnum = transactionnum;
      this.acctnum = acctnum;
      this.clientAccountID = clientAccountID;
      this.tradetype = tradetype;
      this.webservicenum = webservicenum;
      this.cusip = cusip;
      this.investment = investment;
      this.bankname = bankname;
      this.bankinfo = bankinfo;
      this.info1 = info1;
      this.info2 = info2;
      this.info3 = info3;
   }

   public Long getTransactionnum()
   {
      return transactionnum;
   }

   public void setTransactionnum(Long transactionnum)
   {
      this.transactionnum = transactionnum;
   }

   public Long getAcctnum()
   {
      return acctnum;
   }

   public void setAcctnum(Long acctnum)
   {
      this.acctnum = acctnum;
   }

   public String getClientAccountID()
   {
      return clientAccountID;
   }

   public void setClientAccountID(String clientAccountID)
   {
      this.clientAccountID = clientAccountID;
   }

   public String getTradetype()
   {
      return tradetype;
   }

   public void setTradetype(String tradetype)
   {
      this.tradetype = tradetype;
   }

   public String getWebservicenum()
   {
      return webservicenum;
   }

   public void setWebservicenum(String webservicenum)
   {
      this.webservicenum = webservicenum;
   }

   public String getCusip()
   {
      return cusip;
   }

   public void setCusip(String cusip)
   {
      this.cusip = cusip;
   }

   public Double getInvestment()
   {
      return investment;
   }

   public void setInvestment(Double investment)
   {
      this.investment = investment;
   }

   public String getBankname()
   {
      return bankname;
   }

   public void setBankname(String bankname)
   {
      this.bankname = bankname;
   }

   public String getBankinfo()
   {
      return bankinfo;
   }

   public void setBankinfo(String bankinfo)
   {
      this.bankinfo = bankinfo;
   }

   public String getInfo1()
   {
      return info1;
   }

   public void setInfo1(String info1)
   {
      this.info1 = info1;
   }

   public String getInfo2()
   {
      return info2;
   }

   public void setInfo2(String info2)
   {
      this.info2 = info2;
   }

   public String getInfo3()
   {
      return info3;
   }

   public void setInfo3(String info3)
   {
      this.info3 = info3;
   }
}
