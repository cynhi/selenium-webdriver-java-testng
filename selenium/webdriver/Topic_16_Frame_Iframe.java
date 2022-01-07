package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;

public class Topic_16_Frame_Iframe {
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
	public void TC_01_Iframe() {
		driver.get("https://kyna.vn/");
		WebElement iframeFB = driver.findElement(By.xpath("//div[@class='face-content']//iframe"));
		//Switch Default => Facebook iframe
		driver.switchTo().frame(iframeFB);
		WebElement FBlikes = driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div"));
		Assert.assertEquals(FBlikes.getText(), "167K likes");
		
		//Switch Facebook => Default iframe before switching to another iframe
		driver.switchTo().defaultContent();
		
		WebElement iframeChat = driver.findElement(By.xpath("//iframe[@id='cs_chat_iframe']"));
		//Switch Default => Chat iframe
		driver.switchTo().frame(iframeChat);
		driver.findElement(By.cssSelector("div.button_bar")).click();
		sleepInSecond(2);
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("abcde");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("09090909090");
		new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
		driver.findElement(By.cssSelector("textarea[name='message']")).sendKeys("Register new Course");
		
		//Switch Chat => Default iframe
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//input[@id='live-search-bar']")).sendKeys("Excel");
		driver.findElement(By.xpath("//button[@class='search-button']")).click();  
		sleepInSecond(3);
		List<WebElement> courseNames = driver.findElements(By.xpath("//ul[@class='k-box-card-list']//h4"));
		for (WebElement course : courseNames) {
			Assert.assertTrue(course.getText().toLowerCase().contains("excel"));
		}
	}

	@Test
	public void TC_02_Frame(){
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		WebElement loginFrame = driver.findElement(By.xpath("//frame[@name='login_page']"));
		driver.switchTo().frame(loginFrame);
		driver.findElement(By.xpath("//input[@name='fldLoginUserId']")).sendKeys("automation");
		driver.findElement(By.xpath("//a[contains(@class,'login-btn')]")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='fldPassword']")).isDisplayed());
		
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