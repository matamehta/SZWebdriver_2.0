package com.bizrate.common;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Browser {

	WebDriver driver;
	String server;

	// open page
	public void openPage(WebDriver driver, String url) {
		driver.get(url);
	}

	// Confirm Element
	public boolean isElementPresentByID(WebDriver driver, String locator) {
		return driver.findElements(By.id(locator)).size() != 0;
	}

	public boolean isElementPresentByXPath(WebDriver driver, String locator) {
		return driver.findElements(By.xpath(locator)).size() != 0;
	}

	public boolean isElementPresentByName(WebDriver driver, String locator) {
		return driver.findElements(By.name(locator)).size() != 0;
	}

	public boolean isElementPresentByCSS(WebDriver driver, String locator) {
		return driver.findElements(By.className(locator)).size() != 0;
	}

	// visibility
	public boolean isElementDisplayedByXPath(WebDriver driver, String locator) {
		return driver.findElement(By.xpath(locator)).isDisplayed();
	}

	public boolean isElementEnabledByXPath(WebDriver driver, String locator) {
		return driver.findElement(By.xpath(locator)).isEnabled();
	}

	// Click Methods
	public void clickByXPath(WebDriver driver, String locator) {
		driver.findElement(By.xpath(locator)).click();
		waitForResults(1000);
	}

	public void clickByClass(WebDriver driver, String locator) {
		driver.findElement(By.className(locator)).click();
	}

	public void clickByID(WebDriver driver, String locator) {
		driver.findElement(By.id(locator)).click();
	}

	public void clickByName(WebDriver driver, String locator) {
		driver.findElement(By.name(locator)).click();
	}

	// Get Methods
	public void getHomePage(WebDriver driver) {
		driver.get(server);
		waitForResults(2000);
	}

	public String getCurrentURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
		waitForResults(1000);
	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public String getTextByClassName(WebDriver driver, String locator) {
		try {
			return driver.findElement(By.className(locator)).getText();
		} catch (Exception e) {
			return "not found";
		}
	}

	public String getTextByID(WebDriver driver, String locator) {
		try {
			return driver.findElement(By.id(locator)).getText();
		} catch (Exception e) {
			return "not found";
		}
	}

	public String getTextByXPath(WebDriver driver, String locator) {
		try {
			return driver.findElement(By.xpath(locator)).getText();
		} catch (Exception e) {
			return "not found";
		}
	}

	public String getAttributeByXPath(WebDriver driver, String locator,
			String attr) {
		String temp = "";
		try {
			temp = driver.findElement(By.xpath(locator)).getAttribute(attr);
			if (temp == null)
				temp = "not found";
		} catch (Exception e) {
			temp = "not found";
		}
		return temp;
	}

	public String getAttributeByID(WebDriver driver, String locator, String attr) {
		return driver.findElement(By.id(locator)).getAttribute(attr);
	}

	public List<WebElement> getListOfWebElementsByXPath(WebDriver driver,
			String locator) {
		List<WebElement> elements = driver.findElements(By.xpath(locator));
		return elements;
	}

	public String getDropdownSelectedValueByID(WebDriver driver, String locator) {
		Select options = new Select(driver.findElement(By.id(locator)));
		return options.getFirstSelectedOption().getText();
	}

	public String getTextByCSS(WebDriver driver, String locator) {
		return driver.findElement(By.cssSelector(locator)).getText();
	}

	public int getSizeByXpath(WebDriver driver, String locator) {
		return driver.findElements(By.xpath(locator)).size();
	}

	public int getSizeByID(WebDriver driver, String locator) {
		return driver.findElements(By.id(locator)).size();
	}

	public Point getLocationByXpath(WebDriver driver, String locator) {
		return driver.findElement(By.xpath(locator)).getLocation();
	}

	// Send Keys

	public void writeInTextBoxByXPath(WebDriver driver, String locator,
			String text) {
		driver.findElement(By.xpath(locator)).clear();
		driver.findElement(By.xpath(locator)).sendKeys(text);
	}

	public void writeInTextBoxByID(WebDriver driver, String locator, String text) {
		driver.findElement(By.id(locator)).clear();
		driver.findElement(By.id(locator)).sendKeys(text);
	}

	public void writeInTextBoxByName(WebDriver driver, String locator,
			String text) {
		driver.findElement(By.name(locator)).clear();
		driver.findElement(By.name(locator)).sendKeys(text);
		driver.findElement(By.name("sd"));

	}

	// waitTime in ms
	public void waitForResults(int waitTime) {
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void waitImplicitly(WebDriver driver, int waitTime) {
		driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
	}

	public void pressEnterKey(WebDriver driver) { // Wrong method need to check
													// history
		String winHandleBefore = driver.getWindowHandle();

		// Perform the click operation that opens new window

		// Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

		// Perform the actions on new window

		// Close the new window, if that window no more required
		waitForResults(1000);
		try {
			driver.findElement(By.id("alertify-ok")).sendKeys(Keys.RETURN);
		} catch (Exception e) {
			// In case id=alertify-ok fails
			driver.findElement(
					By.xpath("//button[@class='alertify-button alertify-button-ok']"))
					.sendKeys(Keys.RETURN);
		}
		// Switch back to original browser (first window)

		driver.switchTo().window(winHandleBefore);

	}

	// newly added methods from previous framework

	public void pressEnterKeyUsingId(WebDriver driver, String locator,
			String keyword) {
		driver.findElement(By.id(locator)).sendKeys(
				new CharSequence[] { keyword, Keys.RETURN });
	}

	public void cookieToBeDeleted(WebDriver driver, String cookie) {
		driver.manage().deleteCookieNamed(cookie);
	}

	public void cookie_DeleteAllCookies(WebDriver driver) {
		driver.manage().deleteAllCookies();
	}

	public String cookieGetCookieValue(WebDriver driver, String cookie) {
		return driver.manage().getCookieNamed(cookie).getValue();
	}

	public void cookiePrintAllCookies(WebDriver driver) {
		Set<Cookie> allCookies = driver.manage().getCookies();
		System.out.println("Cookies are as follows:");
		for (Object c : allCookies) {
			System.out.println(c);
		}
	}

	/*
	 * public void cookie_WaitForCookiesToGenerate(WebDriver driver, int time,
	 * String cookieToFind, String startWith) { int t = time; final String
	 * cookie = cookieToFind; final String startText = startWith;
	 * 
	 * new WebDriverWait(driver, t).until(new ExpectedCondition() { public
	 * Boolean apply(WebDriver driver) { return
	 * Boolean.valueOf(driver.manage().getCookieNamed(cookie)
	 * .getName().startsWith(startText)); } }); }
	 */

	public void getWindowHandlerUsingXPath(WebDriver driver, String locator) {
		String baseWindow = driver.getWindowHandle();
		clickByXPath(driver, locator);
		Set newWindowHandlers = driver.getWindowHandles();
		Iterator ite = newWindowHandlers.iterator();
		String newWindowUrl = "";
		while (ite.hasNext()) {
			String newWindow = ite.next().toString();
			if (!newWindow.contains(baseWindow)) {
				driver.switchTo().window(newWindow);
				newWindowUrl = driver.getCurrentUrl();
				System.out.println("New Window Url: " + newWindowUrl);
				driver.switchTo().window(baseWindow);
			}
		}
	}

	public void maximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}

}
