package com.arrival.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Aaron on 22.05.2015.
 **/
public class SeleniumSimpleTest {

    WebDriver driver;

    public SeleniumSimpleTest() {

    }

    public static void main(String[] args) {
        SeleniumSimpleTest simpleTest = new SeleniumSimpleTest();
        simpleTest.setUpConfi();
        simpleTest.runTest();
        simpleTest.closeBrowser();
    }

    public void setUpConfi() {

        // Create a new instance of the Firefox driver
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
        driver = new FirefoxDriver();
        // driver = new ChromeDriver();
    }

    public void runTest() {

        // And now use this to visit Google
        driver.get("http://www.google.com");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        // Find the text input element by its name
        WebElement element = driver.findElement(By.name("q"));

        // Enter something to search for
        element.sendKeys("Cheese!");

        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();

        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());

        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("cheese!");
            }
        });

        // Should see: "cheese! - Google Search"
        System.out.println("Page title is: " + driver.getTitle());
    }

    public void closeBrowser() {

        //Close the browser
        driver.close();
        driver.quit();
    }
}
