package com.arrival.selenium;

/**
 * Created by Aaron on 22.05.2015.
 **/

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * This Class run Testcase on a Remoter (hiere SimpleGri.)
 * Before running this Class make sure to run the SeleniumSimpleGrid.
 **/

public class SeleniumRunTestSimpleGrid {
    WebDriver driver;

    public SeleniumRunTestSimpleGrid() {
    }

    public static void main(String[] args) {
        try {
            SeleniumRunTestSimpleGrid simpleRCTest = new SeleniumRunTestSimpleGrid();
            simpleRCTest.setUpConfi();
            simpleRCTest.runTest();
            simpleRCTest.closeBrowser();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setUpConfi() throws MalformedURLException {

        // Create a new instance of the RemoteWebDriver
        // with a firefox DesiredCapability and URL
        driver = new RemoteWebDriver(new URL("http://localhost:5555/wd/hub"), DesiredCapabilities.firefox());
    }

    public void runTest() throws InterruptedException {

        // RemoteWebDriver does not implement the TakesScreenshot class
        // if the driver does have the Capabilities to take a screenshot
        // then Augmenter will add the TakesScreenshot methods to the instance
        driver.get("http://www.google.com");
        WebDriver augmentedDriver = new Augmenter().augment(driver);
        File screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
        Thread.sleep(2000);
    }

    public void closeBrowser() {

        //Close the browser
        driver.close();
        driver.quit();
    }
}
