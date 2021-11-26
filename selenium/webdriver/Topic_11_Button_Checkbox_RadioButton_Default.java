package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Button_Checkbox_RadioButton_Default {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Button_JsExecutor() {
		driver.get("https://www.fahasa.com/customer/account/create");
		By loginTab = By.cssSelector("li.popup-login-tab-login");
		By loginButton = By.cssSelector("button.fhs-btn-login");
		
		driver.findElement(loginTab).click();
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		
		driver.findElement(By.id("login_username")).sendKeys("cyn@gmail.com");
		driver.findElement(By.id("login_password")).sendKeys("12345678");
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		
		//getCSSValue returns rgb background color which might be incorrect on different browsers => use hexa string always correct on any browser
		String loginButtonBgColor = Color.fromString(driver.findElement(loginButton).getCssValue("background-color")).asHex().toUpperCase();
		
		Assert.assertEquals(loginButtonBgColor, "#C92127");
		
		driver.navigate().refresh();
		driver.findElement(loginTab).click();
		
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled');", driver.findElement(loginButton));
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		driver.findElement(loginButton).click();
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='login_username']/parent::div/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='login_password']/parent::div/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
	}

	@Test
	public void TC_02_Default_Checkbox() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		By dualZoneAirCheckbox= By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
		driver.findElement(dualZoneAirCheckbox).click();
		Assert.assertTrue(driver.findElement(dualZoneAirCheckbox).isSelected());
		sleepInSecond(2);
		driver.findElement(dualZoneAirCheckbox).click();
		Assert.assertFalse(driver.findElement(dualZoneAirCheckbox).isSelected());
	}

	@Test
	public void TC_03_Default_Radio() {
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		By twoPetrolRadioButton = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
		driver.findElement(twoPetrolRadioButton).click();
		if(!driver.findElement(twoPetrolRadioButton).isSelected()) {
			driver.findElement(twoPetrolRadioButton).click();
		}
	}
	
	@Test
	public void TC_04_Multiple_Checkbox() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type ='checkbox']"));
		for(WebElement checkbox :  checkboxes) {
			if(!checkbox.isSelected()) {
				checkbox.click();
				sleepInSecond(1);
			}
			Assert.assertTrue(checkbox.isSelected());
		}
		
		for(WebElement checkbox :  checkboxes) {
			if(checkbox.isSelected()) {
				checkbox.click();
				sleepInSecond(1);
			}
			Assert.assertFalse(checkbox.isSelected());
		}
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