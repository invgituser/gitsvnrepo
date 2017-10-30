package com.invessence.web.dao.common;

import java.util.*;
import javax.faces.bean.*;
import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import com.invessence.web.data.common.*;
import com.invessence.web.util.WebUtil;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Created by sagar on 10/29/2017.
 */

@ManagedBean(name = "transactionDAO")
@SessionScoped


public class TransactionDAO extends JdbcDaoSupport
{
   SQLData convert = new SQLData();

   public List<Transaction> loadTransaction(WebUtil webUtil, Long acctnum,String transactionStatus)
   {
      List<Transaction> transactionList = new ArrayList<Transaction>();
      DataSource ds = getDataSource();
      String storedProcName = "sel_transaction_dtls";
      TransactionSP sp = new TransactionSP(ds, storedProcName);

      Map outMap = sp.loadDBData(webUtil.getLogonid(), acctnum, webUtil.getUserInfoData().getAdvisor(), webUtil.getUserInfoData().getRep(),transactionStatus);
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         for (Map<String, Object> map : rows)
         {
            Map rs = (Map) rows.get(i);
            Transaction data = new Transaction();

            data.setSortorder(convert.getLongData(rs.get("sortorder")));
            data.setAssetclass(convert.getStrData(rs.get("assetclass")));
            data.setSubclass(convert.getStrData(rs.get("subclass")));

            data.setAssetName(convert.getStrData(rs.get("assetName")));
            data.setSubclassName(convert.getStrData(rs.get("subclassName")));
            data.setColor(convert.getStrData(rs.get("color")));
            data.setAcctnum(convert.getLongData(rs.get("acctnum")));
            data.setClientAccountID(convert.getStrData(rs.get("clientAccountID")));
            data.setFirstname(convert.getStrData(rs.get("firstname")));
            data.setLastname(convert.getStrData(rs.get("lastname")));
            data.setTicker(convert.getStrData(rs.get("ticker")));
            data.setDescription(convert.getStrData(rs.get("description")));
            data.setAccountAlias(convert.getStrData(rs.get("accountAlias")));
            data.setConfirmNumber(convert.getStrData(rs.get("confirmNumber")));
            data.setTransactionSource(convert.getStrData(rs.get("transactionSource")));
            data.setTransactionType(convert.getStrData(rs.get("transactionType")));
            data.setTransactionStatus(convert.getStrData(rs.get("transactionStatus")));
            data.setControlNumber(convert.getStrData(rs.get("controlNumber")));
            data.setQuantity(convert.getIntData(rs.get("quantity")));
            data.setPrice(convert.getDoubleData(rs.get("price")));
            data.setNetAmount(convert.getDoubleData(rs.get("netAmount")));
            data.setTradeDate(convert.getStrData(rs.get("tradeDate")));
            data.setSettDate(convert.getStrData(rs.get("settDate")));
            data.setVoidDate(convert.getStrData(rs.get("voidDate")));
            data.setComment(convert.getStrData(rs.get("comment")));
            data.setTradedCurrency(convert.getStrData(rs.get("tradedCurrency")));
            data.setFxRateToBase(convert.getDoubleData(rs.get("fxRateToBase")));
            data.setSettleCurrency(convert.getStrData(rs.get("settleCurrency")));
            data.setSettleQty(convert.getDoubleData(rs.get("settleQty")));
            data.setSettlePrice(convert.getDoubleData(rs.get("settlePrice")));
            data.setSettleNetAmount(convert.getDoubleData(rs.get("settleNetAmount")));
            transactionList.add(i, data);
            i++;
         }
      }
      return transactionList;
   }


}

