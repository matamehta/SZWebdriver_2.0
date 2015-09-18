package com.bizrate.common;

public enum SortFilter {

	BESTMATCH("Best Match"), PRODUCTRATING("Product Rating"), PRICELOW2HIGH(
			"Price Low-High"), PRICEHIGH2LOW("Price High-Low");
	private String strSortFilter;

	private SortFilter(String s) {
		strSortFilter = s;
	}

	public String getFilter() {
		return strSortFilter;
	}

}
