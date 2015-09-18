/**
 * 
 */
package com.bizrate.common;

/**
 * @author ssingh13
 * 
 */
public class Constants {
	// Debug Header
	public static final String DHEADER_NAME_SESSIONID = "sessionIdDebugParam";
	public static final String DHEADER_XP_SET_PARAM_BUTTON = "//div[@id='debugParamCommandButtons']/input[1]";
	public static final String DHEADER_ID_ERROR_DOT = "requestErrorIndicator";
	public static final String DHEADER_ID_DEBUGHEADER = "debug_header";
	public static final String DHEADER_ID_PAGE_TOKEN = "pageTokenValue";

	// Header Pod
	public static final String HEADER_ID_IB_MARKETING_HEADER = "ib-marketing-header";
	public static final String HEADER_ID_SEARCH_BOX = "searchTerm";
	public static final String HEADER_NAME_SEARCH_BUTTON = "SEARCH_GO";

	// Attribute Pod
	public static final String ATTR_COOL_STORES_XP = "//*[@id='main']/div[1]/div[3]/li[3]/div/ul/li/a";
	public static final String ATTR_COOL_STORES_EIGHTS_POSITION_XP = "//*[@id='main']/div[1]/div[3]/li[3]/div/ul/li[8]/a";
	public static final String ATTR_SHOPPING_ALERT_BUTTON = "set-shopping-alert-button";
	public static final String ATTR_REFINE_KEYWORD_TEXTBOX_ID = "keyword-refine";
	public static final String ATTR_REFINE_KEYWORD_BUTTON_XP = "//*[@id='refine']/input[2]";

	// SRP Pod
	public static final String SRP_XP_STRONG_KEYWORD_TITLE = "//div[@id='column2']/div[2]/table/tbody/tr/td[2]/p[1]/a/strong";
	public static final String SRP_XP_MLT = "//div[@id='column2']/div[2]/table/tbody/tr/td[2]/div/ul/li[1]/span";
	public static final String SRP_ID_MIN_PRICE = "minPriceBox";
	public static final String SRP_NAME_MAX_PRICE = "maxPriceBox";
	public static final String SRP_ID_PRICE_FILTER_BUTTON = "update-go-button";
	public static final String SRP_PAGE_INTRO_HEADER_ID = "intro-header";
	public static final String SRP_PAGE_INTRO_ID = "intro";

	// Footer Pod
	public static final String FOOTER_XP_PLA_HEADING = "//div[@id='pla-sponsored-links']/span";
	public static final String FOOTER_XP_SL_HEADING = "//div[@id='sponsored-links']/span";

	// W/O any Pods
	public static final String MERCHANT_XP_C14N = "//head/link[@rel='canonical']";
	public static final String MERCHANT_RATINGPOD_6E_XP = "//div[@id='store_ratings']/div[1]/table";
	public static final String MERCHANT_SMILEY_WOULDSHOPHERE_6B_XP = "//div[@id='store_ratings']/div[2]/div[1]/div/span[2]";
	public static final String MERCHANT_SMILEY_LIKELIHOOD_6B_XP = "//div[@id='store_ratings']/div[2]/div[1]/div/span[3]";

}
