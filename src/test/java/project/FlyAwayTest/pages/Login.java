package project.FlyAwayTest.pages;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {

	public WebDriver driver;
	
	@FindBy(name = "email_id")
	public WebElement emailID;
	
	@FindBy(name = "pwd")
	public WebElement passwrd;

	@FindBy(xpath = "//button[text()='Login']")
	public WebElement loginButton;
	
	public Login(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}	
	public void Login(String URL,String email,String pass) throws InterruptedException
	{
		driver.get(URL);
		driver.findElement(By.linkText("Login/Signup")).click();
		Thread.sleep(3000);

		emailID.sendKeys(email);
		Thread.sleep(3000);

		passwrd.sendKeys(pass);
		Thread.sleep(3000);
		
		getScreenShot(driver,"loginData");
		loginButton.click();
		
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
