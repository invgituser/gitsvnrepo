package com.automation.testscripts.PageObjects;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.automation.testscripts.Utility.DBConnection;

public class AdvisorLoginPO {
	//private static WebDriver driver;
	private static Logger log = Logger.getLogger(Logger.class.getName());
	
	//Account type Section in Account Opening
	
	public static boolean active(WebDriver driver,String accounttype,String acctnum,String lname,String fname,
String strategy,String goal,String investedamount,String vlogo) throws InterruptedException
	{	boolean active=false;
		try{
		
		driver.findElement(By.xpath("//span[contains(text(), 'Dashboard')]")).click();
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[contains(text(),'Active')]")).click();
		
		Thread.sleep(2000);
		List<WebElement> rows = driver.findElements(By.xpath("//*[@id='form:managedTable_data']/tr"));
		int rowcount = rows.size();
		
		//rowcount = 1;
		if(rowcount > 0)
		{
			for(int i = 0; i<= rowcount; i++)
			{
				String vstrategy,vfullname,vacctid,vaccttype,vgoal,vinvestamt,vdateopened;
				String viewbtn = "form:managedTable:"+i+":ovButton";
				String strategycol = "form:managedTable:"+i+":advactportname";
				String fullnamecol = "form:managedTable:"+i+":advactfullname";
				String acctidcol = "form:managedTable:"+i+":advactacctid";
				String accttypecol = "form:managedTable:"+i+":advactaccttype";
				String goalcol = "form:managedTable:"+i+":advactgoal";
				String investamtcol = "form:managedTable:"+i+":advactinvest";
				String dateopenedcol = "form:managedTable:"+i+":advactdateopn";
				
				System.out.println(strategycol);
				//Capture the value in each row
				Thread.sleep(2000);
				vstrategy= driver.findElement(By.id(strategycol)).getText();
				vfullname = driver.findElement(By.id(fullnamecol)).getText();
				vacctid = driver.findElement(By.id(acctidcol)).getText();
				vaccttype= driver.findElement(By.id(accttypecol)).getText();
				vgoal= driver.findElement(By.id(goalcol)).getText();
				vinvestamt= driver.findElement(By.id(investamtcol)).getText();
				vdateopened= driver.findElement(By.id(dateopenedcol)).getText();
				Thread.sleep(2000);
				//Verify if the first row has your data.
				if(vacctid.equals(acctnum))
					
					// Verify if the account details are displayed
				{
						System.out.println("Pass - Account number details are displayed.");
						log.info("Pass - Account number details are displayed.");
								
						driver.findElement(By.id(viewbtn)).click();
						Thread.sleep(2000);
						//Verify the window that opens after hitting view button 
						
						String acctidtext = driver.findElement(By.id("ovrvwclientid")).getText();
						String acctid =StringUtils.substringAfterLast(acctidtext, ": ");
								
						if(vacctid.equals(acctid))
						
							// Verify if the account details are displayed
							{
								System.out.println("Pass - Account id  matching");
								log.info("Pass - Account id  matching");
								String name = driver.findElement(By.id("ovrvwfullname")).getText();
								if(name.equals(vfullname))
								{
									System.out.println("Pass - Full name is matching");
									log.info("Pass- Full name is matching");
									String displayedstrtegytext = driver.findElement(By.id("ovrvwacctalias")).getText();
									String displayedstrtegy =StringUtils.substringAfterLast(displayedstrtegytext, ": ");
									if(displayedstrtegy.equals(vstrategy))
									{
										System.out.println("Pass - Strategy is matching");
										log.info("Pass - Strategy is matching");
										active=true;
									}
									else
									{
										System.out.println("Fail - Strategy is not matching");
										log.info("Fail - Strategy is not matching");
										active=false;
									}
								}
								else
								{
									System.out.println("Fail - Full name is not matching");
									log.info("Fail - Full name is not matching");
									active=false;
								}
								
							}
							else
							{
								System.out.println("Fail - Account id is not matching");
								log.info("Fail - Account id is not matching");
								active=false;
							}	
							driver.findElement(By.xpath("//span[contains(text(), 'Dashboard')]")).click();
							Thread.sleep(3000);
							driver.findElement(By.xpath("//*[contains(text(),'Active')]")).click();
							Thread.sleep(2000);

				}
			
				else
				{
					System.out.println("Fail - This is not the row with my account number check next row");
					log.info("Fail - This is not the row with my account number check next row");
					active=false;
				}
			}
	
						
		}
		else
			{	
				log.info("Fail - No Rows are displayed");
				System.out.println("Fail - No Rows are displayed");
				active=false;
			
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			active=false;
		}
		return active;
	}
		
}
