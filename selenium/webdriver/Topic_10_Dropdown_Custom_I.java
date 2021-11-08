package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

public class Topic_10_Dropdown_Custom_I {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor je = (JavascriptExecutor) driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		
		explicitWait = new WebDriverWait(driver, 40);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_JQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		sleepInSecond(2);
		selectCustomDropdownItem("//span[@id ='number-button']", "//ul[@id='number-menu']/li/div", "19");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "19");

	}

	@Test
	public void TC_02_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		sleepInSecond(2);
		selectCustomDropdownItem("//div[@role = 'listbox']", "//div[@class='visible menu transition']/div", "Christian");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='listbox']/div[@role='alert']")).getText(), "Christian");
	}

	@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		sleepInSecond(2);
		selectCustomDropdownItem("//li[@class ='dropdown-toggle']", "//ul[@class ='dropdown-menu']/li/a", "Third Option");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class ='dropdown-toggle']")).getText(), "Third Option");
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
	
	public void selectCustomDropdownItem(String parentElement, String childElement, String itemText) {
		driver.findElement(By.xpath(parentElement)).click();
		sleepInSecond(2);
		
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childElement)));
		List<WebElement> childItems = driver.findElements(By.xpath(childElement));
		
		for (WebElement item : childItems) {
			if (item.getText().equals(itemText)) {
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(2);
				item.click();
				sleepInSecond(2);
				break;
			}
		}
		
	}

}