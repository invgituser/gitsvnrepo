package com.automation.testscripts.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLserverConnection1 {
	/**
	 * @param args
	 */
	public SQLserverConnection1(CharSequence lname,CharSequence fname) throws ClassNotFoundException, SQLException {
			try{
				
				String url = "jdbc:jtds:sqlserver://10.2.1.19:1433/"; //URL of sql server
				String driver = "net.sourceforge.jtds.jdbc.Driver"; //Driver for Sql Server databases
				String dbname ="V3B2"; 
				String userName= "progno"; 
				String password ="progno"; 

				//	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
				Class.forName(driver).newInstance(); //create object of Driver class 
				Connection conn = null;  //Create object of Connection object 
			    conn=DriverManager.getConnection(url+dbname,userName,password); //connection will be established from this line 

				
				System.out.println("DATABASE NAME IS:"  + conn.getMetaData().getDatabaseProductName());
					Statement sta = conn.createStatement();
					String Sql = "select * from MST_PATIENT where pt_fname = '"+ fname +"'and pt_lname = '"+ lname +"'";
					System.out.println("SQL Statement is : "+Sql);
					ResultSet rs = sta.executeQuery(Sql);
					while (rs.next()) {
						System.out.println(rs.getString("PT_CHART_NO"));
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
		CharSequence lname="Smith";
		CharSequence fname="Donna1";
		
		new SQLserverConnection1(lname,fname);
	}
}
