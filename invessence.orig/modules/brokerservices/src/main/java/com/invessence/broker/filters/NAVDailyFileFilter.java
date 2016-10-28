package com.invessence.broker.filters;

import org.apache.camel.component.file.*;

public class NAVDailyFileFilter implements GenericFileFilter
{
   private DateFilter dateFilter;

   public void setDateFilter(DateFilter dateFilter)
   {
      this.dateFilter = dateFilter;
   }

   @Override
   public boolean accept(GenericFile file)
   {
      return file.getFileName().contains("NAV_Daily") && dateFilter.accept(file.getFileName());
   }
}
