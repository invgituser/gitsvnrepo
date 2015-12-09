package com.invessence.dao.consumer;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import com.invessence.data.consumer.AggregationData;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

@ManagedBean(name = "aggregationDAO")
@SessionScoped
public class AggregationDAO extends JdbcDaoSupport implements Serializable
{
   SQLData convert = new SQLData();

   public AggregationData loadDetailData(Long logonid) {
      DataSource ds = getDataSource();
      AggregationSP sp = new AggregationSP(ds, "sel_consolidated_position",0);
      Map outMap = sp.loadDetailData(logonid);
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         AggregationData aggrdata = new AggregationData();
         for (Map<String, Object> map : rows)
         {
            Map rs = (Map) rows.get(i);
             aggrdata.addData(
                     convert.getIntData(rs.get("sortorder")),
                     convert.getStrData(rs.get("sitename")),
                     convert.getStrData(rs.get("sitenid")),
                     convert.getLongData(rs.get("acctnum")),
                     convert.getStrData(rs.get("clientAccountID")),
                     convert.getStrData(rs.get("acctname")),
                     convert.getStrData(rs.get("currencyPrimary")),
                     convert.getStrData(rs.get("assetClass")),
                     convert.getStrData(rs.get("color")),
                     convert.getStrData(rs.get("subclass")),
                     convert.getDoubleData(rs.get("fxRateToBase")),
                     convert.getStrData(rs.get("symbol")),
                     convert.getStrData(rs.get("description")),
                     convert.getStrData(rs.get("reportDate")),
                     convert.getStrData(rs.get("side")),
                     convert.getIntData(rs.get("quantity")),
                     convert.getDoubleData(rs.get("costBasisPrice")),
                     convert.getDoubleData(rs.get("costBasisMoney")),
                     convert.getDoubleData(rs.get("markPrice")),
                     convert.getDoubleData(rs.get("positionValue")),
                     convert.getDoubleData(rs.get("fifoPnlUnrealized")),
                     convert.getStrData(rs.get("levelOfDetail"))
             );
           i++;
         }
         return aggrdata;
      }
      return null;
   }
}