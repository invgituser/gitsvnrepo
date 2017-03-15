
package com.automation.testscripts.Utility;


import org.apache.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class Invessence_Utility {

	private static WebDriver driver;
	
	private static Logger log = Logger.getLogger(Logger.class.getName());

	/*
	static WebElement productselect = driver.findElement(By.id("bbdemo"));
	
	static WebElement getstartedbtn = driver.findElement(By.id("j_idt18"));
	
	static  WebElement loginbtn = driver.findElement(By.id("j_idt19")); 

	static  WebElement loginbtn1 = driver.findElement(By.id("j_idt15"));

	static  WebElement vusername = driver.findElement(By.xpath("//*[@id='j_username']"));
	
	static  WebElement vpassword = driver.findElement(By.xpath("//*[@id='j_password']"));
	
	static  WebElement vlogin = driver.findElement(By.xpath("//*[@id='login']"));
	
	static  WebElement logoutbtn = driver.findElement(By.xpath("//*[@id='layout-topbar-menu']/li[5]/a"));
	*/
		
	public static WebDriver getstartedpreuat(WebDriver driver,String browser) throws InterruptedException 
	{
		//Demo 
		driver.findElement(By.id("bbdemo")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("j_idt29")).click();
		/*//Production
		driver.findElement(By.xpath("//a[contains(text(), 'GET STARTED')]")).click();*/
		
		Thread.sleep(3000);
		return driver;
	}
	
	
	public static WebDriver login(WebDriver driver,String username,String password,String browser) throws InterruptedException 
	{
		//Demo Login
		/*driver.findElement(By.id("bbdemo")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[contains(text(), 'Login')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[contains(text(), 'Login')]")).click();
		Thread.sleep(1000);*/
		
		/*//Production Login
		driver.findElement(By.xpath("//a[contains(text(), 'LOG IN')]")).click();*/
		
		
		driver.findElement(By.xpath("//*[@id='j_username']")).sendKeys(username);
		driver.findElement(By.xpath("//*[@id='j_password']")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id='login']")).click();
		Thread.sleep(3000);
		if (driver.findElement(By.xpath("//a[contains(text(), ' New Account')]")).isDisplayed())
		{
			System.out.println("Pass- Login sucessfull");
			log.info("Pass- Login sucessfull");
		}
		else{
			System.out.println("Fail - Login Failed");
			log.info("Fail - Login Failed");
		}
		return driver;

	}

	
}