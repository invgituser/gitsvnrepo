package com.invessence.dao;

import java.util.*;
import javax.faces.bean.*;
import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import com.invessence.data.Position;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

@ManagedBean(name = "positionDAO")
@SessionScoped
public class PositionDAO extends SimpleJdbcDaoSupport
{
   SQLData convert = new SQLData();

   public List<Position> loadDBPosition(Long p_logonid, Long p_acctnum)
   {
      DataSource ds = getDataSource();
      String storedProcName = "sel_position";
      PositionSP sp = new PositionSP(ds, storedProcName);
      List<Position> positionList = new ArrayList<Position>();

      Map outMap = sp.loadDBData(p_acctnum);
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
            data.setInvested(convert.getDoubleData(rs.get("positionValue")));
            data.setFifoPnlUnrealized(convert.getDoubleData(rs.get("fifoPnlUnrealized")));
            data.setLevelOfDetail(convert.getStrData(rs.get("levelOfDetail")));
            positionList.add(i, data);
            i++;
         }
      }

      return positionList;

   }

   public List<Position> loadDBPendingPosition(Long p_logonid, Long p_acctnum)
   {
      DataSource ds = getDataSource();
      String storedProcName = "sel_pendingposition";
      PositionSP sp = new PositionSP(ds, storedProcName);
      List<Position> positionList = new ArrayList<Position>();

      Map outMap = sp.loadDBData(p_acctnum);
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         for (Map<String, Object> map : rows)
         {
            Map rs = (Map) rows.get(i);
            Position data = new Position();
            data.setLogonid(p_logonid);
            data.setAcctnum(convert.getLongData(rs.get("acctnum")));
            data.setInstrumentid(convert.getLongData(rs.get("instrumentid")));
            data.setTicker(convert.getStrData(rs.get("ticker")));
            data.setAssetclass(convert.getStrData(rs.get("assetclass")));
            data.setSubclass(convert.getStrData(rs.get("subclass")));
            data.setColor(convert.getStrData(rs.get("color")));
            data.setName(convert.getStrData(rs.get("name")));
            data.setSide("Buy");
            // data.setP_s_indicator(getData(rs.get("p_s_indicator")));
            data.setQty(convert.getIntData(rs.get("qty")));
            data.setPrice(convert.getDoubleData(rs.get("price")));
            data.setInvested(convert.getDoubleData(rs.get("invested")));
            positionList.add(i, data);
            i++;
         }
      }

      return positionList;

   }
}

