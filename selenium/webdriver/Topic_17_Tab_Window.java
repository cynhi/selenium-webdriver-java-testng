package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Tab_Window {
	WebDriver driver;
	WebDriverWait explicitWait;
	Alert alert;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
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
		driver.get("https://kyna.vn/");
		sleepInSecond(2);
		clickByJS(driver.findElement(By.xpath("//div[@class='hotline']//img[@alt='facebook']")));
		sleepInSecond(2);
		switchToTabByTitle("Kyna.vn - Home | Facebook");
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/kyna.vn");
		sleepInSecond(2);
		switchToTabByTitle("Kyna.vn - Học online cùng chuyên gia");
		clickByJS(driver.findElement(By.xpath("//div[@class='hotline']//img[@alt='youtube']")));
		sleepInSecond(4);
		switchToTabByTitle("Kyna.vn - YouTube");
		sleepInSecond(2);
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/user/kynavn");
		switchToTabByTitle("Kyna.vn - Học online cùng chuyên gia");
		Set<String> allTabIDs = driver.getWindowHandles();
		for(String id : allTabIDs) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if(!actualTitle.equals("Kyna.vn - Học online cùng chuyên gia")) {
				driver.close();
			}
		}
	}
	
	
	@Test
	public void TC_04_() {
		driver.get("http://live.techpanda.org/");
		sleepInSecond(2);
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Sony Xperia has been added to comparison list.']")).isDisplayed());
		driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']")).isDisplayed());
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		switchToTabByTitle("Products Comparison List - Magento Commerce");
		sleepInSecond(2);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/catalog/product_compare/index/");
//		driver.close();
		driver.findElement(By.xpath("//button[@title='Close Window']")).click();
		switchToTabByTitle("Mobile");
		sleepInSecond(2);
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		sleepInSecond(2);
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The comparison list was cleared.");
	}
	
	
	@Test
	public void TC_05_() {
		driver.get("https://dictionary.cambridge.org/vi/");
		sleepInSecond(2);
		driver.findElement(By.xpath("//span[text()='Đăng nhập']")).click();
		sleepInSecond(2);
		switchToTabByTitle("Login");
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[@data-bound-to='loginID' and text()='This field is required']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[@data-bound-to='password' and text()='This field is required']")).isDisplayed());
		driver.findElement(By.xpath("//input[@name='username' and @aria-label='Email']")).sendKeys("automationfc.com@gmail.com");
		sleepInSecond(1);
		driver.findElement(By.xpath("//form[contains(@class,'login-form')]//input[@name='password' and @aria-label='Password']")).sendKeys("Automation000***");
		sleepInSecond(2);
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		switchToTabByTitle("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@amp-access = 'loggedIn']//span[text()='Automation FC']")).isDisplayed());
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
	
	public void clickByJS(WebElement element) {
		jsExecutor.executeScript("arguments[0].click();", element);
	}

}