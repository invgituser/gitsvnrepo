package com.automation.testscripts.PageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.automation.testscripts.Utility.Utility;

public class ChangeStrategyPO {
	//private static WebDriver driver;
	private static Logger log = Logger.getLogger(Logger.class.getName());
	
	//Change strategy
	
	public static boolean changestrategy(WebDriver driver,String clientaccountnumber,String changeinvestmentamount,String changeinvestmentgoal,String age,String status,String retireage,String objective,String projectionoption,String radiobtnoption,String changedprojection,String percent) throws InterruptedException
	{	boolean accttype=false;
		try{
		
			if (!clientaccountnumber.isEmpty() || !clientaccountnumber.equals("null"))
			{
				Thread.sleep(2000);
				WebElement changebtn = driver.findElement(By.xpath("//*[contains(text(),'"+clientaccountnumber+"')]/parent::td/parent::tr/parent::tbody/parent::table/following-sibling::table/following-sibling::table//*[contains(@id, 'editPortfolioButton')]"));
				changebtn.click();		
				Thread.sleep(8000);
							
				WebElement radiobtn = driver.findElement(By.xpath("//*[contains(text(),'"+radiobtnoption+"')]/parent::td/preceding-sibling::td//span"));
				if(radiobtnoption.equals("I prefer to complete the risk tolerance questionnaire again to update factors that have changed."))
				{
					radiobtn.click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[contains(text(),'Continue')]")).click();
				Thread.sleep(2000);
				boolean portfoliocreation = porfoliocreationPO.portfoliocreation(driver, changeinvestmentamount, changeinvestmentgoal, age, status, retireage,objective,changedprojection,clientaccountnumber);
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
					Thread.sleep(5000);
					radiobtnoption = "I already know which strategy I would like assigned to my portfolio. I would like to skip the risk tolerance questionnaire process and move forward with assigning a new strategy to my portfolio.";
					radiobtn.click();
					Thread.sleep(200);
					driver.findElement(By.xpath("//*[contains(text(),'Continue')]")).click();
					Thread.sleep(2000);
					driver.findElement(By.id("ceForm:tcmp7achngstrategy2")).click();
					Thread.sleep(2000);
					String sliderxpath = "//*[@id='ceForm:allocSlider']/span";
					String xpath = "//*[@id='ceForm:allocValue']";
					 String value = "1";
					Utility.slider(driver, sliderxpath,percent,value);
					driver.findElement(By.id("ceForm:tcmp7afpbtn")).click();
					Thread.sleep(1000);
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
								accttype = true;
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
								accttype = false;
							}
										
						}
				}
			}
			else
			{
				log.info("Account number is empty please enter account number");
				System.out.println("Account number is empty please enter account number");
				accttype = false;
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
