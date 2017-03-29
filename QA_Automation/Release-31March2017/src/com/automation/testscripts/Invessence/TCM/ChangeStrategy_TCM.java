/********************************************************************
 *               Copyright by Mindcraft					*
 *               All rights reserved									*
 *********************************************************************
 *   Package Name space: com.automation.testscripts.Invessence             
 *   File name: ChangeStrategy.java				                   
 *   Description: This script is to change the startegy of the account that is already created.					
 *   Author: 	Deepali Chaudhari		                                      
 *   Date Created:  16-March-2017	                                               
 *   Version:                                                       
 *******************************************************************
 *   Change History:                                                                                             
 *                                                                                                                                 
 *    Date:          By:    Description: Generalized the method
 *******************************************************************/


package com.automation.testscripts.Invessence.TCM;

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

import com.automation.testscripts.PageObjects.ChangeStrategyPO;
import com.automation.testscripts.Utility.Invessence_Utility;
import com.automation.testscripts.Utility.Utility;

public class ChangeStrategy_TCM {
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
		myXLPath = Utility.readTestDataFromProperties("Properties/testdatalocation.properties","changestrategy_tcm");
		//dburl= Utility.readTestDataFromProperties("Properties/testdatalocation.properties","dbUrl");
	//	dbusername = Utility.readTestDataFromProperties("Properties/testdatalocation.properties","dbusername");
	//	dbpassword = Utility.readTestDataFromProperties("Properties/testdatalocation.properties","dbpassword");
		xData = Utility.xlRead(myXLPath,"TestData");
	  return xData;
		             
     }
	

@Test(dataProvider = "myTest")
public void changestrategy (String TCID,String vURL,String vBrowser,String username,String password,String investmentamount,
		String investmentgoal,String age,String status,String retireage,String objective,
		String projectionoption,String accounttype,String fname,String lname,String dob,String ssn,String phoneno,
		String email,String streetname,String city,String state,String zip,String regulatoryoption,
		String empstatus,String incomesrc,String employername,String occupation,String bfname ,String blname,
		String bdob,String bssn,String relationship,String sharepercent,String fundingtype,
		String investmentamt,String bankaccttype,String bankname,String nameofbankacct,String bankcity,
		String bankphone,String routingno,String bankacctno,String frequency,String trnamt,String trndate,String accountnumber,String clientaccountnumber,
		String changedobjective,String changedprojection,String percent,String vlogo,String radiobtnoption,
		String Execute,String Results) throws Exception {

	if (Execute.equals("Y")) {	
		try{			
			
			if (i == 0) {
				i = i + 1;
			}
			// Open the browser
				if (!(vBrowser.equals("FIREFOX") || vBrowser.equals("IE")|| vBrowser.equals("SAFARI") || vBrowser
							.equals("CHROME"))) 
				{
					xData[i][48] = "Browser not Supported";
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
		
					// Change the strategy of the portfolio
					ChangeStrategyPO.changestrategy(driver,clientaccountnumber, investmentamount, investmentgoal, age, status, retireage, changedobjective, projectionoption,radiobtnoption,changedprojection,percent);
					
				}
				}		
				 catch (Exception e) {
											e.printStackTrace();
											log.error("An exception occurred: " + Utility.getStackTrace(e));
				                             xData[i][48] = "Exception occured,Refer logs for Details -  Fail";
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
