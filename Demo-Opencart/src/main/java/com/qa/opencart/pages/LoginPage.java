package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
	
	private Page page;
	
	// String Locators - OR
	private String emailTextField = "input#input-email";
	private String passwordTextField = "input#input-password";
	private String loginButton = "//input[@type='submit' and @value='Login']";
	private String forgotPasswordLink = "//div[@class='form-group']//a[normalize-space()='Forgotten Password']";
	private String continueButtonRegister = "//a[normalize-space()='Continue']";
	private String logoutLink = "//a[@class='list-group-item'][normalize-space()='Logout']";
	
	// Page constructor
	public LoginPage(Page page) {
		this.page = page;
	}
	
	// Page actions/methods
	public String getLoginPageTitle() {
		String actualLoginPageTitle = page.title();
		System.out.println("Page title is: "+actualLoginPageTitle);
		return actualLoginPageTitle;
	}
	
	public boolean isForgotPasswordLinkExist() {
		return page.isVisible(forgotPasswordLink);
	}
	
	public boolean doLogin(String appUserName, String appPassword) {
		System.out.println("App Credentials -> "+appUserName+" : "+appPassword);
		page.fill(emailTextField, appUserName);
		page.fill(passwordTextField, appPassword);
		page.click(loginButton);
		if(page.isVisible(logoutLink)) {
			System.out.println("User is logged in successfully");
			return true;
		} else {
			return false;
		}
	}

}
