package com.invessence.rbsa.filters;

import org.apache.camel.component.file.*;

public class InputFileFilter implements GenericFileFilter
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
