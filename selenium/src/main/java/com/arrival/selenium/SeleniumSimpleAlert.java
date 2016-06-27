package com.arrival.selenium;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium. firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.security.UserAndPassword;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.bouncycastle.cms.RecipientId.password;

/**
 * Created by kutekidila on 16.06.2016.
 */
public class SeleniumSimpleAlert {

    WebDriver driver;

    public SeleniumSimpleAlert() {
    }

    public static void main(String[] args) {
        SeleniumSimpleAlert simpleTest = new SeleniumSimpleAlert();
        simpleTest.setUpConfi();
        simpleTest.runTest();
        simpleTest.closeBrowser();
    }

    public void setUpConfi() {

        // Create a new instance of the Firefox driver
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("network.http.phishy-userpass-length", 255);
        profile.setPreference("network.automatic-ntlm-auth.trusted-uris", "https://stagingportal.bmptest.de/");
        profile.setPreference("intl.accept_languages","it");
        driver = new FirefoxDriver(profile);
    }

    public void runTest() {

        // And now use this to visit Google
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");
       // driver.get("https://stagingportal.bmptest.de/");
//        Wait 10 seconds till alert is present
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        driver.manage().window().maximize();
//        driver.get("https://stagingportal.bmptest.de");
//        new SendKeys("myUser");
//        new SendKeys("{TAB}");
//        new SendKeys("MyPassword");
//        new SendKeys("~"); // Enter
//        alert.accept();
//
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
//
//        //alert.authenticateUsing(new UserAndPassword("t34u", " 151012$!"));
//        System.out.println(alert.getText());
//        alert.sendKeys("test");
//        alert.setCredentials(new UserAndPassword("t34u", " 151012$!"));
//        alert.accept();

//        driver.switchTo().alert();
//        //Selenium-WebDriver Java Code for entering Username & Password as below:
//        driver.findElement(By.id("userID")).sendKeys("userName");
//        driver.findElement(By.id("password")).sendKeys("myPassword");
//        driver.switchTo().alert().accept();
//        driver.switchTo().defaultContent();
    }

    public void closeBrowser() {

        //Close the browser
        driver.close();
        driver.quit();
    }
}
