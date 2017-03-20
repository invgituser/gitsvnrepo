package com.automation.testscripts.PageObjects;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.automation.testscripts.Utility.DBConnection;

public class AccountOpeningPO {
	//private static WebDriver driver;
	private static Logger log = Logger.getLogger(Logger.class.getName());
	
	//Account type Section in Account Opening
	
	public static boolean accounttype(WebDriver driver,String accounttype ) throws InterruptedException
	{	boolean accttype=false;
		try{
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'Account Type')]"));
		driver.findElement(By.xpath("//*[contains(text(),'"+accounttype +"')]/parent::div/parent::div")).click();
	//	driver.findElement(By.id("ctoForm:tdaccordian:j_idt63_content")).click();
	//	Actions builder = new Actions(driver);
	//	builder.moveToElement(el).click().perform();
		
		Thread.sleep(4000);
		driver.findElement(By.id("ctoForm:tdaccordian:tdp0next")).click();
		Thread.sleep(1000);
		log.info("Account Type Section entered");
		System.out.println("Account Type Section entered");
		accttype=true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			accttype=false;
		}
		return accttype;
	}
	
	public static boolean accountholder(WebDriver driver,String accounttype,String fname,String lname,String dob,String ssn,String phoneno,
			String email,String streetname,String city,String state,String zip,String bfname ,String blname,String bdob,
			String bssn) throws InterruptedException
	{boolean acctholder=false;
		try
		{
			//Primary account holder
			Thread.sleep(4000);
			
		//	driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:accHolderTabV']//a[contains(text(),'Joint Holder')]")).click();
			driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:accHolderTabV:ownerHolderPanel']//input[@placeholder='First Name']")).sendKeys(fname);
			driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:accHolderTabV:ownerHolderPanel']//input[@placeholder='Last Name']")).sendKeys(lname);
			driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:accHolderTabV:ownerHolderPanel']//input[@placeholder='MM/DD/YYYY']")).click();
			driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:accHolderTabV:ownerHolderPanel']//input[@placeholder='MM/DD/YYYY']")).sendKeys(dob);
			driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:accHolderTabV:ownerHolderPanel']//input[@placeholder='###-##-####']")).sendKeys(ssn);
			driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:accHolderTabV:ownerHolderPanel_content']//div//span")).click();
			driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:accHolderTabV:ownerHolderPanel']//input[@placeholder='Primary Phone']")).sendKeys(phoneno);
			driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:accHolderTabV:ownerHolderPanel']//input[@placeholder='Email Address']")).sendKeys(email);
			driver.findElement(By.id("ctoForm:tdaccordian:accHolderTabV:tdp1next")).click();
			Thread.sleep(2000);
			
			//Enter Joint account holder details
				if(accounttype.equals("Joint") || accounttype.equals("UTMA / UGMA"))
				{
					Thread.sleep(5000);
					if (accounttype.equals("Joint"))
					{
						driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:accHolderTabV']//a[contains(text(),'Joint Holder')]")).click();
					}
					else
					{
						driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:accHolderTabV']//a[contains(text(),'Minor Holder')]")).click();
					}
					driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:accHolderTabV:jointHolderPanel']//input[@placeholder='First Name']")).sendKeys(bfname);
					driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:accHolderTabV:jointHolderPanel']//input[@placeholder='Last Name']")).sendKeys(blname);
					driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:accHolderTabV:jointHolderPanel']//input[@placeholder='MM/DD/YYYY']")).click();
					driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:accHolderTabV:jointHolderPanel']//input[@placeholder='MM/DD/YYYY']")).sendKeys(bdob);
					driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:accHolderTabV:jointHolderPanel']//input[@placeholder='###-##-####']")).sendKeys(bssn);
					driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:accHolderTabV:jointHolderPanel_content']//div//span")).click();
					driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:accHolderTabV:jointHolderPanel']//input[@placeholder='Primary Phone']")).sendKeys(phoneno);
					driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:accHolderTabV:jointHolderPanel']//input[@placeholder='Email Address']")).sendKeys(email);
					driver.findElement(By.id("ctoForm:tdaccordian:accHolderTabV:tdp2next")).click();
					Thread.sleep(200);
				}
				
					log.info("Account Holder Section entered");
					System.out.println("Account Holder Section entered");
					acctholder=true;
	}
	catch(Exception e)
	{
		e.printStackTrace();
		acctholder=false;
	}
		return acctholder;
	
	}
	
	//Address for account opening
	
	public static boolean accountaddress(WebDriver driver,String streetname,String city,String state,String zip,String accounttype) throws InterruptedException
	{	boolean acctaddress=false;
		try
		{
			Thread.sleep(5000);	
				driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:addresstab:owneraddressPanel']//input[@placeholder='Residence Street Address']")).sendKeys(streetname);
				driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:addresstab:owneraddressPanel']//input[@placeholder='City']")).sendKeys(city);
				driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:addresstab:owneraddressPanel']//input[@placeholder='City']")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:addresstab:owneraddressPanel_content']//div[@id='ctoForm:tdaccordian:addresstab:tdppstate']")).click();
				driver.findElement(By.xpath("//*[@data-label='"+state+"']")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:addresstab:owneraddressPanel']//input[@placeholder='Zip Code']")).sendKeys(zip);
				
				driver.findElement(By.id("ctoForm:tdaccordian:addresstab:tdp3next")).click();
				Thread.sleep(5000);
				if(accounttype.equals("Joint") || accounttype.equals("UTMA / UGMA"))
				{
										
					driver.findElement(By.id("ctoForm:tdaccordian:addresstab:tdp4next")).click();
					Thread.sleep(3000);
					
				}
				log.info("Account address Section entered");
				System.out.println("Account address Section entered");
				acctaddress=true;
	}
	catch(Exception e)
	{
		e.printStackTrace();
		acctaddress=false;
	}
		return acctaddress;
	}
	
	//Regulatory Section
	
	public static boolean regulatory(WebDriver driver,String regulatoryoption) throws InterruptedException
	{  boolean regulatorystatus= false;
		try
		{
			Thread.sleep(5000);
			driver.findElement(By.xpath("//span[contains(text(),'"+ regulatoryoption +"')]/parent:: div//span")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("ctoForm:tdaccordian:regTabV:tdp5next")).click();
			Thread.sleep(1000);
			
			log.info("Regulaory Section entered");
			System.out.println("Regulaory Section entered");
			regulatorystatus= true;
	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			regulatorystatus= false;
		}
		return regulatorystatus;
	}
	
	
	//Employment section in Account Opening
	public static boolean employment(WebDriver driver,String empstatus,String incomesrc,String employername,String occupation,String accounttype ) throws InterruptedException
	{	boolean emp= false;
		try
		{
			Thread.sleep(4000);
			driver.findElement(By.xpath("//label[contains(text(),'Select Employment Status')]")).click();
				Thread.sleep(500);
				driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:employmenttab:tdeptype_panel']//li[@data-label='"+ empstatus +"']")).click();
				Thread.sleep(1000);
				if(empstatus.equals("Employed") || empstatus.equals("Self-Employed") )
				{
						driver.findElement(By.id("ctoForm:tdaccordian:employmenttab:tdepname")).sendKeys(employername);
						driver.findElement(By.id("ctoForm:tdaccordian:employmenttab:tdepoccupation")).sendKeys(occupation);
						
				}		
				else
				{						
						driver.findElement(By.id("ctoForm:tdaccordian:employmenttab:tdesource")).sendKeys(incomesrc);
						driver.findElement(By.id("")).click();
						Thread.sleep(500);
				}
				
				driver.findElement(By.id("ctoForm:tdaccordian:employmenttab:tdp6next")).click();
				Thread.sleep(5000);
				if(accounttype.equals("Joint"))
				{
					driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:employmenttab:jointaempPanel_content']/div//label[contains(text(),'Select Employment Status')]")).click();
					Thread.sleep(500);
					driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:employmenttab:tdestype_panel']//li[contains(text(),'"+ empstatus +"')]")).click();
					Thread.sleep(3000);
					if(empstatus.equals("Employed") || empstatus.equals("Self-Employed") )
					{
							driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:employmenttab:jointaempPanel_content']//input[@id='ctoForm:tdaccordian:employmenttab:tdesname']")).sendKeys(employername);
							driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:employmenttab:jointaempPanel_content']//input[@id='ctoForm:tdaccordian:employmenttab:tdesoccupation']")).sendKeys(occupation);
							
					}		
					else
					{						
							driver.findElement(By.id("ctoForm:tdaccordian:employmenttab:tdesource")).sendKeys(incomesrc);
							//driver.findElement(By.id("")).click();
							Thread.sleep(2000);
					}
					
					driver.findElement(By.id("ctoForm:tdaccordian:employmenttab:tdp7next")).click();
					Thread.sleep(500);
				}
				log.info("Employment Section entered");
				System.out.println("Employment Section entered");
				emp= true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			emp= false;
		}
		return emp;
	
	}
	
	
	public static boolean benificiary(WebDriver driver,String accounttype, String employername,String occupation,String bfname ,String blname,String bdob,
			String bssn,String relationship,
			String sharepercent) throws InterruptedException
	{	boolean benificiary= false;
		try
		{	Thread.sleep(5000);
			if(accounttype.equals("Individual") || accounttype.equals("Joint") || accounttype.equals("UTMA / UGMA"))
			{
				driver.findElement(By.xpath("//span[contains(text(),'I prefer not to name a beneficiary for this account.')]/parent::div//span")).click();
				Thread.sleep(3000);
			}
			else
			{
				driver.findElement(By.id("ctoForm:tdaccordian:tdbaddBene")).click();
				Thread.sleep(2000);
				driver.findElement(By.id("ctoForm:tdaccordian:tdbfname")).sendKeys(bfname);
				driver.findElement(By.id("ctoForm:tdaccordian:tdblname")).sendKeys(blname);
				driver.findElement(By.id("ctoForm:tdaccordian:tdbdob")).click();
				driver.findElement(By.id("ctoForm:tdaccordian:tdbdob")).sendKeys(bdob);
				driver.findElement(By.id("ctoForm:tdaccordian:tdbssn")).sendKeys(bssn);
				driver.findElement(By.id("ctoForm:tdaccordian:tdbrelation")).sendKeys(relationship);
				driver.findElement(By.xpath("//label[contains(text(),'Primary')]/parent::td/preceding-sibling::td/div//span")).click();
				driver.findElement(By.id("ctoForm:tdaccordian:tdbshare_input")).sendKeys(sharepercent);
				
				driver.findElement(By.id("ctoForm:tdaccordian:tdbsave")).click();
				Thread.sleep(1000);
									
			 }
			driver.findElement(By.id("ctoForm:tdaccordian:tdp8next")).click();
			Thread.sleep(3000);
			log.info("Benificiary Section entered");
			System.out.println("Benificiary Section entered");
			benificiary= true;
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
		benificiary= false;
	}
		return benificiary;
	}
	
	public static boolean funding(WebDriver driver,String accounttype, String fundingtype,String investmentamt,String bankaccttype,String bankname,String nameofbankacct,
			String bankcity,String bankphone,String routingno,String bankacctno,String frequency,String trnamt,String trndate,String recurringflag,String fundingflag,
			String accounttitle,String accounttype1 ,String deliveringfirm) throws InterruptedException
	{  boolean fund = false;
		try{
			Thread.sleep(4000);
		
			if(fundingflag.equals("Y"))
			{
				
			driver.findElement(By.xpath("//*[contains(text(),'"+ fundingtype +"')]/parent::div/following-sibling::div/div")).click();
			//Fund tab
				if(fundingtype.equals("ACH"))
				{
					Thread.sleep(1000);
					driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdfundAmt_input")).clear();
					driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdfundAmt_input")).sendKeys(investmentamt);
					driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdachtype_label")).click();
					Thread.sleep(100);
					driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:fundTabV:tdachtype_items']/li[contains(text(),'"+ bankaccttype +"')]")).click();
					Thread.sleep(100);
					driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdachbname")).sendKeys(bankname);
					driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdachbacctname")).sendKeys(nameofbankacct);
					driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdachbacctlocation")).sendKeys(bankcity);
					driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdachbacctphone")).sendKeys(bankphone);
					driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdachbacctaba")).sendKeys(routingno);
					driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdachbacctnum")).sendKeys(bankacctno);
					
					
				}
				else if(fundingtype.equals("ACAT"))
				{
					Thread.sleep(100);
					driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdacattitle")).sendKeys(accounttitle);
					driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdacatacctnum")).sendKeys(bankacctno);
					driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdacataccttype")).click();
					driver.findElement(By.xpath("//li[contains(text(),'"+accounttype1+"')]")).click();
					driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:event_input")).sendKeys(deliveringfirm);
					driver.findElement(By.xpath("//*[contains(text(),'Full Transfer')]/parent::td/preceding-sibling::td/div//span")).click();
					Thread.sleep(4000);
					
				}
				else
				{
					Thread.sleep(100);
					String accountmanaged = "Retail";
					String retailaccountnumber = "12345";
					String advisorid = "123";
					driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdtdretailflag_label")).click();
					driver.findElement(By.xpath("//li[contains(text(),'"+accountmanaged+"')]")).click();
					Thread.sleep(2000);
					driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdtdbacctno")).clear();
					driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdtdbacctno")).sendKeys(retailaccountnumber);
					driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdtdbadvid")).sendKeys(advisorid);;
				}
				
				driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdp9next")).click();
				Thread.sleep(2000);
				
			//Recurring tab
				if(recurringflag.equals("Y"))
				{
				//driver.findElement(By.xpath(" //*[@id='ctoForm:tdaccordian:fundTabV']//a[contains(text(),'Recurring')]")).click();
				Thread.sleep(1000);
				driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdrfreq")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:fundTabV:tdrfreq_items']/li[contains(text(),'"+ frequency +"')]")).click();
				Thread.sleep(1000);
				driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdrAmt_input")).clear();
				driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdrAmt_input")).sendKeys(trnamt);
				driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdrsdate")).clear();
				driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdrsdate")).sendKeys(trndate);
				driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdrbaccttype_label")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:fundTabV:tdrbaccttype_items']/li[contains(text(),'"+ bankaccttype +"')]")).click();
				Thread.sleep(1000);
				driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdrbname")).sendKeys(bankname);
				driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:j_idt404")).sendKeys(nameofbankacct);
				driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdrbloc")).sendKeys(bankcity);
				driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdrbphone")).sendKeys(bankphone);
				driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdrbaba")).sendKeys(routingno);
				driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdrbacctnum")).sendKeys(bankacctno);
				driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:fundTabV:tdp10save']/span")).click();
				Thread.sleep(3000);
				}
				else
				{
				//Optout recurring
				driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:fundTabV:tdroptoutflag']/div[2]/span")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:fundTabV:tdp10save']/span")).click();
				//Thread.sleep(3000);driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:fundTabV:tdp10save']/span")).click();
				Thread.sleep(3000);
					}
				
			}
			else
			{
				//Opt out of Funding
				driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:actfundingoptout")).click();
				Thread.sleep(1000);
				driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdp9save")).click();
				Thread.sleep(1000);				
			}
				log.info("Funding Section entered");
				System.out.println("Funding Section entered");
				fund = true;
			
	}
	catch(Exception e)
	{
		e.printStackTrace();
		fund = false;
	}
		return fund;
	}
	
	
	//Complete account opening scenarios
	
	public static boolean AccountopeningScenario(WebDriver driver,String dburl,String dbusername,String dbpassword,String accounttype,String fname,String lname,String dob,String ssn,String phoneno,
			String email,String streetname,String city,String state,String zip,String regulatoryoption,
			String empstatus,String incomesrc,String employername,String occupation,String bfname ,String blname,
			String bdob,String bssn,String relationship,String sharepercent,String fundingtype,
			String investmentamt,String bankaccttype,String bankname,String nameofbankacct,String bankcity,
			String bankphone,String routingno,String bankacctno,String accounttitle,String accounttype1 ,String deliveringfirm,String frequency,
			String trnamt,String trndate,String accountnumber,String clientaccountnumber,String recurringflag,String fundingflag) throws InterruptedException
	{
		boolean accountopen = false;
		try{
		
		//Select Account Type
		
		boolean accttype = AccountOpeningPO.accounttype(driver, accounttype);
		
		//Enter account holder details
		boolean acctholder =AccountOpeningPO.accountholder(driver, accounttype, fname, lname, dob, ssn, phoneno, email, streetname, city, state, zip, bfname, blname, bdob, bssn);
		
		//Enter address
		
		boolean acctaddress= AccountOpeningPO.accountaddress(driver, streetname, city, state, zip,accounttype);
		
		//Regulatory
		boolean acctaddressegulatory = AccountOpeningPO.regulatory(driver, regulatoryoption);
		
		//Employment
		
		boolean acctemp = AccountOpeningPO.employment(driver, empstatus, incomesrc, employername, occupation,accounttype);
		
		// Benificiary
		
		boolean accbenificiary = AccountOpeningPO.benificiary(driver, accounttype, employername, occupation, bfname, blname, bdob, bssn,
				relationship, sharepercent);
		
		//Funding
		
		boolean accfunding = AccountOpeningPO.funding(driver, accounttype, fundingtype, investmentamt, bankaccttype, bankname, nameofbankacct,
				bankcity, bankphone, routingno, bankacctno, frequency, trnamt, trndate,recurringflag,fundingflag,accounttitle,accounttype1 ,
				deliveringfirm);
		
		//Hit Submit button
		
		
		driver.findElement(By.xpath("//*[@id='ctoForm:submitBtnId']//span")).click();
		Thread.sleep(5000);
		//Result verification for Reporting
		System.out.println("#############################################");
		log.info("#######################################################");
		if(accttype && acctholder && acctaddress && acctaddressegulatory && acctemp && accbenificiary && accfunding)
		{
			log.info("PASS - Account Opening sections Entered Sucessfully");
			System.out.println("PASS - Account Opening sections Entered Sucessfully");
			accountopen = true;
			
		}
		else
		{
			log.info("Fail- Account Opening Failed.");
			System.out.println("Fail- Account Opening Failed.");
			accountopen = false;
		}
			
			
	}
		catch(Exception e)
		{
			e.printStackTrace();
			accountopen = false;
		}
			return accountopen;
		}

	
}
