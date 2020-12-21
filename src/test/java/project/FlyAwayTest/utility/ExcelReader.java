package project.FlyAwayTest.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import project.FlyAwayTest.apiCalls.CallAPIs;

public class ExcelReader extends CallAPIs {

	private static XSSFWorkbook ExcelWBook;
	private static XSSFSheet ExcelWSheet;
	private static XSSFCell ExcelCell;
	private static CallAPIs apiTest = new CallAPIs();
	
	public static void getExcelDataLogin(String FilePath,String SheetName) throws IOException
	{
		
		try 
		{
			FileInputStream ExcelFile = new FileInputStream(FilePath);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			
			int LastRowNum = ExcelWSheet.getLastRowNum();
			
			for(int cRow = 1;cRow<=LastRowNum;cRow++)
			{
				XSSFRow Row = ExcelWSheet.getRow(cRow);
				XSSFCell cellUserN = Row.getCell(1);
				XSSFCell cellPswrd = Row.getCell(2);
				XSSFCell cellExpRes = Row.getCell(3);
				
				String UserN = cellUserN.getStringCellValue();
				String Pswrd = cellPswrd.getStringCellValue();
				String ExpRes = cellExpRes.getStringCellValue();
				
				String ActRes = apiTest.loginAPI(UserN, Pswrd);
				
				System.out.println(UserN+" "+Pswrd+" "+ExpRes+" "+ActRes);
				
				XSSFCell cellActRes = ExcelWSheet.getRow(cRow).createCell(4);
				
				cellActRes.setCellValue(ActRes);
				
				XSSFCell cellTestRes = ExcelWSheet.getRow(cRow).createCell(5);
				
				if (ActRes.equals(ExpRes))
				{
					cellTestRes.setCellValue("Passed");
				}
			
				else
				{
					cellTestRes.setCellValue("Failed");
				}
			}
			
			FileOutputStream fos = new FileOutputStream(new File("src/test/java/project/FlyAwayTest/testData/testResLogin.xlsx"));
			ExcelWBook.write(fos);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Could not read the excel sheet");
			e.printStackTrace();
		}
		
		catch(IOException e)
		{
			System.out.println("Could not read the excel sheet");
			e.printStackTrace();
		}
		
	}
	
	public static void getExcelDataSearch(String FilePath,String SheetName) throws IOException
	{
		
		try 
		{
			FileInputStream ExcelFile = new FileInputStream(FilePath);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			
			int LastRowNum = ExcelWSheet.getLastRowNum();
			
			for(int cRow = 1;cRow<=LastRowNum;cRow++)
			{
				XSSFRow Row = ExcelWSheet.getRow(cRow);
				XSSFCell cellSrc = Row.getCell(1);
				XSSFCell cellDest = Row.getCell(2);
				XSSFCell cellExpRes = Row.getCell(3);
				
				String src = cellSrc.getStringCellValue();
				String dest = cellDest.getStringCellValue();
				String ExpRes = cellExpRes.getStringCellValue();
				
				String ActRes = apiTest.searchAPI(src, dest);
				
				System.out.println(src+" "+dest+" "+ExpRes+" "+ActRes);
				
				XSSFCell cellActRes = ExcelWSheet.getRow(cRow).createCell(4);
				
				cellActRes.setCellValue(ActRes);
				
				XSSFCell cellTestRes = ExcelWSheet.getRow(cRow).createCell(5);
				
				if (ActRes.equals(ExpRes))
				{
					cellTestRes.setCellValue("Passed");
				}
			
				else
				{
					cellTestRes.setCellValue("Failed");
				}
			}
			
			FileOutputStream fos = new FileOutputStream(new File("src/test/java/project/FlyAwayTest/testData/testResSearch.xlsx"));
			ExcelWBook.write(fos);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Could not read the excel sheet");
			e.printStackTrace();
		}
		
		catch(IOException e)
		{
			System.out.println("Could not read the excel sheet");
			e.printStackTrace();
		}
		
	}

	public static void getExcelDataSignup(String FilePath,String SheetName) throws IOException
	{
		
		try 
		{
			FileInputStream ExcelFile = new FileInputStream(FilePath);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			
			int LastRowNum = ExcelWSheet.getLastRowNum();
			
			for(int cRow = 1;cRow<=LastRowNum;cRow++)
			{
				XSSFRow Row = ExcelWSheet.getRow(cRow);
				XSSFCell cellEmail = Row.getCell(1);
				XSSFCell cellPswrd = Row.getCell(2);
				XSSFCell cellCpswrd = Row.getCell(3);
				XSSFCell cellName = Row.getCell(4);
				XSSFCell cellAddress = Row.getCell(5);
				XSSFCell cellCity = Row.getCell(6);
				XSSFCell cellExpRes = Row.getCell(7);
				
				String email = cellEmail.getStringCellValue();
				String pswrd = cellPswrd.getStringCellValue();
				String cPswrd = cellCpswrd.getStringCellValue();
				String name = cellName.getStringCellValue();
				String address = cellAddress.getStringCellValue();
				String city = cellCity.getStringCellValue();
				
				String ExpRes = cellExpRes.getStringCellValue();
				
				String ActRes = apiTest.signupAPI(email, pswrd, cPswrd, name, address, city);
				
				System.out.println(email+" "+pswrd+" "+cPswrd+" "+name+" "+address+" "+city+" "+ExpRes+" "+ActRes);
				
				XSSFCell cellActRes = ExcelWSheet.getRow(cRow).createCell(8);
				
				cellActRes.setCellValue(ActRes);
				
				XSSFCell cellTestRes = ExcelWSheet.getRow(cRow).createCell(9);
				
				if (ActRes.equals(ExpRes))
				{
					cellTestRes.setCellValue("Passed");
				}
			
				else
				{
					cellTestRes.setCellValue("Failed");
				}
			}
			
			FileOutputStream fos = new FileOutputStream(new File("src/test/java/project/FlyAwayTest/testData/testResSignup.xlsx"));
			ExcelWBook.write(fos);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Could not read the excel sheet");
			e.printStackTrace();
		}
		
		catch(IOException e)
		{
			System.out.println("Could not read the excel sheet");
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args)
	{
		String FilePathLogin = "src/test/java/project/FlyAwayTest/testData/testDataLogin.xlsx";
		String FilePathSearch = "src/test/java/project/FlyAwayTest/testData/testDataSearch.xlsx";
		String FilePathSignup = "src/test/java/project/FlyAwayTest/testData/testDataSignup.xlsx";
		
		String SheetName = "Sheet1";
		try {
			ExcelReader.getExcelDataLogin(FilePathLogin, SheetName);
			ExcelReader.getExcelDataSearch(FilePathSearch, SheetName);
			ExcelReader.getExcelDataSignup(FilePathSignup, SheetName);
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
