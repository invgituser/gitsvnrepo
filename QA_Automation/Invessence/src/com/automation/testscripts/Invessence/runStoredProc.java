package com.automation.testscripts.Invessence;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.io.File;
import java.io.IOException;
import java.lang.instrument.IllegalClassFormatException;
import java.util.IllegalFormatException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.automation.testscripts.PageObjects.porfoliocreationPO;
import com.automation.testscripts.Utility.DBConnection;
import com.automation.testscripts.Utility.Invessence_Utility;
import com.automation.testscripts.Utility.Utility;


public class runStoredProc  {
		
		private static WebDriver driver;
		
		String myXLPath;
		static int xRows;
		static int xCols;
		static String xData[][]; // 2-Dimension array
		public String testreport;
		public File resultdir;
		public static String resultpath,asFailureLogFolderPath;
		static String placeresultsin;
		 int i=0;
		int j;
		String scriptname = this.getClass().getSimpleName();
		private static Logger log = Logger.getLogger(Logger.class.getName());
		
		
		@DataProvider(name = "myTest")
        public String [][] createData() throws IllegalFormatException, IOException, EncryptedDocumentException, InvalidFormatException, IllegalClassFormatException {
			 PropertyConfigurator.configure("Properties/Log4j.properties"); 
			myXLPath = Utility.readTestDataFromProperties("Properties/testdatalocation.properties","dbmethod");
			xData = Utility.xlRead(myXLPath,"TestData");
		  return xData;
 		             
         }

	@Test(dataProvider = "myTest")
	public void ClientAccountId (String TCID,String dbURL,String dbusername,String dbpassword,String Execute,String Results) throws Exception {

		if (Execute.equals("Y")) {	
			try{			
				
				if (i == 0) {
					i = i + 1;
				}
				String accountnumber = "2893";
			    String Amount = "50000";
			    String spopenacct = "testing.sp_emulate_td_openaccount("+accountnumber+")";
				String spactivateacct = "testing.sp_emulate_td_activateaccount("+accountnumber+","+Amount+")";
				DBConnection.runProcedure(dbURL,dbusername,dbpassword,spopenacct);
				Thread.sleep(3000);
				DBConnection.runProcedure(dbURL,dbusername,dbpassword,spactivateacct);
				String sqlQuery = "select status from invdb.ext_acct_info where acctnum="+accountnumber;
				String[] status = DBConnection.selectDataFromDB(dbURL,dbusername,dbpassword,sqlQuery);
				System.out.println(status);
				if (status.equals("A")) 
				{
					System.out.println("Account is active");
					
				
				}			
					//Utility.xlwrite(xData,scriptname);
					log.info("End of Loop for data " + TCID);

			}			
				
		 catch (Exception e) {
									e.printStackTrace();
									log.error("An exception occurred: " + Utility.getStackTrace(e));
		                           //  xData[i][13] = "Exception occured,Refer logs for Details -  Fail";
		                             Utility.xlwrite(xData,scriptname);
		                             log.info("End of Loop for data " + TCID);
		                           //  driver.quit();
		         			    
								
		 					 } 
	}
		i++;
	}
	/*@AfterTest
	public void shutDown() throws IOException, Exception {
	    driver.quit();
	  }*/

	
}
 

	

	
