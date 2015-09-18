package com.testsuite;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.internal.Utils;

import com.bizrate.common.Browser;
import com.bizrate.common.Constants;
import com.bizrate.common.Tokens;
import com.bizrate.pods.DebugHeaderPod;
import com.bizrate.pods.HeaderPod;
import com.bizrate.pods.SearchResultPod;
import com.common.SoftAssert;
import com.bizrate.common.Helper;

public class BizrateTestSuite {

	Helper helper;
	HeaderPod headerPod;
	SearchResultPod searchResultPod;
	DebugHeaderPod debugHeaderPod;
	Browser browser;
	Tokens tokens;
	SoftAssert softassert;
	WebDriver driver;
	Properties prop;

	@BeforeClass
	public void init() {
		helper = new Helper();
		searchResultPod = new SearchResultPod();
		debugHeaderPod = new DebugHeaderPod();
		browser = new Browser();
		tokens = new Tokens();

		helper.getProperties();
		driver = helper.getDriver();
		softassert = new SoftAssert();
	}

	@AfterClass
	public void tearDown() {
		// driver.close();
		driver.quit();
	}

	/*
	 * Test method using @Parameter
	 */
	@Test
	@Parameters({ "Brand", "Framework" })
	public void setup(String br, String fw) {
		System.out.println("Brand = " + br);
		System.out.println("Value = " + fw);
	}

	/*
	 * S11452 - [Bizrate] A/B Test Slim SRP Layout verifySlimLayoutForHotPages
	 * Slim layout has been scrapped from SEM - TC updated accordingly
	 */
	@Test
	public void verifyNoSlimLayoutForHotPagesSEM() {
		softassert.flush();
		Utils.log("TestClass.class", 1, "@Test verifySlimLayoutForHotPages()");
		String url = helper.server + helper.t7s + helper.debug + helper.rfsem;
		Utils.log("TestClass.class", 1, "Test running for url: " + url);
		// driver.get(url);
		browser.openPage(driver, url);
		// helper.reRunUrlIfErrorPresent(driver, url);
		softassert.assertTrue(
				browser.getSizeByXpath(driver, Constants.SRP_XP_MLT) != 0,
				"Slim layout is coming, it should not come for URL= " + url);
	}

	/*
	 * S11518 - [Bizrate] Fix Merchant Page Canonicalization Issue S11926 -
	 * [BR]Rewrite Merchant Page URLs DE11240 - Pagination on merchant reviews
	 * is broken
	 */
	@Test
	public void verifyMerchantPagesForC14N() {
		softassert.flush();
		Utils.log("TestClass.class", 1, "@Test verifyMerchantPagesForC14N()");
		String url = helper.server + helper.t6b_old2 + helper.debug
				+ helper.rfdefault;
		String url2 = helper.server + helper.t6b_old1 + helper.debug
				+ helper.rfdefault;
		Utils.log("TestClass.class", 1, "Test running for url: " + url);

		// driver.get(url);
		browser.openPage(driver, url);
		String href = browser.getAttributeByXPath(driver,
				Constants.MERCHANT_XP_C14N, "href");
		softassert.assertTrue(href.endsWith("/reviews/qvc/283/"),
				"Correct canonical URL is not present in 6B token for URL = "
						+ url);

		// driver.get(url2);
		browser.openPage(driver, url2);
		softassert.assertTrue(
				browser.getCurrentURL(driver).contains("/reviews/"),
				"New 6B Url format [/reviews/merchant/mid/] is not coming");
	}

	/*
	 * DE11240 - Pagination on merchant reviews is broken
	 */
	@Test
	public void verifyMerchantPaginations() {
		softassert.flush();
		Utils.log("TestClass.class", 1, "@Test verifyMerchantPaginations()");
		String url = helper.server + helper.t6b_pagination + helper.debug
				+ helper.rfdefault;
		Utils.log("TestClass.class", 1, "Test running for url: " + url);

		// driver.get(url);
		browser.openPage(driver, url);
		boolean pg = browser.getCurrentURL(driver).contains(
				"/index__start--20.html#reviews-top");
		softassert.assertTrue(pg,
				"Pagination is not working for merchant pages [6B] for URL = "
						+ url);
	}

	/*
	 * Cool Pages Brand, Store any attribute should not have "more" link to go
	 * on 7T page. Max 8 attribute should come for any attribute
	 */
	@Test
	public void verifyAttributeInCoolExperience() {
		softassert.flush();
		Utils.log("TestClass.class", 1,
				"@Test verifyAttributeInCoolExperience()");
		String url = helper.server + helper.t7y + helper.debug
				+ helper.rfdefault;
		Utils.log("TestClass.class", 1, "Test running for url: " + url);

		browser.openPage(driver, url);
		int brandCount = browser.getSizeByXpath(driver,
				Constants.ATTR_COOL_STORES_XP);
		softassert.assertTrue(brandCount == 8,
				"Brands should be eight if not change the 7Y Urls and re run test");
		softassert.assertFalse(
				browser.getTextByXPath(driver,
						Constants.ATTR_COOL_STORES_EIGHTS_POSITION_XP)
						.equalsIgnoreCase("More"),
				"More(7T) should not come in Cool attributes for URL= " + url);
		softassert.assertAll();
	}

	/*
	 * S11975: [Bizrate] Turn off Shopping AlertsWe have removed Shopping alert
	 * for all RF codes
	 */
	@Test
	public void verifyShoppingAlertButtonAbsence() {

		softassert.flush();
		Utils.log("TestClass.class", 1,
				"@Test verifyShoppingAlertButtonAbsence()");

		String urlwlk = helper.server + helper.t7s + helper.debug
				+ helper.rfwlk;
		Utils.log("TestClass.class", 1, "Test running for url: " + urlwlk);
		browser.openPage(driver, urlwlk);
		softassert.assertTrue(browser.getSizeByID(driver,
				Constants.ATTR_SHOPPING_ALERT_BUTTON) == 0,
				"Shopping alert button is visibe to WLK traffic for URL= "
						+ urlwlk);

		String urlseo = helper.server + helper.t7s + helper.debug
				+ helper.rfseo;
		Utils.log("TestClass.class", 1, "Test running for url: " + urlseo);
		browser.openPage(driver, urlseo);
		softassert.assertTrue(browser.getSizeByID(driver,
				Constants.ATTR_SHOPPING_ALERT_BUTTON) == 0,
				"Shopping alert button is visibe to SEO traffic for URL= "
						+ urlseo);

		String urlsem = helper.server + helper.t7s + helper.debug
				+ helper.rfsem;
		Utils.log("Test_AttributePod.class", 1, "Test running for url: "
				+ urlsem);
		browser.openPage(driver, urlsem);
		softassert.assertTrue(browser.getSizeByID(driver,
				Constants.ATTR_SHOPPING_ALERT_BUTTON) == 0,
				"Shopping alert button is visibe to SEM traffic for URL= "
						+ urlsem);

		softassert.assertAll();
	}

	/*
	 * DE 8826 - Refining by keyword gives 500 uh oh page In prod if user refine
	 * search using refine keyword search box present in middle of hot pages -
	 * 500 error code used to come Here we make sure while searching anything,
	 * 500 error should not come 7S, 10J, 8B, 7X, 8F, ARL, MLT
	 */
	@Test
	public void verifyRefineKeyword() {

		softassert.flush();
		Utils.log("TestClass.class", 1, "@Test testRefineKeyword()");

		List<String> tokens = new ArrayList<String>();
		tokens.add(helper.tmlt);
		tokens.add(helper.t8b);

		for (String token : tokens) {
			String url = helper.server + token + helper.debug
					+ helper.rfdefault;
			Utils.log("TestClass.class", 1, "Test running for url: " + url);
			browser.openPage(driver, url);
			boolean error500 = false;
			browser.writeInTextBoxByID(driver,
					Constants.ATTR_REFINE_KEYWORD_TEXTBOX_ID, "dell");
			browser.clickByXPath(driver,
					Constants.ATTR_REFINE_KEYWORD_BUTTON_XP);
			browser.getPageSource(driver).contains(
					"Uh oh! You found an error with our site.");
			softassert.assertFalse(error500, "Error 500 coming for url = "
					+ url);
		}
		softassert.assertAll();
	}

	/*
	 * S9859 - [SEO] BR - Remove Page Intro from 8N / 7Y Page intro div should
	 * not come for 8N and 7Y token
	 */
	@Test
	public void verifyPageIntroAbsenceFor7Yand8N() {
		softassert.flush();
		Utils.log("TestClass.class", 1, "@Test verifyPageIntroFor7Yand8N()");

		List<String> tokens = new ArrayList<String>();
		tokens.add(helper.t7y);
		tokens.add(helper.t8n);

		for (String token : tokens) {
			String url = helper.server + token + helper.debug
					+ helper.rfdefault;
			Utils.log("TestClass.class", 1, "Test running for url: " + url);
			browser.openPage(driver, url);

			// String introHeader="id=\"intro-header\"";
			// String intro="id=\"intro\"";
			// softassert.assertFalse(browser.getPageSource(driver).contains(introHeader),"Intro is coming for url ="+url);
			// softassert.assertFalse(browser.getPageSource(driver).contains(intro),
			// "Intro is coming for url ="+url);

			softassert.assertFalse(browser.isElementPresentByID(driver,
					Constants.SRP_PAGE_INTRO_HEADER_ID),
					"Page intro header should not come for Url =" + url);
			softassert.assertFalse(browser.isElementPresentByID(driver,
					Constants.SRP_PAGE_INTRO_ID),
					"Page intro desc should not come for Url =" + url);
		}
		softassert.assertAll();
	}

	/*
	 * S7650 | S9668 - [SEO] - BR - Integrate Google Analytics Code for Bizrate
	 * GA should come for all page token
	 */

	@Test
	public void verifyGACode() {
		softassert.flush();
		Utils.log("TestClass.class", 1, "@Test verifyGACode()");

		List<String> tokens = new ArrayList<String>();
		tokens.add(helper.t7y);
		tokens.add(helper.t8n);
		tokens.add(helper.t7s);
		tokens.add(helper.t8b);

		for (String token : tokens) {
			String url = helper.server + token + helper.debug
					+ helper.rfdefault;
			Utils.log("TestClass.class", 1, "Test running for url: " + url);
			browser.openPage(driver, url);
			softassert.assertTrue(
					browser.getPageSource(driver).contains(
							"google-analytics.com/ga.js"),
					"GA code is not present for url =" + url);
		}
		softassert.assertAll();
	}

	/*
	 * S10463 - [Bizrate] Disable user tracking for bot | Valid tokens:
	 * 7Y,12,8N,7R,7X,7S,8B,10J
	 */
	@Test
	public void verifyComscoreGAAbsenceForBot() {
		softassert.flush();
		Utils.log("TestClass.class", 1, "@Test verifyComscoreGAAbsenceForBot()");

		List<String> tokens = new ArrayList<String>();
		tokens.add(helper.t7y);
		tokens.add(helper.t7s);
		tokens.add(helper.t8b);

		driver = helper.getGoogleBotMozillaDriver(); // Bot driver initialized

		for (String token : tokens) {
			String url = helper.server + token + helper.debug;
			Utils.log("TestClass.class", 1, "Test running for url: " + url);
			boolean bool = true;
			browser.openPage(driver, url);
			String texttofind = "scorecardresearch.com/beacon.js";
			bool = browser.getPageSource(driver).contains(texttofind);
			softassert.assertFalse(bool,
					"Comscore still visible in page for url = " + url);
		}
		driver = helper.getDriver(); // Switch back to normal driver from Bot
										// driver
		softassert.assertAll();
	}

	/*
	 * S10911 - [BIZRATE] Google Insights Code Snippet for 7Y, 8B and 7S
	 */
	@Test
	public void verifyGoogleInsightSurvey() {
		softassert.flush();
		Utils.log("TestClass.class", 1, "@Test verifyGoogleInsightSurvey()");

		List<String> tokens = new ArrayList<String>();
		tokens.add(helper.t7y);
		tokens.add(helper.t7s);
		tokens.add(helper.t8b);

		for (String token : tokens) {
			String url = helper.server + token + helper.debug
					+ helper.rfdefault;
			Utils.log("TestClass.class", 1, "Test running for url: " + url);
			browser.openPage(driver, url);
			browser.waitImplicitly(driver, 10);
			String texttofind = "//survey.g.doubleclick.net/async_survey?site=l53g2d5ngvmnbiu5bidybvgjmi";
			softassert.assertTrue(
					browser.getPageSource(driver).contains(texttofind),
					"Google Insight survey is not coming for url = " + url);
		}
		softassert.assertAll();
	}

	/*
	 * DE 9765 - [Bizrate] Fix UI Issue with Detailed Store Ratings Page
	 */
	@Test
	public void verifyOverallSatisfactionPodAlignmentIn6E() {
		softassert.flush();
		Utils.log("TestClass.class", 1,
				"@Test verifyOverallSatisfactionPodAlignmentIn6E()");
		String url = helper.server + helper.t6e + helper.debug
				+ helper.rfdefault;
		Utils.log("TestClass.class", 1, "Test running for url: " + url);

		browser.openPage(driver, url);
		browser.maximizeWindow(driver);
		Point loc = browser.getLocationByXpath(driver,
				Constants.MERCHANT_RATINGPOD_6E_XP);
		Utils.log("Test_MerchantPages.class", 1,
				"Y Location is:" + loc.getY()
						+ "   and X location is:" + loc.getX());
		softassert.assertTrue(loc.getY() > 730 && loc.getY() < 775,
				"6E is misaligned");
		softassert.assertAll();
	}

	/*
	 * S10751 - [INSGHTS] Incorrect Smileys shown on Merchant Report Card Pages
	 * 2nd and 3rd smiley on 6B contains null in class because of which wrong
	 * status is showing
	 */
	@Test
	public void verifySmileyStatusOnMerchantPages() {
		softassert.flush();
		Utils.log("TestClass.class", 1,
				"@Test verifySmileyStatusOnMerchantPages()");
		String url = helper.server + helper.t6b + helper.debug
				+ helper.rfdefault;
		Utils.log("TestClass.class", 1, "Test running for url: " + url);
		Utils.log("TestClass.class", 1, "Test running for url: " + url);

		browser.openPage(driver, url);
		softassert.assertFalse(
				browser.getAttributeByXPath(driver,
						Constants.MERCHANT_SMILEY_WOULDSHOPHERE_6B_XP, "class")
						.contains("null"),
				"Null is coming in Class name for smileys in 6B page, URL = "
						+ url);
		softassert.assertFalse(
				browser.getAttributeByXPath(driver,
						Constants.MERCHANT_SMILEY_LIKELIHOOD_6B_XP, "class")
						.contains("null"),
				"Null is coming in Class name for smileys in 6B page, URL = "
						+ url);
		softassert.assertAll();
	}

	/*
	 * S12071 - [Bizrate] AB Test AFSH SLs
	 */

	@Test
	public void verifyAFSHNewPLA() {
		softassert.flush();
		Utils.log("TestClass.class", 1, "@Test verifyAFSHNewPLA()");
		List<String> rfs = new ArrayList<String>();
		rfs.add("?rf=ggl");
		rfs.add("?rf=wlk");
		rfs.add("?rf=wgg");
		rfs.add("?rf=ssb");

		for (String rf : rfs) {
			List<String> listOftokens = tokens.getAllTokensOnce(helper); // Used
																			// for
																			// complete
																			// QA
			// List<String>
			// listOftokens=tokens.getHotSRPTokensForSanity(helper); // Used for
			// Regression test suite

			int i = 1;
			for (String token : listOftokens) {
				String url = helper.server + token + helper.debug + rf;
				// Utils.log("TestClass.class", 1,
				// "Test running for url: "+url);
				browser.openPage(driver, url);

				if (i == 1) {
					debugHeaderPod.setSessionID(driver, "15");
					debugHeaderPod.clickSetParamsButton(driver);
				}

				try {

					String pgToken = debugHeaderPod.getPageToken(driver);
					// boolean isPLA=browser.getTextByXPath(driver,
					// Constants.FOOTER_XP_PLA_HEADING).contains("Product Listing Ads");
					// - removed to avoid noSuchElement exception
					// int isPLA=browser.getSizeByXpath(driver,
					// Constants.FOOTER_XP_PLA_HEADING);
					boolean isPLA = browser.isElementPresentByXPath(driver,
							Constants.FOOTER_XP_PLA_HEADING);
					// boolean
					// isPLADisp=browser.isElementDisplayedByXPath(driver,
					// Constants.FOOTER_XP_PLA_HEADING);
					// boolean isPLAEnab=browser.isElementEnabledByXPath(driver,
					// Constants.FOOTER_XP_PLA_HEADING);

					// boolean isSL=browser.getTextByXPath(driver,
					// Constants.FOOTER_XP_SL_HEADING).contains("Sponsored Links");
					int isSL = browser.getSizeByXpath(driver,
							Constants.FOOTER_XP_SL_HEADING);

					String pageSource = browser.getPageSource(driver);

					String regex = "\"vert-pla-bizrate-us-[a-z][a-z][a-z]";
					Pattern pattern = Pattern.compile(regex);
					Matcher matcher = pattern.matcher(pageSource);
					if (matcher.find()) {
						String match = matcher.group(0);
						System.out.println("," + i + "," + rf + "," + pgToken
								+ "," + isPLA + ", " + isSL + "," + match + ","
								+ url);
						// System.out.println(","+i+","+rf+","+pgToken+",Count="+isPLA+", Disp="+isPLADisp+",ena= "+isPLAEnab+", "+isSL+","+match+","+url);
					} else {
						System.out.println("," + i + "," + rf + "," + pgToken
								+ "," + isPLA + ", " + isSL + ","
								+ "No Matcher" + "," + url);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				i++;

			}
		}
		System.out.println("?rf= *****************************************");
	}

	@Test
	public void verifyPageToken() {
		List<String> listTokens = tokens.getAllTokens(helper);
		int i = 0;

		for (String token : listTokens) {
			i++;
			String url = helper.server + token + helper.rfdefault;
			browser.openPage(driver, url);
			try {
				String tokenId = browser.getAttributeByID(driver,
						"SZPageToken", "id");
				// String tokenId=debugHeaderPod.getPageToken(driver);
				String tokenValue = browser.getAttributeByID(driver,
						"SZPageToken", "value");
				System.out.println(i + "," + tokenId + "," + tokenValue + ","
						+ url);
			} catch (NoSuchElementException e) {
				System.out.println(i + ",EXCEPTION," + url);
			}

		}

	}

	/*
	 * S12365 - Data href should not be part of code as Google Bot now able to
	 * read JS
	 */
	@Test
	public void verifyDataHrefAbsense() {
		softassert.flush();
		Utils.log("TestClass.class", 1, "@Test verifyAFSHNewPLA()");
		List<String> rfs = new ArrayList<String>();
		rfs.add("?rf=ggl");
		// rfs.add("?rf=wlk");
		// rfs.add("?rf=wgg");
		// rfs.add("?rf=eml");
		// rfs.add("?rf=ssb");

		for (String rf : rfs) {
			List<String> listOftokens = tokens.getAllTokens(helper);
			// List<String>
			// listOftokens=tokens.getHotSRPTokensForSanity(helper);
			for (String token : listOftokens) {
				String url = helper.server + token + helper.debug + rf;
				browser.openPage(driver, url);
				String tokenValue = debugHeaderPod.getPageToken(driver);
				boolean datahref = browser.getPageSource(driver).contains(
						"data-href");
				System.out.println(datahref + "," + tokenValue + "," + url);
				softassert.assertFalse(datahref,
						"Data-href appearing for url =" + url);
			}
		}
		softassert.assertAll();
	}

	/*
	 * S10456 - [CNX_SITE] SEO : Fix broken contact us links
	 */
	@Test
	public void verifyConnexityLinks() {
		softassert.flush();
		Utils.log("TestClass.class", 1, "@Test verifyConnexityLinks()");

		BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(
					new FileReader(
							"C:\\sourceCode\\testngworkspace2.0\\testngworkspace2.0\\textfiles\\connexity_urls.txt"));
			int i = 0;
			while ((sCurrentLine = br.readLine()) != null) {
				i++;
				System.out.println(i + "-" + sCurrentLine);
				driver.get(sCurrentLine);

				List<WebElement> list = driver.findElements(By
						.linkText("contact us"));
				for (WebElement li : list) {
					String href = li.getAttribute("href");
					System.out.println(href);
					softassert.assertTrue(href.contains("/contact/"),
							"Wrong href created i.e. " + href);
				}

			}
			softassert.assertAll();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	@Test
	public void sampleCode() {

		try {

			String content = "This is the content to write into file";
			File file = new File("D:\\temp\\sysout.txt");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.write("New Updates");
			bw.newLine();
			bw.write("New 123 Updates");

			bw.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Following Tests Are No longer valid for Bizrate
	 */

	/*
	 * S12000 - Do Not Display PLA Text if PLA Texts Do Not Render
	 */
	// @Test
	public void verifyPLAAbsence() {
		softassert.flush();
		Utils.log("TestClass.class", 1, "@Test verifyPLAAbsence()");
		List<String> rfs = new ArrayList<String>();
		rfs.add("?rf=wlk");
		// rfs.add("?rf=ggl");
		// rfs.add("?rf=wgg");

		boolean pla = true;
		for (String rf : rfs) {
			// List<String> listOftokens=tokens.getAllTokens(helper); // Used
			// for complete QA
			List<String> listOftokens = tokens.getHotSRPTokensForSanity(helper); // Used
																					// for
																					// Regression
																					// test
																					// suite

			for (String token : listOftokens) {
				String url = helper.server + token + helper.debug + rf;
				Utils.log("TestClass.class", 1, "Test running for url: " + url);
				browser.openPage(driver, url);
				browser.waitForResults(2000);

				// boolean
				// brjs=driver.getPageSource().contains("/static/br3/js/bizrate.js");
				// boolean
				// cspjs=driver.getPageSource().contains("/br3/js/bizrate/csp.js");
				pla = browser.isElementPresentByID(driver,
						"pla-sponsored-links");

				if (pla) {
					browser.openPage(driver, url);
					pla = browser.isElementPresentByID(driver,
							"pla-sponsored-links");
				}
				// System.out.println(debugHeaderPod.getPageToken(driver)+", PLA="+pla+", BRJS="+brjs+", CSPJS="+cspjs+", "+url);
				softassert.assertFalse(pla, "PLA div visible for = " + url);
			}
		}
		softassert.assertAll();
	}

	/*
	 * S11467 - [BR] Test turning off Marketing Interstitial A/B - Interstitial
	 * should not come for session range = 40 to 99 |
	 * S11467_Turn_Off_Interstitial Test is a looser hence removed
	 */
	// @Test
	public void verifyInterstitial() {
		softassert.flush();
		Utils.log("TestClass.class", 1, "@Test verifyInterstitial()");
		String url = helper.server + helper.tmkt;
		Utils.log("TestClass.class", 1, "Test running for url: " + url);

		// This block will set session
		String url2 = helper.server + helper.t7s + helper.debug + helper.rfsem;
		driver.get(url2);
		String session = "39";
		debugHeaderPod.setSessionID(driver, session);
		debugHeaderPod.clickSetParamsButton(driver);

		driver.get(url);
		String pageSource = driver.getPageSource();
		boolean a = pageSource.contains("id=\"interstitial\"");

		softassert.assertFalse(a, "Interstitial appears for session = "
				+ session);
	}

}
