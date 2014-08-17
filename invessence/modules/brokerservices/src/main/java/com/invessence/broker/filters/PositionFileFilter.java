package com.invessence.broker.filters;

import org.apache.camel.component.file.*;

public class PositionFileFilter implements GenericFileFilter
{
   private DateFilter dateFilter;

   public void setDateFilter(DateFilter dateFilter)
   {
      this.dateFilter = dateFilter;
   }

   @Override
   public boolean accept(GenericFile file)
   {
      return file.getFileName().contains("Open_Positions") && dateFilter.accept(file.getFileName());
   }
}
