package com.invessence.broker.processors;

import java.io.*;
import java.util.*;
import javax.sql.DataSource;

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import com.invessence.broker.constants.BrokerServiceConstants;
import com.invessence.broker.dao.DatabaseBean;
import org.apache.commons.io.Charsets;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import static com.google.common.collect.Iterables.filter;
import static com.google.common.io.Files.readLines;

public class AccountFileProcessor
{
   private Logger logger = Logger.getLogger(AccountFileProcessor.class.getName());
   private DataSource dataSource;
   private DatabaseBean dao;

   public void setDataSource(org.springframework.jdbc.datasource.DriverManagerDataSource dataSource)
   {
      this.dataSource = dataSource;
   }

   public void setDao(DatabaseBean dao)
   {
      this.dao = dao;
   }

   public List<Map<String, String>> process()
   {
      List<Map<String, String>> rowsToInsert = new LinkedList<Map<String, String>>();
      File fileToProcess;
      try
      {
         fileToProcess = FileProcessorUtil.getFileToProcess("Accounts");
         List<Map<String, String>> rows = processFile(fileToProcess);
         if (rows.size() > 1)
         {
            // Don't delete this file till there is new data.
            cleanAccountsTable();
            // dao.setBrokerDate(FileProcessorUtil.extractDateFromName(fileToProcess.getName()));
         }
         FileProcessorUtil.renameProcessedFile(fileToProcess);
         return rows;
      }
      catch (IOException e)
      {
         logger.error("Failed to load properties", e);
      }
      return rowsToInsert;
   }

   private void cleanAccountsTable()
   {
      JdbcTemplate clearTable = new JdbcTemplate(dataSource);

      String sql = "delete from tmp_IB_Accounts";
      clearTable.execute(sql);
   }

   private List<Map<String, String>> processFile(File file)
   {
      List<Map<String, String>> rowsToInsert = new LinkedList<Map<String, String>>();
      if (file != null)
      {
         logger.info("Processing file " + file.getAbsolutePath());
         try
         {
            Iterable<String> allLines = filter(readLines(file, Charsets.UTF_8), new Predicate<String>()
            {
               @Override
               public boolean apply(String s)
               {
                  return !s.startsWith("\"ClientAccountID");
               }
            });

            for (String line : allLines)
            {
               Map<String, String> map = Maps.newHashMap();
               String[] strings = line.split("\",");
               for (int i = 0; i < BrokerServiceConstants.ACCOUNT_TABLE_COLUMNS.length; i++)
               {
                  map.put(BrokerServiceConstants.ACCOUNT_TABLE_COLUMNS[i], strings[i].replaceAll("\"", ""));
               }
               rowsToInsert.add(map);
            }
            logger.info("Processing done");
         }
         catch (Exception e)
         {
            logger.error("Error processing file: " + file.getAbsolutePath(), e);
         }
      }
      return rowsToInsert;
   }
}
