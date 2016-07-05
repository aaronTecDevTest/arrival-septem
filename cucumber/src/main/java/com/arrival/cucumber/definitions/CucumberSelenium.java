package com.arrival.cucumber.definitions;

import cucumber.api.java.en.Given;

/**
 * Created by Aaaron Pichou Kutekidila on 05.07.2016.
 */
public class CucumberSelenium {

    @Given("openURL @url: (\\w+)")
    public void assertElementText(String url){
        System.out.println("Test:" + url);
    }

}
