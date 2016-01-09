package com.invessence.broker.filters;

import org.apache.camel.component.file.*;

public class RelAndUnrelFileFilter implements GenericFileFilter
{
   private DateFilter dateFilter;

   public void setDateFilter(DateFilter dateFilter)
   {
      this.dateFilter = dateFilter;
   }

   @Override
   public boolean accept(GenericFile file)
   {
      return file.getFileName().contains("RelAndUnRel") && dateFilter.accept(file.getFileName());
   }
}
