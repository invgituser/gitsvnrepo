package com.invessence.dao;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.invessence.data.ManageGoals;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.object.StoredProcedure;
import com.invmodel.asset.data.AssetClass;

public class AllocationSP extends StoredProcedure
{

   public AllocationSP(DataSource datasource)
   {

      super(datasource, "sp_asset_alloc_add_mod");

      declareParameter(new SqlParameter("p_addmodflag", Types.VARCHAR));
      declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
      declareParameter(new SqlParameter("p_assetclass", Types.VARCHAR));
      declareParameter(new SqlParameter("p_themecode", Types.VARCHAR));
      declareParameter(new SqlParameter("p_allocationmodel", Types.VARCHAR));
      declareParameter(new SqlParameter("p_assetyear", Types.INTEGER));
      declareParameter(new SqlParameter("p_active", Types.VARCHAR));
      declareParameter(new SqlParameter("p_weight", Types.FLOAT));
      compile();
   }


   @SuppressWarnings({"unchecked", "rawtypes"})
   public void updateAllocation(ManageGoals data)
   {

      String addmodflag;
      int rowExists = checkAllocation(data.getAcctnum());
      if (rowExists != 0)
      {
         addmodflag = "M";
      }
      else
      {
         addmodflag = "A";
      }

      AssetClass aac[] = data.getAssetData();
      int rowSize = aac[0].getOrderedAsset().size();

      for (int loop = 0; loop < rowSize; loop++)
      {
         Map inputAssetMap = new HashMap();
         String assetname = aac[0].getOrderedAsset().get(loop);
         inputAssetMap.put("p_addmodflag", addmodflag);
         inputAssetMap.put("p_acctnum", data.getAcctnum());
         inputAssetMap.put("p_assetclass", assetname);
         inputAssetMap.put("p_themecode", data.getTheme());
         inputAssetMap.put("p_allocationmodel", data.getModel());
         inputAssetMap.put("p_assetyear", data.getAssetyear());
         inputAssetMap.put("p_active", data.getActive());
         inputAssetMap.put("p_weight", data.getAssetData()[0].getAssetRoundedActualWeight(assetname));

         super.execute(inputAssetMap);
      }
   }

   public int checkAllocation(Long acctnum)
   {
      String sql = "select count(*) from asset_alloc where acctnum = ?";
      return getJdbcTemplate().queryForInt(sql, new Object[]{acctnum});
   }
}
