package com.arrival.appium.android;

/**
 * Created by tecdesdev on 16/06/15.
 */

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WebChrome {

    private static String url = "http://127.0.0.1:4723/wd/hub";
    private static AndroidDriver wd;

    //@DataProvider(name = "driver",parallel = true)
    @DataProvider(name = "driver")
    public Object[][] createDriver() {

        AndroidDriver tempWD1 = null;
        AndroidDriver tempWD2 = null;
        AndroidDriver tempWD3 = null;

        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(CapabilityType.PLATFORM, Platform.ANDROID);
            capabilities.setCapability(CapabilityType.VERSION, "4.4.2");
            capabilities.setCapability(CapabilityType.BROWSER_NAME,"chrome");
            capabilities.setCapability("udid", "20715382");
            capabilities.setCapability("deviceName", "Note3");
            tempWD1 = new AndroidDriver(new URL("http://127.0.0.1:4444/wd/hub"), capabilities);
            tempWD1.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(CapabilityType.PLATFORM, Platform.ANDROID);
            capabilities.setCapability(CapabilityType.VERSION, "4.4.2");
            capabilities.setCapability(CapabilityType.BROWSER_NAME,"chrome");
            capabilities.setCapability("udid", "06510ebe170ef362");
            capabilities.setCapability("deviceName", "G Flex");
            tempWD2 = new AndroidDriver(new URL("http://127.0.0.1:4444/wd/hub"), capabilities);
            tempWD2.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(CapabilityType.PLATFORM, Platform.ANDROID);
            capabilities.setCapability(CapabilityType.VERSION, "4.4.2");
            capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
            capabilities.setCapability("udid", "018f5cf89c8c39ca");
            capabilities.setCapability("deviceName", "G2");
            tempWD3 = new AndroidDriver(new URL("http://127.0.0.1:4444/wd/hub"), capabilities);
            tempWD3.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Object[][] tempDriver = new Object[][]{
                                                       {"20715382", tempWD1}
                                                      ,{"06510ebe170ef362", tempWD2}
                                                      ,{"018f5cf89c8c39ca", tempWD3}
        };

        return tempDriver;
    }

    @BeforeTest
    public void setup() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PLATFORM, Platform.ANDROID);
        capabilities.setCapability(CapabilityType.VERSION, "4.4.2");
        capabilities.setCapability(CapabilityType.BROWSER_NAME,"CHROME");
        capabilities.setCapability("udid", "20715382");
        capabilities.setCapability("deviceName", "Note3");
        wd = new AndroidDriver(new URL("http://127.0.0.1:5555/wd/hub"), capabilities);
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }


    //@Test(dataProvider = "driver", description = "Add a new City in App WetterInfo")
   // public void getGoogle(String udid, AndroidDriver ad) {
    @Test
    public void getGoogle() {
      //  wd = ad;
        pause(500);
        // And now use this to visit Google
        wd.get("http://www.google.com");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");
        pause(1000);
        // Find the text input element by its name
        WebElement element = wd.findElement(By.name("q"));

        // Enter something to search for
        element.sendKeys("Cheese!");

        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();

        // Check the title of the page
        System.out.println("Page title is: " + wd.getTitle());

        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(wd, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("cheese!");
            }
        });

        // Should see: "cheese! - Google Search"
        System.out.println("Page title is: " + wd.getTitle());
    }

    @AfterTest
    public void tearDown() throws Exception {
        wd.quit();
    }

    private void pause(int timeInmilsec)
    {
        try{
            Thread.sleep(timeInmilsec);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
