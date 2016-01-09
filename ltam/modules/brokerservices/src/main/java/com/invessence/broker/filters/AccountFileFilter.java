package com.invessence.broker.filters;

import org.apache.camel.component.file.*;

public class AccountFileFilter implements GenericFileFilter
{
   private DateFilter dateFilter;

   public void setDateFilter(DateFilter dateFilter)
   {
      this.dateFilter = dateFilter;
   }

   @Override
   public boolean accept(GenericFile file)
   {
      return file.getFileName().contains("Account_Information") && dateFilter.accept(file.getFileName());
   }
}
