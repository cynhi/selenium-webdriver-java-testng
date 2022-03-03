package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import net.bytebuddy.utility.RandomString;

public class Topic_18_JS_Executor {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01() {
		driver.get("http://live.techpanda.org/");
		sleepInSecond(3);
		String domain = (String) jsExecutor.executeScript("return document.domain;");
		Assert.assertEquals(domain, "live.techpanda.org");
		
		String url = (String) jsExecutor.executeScript("return document.URL;");
		Assert.assertEquals("http://live.techpanda.org/", url);
		
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Menu']")));
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Mobile']")));
		
		WebElement ssgAddToCartBtn = driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button[@title='Add to Cart']"));
		jsExecutor.executeScript("arguments[0].click();", ssgAddToCartBtn);
		
		String textMessage = (String) jsExecutor.executeScript("return arguments[0].textContent;", driver.findElement(By.xpath("//li[@class='success-msg']//span")));
		Assert.assertEquals(textMessage, "Samsung Galaxy was added to your shopping cart.");
	
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Customer Service']")));
		sleepInSecond(1);
		String csTitle = (String) jsExecutor.executeScript("return document.title;");
		Assert.assertEquals(csTitle, "Customer Service");
		
		WebElement newsletterTextBox = driver.findElement(By.xpath("//input[@id='newsletter']"));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", newsletterTextBox);
		jsExecutor.executeScript("arguments[0].setAttribute('value', 'kjycyn@gmail.com');", newsletterTextBox);
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@title='Subscribe']")));
		sleepInSecond(1);
		textMessage = (String) jsExecutor.executeScript("return arguments[0].textContent;", driver.findElement(By.xpath("//li[@class='success-msg']//span")));
		Assert.assertEquals(textMessage, "Thank you for your subscription.");
		sleepInSecond(2);
		jsExecutor.executeScript("window.location = 'http://demo.guru99.com/v4/'");
		sleepInSecond(5);
		String domain2 = (String)jsExecutor.executeScript("return document.domain;");
		Assert.assertEquals(domain2, "demo.guru99.com");
	}

	@Test
	public void TC_02_() {
		driver.get("https://automationfc.github.io/html5/index.html");
		
		By name = By.xpath("//input[@id='fname']");
		By pwd = By.xpath("//input[@id='pass']");
		By email = By.xpath("//input[@id='em']");
		By address = By.xpath("//b[contains(text(), 'ADDRESS')]/parent::label/following-sibling::select");
		By submit = By.xpath("//input[@type='submit']");
		
		sleepInSecond(2);
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(submit));
		String validationMessage = (String)jsExecutor.executeScript("return arguments[0].validationMessage;", driver.findElement(name));
		Assert.assertEquals(validationMessage, "Please fill out this field.");
		
		jsExecutor.executeScript("arguments[0].setAttribute('value', 'kjymanse');", driver.findElement(name));
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(submit));
		validationMessage = (String)jsExecutor.executeScript("return arguments[0].validationMessage;", driver.findElement(pwd));
		Assert.assertEquals(validationMessage, "Please fill out this field.");
		
		jsExecutor.executeScript("arguments[0].setAttribute('value', '12345678');", driver.findElement(pwd));
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(submit));
		validationMessage = (String)jsExecutor.executeScript("return arguments[0].validationMessage;", driver.findElement(email));
		Assert.assertEquals(validationMessage, "Please fill out this field.");
		
		jsExecutor.executeScript("arguments[0].setAttribute('value', '12345678');", driver.findElement(email));
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(submit));
		validationMessage = (String)jsExecutor.executeScript("return arguments[0].validationMessage;", driver.findElement(email));
		Assert.assertEquals(validationMessage, "Please enter an email address.");
		
		jsExecutor.executeScript("arguments[0].setAttribute('value', '1234@5678');", driver.findElement(email));
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(submit));
		validationMessage = (String)jsExecutor.executeScript("return arguments[0].validationMessage;", driver.findElement(email));
		Assert.assertEquals(validationMessage, "Please match the requested format.");
		
		jsExecutor.executeScript("arguments[0].setAttribute('value', 'kjycyn@gmail.com');", driver.findElement(email));
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(submit));
		validationMessage = (String)jsExecutor.executeScript("return arguments[0].validationMessage;", driver.findElement(address));
		Assert.assertEquals(validationMessage, "Please select an item in the list.");
		
	}

	@Test
	public void TC_03_() {
		driver.get("https://login.ubuntu.com/");
		By popup = By.xpath("//div[@id='modal']");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(popup));
		Assert.assertTrue(driver.findElement(popup).isDisplayed());
		
		driver.findElement(By.xpath("//div[@id='modal']//button[text()='Accept all and visit site']")).click();
		By email = By.xpath("//form[@id='login-form']//input[@name='email']");
		
		jsExecutor.executeScript("arguments[0].setAttribute('value', '123');", driver.findElement(email));
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Log in']")));
		String validationMessage = (String)jsExecutor.executeScript("return arguments[0].validationMessage;", driver.findElement(email));
		Assert.assertEquals(validationMessage, "Please enter an email address.");
		
		
		jsExecutor.executeScript("window.location = 'https://sieuthimaymocthietbi.com/account/register'");
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@value='Đăng ký']")));
		validationMessage = (String)jsExecutor.executeScript("return arguments[0].validationMessage;", driver.findElement(By.xpath("//input[@id='lastName']")));
		Assert.assertEquals(validationMessage, "Please fill out this field.");
		
		jsExecutor.executeScript("window.location = 'https://warranty.rode.com/'");
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[contains(text(),'Register')]")));
		validationMessage = (String) jsExecutor.executeScript("return arguments[0].validationMessage;", driver.findElement(By.xpath("//input[@id='firstname']")));
		Assert.assertEquals(validationMessage, "Please fill out this field.");
		
		jsExecutor.executeScript("window.location = 'https://www.pexels.com/vi-vn/join-contributor/'");
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[contains(text(),'Tạo tài khoản mới')]")));
		validationMessage = (String) jsExecutor.executeScript("return arguments[0].validationMessage;", driver.findElement(By.xpath("//input[@id='user_first_name']")));
		Assert.assertEquals(validationMessage, "Please fill out this field.");
	}
	
	@Test
	public void TC_04_() {
		driver.get("http://demo.guru99.com/v4");
		
		jsExecutor.executeScript("arguments[0].setAttribute('value', 'mngr389721')", driver.findElement(By.xpath("//input[@name='uid']")));
		jsExecutor.executeScript("arguments[0].setAttribute('value', 'gYjAbyg')", driver.findElement(By.xpath("//input[@name='password']")));
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@name='btnLogin']")));
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='New Customer']")));
		sleepInSecond(2);
		jsExecutor.executeScript("arguments[0].setAttribute('value', 'kwonjiyong')", driver.findElement(By.xpath("//input[@name='name']")));
		jsExecutor.executeScript("arguments[0].removeAttribute('type');", "//input[@id='dob']");
		jsExecutor.executeScript("arguments[0].setAttribute('value', '08/18/1988')", driver.findElement(By.xpath("//input[@id='dob']")));
		jsExecutor.executeScript("arguments[0].setAttribute('value', '18\nItaewon')", driver.findElement(By.xpath("//textarea[@name='addr']")));
		jsExecutor.executeScript("arguments[0].setAttribute('value', 'Seoul')", driver.findElement(By.xpath("//input[@name='city']")));
		jsExecutor.executeScript("arguments[0].setAttribute('value', 'South Korea')", driver.findElement(By.xpath("//input[@name='state']")));
		jsExecutor.executeScript("arguments[0].setAttribute('value', '180888')", driver.findElement(By.xpath("//input[@name='pinno']")));
		jsExecutor.executeScript("arguments[0].setAttribute('value', '091808181988')", driver.findElement(By.xpath("//input[@name='telephoneno']")));
		jsExecutor.executeScript("arguments[0].setAttribute('value', 'kjyheartcyn@gmail.com')", driver.findElement(By.xpath("//input[@name='emailid']")));
		jsExecutor.executeScript("arguments[0].setAttribute('value', '18081988')", driver.findElement(By.xpath("//input[@name='password']")));
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@value='Submit']")));
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Customer Registered Successfully!!!']")).isDisplayed());
		
	}
	
	@Test
	public void TC_05_() {
		driver.get("http://live.techpanda.org/");
		
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account']")));
		sleepInSecond(2);
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Create an Account']")));
		sleepInSecond(1);
		
		jsExecutor.executeScript("arguments[0].setAttribute('value', 'yong')", driver.findElement(By.xpath("//input[@id='firstname']")));
		jsExecutor.executeScript("arguments[0].setAttribute('value', 'ji')", driver.findElement(By.xpath("//input[@id='middlename']")));
		jsExecutor.executeScript("arguments[0].setAttribute('value', 'kwon')", driver.findElement(By.xpath("//input[@id='lastname']")));
		jsExecutor.executeScript("arguments[0].setAttribute('value', 'Itaewon, Seoul')", driver.findElement(By.xpath("//input[@id='email_address']")));
		jsExecutor.executeScript("arguments[0].setAttribute('value', '180888')", driver.findElement(By.xpath("//input[@id='password']")));
		jsExecutor.executeScript("arguments[0].setAttribute('value', '180888')", driver.findElement(By.xpath("//input[@id='confirmation']")));
		
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@title='Register']")));
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']")).getText(), "Thank you for registering with Main Website Store.");
		
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.xpath("//a[@title='Log Out']")));
		
		By homeTitle = By.xpath("//h2[contains(text(),'This is demo site for')]");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(homeTitle));
		Assert.assertTrue(driver.findElement(homeTitle).isDisplayed());
		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public String getRandomEmail() {
		Random rNumber = new Random();
		String rEmail = "kjy" + rNumber.nextInt(99999) + "@gmail.com";
		return rEmail;
	}
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}