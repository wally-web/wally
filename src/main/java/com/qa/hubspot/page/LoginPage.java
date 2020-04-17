package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.Credentials;
import com.qa.hubspot.util.ElementUtil;
import com.qa.hubspot.util.JavaScriptUtil;

public class LoginPage extends BasePage {
	
	WebDriver driver;
	ElementUtil elementUtil;
	JavaScriptUtil jsUtil;
	//1. Create Locators: By
	
	
	By emailID = By.id("username");
	By psw = By.id("password");
	By loginbtn = By.id("loginBtn");
	By signUpLink = By.linkText("Sign up");
	By errorLoginMsg = By.xpath("//div[@class='private-alert__inner']");
	 
	
	public LoginPage(WebDriver driver){	//2.we create the constructor
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
	}
	
	//3.page actions
	
//	public String getPageTitleUsingJS(){
//		return jsUtil.getTitleByJS();
//	}
	
	public String pageGetTitle(){
		return elementUtil.doGetPageTitle();
	}
	
	

	public boolean checkSignUpLink(){
		return elementUtil.doIsDisplayed(signUpLink);
	}
	
	public HomePage doLogIn(Credentials userCred){
		//elementUtil.waitForElementPresent(emailID);
		elementUtil.doSendKeys(emailID, userCred.getAppUsername());
		elementUtil.doSendKeys(psw, userCred.getAppPassword());
		elementUtil.doClick(loginbtn);
		
		return new HomePage(driver);
		
	}
	public boolean checkLoginErrorMessage(){
		return elementUtil.doIsDisplayed(errorLoginMsg);
	}
	
	
	

}
