/********************************************************************
 *               Copyright by Mindcraft					*
 *               All rights reserved									*
 *********************************************************************
 *   Package Name space: com.automation.testscripts.Invessence             
 *   File name: AdvisorLogin.java				                   
 *   Description: This script is to verify if the advisor details displayed are correct.					
 *   Author: 	Deepali Chaudhari		                                      
 *   Date Created:  27-March-2017	                                               
 *   Version:                                                       
 *******************************************************************
 *   Change History:                                                                                             
 *                                                                                                                                 
 *    Date:          By:    Description: Generalized the method
 *******************************************************************/


package com.automation.testscripts.Invessence.BB;

import java.io.IOException;
import java.lang.instrument.IllegalClassFormatException;
import java.util.IllegalFormatException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.testscripts.PageObjects.AdvisorLoginPO;
import com.automation.testscripts.PageObjects.ChangeStrategyPO;
import com.automation.testscripts.Utility.Invessence_Utility;
import com.automation.testscripts.Utility.Utility;

public class AdvisorLogin_PendingAccounts_BB {
	private static WebDriver driver;
	static String xData[][]; // 2-Dimension array
	String scriptname = this.getClass().getSimpleName();
	private static Logger log = Logger.getLogger(Logger.class.getName());
	int i=0;
	int j;
	String myXLPath;
	
	
	
	@DataProvider(name = "myTest")
    public String [][] createData() throws IllegalFormatException, IOException, EncryptedDocumentException, InvalidFormatException, IllegalClassFormatException {
		 PropertyConfigurator.configure("Properties/Log4j.properties"); 
		myXLPath = Utility.readTestDataFromProperties("Properties/testdatalocation.properties","advisorlogin_pendingaccount");
		//dburl= Utility.readTestDataFromProperties("Properties/testdatalocation.properties","dbUrl");
	//	dbusername = Utility.readTestDataFromProperties("Properties/testdatalocation.properties","dbusername");
	//	dbpassword = Utility.readTestDataFromProperties("Properties/testdatalocation.properties","dbpassword");
		xData = Utility.xlRead(myXLPath,"TestData");
	  return xData;
		             
     }
	

@Test(dataProvider = "myTest")
public void pendingaccountverificcation (String TCID,String vURL,String vBrowser,String username,String password,
		String acctnum,String lname,String fname,String strategy,String goal,
		String investedamount,String vlogo,String Process,String Execute,String Results) throws Exception {

	if (Execute.equals("Y")) {	
		try{			
			
			if (i == 0) {
				i = i + 1;
			}
			// Open the browser
				if (!(vBrowser.equals("FIREFOX") || vBrowser.equals("IE")|| vBrowser.equals("SAFARI") || vBrowser
							.equals("CHROME"))) 
				{
					xData[i][14] = "Browser not Supported";
					System.out.println("Unsupported Brow");
					log.info("Unsupported Brow");
					Assert.fail("Unsupported Brow");
				} 
				else
				{
	
						//Open browser
					driver = Utility.Browser(vBrowser);
					
					//Opening the URL
					System.out.println("Navigating to URL");
					driver.get(vURL);
					
					//Login
													
					Invessence_Utility.login(driver, username, password, vBrowser,vlogo);
		
					// Login with an advisor and verify details on the screen are correct					
					
					AdvisorLoginPO.pending(driver, acctnum,strategy,goal,investedamount,vlogo,Process);
					
					
				}
				}		
				 catch (Exception e) {
											e.printStackTrace();
											log.error("An exception occurred: " + Utility.getStackTrace(e));
				                             xData[i][14] = "Exception occured,Refer logs for Details -  Fail";
				                             Utility.xlwrite(xData,scriptname);
				                             log.info("End of Loop for data " + TCID);
				                             Assert.fail("Exception occured,Refer logs for Details -  Fail");
				                           //  driver.quit();
				         			    
										
				 					 } 
					System.out.println("#############################################");
					log.info("#############################################");	
					Utility.xlwrite(xData,scriptname);
					log.info("End of Loop for data " + TCID);
				   	driver.quit();
					
			}
				
				i++;
			}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
