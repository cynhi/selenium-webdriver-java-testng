package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Tab_Window {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Only_2_Tabs_Or_Windows() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(2);
		String parentTabID = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		//Switch to Google tab
		switchToTabByID(parentTabID);
		Assert.assertEquals(driver.getCurrentUrl(), "https://google.com.vn/");
		
		String googleTabID = driver.getWindowHandle();
		//Switch to parent tab
		switchToTabByID(googleTabID);
		
	}

	@Test
	public void TC_02_Multi_Tabs() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String parentTabTitle = "SELENIUM WEBDRIVER FORM DEMO";
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		switchToTabByTitle("Google");
		sleepInSecond(3);
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
		switchToTabByTitle(parentTabTitle);
		
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(3);
		switchToTabByTitle("Facebook – log in or sign up");
		sleepInSecond(3);
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/");
		switchToTabByTitle(parentTabTitle);
		
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(3);
		switchToTabByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		sleepInSecond(3);
		Assert.assertEquals(driver.getCurrentUrl(), "https://tiki.vn/");
		switchToTabByTitle(parentTabTitle);
		
		Set<String> allTabIDs = driver.getWindowHandles();
		for(String id : allTabIDs) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if(!actualTitle.equals(parentTabTitle)) {
				driver.close();
			}
		}
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
	}

	@Test
	public void TC_03_() {
	}
	
	@Test
	public void TC_04_() {
	}
	
	@Test
	public void TC_05_() {
	}
	
	@AfterClass
	public void afterClass() {
//		driver.quit();
	}
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//Switch to tab that its ID differs from unexpectedID
	public void switchToTabByID (String unexpectedID) {
		Set<String> allTabIDs = driver.getWindowHandles();
		for (String id : allTabIDs) {
			if(!id.equals(unexpectedID)) {
				driver.switchTo().window(id);
				break;
			}
			
		}
		
	}
	
	//Switch to tab by title
	public void switchToTabByTitle (String expectedTitle) {
		Set<String> allTabIDs = driver.getWindowHandles();
		for(String id : allTabIDs) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if(actualTitle.equals(expectedTitle)) {
				break;
			}
		}
		
	}

}