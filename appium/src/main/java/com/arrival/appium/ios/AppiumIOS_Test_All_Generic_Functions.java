package com.arrival.appium.ios;

import com.arrival.appium.Generic;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

//import org.json.JSONObject;

/**
 * <a href="https://github.com/appium/appium">Appium</a> test which runs against a local Appium instance deployed
 * with the 'AppiumIOS_WetterInfo_Live' iPhone project which is included in the Appium source distribution.
 *
 * @author Ross Rowe
 */
public class AppiumIOS_Test_All_Generic_Functions extends Generic {

//private AppiumDriver driver;
private IOSDriver driver;

@Before
public void setUp() throws Exception {
	// set up appium
	File appDir = new File("/Users/hendriklohrum/Desktop/Appium");
	File app = new File(appDir, "UICatalog_new.app");
	    
	    /*
	     //Run in a Simulator
	    DesiredCapabilities capabilities = new DesiredCapabilities();
	    capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
	    capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.1");
	    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone Simulator");
	    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
	    capabilities.setCapability("app", app.getAbsolutePath());
	    driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);*/




	//Run in a iOS-Devices
	DesiredCapabilities capabilities = new DesiredCapabilities();
	capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
	capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone6");
	capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.1.2");
	capabilities.setCapability("udid", "22d337b9a379a10c6a03fff6ef8c4f25d09defc7");
	capabilities.setCapability("bundleid", "com.example.apple-samplecode.AppiumIOS_WetterInfo_Live");
	capabilities.setCapability("app", app.getAbsolutePath());
	driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
}

@After
public void tearDown() throws Exception {
	Thread.sleep(10000);
	driver.quit();
}


/*
 * Ab hier sind die Testfalle für AppiumIOS_WetterInfo_Live umd die Generiche funktin zufesten.
 *
 */
//@Test
public void openMenuPosition(int index) throws Exception {
	MobileElement table = (MobileElement) driver.findElementByClassName("UIATableView");
	WebElement row = table.findElementsByClassName("UIATableCell").get(index);
	row.click();
}

@Test
public void test() throws Exception {
	//openMenuPosition(15);
	MobileElement table = (MobileElement) driver.findElementByClassName("UIATableView");
	pullToReload(driver, table);
	//MobileElement row =  (MobileElement) table.findElementsByClassName("UIATableCell").get(10);
	//MobileElement slider = (MobileElement) driver.findElementByClassName("UIASlider");
	//slider(slider, 1);
	//swipeLeftToRigt(table, 0);
}
}
