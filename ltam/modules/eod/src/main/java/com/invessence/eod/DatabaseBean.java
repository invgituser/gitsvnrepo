package com.invessence.eod;
//test

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.object.StoredProcedure;

import javax.sql.DataSource;

public class DatabaseBean
{

   private DataSource dataSource;
   private String updatePriceDateStoredProc;

   public void setUpdatePriceDateStoredProc(String updatePriceDateStoredProc)
   {
      this.updatePriceDateStoredProc = updatePriceDateStoredProc;
   }

   public void setDataSource(DataSource dataSource)
   {
      this.dataSource = dataSource;
   }

   public void cleanTickerInformationTable() throws Exception
   {
      JdbcTemplate jdbc = new JdbcTemplate(dataSource);

      String sql = "delete from yahoo_prices";
      jdbc.execute(sql);
   }

   public void updatePriceBusinessDate()
   {
      new UpdatePriceBusinessDateStoredProcedure(dataSource).execute();
   }

   private class UpdatePriceBusinessDateStoredProcedure extends StoredProcedure
   {

      private UpdatePriceBusinessDateStoredProcedure(DataSource ds)
      {
         super(ds, updatePriceDateStoredProc);
      }
   }
}