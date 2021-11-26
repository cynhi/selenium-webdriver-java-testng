package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Button_Checkbox_RadioButton_Custom {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		
		
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Custom_Radio_01() {
		//Method1: 1 element but has 2 locators => take much time when maintaining code
		//<input> item cannot be clicked (by sel in-built func) but can be asserted, <span> item can be clicked (by sel in-built func) but cannot be asserted
		//=> use <span> to click (by sel in-built func), <input> to verify
		driver.get("https://material.angular.io/components/radio/examples");
		sleepInSecond(2);
		WebElement winterRadio = driver.findElement(By.xpath("//input[@value='Winter']"));
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", winterRadio);
		winterRadio.click();
		Assert.assertTrue(winterRadio.isSelected());
	
	}

	@Test
	public void TC_01_Custom_Radio_02() {
		//Method2: 1 element 1 locator <input>
		//Use JSexecutor to click on invisible <input> then verify
		//=> best practice
		driver.get("https://material.angular.io/components/radio/examples");
		WebElement winterRadio = driver.findElement(By.xpath("//input[@value='Winter']"));
		jsExecutor.executeScript("arguments[0].click();", winterRadio);
		Assert.assertTrue(winterRadio.isSelected());
	}

	@Test
	public void TC_02_Custom_Checkbox() {
		driver.get("https://material.angular.io/components/checkbox/examples");
		sleepInSecond(2);
		
		WebElement checkedCheckbox = driver.findElement(By.xpath("//input[@id='mat-checkbox-1-input']"));
		WebElement indeterminateCheckbox = driver.findElement(By.xpath("//input[@id='mat-checkbox-2-input']"));
		
		checkCheckbox(checkedCheckbox);
		Assert.assertTrue(checkedCheckbox.isSelected());
		
		checkCheckbox(indeterminateCheckbox);
		Assert.assertTrue(checkedCheckbox.isSelected());
		
		sleepInSecond(1);
		uncheckCheckbox(checkedCheckbox);
		Assert.assertFalse(checkedCheckbox.isSelected());
		
		uncheckCheckbox(indeterminateCheckbox);
		Assert.assertFalse(checkedCheckbox.isSelected());
		
	}
	
	@Test
	public void TC_03_Custom_Radio_Checkbox() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		WebElement ctCheckbox = driver.findElement(By.xpath("//div[@data-value='Cần Thơ']"));
		Assert.assertTrue(driver.findElement(By.xpath("//div[@data-value='Cần Thơ' and @aria-checked='false']")).isDisplayed());
		ctCheckbox.click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@data-value='Cần Thơ' and @aria-checked='true']")).isDisplayed());
		
		List<WebElement> checkboxes = driver.findElements(By.xpath("//div[starts-with(@aria-label, 'Quảng') and @role ='checkbox']"));
		for (WebElement checkbox :checkboxes) {
			checkbox.click();
			sleepInSecond(1);
			Assert.assertEquals(checkbox.getAttribute("aria-checked"), "true");
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void checkCheckbox(WebElement checkbox) {
		if(!checkbox.isSelected()) {
			jsExecutor.executeScript("arguments[0].click();", checkbox);
		}
	}
	
	public void uncheckCheckbox(WebElement checkbox) {
		if(checkbox.isSelected()) {
			jsExecutor.executeScript("arguments[0].click();", checkbox);
		}
	}
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}