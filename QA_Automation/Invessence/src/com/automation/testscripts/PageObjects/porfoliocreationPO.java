package com.automation.testscripts.PageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.automation.testscripts.Utility.Utility;



public class porfoliocreationPO {
	
	private static WebDriver driver;
	private static Logger log = Logger.getLogger(Logger.class.getName());
	
	/*public static WebElement vnewaccount = driver.findElement(By.xpath("//*[@id='menutopbar:j_idt34']"));
	
	public static WebElement vinvestmentamt = driver.findElement(By.xpath("//*[@id='ceForm:initialIid_input']"));
	
	//static WebElement vgoal = driver.findElement(By.xpath("//*[@id='ceForm:q3_label']"));
	
	public static void selectgoal(String investmentgoal){
		WebElement vgoal = driver.findElement(By.xpath("//li[contains(text(), '"+investmentgoal+"')]"));
		vgoal.click();
	}
	
	public static void retirestatus(String status){
		WebElement retirementstaus = driver.findElement(By.xpath("//li[contains(text(), '"+status+"')]"));
		retirementstaus.click();
	}
	
	public static WebElement currentage = driver.findElement(By.id("ceForm:ageid_input"));
	
	public static WebElement  retirmentage = driver.findElement(By.id("ceForm:retireageID_input"));
		
	public static WebElement  nextbtn  =driver.findElement(By.id("ceForm:j_idt97"));
	
	public static void selectobjective(String objective){
		WebElement  selectobjective  =driver.findElement(By.xpath("//*[contains(text(), '"+ objective+ "')]"));
		selectobjective.click();
	}*/
	
	public static boolean portfoliocreation(WebDriver driver,String investmentamount,String investmentgoal,String age,String status,String retireage,String objective,String projectionoption,String clientaccountnumber) throws InterruptedException
	{
		boolean portfoliocreation=false;

		try{

		//New Account start page
		//
		Thread.sleep(1000);
		//System.out.println("Client No : "+clientaccountnumber);
		int leng = clientaccountnumber.length();

		if (clientaccountnumber.isEmpty() || clientaccountnumber.equals("null") || clientaccountnumber.equals(""))
			{
				driver.findElement(By.xpath("//*[@id='ceForm:initialIid_input']")).clear();
				driver.findElement(By.xpath("//*[@id='ceForm:initialIid_input']")).sendKeys(investmentamount);
				
			}
		
			driver.findElement(By.xpath("//*[@id='ceForm:q3_label']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//li[contains(text(),'"+investmentgoal+"')]")).click();
			Thread.sleep(2000);	
			
		if (investmentgoal.equals("Retirement"))
		{
			driver.findElement(By.id("ceForm:ageid_input")).clear();					
			driver.findElement(By.id("ceForm:ageid_input")).sendKeys(age);
			driver.findElement(By.id("ceForm:retiredDDID_label")).click();
			Thread.sleep(1000);
			WebElement retirementstaus = driver.findElement(By.xpath("//li[contains(text(), '"+status+"')]"));
			retirementstaus.click();
			Thread.sleep(3000);	
			
			/*
			driver.findElement(By.id("ceForm:retiredDDID_label")).click();
			Thread.sleep(1000);	
			driver.findElement(By.id("ceForm:retiredDDID_0")).click();
			Thread.sleep(1000);	
			Select dropdown1 = new Select(driver.findElement(By.id("ceForm:retiredDDID_input")));
			dropdown1.selectByVisibleText(status);*/
			if(status.equals("Not Retired"))
			{
				driver.findElement(By.id("ceForm:retireageID_input")).clear();
				driver.findElement(By.id("ceForm:retireageID_input")).sendKeys(retireage);
			}

		}
		else
		{
			int intage = Utility.stringtoint(age);
			if (investmentgoal.equals("College"))
			{
				if (intage>= 1 || intage <= 18)
				{
					driver.findElement(By.id("ceForm:collegeid_input")).sendKeys(age);
				}
				else
				{
					System.out.println("Age limit is 1 to 18 so enter age between 1 and 18");
					log.info("Age limit is 1 to 18 so enter age between 1 and 18");
				}
			}
			else{
				
					driver.findElement(By.id("ceForm:otherid_input")).sendKeys(age);
				
			}
		}
		//driver.findElement(By.id("ceForm:j_idt97")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Next')]")).click();
		Thread.sleep(2000);
		
		//Objective Page
		//boolean objectivepage = driver.findElement(By.xpath("//*[@id='ceForm:p3id']/tbody/tr[1]/td[2]/label")).isDisplayed();
		
			//Select objective
			WebElement  selectobjective  =driver.findElement(By.xpath("//*[contains(text(), '"+ objective+ "')]"));
			Thread.sleep(2000);
			selectobjective.click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[contains(text(),'Next')]")).click();
			
			//Risk page
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@class='riskImage']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[contains(text(),'Next')]")).click();
			Thread.sleep(2000);
			
			//Projection page
			driver.findElement(By.xpath("//span[contains(text(),'"+projectionoption+"')]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[contains(text(),'Next')]")).click();
			Thread.sleep(5000);
			
			//Review screen is displayed
			//   WebElement startoverbtn = driver.findElement(By.id("ceForm:j_idt239"));
			WebElement startoverbtn = driver.findElement(By.xpath("//span[contains(text(),'Start Over')]"));
			//if(driver.findElement(By.id("ceForm:tcmp7anext")).isDisplayed())
			if (!clientaccountnumber.isEmpty() || !clientaccountnumber.equals(""))
			 {
							
				String nextbtn = driver.findElement(By.id("ceForm:tcmp7anext")).getAttribute("aria-disabled");
				if(nextbtn.equals("false"))
				{	 	driver.findElement(By.xpath("//*[contains(text(),'Next')]")).click();
							Thread.sleep(3000);
							String portfoliorebalancing = "Changing your strategy will require portfolio rebalancing which may incur: the realization of capital gains or losses, additional transaction costs, and/or short-term trading penalties/fees. Strategy changes have the potential to be detrimental to performance.";
							WebElement chkbox1 = driver.findElement(By.xpath("//*[contains(text(),'"+portfoliorebalancing+"')]/preceding-sibling::div/span"));
						    chkbox1.click();
						    Thread.sleep(100);
							String	acceptcriteria1 = "Due to limited liquidity in some investments, a strategy change and accompanying rebalance may take several months to be fully completed. It may also result in temporarily increased levels of cash in the portfolio and/or variances from the model strategy allocations.  Depending on the specifics of each situation, we may also utilize placeholder investments temporarily in lieu of model strategy investments that are not currently available for purchase.";
						    WebElement chkbox2 =driver.findElement(By.xpath("//*[contains(text(),'Due to limited liquidity in some investments, a strategy change and accompanying rebalance may take several months to be fully completed. It may also result in temporarily increased levels of cash in the portfolio and/or variances from the model strategy allocations.  Depending on the specifics of each situation, we may also utilize placeholder investments temporarily in lieu of model strategy investments that are not currently available for purchase.')]/preceding-sibling::div/span"));
						    chkbox2.click();
						    Thread.sleep(100);
						    driver.findElement(By.xpath("//*[contains(text(),'Accept')]")).click();
						    Thread.sleep(2000);
						    System.out.println("#############################################");
							log.info("#############################################");
							System.out.println("Pass - Cureent portfolio strategy is changed ");
							log.info("Pass - Cureent portfolio strategy is changed ");
							portfoliocreation = true;
				}
				else
				{
				boolean changestatus = driver.findElement(By.xpath("//*[contains(text(),'Your current and revised portfolio is same, you can not do further processing.')]")).isDisplayed();
				if(changestatus)
				{
					System.out.println("#############################################");
					log.info("#############################################");
					System.out.println("Fail - Current portfolio strategy is not changed ");
					log.info("Fail - Current portfolio strategy is not changed ");
					System.out.println("Your current and revised portfolio is same, you can not do further processing.");
					log.info("Your current and revised portfolio is same, you can not do further processing.");
					portfoliocreation = false;
				}
								
				}
			 }
			else 
			{
				if(( driver.findElement(By.xpath("//span[contains(text(),'Open Account')]")).isDisplayed()))
				
				{
					System.out.println("Pass- Open an New account");
					log.info("Pass- Open an New account");
					System.out.println("#############################################");
					log.info("#############################################");
					if(startoverbtn.isDisplayed())
						{
							System.out.println("Pass - New Portfolio is created");
							log.info("Pass -New Portfolio is created");
							portfoliocreation = true;
			
			
						}
					else
						{
							System.out.println("Fail - New Portfolio creation Failed");
							log.info("Fail - New Portfolio creation Failed");
							portfoliocreation = false;
						}
				}
			
				
			}
						
			System.out.println("#############################################");
			log.info("#############################################");
						
		}
		catch(Exception e)
		{
			e.printStackTrace();
			portfoliocreation =false;
		}
		return portfoliocreation;
	}
	
	
	public static boolean portfoliocreationVisitor(WebDriver driver,String investmentamount,String investmentgoal,String age,String status,String retireage,String objective,String projectionoption,String clientaccountnumber) throws InterruptedException
	{
		boolean portfoliocreationvisitor=false;

		try{

		//New Account start page
		//
		Thread.sleep(1000);
		//System.out.println("Client No : "+clientaccountnumber);
		int leng = clientaccountnumber.length();

		if (clientaccountnumber.isEmpty() || clientaccountnumber.equals("null") || clientaccountnumber.equals(""))
			{
				driver.findElement(By.xpath("//*[@id='ceForm:initialIid_input']")).clear();
				driver.findElement(By.xpath("//*[@id='ceForm:initialIid_input']")).sendKeys(investmentamount);
				
			}
		
			driver.findElement(By.xpath("//*[@id='ceForm:q3_label']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//li[contains(text(),'"+investmentgoal+"')]")).click();
			Thread.sleep(2000);	
			
		if (investmentgoal.equals("Retirement"))
		{
			driver.findElement(By.id("ceForm:ageid_input")).clear();					
			driver.findElement(By.id("ceForm:ageid_input")).sendKeys(age);
			driver.findElement(By.id("ceForm:retiredDDID_label")).click();
			Thread.sleep(1000);
			WebElement retirementstaus = driver.findElement(By.xpath("//li[contains(text(), '"+status+"')]"));
			retirementstaus.click();
			Thread.sleep(3000);	
			
			/*
			driver.findElement(By.id("ceForm:retiredDDID_label")).click();
			Thread.sleep(1000);	
			driver.findElement(By.id("ceForm:retiredDDID_0")).click();
			Thread.sleep(1000);	
			Select dropdown1 = new Select(driver.findElement(By.id("ceForm:retiredDDID_input")));
			dropdown1.selectByVisibleText(status);*/
			if(status.equals("Not Retired"))
			{
				driver.findElement(By.id("ceForm:retireageID_input")).clear();
				driver.findElement(By.id("ceForm:retireageID_input")).sendKeys(retireage);
			}

		}
		else
		{
			int intage = Utility.stringtoint(age);
			if (investmentgoal.equals("College"))
			{
				if (intage>= 1 || intage <= 18)
				{
					driver.findElement(By.id("ceForm:collegeid_input")).sendKeys(age);
				}
				else
				{
					System.out.println("Age limit is 1 to 18 so enter age between 1 and 18");
					log.info("Age limit is 1 to 18 so enter age between 1 and 18");
				}
			}
			else{
				
					driver.findElement(By.id("ceForm:otherid_input")).sendKeys(age);
				
			}
		}
		//driver.findElement(By.id("ceForm:j_idt97")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Next')]")).click();
		Thread.sleep(2000);
		
		//Objective Page
		//boolean objectivepage = driver.findElement(By.xpath("//*[@id='ceForm:p3id']/tbody/tr[1]/td[2]/label")).isDisplayed();
		
			//Select objective
			WebElement  selectobjective  =driver.findElement(By.xpath("//*[contains(text(), '"+ objective+ "')]"));
			Thread.sleep(2000);
			selectobjective.click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[contains(text(),'Next')]")).click();
			
			//Risk page
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@class='riskImage']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[contains(text(),'Next')]")).click();
			Thread.sleep(2000);
			
			//Projection page
			driver.findElement(By.xpath("//span[contains(text(),'"+projectionoption+"')]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[contains(text(),'Next')]")).click();
			Thread.sleep(5000);
			
			//Review screen is displayed
			

			WebElement saverecomendationbtn = driver.findElement(By.xpath("//span[contains(text(),'Save Recommendations')]"));
			Thread.sleep(3000);		
			if(( driver.findElement(By.xpath("//span[contains(text(),'Save Recommendations')]")).isDisplayed()))
				
				{
					System.out.println("#############################################");
					log.info("#############################################");
					if(saverecomendationbtn.isDisplayed())
						{
							saverecomendationbtn.click();
							Thread.sleep(3000);	
							System.out.println("Pass- Portfolio is created");
							log.info("Pass- Portfolio is created");
							portfoliocreationvisitor = true;
			
			
						}
					else
						{
							System.out.println("Fail - Portfolio is created Failed");
							log.info("Fail - Portfolio is created Failed");
							portfoliocreationvisitor = false;
						}
				}
				
			System.out.println("#############################################");
			log.info("#############################################");
						
		}
		catch(Exception e)
		{
			e.printStackTrace();
			portfoliocreationvisitor =false;
		}
		return portfoliocreationvisitor;
	}
	
}
