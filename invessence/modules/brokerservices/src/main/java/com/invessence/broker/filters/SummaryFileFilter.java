package com.invessence.broker.filters;

import com.invessence.broker.dao.DatabaseBean;
import org.apache.camel.component.file.*;
import org.apache.log4j.Logger;

public class SummaryFileFilter implements GenericFileFilter
{
   private DateFilter dateFilter;
   private DatabaseBean databaseBean;
   private Logger logger = Logger.getLogger(SummaryFileFilter.class.getName());

   public void setDateFilter(DateFilter dateFilter)
   {
      this.dateFilter = dateFilter;
   }

   @Override
   public boolean accept(GenericFile file)
   {
      if (file.isDirectory())
      {
         return true;
      }
      boolean retrieveFile = file.getFileName().contains("Monthly_Statement") && dateFilter.acceptSummaryFile(file.getFileName());
      if (retrieveFile)
      {
         logger.info("Retrieving Monthly Summary File: " + file.getFileName());
         databaseBean.updateSummaryDate(true);
      }
      return retrieveFile;
   }

   public void setDao(com.invessence.broker.dao.DatabaseBean dao)
   {
      databaseBean = dao;
   }
}
