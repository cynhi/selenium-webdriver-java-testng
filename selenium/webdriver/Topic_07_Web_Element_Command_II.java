package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Web_Element_Command_II {
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
	public void TC_01_Check_Displayed_Element() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='mail']"));
		Boolean emailDisplayed = emailTextbox.isDisplayed();
		if(emailDisplayed) {
			System.out.print("'Email' is displayed\n");
			emailTextbox.sendKeys("Automation Testing");
		} else {
			System.out.print("'Email' is not displayed\n");
		}
		
		
		WebElement under18RadioButton = driver.findElement(By.id("under_18"));
		Boolean under18Displayed = under18RadioButton.isDisplayed();
		if(under18Displayed) {
			System.out.print("'Under 18' radio button is displayed\n");
			under18RadioButton.click();
		} else {
			System.out.print("'Under 18' radio button is not displayed\n");
		}
		
	
		WebElement educationTextarea = driver.findElement(By.id("edu"));
		Boolean educationDisplayed = educationTextarea.isDisplayed();
		if(educationDisplayed) {
			System.out.print("'Education' textarea is displayed\n");
			educationTextarea.click();
		} else {
			System.out.print("'Education' textarea is not displayed\n");
		}
		
		
		WebElement user5Text = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
		Boolean user5Displayed = user5Text.isDisplayed();
		if(user5Displayed) {
			System.out.print("'User 5' text is displayed\n");
		} else {
			System.out.print("'User 5' text is not displayed\n");
		}
		
	}

	@Test
	public void TC_02_Check_Enabled_Element() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='mail']"));
		Boolean emailEnabled = emailTextbox.isEnabled();
		if(emailEnabled) {
			System.out.print("'Email' is enabled\n");
		} else {
			System.out.print("'Email' is not enabled\n");
		}
		
		
		WebElement under18RadioButton = driver.findElement(By.id("under_18"));
		Boolean under18Enabled = under18RadioButton.isEnabled();
		if(under18Enabled) {
			System.out.print("'Under 18' radio button is enabled\n");
		} else {
			System.out.print("'Under 18' radio button is not enabled\n");
		}
	
		WebElement educationTextarea = driver.findElement(By.id("edu"));
		Boolean educationEnabled = educationTextarea.isEnabled();
		if(educationEnabled) {
			System.out.print("'Education' textarea is enabled\n");
		} else {
			System.out.print("'Education' textarea is not enabled\n");
		}	
			
		
		WebElement job1DropdownList = driver.findElement(By.id("job1"));
		Boolean job1Enabled = job1DropdownList.isEnabled();
		if(job1Enabled) {
			System.out.print("'Job Role 01' textarea is enabled\n");
		} else {
			System.out.print("'Job Role 01' textarea is not enabled\n");
		}	
		
		
		WebElement job2DropdownList = driver.findElement(By.id("job2"));
		Boolean job2Enabled = job2DropdownList.isEnabled();
		if(job2Enabled) {
			System.out.print("'Job Role 02' textarea is enabled\n");
		} else {
			System.out.print("'Job Role 02' textarea is not enabled\n");
		}	
		
		
		WebElement developmentCheckbox = driver.findElement(By.id("development"));
		Boolean developmentEnabled = developmentCheckbox.isEnabled();
		if(developmentEnabled) {
			System.out.print("'Development' checkbox is enabled\n");
		} else {
			System.out.print("'Development' checkbox is not enabled\n");
		}

		WebElement slider01 = driver.findElement(By.id("slider-1"));
		Boolean slider01Enabled = slider01.isEnabled();
		if(slider01Enabled) {
			System.out.print("'Slider 01' is enabled\n");
		} else {
			System.out.print("'Slider 01' is not enabled\n");
		}
		
		
		
		WebElement passwordTextbox = driver.findElement(By.name("user_pass"));
		Boolean passwordEnabled = passwordTextbox.isEnabled();
		if(passwordEnabled) {
			System.out.print("'Password' textbox is enabled\n");
		} else {
			System.out.print("'Password' textbox is not enabled\n");
		}
		
		WebElement radioButton = driver.findElement(By.id("radio-disabled"));
		Boolean radioEnabled = radioButton.isEnabled();
		if(radioEnabled) {
			System.out.print("'Radio button is disabled' button is enabled\n");
		} else {
			System.out.print("'Radio button is disabled' button is not enabled\n");
		}
		

		WebElement biographyTextArea = driver.findElement(By.id("bio"));
		Boolean biographyEnabled = biographyTextArea.isEnabled();
		if(biographyEnabled) {
			System.out.print("'Biography' textarea is enabled\n");
		} else {
			System.out.print("'Biography' textarea is not enabled\n");
		}
		
		
		
		WebElement job3Label = driver.findElement(By.id("job3"));
		Boolean job3Enabled = job3Label.isEnabled();
		if(job3Enabled) {
			System.out.print("'Slider 01' is enabled\n");
		} else {
			System.out.print("'Slider 01' is not enabled\n");
		}
		
		
		WebElement disabledCheckbox = driver.findElement(By.id("check-disbaled"));
		Boolean disabledCheckbokEnabled = disabledCheckbox.isEnabled();
		if(disabledCheckbokEnabled) {
			System.out.print("'Job Role 03 - Disable Dropdown:' is enabled\n");
		} else {
			System.out.print("'Job Role 03 - Disable Dropdown:' is not enabled\n");
		}
		
		
		WebElement slider02 = driver.findElement(By.id("slider-2"));
		Boolean slider02Enabled = slider02.isEnabled();
		if(slider02Enabled) {
			System.out.print("'Slider 02' is enabled\n");
		} else {
			System.out.print("'Slider 02' is not enabled\n");
		}
		
	}

	@Test
	public void TC_03_Check_Selected_Element() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement under18RadioButton = driver.findElement(By.id("under_18"));
		under18RadioButton.click();
		Boolean under18Selected = under18RadioButton.isSelected();
		if(under18Selected) {
			System.out.print("'Under 18' radio button is selected\n");
		} else {
			System.out.print("'Under 18' radio button is not selected\\n");
		}
		
		WebElement javaCheckbox = driver.findElement(By.id("java"));
		javaCheckbox.click();
		Boolean javaSelected = javaCheckbox.isSelected();
		if(javaSelected) {
			System.out.print("'Java' checkbox is selected\n");
		} else {
			System.out.print("'Java' checkbox is not selected\\n");
		}
		
		javaCheckbox.click();
		javaSelected = javaCheckbox.isSelected();
		if(javaSelected) {
			System.out.print("'Java' checkbox is selected\n");
		} else {
			System.out.print("'Java' checkbox is not selected\n");
		}
		
	}
	
	@Test
	public void TC_04_Register_At_MailChimp() {
		driver.get("https://login.mailchimp.com/signup/");
		sleepInSecond(5);
		
		driver.findElement(By.id("email")).sendKeys("abc@gmail.com");
		driver.findElement(By.id("new_username")).sendKeys("abcdxyz");
		
		WebElement password = driver.findElement(By.id("new_password"));
		WebElement signupButton = driver.findElement(By.id("create-account"));
		password.sendKeys("auto");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed' and text() = 'One lowercase character']")).isDisplayed());
		
		password.clear();
		password.sendKeys("@#*$");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed' and text() = 'One special character']")).isDisplayed());
		Assert.assertFalse(signupButton.isEnabled());
		
		password.clear();
		password.sendKeys("KJY");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed' and text() = 'One uppercase character']")).isDisplayed());
		Assert.assertFalse(signupButton.isEnabled());
		
		password.clear();
		password.sendKeys("1888");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed' and text() = 'One number']")).isDisplayed());
		Assert.assertFalse(signupButton.isEnabled());
		
		password.clear();
		password.sendKeys("automation");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed' and text() = '8 characters minimum']")).isDisplayed());
		Assert.assertFalse(signupButton.isEnabled());
		
		password.clear();
		password.sendKeys("Aut0maTi@n");
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()=\"Your password is secure and you're all set!\"]")).isDisplayed());
		sleepInSecond(2);
		Assert.assertTrue(signupButton.isEnabled());
		
		
		WebElement checkbox = driver.findElement(By.id("marketing_newsletter"));
		Assert.assertFalse(checkbox.isSelected());
		checkbox.click();
		Assert.assertTrue(checkbox.isSelected());	
		
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