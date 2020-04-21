package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

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

	@Epic("Epic - 102 : create Home page features")
	@Feature("US - 502 : create test for Home page on hubspot")
	public class HomePageTest {
	
	BasePage basePage; 
	Properties prop;
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	Credentials userCred;
	
	@BeforeTest
	public void setup() throws InterruptedException{
		basePage = new BasePage();
		prop = basePage.init_properties();
		String browserName = prop.getProperty("browser");
		driver = basePage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		homePage = loginPage.doLogIn(userCred);
		Thread.sleep(5000);
	}
	
		@Test(priority=1)
		@Description("Verify home page title test ...")
		@Severity(SeverityLevel.NORMAL)
		public void verifyHomePageTitleTest(){
			String title = homePage.getHomePageTitle();
			System.out.println("Home page title is: " + title );
			Assert.assertEquals(title, AppConstants.HOME_PAGE_TITLE);
		}
		
		@Test(priority=2)
		@Description("Verify home page header test ...")
		@Severity(SeverityLevel.NORMAL)
		public void verifyHomePageHeaderTest(){
			String header = homePage.getHomePageHeader();
			System.out.println("Home page Header is: " +  header);
			Assert.assertEquals(header, AppConstants.HOME_PAGE_HEADER);
		}
		
		@Test(priority=3)
		@Description("Verify User account name test ...")
		@Severity(SeverityLevel.CRITICAL)
		public void verifyUserAccountName(){
			String accountname = homePage.getUserAccountName();
			System.out.println("Login Account name is: " + accountname);
			Assert.assertEquals(accountname, AppConstants.USER_ACCOUNT_NAME); 
		}
	
		
	
	@AfterTest
	public void tearDown(){
		driver.close();
	}
}
