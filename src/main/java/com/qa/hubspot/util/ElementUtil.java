package com.qa.hubspot.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	WebDriver driver;
	WebDriverWait wait;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, AppConstants.DEFAULT_TIME_OUT);
	}
	
	public boolean waitForElementPresent(By locator){
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		return true;
	}

	
	
	public void waitForTitlePresent(By locator){
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		System.out.println(driver.getTitle());
		
	}
	
	public String doGetPageTitle(){
		try{
		return driver.getTitle();
		}
		catch(Exception e){
			System.out.println("Some Exception got occured while trying to get the title");
		}
		return null;
	}
	

	/**
	 * This method is used to create the WebElement on the basis of By locator
	 * 
	 * @param locator
	 * @return element
	 */
	public WebElement getElement(By locator) {
		WebElement element = null;
		try {
			if(waitForElementPresent(locator));
			element = driver.findElement(locator);
		} catch (Exception e) {
			System.out.println("Some Exception was occured while creating the web element...");
		}
		return element;
	}

	public void doClick(By locator) {
		try {
			getElement(locator).click();
		} catch (Exception e) {
			System.out.println("Some Exception got occured while clicking on the Web Element....");
		}

	}
	public void doSendKeys(By locator, String value){
		try{
		WebElement ele = getElement(locator);
		ele.clear();
		ele.sendKeys(value);
		}
		catch(Exception e){
			System.out.println("Some Exception got occured while entring the values...");
		}
	}
	public boolean doIsDisplayed(By locator){
		try{
		return getElement(locator).isDisplayed();
		}
		catch(Exception e){
			System.out.println("Some Exception got occured....");
		}
		return false;
		
	}
	public String doGetText(By locator){
		try{
		return getElement(locator).getText();
		}
		catch(Exception e){
			System.out.println("Some Exception got occured while getting text...");
		}
		return null;
	}
	

	
}
