package com.bizrate.pods;

import org.openqa.selenium.WebDriver;

import com.bizrate.common.Browser;
import com.bizrate.common.Constants;

public class AttributePod {

	Browser browser;

	public AttributePod() {
		browser = new Browser();
	}

	public void setPriceRange(WebDriver driver, String min, String max) {
		browser.writeInTextBoxByID(driver, Constants.SRP_ID_MIN_PRICE, min);
		browser.writeInTextBoxByName(driver, Constants.SRP_NAME_MAX_PRICE, max);
		browser.clickByID(driver, Constants.SRP_ID_PRICE_FILTER_BUTTON);
	}

}
