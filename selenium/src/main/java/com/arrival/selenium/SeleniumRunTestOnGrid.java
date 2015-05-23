package com.arrival.selenium;

/**
 * Created by Aaron on 22.05.2015.
 **/
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.testng.Assert;
import org.testng.annotations.*;


public class SeleniumRunTestOnGrid {

    //Declaration  var
    DesiredCapabilities capabilitiesGo;
    WebDriver driverGo;

    DesiredCapabilities capabilitiesFa;
    WebDriver driverFa;

    String baseURLGo;
    String baseURLFa;
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
        baseURLGo = "https://www.google.com";
        baseURLFa = "https://www.facebook.com";
    }


    @Test
    public void runTestOnCH() throws Exception {
        nodeURL = "http://localhost:5556/wd/hub";

        //Create a new instance of Capabilities with Chrome
        capabilitiesGo = DesiredCapabilities.chrome();
        capabilitiesGo.setBrowserName("chrome");
        capabilitiesGo.setPlatform(Platform.WINDOWS);

        // Create a new instance of the RemoteWebDriver
        // with a firefox DesiredCapability and URL
        driverGo = new RemoteWebDriver(new URL(nodeURL), capabilitiesGo);

        driverGo.get(baseURLGo);
        Thread.sleep(5000);
        Assert.assertEquals("Google", driverGo.getTitle());
        driverGo.close();
    }


    @Test
    public void runTestOnFF() throws Exception {
        nodeURL = "http://localhost:5555/wd/hub";

        //Create a new instance of Capabilities with Firefox
        capabilitiesFa = DesiredCapabilities.chrome();
        capabilitiesFa.setBrowserName("chrome");
        capabilitiesFa.setPlatform(Platform.WINDOWS);

        // Create a new instance of the RemoteWebDriver
        // with a firefox DesiredCapability and URL
        driverFa = new RemoteWebDriver(new URL(nodeURL), capabilitiesFa);

        driverFa.get(baseURLFa);
        Thread.sleep(5000);
        Assert.assertEquals("Google", driverFa.getTitle());
        driverFa.close();
   }


    //@AfterTest
    public void closeBrowser(){
        //Close the browser
    }
}
