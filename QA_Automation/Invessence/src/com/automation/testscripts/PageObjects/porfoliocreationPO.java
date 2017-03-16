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

		//New Account start page
		//
		Thread.sleep(1000);
		
		if (clientaccountnumber.isEmpty() || clientaccountnumber.equals("null"))
			{
				driver.findElement(By.xpath("//*[@id='ceForm:initialIid_input']")).clear();
				driver.findElement(By.xpath("//*[@id='ceForm:initialIid_input']")).sendKeys(investmentamount);
				driver.findElement(By.xpath("//*[@id='ceForm:q3_label']")).click();
				Thread.sleep(2000);
			}
		else
			{
			driver.findElement(By.xpath("//li[contains(text(),'"+investmentgoal+"')]")).click();
			Thread.sleep(2000);	
			}
		if (investmentgoal.equals("Retirement"))
		{
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
		boolean objectivepage = driver.findElement(By.xpath("//*[@id='ceForm:p3id']/tbody/tr[1]/td[2]/label")).isDisplayed();
		if(objectivepage)
		{
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
			System.out.println("#############################################");
			log.info("#############################################");
		}
		return portfoliocreation;
	}
}
