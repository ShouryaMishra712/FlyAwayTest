package project.FlyAwayTest.pages;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Bookings {

	static WebDriver driver;

	@FindBy(linkText = "Book Flight")
	WebElement book;
	
	@FindBy(xpath = "/html/body/text()[7]")
	WebElement dtStamp;
	
	@FindBy(linkText = "Click to complete booking")
	WebElement complete;
	
	@FindBy(linkText="Your Bookings")
	WebElement yourBook;
	
	@FindBy(xpath="//b[text()='Source']//ancestor::table")
	WebElement table;
	
	public Bookings(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean bookFlight() throws InterruptedException
	{
		book.click();
		Thread.sleep(3000);
		getScreenShot(driver,"bookingData");
		
		String dateTime = dtStamp.toString();
		complete.click();
		return true;
	}

	public boolean verifyBooking(String[] refBook) throws InterruptedException
	{
		String[] booking = {"","","",""};

		Thread.sleep(3000);

		yourBook.click();

		Thread.sleep(3000);
		
		getScreenShot(driver,"verifyBookingData");
		
		WebElement table = driver.findElement(By.xpath("//b[text()='Source']//ancestor::table"));

		List<WebElement> rows = table.findElements(By.tagName("tr"));
		if(rows.size()>1)
		{
			int i,j = 0;
			
			List<WebElement> col = rows.get(1).findElements(By.tagName("td"));
			for (i=1;i<col.size();i++) 
			{
				booking[i-1] = col.get(i).getText();
				System.out.println(refBook[i-1]+" & "+booking[i-1]);
				if(refBook[i-1].equals(booking[i-1]))
				{
					j++;
				}
			}
			
			if(j==booking.length)
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