package com.invessence.broker.processors;

import java.io.*;
import java.util.*;
import javax.sql.DataSource;

import com.google.common.base.*;
import com.google.common.collect.Maps;
import com.invessence.broker.constants.BrokerServiceConstants;
import com.invessence.broker.dao.DatabaseBean;
import org.apache.commons.io.Charsets;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.transform;
import static com.google.common.io.Files.readLines;

public class PositionFileProcessor
{
   private Logger logger = Logger.getLogger(PositionFileProcessor.class.getName());
   private DataSource dataSource;
   private DatabaseBean dao;

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
         clearPositionsTable();
         fileToProcess = FileProcessorUtil.getFileToProcess("Positions");
         List<Map<String, String>> rows = processFile(fileToProcess);
         if (rows.size() > 1)
         {
            dao.setBrokerDate(FileProcessorUtil.extractDateFromName(fileToProcess.getName()));
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

   private void clearPositionsTable()
   {
      JdbcTemplate clearTable = new JdbcTemplate(dataSource);

      String sql = "delete from tmp_position";
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
            Iterable<String> allLines = filter(transform(readLines(file, Charsets.UTF_8), new Function<String, String>()
            {
               @Override
               public String apply(String s)
               {
                  return s.replaceAll("\"", "");
               }
            }), new Predicate<String>()
            {
               @Override
               public boolean apply(String s)
               {
                  return !s.startsWith("ClientAccountID");
               }
            });

            for (String line : allLines)
            {
               Map<String, String> map = Maps.newHashMap();
               String[] strings = line.split(",");
               for (int i = 0; i < BrokerServiceConstants.POSITION_TABLE_COLUMNS.length; i++)
               {
                  map.put(BrokerServiceConstants.POSITION_TABLE_COLUMNS[i], strings[i]);
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

   public void setDataSource(DataSource dataSource)
   {
      this.dataSource = dataSource;
   }
}
