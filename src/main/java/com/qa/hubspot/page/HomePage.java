package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.ElementUtil;

public class HomePage extends BasePage {
	WebDriver driver;
	ElementUtil elementUtil;

	By header = By.cssSelector("h1.private-page__title");
	By accountname = By.xpath("//span[@class='account-name ']");
	
	By mainContactsList = By.id("nav-primary-contacts-branch");
	By childContactsLink = By.id("nav-secondary-contacts");

	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public String getHomePageTitle() {
		return elementUtil.doGetPageTitle();
	}

	public String getHomePageHeader() {
		return elementUtil.doGetText(header);
	}

	public String getUserAccountName(){
		return elementUtil.doGetText(accountname);
	}

	public void clickOnContacts(){
		elementUtil.waitForElementPresent(mainContactsList);
		elementUtil.doClick(mainContactsList);
		
		elementUtil.waitForElementPresent(childContactsLink);
		elementUtil.doClick(childContactsLink);
	}
	
	public ContactsPage goToContactsPage(){
		clickOnContacts();
		return new ContactsPage(driver);
	}
	
	
}
