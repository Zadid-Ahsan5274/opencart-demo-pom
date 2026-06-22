package com.qa.opencart.base;

import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.microsoft.playwright.Page;
import com.qa.opencart.factory.PlaywrightFactory;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;

public class TestBase {
	
	protected PlaywrightFactory pf;
	protected Page page;
	protected HomePage homePage;
	protected LoginPage loginPage;
	protected Properties prop;
	
	@BeforeClass
	public void setUp() {
		pf = new PlaywrightFactory();
		prop = pf.init_prop();
		page = pf.initBrowser(prop);
		homePage = new HomePage(page);
	}
	
	@AfterClass
	public void tearDown() {
		page.context().browser().close();
	}

}
