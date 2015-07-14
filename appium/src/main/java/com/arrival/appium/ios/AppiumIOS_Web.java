package com.arrival.appium.ios;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

//import org.json.JSONObject;

/**
 * <a href="https://github.com/appium/appium">Appium</a> test which runs against a local Appium instance deployed
 * with the 'AppiumIOS_WetterInfo_Live' iPhone project which is included in the Appium source distribution.
 *
 * @author Ross Rowe
 */
public class AppiumIOS_Web {

    private AppiumDriver driver;
    //private IOSDriver driver;
    private WebElement row;

    @BeforeTest
    public void setUp() throws Exception {
        // set up appium
        String browser = "Safari";

        //Runtime rt = Runtime.getRuntime();
        //	Process pr = rt.exec("node ios-webkit-debug-proxy-launcher.js -c 22d337b9a379a10c6a03fff6ef8c4f25d09defc7:27753 -d");

        //Run in a iOS-Devices
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone-6-Test");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.3");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, MobileBrowserType.SAFARI);
        capabilities.setCapability("waitForAppScript", "true");
        capabilities.setCapability("autoWebview", "true");
        capabilities.setCapability("udid", "22d337b9a379a10c6a03fff6ef8c4f25d09defc7");
        capabilities.setCapability("app", browser);
        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        Thread.sleep(10000);
    }

    @AfterTest
    public void tearDown() throws Exception {
        Thread.sleep(10000);
        driver.quit();
    }


    @Test
    public void getGoogle() {
        driver.get("http://www.google.com");
        System.out.println("Android Title is: " + driver.getTitle());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void getWetterInfo() {
        driver.get("http://www.wetter.info");
        System.out.println("Android Title is: " + driver.getTitle());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
