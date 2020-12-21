package project.FlyAwayTest.testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import project.FlyAwayTest.pages.Bookings;
import project.FlyAwayTest.pages.EditProfile;
import project.FlyAwayTest.pages.Home;
import project.FlyAwayTest.pages.Login;
import project.FlyAwayTest.pages.Signup;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class UITestCases {
	private static final String FileUtils = null;
	WebDriver driver;
	String URL = "http://localhost:8080/FlyAway/";
	
	@FindBy(linkText = "Login to continue checking flights")
	WebElement loginLink;
	
	@Test
	public void T1() throws InterruptedException //Search Flight & Verify Data
	{
		String email = "shm71298@gmail.com";
		String pass = "12345";
		
		Login login = new Login(driver);
		login.Login(URL, email, pass);//verify login 
		getScreenShot(driver,"afterLogin");
		
		Home home = new Home(login.driver);
		AssertJUnit.assertTrue(home.home.isDisplayed());//verify login done home screen present
		String from = "Bangalore";
		String to = "Chennai";
		home.searchFlight(from, to);//search flight
		getScreenShot(driver,"afterSearch");
		
		String[] refData = {from,to,"AirAsia","Dep.8 pm Arr.9.30pm (1.30 hours)","4500.00"};
		AssertJUnit.assertEquals(home.verifyData(refData), true);//verify searched flight
		
		Bookings bookings = new Bookings(home.driver);
		AssertJUnit.assertEquals(bookings.bookFlight(), true);//verify book flight
		getScreenShot(driver,"afterBooking");
		
		String[] refBook = {from,to,"Dep.8 pm Arr.9.30pm (1.30 hours)","4500.00"};
		AssertJUnit.assertEquals(bookings.verifyBooking(refBook), true);//verify booked flight
		getScreenShot(driver,"verifyBooking");
		
	}
	
	@Test
	public void T2() throws InterruptedException 
	{
		String email = "shm101@gmail.com";
		String pass = "123456";
		String name = "Shourya Mishra";
		String address = "37-Suraj Nagar,Khatipura";
		String city = "Indore";
	
		Signup signup = new Signup(driver);
		AssertJUnit.assertEquals(signup.SignUp(URL, email, pass, pass , name, address, city), true);// verify sign up user
		getScreenShot(driver,"afterSignup");
		
		Thread.sleep(5000);
		
		Login login = new Login(signup.driver);
		login.Login(URL, email, pass);//verify login of signed up user
		
		String Pass = "123456";
		String Name = "Surya Mishra";
		String Address = "Mrinal Residency";
		String City = "Bhopal";
		
		EditProfile editProfile = new EditProfile(driver);
		AssertJUnit.assertEquals(editProfile.EditProfileUser(Pass, Pass, Name, Address, City), true);// verify edit profile 
		
	}
	
	@BeforeMethod
	public void beforeMethod() 
	{
		System.setProperty("webdriver.chrome.driver", "files/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@AfterMethod
	public void afterMethod() 
	{
		driver.close();
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
