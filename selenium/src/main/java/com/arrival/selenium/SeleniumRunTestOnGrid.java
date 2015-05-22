package com.arrival.selenium;

/**
 * Created by Aaron on 22.05.2015.
 **/
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.testng.Assert;
import org.testng.annotations.*;


public class SeleniumRunTestOnGrid {

    //Declaration  var
    DesiredCapabilities capabilities;
    WebDriver driver;
    String baseURL;
    String nodeURL;

    public static void main(String[] args) {
        try {
            SeleniumRunTestOnGrid simpleGrid = new SeleniumRunTestOnGrid();
            simpleGrid.setUp();
            simpleGrid.runTestOnFF();
            simpleGrid.runTestOnFF();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SeleniumRunTestOnGrid(){
    }

    @BeforeTest
    public void setUp() throws MalformedURLException {
        baseURL = "https://www.google.com";
    }


    @Test
    public void runTestOnCH() throws Exception {
        nodeURL = "http://localhost:5556/wd/hub";

        //Create a new instance of Capabilities with Chrome
        capabilities = DesiredCapabilities.chrome();
        capabilities.setBrowserName("chrome");
        capabilities.setPlatform(Platform.WINDOWS);

        // Create a new instance of the RemoteWebDriver
        // with a firefox DesiredCapability and URL
        driver = new RemoteWebDriver(new URL(nodeURL), capabilities);

        driver.get(baseURL);
        Thread.sleep(2000);
        Assert.assertEquals("Google", driver.getTitle());
    }


    @Test
    public void runTestOnFF() throws Exception {
        nodeURL = "http://localhost:5555/wd/hub";

        //Create a new instance of Capabilities with Firefox
        capabilities = DesiredCapabilities.firefox();
        capabilities.setBrowserName("firefox");
        capabilities.setPlatform(Platform.WINDOWS);

        // Create a new instance of the RemoteWebDriver
        // with a firefox DesiredCapability and URL
        driver = new RemoteWebDriver(new URL(nodeURL), capabilities);

        driver.get(baseURL);
        Thread.sleep(2000);
        Assert.assertEquals("Google", driver.getTitle());
   }


    @AfterTest
    public void closeBrowser(){
        //Close the browser
        driver.close();
        driver.quit();
    }
}
