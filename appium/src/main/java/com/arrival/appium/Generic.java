package com.arrival.appium;

/**
 * @autor Aaron Kutekidila
 * @creat 21.056.2015
 */

import io.appium.java_client.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Generic {

    /*
     *
     */
    public Generic() {
    }


	/*
     * Ab hier beginnen die Genrische Funktion, welche für jeden IOS-Automeded-Test
	 * gebraucht werden.
	 */

    public void swipeRightToLeft(MobileElement e, int swipeCounter) {
        for (int i = 0; i <= swipeCounter; i++) {
            e.swipe(SwipeElementDirection.LEFT, 500);
        }
    }


    /*
     *
     */
    public void swipeLeftToRigt(MobileElement e, int swipeCounter) {
        for (int i = 0; i <= swipeCounter; i++) {
            e.swipe(SwipeElementDirection.RIGHT, 500);
        }
    }


    /*
     *
     */
    public void scrollDown(AppiumDriver dr, MobileElement e, int scrollCounter) {
        Point startCenter = getCenter(e);
        Point endCenter = getCenter(e);
        Dimension dimensions = e.getSize();
        int endY = dimensions.getHeight() / 4;

        for (int i = 0; i < scrollCounter; i++) {
            dr.swipe(startCenter.x, startCenter.y, endCenter.x, endY, 1000);
        }
    }


    /*
     *
     */
    public void scrollUp(AppiumDriver dr, MobileElement e, int scrollCounter) {
        Point startCenter = getCenter(e);
        Point endCenter = getCenter(e);
        Dimension dimensions = e.getSize();
        int endY = dimensions.getHeight() - dimensions.getHeight() / 4;

        for (int i = 0; i < scrollCounter; i++) {
            dr.swipe(startCenter.x, startCenter.y, endCenter.x, endY, 1000);
        }
    }


    /*
     *
     */
    public void zoomIn(WebDriver wb, WebElement e) {
        TouchAction ta1 = new TouchAction((MobileDriver) wb);
        TouchAction ta2 = new TouchAction((MobileDriver) wb);
        MultiTouchAction ma = new MultiTouchAction((MobileDriver) wb);
        Point eCenter = getCenter(e);

        int moveTa1X = eCenter.x - e.getSize().width / 2;
        int moveTa1Y = eCenter.y;

        int moveTa2X = eCenter.x + e.getSize().width / 2;
        int moveTa2Y = eCenter.y;


        ta1.longPress(eCenter.x, eCenter.y).moveTo(moveTa1X, moveTa1Y).release();
        ta2.longPress(eCenter.x, eCenter.y).moveTo(moveTa2X, moveTa2Y).release();

        ma.add(ta1);
        ma.add(ta2);
        ma.perform();
    }


    public void zoomOut(AppiumDriver dr, WebElement e) {
        TouchAction ta1 = new TouchAction(dr);
        TouchAction ta2 = new TouchAction(dr);
        MultiTouchAction ma = new MultiTouchAction(dr);
        Point eCenter = getCenter(e);

        int moveTa1X = eCenter.x - e.getSize().width / 2;
        int moveTa1Y = eCenter.y;

        int moveTa2X = eCenter.x + e.getSize().width / 2;
        int moveTa2Y = eCenter.y;


        ta1.longPress(moveTa1X, moveTa1Y).moveTo(eCenter.x, eCenter.y).release();
        ta2.longPress(moveTa2X, moveTa2Y).moveTo(eCenter.x, eCenter.y).release();

        ma.add(ta1);
        ma.add(ta2);
        ma.perform();
    }


    /*
     * slider values can be string representations of numbers between 0 and 1
     *  e.g., "0.1" is 10%, "1.0" is 100%
     */
    public void slider(MobileElement e, double slideTo) {
        WebElement slider = (WebElement) e;
        slider.sendKeys(String.valueOf(slideTo));
    }


    /*
     *
     */
    public void pullToReload(AppiumDriver dr, WebElement e) {
        /*Point startCenter = getCenter(e);
		Point endCenter = getCenter(e);
		Dimension dimensions = e.getSize();
		int endY = dimensions.getHeight()/4;

		dr.swipe(startCenter.x, startCenter.y, endCenter.x, endY, 1000);*/


        Point eCenter = getCenter(e);
        TouchAction ta = new TouchAction(dr);
        ta
                .longPress(eCenter.x, eCenter.y)
                .moveTo(eCenter.x, eCenter.y - e.getSize().height / 2)
                .release()
                .perform();
    }

    /*
     *
     */
    public void sendTextEnter(WebElement e, String text) {
        e.sendKeys(text);
        e.submit();
    }

    /**
     * @return : retur the Center of a element as Point
     * @para element : WebElement
     */
    private Point getCenter(WebElement element) {
        Point upperLeft = element.getLocation();
        Dimension dimensions = element.getSize();
        return new Point(upperLeft.getX() + dimensions.getWidth() / 2, upperLeft.getY() + dimensions.getHeight() / 2);
    }
}

