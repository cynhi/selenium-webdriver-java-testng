package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_09_Dropdown_Default {
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
	public void TC_01_Rode() {
		driver.get("https://www.rode.com/wheretobuy");
		
		Select countryDropdown = new Select(driver.findElement(By.id("where_country")));
		Assert.assertFalse(countryDropdown.isMultiple());
		countryDropdown.selectByVisibleText("Vietnam");
		sleepInSecond(3);
		Assert.assertEquals(countryDropdown.getFirstSelectedOption().getText(), "Vietnam");
		Assert.assertEquals(countryDropdown.getOptions().size(), 233);
		
		driver.findElement(By.id("search_loc_submit")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector(".result_count>span")).getText(), "31");
		
		List <WebElement> distributorNames = driver.findElements(By.xpath("//div[@class='result_item']//div[@class='store_name']"));
		for (WebElement distributor : distributorNames) {
			System.out.print(distributor.getText() + "\n");
		}
	}

	@Test
	public void TC_02_Nopcommerce() {
		String inputFirstName = "Gee";
		String inputLastName = "Kwon";
		String inputDay = "10";
		String inputMonth = "May";
		String inputYear = "1980";
		String inputPassword = "123456";
		String inputEmail = getRandomEmail();
		
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.cssSelector(".ico-register")).click();
		
		driver.findElement(By.id("gender-female")).click();
		driver.findElement(By.id("FirstName")).sendKeys(inputFirstName);
		driver.findElement(By.id("LastName")).sendKeys(inputLastName);
		Select dayDropdown = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(dayDropdown.getOptions().size(), 32);
		dayDropdown.selectByVisibleText(inputDay);
		
		Select monthDropDown = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(monthDropDown.getOptions().size(), 13);
		monthDropDown.selectByVisibleText(inputMonth);
		
		Select yearDropdown = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(yearDropdown.getOptions().size(), 112);
		yearDropdown.selectByVisibleText(inputYear);
		
		driver.findElement(By.id("Email")).sendKeys(inputEmail);
		driver.findElement(By.id("Password")).sendKeys(inputPassword);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(inputPassword);
		driver.findElement(By.id("register-button")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class ='result' and text()='Your registration completed']")).isDisplayed());
		driver.findElement(By.className("ico-account")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='page-title']/h1")).getText(), "My account - Customer info");
		
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), inputFirstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), inputLastName);
		
		dayDropdown = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(dayDropdown.getFirstSelectedOption().getText(), inputDay);
		
		monthDropDown = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(monthDropDown.getFirstSelectedOption().getText(), inputMonth);
		
		yearDropdown = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(yearDropdown.getFirstSelectedOption().getText(), inputYear);
		
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), inputEmail);
		
		
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
	
	public String getRandomEmail() {
		Random rNumber = new Random();
		String rEmail = "kjy_" + rNumber.nextInt(99999) + "@gmail.com";
		return rEmail;
	}


}