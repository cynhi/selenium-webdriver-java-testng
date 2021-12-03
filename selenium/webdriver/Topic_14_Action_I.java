package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Action_I {
	WebDriver driver;
	Actions action;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		
		action = new Actions(driver);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Hover() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		sleepInSecond(3);
		action.moveToElement(driver.findElement(By.id("age"))).perform();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.className("ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
	}

	@Test
	public void TC_02_Hover_And_Click() {
		driver.get("http://www.myntra.com/");
		sleepInSecond(3);
		action.moveToElement(driver.findElement(By.xpath("//a[@data-group='kids']"))).perform();
		sleepInSecond(3);
		action.click(driver.findElement(By.xpath("//a[text()='Home & Bath']"))).perform();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='title-title']")).getText(), "Kids Home Bath");
		
	}

	@Test
	public void TC_03_Hove_And_Click() {
		driver.get("https://www.fahasa.com/");
		sleepInSecond(3);
		action.moveToElement(driver.findElement(By.xpath("//div[contains(@class, 'background-menu-homepage')]//a[@title= 'Làm Đẹp - Sức Khỏe']"))).perform();
		sleepInSecond(3);
		WebElement subItem = driver.findElement(By.xpath("//div[contains(@class, 'background-menu-homepage')]//a[text()='Sản Phẩm Làm Đẹp']"));
		Assert.assertTrue(subItem.isDisplayed());
		action.click(subItem).perform();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='mb-breadcrumbs']//strong")).getText(),"SẢN PHẨM LÀM ĐẸP");
	}
	
	@Test
	public void TC_04_Click_And_Hold_Blocks() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		sleepInSecond(3);
		
		List<WebElement> numbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		action.clickAndHold(numbers.get(0)).moveToElement(numbers.get(7)).release().perform();
		sleepInSecond(3);
		
		List<WebElement> clickedNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(clickedNumbers.size(), 8);
	}
	
	@Test
	public void TC_05_Click_And_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		sleepInSecond(3);
		
		List<WebElement> numbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		action.keyDown(Keys.COMMAND).perform();
		action.click(numbers.get(2)).click(numbers.get(4)).click(numbers.get(10)).click(numbers.get(7)).perform();
		action.keyUp(Keys.COMMAND).perform();
		List<WebElement> clickedNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(clickedNumbers.size(), 4);
		
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