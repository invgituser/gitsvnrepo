package com.invessence.broker.filters;

import com.invessence.broker.dao.DatabaseBean;
import org.apache.camel.component.file.*;
import org.apache.log4j.Logger;

public class CommissionFileFilter implements GenericFileFilter
{
   private DateFilter dateFilter;
   private Logger logger = Logger.getLogger(CommissionFileFilter.class.getName());
   private DatabaseBean databaseBean;

   public void setDateFilter(DateFilter dateFilter)
   {
      this.dateFilter = dateFilter;
   }

   @Override
   public boolean accept(GenericFile file)
   {
      boolean retrieveFile = file.getFileName().contains(".Commissions") && dateFilter.acceptFirstDayOfMonth(file.getFileName());
      if (retrieveFile)
      {
         logger.info("Retrieving Monthly End Commission File: " + file.getFileName());
         databaseBean.updateFirstDayofMonth(true);
      }
      return retrieveFile;
   }

   public void setDao(com.invessence.broker.dao.DatabaseBean dao)
   {
      databaseBean = dao;
   }
}
