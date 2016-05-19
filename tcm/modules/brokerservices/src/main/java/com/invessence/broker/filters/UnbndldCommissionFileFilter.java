package com.invessence.broker.filters;

import org.apache.camel.component.file.*;

public class UnbndldCommissionFileFilter implements GenericFileFilter
{
   private DateFilter dateFilter;

   public void setDateFilter(DateFilter dateFilter)
   {
      this.dateFilter = dateFilter;
   }

   @Override
   public boolean accept(GenericFile file)
   {
      return file.getFileName().contains("Unbundled_Commissions") && dateFilter.accept(file.getFileName());
   }
}
