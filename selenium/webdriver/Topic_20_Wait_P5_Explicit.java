package webdriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Wait_P5_Explicit {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String separatorChar = File.separator;
	String uploadFolderLocation = projectPath + separatorChar + "uploadFiles" + separatorChar;
	
	
	By loadingIcon = By.cssSelector("div#loading");
	By hwText = By.xpath("//div[@id='finish']/h4");
	
	String img1 = "IMG_9853.jpg";
	String img2 = "IMG_9770.HEIC";
	String img3 = "IMG_9806.HEIC";
	String img4 = "IMG_9831.jpg";
	
	String img1Location = uploadFolderLocation + img1;
	String img2Location = uploadFolderLocation + img2;
	String img3Location = uploadFolderLocation + img3;
	String img4Location = uploadFolderLocation + img4;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		
		
		driver.manage().window().maximize();
	}
	//------------Wait for invisible of loading icon--------------//

	@Test
	public void TC_01_Enough_Wait() throws InterruptedException {
		explicitWait = new WebDriverWait(driver, 5);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		
		//Loading icon disappears after 5s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
	}

	@Test
	public void TC_02_Less_Wait() throws InterruptedException {
		explicitWait = new WebDriverWait(driver, 3);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		
		//Loading icon disappears after 3s
				explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
	}

	@Test
	public void TC_03_More_Wait() throws InterruptedException {
		explicitWait = new WebDriverWait(driver, 30);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		
		//Loading icon disappears after 30s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
	}
	
	//------------Wait for visible of text HELLO WORLD--------------//
	
	@Test
	public void TC_03_Enough_Wait() throws InterruptedException {
		explicitWait = new WebDriverWait(driver, 5);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		
		//Hello world appears after 5s
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(hwText));
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
	}
	
	// can use ^ OR v 
	
	@Test
	public void TC_04_Enough_Wait() throws InterruptedException {
		explicitWait = new WebDriverWait(driver, 5);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		
		//Hello world appears after 5s
		explicitWait.until(ExpectedConditions.numberOfElementsToBe(hwText, 1));
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
	}
	
	
	//------------Ajax loading------------//
	//Date picker
	
	@Test
	public void TC_05_Ajax() {
		//Wait for date picker appears
		explicitWait = new WebDriverWait(driver, 30);
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceholder1_Panel1")));
		
		// Element is found when not select any date yet
		WebElement selectedDateText = driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1"));
		Assert.assertEquals(selectedDateText.getText(), "No Selected Dates to display.");
		
		//Wait and click on a date
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='20']"))).click();
		
		//Wait for loading icon to disappear
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@id, 'RadCalendar1')]/div[@class='raDiv']")));
		
		//Verify updated selected date text
		//Find element selectedDateText again after selected date
		selectedDateText = driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1"));
		Assert.assertEquals(selectedDateText.getText(), "Sunday, February 20, 2022");
	
		
		WebElement selectedDateIcon = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']/a[text()='20']")));
		Assert.assertTrue(selectedDateIcon.isDisplayed());
		
	}
	
	//Updload file
	@Test
	public void TC_06_Upload() {
		explicitWait = new WebDriverWait(driver, 60);
		driver.get("https://gofile.io/uploadFiles");
		
		By uploadFileBy = By.xpath("//input[@type='file']");
		//Load file + start uploading
		driver.findElement(uploadFileBy).sendKeys(img1Location + "\n" + img2Location + "\n" + img3Location + "\n" + img4Location);
		 
		//Wait for loading progress disappear
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress"))));
		
		WebElement uploadedText = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Your files have been successfully uploaded']")));
		Assert.assertTrue(uploadedText.isDisplayed());
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	

}