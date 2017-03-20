
package com.automation.testscripts.Utility;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.instrument.IllegalClassFormatException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;



public class Utility {

	private static WebDriver driver;
	private static Logger log = Logger.getLogger(Logger.class.getName());
	static int xRows, xRowsCount;
	static int xCols, xColsCount;
	static String xData[][], xData1[][];
	static String excelData[][];
	public static String testreport;
	public static File resultdir, preUpgradeDataSaveDir;
	public static String datetimewiseresult = Utility.readTestDataFromProperties("Properties/testdatalocation.properties","Results");
	static String placeresultsin;
	public static String resultpath;
	static String[] queryResults ;
	
	
	
	public static String readTestDataFromProperties(String propertiesfilename,String Xlpath) 
	{
		Properties properties = new Properties();
		try 
		{ //  Log.info("Reading Properties files");
			properties.load(new FileInputStream(propertiesfilename));
		} 
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			log.error("[ERROR] File Not Found");
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			log.error("[ERROR] File reading fail");
		}
		String testdataPath = properties.getProperty(Xlpath);
		// System.out.println(testdataPath);
		return testdataPath;
	}
	
	
	// Getting the URL content and then Switching to new required Window
		public static WebDriver NewWindowDriver(String url, WebDriver driver)
				throws Exception 
		{
			// String parentWindowHandler = driver.getWindowHandle(); // Store your
			// parent window
			Thread.sleep(3000);
			for (String handles : driver.getWindowHandles())
				driver.switchTo().window(handles);
			if (driver.getCurrentUrl().contains(url)) {
				log.info("Selected Window URL : "+ driver.getCurrentUrl());
				//System.out.println("Selected Window URL : "	+ driver.getCurrentUrl());
				return driver;
			}
			
			//System.out.println("Not Required Window URL :" + driver.getCurrentUrl());
			log.info("Not Required Window URL :" + driver.getCurrentUrl());
			return driver;
		}
		@Test
		public static WebDriver Browser(String test_browser) 
		{
			
			log.info("Opening Browser :" + test_browser);
			switch (test_browser) 
			{
			case "FIREFOX":
				driver = new FirefoxDriver();
				return driver;

			case "IE":
				System.setProperty("webdriver.ie.driver",
						"BrowserDrivers/IE/IEDriverServer.exe");
				/*DesiredCapabilities capabilities = DesiredCapabilities
						.internetExplorer();
				capabilities.setCapability(
						InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				driver = new InternetExplorerDriver(capabilities);*/
				driver =new InternetExplorerDriver();
				return driver;

			case "CHROME":
				System.setProperty("webdriver.chrome.driver",
						"BrowserDrivers/Chrome/chromedriver.exe");
				//driver = new ChromeDriver();
				ChromeOptions chromeOption = new ChromeOptions();
				chromeOption.addArguments("--disable-popup-blocking");
			//
			//	driver = new ChromeDriver();
				return  new ChromeDriver(chromeOption);
				//return driver;

			case "SAFARI":
				SafariOptions options = new SafariOptions();
				//driver = new SafariDriver();
				options.setUseCleanSession(true);
				driver = new SafariDriver(options);
				return driver;
			case "HTMLUnit":
				// Declaring and initialising the HtmlUnitWebDriver
				HtmlUnitDriver driver = new HtmlUnitDriver();
				driver.setJavascriptEnabled(true);
				return driver;
			
			default:
				System.out.println("We don't support this Browser Sorry!!!!!!!!!!");
				throw new RuntimeException("Browser type unsupported");
			}
		}
		
		//Reading excel
		public static String[][] xlRead(String sPath, String sheetName)
				throws IOException, IllegalClassFormatException, EncryptedDocumentException, InvalidFormatException 
		{   log.info("Reading Input data from excelSheet :"+ sPath);
			File myxl = new File(sPath);
			String fileName = myxl.getName().toString();
			//System.out.println("File name is :" + fileName);
			//System.out.println("Path of file is :" + myxl);
			// Create an object of FileInputStream class to read excel file

			FileInputStream inputStream = new FileInputStream(myxl);
			Workbook myWB = null;
			String fileExtensionName = fileName.substring(fileName.indexOf("."));

			// Check condition if the file is xlsx file

			if (fileExtensionName.equals(".xlsx"))
			{

				// If it is xlsx file then create object of XSSFWorkbook class
				myWB = WorkbookFactory.create(inputStream);
				// myWB = new XSSFWorkbook(inputStream);
			}
			// Check condition if the file is xls file

			else if (fileExtensionName.equals(".xls")) 
			{
				// If it is xls file then create object of HSSFWorkbook class
				myWB = new HSSFWorkbook(inputStream);

			}
			// HSSFSheet mySheet = myWB.getSheetAt(4); // Referring to 4th sheet
			Sheet mySheet = myWB.getSheet(sheetName); // Refering to the Sheet
														// TestData in the Xls file.
			xRows = mySheet.getLastRowNum() + 1;
			xCols = mySheet.getRow(0).getLastCellNum();
		//	System.out.println("Rows are " + xRows);
		//	System.out.println("Cols are " + xCols);
			xData = new String[xRows][xCols];
			for (int i = 0; i < xRows; i++)
			{
				Row row = mySheet.getRow(i);
				for (int j = 0; j < xCols; j++)
				{
					Cell cell = row.getCell(j); // To read value from each col in  each row
					// if(cell.getStringCellValue() != ""){
					if (cell != null && cell.getCellType() != HSSFCell.CELL_TYPE_BLANK)
					{
						String value = cellToString(cell);
						xData[i][j] = value;
						log.info("[INFO]cell value"+value);
						System.out.print("  ");
					} 
					else 
					{
						String value = "";
						xData[i][j] = value;
						log.info("[INFO] Cell is blank");
					}
				}
				System.out.println("");
			}
			return xData;
		}

		@Test
		// This function will Write the Results back into the Excel sheet

		public static void xlwrite(String[][] xldata, String scriptname) throws Exception 
		{
			//System.out.println("Inside XL Write");
			
			placeresultsin = Utility.createdirectory(datetimewiseresult);
			String finalresultfilepath = placeresultsin + "/" + "TestResults" + "_"+ scriptname + ".xls";
			File outFile = new File(finalresultfilepath);
			log.info("Writing Results to excel sheet: " + outFile );
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet osheet = wb.createSheet("TESTRESULTS");
			xRows = xldata.length;
			xCols = xldata[0].length;
			// Create row at index zero ( Top Row)
			for (int myrow = 0; myrow < xRows; myrow++)
			{
				// System.out.println("Inside XL Write");
				HSSFRow row = osheet.createRow(myrow);
				// Create a cell at index zero ( Top Left)
				for (int mycol = 0; mycol < xCols; mycol++)
				{
					HSSFCell cell = row.createCell(mycol);
					// Lets make the cell a string type
					cell.setCellType(Cell.CELL_TYPE_STRING);
					// Type some content
					cell.setCellValue(xldata[myrow][mycol]);
					// System.out.print("..." + xldata[myrow][mycol]);
				}
				FileOutputStream fOut = new FileOutputStream(outFile);
				// Write the XL sheet
				wb.write(fOut);
				wb.close();
				fOut.flush();
				fOut.close();
			}
		}
		@Test
		// This function will convert an object of type excel cell to a string value
		public static String cellToString(Cell cell) 
		{
			int type = cell.getCellType();
			Object result;
			switch (type) {
			case Cell.CELL_TYPE_NUMERIC: // 0
				result = cell.getNumericCellValue();
				break;
			case Cell.CELL_TYPE_STRING: // 1
				result = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_FORMULA: // 2
				throw new RuntimeException("We can't evaluate formulas in Java");
			case Cell.CELL_TYPE_BLANK: // 3
				result = "-";
				break;
			case Cell.CELL_TYPE_BOOLEAN: // 4
				result = cell.getBooleanCellValue();
				break;
			case Cell.CELL_TYPE_ERROR: // 5
				throw new RuntimeException("This cell has an error");
			default:
				throw new RuntimeException("We don't support this cell type: "
						+ type);

			}
			return result.toString();
		}
//create derectory
		
		public static String createdirectory(String testreportpath) 
		{
			testreport = testreportpath + "_" + new SimpleDateFormat("yyyy_MM_dd").format(Calendar.getInstance().getTime()).toString();
			resultdir = new File(testreport);
			if (!resultdir.exists()) 
			{
				resultdir.mkdirs();
			}
			resultpath = resultdir.getPath();
			return resultpath;
		}
		
		/**
		 * This method scrolls the element into view and returns the same for further operation(s).
		 * @param driver : WebDriver instance
		 * @param locator : Webelement locator
		 * @return WebElement
		 */
		public static WebElement scrollElementIntoView(WebDriver driver, By locator) {
			WebElement element = driver.findElement(locator);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		//	((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", element);
			return element;
		}
		
		//Get stack trace of the exception
		
		public static String getStackTrace(Exception e)
		{
		    StringWriter sWriter = new StringWriter();
		    PrintWriter pWriter = new PrintWriter(sWriter);
		    e.printStackTrace(pWriter);
		    return sWriter.toString();
		}
	

		public static void DownloadChromeFile(WebDriver driver,String filepatth) {
			
			 try{
				 Thread.sleep(20);
            Actions builder = new Actions(driver);
            
           /* builder.keyDown(Keys.CONTROL);
            builder.sendKeys("S");
            //builder.keyDown(Keys.);
            
            builder.keyUp(Keys.CONTROL);
            
            builder.build().perform();*/

            builder.keyDown(Keys.CONTROL).sendKeys("S").keyUp(Keys.CONTROL).perform();
                      
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR"+e);
        }


		}
	
		public static void captureScreenShot(WebDriver driver){

		String	screenshotpath = Utility.readTestDataFromProperties("Properties/testdatalocation.properties","screenshot");
		// Take screenshot and store as a file format
			File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try 
			{
				// now copy the  screenshot to desired location using copyFile method
	
				FileUtils.copyFile(src, new File(screenshotpath +System.currentTimeMillis()+".png"));
			}

			catch (IOException e)

			{

				System.out.println(e.getMessage());

			}

			}
		//Clicking on getstarted button
		public static void getstarted(WebDriver driver) throws InterruptedException
		{
			driver.findElement(By.id("bbdemo")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("j_idt18")).click();
			Thread.sleep(1000);	
		}
	
		
		//Convert String to int
		public static int stringtoint(String str)
		{
			int result = Integer.parseInt(str);
			return result;
		}
		
		//Slider fuctionality
		public static void slider(WebDriver driver,String xpath,String percent)
		{
			try
			{
				Thread.sleep(200);
				WebElement slider = driver.findElement(By.xpath(xpath));
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].setAttribute('style', 'left: "+percent+"%;')",slider);
			
			}
			catch (Exception e)

			{
				 e.printStackTrace();
		         System.out.println("ERROR"+e);
		         log.info("ERROR"+e);

			}
	        
		}
		
		/**
		 * Moves a jQuery slider to percental position, don't care about directions
		 * @param slider to move
		 * @param percent to set the slider
		 */
	/*	public void moveSliderToPercent(WebElement slider, int percent){

		    Actions builder = new Actions(this.driver);

		    Action dragAndDrop;

		    int height = slider.getSize().getHeight();
		    int width = slider.getSize().getWidth();


		    if(width>height){
		        //highly likely a horizontal slider
		        dragAndDrop = builder.clickAndHold(slider).moveByOffset(-(width/2),0).
		                       moveByOffset((int)((width/100)*percent),0).
		                       release().build();
		    }else{
		        //highly likely a vertical slider
		        dragAndDrop = builder.clickAndHold(slider).moveByOffset(0, -(height/2)).
		                       moveByOffset(0,(int)((height/100)*percent)).
		                       release().build();
		    }


		    dragAndDrop.perform();

		}*/
}