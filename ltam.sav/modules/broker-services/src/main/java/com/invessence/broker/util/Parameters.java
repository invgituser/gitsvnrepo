//package com.invessence.broker.util;
//
//import org.springframework.beans.factory.annotation.*;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
///**
// * Created by abhangp on 1/19/2016.
// */
//@Configuration
//public class Parameters
//{
//
//   public static String baseDirectory;
//   public static String sqlInsertNewaccounts;
//   public static String sqlInsertPosition;
//   public static String sqlInsertTransaction;
//
//   @Autowired
//   public void setBaseDirectory(@Value("${baseDirectory}")String baseDirectory)
//   {
//      Parameters.baseDirectory = baseDirectory;
//   }
//   @Autowired
//   public void setSqlInsertNewaccounts(@Value("${sqlInsertNewaccounts}")String sqlInsertNewaccounts)
//   {
//      Parameters.sqlInsertNewaccounts = sqlInsertNewaccounts;
//   }
//   @Autowired
//   public void setSqlInsertPosition(@Value("${sqlInsertPosition}")String sqlInsertPosition)
//   {
//      Parameters.sqlInsertPosition = sqlInsertPosition;
//   }
//   @Autowired
//   public void setSqlInsertTransaction(@Value("${sqlInsertTransaction}")String sqlInsertTransaction)
//   {
//      Parameters.sqlInsertTransaction = sqlInsertTransaction;
//   }
//}
