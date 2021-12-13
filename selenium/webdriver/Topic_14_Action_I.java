package webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Action_I {
	WebDriver driver;
	Actions action;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String dragDropHelper = projectPath + "/dragdropHTML5/jquery_load_helper.js";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Hover() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		sleepInSecond(3);
		action.moveToElement(driver.findElement(By.id("age"))).perform();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.className("ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
	}

	@Test
	public void TC_02_Hover_And_Click() {
		driver.get("http://www.myntra.com/");
		sleepInSecond(3);
		action.moveToElement(driver.findElement(By.xpath("//a[@data-group='kids']"))).perform();
		sleepInSecond(3);
		action.click(driver.findElement(By.xpath("//a[text()='Home & Bath']"))).perform();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='title-title']")).getText(), "Kids Home Bath");
		
	}

	@Test
	public void TC_03_Hove_And_Click() {
		driver.get("https://www.fahasa.com/");
		sleepInSecond(3);
		action.moveToElement(driver.findElement(By.xpath("//div[contains(@class, 'background-menu-homepage')]//a[@title= 'Làm Đẹp - Sức Khỏe']"))).perform();
		sleepInSecond(4);
		WebElement subItem = driver.findElement(By.xpath("//div[contains(@class, 'background-menu-homepage')]//a[text()='Sản Phẩm Làm Đẹp']"));
		Assert.assertTrue(subItem.isDisplayed());
		action.click(subItem).perform();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='mb-breadcrumbs']//strong")).getText(),"SẢN PHẨM LÀM ĐẸP");
	}
	
	@Test
	public void TC_04_Click_And_Hold_Blocks() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		sleepInSecond(3);
		
		List<WebElement> numbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		action.clickAndHold(numbers.get(0)).moveToElement(numbers.get(7)).release().perform();
		sleepInSecond(3);
		
		List<WebElement> clickedNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(clickedNumbers.size(), 8);
	}
	
	@Test
	public void TC_05_Click_And_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		sleepInSecond(3);
		
		List<WebElement> numbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		action.keyDown(Keys.COMMAND).perform();
		action.click(numbers.get(2)).click(numbers.get(4)).click(numbers.get(10)).click(numbers.get(7)).perform();
		action.keyUp(Keys.COMMAND).perform();
		List<WebElement> clickedNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(clickedNumbers.size(), 4);
		
	}
	
	@Test
	public void TC_06_Double_Click() {
		//Sometimes failed if the element is not in viewport => need to scroll to element
		driver.get("https://automationfc.github.io/basic-form/index.html");
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']")));
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.id("demo")).getText(), "Hello Automation Guys!");
		
	}
	
	@Test
	public void TC_07_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[text()='right click me']")));
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		//After right clicking, hover onto any item in sub menu => inspect its locator => verify its changed after hovering
	    action.moveToElement(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-copy')]"))).perform();
	    Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-copy context-menu-hover context-menu-visible')]")).isDisplayed());
	    action.click(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-copy')]"))).perform();
	    //After clicking, alert appears => verify alert
	    Assert.assertEquals(driver.switchTo().alert().getText(), "clicked: copy");
	    driver.switchTo().alert().accept();
	    sleepInSecond(3);
	    //After accepting alert, verify if sub-menu (specific item on it) is not displayed on UI (still on DOM)
	    Assert.assertFalse(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-copy')]")).isDisplayed());
	    
	}
	
	@Test
	public void TC_08_Drag_And_Drop_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		WebElement smallCircle = driver.findElement(By.id("draggable"));
		WebElement bigCircle = driver.findElement(By.id("droptarget"));
		action.dragAndDrop(smallCircle, bigCircle).perform();
		Assert.assertEquals(bigCircle.getText(), "You did great!");
		String rgbColor = bigCircle.getCssValue("background-color");
		String hexaColor = Color.fromString(rgbColor).asHex().toLowerCase();
		Assert.assertEquals(hexaColor, "#03a9f4");	
	}
	
	//Use JQuery lib => only works with Css locator
	//Unstable, not recommend for auto
	@Test
	public void TC_09_Drag_And_Drop_HTML5_Css() throws IOException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		sleepInSecond(3);
		String columnA = "#column-a";
		String columnB = "#column-b";
		String dragDropHelperContent = getContentFile(dragDropHelper);
		dragDropHelperContent = dragDropHelperContent + "$(\"" + columnA + "\").simulateDragDrop({ dropTarget: \"" + columnB + "\"});";
		//sDrag and drop A to B
		jsExecutor.executeScript(dragDropHelperContent);
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("#column-a>header")).getText(), "B");
		Assert.assertEquals(driver.findElement(By.cssSelector("#column-b>header")).getText(), "A");
	}
	
	//Unstable, not recommend for auto
	@Test
	public void TC_10_Drag_And_Drop_HTML5_Xpath() throws AWTException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		sleepInSecond(3);
		String columnA = "//div[@id='column-a']";
		String columnB = "//div[@id='column-b']";
		dragAndDropHTML5ByXpath(columnA, columnB);
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("#column-a>header")).getText(), "B");
		Assert.assertEquals(driver.findElement(By.cssSelector("#column-b>header")).getText(), "A");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	
	
	public String getContentFile(String filePath) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(filePath);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}
	
	public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}