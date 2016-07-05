package com.arrival.cucumber.definitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;

/**
 * Created by Aaaron Pichou Kutekidila on 05.07.2016.
 */
public class CucumberSelenium {

    @Given("^openURL url \"([^\"]*)\"$")
    public void openurl_url(String arg1)   {
        // Write code here that turns the phrase above into concrete actions
        System.out.format("Test: %n\n", arg1);

    }

    @Given("^I have (\\d+) cukes in my belly")
    public void I_have_cukes_in_my_belly(int cukes) {
        System.out.format("Cukes: %n\n", cukes);
    }
}
