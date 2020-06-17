package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions; 
import org.openqa.selenium.safari.SafariDriver; 
import io.github.bonigarcia.wdm.WebDriverManager;
  
  
	public class BasePage {

	//public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	
	public static synchronized WebDriver getDriver(){
		return tldriver.get();
	}

	public WebDriver init_driver(String browserName) {
		System.out.println("Browser name is: " + browserName);
		optionsManager = new OptionsManager(prop);

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			
			tldriver.set(new ChromeDriver(optionsManager.getChromeOptions()));

		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			
			tldriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			

		} else if (browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
			//driver = new SafariDriver();
			tldriver.set(new SafariDriver());
		} else {
			System.out.println("Browser name " + browserName + " was not found, please use the correct browser...");
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		// driver.get(url);

		return getDriver();

	}
	
		public  Properties init_properties(){
			prop = new Properties();
			String path = ".\\src\\main\\java\\com\\qa\\hubspot\\config\\config.properties";
			try {
				FileInputStream  ip = new FileInputStream(path);
				prop.load(ip);
				
			} catch (FileNotFoundException e) {
				
				System.out.println("Some issue with Config Properties... please double check your Config");
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			return prop;
		}
	
		/**
		 * take ScreenShot
		 */
		
		public String getScreenshot(){
			File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
			String path = System.getProperty("user.dir") + "\\Screenshots\\" + System.currentTimeMillis() + ".png";
			File destination = new File(path);
			
			try {
				FileUtils.copyFile(src, destination);
			} catch (IOException e) {
				System.out.println("taking Screen shot failed");
			}
			return path;
			
		}
	

}
