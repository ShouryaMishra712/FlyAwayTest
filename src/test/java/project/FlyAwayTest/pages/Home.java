package project.FlyAwayTest.pages;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.By.ByTagName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Home {

	public WebDriver driver;
	
	@FindBy(linkText="Home")
	public
	WebElement home;
	
	@FindBy(name="source")
	WebElement source;
	
	@FindBy(name="destination")
	WebElement dest;
	
	@FindBy(xpath="//Button[text()='Submit']")
	WebElement submit;
	
	@FindBy(xpath="//b[text()='Source']//ancestor::table")
	WebElement table;
	
	
	public Home(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public boolean searchFlight(String from,String to) throws InterruptedException
	{
		home.click();
		Thread.sleep(3000);
		Select From = new Select(source);
		From.selectByVisibleText(from);
		
		Thread.sleep(3000);
		Select To = new Select(dest);
		To.selectByVisibleText(to);
		
		Thread.sleep(3000);
		
		getScreenShot(driver,"searchData");
		
		submit.click();
		
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		if(rows.size()>1)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public boolean verifyData(String[] data) throws InterruptedException
	{
		String[] preData = {"","","","",""};
		
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		if(rows.size()>1)
		{
			int i,j = 0;
			
			List<WebElement> col = rows.get(1).findElements(By.tagName("td"));
			for (i=0;i<col.size()-1;i++) 
			{
				preData[i] = col.get(i).getText();
				System.out.println(preData[i]+" & "+data[i]);
				if(preData[i].equals(data[i]))
				{
					j++;
				}
			}
			
			if(j==col.size()-1)
			{
				return true;
			}
			
			else
			{
				return false;
			}
		}
		
		else
		{
			return false;
		}
	}
	public void getScreenShot(WebDriver driver, String ScreenshotName) 
	{
	      try {
	            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	            //The below method will save the screen shot in destination directory with name "screenshot.png"
	             FileHandler.copy(scrFile, new File("Screenshots/"+ScreenshotName+".png"));
	         } catch (IOException e) {
	             e.printStackTrace();
	            }
	  }
}
