package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		
//		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
//		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/r.php");
	}

	@Test
	public void TC_01_ID() {
		driver.findElement(By.id("password_step_input")).sendKeys("12345678");
		sleepInSecond(3);
	}

	@Test
	public void TC_02_Class() {
		driver.findElement(By.className("registration_redesign")).isDisplayed();
		sleepInSecond(3);
	}

	@Test
	public void TC_03_Name() {
		driver.findElement(By.name("reg_email__")).sendKeys("kjy@gmail.com");
		sleepInSecond(3);
	}
	
	@Test
	public void TC_04_LinkText() {
		driver.findElement(By.linkText("Bạn đã có tài khoản?")).click();
		sleepInSecond(3);
	}
	
	@Test
	public void TC_05_PartialLinkText() {
		driver.findElement(By.partialLinkText("tài khoản?")).click();
		sleepInSecond(3);
	}
	
	@Test
	public void TC_06_TagName() {
		int inputSize = driver.findElements(By.tagName("input")).size();
		System.out.println("Input size = " + inputSize );
		sleepInSecond(3);
	}
	
	@Test
	public void TC_07_Css() {
		driver.findElement(By.cssSelector("input[id='identify_email']")).sendKeys("nhi@gmail.com");
		sleepInSecond(3);
	}
	
	@Test
	public void TC_08_Xpath() {
		driver.findElement(By.xpath("//button[@name='did_submit']")).click();
		sleepInSecond(3);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}