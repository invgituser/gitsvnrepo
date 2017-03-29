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
import java.sql.SQLException;
import java.sql.Statement;

public class SQLserverConnection {
	/**
	 * @param args
	 */
	public SQLserverConnection() throws ClassNotFoundException, SQLException {
			try{
				
				String url = "jdbc:jtds:sqlserver://10.2.1.19:1433/V3B2"; //URL of sql server
				String driver = "net.sourceforge.jtds.jdbc.Driver"; //Driver for Sql Server databases
				String dbname =""; 
				String userName= ""; 
				String password =""; 

				//Class.forName("net.sourceforge.jtds.jdbc.Driver");	
				Class.forName(driver).newInstance(); //create object of Driver class 
				Connection conn = null;  //Create object of Connection object 
			    conn=DriverManager.getConnection(url+dbname,userName,password); //connection will be established from this line 

				
				System.out.println("DATABASE NAME IS:"  + conn.getMetaData().getDatabaseProductName());
					Statement sta = conn.createStatement();
					String Sql = "select clientAccountID from ext_acct_info where acctnum='2257'";
					ResultSet rs = sta.executeQuery(Sql);
					while (rs.next()) {
						System.out.println(rs.getString("PT_ID"));
					}
					conn.close();
		      }
					catch( Exception e )
				    {
				        e.printStackTrace();
				        System.out.println( "Error connecting to database.  Error: "+e.getMessage() );
				    }
			 
	}
	

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {
		new SQLserverConnection();
	}
}
