package com.webpagetest;

public class SuiteUtility {	
 
	public static Object[][] GetTestDataUtility(Read_XLS xls, String sheetName){
	 	return xls.retrieveTestData(sheetName);	
	}
 
	public static boolean WriteResultUtility(Read_XLS xls, String sheetName, String ColName, int rowNum, String Result){			
		return xls.writeResult(sheetName, ColName, rowNum, Result);		 	
	}
	
}

