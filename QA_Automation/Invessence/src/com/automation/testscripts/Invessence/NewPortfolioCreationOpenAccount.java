package com.automation.testscripts.Invessence;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.io.File;
import java.io.IOException;
import java.lang.instrument.IllegalClassFormatException;
import java.util.IllegalFormatException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.automation.testscripts.PageObjects.AccountOpeningPO;
import com.automation.testscripts.PageObjects.porfoliocreationPO;
import com.automation.testscripts.Utility.DBConnection;
import com.automation.testscripts.Utility.Invessence_Utility;
import com.automation.testscripts.Utility.Utility;


public class NewPortfolioCreationOpenAccount  {
		
		private static WebDriver driver;
		
		String myXLPath;
		static int xRows;
		static int xCols;
		static String xData[][]; // 2-Dimension array
		public String testreport;
		public File resultdir;
		public static String resultpath,asFailureLogFolderPath;
		static String placeresultsin;
		public static String	dburl,dbusername,dbpassword;
		 int i=0;
		int j;
		String scriptname = this.getClass().getSimpleName();
		private static Logger log = Logger.getLogger(Logger.class.getName());
	
		porfoliocreationPO myporfoliocreationPO = new porfoliocreationPO();
		
		
		@DataProvider(name = "myTest")
        public String [][] createData() throws IllegalFormatException, IOException, EncryptedDocumentException, InvalidFormatException, IllegalClassFormatException {
			 PropertyConfigurator.configure("Properties/Log4j.properties"); 
			myXLPath = Utility.readTestDataFromProperties("Properties/testdatalocation.properties","openaccount");
			dburl= Utility.readTestDataFromProperties("Properties/testdatalocation.properties","dbUrl");
			dbusername = Utility.readTestDataFromProperties("Properties/testdatalocation.properties","dbusername");
			dbpassword = Utility.readTestDataFromProperties("Properties/testdatalocation.properties","dbpassword");
			xData = Utility.xlRead(myXLPath,"TestData");
		  return xData;
 		             
         }
		

	@Test(dataProvider = "myTest")
	public void newaccount (String TCID,String vURL,String vBrowser,String username,String password,String investmentamount,
			String investmentgoal,String age,String status,String retireage,String objective,
			String projectionoption,String accounttype,String fname,String lname,String dob,String ssn,String phoneno,
			String email,String streetname,String city,String state,String zip,String regulatoryoption,
			String empstatus,String incomesrc,String employername,String occupation,String bfname ,String blname,
			String bdob,String bssn,String relationship,String sharepercent,String fundingtype,
			String investmentamt,String bankaccttype,String bankname,String nameofbankacct,String bankcity,
			String bankphone,String routingno,String bankacctno,String accounttitle,String accounttype1 ,String deliveringfirm,String frequency,
			String trnamt,String trndate,String accountnumber,String clientaccountnumber,String recurringflag,
			String fundingflag,String vlogo,
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
						xData[i][55] = "Browser not Supported";
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
					
					//Login
					
					
					Invessence_Utility.login(driver, username, password, vBrowser,vlogo);
					
					//Portfolio creation
					driver.findElement(By.xpath("//a[contains(text(), ' New Account')]")).click();
					boolean portfoliocreation = porfoliocreationPO.portfoliocreation(driver, investmentamount, investmentgoal, age, status, retireage,objective,projectionoption,clientaccountnumber);
					if(portfoliocreation)
					{
						System.out.println("Pass -New Portfolio is created");
						log.info("Pass -New Portfolio is created");
						//open account
						
						WebElement openaccountbtn = driver.findElement(By.xpath("//span[contains(text(),'Open Account')]"));
						openaccountbtn.click();
						Thread.sleep(3000);
						accountnumber = driver.getCurrentUrl();
						System.out.println("URL is :"+ accountnumber);
						accountnumber =StringUtils.substringAfterLast(accountnumber, "=");
						System.out.println("acct nos is :"+ accountnumber);
						
						boolean accountopen = AccountOpeningPO.AccountopeningScenario(driver, vURL, username, password, accounttype, fname, lname, dob, ssn, phoneno, email, streetname, city, state, zip, regulatoryoption, empstatus, incomesrc, employername, occupation, bfname, blname, bdob, bssn, relationship, sharepercent, fundingtype, investmentamt, bankaccttype, bankname, nameofbankacct, bankcity, bankphone, routingno, bankacctno, accounttitle, accounttype1, deliveringfirm, frequency, trnamt, trndate, accountnumber, clientaccountnumber,recurringflag, fundingflag);
						if(accountopen)
						 	{
								log.info("PASS - Account Opening sections Entered Sucessfully");
								System.out.println("PASS - Account Opening sections Entered Sucessfully");
								// Verify in database	
								
								String sqlQuery = "select acctnum from invdb.user_trade_profile  where acctnum="+accountnumber;
								String[] acctentry = DBConnection.selectDataFromDB(dburl,dbusername,dbpassword,sqlQuery);
								System.out.println(acctentry[0]);
								xData[i][49]=acctentry[0];
								if (acctentry[0].equals(accountnumber))
								{
									System.out.println("Pass -Account TD from is Submitted Sucessfully");
									log.info("Pass - Account TD from is Submitted Sucessfully");
									System.out.println("#############################################");
									log.info("#############################################");	
									//Emulation of account opening and accoutn activation by running stored procedure
									
									String spopenacct = "testing.sp_emulate_td_openaccount("+accountnumber+")";
									String spactivateacct = "testing.sp_emulate_td_activateaccount("+accountnumber+","+investmentamt+")";
									DBConnection.runProcedure(dburl,dbusername,dbpassword,spopenacct);
									Thread.sleep(3000);
									DBConnection.runProcedure(dburl,dbusername,dbpassword,spactivateacct);
									Thread.sleep(2000);
									String sqlQuery1 = "select clientAccountID from invdb.ext_acct_info where acctnum="+accountnumber;
									String[] clientacctnum = DBConnection.selectDataFromDB(dburl,dbusername,dbpassword,sqlQuery1);
									System.out.println(clientacctnum[0]);
									xData[i][50]=clientacctnum[0];
									Thread.sleep(2000);
									String sqlQuery2 = "select status from invdb.ext_acct_info where acctnum="+accountnumber;
									String[] status2 = DBConnection.selectDataFromDB(dburl,dbusername,dbpassword,sqlQuery2);
									System.out.println(status2[0]);
									if (status2[0].equals("A")) 
									{
										System.out.println("Pass- Account is active sucessfully");
										log.info("Pass -Account is active Sucessfully");
										xData[i][55]= "Pass -Account is active Sucessfully";
									}
									else
									{
										System.out.println("Fail- Account activation failed");
										log.info("Fail - Account is active");
										xData[i][55]= "Fail - Account is active";
										Assert.fail("Fail - Account is active");
										System.out.println("#############################################");
										log.info("#############################################");	
									}
								
								}
							else
							{
								System.out.println("Fail - Account TD from Submission failed");
								log.info("Fail - Account TD from Submission failed");
								xData[i][55]= "Fail - Account TD from Submission failed";
								System.out.println("#############################################");
								log.info("#############################################");	
								Assert.fail("Fail - Account TD from Submission failed");
							}				
						}
						else
						{
							log.info("Fail- Account Opening Failed.");
							System.out.println("Fail- Account Opening Failed.");
							xData[i][55]= "Fail- Account Opening Failed.";
							Assert.fail("Fail- Account Opening Failed.");
						}
						
					}
					else
					{
						System.out.println("Fail Portfolio Creation Failed.");
						log.info("Fail Portfolio Creation Failed.");
						xData[i][55]= "Fail Portfolio Creation Failed.";
						Assert.fail("Fail Portfolio Creation Failed.");
					}
					
			}	
					
					
		}		
		 catch (Exception e) {
									e.printStackTrace();
									log.error("An exception occurred: " + Utility.getStackTrace(e));
		                             xData[i][55] = "Exception occured,Refer logs for Details -  Fail";
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
	/*@AfterTest
	public void shutDown() throws IOException, Exception {
	    driver.quit();
	  }*/

	
}
 

	

	
