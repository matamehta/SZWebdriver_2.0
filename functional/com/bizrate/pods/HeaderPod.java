package com.bizrate.pods;

import org.openqa.selenium.WebDriver;

import com.bizrate.common.Browser;
import com.bizrate.common.Constants;

public class HeaderPod {

	static Browser browser;

	public HeaderPod() {
		browser = new Browser();
	}

	public static void searchForKeyword(WebDriver driver, String keyword) {
		browser.writeInTextBoxByID(driver, Constants.HEADER_ID_SEARCH_BOX,
				keyword);
		browser.clickByName(driver, Constants.HEADER_NAME_SEARCH_BUTTON);
	}

}
