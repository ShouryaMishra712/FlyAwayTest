package project.FlyAwayTest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Signup {
	public WebDriver driver;
	
	@FindBy(linkText = "Login/Signup")
	WebElement loginSignup;
	
	@FindBy(linkText = "Not a member? Signup")
	WebElement signup;
	
	@FindBy(name = "email_id")
	WebElement email;
	
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
	
	@FindBy(xpath = "//button[text()='Signup']")
	WebElement signupBtn;

	public Signup(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean SignUp(String URL,String emailID,String Pass1,String Pass2, String name , String address , String city) throws InterruptedException
	{
		driver.get(URL);
		Thread.sleep(3000);
		loginSignup.click();
		Thread.sleep(3000);
		signup.click();
		Thread.sleep(3000);
		email.sendKeys(emailID);
		Thread.sleep(3000);
		pass.sendKeys(Pass1);
		Thread.sleep(3000);
		pass2.sendKeys(Pass2);
		Thread.sleep(3000);
		Name.sendKeys(name);
		Thread.sleep(3000);
		Address.sendKeys(address);
		Thread.sleep(3000);
		City.sendKeys(city);;
		Thread.sleep(3000);
		signupBtn.click();
		return true;
	}
}
