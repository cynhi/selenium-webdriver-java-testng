package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Dropdown_Custom_I {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor je = (JavascriptExecutor) driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		
		explicitWait = new WebDriverWait(driver, 40);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_JQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		sleepInSecond(2);
		selectCustomDropdownItem("//span[@id ='number-button']", "//ul[@id='number-menu']/li/div", "5");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "5");
		sleepInSecond(2);
		
		selectCustomDropdownItem("//span[@id ='number-button']", "//ul[@id='number-menu']/li/div", "19");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "19");

	}

	@Test
	public void TC_02_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		sleepInSecond(2);
		selectCustomDropdownItem("//i[@class='dropdown icon']", "//div[contains(@class,'item')]/span[@class='text']", "Christian");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Christian");
		
		sleepInSecond(1);
		selectCustomDropdownItem("//i[@class='dropdown icon']", "//div[contains(@class, 'item')]/span[@class='text']", "Matt");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Matt");
		
		sleepInSecond(1);
		selectCustomDropdownItem("//i[@class='dropdown icon']", "//div[contains(@class, 'item')]/span[@class='text']", "Elliot Fu");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Elliot Fu");
		
		sleepInSecond(1);
		selectCustomDropdownItem("//i[@class='dropdown icon']", "//div[contains(@class, 'item')]/span[@class='text']", "Christian");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Christian");
	}

	@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		sleepInSecond(2);
		selectCustomDropdownItem("//li[@class ='dropdown-toggle']", "//ul[@class ='dropdown-menu']/li/a", "Third Option");
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class ='dropdown-toggle']")).getText(), "Third Option");
		
		sleepInSecond(1);
		selectCustomDropdownItem("//li[@class ='dropdown-toggle']", "//ul[@class ='dropdown-menu']/li/a", "First Option");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class ='dropdown-toggle']")).getText(), "First Option");
		
		sleepInSecond(2);
		selectCustomDropdownItem("//li[@class ='dropdown-toggle']", "//ul[@class ='dropdown-menu']/li/a", "Second Option");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class ='dropdown-toggle']")).getText(), "Second Option");
		
		sleepInSecond(2);
		selectCustomDropdownItem("//li[@class ='dropdown-toggle']", "//ul[@class ='dropdown-menu']/li/a", "Third Option");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class ='dropdown-toggle']")).getText(), "Third Option");
	}
	
	@Test
	public void TC_04_Angular_1() {
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		sleepInSecond(2);
		selectCustomDropdownItem("//span[@aria-owns='games_options']", "//ul[@id='games_options']/li", "Golf");
		Assert.assertEquals(jsExecutor.executeScript("return document.querySelector('#game input').value;"), "Golf");
		sleepInSecond(1);
		selectCustomDropdownItem("//span[@aria-owns='games_options']", "//ul[@id='games_options']/li", "Football");
		Assert.assertEquals(jsExecutor.executeScript("return document.querySelector('#game input').value;"), "Football");
		sleepInSecond(1);
		selectCustomDropdownItem("//span[@aria-owns='games_options']", "//ul[@id='games_options']/li", "Basketball");
		Assert.assertEquals(jsExecutor.executeScript("return document.querySelector('#game input').value;"), "Basketball");
	}
	
	@Test
	public void TC_05_Angular_2() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(2);
		selectCustomDropdownItem("//ng-select[@formcontrolname='ordinalOfInjection']", "//ng-select[@formcontrolname='ordinalOfInjection']//div[@role='option']/span", "Mũi tiêm thứ nhất");
		Assert.assertEquals(jsExecutor.executeScript("return document.querySelector(\"ng-select[formcontrolname='ordinalOfInjection']\").innerText").toString().replace("×", "").trim(), "Mũi tiêm thứ nhất");

		selectCustomDropdownItem("//ng-select[@formcontrolname='gender']", "//ng-select[@formcontrolname='gender']//div[@role='option']/span", "Nam");
		Assert.assertEquals(jsExecutor.executeScript("return document.querySelector(\"ng-select[formcontrolname='gender']\").innerText").toString().replace("×", "").trim(), "Nam");
		
		selectCustomDropdownItem("//ng-select[@bindvalue='provinceCode']", "//span[contains(@class,'ng-option-label')]", "Thành phố Cần Thơ");
		Assert.assertEquals(jsExecutor.executeScript("return document.querySelector(\"ng-select[bindvalue = 'provinceCode']\").innerText;").toString().replace("×", "").trim(), "Thành phố Cần Thơ");
		
		selectCustomDropdownItem("//ng-select[@bindvalue='districtCode']", "//span[contains(@class,'ng-option-label')]", "Quận Ninh Kiều");
		Assert.assertEquals(jsExecutor.executeScript("return document.querySelector(\"ng-select[bindvalue = 'districtCode']\").innerText;").toString().replace("×", "").trim(), "Quận Ninh Kiều");
		
		selectCustomDropdownItem("//ng-select[@bindvalue='wardCode']", "//span[contains(@class,'ng-option-label')]", "Phường Xuân Khánh");
		Assert.assertEquals(jsExecutor.executeScript("return document.querySelector(\"ng-select[bindvalue = 'wardCode']\").innerText;").toString().replace("×", "").trim(), "Phường Xuân Khánh");
		
		selectCustomDropdownItem("//ng-select[@bindvalue='provinceCode']", "//span[contains(@class,'ng-option-label')]", "Tỉnh Hà Giang");
		Assert.assertEquals(jsExecutor.executeScript("return document.querySelector(\"ng-select[bindvalue = 'provinceCode']\").innerText;").toString().replace("×", "").trim(), "Tỉnh Hà Giang");
		
		selectCustomDropdownItem("//ng-select[@bindvalue='districtCode']", "//span[contains(@class,'ng-option-label')]", "Huyện Hoàng Su Phì");
		Assert.assertEquals(jsExecutor.executeScript("return document.querySelector(\"ng-select[bindvalue = 'districtCode']\").innerText;").toString().replace("×", "").trim(), "Huyện Hoàng Su Phì");
		
		selectCustomDropdownItem("//ng-select[@bindvalue='wardCode']", "//span[contains(@class,'ng-option-label')]", "Xã Nậm Khòa");
		Assert.assertEquals(jsExecutor.executeScript("return document.querySelector(\"ng-select[bindvalue = 'wardCode']\").innerText;").toString().replace("×", "").trim(), "Xã Nậm Khòa");
		
		selectCustomDropdownItem("//ng-select[@bindvalue='ethnicityCode']", "//span[contains(@class,'ng-option-label')]", "Hoa");
		Assert.assertEquals(jsExecutor.executeScript("return document.querySelector(\"ng-select[bindvalue = 'ethnicityCode']\").innerText;").toString().replace("×", "").trim(), "Hoa");
		
		selectCustomDropdownItem("//ng-select[@bindvalue='nationCode']", "//span[contains(@class,'ng-option-label')]", "Japan");
		Assert.assertEquals(jsExecutor.executeScript("return document.querySelector(\"ng-select[bindvalue = 'nationCode']\").innerText;").toString().replace("×", "").trim(), "Japan");
		
		selectCustomDropdownItem("//ng-select[@bindvalue='ethnicityCode']", "//span[contains(@class, 'ng-option-label ng-star-inserted')]", "Kinh");
		Assert.assertEquals(jsExecutor.executeScript("return document.querySelector(\"ng-select[bindvalue='ethnicityCode']\").innerText").toString().replace("×", "").trim(), "Kinh");
		
		selectCustomDropdownItem("//ng-select[@bindvalue='nationCode']", "//span[contains(@class,'ng-option-label ng-star-inserted')]", "Bhutan");
		Assert.assertEquals(jsExecutor.executeScript("return document.querySelector(\"ng-select[bindvalue='nationCode']\").innerText").toString().replace("×", "").trim(), "Bhutan");
		
		selectCustomDropdownItem("//ng-select[@formcontrolname='objectId']", "//div[@role='option']/div", "3. Lực lượng Quân đội");
		Assert.assertEquals(jsExecutor.executeScript("return document.querySelector(\"ng-select[formcontrolname='objectId']\").innerText").toString().replace("×", "").trim(), "3. Lực lượng Quân đội");
	}
	
	@Test
	public void TC_06_Editable_1() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(2);
		selectEditableDropdownItem("//ng-select[@bindvalue='provinceCode']//input", "//span[contains(@class,'ng-option-label')]", "Tỉnh Nam Định");
		Assert.assertEquals(jsExecutor.executeScript("return document.querySelector(\"ng-select[bindvalue = 'provinceCode']\").innerText;").toString().replace("×", "").trim(), "Tỉnh Nam Định");
		sleepInSecond(1);
		selectEditableDropdownItem("//ng-select[@bindvalue='districtCode']//input", "//span[contains(@class,'ng-option-label')]", "Huyện Ý Yên");
		Assert.assertEquals(jsExecutor.executeScript("return document.querySelector(\"ng-select[bindvalue = 'districtCode']\").innerText;").toString().replace("×", "").trim(), "Huyện Ý Yên");
		sleepInSecond(1);
		selectEditableDropdownItem("//ng-select[@bindvalue='wardCode']//input", "//span[contains(@class,'ng-option-label')]", "Xã Yên Thọ");
		Assert.assertEquals(jsExecutor.executeScript("return document.querySelector(\"ng-select[bindvalue = 'wardCode']\").innerText;").toString().replace("×", "").trim(), "Xã Yên Thọ");
	}
	
	@Test
	public void TC_07_Editable_2() {
		driver.get("http://indrimuska.github.io/jquery-editable-select/");
		sleepInSecond(2);
		
		selectEditableDropdownItem("//div[@id='basic-place']/input", "//div[@id='basic-place']//li", "Jaguar");
		Assert.assertEquals(jsExecutor.executeScript("return document.querySelector(\"div[id='basic-place'] input\").value;"), "Jaguar");
		
		selectEditableDropdownItem("//div[@id='default-place']/input", "//div[@id='default-place']//li", "Peugeot");
		Assert.assertEquals(jsExecutor.executeScript("return document.querySelector(\"div[id='default-place'] input\").value;"), "Peugeot");
		
		selectEditableDropdownItem("//div[@id='fade-place']/input", "//div[@id='fade-place']//li", "Fiat");
		Assert.assertEquals(jsExecutor.executeScript("return document.querySelector(\"div[id='fade-place'] input\").value;"), "Fiat");
		
		selectEditableDropdownItem("//div[@id='basic-place']/input", "//div[@id='basic-place']//li", "Porsche");
		Assert.assertEquals(jsExecutor.executeScript("return document.querySelector(\"div[id='basic-place'] input\").value;"), "Porsche");
		
		selectEditableDropdownItem("//div[@id='default-place']/input", "//div[@id='default-place']//li", "BMW");
		Assert.assertEquals(jsExecutor.executeScript("return document.querySelector(\"div[id='default-place'] input\").value;"), "BMW");
		
	}
	
	@Test
	public void TC_07_Editable_3() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		sleepInSecond(2);
		
		selectEditableDropdownItem("//input[@class='search']", "//div[@role='listbox']//span", "Benin");
		Assert.assertEquals(jsExecutor.executeScript("return document.querySelector(\"div[class='active selected item'] span\").textContent"), "Benin");
		
		selectEditableDropdownItem("//input[@class='search']", "//div[@role='listbox']//span", "Aruba");
		Assert.assertEquals(jsExecutor.executeScript("return document.querySelector(\"div[class='active selected item'] span\").textContent"), "Aruba");
		
		selectEditableDropdownItem("//input[@class='search']", "//div[@role='listbox']//span", "Afghanistan");
		Assert.assertEquals(jsExecutor.executeScript("return document.querySelector(\"div[class='active selected item'] span\").textContent"), "Afghanistan");
		
		selectEditableDropdownItem("//input[@class='search']", "//div[@role='listbox']//span", "Barbados");
		Assert.assertEquals(jsExecutor.executeScript("return document.querySelector(\"div[class='active selected item'] span\").textContent"), "Barbados");
		
		selectEditableDropdownItem("//input[@class='search']", "//div[@role='listbox']//span", "Bahamas");
		Assert.assertEquals(jsExecutor.executeScript("return document.querySelector(\"div[class='active selected item'] span\").textContent"), "Bahamas");
		
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
	
	public void selectCustomDropdownItem(String parentElement, String childElement, String itemText) {
		driver.findElement(By.xpath(parentElement)).click();
		sleepInSecond(2);
		
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childElement)));
		List<WebElement> childItems = driver.findElements(By.xpath(childElement));
		
		for (WebElement item : childItems) {
			if (item.getText().equals(itemText)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(false);", item);
				sleepInSecond(2);
				item.click();
				sleepInSecond(2);
				break;
			}
		}
		
	}
	
	public void selectEditableDropdownItem(String parentElement, String childElement, String itemText) {
		WebElement parent = driver.findElement(By.xpath(parentElement));
		parent.clear();
		parent.sendKeys(itemText);
		sleepInSecond(1);
		
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childElement)));
		List<WebElement> childItems = driver.findElements(By.xpath(childElement));
		
		for (WebElement item : childItems) {
			if (item.getText().equals(itemText)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(false);", item);
				sleepInSecond(2);
				item.click();
				sleepInSecond(2);
				break;
			}
		}
		
	}

}