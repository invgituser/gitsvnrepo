package com.automation.testscripts.PageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ChangeStrategyPO {
	//private static WebDriver driver;
	private static Logger log = Logger.getLogger(Logger.class.getName());
	
	//Change strategy
	
	public static boolean changestrategy(WebDriver driver,String clientaccountnumber,String changeinvestmentamount,String changeinvestmentgoal,String age,String status,String retireage,String objective,String projectionoption) throws InterruptedException
	{	boolean accttype=false;
		try{
		
			if (!clientaccountnumber.isEmpty() || !clientaccountnumber.equals("null"))
			{
				Thread.sleep(2000);
				WebElement changebtn = driver.findElement(By.xpath("//*[contains(text(),'"+clientaccountnumber+"')]/parent::td/parent::tr/parent::tbody/parent::table/following-sibling::table/following-sibling::table//*[contains(@id, 'editPortfolioButton')]"));
				changebtn.click();		
				Thread.sleep(4000);
				String radiobtnoption = "I prefer to complete the risk tolerance questionnaire again to update factors that have changed.";
				WebElement radiobtn = driver.findElement(By.xpath("//*[contains(text(),'"+radiobtnoption+"')]/parent::td/preceding-sibling::td//span"));
				radiobtn.click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[contains(text(),'Continue')]")).click();
				Thread.sleep(2000);
				boolean portfoliocreation = porfoliocreationPO.portfoliocreation(driver, changeinvestmentamount, changeinvestmentgoal, age, status, retireage,objective,projectionoption,clientaccountnumber);
				if(portfoliocreation)
				{
					log.info("Pass - Change Portfolio is Sucessfull");
					System.out.println("Pass - Change Portfolio is Sucessfull");
					
				}
				else
				{
					log.info("Fail -Change Portfolio creation Failed");
					System.out.println("Fail -New Portfolio creation Failed");
				}
		
			}
			else
			{
				log.info("Account number is empty please enter account number");
				System.out.println("Account number is empty please enter account number");
			}
			
			}
		catch(Exception e)
		{
			e.printStackTrace();
			accttype=false;
		}
		return accttype;
	}
	
	
	
	
	
	
}
