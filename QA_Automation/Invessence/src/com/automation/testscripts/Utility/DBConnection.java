package com.automation.testscripts.Utility;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DBConnection 
{
	/**
	 * Method has been created by Deepali Chaudhari on 14-03-2017
	 * This method fetches data from Database
	 * @param dbName
	 * @param userName
	 * @param password
	 * @param sqlQuery
	 * @return
	 */
	
	static String[] queryResults ;
	public static String[]  selectDataFromDB(String dbUrl,String username, String password, String sqlQuery) throws ClassNotFoundException, SQLException
	{
		try{
					
				
			//Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"		
		   // String dbUrl = "jdbc:mysql://preuat.invessence.com:3306/PREUAT-WEBUSER";					
		
			//Database Username		
		//	username = "webuser";	
		    
			//Database Password		
		//	password = "inv3ss3nc3u530n1y";				
		
			//Query to Execute		
		//	sqlQuery = "select clientAccountID from ext_acct_info where acctnum=2257;";	
		    
			    //Load mysql jdbc driver		
			    Class.forName("com.mysql.jdbc.Driver").newInstance();		
		
				//Create Connection to DB		
			Connection con = DriverManager.getConnection(dbUrl,username,password);
			
			//Execute the stored Proc
			// Execute the SQL Query. Store results in ResultSet		
			Statement statement = con.createStatement();
			ResultSet records= statement.executeQuery(sqlQuery);	
			ResultSetMetaData listOfColumns = records.getMetaData();//to find dynamic number of columns fetched based on  query
			int columnCount = listOfColumns.getColumnCount();
			/*
			String storedProc = "{call stored_proc()}";
			statement.execute(storedProc);*/
			
			// While Loop to iterate through all data and print results		
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
  			 // closing DB Connection		
  			con.close();	
		 }
		catch( Exception e )
	    {
	        e.printStackTrace();
	        System.out.println( "Error connecting to database.  Error: "+e.getMessage() );
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
