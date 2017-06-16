package com.invessence.rbsa.filters;

import java.text.*;
import java.util.Date;

import com.invessence.rbsa.dao.DatabaseBean;
import org.apache.log4j.Logger;

import static com.invessence.rbsa.constants.RBSAServiceConstant.YEAR_MONTH_DAY_FORMAT;

public class DateFilter
{
   private Date dbDate;
   private Logger logger = Logger.getLogger(DateFilter.class.getName());

   public void setDatabaseBean(DatabaseBean databaseBean)
   {
      try
      {
         dbDate = new SimpleDateFormat(YEAR_MONTH_DAY_FORMAT).parse(databaseBean.getLastDateFundsOptimized());
      }
      catch (ParseException e)
      {
         logger.error("Error parsing date:", e);
      }
   }

   public boolean accept(String fileName)
   {
      if (dbDate != null)
      {
         String[] strings = fileName.split("\\.");
         try
         {
            Date fileDate = new SimpleDateFormat(YEAR_MONTH_DAY_FORMAT).parse(strings[strings.length - 2]);
            return fileDate.after(dbDate);
         }
         catch (ParseException e)
         {
            logger.error("Error parsing date in file name " + fileName, e);
         }
      }
      return false;
   }

}
