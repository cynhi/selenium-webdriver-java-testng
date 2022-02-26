package webdriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Wait_P2_FindElement_ImplicitWait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
	}
 
	@Test
	public void TC_01_FindElement() {
		//---Has only 1 element---
		//If found element => returns element
		//If not found => after 0.5s, retry... until timeout (10s - set at line 28)
	
		
//		System.out.println("Start time: " + getCurrentTime());
//		driver.findElement(By.xpath("//input[@name='firstname']"));
//		System.out.println("End time: " + getCurrentTime());
		
		
		//---Has no element---
		//If not found => after 0.5s, retry... until timeout (10s - set at line 28)
		//TC failed at step findElement + throw  exception

//			System.out.println("Start time: " + getCurrentTime());
//		driver.findElement(By.xpath("//input[@name='firstname']"));
		//==> findElement should not be used to find for a non-presence element, use findElements instead
		
		
		//---Has many element---
		//Get 1st element
		
//		driver.findElement(By.xpath("//div[@id='pageFooterChildren']//a[text()]")).click();
		
		
	}

	@Test
	public void TC_02_FindElements() {
		//---Has only 1 element---
		//---Has many element---
		//If found element => returns element
		//If not found => after 0.5s, retry... until timeout (10s - set at line 28)
		int numb = 0;
		numb = driver.findElements(By.xpath("//input[@id='email']")).size();
		System.out.println("1 found: " + numb);
		
		numb = driver.findElements(By.xpath("//div[@id='pageFooterChildren']//a[text()]")).size();
		System.out.println("many found: " + numb);
		
		
		//USE TO VERITY FOR AN ELEMENT THAT DOES NOT EXIST
		//---Has no element---
		//If not found => after 0.5s, retry... until timeout (10s - set at line 28)
		//TC PASSED & continue to run even when not found element
		System.out.println("Start time: " + getCurrentTime());
		driver.findElements(By.xpath("//input[@name='cynhi']"));
		System.out.println("End time: " + getCurrentTime());
		System.out.println("Not found: " + numb);

	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	
	public String getCurrentTime() {
		Date date = new Date();
		return date.toString();
	}
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}