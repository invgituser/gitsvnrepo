package com.invessence.dao.advisor;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import com.invessence.dao.consumer.ConsumerListSP;
import com.invessence.data.advisor.*;
import com.invessence.data.common.AccountData;
import com.invessence.data.consumer.ConsumerData;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

@ManagedBean(name = "advisorListDataDAO")
@SessionScoped
public class AdvisorListDataDAO extends JdbcDaoSupport implements Serializable
{
   SQLData convert = new SQLData();

   public void advisorDashBoard(AdvisorDashData addata) {
      try {
         if (addata == null) {
            addata = new AdvisorDashData();
         }

         DataSource ds = getDataSource();
         AdvisorListSP sp = new AdvisorListSP(ds, "sel_advisorDashBoard",0);
         Map outMap = sp.collectDashBoardData(addata.getLogonid());
         if (outMap != null)
         {
            ArrayList<Map<String, Object>> rows;

            rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            if (rows != null)  {
               Integer i = 0;
               for (Map<String, Object> map : rows)
               {
                  Map rs = (Map) rows.get(i);
                  addata.setStatInfo(convert.getStrData(rs.get("source")),
                                     convert.getIntData(rs.get("value")));
                  i++;
               }
            }


            rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-2");
            if (rows != null)  {
               Integer i = 0;
               for (Map<String, Object> map : rows)
               {
                  Map rs = (Map) rows.get(i);
                  addata.addSecurityInfo(convert.getStrData(rs.get("source")),
                                         convert.getStrData(rs.get("cusip")),
                                         convert.getStrData(rs.get("ticker")),
                                         convert.getStrData(rs.get("description")),
                                         convert.getDoubleData(rs.get("markPrice")),
                                         convert.getDoubleData(rs.get("positionValue")),
                                         convert.getIntData(rs.get("count"))
                  );
                  i++;
               }
            }


         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

}