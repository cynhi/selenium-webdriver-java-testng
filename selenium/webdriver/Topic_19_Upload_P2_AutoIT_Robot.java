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

//For Java robot
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;

public class Topic_19_Upload_P2_AutoIT_Robot {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String img1 = "IMG_9831.jpg";
	String img2 = "IMG_9853.jpg";
	
	//separator "/" for MAC or "\\" for windows
	String separatorChar = File.separator;
	String uploadFolderLocation = projectPath + separatorChar + "uploadFiles" + separatorChar;
	
	String img1Location = uploadFolderLocation + img1;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	//AutoIT only supports Windows

	@Test
	public void TC_01_Upload_File_By_File() throws AWTException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		//Copy path to file into clipboard
		StringSelection select = new StringSelection(img1);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
		
		//Click to open "Open file" dialog
		driver.findElement(By.cssSelector("span.fileinput-button")).click();
		
		//Load file
		Robot robot = new Robot();
		sleepInSecond(1);
		
		//Load file: Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	
		//Load file: Ctrl V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		sleepInSecond(1);
		
		robot.keyPress(KeyEvent.VK_SPACE);
		robot.keyRelease(KeyEvent.VK_SPACE);
		
		//Load file: Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		
		//Upload file
		List<WebElement> startButtons = driver.findElements(By.xpath("//span[text()='Start']"));
		for (WebElement start : startButtons) {
			start.click();
			sleepInSecond(1);
		}
		
		//Verify uploaded successfully
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + img1 + "']")).isDisplayed());
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