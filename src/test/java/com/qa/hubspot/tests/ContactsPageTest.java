package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.ContactsPage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;
import com.qa.hubspot.util.ExcelUtil;

public class ContactsPageTest {
	BasePage basePage; 
	Properties prop;
	WebDriver driver;
	LoginPage loginPage;
	Credentials userCred;
	HomePage homePage;
	ContactsPage contactsPage;
	
	@BeforeMethod
	public void setup(){
		basePage = new BasePage();
		prop = basePage.init_properties();
		String browserName = prop.getProperty("browser");
		driver = basePage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		homePage = loginPage.doLogIn(userCred);
		contactsPage = homePage.goToContactsPage();
		
	}
	
	@Test(priority=1)
	public void VerifyContactsPageTitle(){
		String title = contactsPage.getContactsPageTitle();
		System.out.println("Contacts page title is: " + title);
		Assert.assertEquals(title, contactsPage.getContactsPageTitle());
	}
	
	@DataProvider
	public Object[][] getContactsTestData(){
		Object[][]data = ExcelUtil.getTestData(AppConstants.CONTACTS_SHEET_NAME);
		return data;
	}
	
	
	@Test(priority=2, dataProvider = "getContactsTestData")
	public void createContactsTest(String firstName, String lastName, String email, String jobTitle){
		contactsPage.createNewContacts(firstName, lastName, email, jobTitle);
	}
	
	
	
	
	
}
