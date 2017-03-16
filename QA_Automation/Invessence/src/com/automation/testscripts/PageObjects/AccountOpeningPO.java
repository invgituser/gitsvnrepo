package com.automation.testscripts.PageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

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
			Thread.sleep(200);
			
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
	
	public static boolean funding(WebDriver driver,String accounttype, String fundingtype,String investmentamt,String bankaccttype,String bankname,String nameofbankacct,String bankcity,String bankphone,
			String routingno,String bankacctno,String frequency,String trnamt,String trndate) throws InterruptedException
	{  boolean fund = false;
		try{
			Thread.sleep(5000);
		//driver.findElement(By.xpath(" //*[@id='ctoForm:tdaccordian:fundTabV']//a[contains(text(),'Fund')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[contains(text(),'"+ fundingtype +"')]/parent::div/following-sibling::div/div")).click();
		//Fund tab
				Thread.sleep(100);
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
				driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdp9next")).click();
				Thread.sleep(2000);
				
				//Recurring tab
				/*driver.findElement(By.xpath(" //*[@id='ctoForm:tdaccordian:fundTabV']//a[contains(text(),'Recurring')]")).click();
				Thread.sleep(100);
				driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdrfreq")).click();
				Thread.sleep(100);
				driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:fundTabV:tdrfreq_items']/li[contains(text(),'"+ frequency +"')]")).click();
				Thread.sleep(100);
				driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdrAmt_input")).sendKeys(trnamt);
				driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdrsdate")).sendKeys(trndate);
				driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdrbaccttype_label")).click();
				Thread.sleep(100);
				driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:fundTabV:tdrbaccttype_items']/li[contains(text(),'"+ bankaccttype +"')]")).click();
				Thread.sleep(100);
				driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdachbname")).sendKeys(bankname);
				driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdachbacctname")).sendKeys(nameofbankacct);
				driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdachbacctlocation")).sendKeys(bankcity);
				driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdachbacctphone")).sendKeys(bankphone);
				driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdachbacctaba")).sendKeys(routingno);
				driver.findElement(By.id("ctoForm:tdaccordian:fundTabV:tdachbacctphone")).sendKeys(bankacctno);*/
				
				//Optout recurring
				driver.findElement(By.xpath("//*[@id='ctoForm:tdaccordian:fundTabV:tdroptoutflag']/div[2]/span")).click();
				Thread.sleep(1000);
				
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
	
	
	
	
	
}
