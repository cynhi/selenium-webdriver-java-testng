package testNG;

import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class Topic_07_Loop {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	By emailTextbox = By.xpath("//input[@id='email']");
	By pwdTextbox = By.xpath("//input[@id='pass']");
	By loginButton = By.xpath("//button[@title='Login']");
	String emailAddress = "kjy";
	String password = "123456";

	@BeforeClass
	public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
//		driver = new FirefoxDriver();
//		
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
	}
	
	@Test(invocationCount = 10)
	public void register() {
		 System.out.println(emailAddress + getRandomNumber() + "@gmail.com");
		 System.out.println(password);
	 }
	

  
  
  @AfterClass
  public void afterClass() {
//	  driver.quit();
  }
  
  public int getRandomNumber() {
	  Random rand = new Random();
	  return rand.nextInt();
  }
}
