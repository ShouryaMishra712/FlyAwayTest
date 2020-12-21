package project.FlyAwayTest.testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import project.FlyAwayTest.utility.ExcelReader;

public class APITestCases {
	ExcelReader reader = new ExcelReader();
	String FilePathLogin = "src/test/java/project/FlyAwayTest/testData/testDataLogin.xlsx";
	String FilePathSearch = "src/test/java/project/FlyAwayTest/testData/testDataSearch.xlsx";
	String FilePathSignup = "src/test/java/project/FlyAwayTest/testData/testDataSignup.xlsx";
	
	String SheetName = "Sheet1";
	
	@Test
	public void Login() throws IOException 
	{
		reader.getExcelDataLogin(FilePathLogin, SheetName);
	}
	
	@Test
	public void Search() throws IOException 
	{
		reader.getExcelDataSearch(FilePathSearch, SheetName);
	}
	
	@Test
	public void Signup() throws IOException 
	{
		reader.getExcelDataSignup(FilePathSignup, SheetName);
	}
}
