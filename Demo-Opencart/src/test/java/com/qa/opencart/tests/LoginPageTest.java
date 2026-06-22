package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.TestBase;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends TestBase{
	
	@Test(priority = 1)
	public void loginPageNavigationTest() {
		loginPage = homePage.navigateToLoginPage();
		String actualLoginPageTitle = loginPage.getLoginPageTitle();
		System.out.println("Login page title is: "+actualLoginPageTitle);
		Assert.assertEquals(actualLoginPageTitle, AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority = 2)
	public void forgotPasswordLinkExistTest() {
		loginPage = homePage.navigateToLoginPage();
		Assert.assertTrue(loginPage.isForgotPasswordLinkExist());
	}
	
	@Test(priority = 3)
	public void loginTest() {
		Assert.assertTrue(loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim()));
	}

}
