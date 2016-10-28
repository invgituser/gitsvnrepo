package com.invessence.broker.filters;

import org.apache.camel.component.file.*;

public class CashTransactionFileFilter implements GenericFileFilter
{
   private DateFilter dateFilter;

   public void setDateFilter(DateFilter dateFilter)
   {
      this.dateFilter = dateFilter;
   }

   @Override
   public boolean accept(GenericFile file)
   {
      boolean retrieveFile = file.getFileName().contains("Cash_Transaction") && dateFilter.acceptFirstDayOfMonth(file.getFileName());
      return retrieveFile;
   }
}
