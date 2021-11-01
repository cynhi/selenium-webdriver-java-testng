package webdriver;
import java.util.Random;
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

public class Topic_07_Textbox_TextArea {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String homePage;
	String email, userID, userPassword;
	String cusName, cusGender, cusDOB, cusInputAddress, cusOutputAddress, cusCity, cusState, cusPIN, cusMobileNo, cusID;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		homePage = "https://demo.guru99.com/v4/";
		email = getRandomEmail();
		cusName = "Kwon G";
		cusGender = "male";
		cusDOB = "1988-08-18"; 
		cusInputAddress = "123 Los Angeles\n US";
		cusOutputAddress = "123 Los Angeles US";
		cusCity = "New York";
		cusState= "California";
		cusPIN= "100100";
		cusMobileNo = "0987654321";
		
	}

	@Test
	public void TC_01_Register() {
		driver.get(homePage);
		
		driver.findElement(By.xpath("//a[text() = 'here']")).click();
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("btnLogin")).click();

		userID = driver.findElement(By.xpath("//td[text() = 'User ID :']/following-sibling::td")).getText();
		userPassword = driver.findElement(By.xpath("//td[text() = 'Password :']/following-sibling::td")).getText();

	}

	@Test
	public void TC_02_Login() {
		driver.get(homePage);
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(userPassword);
		driver.findElement(By.name("btnLogin")).click();
		Assert.assertEquals(driver.getTitle(), "Guru99 Bank Manager HomePage");
		Assert.assertEquals(driver.findElement(By.cssSelector("marquee.heading3")).getText(),
				"Welcome To Manager's Page of Guru99 Bank");
		Assert.assertEquals(driver.findElement(By.cssSelector("tr.heading3>td")).getText(), "Manger Id : " + userID);
	}

	@Test
	public void TC_03_Register_New_Customer() {
		driver.findElement(By.xpath("//a[text()= 'New Customer']")).click();
		driver.findElement(By.name("name")).sendKeys(cusName);
		if (cusGender.equals("female")) {
			driver.findElement(By.xpath("//*[@name = 'rad1' and @value = 'f']")).click();
		};
		WebElement dobTextbox = driver.findElement(By.id("dob"));
		
		jsExecutor.executeScript("arguments[0].removeAttribute('type')", dobTextbox);
		sleepInSecond(3);
		dobTextbox.sendKeys(cusDOB);
		
		driver.findElement(By.name("addr")).sendKeys(cusInputAddress);
		driver.findElement(By.name("city")).sendKeys(cusCity);
		driver.findElement(By.name("state")).sendKeys(cusState);
		driver.findElement(By.name("pinno")).sendKeys(cusPIN);
		driver.findElement(By.name("telephoneno")).sendKeys(cusMobileNo);
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("password")).sendKeys(userPassword);
		driver.findElement(By.xpath("//input[@value = 'Submit']")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p.heading3")).getText(), "Customer Registered Successfully!!!");
		cusID = driver.findElement(By.xpath("//td[text() = 'Customer ID']/following-sibling::td")).getText();
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'Customer Name']/following-sibling::td")).getText(), cusName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'Gender']/following-sibling::td")).getText(), cusGender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'Birthdate']/following-sibling::td")).getText(), cusDOB);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'Address']/following-sibling::td")).getText(), cusOutputAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'City']/following-sibling::td")).getText(), cusCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'State']/following-sibling::td")).getText(), cusState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'Pin']/following-sibling::td")).getText(), cusPIN);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'Mobile No.']/following-sibling::td")).getText(), cusMobileNo);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'Email']/following-sibling::td")).getText(), email );
	}
	
	@Test
	public void TC_04_Edit_Customer_Information() {
		driver.findElement(By.xpath("//a[text() = 'Edit Customer']")).click();
		driver.findElement(By.name("cusid")).sendKeys(cusID);
		driver.findElement(By.name("AccSubmit")).click();
		Assert.assertEquals(driver.findElement(By.name("name")).getAttribute("value"), cusName);
		Assert.assertEquals(driver.findElement(By.name("addr")).getText(), cusInputAddress);
		
		cusOutputAddress = "123 Mapo-gu";
		cusCity = "Seoul";
		cusState= "Korea";
		cusPIN= "180888";
		cusMobileNo = "0918180888";
		email = getRandomEmail();
		
		WebElement addr = driver.findElement(By.name("addr"));
		addr.clear();
		addr.sendKeys(cusOutputAddress);
		
		WebElement city = driver.findElement(By.name("city"));
		city.clear();
		city.sendKeys(cusCity);
		
		
		WebElement state = driver.findElement(By.name("state"));
		state.clear();
		state.sendKeys(cusState);
		
		WebElement pinno = driver.findElement(By.name("pinno"));
		pinno.clear();
		pinno.sendKeys(cusPIN);
		
		WebElement telephoneno = driver.findElement(By.name("telephoneno"));
		telephoneno.clear();
		telephoneno.sendKeys(cusMobileNo);
		
		WebElement emailid = driver.findElement(By.name("emailid"));
		emailid.clear();
		emailid.sendKeys(email);
		
		driver.findElement(By.name("sub")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("p.heading3")).getText(), "Customer details updated Successfully!!!");
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), cusOutputAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'City']/following-sibling::td")).getText(), cusCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'State']/following-sibling::td")).getText(), cusState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'Pin']/following-sibling::td")).getText(), cusPIN);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'Mobile No.']/following-sibling::td")).getText(), cusMobileNo);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'Email']/following-sibling::td")).getText(), email );
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
		String rEmail = "kjy" + rNumber.nextInt(99999) + "@gmail.com";
		return rEmail;
	}

}