package com.invessence.rbsa.processors;

import java.io.*;
import java.util.*;
import javax.sql.DataSource;

import com.google.common.base.*;
import com.google.common.collect.Maps;
import com.invessence.rbsa.constants.RBSAServiceConstant;
import com.invessence.rbsa.dao.DatabaseBean;
import org.apache.commons.io.Charsets;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.transform;
import static com.google.common.io.Files.readLines;

public class LoadDataFileProcessor
{
   private Logger logger = Logger.getLogger(LoadDataFileProcessor.class.getName());
   private DataSource dataSource;
   private DatabaseBean dao;
   private String ticker;

   public void setDao(DatabaseBean dao)
   {
      this.dao = dao;
   }

   public File getFile()
   {
      String filename;
      try
      {
         //clearTempTable();
         File filesToProcess = FileProcessorUtil.getFile("in");
         if (filesToProcess != null) {
               return filesToProcess;
            }
         else
            return null;
         }
      catch (Exception ex) {
         return null;
      }
   }

   public List<Map<String, String>> process(File file)
   {
      System.out.println(file.getAbsolutePath());
      List<Map<String, String>> rowsToInsert = new LinkedList<Map<String, String>>();
      ArrayList<File> filesToProcess;
      try
      {
         //clearTempTable();
         filesToProcess = FileProcessorUtil.getFileToProcess("in");
         if (filesToProcess != null) {
            for (File fileToProcess : filesToProcess) {
               System.out.println("Processing Filename:" + fileToProcess.getName());
               List<Map<String, String>> rows = processFile(fileToProcess);
               if (rows.size() > 1)
               {
                     ticker= fileToProcess.getName();
                  //dao.getBusinessDate(FileProcessorUtil.extractDateFromName(fileToProcess.getName()));
               }
               //FileProcessorUtil.renameProcessedFile(fileToProcess);
               return rows;
            }

         }
/*
         List<Map<String, String>> rows = processFile(fileToProcess);
         if (rows.size() > 1)
         {
            ticker=fileToProcess.getName();
            //dao.getBusinessDate(FileProcessorUtil.extractDateFromName(fileToProcess.getName()));
         }
         FileProcessorUtil.renameProcessedFile(fileToProcess);
         return rows;
*/
      }
      catch (IOException e)
      {
         logger.error("Failed to load properties", e);
      }
      return rowsToInsert;
   }

   private void clearTempTable()
   {
      JdbcTemplate clearTable = new JdbcTemplate(dataSource);

      String sql = "delete from tmp_sec_load";
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
                  return !s.startsWith("Ticker");
               }
            });

            String prevbdate = "";
            String prevclose="";
            for (String line : allLines)
            {
               Map<String, String> map = Maps.newHashMap();
               String[] strings = line.split(",");
               for (int i = 0; i < RBSAServiceConstant.tmp_sec_load.length; i++)
               {
                  map.put(RBSAServiceConstant.tmp_sec_load[i], strings[i]);
               }
               map.put("prevbdate", prevbdate);
               map.put("prevclose", prevclose);
               rowsToInsert.add(map);
               prevbdate = map.get("bdate");
               prevclose = map.get("close");
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
