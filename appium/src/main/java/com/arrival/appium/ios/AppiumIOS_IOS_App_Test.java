package com.arrival.appium.ios;

//import org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

//import org.openqa.selenium.remote.server.handler.interactions.touch.Scroll;


public class AppiumIOS_IOS_App_Test {

    public static int testcases_passed = 0;
    public static int testcases_failed = 0;
    public WebDriver driver = null;
    int serial_no = 0;
    String appName = "App_iOS";
    WebElement url;


    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        AppiumIOS_IOS_App_Test testcases = new AppiumIOS_IOS_App_Test();

        // set up appium and launch app
        testcases.setUp();

        // email signup
        testcases.emailSignup();

        // search
        testcases.search();

        // logout
        testcases.logOut();

    }

    public void setUp() {

        try {

            System.out.println("Launching App");

            // two ways to launch app :
            // you can set up appium in following manner - if you want to run on simulator
            File appDir = new File("/Users/Desktop/smriti/iPhoneSimulator"); //iphoneSimulator is the folder where .app is located
            File app = new File(appDir, "App_iOS.app");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(CapabilityType.BROWSER_NAME, "iOS");
            capabilities.setCapability(CapabilityType.VERSION, "8.1");
            capabilities.setCapability(CapabilityType.PLATFORM, "Mac");
            capabilities.setCapability("app", app.getAbsolutePath());
            driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);


            //or
            //you can set up appium in following manner - if you want to run on real device
            /*DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("device", "iPhone");
			capabilities.setCapability("udid", "80644d3a823bb12fe012ea3e54223e00feef8097");
			capabilities.setCapability("bundleid", "com.example.appiumiphonetest");
			capabilities.setCapability("ipa", "App_iOS.ipa");
			driver = new RemoteWebDriver( new URL("http://127.0.0.1:4723/wd/hub"), capabilities); */


            System.out.println("App launched");
            SuccessMessage();
            Thread.sleep(5000);

        } catch (Exception e) {
            System.out.println("App launch fail");
            FailureMessage();
        }
    }


    public void emailSignup() throws InterruptedException {

        try {

            System.out.println("\nEmail Signup");
            Thread.sleep(5000);

            // login button
            WebElement login_button = driver.findElement(By
                                                                 .xpath("//window[1]/button[3]"));
            Assert.assertTrue("Email signup Fail", login_button.isDisplayed());
            login_button.click();
            Thread.sleep(3000);

            // signup section
            driver.findElement(By.name("Sign Up")).click();
            Thread.sleep(3000);

            // email field
            WebElement signup_email = driver.findElement(By
                                                                 .xpath("//window[1]/scrollview[1]/textfield[1]"));
            signup_email.click();
            Thread.sleep(3000);
            signup_email.sendKeys("abc@xyz.com");
            Thread.sleep(3000);

            // password field
            WebElement signup_password = driver.findElement(By
                                                                    .xpath("//window[1]/scrollview[1]/secure[1]"));
            signup_password.click();
            signup_password.sendKeys("password");
            Thread.sleep(3000);

            // signup
            driver.findElement(By.name("Create Account")).click();
            SuccessMessage();
            Thread.sleep(5000);

        } catch (AssertionError ex) {
            //AppendMessageWithTags(ex);
            System.err.println(ex);
        }

    }

    public void search() throws InterruptedException {

        System.out.println("\nSearch");

        // enter keyword
        try {
            url = driver.findElement(By.xpath("//window[1]/textfield[1]"));
            Assert.assertTrue("Search fail - Could not type in omnibar",
                                     url.isDisplayed());
            url.click();

            // search something
            url.sendKeys("web");
            driver.findElement(By.xpath("//window[2]/UIAKeyboard[1]/button[4]"))
                    .click();
            Thread.sleep(6000);
            SuccessMessage();

        } catch (AssertionError ex) {
            //AppendMessageWithTags(ex);
            System.err.println(ex);
        }
    }


    public void logOut() throws InterruptedException {

        //appendSerialNumber();
        System.out.println("\nLog Out Check");
        System.out.println("Logging out");
        try {
            //open menu to logout
            openMenu();

            //log out
            WebElement log_out = driver.findElement(By
                                                            .xpath("//window[1]/tableview[1]/cell[1]"));
            Assert.assertTrue("Log out fail", log_out.isDisplayed());
            log_out.click();
            Thread.sleep(3000);
            driver.findElement(By.name("Yes")).click();
            System.out.println("Logged Out ");
            SuccessMessage();

        } catch (AssertionError ex) {
            //AppendMessageWithTags(ex);
            System.err.println(ex);
        }
    }


    public void openMenu() throws InterruptedException {

        try {
            WebElement menu = driver.findElement(By
                                                         .name("btn menu normal"));
            Assert.assertTrue("Could not open menu", menu.isDisplayed());
            menu.click();
            Thread.sleep(3000);
        } catch (AssertionError ex) {
            //AppendMessageWithTags(ex);
            System.err.println(ex);
        }
    }


    public void FailureMessage() {
        System.out.println("Failure");
        testcases_failed++;
    }

    public void SuccessMessage() {
        System.out.println("Success");
        testcases_passed++;
    }

}