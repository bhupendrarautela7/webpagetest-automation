package com.webpagetest;

import java.io.IOException;

public class Config {
	//public static WebDriver driver= null;
	public static Read_XLS TestCaseList_1=null;
	public static Read_XLS TestCaseListExcelTwo=null;
	public static String suite_1="./Input/Main1.xls";
	//public static String suite_2=System.getProperty("user.dir")+"/SuiteFiles/TextBoxes.xls";
	
	public void init() throws IOException{
	/*GetUrl.hitUrl(driver,"");
	driver.navigate().refresh();
	Thread.sleep(2000);*/
	TestCaseList_1=new Read_XLS(suite_1);
	//TestCaseListExcelTwo=new Read_XLS(suite_2);
	}
}
