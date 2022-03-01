package webdriver;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Upload_P1_Sendkey {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String img1 = "IMG_9831.jpg";
	String img2 = "IMG_9853.jpg";
	
	//separator "/" for MAC or "\\" for windows
	String separatorChar = File.separator;
	String uploadFolderLocation = projectPath + separatorChar + "uploadFiles" + separatorChar;
	
	String img1Location = uploadFolderLocation + img1;
	String img2Location = uploadFolderLocation + img2;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Upload_File_By_File() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		//Load file
		By uploadFileBy = By.xpath("//input[@type='file']");
		driver.findElement(uploadFileBy).sendKeys(img1Location);
		sleepInSecond(1);
		driver.findElement(uploadFileBy).sendKeys(img2Location);
		sleepInSecond(1);
		
		
		//Upload
		//Click 'Start upload' button to upload all
//		driver.findElement(By.xpath("//button[@type='submit']//span[text()='Start upload']")).click();
		
		//Or click 'Start' button after every loaded file to upload file by file
		List<WebElement> startButtons = driver.findElements(By.xpath("//span[text()='Start']"));
		for (WebElement start : startButtons) {
			start.click();
			sleepInSecond(1);
		}
		
		//Verify uploaded successfully
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + img1 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + img2 + "']")).isDisplayed());
	
	}

	@Test
	public void TC_02_Upload_Multi_Files() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		//Load file
		By uploadFileBy = By.xpath("//input[@type='file']");
		driver.findElement(uploadFileBy).sendKeys(img1Location + "\n" + img2Location);
		sleepInSecond(1);
		
		//Upload
		//Click 'Start upload' button to upload all
		driver.findElement(By.xpath("//button[@type='submit']//span[text()='Start upload']")).click();
		
		//Verify uploaded successfully
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + img1 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + img2 + "']")).isDisplayed());
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