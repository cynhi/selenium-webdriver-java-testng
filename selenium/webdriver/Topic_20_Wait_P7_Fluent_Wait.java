package webdriver;

import java.time.Duration;
import java.util.Date;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_20_Wait_P7_Fluent_Wait {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	FluentWait <WebDriver> fluentWaitWebDriver;
	FluentWait <WebElement>  fluentWaitWebElement;
	FluentWait <WebDriver> fluentWaitTest;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}
	
	public void TC_Sample_Fulent_WebDriver() {
		//Find Element with total wait time is 15s, polling is 1s
		fluentWaitWebDriver = new FluentWait<WebDriver> (driver);
		
		fluentWaitWebDriver.withTimeout(Duration.ofSeconds(15))
						   .pollingEvery(Duration.ofSeconds(1))
						   .ignoring(NoSuchElementException.class)
						   .until(new Function<WebDriver, WebElement>(){
							   public WebElement apply(WebDriver driver) {
								   return driver.findElement(By.xpath("..."));
							   }
						   });
	}
	
	public void TC_Sample_Fulent_WebElement() {
		WebElement loginButton = driver.findElement(By.xpath(""));
		
		fluentWaitWebElement = new FluentWait<WebElement> (loginButton);
		
		//Set time
		fluentWaitWebElement.withTimeout(Duration.ofSeconds(30))
							.pollingEvery(Duration.ofMillis(200))
						    .ignoring(ElementNotVisibleException.class);
		
		//Apply condition and returns text of element to verify later
		String loginButtonText = fluentWaitWebElement.until(new Function <WebElement, String>() {
			public String apply(WebElement element) {
				return element.getText();
			}
		});
		Assert.assertEquals(loginButtonText, "...");
	}
	
	@Test
	public void TC_01_Ex9() {
		//As Hello World element is not shown yet, FluentWait<WebElement> would go failed when find, we should use FluentWait<WebDriver> 
		driver.get("https://automationfc.github.io/dynamic-loading/");
		fluentWaitTest = new FluentWait<WebDriver> (driver);
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		
		//Find Element with total wait time is 6s, polling is 1s
		fluentWaitTest.withTimeout(Duration.ofSeconds(6))
					.pollingEvery(Duration.ofSeconds(1))
					.ignoring(NoSuchElementException.class)
					.until(new Function <WebDriver, Boolean>() {
//			@Override
			public Boolean apply(WebDriver driver) {
				boolean status = driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed();
				return status;
			}
//					.until(new Function <WebDriver, String>() {
//			public String apply(WebDriver driver) {
//				String text = driver.findElement(By.xpath("//div[@id='finish']/h4")).getText();
//				return text;
//			}						
			
		});
		
	}
	
	@Test
	//As this TC waits for an existing element to change its status => use FluentWait <WebElement>
	public void TC_02_Ex8() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		WebElement countdown = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
		//Condition
		fluentWaitWebElement = new FluentWait<WebElement>(countdown);
		
		fluentWaitWebElement.withTimeout(Duration.ofSeconds(30))
							.pollingEvery(Duration.ofSeconds(1))
							.ignoring(NoSuchElementException.class)
							.until(new Function<WebElement, Boolean>(){

								@Override
								public Boolean apply(WebElement element) {
									boolean result = element.getText().endsWith("00");
									return result;
								}
							});
	}
	
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	//Custom functions
	private long sumTime = 30;
	private long pollingTime = 1;
	
	public WebElement findElement(By locator) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver> (driver).withTimeout(Duration.ofSeconds(sumTime))
																	   .pollingEvery(Duration.ofSeconds(pollingTime))
																	   .ignoring(NoSuchElementException.class);
		WebElement element = wait.until(new Function<WebDriver, WebElement>(){
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		return element;
	}
	
	public void clickToElement(By locator) {
		WebElement element = findElement(locator);
		element.click();
	}
	
	public WebElement waitElementVisible(By locator) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(sumTime))
																	  .pollingEvery(Duration.ofSeconds(pollingTime))
																	  .ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public boolean isElementDisplayed(By locator) {
		WebElement element = waitElementVisible(locator);
		FluentWait<WebElement> wait = new FluentWait<WebElement>(element).withTimeout(Duration.ofSeconds(sumTime))
																		.pollingEvery(Duration.ofSeconds(pollingTime))
																		.ignoring(NoSuchElementException.class);
		boolean isDisplayed = wait.until(new Function<WebElement, Boolean>(){
			public Boolean apply(WebElement element) {
				boolean flag = element.isDisplayed();
				return flag;
			}
		});
		return isDisplayed;		
	}
	
	
}