package com.invessence.broker.filters;

import org.apache.camel.component.file.*;

public class FileFilter implements GenericFileFilter
{
   private DateFilter dateFilter;

   public void setDateFilter(DateFilter dateFilter)
   {
      this.dateFilter = dateFilter;
   }

   public boolean process(DataFileInfo datafileinfo, GenericFile file)
   {
      if (datafileinfo.getVendor().equalsIgnoreCase("IB")) {
         if (datafileinfo.getAvailable().equalsIgnoreCase("Daily")) {
            return file.getFileName().contains(datafileinfo.getFilename()) && dateFilter.accept(file.getFileName());
         }
         else if (datafileinfo.getAvailable().equalsIgnoreCase("Monthly")) {
            return file.getFileName().contains(datafileinfo.getFilename()) && dateFilter.accept(file.getFileName());
         }
      }
      return false;
   }

   @Override
   public boolean accept(GenericFile file) {
      return true;
   }
}
