package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.qa.opencart.base.TestBase;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.factory.PlaywrightFactory;
import com.qa.opencart.pages.HomePage;

public class HomePageTest extends TestBase{
	
	@Test
	public void homePageTitleTest() {
		String actualHomePageTitle = homePage.getHomePageTitle();
		Assert.assertEquals(actualHomePageTitle, AppConstants.HOME_PAGE_TITLE);
	}
	
	@Test
	public void homePageURLTest() {
		String actualHomePageURL = homePage.getHomePageURL();
		Assert.assertEquals(actualHomePageURL, prop.getProperty("url"));
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] 
		{
			{"Macbook"},
			{"iMac"},
			{"Samsung"}
		};
	}
	
	@Test(dataProvider = "getProductData")
	public void searchTest(String productName) {
		String actualSearchHeader = homePage.doSearch(productName);
		Assert.assertEquals(actualSearchHeader, "Search - "+productName);
	}
	
	

}
