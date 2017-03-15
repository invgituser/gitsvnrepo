package com.automation.testscripts.Invessence;


import org.testng.annotations.AfterTest;
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
import org.openqa.selenium.WebElement;
import com.automation.testscripts.Utility.Utility;


public class Registration  {
		
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
			asFailureLogFolderPath = Utility.readTestDataFromProperties("Properties/testdatalocation.properties","logfilepath");
			log.info("Log File will be saved at  "+ asFailureLogFolderPath);
			myXLPath = Utility.readTestDataFromProperties("Properties/testdatalocation.properties","appdirectsignup");
			xData = Utility.xlRead(myXLPath,"TestData");
		  return xData;
 		             
         }

	@Test(dataProvider = "myTest")
	public void wikibookcreator (String TCID,String vURL,String vBrowser,String fname,String lname,String email,String username,String password,String confirmpassword,String Execute,String Results) throws Exception {

		if (Execute.equals("Y")) {	
			try{			
				
				if (i == 0) {
					i = i + 1;
				}
				// Open the browser
					if (!(vBrowser.equals("FIREFOX") || vBrowser.equals("IE")|| vBrowser.equals("SAFARI") || vBrowser
								.equals("CHROME"))) 
					{
						xData[i][10] = "Browser not Supported";
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
					
					//Click on Register button
					
					WebElement registerbutton = driver.findElement(By.xpath("//*[@id='j_idt19']/span"));
					if (registerbutton.isDisplayed())
					{	System.out.println("Clicked on Register button");
					registerbutton.click();
					}
					Thread.sleep(4000);
								
						//Enter Details
						driver.findElement(By.xpath("//*[@id='j_idt15:firstName']")).sendKeys(fname);
						driver.findElement(By.xpath("//*[@id='j_idt15:lastName']")).sendKeys(lname);
						driver.findElement(By.xpath("//*[@id='[j_idt15:email']")).sendKeys(email);
						driver.findElement(By.xpath("//*[@id='j_idt15:userid']")).sendKeys(username);
						driver.findElement(By.xpath("//*[@id='j_idt15:pwd1']")).sendKeys(password);
						driver.findElement(By.xpath("//*[@id='j_idt15:pwd2']")).sendKeys(confirmpassword);
						driver.findElement(By.xpath("//*[@id='j_idt15:j_idt32']/span")).click();
						Thread.sleep(3000);
					/*	if(downloadfilelink.isDisplayed())
							{
								System.out.println("Document generation is sucessfull");
								log.info("Document generation is Sucessfull");
								xData[i][10] = "Pass -Document generation is Sucessfull";
								driver.findElement(By.linkText("Download the file")).click();
								Thread.sleep(3000);
							
							}
							else
							{
								System.out.println("Document generation is Failed");
								log.info("Document generation is Failed");
								xData[i][10] = "Fail -Document generation is Failed";
							}*/
					
					Utility.xlwrite(xData,scriptname);
					log.info("End of Loop for data " + TCID);
				   	driver.quit();
			}			
		}		
		 catch (Exception e) {
									e.printStackTrace();
									log.error("An exception occurred: " + Utility.getStackTrace(e));
		                             xData[i][10] = "Exception occured,Refer logs for Details -  Fail";
		                             Utility.xlwrite(xData,scriptname);
		                             log.info("End of Loop for data " + TCID);
		         			    
								
		 					 } 
	}
		i++;
	}
	@AfterTest
	public void shutDown() throws IOException, Exception {
	    driver.quit();
	  }

	
}
 

	

	
