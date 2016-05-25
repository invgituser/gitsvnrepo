package com.invessence.rbsa.processors;

import java.io.*;
import java.util.*;

import com.google.common.base.*;
import com.google.common.collect.*;
import org.apache.log4j.Logger;

public class FileProcessorUtil
{
   private static Logger logger = Logger.getLogger(FileProcessorUtil.class.getName());

   static File getFile(String directory) throws IOException
   {
      File localDirectory = determineLocalDirectory(directory);
      List<String> filesToProcess = filterProcessedFiles(localDirectory);
      if (filesToProcess != null) {
         if (filesToProcess.size() != 1)
         {
            logger.error("More than one file to process " + Joiner.on(",").join(filesToProcess) + " .. skipping DB update");
            return null;
         }
         File filename = new File(localDirectory.getAbsolutePath() + File.separator + filesToProcess.get(0));
         if (filename.isDirectory()) {
            logger.info("Is directory " + filename.getName() + " .. skipping");
            return null;
         }
         return filename;
      }
      else {
         logger.error("Invalid Path, no files  .. skipping DB update");
         return null;
      }
   }

   static ArrayList<File> getFileToProcess(String directory) throws IOException
   {
      File localDirectory = determineLocalDirectory(directory);
      List<String> filesToProcess = filterProcessedFiles(localDirectory);
      if (filesToProcess != null) {
         ArrayList<File> listoffiles = new ArrayList<File>();
         int count=0;
         for (String file : filesToProcess) {
            if (! file.contains("camel")) {
               File fundfile =  new File(localDirectory.getAbsolutePath() + File.separator + file);
               listoffiles.add(count, fundfile);
               count++;
            }
         }
         return listoffiles;
      }
      else {
         logger.error("Invalid Path, no files  .. skipping DB update");
         return null;
      }
   }

   private static File determineLocalDirectory(String directory) throws IOException
   {
      Properties properties = new Properties();
      properties.load(FileProcessorUtil.class.getResourceAsStream("/rsba.properties"));
      return new File(properties.getProperty("rsba.localdir") + File.separator + directory);
   }

   private static List<String> filterProcessedFiles(File localDirectory)
   {
      return Lists.newLinkedList(Iterables.filter(Arrays.asList(localDirectory.list()), new Predicate<String>()
      {
         @Override
         public boolean apply(String s)
         {
            return !s.endsWith("csv_processed");
         }
      }));
   }

   static String extractDateFromName(String name)
   {
      String[] tokens = name.split("\\.");
      return tokens[tokens.length - 2];
   }

   static void renameProcessedFile(File fileToProcess)
   {
      if (fileToProcess != null)
      {
         File processedFile = new File(fileToProcess.getAbsolutePath() + "_processed");
         if (processedFile.exists())
         {
            processedFile.delete();
         }
         if (!fileToProcess.renameTo(processedFile))
         {
            logger.error("Error renaming " + fileToProcess.getName());
         }
      }
   }
}
