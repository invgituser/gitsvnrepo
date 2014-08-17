package com.invessence.dao;

import com.invessence.data.ManageGoals;
import com.invmodel.portfolio.data.Portfolio;
import com.invmodel.portfolio.data.PortfolioSecurityData;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ramesh
 * Date: 9/20/13
 * Time: 6:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class PortfolioSP extends StoredProcedure
{

   public PortfolioSP(DataSource datasource)
   {

      super(datasource, "sp_virtual_portfolio_add_mod");


      declareParameter(new SqlParameter("p_addmodflag", Types.VARCHAR));
      declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
      declareParameter(new SqlParameter("p_itemnum", Types.INTEGER));
      declareParameter(new SqlParameter("p_ticker", Types.VARCHAR));
      declareParameter(new SqlParameter("p_active", Types.VARCHAR));
      declareParameter(new SqlParameter("p_qty", Types.INTEGER));
      declareParameter(new SqlParameter("p_weightByAsset", Types.FLOAT));
      declareParameter(new SqlParameter("p_tradeprice", Types.FLOAT));
      declareParameter(new SqlParameter("p_investmentvalue", Types.DOUBLE));
      //declareParameter(new SqlParameter("p_weightByPortfolio", Types.DOUBLE));
      compile();
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public void updatePortfolio(ManageGoals data)
   {

      String addmodflag;
      int rowExists = checkPortfolio(data.getAcctnum());
      if (rowExists != 0)
      {
         addmodflag = "M";
      }
      else
      {
         addmodflag = "A";
      }


      String risk = data.getRisk();
      risk = "M";

      Portfolio pfclass[] = data.getPortfolioData();
      if (pfclass == null)
         return;
      int rowSize = pfclass[0].getPortfolio().size();

      for (int loop = 0; loop < rowSize; loop++)
      {
         PortfolioSecurityData pfList = pfclass[0].getPortfolio().get(loop);
         Map inputPortfolioMap = new HashMap();
         inputPortfolioMap.put("p_addmodflag", addmodflag);
         inputPortfolioMap.put("p_acctnum", data.getAcctnum());
         inputPortfolioMap.put("p_itemnum", loop + 1);
         inputPortfolioMap.put("p_ticker", pfList.getTicker());
         inputPortfolioMap.put("p_active", data.getActive());
         inputPortfolioMap.put("p_qty", pfList.getShares());
         inputPortfolioMap.put("p_weightByAsset", pfList.getWeight());
         inputPortfolioMap.put("p_tradeprice", pfList.getDailyprice());
         inputPortfolioMap.put("p_investmentvalue", pfList.getMoney());
         //inputPortfolioMap.put("p_weightByPortfolio", pfList.getTickerWeights());

         super.execute(inputPortfolioMap);
      }
   }

   public int checkPortfolio(Long acctnum)
   {
      String sql = "select count(*) from virtual_portfolio where acctnum = ?";
      return getJdbcTemplate().queryForInt(sql, new Object[]{acctnum});
   }
}
