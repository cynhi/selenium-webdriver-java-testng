package testNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import listener.ExtentReport;


@Listeners(ExtentReport.class)

public class Topic_08_Dependencies {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
//		driver = new FirefoxDriver();
//		
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_() {
		System.out.println("Start TC 1");
		Assert.assertTrue(false);
	}

	@Test (dependsOnMethods = "TC_01_")
	public void TC_02_() {
	}

	@Test (dependsOnMethods = {"TC_01_", "TC_02_"})
	public void TC_03_() {
	}
	
// => If 1 goes failed, depended TCs will be skipped
	
	
	
	@AfterClass
	public void afterClass() {
//		driver.quit();
	}
	

}