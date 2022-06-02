package testNG;

import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class Topic_05_Data_Provider {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	By emailTextbox = By.xpath("//input[@id='email']");
	By pwdTextbox = By.xpath("//input[@id='pass']");
	By loginButton = By.xpath("//button[@title='Login']");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test(dataProvider = "data")
	public void register(String userName, String password) {
		System.out.println(userName);
		System.out.println(password);
	}

	@Test(dataProvider = "data")
	public void login(String userName, String password) {
		driver.get("http://live.techpanda.org/index.php/customer/account/login");
		driver.findElement(emailTextbox).sendKeys(userName);
		driver.findElement(pwdTextbox).sendKeys(password);
		driver.findElement(loginButton).click();

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(userName));

		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
	}

	@DataProvider(name = "data")
	public Object[][] loginData(Method method) {
		Object[][] obj = null;
		if (method.getName().equals("register")) {
			obj = new Object[][] { { "selenium_12@gmail.com", "111111" }, { "selenium_13@gmail.com", "111111" },
					{ "selenium_14@gmail.com", "111111" }

			};
		} else {
			obj = new Object[][] { { "selenium_11_01@gmail.com", "111111" }, { "selenium_11_02@gmail.com", "111111" },
					{ "selenium_11_03@gmail.com", "111111" } };
		}

		return obj;

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
