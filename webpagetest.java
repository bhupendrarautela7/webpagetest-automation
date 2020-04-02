package com.webpagetest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class webpagetest extends Config {
	private WebDriver driver;
	private static String baseUrl;
	private static String pageName;
	private static String pageURL;
	Read_XLS FilePath=null;
	String TestSuiteName=null;
	
	
	@BeforeTest
	public void checkCaseToRun() throws IOException, InterruptedException{
	    //Called init() function from SuiteBase class to Initialize .xls Files
		init();	
		FilePath = TestCaseList_1;
		TestSuiteName = this.getClass().getSimpleName();	
		}
public static String rootURL="http://www.proptiger.com/";

	
	@BeforeClass
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.MINUTES);
		baseUrl = "http://www.webpagetest.org";	
		
	}

	@Test(dataProvider="pages")
	public void TestHomePage(String pageName , String pageURL) throws Exception {
		
		
		System.out.println("======================================================================|");
		System.out.println("Welcome to Webpagetest automation....!");
		System.out.println("Test Location - [Indore,M.P. India]");
		System.out.println("Selected Browser - [Chrome]");
		//System.out.println("Mobile Plateform - Dulles, VA USA (Android 4.4, iOS)");
		//System.out.println("Mobile Browser - Nexus 7 - Chrome");
		Date d = new Date();
		System.out.println("Date : " + d);
		
		
		driver.get(baseUrl);
		driver.findElement(By.id("url")).clear();
		driver.findElement(By.id("url")).sendKeys(rootURL+pageURL);
		//Desktop site test
		new Select(driver.findElement(By.id("location"))).selectByVisibleText("Indore, M.P., India (IE 9,Chrome,Firefox,Safari)");
		new Select(driver.findElement(By.id("browser"))).selectByVisibleText("Chrome");
		
		//Mobile Site testing
		//new Select(driver.findElement(By.id("location"))).selectByVisibleText("Dulles, VA USA (Android 4.4, iOS)");
	    //new Select(driver.findElement(By.id("browser"))).selectByVisibleText("Nexus 7 - Chrome");
		
		driver.findElement(By.name("submit")).click();
		System.out.println(pageName.toUpperCase()+" Test is in process.......");

		new WebDriverWait(driver, 300).until(ExpectedConditions.visibilityOfElementLocated((By.id("tableResults"))));

		System.out.println("-----------------------------------");
		System.out.println("["+pageName+"]"+"[ "+rootURL+pageURL+" ]");
		System.out.println("-----------------------------------");

		System.out.println("First View Load Time : "+driver.findElement(By.id("fvLoadTime")).getText()+ " || Request :"+driver.findElement(By.id("fvRequests")).getText()+" || Bytes In :"+driver.findElement(By.id("fvBytes")).getText());

		System.out.println("Repeat View Load Time : "+driver.findElement(By.id("rvLoadTime")).getText()+ " || Request :"+driver.findElement(By.id("rvRequests")).getText()+" || Bytes In :"+driver.findElement(By.id("rvBytes")).getText());

		String PageURL= driver.getCurrentUrl();
		driver.findElement(By.linkText("Performance Review")).click();
		System.out.println(driver.findElement(By.xpath("//a[@name='compress_images']/h3")).getText());
		System.out.println(driver.findElement(By.xpath("//a[@name='use_of_cdn']/h3")).getText());
		System.out.println("For more details : "+ PageURL);
		System.out.println("======================================================================|");

	}

	@DataProvider
	
	public Object[][] pages(){
		//To retrieve data from Data 1 Column,Data 2 Column,Data 3 Column and Expected Result column of SuiteOneCaseOne data Sheet.
		//Last two columns (DataToRun and Pass/Fail/Skip) are Ignored programatically when reading test data.
		//ConsoleLogs.PrintBoundry();
		//ConsoleLogs.PrintLogs("TestCaseName Is :","TypeAndValidate");
		return SuiteUtility.GetTestDataUtility(FilePath, "page");	
		}

	@AfterMethod
	@AfterSuite
	public void tearDown() throws Exception 
	{
		//driver.quit();
		
		
	}
}
