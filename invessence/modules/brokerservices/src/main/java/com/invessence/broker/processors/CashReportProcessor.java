package com.invessence.broker.processors;

import java.io.*;
import java.util.*;
import javax.sql.DataSource;

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import com.invessence.broker.dao.DatabaseBean;
import org.apache.commons.io.Charsets;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import static com.google.common.collect.Iterables.filter;
import static com.google.common.io.Files.readLines;

public class CashReportProcessor
{
   private Logger logger = Logger.getLogger(CashReportProcessor.class.getName());
   private DataSource dataSource;
   private DatabaseBean dao;

   public void setDataSource(DataSource dataSource)
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
         fileToProcess = FileProcessorUtil.getFileToProcess("CashReport");
         List<Map<String, String>> rows = processFile(fileToProcess);
         if (rows.size() > 1)
         {
            cleanCashInfoTable();
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

   private void cleanCashInfoTable()
   {
      JdbcTemplate clearTable = new JdbcTemplate(dataSource);

      String sql = "delete from tmp_IB_Cash_Info";
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
               for (CashReportColumns column : CashReportColumns.values())
               {
                  map.put(column.getName(), strings[column.getIndex()].replaceAll("\"", ""));
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

enum CashReportColumns
{
   CLIENT_ACCOUNT_ID(0, "clientAccountID"),
   ACCOUNT_ALIAS(1, "accountAlias"),
   CURRENCY_PRIMARY(2, "currencyPrimary"),
   FROM_DATE(3, "fromDate"),
   TO_DATE(4, "toDate"),
   STARTING_CASH(5, "startingCash"),
   ENDING_CASH(6, "endingCash");

   private final int index;
   private final String name;

   private CashReportColumns(int index, String name)
   {
      this.index = index;
      this.name = name;
   }

   public int getIndex()
   {
      return index;
   }

   public String getName()
   {
      return name;
   }
}