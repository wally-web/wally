package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter; 
import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
 
 
	@Epic("Epic - 101 : create login page features")
	@Feature("US - 501 : create test for login page on hubspot")
	public class LoginPageTest {
	BasePage basePage; 
	Properties prop;
	WebDriver driver;
	LoginPage loginPage;
	Credentials userCred;
	  
	@BeforeMethod(alwaysRun = true)
	@Parameters(value={"browser"})
	public void setup(String browser){
		
		String browserName  = null;
		basePage = new BasePage();
		prop = basePage.init_properties();
		
		if(browser.equals(null)){
			browserName = prop.getProperty("browser");
		}else{
			browserName = browser;
		}
		
		driver = basePage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
	}
 
		@Test(priority=1)
		@Description("verify LoginPage Title Test...")
		@Severity(SeverityLevel.NORMAL)
		public void verifyLoginPageTitleTest() throws InterruptedException{
			Thread.sleep(6000);
			String title = loginPage.pageGetTitle();
			System.out.println("Login page title is: " + title); 
			Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE);
		}
	
		@Test(priority=2)
		@Description("verify signup link test...")
		@Severity(SeverityLevel.CRITICAL)
		public void verifySignUpLinkTest(){
			Assert.assertTrue(loginPage.checkSignUpLink());
		}
		@Test(priority=3)
		@Description("Verify login test...")
		@Severity(SeverityLevel.BLOCKER)
		public void loginTest(){
			HomePage homePage = loginPage.doLogIn(userCred);
//			String accountname = homePage.getUserAccountName();
//			Assert.assertEquals(accountname, prop.getProperty(accountname));
			
		}
//		@DataProvider
//		public Object[][] getLoginInvalidData(){
//			Object data[][]={
//					{"test1111@gmail.com", "test12"},
//				 	{"test2@gmail.com", "  "},
//					{"  ", "test12345"},
//					{" ", "  "}
//			};
//			return data;
//			
//		}
//		
//		
//	    @Test(priority=4, dataProvider= "getLoginInvalidData")
//		public void login_invalidTestCase(String username, String pwd){
//	    userCred.setAppUsername(username);
//	    userCred.setAppPassword(pwd);
//	    loginPage.doLogIn(userCred);
//		Assert.assertTrue(loginPage.checkLoginErrorMessage());
//	}
	
	
	@AfterTest
	public void tearDown(){
		driver.close();
	}
}
