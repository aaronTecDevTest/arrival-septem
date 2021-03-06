package com.arrival.appium.ios;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;
import java.util.List;


//import org.json.JSONObject;

/**
 * <a href="https://github.com/appium/appium">Appium</a> test which runs against a local Appium instance deployed
 * with the 'AppiumIOS_WetterInfo_Live' iPhone project which is included in the Appium source distribution.
 *
 * @author Ross Rowe
 */
public class AppiumIOS_Nativ {

    File appDir = new File("/Users/hendriklohrum/Desktop/Appium");
    File app = new File(appDir, "AppiumIOS_WetterInfo_Live.app");//wetter.info.ipa/AppiumIOS_WetterInfo_Live.app
    //private AppiumDriver driver;
    private IOSDriver driver;
    public WebElement row;

    @BeforeTest
    public void setUp() throws Exception {
        // set up appium
        // File classpathRoot = new File(System.getProperty("user.dir"));

        /*
        DesiredCapabilities capabilities = new DesiredCapabilities();
     
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone Simulator");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.2");
        capabilities.setCapability("udid", "22d337b9a379a10c6a03fff6ef8c4f25d09defc7");
		capabilities.setCapability("bundleid", "com.example.apple-samplecode.AppiumIOS_WetterInfo_Live");
        capabilities.setCapability("app", app.getAbsolutePath());
        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);*/


        //Run in a iOS-Devices
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone-Test-6");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.2");
        capabilities.setCapability("udid", "22d337b9a379a10c6a03fff6ef8c4f25d09defc7");
        capabilities.setCapability("bundleid", "com.example.apple-samplecode.AppiumIOS_WetterInfo_Live");
        capabilities.setCapability("app", app.getAbsolutePath());
        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @AfterTest
    public void tearDown() throws Exception {
        Thread.sleep(10000);
        driver.quit();
    }

    @Test
    public void test() throws Exception {
        Assert.assertTrue(true);

    }

    private void openMenuPosition(int index) {
        //populate text fields with two random number
        //MobileElement table = new MobileElement((RemoteWebElement)driver.findElementByClassName("UIATableView"), driver);
        MobileElement table = (MobileElement) driver.findElementByClassName("UIATableView");
        row = table.findElementsByClassName("UIATableCell").get(index);
        row.click();
    }

    private Point getCenter(WebElement element) {

        Point upperLeft = element.getLocation();
        Dimension dimensions = element.getSize();
        return new Point(upperLeft.getX() + dimensions.getWidth() / 2, upperLeft.getY() + dimensions.getHeight() / 2);
    }

    @Test
    public void testFindElement() throws Exception {
        //first view in AppiumIOS_WetterInfo_Live is a table
        //MobileElement table = new MobileElement((RemoteWebElement)driver.findElementByClassName("UIATableView"), driver);
        MobileElement table = (MobileElement) driver.findElementByClassName("UIATableView");
        Assert.assertNotNull(table);
        //is number of cells/rows inside table correct
        List<MobileElement> rows = table.findElementsByClassName("UIATableCell");
        Assert.assertEquals(12, rows.size());
        //is first one about buttons
        Assert.assertEquals("Buttons, Various uses of UIButton", rows.get(0).getAttribute("name"));
        //navigationBar is not inside table
        WebElement nav_bar = null;
        try {
            nav_bar = table.findElementByClassName("UIANavigationBar");
        } catch (NoSuchElementException e) {
            //expected
        }
        Assert.assertNull(nav_bar);
        //there is nav bar inside the app
        driver.getPageSource();
        nav_bar = driver.findElementByClassName("UIANavigationBar");
        Assert.assertNotNull(nav_bar);
    }


    @Test
    public void test_location() {
        //get third row location
        row = (WebElement) driver.findElementsByClassName("UIATableCell").get(2);

        Assert.assertEquals(0, row.getLocation().getX());
        Assert.assertEquals(152, row.getLocation().getY());
    }

    @Test
    public void testScreenshot() {
        //make screenshot and get is as base64
        WebDriver augmentedDriver = new Augmenter().augment(driver);
        String screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BASE64);

        Assert.assertNotNull(screenshot);
        //make screenshot and save it to the local filesystem
        File file = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
        // Files.write(appDir, file.,Charset.forName("UTF-8") ,StandardOpenOption.WRITE);

        Assert.assertNotNull(file);
    }

    @Test
    public void testTextFieldEdit() {
        //go to the text fields section
        openMenuPosition(2);
        WebElement text_field = (WebElement) driver.findElementsByClassName("UIATextField").get(0);
        //get default/empty text
        String default_val = text_field.getAttribute("value");
        //write some random text to element
        String rnd_string = RandomStringUtils.randomAlphanumeric(6);
        text_field.sendKeys(rnd_string);
        Assert.assertEquals(rnd_string, text_field.getAttribute("value"));
        //send some random keys
        String rnd_string2 = RandomStringUtils.randomAlphanumeric(6);
        Actions swipe = new Actions(driver).sendKeys(rnd_string2);
        swipe.perform();
        //check if text is there
        Assert.assertEquals(rnd_string + rnd_string2, text_field.getAttribute("value"));
        //clear
        text_field.clear();
        //check if is empty/has default text
        Assert.assertEquals(default_val, text_field.getAttribute("value"));
    }

    @Test
    public void testAlertInteraction() {
        //go to the alerts section
        openMenuPosition(10);

        //trigger modal alert with cancel & ok buttons
        List<WebElement> triggerOkCancel = driver.findElementsByAccessibilityId("Show OK-Cancel");
        triggerOkCancel.get(1).click();
        Alert alert = driver.switchTo().alert();
        //check if title of alert is correct
        Assert.assertEquals("UIAlertView <Alert message>", alert.getText());
        alert.accept();
    }

    @Test
    public void testScroll() {
        //scroll menu
        //get initial third row location
        row = (WebElement) driver.findElementsByClassName("UIATableCell").get(2);
        Point location1 = row.getLocation();
        Point center = getCenter(row);
        //perform swipe gesture
        driver.swipe(center.getX(), center.getY(), center.getX(), center.getY() - 20, 1000);
        //get new row coordinates
        Point location2 = row.getLocation();
        Assert.assertEquals(location1.getX(), location2.getX());
        Assert.assertNotSame(location1.getY(), location2.getY());
    }

    @Test
    public void testSlider() {
        //go to controls
        openMenuPosition(1);
        //get the slider
        WebElement slider = driver.findElementByClassName("UIASlider");
        Assert.assertEquals("50%", slider.getAttribute("value"));
        Point sliderLocation = getCenter(slider);
        driver.swipe(sliderLocation.getX(), sliderLocation.getY(), sliderLocation.getX() - 100, sliderLocation.getY(), 1);
        Assert.assertEquals("0%", slider.getAttribute("value"));
    }

    @Test
    public void testSessions() throws Exception {
        HttpGet request = new HttpGet("http://localhost:4723/wd/hub/sessions");
        HttpClient httpClient = new DefaultHttpClient();//DefaultHttpClient();
        HttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(EntityUtils.toString(entity));

        String sessionId = driver.getSessionId().toString();
        Assert.assertEquals(jsonObject.get("sessionId"), sessionId);
    }

    @Test
    public void testSize() {
        Dimension table = driver.findElementByClassName("UIATableView").getSize();
        Dimension cell = (Dimension) driver.findElementsByClassName("UIATableCell").get(0);
        Assert.assertEquals(table.getWidth(), cell.getWidth());
        Assert.assertNotSame(table.getHeight(), cell.getHeight());
    }

    @Test
    public void testSource() {
        //get main view soruce
        String source_main = driver.getPageSource();
        Assert.assertTrue(source_main.contains("UIATableView"));
        Assert.assertTrue(source_main.contains("TextFields, Uses of UITextField"));

        //got to text fields section
        openMenuPosition(2);
        String source_textfields = driver.getPageSource();
        Assert.assertTrue(source_textfields.contains("UIAStaticText"));
        Assert.assertTrue(source_textfields.contains("TextFields"));

        Assert.assertNotSame(source_main, source_textfields);
    }
}
