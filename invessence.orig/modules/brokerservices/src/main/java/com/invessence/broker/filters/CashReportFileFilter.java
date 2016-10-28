package com.invessence.broker.filters;

import org.apache.camel.component.file.*;

public class CashReportFileFilter implements GenericFileFilter
{
   private DateFilter dateFilter;

   public void setDateFilter(DateFilter dateFilter)
   {
      this.dateFilter = dateFilter;
   }

   @Override
   public boolean accept(GenericFile file)
   {
      return file.getFileName().contains("Cash_Report") && dateFilter.accept(file.getFileName());
   }
}
