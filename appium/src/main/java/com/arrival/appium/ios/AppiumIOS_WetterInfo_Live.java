package com.arrival.appium.ios;

/**
 * Created by Aaron Kutekidila on 21/05/15.
 */


import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

public class AppiumIOS_WetterInfo_Live {
    private IOSDriver driver;

    private WebElement row;

    @BeforeTest
    public void setUp() throws Exception {
        // set up appium
        // File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File("/Users/hendriklohrum/Desktop/Appium/");
        File app = new File(appDir, "wetterInfo.ipa"); //wetter.info.app/AppiumIOS_WetterInfo_Live.app


        /*DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.1");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone Simulator");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
    	//capabilities.setCapability("bundleid", "de.telekom.NiederschlagsRadar");
        capabilities.setCapability("app", app.getAbsolutePath());
        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);*/
        //Run in a iOS-Devices

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.1.1");
        capabilities.setCapability("udid", "22d337b9a379a10c6a03fff6ef8c4f25d09defc7");
        capabilities.setCapability("bundleId", "de.telekom.intern.NiederschlagsRadar");
        capabilities.setCapability("app", app.getAbsolutePath());
    }

    @AfterTest
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void test1() {
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
