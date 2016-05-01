package com.invessence.bean.consumer;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.constant.Const;
import com.invessence.dao.common.UserInfoDAO;
import com.invessence.dao.consumer.ConsumerSaveDAO;
import com.invessence.data.common.AccountData;
import com.invessence.data.consumer.TradeData;
import com.invessence.util.WebUtil;
import com.invessence.ws.bean.WSCallStatus;
import com.invessence.ws.service.ServiceLayer;


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

   @ManagedProperty("#{webutil}")
   WebUtil webutil;
   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   public TradeData getTradeData()
   {
      return tradeData;
   }

   public void setTradeData(TradeData tradeData)
   {
      this.tradeData = tradeData;
   }

   public void initTradeData(TradeData tradeData) {
      if (tradeData != null) {
         this.tradeData = tradeData;
         webutil.redirect("/pages/consumer/tradeinfo.xhtml", null);
      }
   }



}

