package com.bizrate.pods;

import org.openqa.selenium.WebDriver;
import org.testng.internal.Utils;

import com.bizrate.common.Browser;
import com.bizrate.common.Constants;

public class DebugHeaderPod {

	Browser browser;

	public DebugHeaderPod() {
		browser = new Browser();
	}

	public void setSessionID(WebDriver driver, String session) {
		try {
			browser.waitImplicitly(driver, 10);
			browser.writeInTextBoxByName(driver,
					Constants.DHEADER_NAME_SESSIONID, session);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// This method will click on "Set Param" button
	public void clickSetParamsButton(WebDriver driver) {
		try {
			browser.clickByXPath(driver, Constants.DHEADER_XP_SET_PARAM_BUTTON);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * This method assume error is present on all pages. If green button is
	 * visible then boolean will set to false
	 */
	public boolean isErrorPresent(WebDriver driver) {
		boolean error = true;
		String errorButton = browser.getAttributeByID(driver,
				Constants.DHEADER_ID_ERROR_DOT, "src");
		// String
		// errorButton=driver.findElement(By.id("requestErrorIndicator")).getAttribute("src");

		if (errorButton.endsWith("solid_green_circle.gif")) {
			error = false;
		} else if (errorButton.endsWith("blinking_red_circle.gif")) {
			error = true;
		} else {
			Utils.log("Test_DebugHeader.class", 1,
					"UNABLE TO DETECT ERROR STATUS");
		}
		return error;
	}

	public boolean isDebugHeaderOpen(WebDriver driver) {
		boolean debugPresent = false;
		// int size=driver.findElements(By.id("debug_header")).size();
		int size = browser
				.getSizeByID(driver, Constants.DHEADER_ID_DEBUGHEADER);

		if (size > 0) {
			debugPresent = true;
		}
		return debugPresent;
	}

	public String getPageToken(WebDriver driver) {
		if (isDebugHeaderOpen(driver)) {
			// return driver.findElement(By.id("pageTokenValue")).getText();
			return browser.getTextByID(driver, Constants.DHEADER_ID_PAGE_TOKEN);
		}
		return "ERR";
	}

}
