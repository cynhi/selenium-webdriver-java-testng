package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Handle_Popup {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// Fixed, still in DOM even when not show on view
	@Test
	public void TC_01_Fixed_Popup() {
		driver.get("https://ngoaingu24h.vn/");
		//Should use By (locator) instead of WebElement.
		//By only define locator of element
		//WE contains element and its PROPERTIES (which would be changed after user action) => failed when verify after action
		By modalLoginPopup = By.xpath("//div[@id='modal-login-v1']");
		
		//Find element before action then verify
		Assert.assertFalse(driver.findElement(modalLoginPopup).isDisplayed());
		//Action
		driver.findElement(By.cssSelector("button.login_")).click();
		sleepInSecond(2);
		//Find element after action then verify
		Assert.assertTrue(driver.findElement(modalLoginPopup).isDisplayed());
		
		driver.findElement(By.xpath("//input[@name = 'account-input']")).sendKeys("kjymanse@gmail.com");
		driver.findElement(By.xpath("//input[@name = 'password-input']")).sendKeys("123456");
		driver.findElement(By.xpath("//button[contains(@class,'btn-login-v1')]")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='modal-login-v1']//div[contains(@class, 'error-login-panel')]")).getText(), "Tài khoản không tồn tại!");
		
		driver.findElement(By.xpath("//div[@id='modal-login-v1']//button[@class='close']")).click();
		sleepInSecond(2);
		Assert.assertFalse(driver.findElement(modalLoginPopup).isDisplayed());
	}

	// Fixed, still in DOM even when not show on view
	@Test
	public void TC_02_Fixed_Popup() {
		driver.get("https://bizbooks.vn/");
		sleepInSecond(2);
		By loginPopup = By.xpath("//div[@id='md-signin']");
		By registerPopup = By.xpath("//div[@id='md-signup']");
		
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		Assert.assertFalse(driver.findElement(registerPopup).isDisplayed());
	   
		driver.findElement(By.xpath("//span[text()='ĐĂNG NHẬP']")).click();
		sleepInSecond(2);
		driver.findElement(By.xpath("//div[@class='header__elements']//a[text()='Đăng nhập']")).click();
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		Assert.assertFalse(driver.findElement(registerPopup).isDisplayed());
		
		driver.findElement(By.xpath("//div[@id='md-signin']//input[@name='email']")).sendKeys("abcdef@gmail.com");
		driver.findElement(By.xpath("//div[@id='md-signin']//input[@name='password']")).sendKeys("abcdef");
		driver.findElement(By.xpath("//div[@id='md-signin']//button[contains(@class,'js-btn-member-login')]")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='md-signin']//span[@class='text-danger']")).getText(), "Tài khoản không tồn tại");
		driver.findElement(By.xpath("//div[@id='md-signin']//button[contains(@class,'js-btn-member-login')]")).sendKeys(Keys.ESCAPE);
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		Assert.assertFalse(driver.findElement(registerPopup).isDisplayed());
	}
	
	// Fixed, not in DOM when not show on view
	@Test
	public void TC_03_Fixed_Popup() {
		driver.get("https://tiki.vn/");
		sleepInSecond(2);
		By loginPopup = By.xpath("//div[contains(@class,'LoginWithPhone')]");
		
		driver.findElement(By.xpath("//div[@data-view-id = 'header_header_account_container']")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		driver.findElement(By.xpath("//button[@class='btn-close']")).click();
		sleepInSecond(2);
		
	}
	
	@Test
	public void TC_04_Fixed_Popup() {
		driver.get("https://jtexpress.vn/");
		sleepInSecond(2);
		By homepagePopup = By.xpath("//div[@id='homepage-popup']");
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(homepagePopup));
		Assert.assertTrue(driver.findElement(homepagePopup).isDisplayed());
		
		driver.findElement(By.xpath("//div[@id='homepage-popup']//button[@class='close']")).click();
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(homepagePopup));
		Assert.assertFalse(driver.findElement(homepagePopup).isDisplayed());
		
		driver.findElement(By.xpath("//div[@id='track']//input[@name='billcodes']")).sendKeys("841000072647");
		driver.findElement(By.xpath("//div[@id='track']//button[text()='Tra cứu vận đơn']")).click();
		sleepInSecond(5);
	}
	
	@Test
	public void TC_04_() {
	}
	
	@Test
	public void TC_05_() {
	}
	
	@Test
	public void TC_06_() {
	}
	
	@Test
	public void TC_07_() {
	}
	
	@Test
	public void TC_08_() {
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