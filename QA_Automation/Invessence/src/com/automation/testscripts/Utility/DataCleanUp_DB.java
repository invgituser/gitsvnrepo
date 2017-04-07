package com.automation.testscripts.Utility;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DataCleanUp_DB 
{
	/**
	 * Method has been created by Deepali Chaudhari on 07-04-2017
	 * This method deletes data from Database
	 * @param dbName
	 * @param userName
	 * @param password
	 * @param sqlQuery
	 * @return
	 */
	
	public static String dburl,dbusername,dbpassword;
	
		
	public static void  DeleteDataFromDB(String dbUrl,String username, String password)
	{
		try{
			
			dburl= Utility.readTestDataFromProperties("Properties/testdatalocation.properties","proddbUrl");
			dbusername = Utility.readTestDataFromProperties("Properties/testdatalocation.properties","dbusername");
			dbpassword = Utility.readTestDataFromProperties("Properties/testdatalocation.properties","dbpassword");
			
			String spcleanup = "testing.cleandb_for_testing()";
			DBConnection.runProcedure(dburl,dbusername,dbpassword,spcleanup);
			Thread.sleep(5000);
			
		 }
		catch( Exception e )
	    {
	        e.printStackTrace();
	        System.out.println( "Error connecting to database.  Error: "+e.getMessage() );
	    }	
		
	}	
		
}
