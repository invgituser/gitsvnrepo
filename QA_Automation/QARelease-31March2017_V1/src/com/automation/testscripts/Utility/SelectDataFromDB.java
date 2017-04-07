/********************************************************************
 *               Copyright by Mindcraft					*
 *               All rights reserved									*
 *********************************************************************
 *   Package Name space: com.automation.testscripts.Utility             
 *   File name: SelectDataFromDB.java				                   
 *   Description: This script is to select data from Database						
 *   Author: 	Deepali Chaudhari		                                      
 *   Date Created:  14-March-2017	                                               
 *   Version:                                                       
 *******************************************************************
 *   Change History:                                                                                             
 *                                                                                                                                 
 *    Date: 14-03-2017         By:    Description: Generalized the method
 *******************************************************************/


package com.automation.testscripts.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.String;

public class SelectDataFromDB {
	static String[] queryResults ;
	
	/**
	 * Method has been created by Deepali Chaudhari on 14-03-2017
	 * This method fetches data from Database
	 * @param dbName
	 * @param userName
	 * @param password
	 * @param sqlQuery
	 * @return
	 */
	public static String[] selectDataFromDB(String dbName,String userName, String password, String sqlQuery, String sqlServerIp) {
	 try{
		   // String url = "jdbc:jtds:sqlserver://10.2.1.19:1433" + "/" + dbName; //URL of sql server
		    String url = "preuat.invessence.com "; //URL of sql server
			String sqlServerDriver = "net.sourceforge.jtds.jdbc.Driver"; //Driver for Sql Server databases	
			Class.forName(sqlServerDriver).newInstance(); //create object of Driver class 
			Connection conn = null;  //Create object of Connection object 
		    conn = DriverManager.getConnection(url, userName, password); //connection will be established from this line 
			System.out.println("DATABASE NAME IS:"  + conn.getMetaData().getDatabaseProductName());
			Statement sqlStatement = conn.createStatement();
			ResultSet records = sqlStatement.executeQuery(sqlQuery);
			ResultSetMetaData listOfColumns = records.getMetaData();//to find dynamic number of columns fetched based on  query
			int columnCount = listOfColumns.getColumnCount();

			while (records.next()) {
				for (int i = 1; i < columnCount; i++ ) {
				  queryResults = new String[columnCount + 1];	
				  System.out.println("-------"+records.getString(columnCount));
				  //queryResults = new String[i];
				  if(queryResults!=null){
					 // appendValueToArray(queryResults, records.getString(columnCount));
					  appendValueToArrayNew(queryResults, records.getString(columnCount));
				  }
				}
			}
			conn.close();
	  }
	 catch(Exception e)
	  {
		   e.printStackTrace();
		   System.out.println( "Error connecting to database.  Error: "+e.getMessage());
	  }
		return queryResults;	 
	}

	/**
	 * Method has been created by Deepali Chaudhari on 14-03-2017
	 * This method appends multiple results retrived from DB
	 * @param queryResults
	 * @param newString
	 * @return
	 */
	private static String[] appendValueToArray(String[] queryResults, String newString) {		 
		ArrayList<String> temp = new ArrayList<String>(Arrays.asList(queryResults));
		temp.add(newString);
		return temp.toArray(new String[]{});
	 
	  }
	
	
	private static String[] appendValueToArrayNew(String[] queryResults, String newString) {	
		String[] newArray = new String[queryResults.length];
		newArray = queryResults;
		newArray[queryResults.length - 1] = newString;
		return newArray;
	}
}
