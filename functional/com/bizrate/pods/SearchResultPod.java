package com.bizrate.pods;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.bizrate.common.Browser;
import com.bizrate.common.Constants;

public class SearchResultPod {

	List<WebElement> strongTitles;
	Browser browser;

	public SearchResultPod() {
		browser = new Browser();
	}

	/*
	 * For Demo
	 */
	public List<WebElement> getBoldKeywordInTitles(WebDriver driver) {
		return browser.getListOfWebElementsByXPath(driver,
				Constants.SRP_XP_STRONG_KEYWORD_TITLE);
	}
}
