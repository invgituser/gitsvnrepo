package com.invessence.web.dao.common;

import java.util.*;
import javax.faces.bean.*;
import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import com.invessence.web.data.common.Position;
import com.invessence.web.util.WebUtil;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

@ManagedBean(name = "positionDAO")
@SessionScoped
public class PositionDAO extends JdbcDaoSupport
{
   SQLData convert = new SQLData();

   public List<Position> loadDBPosition(WebUtil webUtil, Long acctnum)
   {
      DataSource ds = getDataSource();
      String storedProcName = "sel_position";
      PositionSP sp = new PositionSP(ds, storedProcName);
      List<Position> positionList = new ArrayList<Position>();

      Map outMap = sp.loadDBData(webUtil.getLogonid(), acctnum, webUtil.getUserInfoData().getAdvisor(), webUtil.getUserInfoData().getRep());
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         for (Map<String, Object> map : rows)
         {
            Map rs = (Map) rows.get(i);
            Position data = new Position();
            data.setAcctnum(convert.getLongData(rs.get("acctnum")));
            data.setClientAccountID(convert.getStrData(rs.get("clientAccountID")));
            data.setFirstname(convert.getStrData(rs.get("firstname")));
            data.setLastname(convert.getStrData(rs.get("lastname")));
            data.setDateOpened(convert.getStrData(rs.get("dateOpened")));
            data.setAccountAlias(convert.getStrData(rs.get("accountAlias")));
            data.setCurrencyPrimary(convert.getStrData(rs.get("currencyPrimary")));
            data.setTicker(convert.getStrData(rs.get("symbol")));
            data.setDescription(convert.getStrData(rs.get("description")));
            data.setSide(convert.getStrData(rs.get("side")));
            data.setQty(convert.getIntData(rs.get("quantity")));
            data.setCostBasisPrice(convert.getDoubleData(rs.get("costBasisPrice")));
            data.setCostBasisMoney(convert.getDoubleData(rs.get("costBasisMoney")));
            data.setMarkPrice(convert.getDoubleData(rs.get("markPrice")));
            data.setPositionValue(convert.getDoubleData(rs.get("positionValue")));
            data.setFifoPnlUnrealized(convert.getDoubleData(rs.get("fifoPnlUnrealized")));
            data.setLevelOfDetail(convert.getStrData(rs.get("levelOfDetail")));
            data.setAssetclass(convert.getStrData(rs.get("assetclass")));
            data.setColor(convert.getStrData(rs.get("color")));
            data.setSubclass(convert.getStrData(rs.get("subclass")));
            data.setWeight(convert.getDoubleData(rs.get("weight")));
            data.setYield(convert.getDoubleData(rs.get("yield")));
            data.setExpenseRatio(convert.getDoubleData(rs.get("expenseRatio")));
            data.setRisk(convert.getDoubleData(rs.get("risk")));
            data.setFees(convert.getDoubleData(rs.get("ytdinvoiceFee")));
            data.setGoalAmount(convert.getDoubleData(rs.get("goalAmount")));

            data.setSettleCurrency(convert.getStrData(rs.get("settleCurrency")));
            data.setSettleQty(convert.getDoubleData(rs.get("settleQty")));
            data.setSettlePrice(convert.getDoubleData(rs.get("settlePrice")));
            data.setSettleMoney(convert.getDoubleData(rs.get("settleMoney")));
            data.setSettlePnL(convert.getDoubleData(rs.get("settlePnL")));
            data.setSettleCostBasisMoney(convert.getDoubleData(rs.get("settleCostBasisMoney")));
            data.setSettleMarkPrice(convert.getDoubleData(rs.get("settleMarkPrice")));

            positionList.add(i, data);
            i++;
         }
      }
      return positionList;
   }

   public List<Position> loadDBPosition(WebUtil webUtil, Long acctnum,boolean managed)
   {
      DataSource ds = getDataSource();
      String storedProcName = "sel_position";
      if(!managed){
         storedProcName = "sel_virtual_position";
      }
      PositionSP sp = new PositionSP(ds, storedProcName);
      List<Position> positionList = new ArrayList<Position>();

      Map outMap = sp.loadDBData(webUtil.getLogonid(), acctnum, webUtil.getUserInfoData().getAdvisor(), webUtil.getUserInfoData().getRep());
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         for (Map<String, Object> map : rows)
         {
            Map rs = (Map) rows.get(i);
            Position data = new Position();
            data.setAcctnum(convert.getLongData(rs.get("acctnum")));
//            data.setClientAccountID(convert.getStrData(rs.get("clientAccountID")));
//            data.setFirstname(convert.getStrData(rs.get("firstname")));
//            data.setLastname(convert.getStrData(rs.get("lastname")));
//            data.setDateOpened(convert.getStrData(rs.get("dateOpened")));
//            data.setAccountAlias(convert.getStrData(rs.get("accountAlias")));
//            data.setCurrencyPrimary(convert.getStrData(rs.get("currencyPrimary")));
            data.setTicker(convert.getStrData(rs.get("symbol")));
            data.setQty(convert.getIntData(rs.get("quantity")));
            data.setCostBasisPrice(convert.getDoubleData(rs.get("costBasisPrice")));
            data.setAssetclass(convert.getStrData(rs.get("assetclass")));
            data.setColor(convert.getStrData(rs.get("color")));

//            data.setDescription(convert.getStrData(rs.get("description")));
//            data.setSide(convert.getStrData(rs.get("side")));
//            data.setCostBasisMoney(convert.getDoubleData(rs.get("costBasisMoney")));
//            data.setMarkPrice(convert.getDoubleData(rs.get("markPrice")));
//            data.setPositionValue(convert.getDoubleData(rs.get("positionValue")));
//            data.setFifoPnlUnrealized(convert.getDoubleData(rs.get("fifoPnlUnrealized")));
//            data.setLevelOfDetail(convert.getStrData(rs.get("levelOfDetail")));
//            data.setSubclass(convert.getStrData(rs.get("subclass")));
            data.setWeight(convert.getDoubleData(rs.get("weight")));
//            data.setYield(convert.getDoubleData(rs.get("yield")));
//            data.setExpenseRatio(convert.getDoubleData(rs.get("expenseRatio")));
//            data.setRisk(convert.getDoubleData(rs.get("risk")));
//            data.setFees(convert.getDoubleData(rs.get("ytdinvoiceFee")));
//            data.setGoalAmount(convert.getDoubleData(rs.get("goalAmount")));
            positionList.add(i, data);
            i++;
         }
      }
      return positionList;
   }
}

