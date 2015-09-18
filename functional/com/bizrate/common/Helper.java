package com.bizrate.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.bizrate.pods.DebugHeaderPod;
import com.gargoylesoftware.htmlunit.BrowserVersion;

public class Helper {

	static WebDriver driver;
	Properties prop;

	public String tmkt;
	public String rfsem, rfseo, rfwlk, rfdefault, rfemlib;
	public String server, debug, zip;
	public String driver2run;

	public String t7s, t7y, t10j, t8b, t8n, t8f, t7x, t7xc, tmlt, tarl, t7t,
			t13w, t2, t12, t7r, t6b, t6c, t6d, t6e, t10g, t3h, tcdl2, tbong,
			tfsv, t13s, t8nr, t8no, t8sm, trt1, tfrv, t98, t20, t3j, t15k, t8i,
			t12f, te5, te5_1, t14z, t31, tes1, t33, t15z, t13v, tor1, t25,
			t7r_mature, t7x_mature, t8b_mature, t7y_mature, t12f_mature;
	public String t8nG, tmltG, t10jG, t7sG, t7xG, t7yG, t8bG, t8fG, t8nrG,
			trt1G, t8b_matureG;
	public String t6e_norating, t6e_p2010, t8n_nopdt, t7r_old, t7r_etrx,
			t7r_clothes, t7r_garden, t7r_sports, t7r_computer, t7r_kids,
			t7r_appliance, t8b_ib, t3h_amz, t12_openbox, t6b_old2, t6b_old1,
			t6b_pagination;

	public DebugHeaderPod debugHeaderPod;

	public void getProperties() {
		prop = new Properties();
		try {
			prop.load(new FileInputStream(
					"C:\\workspace\\Webdriver_FW2.0\\config\\bizrate.properties"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		rfdefault = prop.getProperty("RF_DEFAULT");
		rfsem = prop.getProperty("SEM");
		rfseo = prop.getProperty("SEO");
		rfwlk = prop.getProperty("WLK");

		debug = prop.getProperty("debug");
		rfemlib = prop.getProperty("EML_IB");

		server = prop.getProperty("SERVER");
		driver2run = prop.getProperty("DRIVER");
		zip = prop.getProperty("ZIP");

		t7s = prop.getProperty("7S");
		t7y = prop.getProperty("7Y");
		t10j = prop.getProperty("10J");
		t8b = prop.getProperty("8B");
		t8b_ib = prop.getProperty("8B_IB");

		t8n = prop.getProperty("8N");
		t8n_nopdt = prop.getProperty("8N_NOPRODUCT");
		t8nr = prop.getProperty("8NR");
		t8sm = prop.getProperty("8SM");
		t8no = prop.getProperty("8NO");

		t8f = prop.getProperty("8F");
		t7x = prop.getProperty("7X");
		t7xc = prop.getProperty("7XC");
		tmlt = prop.getProperty("MLT");
		tarl = prop.getProperty("ARL");
		t7t = prop.getProperty("7T");
		t8f = prop.getProperty("8F");
		t12 = prop.getProperty("12");
		t12_openbox = prop.getProperty("12_OPENBOX");

		t3h = prop.getProperty("3H");
		t3h_amz = prop.getProperty("3H_AMZ");

		t7r = prop.getProperty("7R");
		t7r_old = prop.getProperty("7R_OLD");
		t7r_etrx = prop.getProperty("7R_ETRX");
		t7r_garden = prop.getProperty("7R_GARDEN");
		t7r_clothes = prop.getProperty("7R_CLOTHES");
		t7r_sports = prop.getProperty("7R_SPORTS");
		t7r_computer = prop.getProperty("7R_COMPUTER");
		t7r_kids = prop.getProperty("7R_KIDS");
		t7r_appliance = prop.getProperty("7R_APPLIANCES");

		t2 = prop.getProperty("2");
		tbong = prop.getProperty("BONG");
		t10g = prop.getProperty("10G");

		t6b = prop.getProperty("6B");
		t6b_old1 = prop.getProperty("6B_OLD1");
		t6b_old2 = prop.getProperty("6B_OLD2"); // added for test
												// verifyMerchantPagesForC14N()
		t6b_pagination = prop.getProperty("6B_PAGINATION");

		t6c = prop.getProperty("6C");
		t6d = prop.getProperty("6D");
		t6e = prop.getProperty("6E");
		t6e_norating = prop.getProperty("6E_NORATING");
		t6e_p2010 = prop.getProperty("6E_P2010");
		tcdl2 = prop.getProperty("CDL2");

		t13w = prop.getProperty("13W");
		t13s = prop.getProperty("13S");
		tfsv = prop.getProperty("FSV");

		trt1 = prop.getProperty("RT1");
		tfrv = prop.getProperty("FRV");
		t98 = prop.getProperty("98");
		t20 = prop.getProperty("20");
		t3j = prop.getProperty("3J");
		t15k = prop.getProperty("15K");
		t8i = prop.getProperty("8I");
		t12f = prop.getProperty("12F");
		te5 = prop.getProperty("E5");
		te5_1 = prop.getProperty("E5_1");
		t14z = prop.getProperty("14Z");
		t31 = prop.getProperty("31");
		tes1 = prop.getProperty("ES1");
		t33 = prop.getProperty("33");
		t15z = prop.getProperty("15Z");
		t13v = prop.getProperty("13V");
		tor1 = prop.getProperty("OR1");
		t25 = prop.getProperty("25");

		t7r_mature = prop.getProperty("7R_MATURE");
		t7x_mature = prop.getProperty("7X_MATURE");
		t8b_mature = prop.getProperty("8B_MATURE");
		t7y_mature = prop.getProperty("7Y_MATURE");
		t12f_mature = prop.getProperty("12F_MATURE");

		t8nG = prop.getProperty("8N_G");
		tmltG = prop.getProperty("MLT_G");
		t10jG = prop.getProperty("10J_G");
		t7sG = prop.getProperty("7S_G");
		t7xG = prop.getProperty("7X_G");
		t7yG = prop.getProperty("7Y_G");
		t8bG = prop.getProperty("8B_G");
		t8fG = prop.getProperty("8F_G");
		t8nrG = prop.getProperty("8NR_G");
		trt1G = prop.getProperty("RT1_G");
		t8b_matureG = prop.getProperty("8B_MATURE_G");

		tmkt = prop.getProperty("MKT");

	}

	public WebDriver getDriver() {
		if (driver2run.equals("chrome")) {
			driver = getChromeWebDriver();
		} else if (driver2run.equals("html")) {
			driver = getHtmlDriver();
		} else if (driver2run.equals("mozilla")) {
			driver = getMozillaFirefoxDriver();
		} else if (driver2run.equals("ie")) {
			driver = getIEWebDriver();
		} else if (driver2run.equals("googlebotmozilla")) {
			driver = getGoogleBotMozillaDriver();
		} else if (driver2run.equals("bingbotmozilla")) {
			driver = getBingBotMozillaDriver();
		} else if (driver2run.equals("htmlbot")) {
			driver = getHtmlBotDriver();
		}
		return driver;
	}

	public WebDriver getChromeWebDriver() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\drivers\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		return driver;
	}

	public WebDriver getMozillaFirefoxDriver() {
		ProfilesIni allProfiles = new ProfilesIni();
		FirefoxProfile profile = allProfiles
				.getProfile("default-1352171257880"); // to get profile name:
														// run > firefox.exe -p
		driver = new FirefoxDriver(profile);
		return driver;
	}

	public WebDriver getIEWebDriver() {
		WebDriver driver = new InternetExplorerDriver();
		return driver;
	}

	public WebDriver getIPadMozillaDriver() {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference(
				"general.useragent.override",
				"Mozilla/5.0 (iPad; U; CPU OS 3_2 like Mac OS X; en-us) AppleWebKit/531.21.10 (KHTML, like Gecko) Version/4.0.4 Mobile/7B334b Safari/531.21.10");
		driver = new FirefoxDriver(profile);
		return driver;
	}

	public WebDriver getIPhoneMozillaDriver() {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference(
				"general.useragent.override",
				"Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_3_2 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8H7 Safari/6533.18.5");
		driver = new FirefoxDriver(profile);
		return driver;
	}

	public WebDriver getGoogleBotMozillaDriver() {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("general.useragent.override",
				"Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)");
		driver = new FirefoxDriver(profile);
		return driver;
	}

	public WebDriver getBingBotMozillaDriver() {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("general.useragent.override",
				"Mozilla/5.0 (compatible; bingbot/2.0; +http://www.bing.com/bingbot.htm)");
		driver = new FirefoxDriver(profile);
		return driver;
	}

	public WebDriver getHtmlDriver() {
		driver = new HtmlUnitDriver();
		return driver;
	}

	public WebDriver getHtmlBotDriver() {
		driver = new HtmlUnitDriver(
				new BrowserVersion(
						"Firefox",
						"5.0 (Windows)",
						"Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)",
						28));
		return driver;
	}

	public void reRunUrlIfErrorPresent(WebDriver driver, String url) {
		if (debugHeaderPod.isErrorPresent(driver)) {
			driver.get(url);
		}
	}

	public void getHttpStatusCode(WebDriver driver, String url) {
		driver.get(url);
		try {
			URL urlObj = new URL(url);
			URLConnection connection = urlObj.openConnection();
			connection.connect();

			if ((connection instanceof HttpURLConnection)) {
				HttpURLConnection httpConnection = (HttpURLConnection) connection;
				int code = httpConnection.getResponseCode();

				System.out.println(" # " + code + " #" + url);
			} else {
				System.err.println("error - not a http request!");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public String regex(String str, String regex) {
		String regexValue = "";
		Pattern myPattern = Pattern.compile(regex, 2);
		Matcher myMatcher = myPattern.matcher(str);
		try {
			if (myMatcher.find()) {
				regexValue = myMatcher.group(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return regexValue;
	}
}
