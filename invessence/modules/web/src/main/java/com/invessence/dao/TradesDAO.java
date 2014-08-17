package com.invessence.dao;

import java.util.*;
import javax.sql.DataSource;

import com.invessence.data.*;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

public class TradesDAO extends SimpleJdbcDaoSupport
{
   public List<Position> getTrades(Long p_logonid, Long p_acctnum)
   {
      DataSource ds = getDataSource();
      TradesSP sp = new TradesSP(ds);
      List<Position> positionList = new ArrayList<Position>();

      Map outMap = sp.getTrades(p_logonid, p_acctnum);
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         for (Map<String, Object> map : rows)
         {
            Map rs = (Map) rows.get(i);
            Position data = new Position();
            data.setLogonid(Long.parseLong(getData(rs.get("logonid"))));
            data.setAcctnum(Long.parseLong(getData(rs.get("acctnum"))));
            data.setInstrumentid(Long.parseLong(getData(rs.get("instrumentid"))));
            data.setTicker(getData(rs.get("ticker")));
            data.setAssetclass(getData(rs.get("assetclass")));
            data.setSubclass(getData(rs.get("subclass")));
            data.setColor(getData(rs.get("color")));
            data.setName(getData(rs.get("name")));
            data.setSide("Buy");
            // data.setP_s_indicator(getData(rs.get("p_s_indicator")));
            data.setQty(Integer.parseInt(getData(rs.get("qty"))));
            data.setPrice(Double.parseDouble(getData(rs.get("price"))));
            data.setInvested(Double.parseDouble(getData(rs.get("invested"))));
            positionList.add(i, data);
            i++;
         }
      }

      return positionList;

   }

   private String getData(Object dataobj)
   {
      String val = null;
      try
      {
         if (dataobj != null)
         {
            val = dataobj.toString();
         }
      }
      catch (Exception ex)
      {
         System.out.println("Stored procedure dataobj IS NULL!");
      }
      return val;
   }
}

