package com.arrival.appium;

/**
 * Created by tecdesdev on 28/05/15.
 */

/**
 * This Class is the Main-Class to run a SeleniumHub and to configure  AppiumSever as a Node
 * in SeleniumHub
 */
public class Main {

    public static void main(String[] args) {
        SeleniumHub hub = new SeleniumHub();
        hub.startHub();

    }
}
