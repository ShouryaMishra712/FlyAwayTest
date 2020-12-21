package project.FlyAwayTest.pages;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditProfile {
	WebDriver driver;

	public EditProfile(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Edit Profile")
	WebElement edit;
	
	@FindBy(name = "pwd")
	WebElement pass;
	
	@FindBy(name = "pwd2")
	WebElement pass2;
	
	@FindBy(name = "name")
	WebElement Name;
	
	@FindBy(name = "address")
	WebElement Address;
	
	@FindBy(name = "city")
	WebElement City;
	
	@FindBy(xpath = "//Button[text()='Update']")
	WebElement updateBtn;

	public boolean EditProfileUser(String Pass1,String Pass2, String name , String address , String city) throws InterruptedException
	{
		Thread.sleep(3000);
		
		edit.click();
		
		Thread.sleep(3000);
		pass.sendKeys(Pass1);
		Thread.sleep(3000);
		pass2.sendKeys(Pass2);
		Thread.sleep(3000);
		Name.clear();
		Name.sendKeys(name);
		Thread.sleep(3000);
		Address.clear();
		Address.sendKeys(address);
		Thread.sleep(3000);
		City.clear();
		City.sendKeys(city);
		Thread.sleep(3000);
		getScreenShot(driver,"editData");
		
		updateBtn.click();
	
		return true;
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

