package com.arrival.selenium;

/**
 * Created by tecdesdev on 26/05/15.
 */

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SeleniumSimpleSafariTest {

private WebDriver driver = null;

public SeleniumSimpleSafariTest() {

}


public static void main(String[] args) {
	SeleniumSimpleSafariTest simpleSafariTest = new SeleniumSimpleSafariTest();
	simpleSafariTest.createDriver();
	simpleSafariTest.runTest();
	simpleSafariTest.quitDriver();
}

private static boolean isSupportedPlatform() {
	Platform current = Platform.getCurrent();
	return Platform.MAC.is(current) || Platform.WINDOWS.is(current);
}


public void createDriver() {
	if (isSupportedPlatform()) {
		driver = new SafariDriver();
	}
}

public void quitDriver() {
	driver.quit();
}

public void runTest() {
	driver.get("http://www.google.com");
	driver.findElement(By.name("q")).sendKeys("webdriver");
	driver.findElement(By.name("btnG")).click();
	//new WebDriverWait(driver, 3).until(ExpectedConditions.titleIs("webdriver"));
	try {
		Thread.sleep(5000);
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}
