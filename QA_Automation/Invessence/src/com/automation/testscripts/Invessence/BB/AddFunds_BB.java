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


package com.automation.testscripts.Invessence.BB;

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
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.testscripts.PageObjects.AccountOpeningPO;
import com.automation.testscripts.PageObjects.ChangeStrategyPO;
import com.automation.testscripts.Utility.Invessence_Utility;
import com.automation.testscripts.Utility.Utility;

public class AddFunds_BB {
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
		myXLPath = Utility.readTestDataFromProperties("Properties/testdatalocation.properties","addfunds");
		//dburl= Utility.readTestDataFromProperties("Properties/testdatalocation.properties","dbUrl");
	//	dbusername = Utility.readTestDataFromProperties("Properties/testdatalocation.properties","dbusername");
	//	dbpassword = Utility.readTestDataFromProperties("Properties/testdatalocation.properties","dbpassword");
		xData = Utility.xlRead(myXLPath,"TestData");
	  return xData;
		             
     }
	

@Test(dataProvider = "myTest")
public void addfunds (String TCID,String vURL,String vBrowser,String username,String password,String accounttype,String fundingtype,
		String investmentamt,String bankaccttype,String bankname,String nameofbankacct,String bankcity,
		String bankphone,String routingno,String bankacctno,String accounttitle,String accounttype1 ,String deliveringfirm,String frequency,
		String trnamt,String trndate,String accountnumber,String clientaccountnumber,String recurringflag,
		String fundingflag,String vlogo,String Execute,String Results) throws Exception {

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
					if (!clientaccountnumber.isEmpty() || !clientaccountnumber.equals("null"))
					{
						Thread.sleep(2000);
						WebElement fundingbtn = driver.findElement(By.xpath("//*[contains(text(),'"+clientaccountnumber+"')]/parent::td/parent::tr/parent::tbody/parent::table/following-sibling::table/following-sibling::table//*[contains(@id, 'fundButton')]"));
						fundingbtn.click();		
						Thread.sleep(8000);

						AccountOpeningPO.addfund(driver, accounttype, fundingtype, investmentamt, bankaccttype, bankname, nameofbankacct, bankcity, bankphone, routingno, bankacctno, frequency, trnamt, trndate, recurringflag, fundingflag, accounttitle, accounttype1, deliveringfirm);
					}
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
