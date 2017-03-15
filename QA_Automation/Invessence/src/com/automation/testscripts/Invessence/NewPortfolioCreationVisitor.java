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
import com.automation.testscripts.Utility.Invessence_Utility;
import com.automation.testscripts.Utility.Utility;


public class NewPortfolioCreationVisitor  {
		
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
	
		porfoliocreationPO myporfoliocreationPO = new porfoliocreationPO();
		
		
		
		@DataProvider(name = "myTest")
        public String [][] createData() throws IllegalFormatException, IOException, EncryptedDocumentException, InvalidFormatException, IllegalClassFormatException {
			 PropertyConfigurator.configure("Properties/Log4j.properties"); 
			myXLPath = Utility.readTestDataFromProperties("Properties/testdatalocation.properties","portfoliocreation");
			xData = Utility.xlRead(myXLPath,"TestData");
		  return xData;
 		             
         }

	@Test(dataProvider = "myTest")
	public void newaccount (String TCID,String vURL,String vBrowser,String username,String password,String investmentamount,String investmentgoal,String age,String status,String retireage,String objective,String projectionoption,String Execute,String Results) throws Exception {

		if (Execute.equals("Y")) {	
			try{			
				
				if (i == 0) {
					i = i + 1;
				}
				// Open the browser
					if (!(vBrowser.equals("FIREFOX") || vBrowser.equals("IE")|| vBrowser.equals("SAFARI") || vBrowser
								.equals("CHROME"))) 
					{
						xData[i][13] = "Browser not Supported";
						System.out.println("Unsupported Brow");
						log.info("Unsupported Brow");
						Assert.fail("Unsupported Brow");
					} 
					else {

						//Open browser
					driver = Utility.Browser(vBrowser);
					
					//Opening the URL
					System.out.println("Navigating to URL");
					driver.get(vURL);
					Thread.sleep(1000);
					//Login
										
					//Invessence_Utility.login(driver, username, password, vBrowser);
					
					// Getstarted Visitor 
					//driver.findElement(By.id("j_idt18")).click();
					Invessence_Utility.getstartedpreuat(driver, vBrowser);
					
					//Portfolio creation
				//	driver.findElement(By.xpath("//a[contains(text(), ' New Account')]")).click();
					boolean portfoliocreation = porfoliocreationPO.portfoliocreation(driver, investmentamount, investmentgoal, age, status, retireage,objective,projectionoption);
					if(portfoliocreation)
					{
						xData[i][13]= "Pass -New Portfolio is created";
						//Open account
						
					}
					else
					{
						xData[i][13]= "Fail Portfolio Creation Failed.";
					}
					
					Utility.xlwrite(xData,scriptname);
					log.info("End of Loop for data " + TCID);
				   	driver.quit();
			}			
		}		
		 catch (Exception e) {
									e.printStackTrace();
									log.error("An exception occurred: " + Utility.getStackTrace(e));
		                             xData[i][13] = "Exception occured,Refer logs for Details -  Fail";
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
 

	

	
