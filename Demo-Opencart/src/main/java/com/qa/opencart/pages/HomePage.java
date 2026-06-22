package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class HomePage {
	
	protected Page page;
	
	// String Locators - OR
	private String search = "//input[@name='search']";
	private String searchIcon = "div#search button";
	private String searchPageHeader = "//*[@id=\"content\"]/h1";
	private String myAccountLink = "//span[normalize-space()='My Account']";
	private String registerLink = "//a[normalize-space()='Register']";
	private String loginLink = "//a[normalize-space()='Login']";
	
	// Page Constructor
	public HomePage(Page page) {
		this.page = page;
	}
	
	// Page actions/methods
	public String getHomePageTitle() {
		String title = page.title();
		System.out.println("Page title is: "+title);
		return title;
	}
	
	public String getHomePageURL() {
		String url = page.url();
		System.out.println("Page URL is: "+url);
		return url;
	}
	
	public String doSearch(String productName) {
		page.fill(search, productName);
		page.click(searchIcon);
		String pageHeader = page.textContent(searchPageHeader);
		System.out.println("Page header is: "+pageHeader);
		return pageHeader;
	}
	
	public LoginPage navigateToLoginPage() {
		page.click(myAccountLink);
		page.click(loginLink);
		return new LoginPage(page);
	}
}
