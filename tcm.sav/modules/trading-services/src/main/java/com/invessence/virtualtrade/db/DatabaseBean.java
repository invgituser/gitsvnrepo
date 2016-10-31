package com.invessence.virtualtrade.db;

import javax.sql.DataSource;

public class DatabaseBean
{
   private DataSource dataSource;

   public void setDataSource(DataSource dataSource)
   {
      this.dataSource = dataSource;
   }

   public void cleanUpVirtualTradeTable()
   {
      System.out.println("Destroy method invoked");
   }
}
