package webdriver;

import java.io.File;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Wait_P6_Implicit_Explicit_Mix {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		
		driver.manage().window().maximize();
	}
	
	//Mix + Found
	@Test
	public void TC_01_Element_Found() {
		driver.get("https://www.facebook.com/");
		By emailID = By.id("email");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		System.out.println("Start im: " + getTimeNow());
		driver.findElement(emailID).isDisplayed();
		System.out.println("End im: " + getTimeNow());
		
		System.out.println("Start ex: " + getTimeNow());
		explicitWait = new WebDriverWait(driver, 15);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailID));
		System.out.println("End ex: " + getTimeNow());
	}
	
	//Not mix, not found
	@Test
	public void TC_02_Element_Not_Found_Imp() {
		driver.get("https://www.facebook.com/");
		By emailID = By.id("emaillllll");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		System.out.println("Start im: " + getTimeNow());
		driver.findElement(emailID).isDisplayed();
		System.out.println("End im: " + getTimeNow());
	}
	
	@Test
	public void TC_03_Element_Not_Found_Exp_By() {
		driver.get("https://www.facebook.com/");
		//Not define imp => imp = 0
		//Defines locator only, not element
		By emailID = By.id("emailsss");
		explicitWait = new WebDriverWait(driver, 15);
		
		System.out.println("Start ex: " + getTimeNow());
		//Failed at line 69 due to finding element in visibilityOfElementLocated always got failed in 15s 
		//Find element is wrapped in visibilityOfElementLocated()
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailID));
		System.out.println("End ex: " + getTimeNow());
	}
	
	
	//Mix + Not found
	@Test
	public void TC_04_Element_Not_Found() {
		driver.get("https://www.facebook.com/");
		By emailID = By.id("emailllll");
		
		//Imp < exp
//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);		
//		System.out.println("Start im: " + getTimeNow());
//		try {
//			driver.findElement(emailID).isDisplayed();
//		} catch (Exception e) {
//			
//		}
//		System.out.println("End im: " + getTimeNow());
//			
//		System.out.println("Start ex: " + getTimeNow());
//		explicitWait = new WebDriverWait(driver, 10);
//		try {
//			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailID));
//		} catch (Exception e) {
//			
//		}
//		System.out.println("End ex: " + getTimeNow());
		
		
		//Imp = exp
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
//		System.out.println("Start im: " + getTimeNow());
//		try {
//			driver.findElement(emailID).isDisplayed();
//		} catch (Exception e) {
//			
//		}
//		System.out.println("End im: " + getTimeNow());
//			
//		System.out.println("Start ex: " + getTimeNow());
//		explicitWait = new WebDriverWait(driver, 10);
//		try {
//			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailID));
//		} catch (Exception e) {
//			
//		}
//		System.out.println("End ex: " + getTimeNow());
		
		//Imp > exp
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
		System.out.println("Start im: " + getTimeNow());
		try {
			driver.findElement(emailID).isDisplayed();
		} catch (Exception e) {
			
		}
		System.out.println("End im: " + getTimeNow());
			
		System.out.println("Start ex: " + getTimeNow());
		explicitWait = new WebDriverWait(driver, 5);
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailID));
		} catch (Exception e) {
			
		}
		System.out.println("End ex: " + getTimeNow());
		
	}	
	
	@Test
	public void TC_05_Element_Not_Found_Exp_WE() {
		driver.get("https://www.facebook.com/");
		//Not define imp => imp = 0
		//Defines element
		//Failed at line 143 due to not found element
		WebElement emailIDTextbox = driver.findElement(By.id("emaileeee"));

		//Take 0s bcuz stops at line 143, not run to below steps
		explicitWait = new WebDriverWait(driver, 15);
		System.out.println("Start ex: " + getTimeNow());
		explicitWait.until(ExpectedConditions.visibilityOf(emailIDTextbox));
		System.out.println("End ex: " + getTimeNow());		
	}
	
	@Test
	public void TC_06_Element_Not_Found_Exp_WE_2() {
		driver.get("https://www.facebook.com/");
		//Not define imp => imp = 0
		//Take 0s bcuz stops at line 143, not run to below steps
		explicitWait = new WebDriverWait(driver, 15);
		System.out.println("Start ex: " + getTimeNow());
		
		//Failed at line 165 bcuz: find element first, got not found => failed in 0s
		explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("emaileeee"))));
		System.out.println("End ex: " + getTimeNow());		
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public String getTimeNow() {
		Date date = new Date();
		return date.toString();
	}

}